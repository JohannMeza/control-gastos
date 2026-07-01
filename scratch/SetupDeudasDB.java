package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupDeudasDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            try {
                System.out.println("Creating table FINANZAS.DEUDAS...");
                stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'DEUDAS' AND s.name = 'FINANZAS')\n" +
                             "CREATE TABLE FINANZAS.DEUDAS (\n" +
                             "    idDeuda INT IDENTITY(1,1) PRIMARY KEY,\n" +
                             "    cAcreedor NVARCHAR(150) NOT NULL,\n" +
                             "    nMontoTotal DECIMAL(15,2) NOT NULL,\n" +
                             "    nTasaInteres DECIMAL(5,2) NOT NULL,\n" +
                             "    nCuotasTotales INT NOT NULL,\n" +
                             "    nCuotasPagadas INT DEFAULT 0,\n" +
                             "    dFechaInicio DATE NOT NULL,\n" +
                             "    lEsActivo BIT DEFAULT 1,\n" +
                             "    idUsuario INT FOREIGN KEY REFERENCES usuarios(id),\n" +
                             "    idUsuReg INT,\n" +
                             "    dFecReg DATETIME DEFAULT GETDATE(),\n" +
                             "    idUsuMod INT,\n" +
                             "    dFecMod DATETIME\n" +
                             ");");

                System.out.println("Creating table FINANZAS.ABONO_DEUDAS...");
                stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'ABONO_DEUDAS' AND s.name = 'FINANZAS')\n" +
                             "CREATE TABLE FINANZAS.ABONO_DEUDAS (\n" +
                             "    idAbono INT IDENTITY(1,1) PRIMARY KEY,\n" +
                             "    idDeuda INT FOREIGN KEY REFERENCES FINANZAS.DEUDAS(idDeuda),\n" +
                             "    nMontoAbono DECIMAL(15,2) NOT NULL,\n" +
                             "    dFechaPago DATE NOT NULL,\n" +
                             "    lEsActivo BIT DEFAULT 1,\n" +
                             "    idUsuReg INT FOREIGN KEY REFERENCES usuarios(id),\n" +
                             "    dFecReg DATETIME DEFAULT GETDATE(),\n" +
                             "    idUsuMod INT,\n" +
                             "    dFecMod DATETIME\n" +
                             ");");

                System.out.println("Inserting initial mock deudas if not exists...");
                stmt.execute("IF (SELECT COUNT(*) FROM FINANZAS.DEUDAS) = 0\n" +
                             "BEGIN\n" +
                             "    INSERT INTO FINANZAS.DEUDAS (cAcreedor, nMontoTotal, nTasaInteres, nCuotasTotales, nCuotasPagadas, dFechaInicio, idUsuario, idUsuReg) VALUES\n" +
                             "    ('Pr\u00e9stamo Hipotecario - Banco BBVA', 120000.00, 4.5, 240, 45, '2023-01-10', 1, 1),\n" +
                             "    ('Tarjeta de Cr\u00e9dito Oro - Visa', 4500.00, 22.0, 12, 1, '2024-05-15', 1, 1),\n" +
                             "    ('Pr\u00e9stamo Automotriz - Ford Credit', 25000.00, 6.0, 48, 30, '2023-09-20', 1, 1);\n" +
                             "END;");

                System.out.println("Inserting initial mock abonos if not exists...");
                stmt.execute("IF (SELECT COUNT(*) FROM FINANZAS.ABONO_DEUDAS) = 0\n" +
                             "BEGIN\n" +
                             "    DECLARE @idBBVA INT = (SELECT TOP 1 idDeuda FROM FINANZAS.DEUDAS WHERE cAcreedor LIKE '%BBVA%');\n" +
                             "    DECLARE @idVisa INT = (SELECT TOP 1 idDeuda FROM FINANZAS.DEUDAS WHERE cAcreedor LIKE '%Visa%');\n" +
                             "    DECLARE @idFord INT = (SELECT TOP 1 idDeuda FROM FINANZAS.DEUDAS WHERE cAcreedor LIKE '%Ford%');\n" +
                             "    \n" +
                             "    INSERT INTO FINANZAS.ABONO_DEUDAS (idDeuda, nMontoAbono, dFechaPago, idUsuReg) VALUES\n" +
                             "    (@idBBVA, 500.00, '2024-05-10', 1),\n" +
                             "    (@idVisa, 150.00, '2024-05-12', 1),\n" +
                             "    (@idFord, 520.00, '2024-05-20', 1);\n" +
                             "END;");
            } catch (Exception ex) {
                System.out.println("Warning: Seeding/creating tables failed: " + ex.getMessage());
            }

            System.out.println("Creating stored procedures for Deudas...");

            // 1. CG_ListaCboDeudasActivas_SP
            stmt.execute("IF OBJECT_ID('CG_ListaCboDeudasActivas_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaCboDeudasActivas_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ListaCboDeudasActivas_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT\n" +
                "        idDeuda AS value\n" +
                "        , cAcreedor + ' (S/. ' + CAST(CAST(nMontoTotal AS DECIMAL(10,2)) AS VARCHAR) + ')' AS label\n" +
                "    FROM FINANZAS.DEUDAS\n" +
                "    WHERE lEsActivo = 1\n" +
                "      AND idUsuario = @idUsuario\n" +
                "      AND nCuotasPagadas < nCuotasTotales;\n" +
                "END;"
            );

            // 2. CG_ListaDeudasPorUsuario_SP
            stmt.execute("IF OBJECT_ID('CG_ListaDeudasPorUsuario_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaDeudasPorUsuario_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ListaDeudasPorUsuario_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT \n" +
                "        d.idDeuda,\n" +
                "        d.cAcreedor AS acreedor,\n" +
                "        d.nMontoTotal AS montoTotal,\n" +
                "        d.nTasaInteres AS tasaInteres,\n" +
                "        d.nCuotasTotales AS cuotasTotales,\n" +
                "        d.nCuotasPagadas AS cuotasPagadas,\n" +
                "        d.dFechaInicio AS fechaInicio,\n" +
                "        FORMAT(d.dFechaInicio, 'dd/MM/yyyy') AS fechaInicioFormat,\n" +
                "        -- Calcular montos pagados y restantes basándose en cuotas o abonos reales\n" +
                "        ISNULL((SELECT SUM(nMontoAbono) FROM FINANZAS.ABONO_DEUDAS WHERE idDeuda = d.idDeuda AND lEsActivo = 1), 0) AS montoPagado,\n" +
                "        (d.nMontoTotal - ISNULL((SELECT SUM(nMontoAbono) FROM FINANZAS.ABONO_DEUDAS WHERE idDeuda = d.idDeuda AND lEsActivo = 1), 0)) AS montoRestante,\n" +
                "        -- Determinar el estado\n" +
                "        CASE \n" +
                "            WHEN d.nCuotasPagadas >= d.nCuotasTotales THEN 'PAGADO'\n" +
                "            WHEN d.cAcreedor LIKE '%Visa%' THEN 'PRÓXIMO A VENCER'\n" +
                "            WHEN d.nCuotasPagadas % 10 = 0 THEN 'PENDIENTE'\n" +
                "            ELSE 'AL DÍA'\n" +
                "        END AS estado,\n" +
                "        d.idUsuario\n" +
                "    FROM FINANZAS.DEUDAS d\n" +
                "    WHERE d.lEsActivo = 1 AND d.idUsuario = @idUsuario\n" +
                "    ORDER BY d.dFechaInicio DESC;\n" +
                "END;"
            );

            // 3. CG_ObtenerDeudaPorId_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerDeudaPorId_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerDeudaPorId_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ObtenerDeudaPorId_SP\n" +
                "    @idDeuda INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT \n" +
                "        idDeuda,\n" +
                "        cAcreedor AS acreedor,\n" +
                "        nMontoTotal AS montoTotal,\n" +
                "        nTasaInteres AS tasaInteres,\n" +
                "        nCuotasTotales AS cuotasTotales,\n" +
                "        nCuotasPagadas AS cuotasPagadas,\n" +
                "        dFechaInicio AS fechaInicio,\n" +
                "        idUsuario\n" +
                "    FROM FINANZAS.DEUDAS\n" +
                "    WHERE idDeuda = @idDeuda;\n" +
                "END;"
            );

            // 4. CG_EliminarDeudaPorId_SP
            stmt.execute("IF OBJECT_ID('CG_EliminarDeudaPorId_SP', 'P') IS NOT NULL DROP PROCEDURE CG_EliminarDeudaPorId_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_EliminarDeudaPorId_SP\n" +
                "    @idDeuda INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    BEGIN TRY\n" +
                "        UPDATE FINANZAS.DEUDAS SET lEsActivo = 0 WHERE idDeuda = @idDeuda;\n" +
                "        -- También desactivar los abonos asociados\n" +
                "        UPDATE FINANZAS.ABONO_DEUDAS SET lEsActivo = 0 WHERE idDeuda = @idDeuda;\n" +
                "        SELECT cMsje = 'Se ha realizado la operación correctamente.';\n" +
                "    END TRY\n" +
                "    BEGIN CATCH\n" +
                "        SELECT cMsje = ERROR_MESSAGE();\n" +
                "    END CATCH\n" +
                "END;"
            );

            // 5. CG_GuardaDeudaIngresada_SP
            stmt.execute("IF OBJECT_ID('CG_GuardaDeudaIngresada_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaDeudaIngresada_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_GuardaDeudaIngresada_SP\n" +
                "    @xmlDeuda XML\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpDeuda TABLE (\n" +
                "        acreedor NVARCHAR(150),\n" +
                "        montoTotal DECIMAL(15,2),\n" +
                "        tasaInteres DECIMAL(5,2),\n" +
                "        cuotasTotales INT,\n" +
                "        fechaInicio DATE,\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlDeuda;\n" +
                "    INSERT INTO @tmpDeuda\n" +
                "    SELECT acreedor, montoTotal, tasaInteres, cuotasTotales, CAST(CAST(fechaInicio AS DATETIMEOFFSET) AS DATE), idUsuario\n" +
                "    FROM OPENXML(@idoc, '/deuda', 2)\n" +
                "    WITH (\n" +
                "        acreedor VARCHAR(150),\n" +
                "        montoTotal DECIMAL(15,2),\n" +
                "        tasaInteres DECIMAL(5,2),\n" +
                "        cuotasTotales INT,\n" +
                "        fechaInicio DATE,\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    EXEC sp_xml_removedocument @idoc;\n" +
                "    \n" +
                "    BEGIN TRANSACTION;\n" +
                "    BEGIN TRY\n" +
                "        INSERT INTO FINANZAS.DEUDAS (cAcreedor, nMontoTotal, nTasaInteres, nCuotasTotales, nCuotasPagadas, dFechaInicio, idUsuario, idUsuReg)\n" +
                "        SELECT acreedor, montoTotal, tasaInteres, cuotasTotales, 0, fechaInicio, idUsuario, idUsuario\n" +
                "        FROM @tmpDeuda;\n" +
                "        \n" +
                "        COMMIT TRANSACTION;\n" +
                "        SELECT cMsje = 'Se ha realizado la operación correctamente.';\n" +
                "    END TRY\n" +
                "    BEGIN CATCH\n" +
                "        ROLLBACK TRANSACTION;\n" +
                "        SELECT cMsje = ERROR_MESSAGE();\n" +
                "    END CATCH\n" +
                "END;"
            );

            // 6. CG_ActualizaDeudaIngresada_SP
            stmt.execute("IF OBJECT_ID('CG_ActualizaDeudaIngresada_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ActualizaDeudaIngresada_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ActualizaDeudaIngresada_SP\n" +
                "    @xmlDeuda XML\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpDeuda TABLE (\n" +
                "        idDeuda INT,\n" +
                "        acreedor NVARCHAR(150),\n" +
                "        montoTotal DECIMAL(15,2),\n" +
                "        tasaInteres DECIMAL(5,2),\n" +
                "        cuotasTotales INT,\n" +
                "        cuotasPagadas INT,\n" +
                "        fechaInicio DATE,\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlDeuda;\n" +
                "    INSERT INTO @tmpDeuda\n" +
                "    SELECT idDeuda, acreedor, montoTotal, tasaInteres, cuotasTotales, cuotasPagadas, CAST(CAST(fechaInicio AS DATETIMEOFFSET) AS DATE), idUsuario\n" +
                "    FROM OPENXML(@idoc, '/deuda', 2)\n" +
                "    WITH (\n" +
                "        idDeuda INT,\n" +
                "        acreedor VARCHAR(150),\n" +
                "        montoTotal DECIMAL(15,2),\n" +
                "        tasaInteres DECIMAL(5,2),\n" +
                "        cuotasTotales INT,\n" +
                "        cuotasPagadas INT,\n" +
                "        fechaInicio DATE,\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    EXEC sp_xml_removedocument @idoc;\n" +
                "    \n" +
                "    BEGIN TRANSACTION;\n" +
                "    BEGIN TRY\n" +
                "        UPDATE d\n" +
                "        SET d.cAcreedor = t.acreedor,\n" +
                "            d.nMontoTotal = t.montoTotal,\n" +
                "            d.nTasaInteres = t.tasaInteres,\n" +
                "            d.nCuotasTotales = t.cuotasTotales,\n" +
                "            d.nCuotasPagadas = t.cuotasPagadas,\n" +
                "            d.dFechaInicio = t.fechaInicio,\n" +
                "            d.idUsuMod = t.idUsuario,\n" +
                "            d.dFecMod = GETDATE()\n" +
                "        FROM FINANZAS.DEUDAS d\n" +
                "        INNER JOIN @tmpDeuda t ON d.idDeuda = t.idDeuda;\n" +
                "        \n" +
                "        COMMIT TRANSACTION;\n" +
                "        SELECT cMsje = 'Se ha realizado la operación correctamente.';\n" +
                "    END TRY\n" +
                "    BEGIN CATCH\n" +
                "        ROLLBACK TRANSACTION;\n" +
                "        SELECT cMsje = ERROR_MESSAGE();\n" +
                "    END CATCH\n" +
                "END;"
            );

            // 7. CG_GuardaAbonoIngresado_SP
            stmt.execute("IF OBJECT_ID('CG_GuardaAbonoIngresado_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaAbonoIngresado_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_GuardaAbonoIngresado_SP\n" +
                "    @xmlAbono XML\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpAbono TABLE (\n" +
                "        idDeuda INT,\n" +
                "        montoAbono DECIMAL(15,2),\n" +
                "        fechaPago DATE,\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAbono;\n" +
                "    \n" +
                "    INSERT INTO @tmpAbono\n" +
                "    SELECT\n" +
                "        idDeuda\n" +
                "        , montoAbono\n" +
                "        , CAST(CAST(fechaPago AS DATETIMEOFFSET) AS DATE)\n" +
                "        , 1\n" +
                "    FROM OPENXML(@idoc, '/abonoDeuda', 2)\n" +
                "    WITH (\n" +
                "        idDeuda INT,\n" +
                "        montoAbono DECIMAL(15,2),\n" +
                "        fechaPago DATE\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_removedocument @idoc;\n" +
                "    \n" +
                "    BEGIN TRANSACTION;\n" +
                "    BEGIN TRY\n" +
                "        DECLARE @idDeuda INT = (SELECT idDeuda FROM @tmpAbono);\n" +
                "        DECLARE @monto DECIMAL(15,2) = (SELECT montoAbono FROM @tmpAbono);\n" +
                "        DECLARE @fecha DATE = (SELECT fechaPago FROM @tmpAbono);\n" +
                "        \n" +
                "        -- Obtener idUsuario de la deuda para asignarlo al abono\n" +
                "        DECLARE @idUsuario INT = (SELECT idUsuario FROM FINANZAS.DEUDAS WHERE idDeuda = @idDeuda);\n" +
                "        \n" +
                "        -- Registrar el abono con el usuario correcto\n" +
                "        INSERT INTO FINANZAS.ABONO_DEUDAS (idDeuda, nMontoAbono, dFechaPago, idUsuReg)\n" +
                "        VALUES (@idDeuda, @monto, @fecha, @idUsuario);\n" +
                "        \n" +
                "        -- Incrementar cuotas pagadas en DEUDAS\n" +
                "        UPDATE FINANZAS.DEUDAS \n" +
                "        SET nCuotasPagadas = nCuotasPagadas + 1 \n" +
                "        WHERE idDeuda = @idDeuda;\n" +
                "        \n" +
                "        COMMIT TRANSACTION;\n" +
                "        SELECT cMsje = 'Se ha realizado la operación correctamente.';\n" +
                "    END TRY\n" +
                "    BEGIN CATCH\n" +
                "        ROLLBACK TRANSACTION;\n" +
                "        SELECT cMsje = ERROR_MESSAGE();\n" +
                "    END CATCH\n" +
                "END;"
            );

            // 8. CG_ObtenerDeudaResumen_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerDeudaResumen_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerDeudaResumen_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ObtenerDeudaResumen_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @totalDeuda DECIMAL(15,2) = 0;\n" +
                "    DECLARE @totalPagadoEsteMes DECIMAL(15,2) = 0;\n" +
                "    \n" +
                "    -- Suma de saldos pendientes de todas las deudas activas del usuario\n" +
                "    SELECT @totalDeuda = ISNULL(SUM(d.nMontoTotal), 0) - \n" +
                "        ISNULL((\n" +
                "            SELECT SUM(a.nMontoAbono)\n" +
                "            FROM FINANZAS.ABONO_DEUDAS a\n" +
                "            INNER JOIN FINANZAS.DEUDAS d2\n" +
                "                ON a.idDeuda = d2.idDeuda\n" +
                "            WHERE a.lEsActivo = 1\n" +
                "              AND d2.lEsActivo = 1\n" +
                "              AND d2.idUsuario = @idUsuario\n" +
                "        ), 0)\n" +
                "    FROM FINANZAS.DEUDAS d\n" +
                "    WHERE d.lEsActivo = 1\n" +
                "      AND d.idUsuario = @idUsuario;\n" +
                "    \n" +
                "    -- Suma de pagos realizados este mes\n" +
                "    SELECT @totalPagadoEsteMes = ISNULL(SUM(a.nMontoAbono), 0)\n" +
                "    FROM FINANZAS.ABONO_DEUDAS a\n" +
                "    INNER JOIN FINANZAS.DEUDAS d\n" +
                "        ON a.idDeuda = d.idDeuda\n" +
                "    WHERE a.lEsActivo = 1 \n" +
                "      AND d.idUsuario = @idUsuario\n" +
                "      AND MONTH(a.dFechaPago) = MONTH(GETDATE())\n" +
                "      AND YEAR(a.dFechaPago) = YEAR(GETDATE());\n" +
                "      \n" +
                "    -- Obtener la fecha del próximo vencimiento de la cuota activa más cercana\n" +
                "    DECLARE @proximoVencimiento DATE = (\n" +
                "        SELECT MIN(DATEADD(month, nCuotasPagadas, dFechaInicio)) \n" +
                "        FROM FINANZAS.DEUDAS \n" +
                "        WHERE idUsuario = @idUsuario \n" +
                "          AND lEsActivo = 1 \n" +
                "          AND nCuotasPagadas < nCuotasTotales\n" +
                "    );\n" +
                "      \n" +
                "    SELECT \n" +
                "        ISNULL(@totalDeuda, 0) AS totalDeuda,\n" +
                "        COALESCE(CONVERT(VARCHAR(10), @proximoVencimiento, 103), 'Sin vencimientos') AS proximoVencimiento,\n" +
                "        ISNULL(@totalPagadoEsteMes, 0) AS totalPagadoEsteMes;\n" +
                "END;"
            );

            // 9. CG_ListaHistorialAbonos_SP
            stmt.execute("IF OBJECT_ID('CG_ListaHistorialAbonos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaHistorialAbonos_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ListaHistorialAbonos_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT \n" +
                "        a.dFechaPago AS fecha,\n" +
                "        FORMAT(a.dFechaPago, 'dd/MM/yyyy') AS fechaFormat,\n" +
                "        d.cAcreedor AS deuda,\n" +
                "        a.nMontoAbono AS montoPagado,\n" +
                "        -- Saldo restante estimado de esa deuda\n" +
                "        (d.nMontoTotal - ISNULL((\n" +
                "            SELECT SUM(sub.nMontoAbono) \n" +
                "            FROM FINANZAS.ABONO_DEUDAS sub \n" +
                "            WHERE sub.idDeuda = d.idDeuda \n" +
                "              AND sub.dFechaPago <= a.dFechaPago \n" +
                "              AND sub.lEsActivo = 1\n" +
                "         ), 0)) AS saldoRestante\n" +
                "    FROM FINANZAS.ABONO_DEUDAS a\n" +
                "    INNER JOIN FINANZAS.DEUDAS d\n" +
                "        ON a.idDeuda = d.idDeuda\n" +
                "    WHERE a.lEsActivo = 1\n" +
                "      AND d.idUsuario = @idUsuario\n" +
                "    ORDER BY a.dFechaPago DESC;\n" +
                "END;"
            );

            System.out.println("Database setup for Deudas completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
