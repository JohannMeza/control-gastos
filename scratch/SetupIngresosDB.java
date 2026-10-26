package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupIngresosDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Updating FINANZAS.INGRESOS columns...");

            // 1. Alter FINANZAS.INGRESOS to add missing columns
            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'idCategoria')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD idCategoria INT NULL;");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'cCategoria')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD cCategoria NVARCHAR(100) NULL;");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'idCuentaDestino')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD idCuentaDestino INT NULL;");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'cCuentaDestino')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD cCuentaDestino NVARCHAR(100) NULL;");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'cTipoFlujo')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD cTipoFlujo NVARCHAR(20) DEFAULT 'Fijo';");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'cEstado')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD cEstado NVARCHAR(20) DEFAULT 'Confirmado';");

            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.INGRESOS') AND name = 'lEsRecurrente')\n" +
                         "    ALTER TABLE FINANZAS.INGRESOS ADD lEsRecurrente BIT DEFAULT 0;");

            System.out.println("Creating and populating FINANZAS.CUENTAS_DESTINO...");
            stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'CUENTAS_DESTINO' AND s.name = 'FINANZAS')\n" +
                         "CREATE TABLE FINANZAS.CUENTAS_DESTINO (\n" +
                         "    idCuenta INT IDENTITY(1,1) PRIMARY KEY,\n" +
                         "    cNombre NVARCHAR(100) NOT NULL,\n" +
                         "    cBanco NVARCHAR(50),\n" +
                         "    lEsActivo BIT DEFAULT 1,\n" +
                         "    idUsuario INT DEFAULT 1\n" +
                         ");");

            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = 'Cuenta Corriente BBVA (Nómina)')\n" +
                         "INSERT INTO FINANZAS.CUENTAS_DESTINO (cNombre, cBanco, lEsActivo, idUsuario) VALUES ('Cuenta Corriente BBVA (Nómina)', 'BBVA', 1, 1);");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = 'Cuenta Dólares')\n" +
                         "INSERT INTO FINANZAS.CUENTAS_DESTINO (cNombre, cBanco, lEsActivo, idUsuario) VALUES ('Cuenta Dólares', 'BCP', 1, 1);");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = 'Santander')\n" +
                         "INSERT INTO FINANZAS.CUENTAS_DESTINO (cNombre, cBanco, lEsActivo, idUsuario) VALUES ('Santander', 'Santander', 1, 1);");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = 'Stripe / Banco')\n" +
                         "INSERT INTO FINANZAS.CUENTAS_DESTINO (cNombre, cBanco, lEsActivo, idUsuario) VALUES ('Stripe / Banco', 'Stripe', 1, 1);");
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = 'BBVA')\n" +
                         "INSERT INTO FINANZAS.CUENTAS_DESTINO (cNombre, cBanco, lEsActivo, idUsuario) VALUES ('BBVA', 'BBVA', 1, 1);");

            System.out.println("Seeding realistic mockup incomes for user 1 (October 2023 & recent)...");
            // Check if October 2023 mockup rows already exist; if not, insert them
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.INGRESOS WHERE idUsuario = 1 AND cDescripcion = 'Nómina Quincenal Empresa Tech' AND dFecha = '2023-10-28')\n" +
                         "BEGIN\n" +
                         "    INSERT INTO FINANZAS.INGRESOS (nMonto, cDescripcion, dFecha, idUsuario, lEsActivo, idUsuReg, dFecReg, cCategoria, cCuentaDestino, cTipoFlujo, cEstado, lEsRecurrente) VALUES\n" +
                         "    (4250.00, 'Nómina Quincenal Empresa Tech', '2023-10-28', 1, 1, 1, GETDATE(), 'Salario', 'BBVA Nómina', 'Fijo', 'Confirmado', 1),\n" +
                         "    (1850.00, 'Consultoría UX/UI Cliente USA', '2023-10-25', 1, 1, 1, GETDATE(), 'Freelance', 'Cuenta Dólares', 'Variable', 'Confirmado', 0),\n" +
                         "    (320.00, 'Rentabilidad Fondo Indexado S&P', '2023-10-20', 1, 1, 1, GETDATE(), 'Inversiones', 'Santander', 'Variable', 'Confirmado', 0),\n" +
                         "    (4250.00, 'Nómina Quincenal Empresa Tech', '2023-10-15', 1, 1, 1, GETDATE(), 'Salario', 'BBVA Nómina', 'Fijo', 'Confirmado', 1),\n" +
                         "    (780.00, 'Venta de Curso / Asesoría', '2023-10-12', 1, 1, 1, GETDATE(), 'Servicios', 'Stripe / Banco', 'Variable', 'Confirmado', 0),\n" +
                         "    (1000.00, 'Reembolso de Gastos de Viaje', '2023-10-05', 1, 1, 1, GETDATE(), 'Reembolso', 'BBVA', 'Variable', 'Confirmado', 0);\n" +
                         "END;");

            // Also seed September 2023 data so variation and comparison works dynamically
            stmt.execute("IF NOT EXISTS (SELECT 1 FROM FINANZAS.INGRESOS WHERE idUsuario = 1 AND dFecha BETWEEN '2023-09-01' AND '2023-09-30')\n" +
                         "BEGIN\n" +
                         "    INSERT INTO FINANZAS.INGRESOS (nMonto, cDescripcion, dFecha, idUsuario, lEsActivo, idUsuReg, dFecReg, cCategoria, cCuentaDestino, cTipoFlujo, cEstado, lEsRecurrente) VALUES\n" +
                         "    (4250.00, 'Nómina Quincenal Empresa Tech', '2023-09-15', 1, 1, 1, GETDATE(), 'Salario', 'BBVA Nómina', 'Fijo', 'Confirmado', 1),\n" +
                         "    (4250.00, 'Nómina Quincenal Empresa Tech', '2023-09-30', 1, 1, 1, GETDATE(), 'Salario', 'BBVA Nómina', 'Fijo', 'Confirmado', 1),\n" +
                         "    (2575.00, 'Proyecto Web Freelance', '2023-09-20', 1, 1, 1, GETDATE(), 'Freelance', 'Cuenta Dólares', 'Variable', 'Confirmado', 0);\n" +
                         "END;");

            System.out.println("Deploying Stored Procedures for Ingresos...");

            // 1. CG_ListaIngresosActivos_SP
            stmt.execute("IF OBJECT_ID('CG_ListaIngresosActivos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaIngresosActivos_SP;");
            stmt.execute("CREATE PROCEDURE CG_ListaIngresosActivos_SP\n" +
                         "    @idUsuario INT,\n" +
                         "    @cTipoFlujo NVARCHAR(20) = NULL,\n" +
                         "    @cEstado NVARCHAR(20) = NULL,\n" +
                         "    @dFechaDesde DATE = NULL,\n" +
                         "    @dFechaHasta DATE = NULL,\n" +
                         "    @cCategoria NVARCHAR(100) = NULL,\n" +
                         "    @cCuentaDestino NVARCHAR(100) = NULL\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SET NOCOUNT ON;\n" +
                         "    SELECT \n" +
                         "        i.idIngreso,\n" +
                         "        i.nMonto,\n" +
                         "        i.cDescripcion,\n" +
                         "        i.dFecha,\n" +
                         "        CONVERT(VARCHAR(10), i.dFecha, 120) AS fechaFormat,\n" +
                         "        ISNULL(i.cCategoria, 'Otros') AS cCategoria,\n" +
                         "        ISNULL(i.cCuentaDestino, 'General') AS cCuentaDestino,\n" +
                         "        ISNULL(i.cTipoFlujo, 'Fijo') AS cTipoFlujo,\n" +
                         "        ISNULL(i.cEstado, 'Confirmado') AS cEstado,\n" +
                         "        ISNULL(i.lEsRecurrente, 0) AS lEsRecurrente,\n" +
                         "        i.idUsuario\n" +
                         "    FROM FINANZAS.INGRESOS i\n" +
                         "    WHERE i.idUsuario = @idUsuario\n" +
                         "      AND i.lEsActivo = 1\n" +
                         "      AND (@cTipoFlujo IS NULL OR @cTipoFlujo = 'Todos' OR i.cTipoFlujo = @cTipoFlujo)\n" +
                         "      AND (@cEstado IS NULL OR @cEstado = 'Todos' OR i.cEstado = @cEstado)\n" +
                         "      AND (@dFechaDesde IS NULL OR i.dFecha >= @dFechaDesde)\n" +
                         "      AND (@dFechaHasta IS NULL OR i.dFecha <= @dFechaHasta)\n" +
                         "      AND (@cCategoria IS NULL OR @cCategoria = 'Todas' OR @cCategoria = 'Todas las Categorías' OR i.cCategoria = @cCategoria)\n" +
                         "      AND (@cCuentaDestino IS NULL OR @cCuentaDestino = 'Todas' OR @cCuentaDestino = 'Todas las Cuentas' OR i.cCuentaDestino = @cCuentaDestino)\n" +
                         "    ORDER BY i.dFecha DESC, i.idIngreso DESC;\n" +
                         "END;");

            // 2. CG_InsertarIngreso_SP
            stmt.execute("IF OBJECT_ID('CG_InsertarIngreso_SP', 'P') IS NOT NULL DROP PROCEDURE CG_InsertarIngreso_SP;");
            stmt.execute("CREATE PROCEDURE CG_InsertarIngreso_SP\n" +
                         "    @nMonto DECIMAL(18,2),\n" +
                         "    @cDescripcion NVARCHAR(255),\n" +
                         "    @dFecha DATE,\n" +
                         "    @cCategoria NVARCHAR(100),\n" +
                         "    @cCuentaDestino NVARCHAR(100),\n" +
                         "    @cTipoFlujo NVARCHAR(20),\n" +
                         "    @cEstado NVARCHAR(20),\n" +
                         "    @lEsRecurrente BIT,\n" +
                         "    @idUsuario INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SET NOCOUNT ON;\n" +
                         "    INSERT INTO FINANZAS.INGRESOS (\n" +
                         "        nMonto, cDescripcion, dFecha, cCategoria, cCuentaDestino, cTipoFlujo, cEstado, lEsRecurrente, idUsuario, lEsActivo, idUsuReg, dFecReg\n" +
                         "    ) VALUES (\n" +
                         "        @nMonto, @cDescripcion, @dFecha, @cCategoria, @cCuentaDestino, @cTipoFlujo, @cEstado, @lEsRecurrente, @idUsuario, 1, @idUsuario, GETDATE()\n" +
                         "    );\n" +
                         "    SELECT SCOPE_IDENTITY() AS idIngreso, 'Ingreso registrado correctamente.' AS mensaje;\n" +
                         "END;");

            // 3. CG_ActualizarIngreso_SP
            stmt.execute("IF OBJECT_ID('CG_ActualizarIngreso_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ActualizarIngreso_SP;");
            stmt.execute("CREATE PROCEDURE CG_ActualizarIngreso_SP\n" +
                         "    @idIngreso INT,\n" +
                         "    @nMonto DECIMAL(18,2),\n" +
                         "    @cDescripcion NVARCHAR(255),\n" +
                         "    @dFecha DATE,\n" +
                         "    @cCategoria NVARCHAR(100),\n" +
                         "    @cCuentaDestino NVARCHAR(100),\n" +
                         "    @cTipoFlujo NVARCHAR(20),\n" +
                         "    @cEstado NVARCHAR(20),\n" +
                         "    @lEsRecurrente BIT,\n" +
                         "    @idUsuario INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SET NOCOUNT ON;\n" +
                         "    UPDATE FINANZAS.INGRESOS SET\n" +
                         "        nMonto = @nMonto,\n" +
                         "        cDescripcion = @cDescripcion,\n" +
                         "        dFecha = @dFecha,\n" +
                         "        cCategoria = @cCategoria,\n" +
                         "        cCuentaDestino = @cCuentaDestino,\n" +
                         "        cTipoFlujo = @cTipoFlujo,\n" +
                         "        cEstado = @cEstado,\n" +
                         "        lEsRecurrente = @lEsRecurrente,\n" +
                         "        idUsuMod = @idUsuario,\n" +
                         "        dFecMod = GETDATE()\n" +
                         "    WHERE idIngreso = @idIngreso AND idUsuario = @idUsuario;\n" +
                         "    SELECT @idIngreso AS idIngreso, 'Ingreso actualizado correctamente.' AS mensaje;\n" +
                         "END;");

            // 4. CG_EliminarIngreso_SP
            stmt.execute("IF OBJECT_ID('CG_EliminarIngreso_SP', 'P') IS NOT NULL DROP PROCEDURE CG_EliminarIngreso_SP;");
            stmt.execute("CREATE PROCEDURE CG_EliminarIngreso_SP\n" +
                         "    @idIngreso INT,\n" +
                         "    @idUsuario INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SET NOCOUNT ON;\n" +
                         "    UPDATE FINANZAS.INGRESOS SET\n" +
                         "        lEsActivo = 0,\n" +
                         "        idUsuMod = @idUsuario,\n" +
                         "        dFecMod = GETDATE()\n" +
                         "    WHERE idIngreso = @idIngreso AND idUsuario = @idUsuario;\n" +
                         "    SELECT @idIngreso AS idIngreso, 'Ingreso eliminado correctamente.' AS mensaje;\n" +
                         "END;");

            System.out.println("Database setup for Ingresos completed successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
