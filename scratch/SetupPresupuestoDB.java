package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupPresupuestoDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Deploying Stored Procedures for Presupuesto (Budgets)...");

            // 1. Redefine CG_ListaPresupuestosActivos_SP
            System.out.println("Deploying CG_ListaPresupuestosActivos_SP...");
            stmt.execute("IF OBJECT_ID('CG_ListaPresupuestosActivos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaPresupuestosActivos_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ListaPresupuestosActivos_SP\n" +
                "    @idUsuario INT,\n" +
                "    @idUsuarioOwner INT,\n" +
                "    @idFiltradoUsuario INT = 0,\n" +
                "    @fechaInicio VARCHAR(10) = NULL,\n" +
                "    @fechaFin VARCHAR(10) = NULL,\n" +
                "    @idCategoria INT = 0,\n" +
                "    @idEstado INT = 0\n" +
                "AS\n" +
                "BEGIN\n" +
                "    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);\n" +
                "    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);\n" +
                "\n" +
                "    DECLARE @UserIds TABLE (idUsuario INT);\n" +
                "    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0\n" +
                "    BEGIN\n" +
                "        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);\n" +
                "        INSERT INTO @UserIds (idUsuario)\n" +
                "        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;\n" +
                "    END\n" +
                "    ELSE\n" +
                "    BEGIN\n" +
                "        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);\n" +
                "    END;\n" +
                "\n" +
                "    ;WITH CTE AS (\n" +
                "        SELECT\n" +
                "            PR.idPresupuesto\n" +
                "            , CA.cDescripcion nombreCategoria\n" +
                "            , PR.nLimPresuMens montoAsignado\n" +
                "            , ISNULL((\n" +
                "                SELECT SUM(GA.nMonto)\n" +
                "                FROM FINANZAS.GASTOS GA\n" +
                "                WHERE GA.idCategoria = PR.idCategoria\n" +
                "                  AND GA.lEsActivo = 1\n" +
                "                  AND GA.idUsuReg IN (SELECT idUsuario FROM @UserIds)\n" +
                "                  AND GA.cFechaGasto BETWEEN @fechaInicio AND @fechaFin\n" +
                "              ), 0) AS montoGastado\n" +
                "            , CASE\n" +
                "                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) > 100\n" +
                "                      THEN 2 -- EXCEDIDO\n" +
                "                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) >= 70\n" +
                "                      THEN 4 -- ADVERTENCIA\n" +
                "                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) <= 30\n" +
                "                      THEN 3 -- BAJO\n" +
                "                  ELSE 1 -- EN CAMINO\n" +
                "              END AS idEstadoCalculado\n" +
                "            , CONCAT(\n" +
                "                ISNULL(CAST(ROUND((ISNULL((\n" +
                "                    SELECT SUM(GA.nMonto)\n" +
                "                    FROM FINANZAS.GASTOS GA\n" +
                "                    WHERE GA.idCategoria = PR.idCategoria\n" +
                "                      AND GA.lEsActivo = 1\n" +
                "                      AND GA.idUsuReg IN (SELECT idUsuario FROM @UserIds)\n" +
                "                      AND GA.cFechaGasto BETWEEN @fechaInicio AND @fechaFin\n" +
                "                  ), 0) * 100.0) / NULLIF(PR.nLimPresuMens,0), 2)\n" +
                "                        AS DECIMAL(5,1)), 0)\n" +
                "                , '%') AS progresoPresupuesto\n" +
                "        FROM FINANZAS.PRESUPUESTO PR\n" +
                "        INNER JOIN MANTENEDOR.CATEGORIAS CA\n" +
                "            ON CA.idCategoria = PR.idCategoria\n" +
                "        WHERE PR.lEsActivo = 1\n" +
                "          AND PR.idUsuReg = @idUsuarioOwner\n" +
                "          AND (@idCategoria = 0 OR PR.idCategoria = @idCategoria)\n" +
                "    )\n" +
                "    SELECT \n" +
                "        C.idPresupuesto,\n" +
                "        C.nombreCategoria,\n" +
                "        C.montoAsignado,\n" +
                "        C.montoGastado,\n" +
                "        EP.cDescripcion AS estadoPresupuesto,\n" +
                "        C.progresoPresupuesto\n" +
                "    FROM CTE C\n" +
                "    INNER JOIN MANTENEDOR.ESTADO_PRESUPUESTO EP\n" +
                "        ON EP.idEstadoPresupuesto = C.idEstadoCalculado\n" +
                "    WHERE (@idEstado = 0 OR C.idEstadoCalculado = @idEstado);\n" +
                "END;"
            );

            // 2. Define CG_ObtenerPresupuestoActividades_SP
            System.out.println("Deploying CG_ObtenerPresupuestoActividades_SP...");
            stmt.execute("IF OBJECT_ID('CG_ObtenerPresupuestoActividades_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerPresupuestoActividades_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ObtenerPresupuestoActividades_SP\n" +
                "    @idUsuarioOwner INT,\n" +
                "    @idFiltradoUsuario INT = 0,\n" +
                "    @fechaInicio VARCHAR(10) = NULL,\n" +
                "    @fechaFin VARCHAR(10) = NULL\n" +
                "AS\n" +
                "BEGIN\n" +
                "    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);\n" +
                "    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);\n" +
                "\n" +
                "    DECLARE @UserIds TABLE (idUsuario INT);\n" +
                "    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0\n" +
                "    BEGIN\n" +
                "        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);\n" +
                "        INSERT INTO @UserIds (idUsuario)\n" +
                "        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;\n" +
                "    END\n" +
                "    ELSE\n" +
                "    BEGIN\n" +
                "        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);\n" +
                "    END;\n" +
                "\n" +
                "    SELECT TOP 3\n" +
                "        g.idGasto,\n" +
                "        g.cGasto,\n" +
                "        c.cDescripcion AS nombreCategoria,\n" +
                "        g.nMonto AS montoGasto,\n" +
                "        g.cFechaGasto,\n" +
                "        p.nLimPresuMens AS limitePresupuesto,\n" +
                "        p.nUmbralAlerta AS umbralAlerta,\n" +
                "        ISNULL((\n" +
                "            SELECT SUM(g2.nMonto)\n" +
                "            FROM FINANZAS.GASTOS g2\n" +
                "            WHERE g2.idCategoria = g.idCategoria\n" +
                "              AND g2.lEsActivo = 1\n" +
                "              AND g2.idUsuReg IN (SELECT idUsuario FROM @UserIds)\n" +
                "              AND g2.cFechaGasto BETWEEN @fechaInicio AND @fechaFin\n" +
                "        ), 0) AS totalGastadoMes\n" +
                "    FROM FINANZAS.GASTOS g\n" +
                "    INNER JOIN FINANZAS.PRESUPUESTO p ON g.idCategoria = p.idCategoria AND p.lEsActivo = 1\n" +
                "    INNER JOIN MANTENEDOR.CATEGORIAS c ON g.idCategoria = c.idCategoria\n" +
                "    WHERE g.lEsActivo = 1\n" +
                "      AND p.idUsuReg = @idUsuarioOwner\n" +
                "      AND g.idUsuReg = @idUsuarioOwner\n" +
                "      AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds)\n" +
                "      AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin\n" +
                "    ORDER BY g.cFechaGasto DESC, g.idGasto DESC;\n" +
                "END;"
            );

            // 3. Define CG_GuardaPresupuestoIngresado_SP
            System.out.println("Deploying CG_GuardaPresupuestoIngresado_SP...");
            stmt.execute("IF OBJECT_ID('CG_GuardaPresupuestoIngresado_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaPresupuestoIngresado_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_GuardaPresupuestoIngresado_SP (\n" +
                "    @xmlPresupuesto XML,\n" +
                "    @idUsuario INT\n" +
                ") AS\n" +
                "BEGIN\n" +
                "    DECLARE @fecActual DATE = GETDATE();\n" +
                "    DECLARE @fecIniMes DATE = DATEADD(DAY, 1, EOMONTH(@fecActual, -1));\n" +
                "    DECLARE @fecFinMes DATE = EOMONTH(@fecActual);\n" +
                "\n" +
                "    DECLARE @nTrancount INT = @@TRANCOUNT;\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpPresupuesto TABLE (\n" +
                "          limPresuMens DECIMAL(15,2)\n" +
                "        , umbralAlerta TINYINT\n" +
                "        , idCategoria INT\n" +
                "        , idTipoPeriodo INT\n" +
                "    )\n" +
                "\n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlPresupuesto \n" +
                "    INSERT INTO @tmpPresupuesto\n" +
                "    SELECT\n" +
                "        limPresuMens\n" +
                "        , umbralAlerta\n" +
                "        , idCategoria\n" +
                "        , idTipoPeriodo\n" +
                "    FROM OPENXML(@idoc, '/presupuesto', 2)\n" +
                "    WITH (\n" +
                "          limPresuMens DECIMAL(15,2)\n" +
                "        , umbralAlerta TINYINT\n" +
                "        , idCategoria INT\n" +
                "        , idTipoPeriodo INT\n" +
                "    );\n" +
                "\n" +
                "    IF @nTrancount = 0\n" +
                "    BEGIN\n" +
                "        BEGIN TRANSACTION;\n" +
                "    END;\n" +
                "\n" +
                "    BEGIN TRY\n" +
                "        IF EXISTS(\n" +
                "            SELECT 1 FROM @tmpPresupuesto TMP \n" +
                "            INNER JOIN FINANZAS.PRESUPUESTO PR \n" +
                "            ON PR.idCategoria = TMP.idCategoria\n" +
                "            AND PR.lEsActivo = 1\n" +
                "            AND PR.idUsuReg = @idUsuario)\n" +
                "        BEGIN\n" +
                "            SELECT cMsje = 'La categoria seleccionada ya existe en el presupuesto.';\n" +
                "            RETURN;\n" +
                "        END\n" +
                "\n" +
                "        INSERT INTO FINANZAS.PRESUPUESTO (\n" +
                "              idCategoria\n" +
                "            , idEstadoPresupuesto\n" +
                "            , idTipoPeriodo\n" +
                "            , nLimPresuMens\n" +
                "            , nUmbralAlerta\n" +
                "            , idUsuReg\n" +
                "        ) SELECT\n" +
                "            tmp.idCategoria\n" +
                "            , CASE\n" +
                "                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) > 100\n" +
                "                    THEN 2 -- EXCEDIDO\n" +
                "                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) >= 70\n" +
                "                    THEN 4 -- ADVERTENCIA\n" +
                "                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) <= 30\n" +
                "                    THEN 3 -- BAJO\n" +
                "                ELSE 1 -- EN CAMINO\n" +
                "            END AS idEstadoPresupuesto\n" +
                "            , idTipoPeriodo\n" +
                "            , limPresuMens\n" +
                "            , umbralAlerta\n" +
                "            , @idUsuario\n" +
                "        FROM @tmpPresupuesto tmp\n" +
                "        INNER JOIN MANTENEDOR.CATEGORIAS CA\n" +
                "            ON CA.idCategoria = tmp.idCategoria\n" +
                "        LEFT JOIN FINANZAS.GASTOS GA\n" +
                "            ON GA.idCategoria = tmp.idCategoria\n" +
                "                AND GA.cFechaGasto BETWEEN @fecIniMes AND @fecFinMes\n" +
                "                AND GA.lEsActivo = 1\n" +
                "        GROUP BY tmp.idCategoria\n" +
                "            , idTipoPeriodo\n" +
                "            , limPresuMens\n" +
                "            , umbralAlerta;\n" +
                "        \n" +
                "        IF @nTrancount = 0\n" +
                "        BEGIN\n" +
                "            COMMIT TRANSACTION;\n" +
                "        END;\n" +
                "\n" +
                "        SELECT cMsje = 'Se ha realizado la operacion correctamente.';\n" +
                "    END TRY\n" +
                "    BEGIN CATCH\n" +
                "        IF @@TRANCOUNT > 0\n" +
                "            ROLLBACK TRANSACTION;\n" +
                "        \n" +
                "        SELECT cMsje = ERROR_MESSAGE();\n" +
                "    END CATCH;\n" +
                "END;"
            );

            System.out.println("Stored procedures for Budget deployed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
