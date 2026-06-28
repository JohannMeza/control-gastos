package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupAhorrosDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Creating table FINANZAS.METAS_AHORRO...");
            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'METAS_AHORRO' AND s.name = 'FINANZAS')\n" +
                         "CREATE TABLE FINANZAS.METAS_AHORRO (\n" +
                         "    idMeta INT IDENTITY(1,1) PRIMARY KEY,\n" +
                         "    cDescripcion NVARCHAR(100) NOT NULL,\n" +
                         "    nMontoObjetivo DECIMAL(15,2) NOT NULL,\n" +
                         "    nMontoActual DECIMAL(15,2) DEFAULT 0.00,\n" +
                         "    dFechaEstimada DATE,\n" +
                         "    cIconPath NVARCHAR(200),\n" +
                         "    lEsActivo BIT DEFAULT 1,\n" +
                         "    idUsuReg INT,\n" +
                         "    dFecReg DATETIME DEFAULT GETDATE(),\n" +
                         "    idUsuMod INT,\n" +
                         "    dFecMod DATETIME,\n" +
                         "    nAsignacionMensual DECIMAL(15,2) NOT NULL DEFAULT 100.00\n" +
                         ");\n" +
                         "ELSE\n" +
                         "BEGIN\n" +
                         "    IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.METAS_AHORRO') AND name = 'nAsignacionMensual')\n" +
                         "    BEGIN\n" +
                         "        ALTER TABLE FINANZAS.METAS_AHORRO ADD nAsignacionMensual DECIMAL(15,2) NOT NULL DEFAULT 100.00;\n" +
                         "    END\n" +
                         "END;");
            
            System.out.println("Inserting 'Ahorro' category if not exists...");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM MANTENEDOR.CATEGORIAS WHERE UPPER(cDescripcion) = 'AHORRO')\n" +
                         "INSERT INTO MANTENEDOR.CATEGORIAS (cDescripcion, cDescripcionLarga, lEsActivo, idUsuReg, dFecReg) \n" +
                         "VALUES ('Ahorro', 'Presupuesto destinado a Ahorros', 1, 1, GETDATE());");

            System.out.println("Inserting initial metas if not exists...");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Fondo Emergencia')\n" +
                         "INSERT INTO FINANZAS.METAS_AHORRO (cDescripcion, nMontoObjetivo, nMontoActual, dFechaEstimada, cIconPath, idUsuReg, nAsignacionMensual) \n" +
                         "VALUES ('Fondo Emergencia', 5000.00, 4750.00, '2024-08-31', '/images/icon/blue/ahorros.png', 1, 500.00);");
            
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Vacaciones')\n" +
                         "INSERT INTO FINANZAS.METAS_AHORRO (cDescripcion, nMontoObjetivo, nMontoActual, dFechaEstimada, cIconPath, idUsuReg, nAsignacionMensual) \n" +
                         "VALUES ('Vacaciones', 3000.00, 1350.00, '2024-12-31', '/images/aplicaciones.png', 1, 200.00);");
            
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Auto Nuevo')\n" +
                         "INSERT INTO FINANZAS.METAS_AHORRO (cDescripcion, nMontoObjetivo, nMontoActual, dFechaEstimada, cIconPath, idUsuReg, nAsignacionMensual) \n" +
                         "VALUES ('Auto Nuevo', 25000.00, 3750.00, '2026-06-30', '/images/deudas.png', 1, 300.00);");

            System.out.println("Creating table FINANZAS.AHORROS...");
            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'AHORROS' AND s.name = 'FINANZAS')\n" +
                         "CREATE TABLE FINANZAS.AHORROS (\n" +
                         "    idAhorro INT IDENTITY(1,1) PRIMARY KEY,\n" +
                         "    idMeta INT FOREIGN KEY REFERENCES FINANZAS.METAS_AHORRO(idMeta),\n" +
                         "    nMonto DECIMAL(15,2) NOT NULL,\n" +
                         "    dFecha DATE NOT NULL,\n" +
                         "    cDescripcion NVARCHAR(300),\n" +
                         "    lEsActivo BIT DEFAULT 1,\n" +
                         "    idUsuReg INT FOREIGN KEY REFERENCES usuarios(id),\n" +
                         "    dFecReg DATETIME DEFAULT GETDATE(),\n" +
                         "    idUsuMod INT,\n" +
                         "    dFecMod DATETIME\n" +
                         ");");

            System.out.println("Inserting initial mock savings if not exists...");
            stmt.execute("IF (SELECT COUNT(*) FROM FINANZAS.AHORROS) = 0\n" +
                         "BEGIN\n" +
                         "    DECLARE @idMetaEmergencia INT = (SELECT idMeta FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Fondo Emergencia');\n" +
                         "    DECLARE @idMetaVacaciones INT = (SELECT idMeta FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Vacaciones');\n" +
                         "    DECLARE @idMetaAuto INT = (SELECT idMeta FROM FINANZAS.METAS_AHORRO WHERE cDescripcion = 'Auto Nuevo');\n" +
                         "    INSERT INTO FINANZAS.AHORROS (idMeta, nMonto, dFecha, cDescripcion, idUsuReg) VALUES\n" +
                         "    (@idMetaEmergencia, 1000.00, '2024-01-15', 'Ahorro inicial para emergencias', 1),\n" +
                         "    (@idMetaEmergencia, 2000.00, '2024-02-10', 'Abono extraordinario', 1),\n" +
                         "    (@idMetaEmergencia, 1750.00, '2024-03-05', 'Transferencia mensual programada', 1),\n" +
                         "    (@idMetaVacaciones, 1350.00, '2024-04-12', 'Fondo para vacaciones fin de año', 1),\n" +
                         "    (@idMetaAuto, 3750.00, '2024-05-20', 'Ahorro para la cuota inicial del auto', 1);\n" +
                         "END;");

            System.out.println("Creating stored procedures for Ahorros...");
            
            // 1. CG_ListaCboMetasAhorro_SP
            stmt.execute("IF OBJECT_ID('CG_ListaCboMetasAhorro_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaCboMetasAhorro_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ListaCboMetasAhorro_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT\n" +
                "        idMeta AS value\n" +
                "        , cDescripcion AS label\n" +
                "    FROM FINANZAS.METAS_AHORRO\n" +
                "    WHERE lEsActivo = 1\n" +
                "      AND idUsuReg = @idUsuario;\n" +
                "END;"
            );

            // 2. CG_ListaAhorrosActivos_SP
            stmt.execute("IF OBJECT_ID('CG_ListaAhorrosActivos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaAhorrosActivos_SP;");
            stmt.execute("CREATE PROCEDURE CG_ListaAhorrosActivos_SP @idUsuario INT AS\n" +
                         "BEGIN\n" +
                         "    SELECT \n" +
                         "        a.idAhorro,\n" +
                         "        a.idMeta,\n" +
                         "        m.cDescripcion AS nombreMeta,\n" +
                         "        a.nMonto AS monto,\n" +
                         "        a.dFecha AS fechaAhorro,\n" +
                         "        FORMAT(a.dFecha, 'dd/MM/yyyy') AS fechaAhorroFormat,\n" +
                         "        a.cDescripcion AS descripcion,\n" +
                         "        u.nombre AS nombreUsuario\n" +
                         "    FROM FINANZAS.AHORROS a\n" +
                         "    INNER JOIN FINANZAS.METAS_AHORRO m ON a.idMeta = m.idMeta\n" +
                         "    INNER JOIN usuarios u ON a.idUsuReg = u.id\n" +
                         "    WHERE a.lEsActivo = 1 AND a.idUsuReg = @idUsuario\n" +
                         "    ORDER BY a.dFecha DESC;\n" +
                         "END;");

            // 3. CG_ObtenerAhorroPorId_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerAhorroPorId_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerAhorroPorId_SP;");
            stmt.execute("CREATE PROCEDURE CG_ObtenerAhorroPorId_SP @idAhorro INT AS\n" +
                         "BEGIN\n" +
                         "    SELECT \n" +
                         "        idAhorro,\n" +
                         "        idMeta,\n" +
                         "        nMonto AS monto,\n" +
                         "        dFecha AS fechaAhorro,\n" +
                         "        cDescripcion AS descripcion\n" +
                         "    FROM FINANZAS.AHORROS\n" +
                         "    WHERE idAhorro = @idAhorro;\n" +
                         "END;");

            // 4. CG_EliminarAhorroPorId_SP
            stmt.execute("IF OBJECT_ID('CG_EliminarAhorroPorId_SP', 'P') IS NOT NULL DROP PROCEDURE CG_EliminarAhorroPorId_SP;");
            stmt.execute("CREATE PROCEDURE CG_EliminarAhorroPorId_SP @idAhorro INT AS\n" +
                         "BEGIN\n" +
                         "    BEGIN TRY\n" +
                         "        DECLARE @idMeta INT = (SELECT idMeta FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);\n" +
                         "        DECLARE @monto DECIMAL(15,2) = (SELECT nMonto FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);\n" +
                         "        \n" +
                         "        UPDATE FINANZAS.AHORROS SET lEsActivo = 0 WHERE idAhorro = @idAhorro;\n" +
                         "        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual - @monto WHERE idMeta = @idMeta;\n" +
                         "        \n" +
                         "        SELECT cMsje = 'Se ha realizado la operación correctamente.';\n" +
                         "    END TRY\n" +
                         "    BEGIN CATCH\n" +
                         "        SELECT cMsje = ERROR_MESSAGE();\n" +
                         "    END CATCH\n" +
                         "END;");

            // 5. CG_GuardaAhorroIngresado_SP
            stmt.execute("IF OBJECT_ID('CG_GuardaAhorroIngresado_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaAhorroIngresado_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_GuardaAhorroIngresado_SP\n" +
                "    @xmlAhorro XML\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpAhorro TABLE (\n" +
                "        idMeta INT,\n" +
                "        monto DECIMAL(15,2),\n" +
                "        fechaAhorro DATE,\n" +
                "        descripcion NVARCHAR(300),\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAhorro;\n" +
                "    \n" +
                "    INSERT INTO @tmpAhorro\n" +
                "    SELECT\n" +
                "        idMeta\n" +
                "        , monto\n" +
                "        , CAST(CAST(fechaAhorro AS DATETIMEOFFSET) AS DATE)\n" +
                "        , descripcion\n" +
                "        , idUsuario\n" +
                "    FROM OPENXML(@idoc, '/ahorro', 2)\n" +
                "    WITH (\n" +
                "        idMeta INT,\n" +
                "        monto DECIMAL(15,2),\n" +
                "        fechaAhorro DATE,\n" +
                "        descripcion VARCHAR(300),\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_removedocument @idoc;\n" +
                "    \n" +
                "    BEGIN TRANSACTION;\n" +
                "    BEGIN TRY\n" +
                "        INSERT INTO FINANZAS.AHORROS (idMeta, nMonto, dFecha, cDescripcion, idUsuReg)\n" +
                "        SELECT\n" +
                "            idMeta\n" +
                "            , monto\n" +
                "            , fechaAhorro\n" +
                "            , descripcion\n" +
                "            , idUsuario\n" +
                "        FROM @tmpAhorro;\n" +
                "        \n" +
                "        UPDATE m\n" +
                "        SET m.nMontoActual = m.nMontoActual + t.monto\n" +
                "        FROM FINANZAS.METAS_AHORRO m\n" +
                "        INNER JOIN @tmpAhorro t\n" +
                "            ON m.idMeta = t.idMeta;\n" +
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

            // 6. CG_ActualizaAhorroIngresado_SP
            stmt.execute("IF OBJECT_ID('CG_ActualizaAhorroIngresado_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ActualizaAhorroIngresado_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ActualizaAhorroIngresado_SP\n" +
                "    @xmlAhorro XML\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @idoc INT;\n" +
                "    DECLARE @tmpAhorro TABLE (\n" +
                "        idAhorro INT,\n" +
                "        idMeta INT,\n" +
                "        monto DECIMAL(15,2),\n" +
                "        fechaAhorro DATE,\n" +
                "        descripcion NVARCHAR(300),\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAhorro;\n" +
                "    \n" +
                "    INSERT INTO @tmpAhorro\n" +
                "    SELECT\n" +
                "        idAhorro\n" +
                "        , idMeta\n" +
                "        , monto\n" +
                "        , CAST(CAST(fechaAhorro AS DATETIMEOFFSET) AS DATE)\n" +
                "        , descripcion\n" +
                "        , idUsuario\n" +
                "    FROM OPENXML(@idoc, '/ahorro', 2)\n" +
                "    WITH (\n" +
                "        idAhorro INT,\n" +
                "        idMeta INT,\n" +
                "        monto DECIMAL(15,2),\n" +
                "        fechaAhorro DATE,\n" +
                "        descripcion VARCHAR(300),\n" +
                "        idUsuario INT\n" +
                "    );\n" +
                "    \n" +
                "    EXEC sp_xml_removedocument @idoc;\n" +
                "    \n" +
                "    BEGIN TRANSACTION;\n" +
                "    BEGIN TRY\n" +
                "        DECLARE @idAhorro INT = (SELECT idAhorro FROM @tmpAhorro);\n" +
                "        DECLARE @idMetaOld INT = (SELECT idMeta FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);\n" +
                "        DECLARE @montoOld DECIMAL(15,2) = (SELECT nMonto FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);\n" +
                "        \n" +
                "        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual - @montoOld WHERE idMeta = @idMetaOld;\n" +
                "        \n" +
                "        UPDATE a\n" +
                "        SET a.idMeta = t.idMeta,\n" +
                "            a.nMonto = t.monto,\n" +
                "            a.dFecha = t.fechaAhorro,\n" +
                "            a.cDescripcion = t.descripcion,\n" +
                "            a.idUsuMod = t.idUsuario,\n" +
                "            a.dFecMod = GETDATE()\n" +
                "        FROM FINANZAS.AHORROS a\n" +
                "        INNER JOIN @tmpAhorro t\n" +
                "            ON a.idAhorro = t.idAhorro;\n" +
                "        \n" +
                "        DECLARE @idMetaNew INT = (SELECT idMeta FROM @tmpAhorro);\n" +
                "        DECLARE @montoNew DECIMAL(15,2) = (SELECT monto FROM @tmpAhorro);\n" +
                "        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual + @montoNew WHERE idMeta = @idMetaNew;\n" +
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

            // 7. CG_ObtenerMetasResumen_SP (to load metadata card actual/target dynamic values)
            stmt.execute("IF OBJECT_ID('CG_ObtenerMetasResumen_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerMetasResumen_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ObtenerMetasResumen_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    DECLARE @metaMensualTarget DECIMAL(15,2);\n" +
                "    \n" +
                "    SELECT @metaMensualTarget = ISNULL(PR.nLimPresuMens, 0.00)\n" +
                "    FROM FINANZAS.PRESUPUESTO PR\n" +
                "    INNER JOIN MANTENEDOR.CATEGORIAS CA ON CA.idCategoria = PR.idCategoria\n" +
                "    WHERE PR.idUsuReg = @idUsuario\n" +
                "      AND UPPER(LTRIM(RTRIM(CA.cDescripcion))) = 'AHORRO'\n" +
                "      AND PR.lEsActivo = 1;\n" +
                "      \n" +
                "    IF @metaMensualTarget IS NULL SET @metaMensualTarget = 0.00;\n" +
                "\n" +
                "    SELECT\n" +
                "        ISNULL(SUM(nMontoActual), 0) AS totalAhorrado\n" +
                "        , (\n" +
                "            SELECT ISNULL(SUM(nMonto), 0)\n" +
                "            FROM FINANZAS.AHORROS\n" +
                "            WHERE idUsuReg = @idUsuario\n" +
                "              AND MONTH(dFecha) = MONTH(GETDATE())\n" +
                "              AND YEAR(dFecha) = YEAR(GETDATE())\n" +
                "              AND lEsActivo = 1\n" +
                "          ) AS ahorradoEsteMes\n" +
                "        , @metaMensualTarget AS metaMensualTarget\n" +
                "        , (\n" +
                "            SELECT COUNT(*)\n" +
                "            FROM FINANZAS.METAS_AHORRO\n" +
                "            WHERE lEsActivo = 1\n" +
                "              AND idUsuReg = @idUsuario\n" +
                "          ) AS totalMetasActivas\n" +
                "    FROM FINANZAS.METAS_AHORRO\n" +
                "    WHERE lEsActivo = 1\n" +
                "      AND idUsuReg = @idUsuario;\n" +
                "END;"
            );
            
            // 8. CG_ObtenerMetasDetalle_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerMetasDetalle_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerMetasDetalle_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_ObtenerMetasDetalle_SP\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    SELECT\n" +
                "        idMeta\n" +
                "        , cDescripcion\n" +
                "        , nMontoObjetivo\n" +
                "        , nMontoActual\n" +
                "        , dFechaEstimada\n" +
                "        , cIconPath\n" +
                "        , nAsignacionMensual\n" +
                "    FROM FINANZAS.METAS_AHORRO\n" +
                "    WHERE lEsActivo = 1\n" +
                "      AND idUsuReg = @idUsuario;\n" +
                "END;"
            );

            // 9. CG_GuardaMetaAhorro_SP
            stmt.execute("IF OBJECT_ID('CG_GuardaMetaAhorro_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaMetaAhorro_SP;");
            stmt.execute(
                "CREATE PROCEDURE CG_GuardaMetaAhorro_SP\n" +
                "    @cDescripcion NVARCHAR(100),\n" +
                "    @nMontoObjetivo DECIMAL(15,2),\n" +
                "    @nAsignacionMensual DECIMAL(15,2),\n" +
                "    @dFechaEstimada DATE,\n" +
                "    @idUsuario INT\n" +
                "AS\n" +
                "BEGIN\n" +
                "    IF UPPER(LTRIM(RTRIM(@cDescripcion))) = 'FONDO EMERGENCIA' AND EXISTS (\n" +
                "        SELECT 1 FROM FINANZAS.METAS_AHORRO\n" +
                "        WHERE UPPER(LTRIM(RTRIM(cDescripcion))) = 'FONDO EMERGENCIA'\n" +
                "          AND idUsuReg = @idUsuario\n" +
                "          AND lEsActivo = 1\n" +
                "    )\n" +
                "    BEGIN\n" +
                "        SELECT cMsje = 'ERROR: Ya existe una meta activa llamada \"Fondo Emergencia\". No se puede duplicar.';\n" +
                "        RETURN;\n" +
                "    END;\n" +
                "\n" +
                "    IF UPPER(LTRIM(RTRIM(@cDescripcion))) IN ('AHORRO', 'AHORROS')\n" +
                "    BEGIN\n" +
                "        SELECT cMsje = 'ERROR: No puede usar el nombre \"Ahorro\" o \"Ahorros\" para una meta de ahorro individual, ya que está reservado para el presupuesto mensual general.';\n" +
                "        RETURN;\n" +
                "    END;\n" +
                "\n" +
                "    INSERT INTO FINANZAS.METAS_AHORRO (cDescripcion, nMontoObjetivo, nMontoActual, dFechaEstimada, idUsuReg, nAsignacionMensual)\n" +
                "    VALUES (@cDescripcion, @nMontoObjetivo, 0.00, @dFechaEstimada, @idUsuario, @nAsignacionMensual);\n" +
                "    \n" +
                "    SELECT cMsje = 'Meta de ahorro guardada correctamente.';\n" +
                "END;"
            );

            System.out.println("Database setup for Ahorros completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
