package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetupDashboardDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            try {
                System.out.println("Seeding categories if not exists...");
                // Ensure categories: Mercado (1), Internet (2), Vivienda, Transporte exist
                stmt.execute("IF NOT EXISTS (SELECT * FROM MANTENEDOR.CATEGORIAS WHERE cDescripcion = 'Vivienda')\n" +
                             "    INSERT INTO MANTENEDOR.CATEGORIAS (cDescripcion, cDescripcionLarga, lEsActivo, idUsuReg, dFecReg) VALUES ('Vivienda', 'Gastos de Vivienda y Servicios', 1, 1, GETDATE());");
                stmt.execute("IF NOT EXISTS (SELECT * FROM MANTENEDOR.CATEGORIAS WHERE cDescripcion = 'Transporte')\n" +
                             "    INSERT INTO MANTENEDOR.CATEGORIAS (cDescripcion, cDescripcionLarga, lEsActivo, idUsuReg, dFecReg) VALUES ('Transporte', 'Gastos de Transporte y Combustible', 1, 1, GETDATE());");

                // Fetch IDs of the categories
                int idMercado = 1;
                int idInternet = 2;
                int idVivienda = 3;
                int idTransporte = 4;

                try (ResultSet rs = stmt.executeQuery("SELECT idCategoria, cDescripcion FROM MANTENEDOR.CATEGORIAS")) {
                    while (rs.next()) {
                        String desc = rs.getString("cDescripcion");
                        int id = rs.getInt("idCategoria");
                        if (desc.equalsIgnoreCase("Mercado")) idMercado = id;
                        else if (desc.equalsIgnoreCase("Internet")) idInternet = id;
                        else if (desc.equalsIgnoreCase("Vivienda")) idVivienda = id;
                        else if (desc.equalsIgnoreCase("Transporte")) idTransporte = id;
                    }
                }

                System.out.println("Seeding budgets (PRESUPUESTO) for user 1...");
                // Vivienda (Vivienda/Servicios): $1500
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.PRESUPUESTO WHERE idCategoria = " + idVivienda + " AND lEsActivo = 1)\n" +
                             "    INSERT INTO FINANZAS.PRESUPUESTO (idCategoria, idTipoPeriodo, idEstadoPresupuesto, nLimPresuMens, nUmbralAlerta, lEsActivo, idUsuReg, dFecReg) VALUES (" + idVivienda + ", 1, 1, 1500.00, 80, 1, 1, GETDATE());");
                // Mercado (Food): $600
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.PRESUPUESTO WHERE idCategoria = " + idMercado + " AND lEsActivo = 1)\n" +
                             "    INSERT INTO FINANZAS.PRESUPUESTO (idCategoria, idTipoPeriodo, idEstadoPresupuesto, nLimPresuMens, nUmbralAlerta, lEsActivo, idUsuReg, dFecReg) VALUES (" + idMercado + ", 1, 1, 600.00, 80, 1, 1, GETDATE());");
                // Transporte: $300
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.PRESUPUESTO WHERE idCategoria = " + idTransporte + " AND lEsActivo = 1)\n" +
                             "    INSERT INTO FINANZAS.PRESUPUESTO (idCategoria, idTipoPeriodo, idEstadoPresupuesto, nLimPresuMens, nUmbralAlerta, lEsActivo, idUsuReg, dFecReg) VALUES (" + idTransporte + ", 1, 1, 300.00, 80, 1, 1, GETDATE());");

                System.out.println("Seeding expenses (GASTOS) for user 1 (June 2026)...");
                // Vivienda: $1200 spent in June 2026
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.GASTOS WHERE idCategoria = " + idVivienda + " AND cFechaGasto BETWEEN '2026-06-01' AND '2026-06-30')\n" +
                             "    INSERT INTO FINANZAS.GASTOS (idCategoria, idTipoGasto, cGasto, cDescripcion, nMonto, cFechaGasto, lEsActivo, idUsuReg, dFecReg) VALUES (" + idVivienda + ", 1, 'Alquiler/Servicios', 'Pago de Alquiler y Luz', 1200.00, '2026-06-05', 1, 1, GETDATE());");
                // Food (Mercado): $650 spent in June 2026 (exceeding budget!)
                stmt.execute("IF (SELECT SUM(nMonto) FROM FINANZAS.GASTOS WHERE idCategoria = " + idMercado + " AND cFechaGasto BETWEEN '2026-06-01' AND '2026-06-30') IS NULL\n" +
                             "    INSERT INTO FINANZAS.GASTOS (idCategoria, idTipoGasto, cGasto, cDescripcion, nMonto, cFechaGasto, lEsActivo, idUsuReg, dFecReg) VALUES (" + idMercado + ", 1, 'Supermercado', 'Compras de Comida del mes', 650.00, '2026-06-10', 1, 1, GETDATE());");
                // Transporte: $180 spent in June 2026
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.GASTOS WHERE idCategoria = " + idTransporte + " AND cFechaGasto BETWEEN '2026-06-01' AND '2026-06-30')\n" +
                             "    INSERT INTO FINANZAS.GASTOS (idCategoria, idTipoGasto, cGasto, cDescripcion, nMonto, cFechaGasto, lEsActivo, idUsuReg, dFecReg) VALUES (" + idTransporte + ", 1, 'Combustible/Taxi', 'Gastos de gasolina y Uber', 180.00, '2026-06-08', 1, 1, GETDATE());");

                System.out.println("Seeding incomes (INGRESOS) for user 1 (June 2026)...");
                // Insert mock incomes
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.INGRESOS WHERE idUsuario = 1 AND dFecha BETWEEN '2026-06-01' AND '2026-06-30')\n" +
                             "BEGIN\n" +
                             "    INSERT INTO FINANZAS.INGRESOS (nMonto, cDescripcion, dFecha, idUsuario, lEsActivo, idUsuReg, dFecReg) VALUES\n" +
                             "    (5000.00, 'Salario Mensual', '2026-06-01', 1, 1, 1, GETDATE()),\n" +
                             "    (1200.00, 'Trabajo Freelance', '2026-06-05', 1, 1, 1, GETDATE());\n" +
                             "END;");

                System.out.println("Seeding savings (AHORROS) for user 1 (June 2026)...");
                // Emergency fund saving: $500, vacation: $300
                stmt.execute("IF NOT EXISTS (SELECT * FROM FINANZAS.AHORROS WHERE idUsuReg = 1 AND dFecha BETWEEN '2026-06-01' AND '2026-06-30')\n" +
                             "BEGIN\n" +
                             "    INSERT INTO FINANZAS.AHORROS (idMeta, nMonto, dFecha, cDescripcion, lEsActivo, idUsuReg, dFecReg) VALUES\n" +
                             "    (1, 500.00, '2026-06-02', 'Depósito Fondo Emergencia', 1, 1, GETDATE()),\n" +
                             "    (2, 300.00, '2026-06-05', 'Depósito Ahorro Vacaciones', 1, 1, GETDATE());\n" +
                             "END;");
            } catch (Exception ex) {
                System.out.println("Warning: Seeding dummy data failed: " + ex.getMessage());
            }

            System.out.println("Deploying Stored Procedures for Dashboard...");

            // 1. CG_ObtenerDashboardResumen_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerDashboardResumen_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerDashboardResumen_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tObtener resumen general financiero para el Dashboard con soporte a filtros\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tDashboard\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_ObtenerDashboardResumen_SP 1, 0, '2026-06-01', '2026-06-30', 0, 0, 0, 9999999\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_ObtenerDashboardResumen_SP\n" +
                         "    @idUsuarioOwner INT,\n" +
                         "    @idFiltradoUsuario INT = 0,\n" +
                         "    @fechaInicio VARCHAR(10) = NULL,\n" +
                         "    @fechaFin VARCHAR(10) = NULL,\n" +
                         "    @idCategoria INT = 0,\n" +
                         "    @idTipoGasto INT = 0,\n" +
                         "    @montoMin DECIMAL(15,2) = 0,\n" +
                         "    @montoMax DECIMAL(15,2) = 99999999\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);\n" +
                         "    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);\n" +
                         "    IF @montoMin IS NULL SET @montoMin = 0;\n" +
                         "    IF @montoMax IS NULL SET @montoMax = 99999999;\n" +
                         "    \n" +
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
                         "    \n" +
                         "    DECLARE @dInicio DATE = CONVERT(DATE, @fechaInicio);\n" +
                         "    DECLARE @dFin DATE = CONVERT(DATE, @fechaFin);\n" +
                         "    \n" +
                         "    DECLARE @fechaInicioPrev DATE = DATEADD(month, -1, @dInicio);\n" +
                         "    DECLARE @fechaFinPrev DATE = DATEADD(month, -1, @dFin);\n" +
                         "    \n" +
                         "    DECLARE @ingresosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.INGRESOS WHERE idUsuario IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicio AND @fechaFin AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @gastosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.GASTOS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND cFechaGasto BETWEEN @fechaInicio AND @fechaFin AND (@idCategoria = 0 OR idCategoria = @idCategoria) AND (@idTipoGasto = 0 OR idTipoGasto = @idTipoGasto) AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @ahorrosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.AHORROS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicio AND @fechaFin AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @balanceMes DECIMAL(15,2) = @ingresosMes - @gastosMes - @ahorrosMes;\n" +
                         "    \n" +
                         "    DECLARE @ingresosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.INGRESOS WHERE idUsuario IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicioPrev AND @fechaFinPrev AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @gastosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.GASTOS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND cFechaGasto BETWEEN @fechaInicioPrev AND @fechaFinPrev AND (@idCategoria = 0 OR idCategoria = @idCategoria) AND (@idTipoGasto = 0 OR idTipoGasto = @idTipoGasto) AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @ahorrosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.AHORROS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicioPrev AND @fechaFinPrev AND nMonto BETWEEN @montoMin AND @montoMax), 0);\n" +
                         "    DECLARE @balanceMesPrev DECIMAL(15,2) = @ingresosMesPrev - @gastosMesPrev - @ahorrosMesPrev;\n" +
                         "    \n" +
                         "    DECLARE @ingresosCambio VARCHAR(50);\n" +
                         "    IF @ingresosMesPrev = 0\n" +
                         "    BEGIN\n" +
                         "        IF @ingresosMes = 0 SET @ingresosCambio = '0.0% vs mes anterior';\n" +
                         "        ELSE SET @ingresosCambio = '+100.0% vs mes anterior';\n" +
                         "    END\n" +
                         "    ELSE\n" +
                         "    BEGIN\n" +
                         "        DECLARE @pctIngresos DECIMAL(10,2) = ((@ingresosMes - @ingresosMesPrev) / @ingresosMesPrev) * 100;\n" +
                         "        IF @pctIngresos >= 0 SET @ingresosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctIngresos AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "        ELSE SET @ingresosCambio = CONVERT(VARCHAR(20), CAST(@pctIngresos AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "    END;\n" +
                         "    \n" +
                         "    DECLARE @gastosCambio VARCHAR(50);\n" +
                         "    IF @gastosMesPrev = 0\n" +
                         "    BEGIN\n" +
                         "        IF @gastosMes = 0 SET @gastosCambio = '0.0% vs mes anterior';\n" +
                         "        ELSE SET @gastosCambio = '+100.0% vs mes anterior';\n" +
                         "    END\n" +
                         "    ELSE\n" +
                         "    BEGIN\n" +
                         "        DECLARE @pctGastos DECIMAL(10,2) = ((@gastosMes - @gastosMesPrev) / @gastosMesPrev) * 100;\n" +
                         "        IF @pctGastos >= 0 SET @gastosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctGastos AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "        ELSE SET @gastosCambio = CONVERT(VARCHAR(20), CAST(@pctGastos AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "    END;\n" +
                         "    \n" +
                         "    DECLARE @ahorrosCambio VARCHAR(50);\n" +
                         "    IF @ahorrosMesPrev = 0\n" +
                         "    BEGIN\n" +
                         "        IF @ahorrosMes = 0 SET @ahorrosCambio = '0.0% vs mes anterior';\n" +
                         "        ELSE SET @ahorrosCambio = '+100.0% vs mes anterior';\n" +
                         "    END\n" +
                         "    ELSE\n" +
                         "    BEGIN\n" +
                         "        DECLARE @pctAhorros DECIMAL(10,2) = ((@ahorrosMes - @ahorrosMesPrev) / @ahorrosMesPrev) * 100;\n" +
                         "        IF @pctAhorros >= 0 SET @ahorrosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctAhorros AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "        ELSE SET @ahorrosCambio = CONVERT(VARCHAR(20), CAST(@pctAhorros AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "    END;\n" +
                         "    \n" +
                         "    DECLARE @balanceCambio VARCHAR(50);\n" +
                         "    IF @balanceMesPrev = 0\n" +
                         "    BEGIN\n" +
                         "        IF @balanceMes = 0 SET @balanceCambio = '0.0% vs mes anterior';\n" +
                         "        ELSE SET @balanceCambio = '+100.0% vs mes anterior';\n" +
                         "    END\n" +
                         "    ELSE\n" +
                         "    BEGIN\n" +
                         "        DECLARE @pctBalance DECIMAL(10,2) = ((@balanceMes - @balanceMesPrev) / @balanceMesPrev) * 100;\n" +
                         "        IF @pctBalance >= 0 SET @balanceCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctBalance AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "        ELSE SET @balanceCambio = CONVERT(VARCHAR(20), CAST(@pctBalance AS DECIMAL(10,1))) + '% vs mes anterior';\n" +
                         "    END;\n" +
                         "    \n" +
                         "    SELECT \n" +
                         "        @ingresosMes AS ingresosMes,\n" +
                         "        @ingresosCambio AS ingresosCambio,\n" +
                         "        @gastosMes AS gastosMes,\n" +
                         "        @gastosCambio AS gastosCambio,\n" +
                         "        @ahorrosMes AS ahorrosMes,\n" +
                         "        @ahorrosCambio AS ahorrosCambio,\n" +
                         "        @balanceMes AS balanceMes,\n" +
                         "        @balanceCambio AS balanceCambio,\n" +
                         "        49.90 AS upcomingPayInternetAmount,\n" +
                         "        'Vence el 15 de este mes' AS upcomingPayInternetDate,\n" +
                         "        120.00 AS upcomingPayHealthAmount,\n" +
                         "        'Vence el 20 de este mes' AS upcomingPayHealthDate,\n" +
                         "        N'¡Vas por buen camino!' AS bannerTitle,\n" +
                         "        N'Has ahorrado 15% más que el mes pasado.' AS bannerDesc;\n" +
                         "END;");

            // 2. CG_ObtenerDashboardGraficos_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerDashboardGraficos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerDashboardGraficos_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tObtener datos de presupuestos vs gastos por categoría para gráficos del Dashboard con soporte a filtros\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tDashboard\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_ObtenerDashboardGraficos_SP 1, 0, '2026-06-01', '2026-06-30', 0, 0, 0, 9999999\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_ObtenerDashboardGraficos_SP\n" +
                         "    @idUsuarioOwner INT,\n" +
                         "    @idFiltradoUsuario INT = 0,\n" +
                         "    @fechaInicio VARCHAR(10) = NULL,\n" +
                         "    @fechaFin VARCHAR(10) = NULL,\n" +
                         "    @idCategoria INT = 0,\n" +
                         "    @idTipoGasto INT = 0,\n" +
                         "    @montoMin DECIMAL(15,2) = 0,\n" +
                         "    @montoMax DECIMAL(15,2) = 99999999\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);\n" +
                         "    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);\n" +
                         "    IF @montoMin IS NULL SET @montoMin = 0;\n" +
                         "    IF @montoMax IS NULL SET @montoMax = 99999999;\n" +
                         "    \n" +
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
                         "    \n" +
                         "    SELECT \n" +
                         "        c.cDescripcion AS categoria,\n" +
                         "        c.cDescripcionLarga AS descripcion,\n" +
                         "        p.nLimPresuMens AS limite,\n" +
                         "        ISNULL((\n" +
                         "            SELECT SUM(g.nMonto) \n" +
                         "            FROM FINANZAS.GASTOS g \n" +
                         "            WHERE g.idCategoria = p.idCategoria \n" +
                         "              AND g.lEsActivo = 1 \n" +
                         "              AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) \n" +
                         "              AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin\n" +
                         "              AND (@idCategoria = 0 OR g.idCategoria = @idCategoria)\n" +
                         "              AND (@idTipoGasto = 0 OR g.idTipoGasto = @idTipoGasto)\n" +
                         "              AND g.nMonto BETWEEN @montoMin AND @montoMax\n" +
                         "        ), 0) AS gasto\n" +
                         "    FROM FINANZAS.PRESUPUESTO p\n" +
                         "    INNER JOIN MANTENEDOR.CATEGORIAS c ON p.idCategoria = c.idCategoria\n" +
                         "    WHERE p.lEsActivo = 1 \n" +
                         "      AND p.idUsuReg = @idUsuarioOwner\n" +
                         "      AND (@idCategoria = 0 OR p.idCategoria = @idCategoria);\n" +
                         "END;");

            // 3. CG_ObtenerDashboardPagos_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerDashboardPagos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerDashboardPagos_SP;");
            stmt.execute("CREATE PROCEDURE CG_ObtenerDashboardPagos_SP\n" +
                         "    @idUsuarioOwner INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SELECT \n" +
                         "        cAcreedor AS concepto,\n" +
                         "        CAST(nMontoTotal / nCuotasTotales AS DECIMAL(15,2)) AS monto,\n" +
                         "        'Vence el ' + CONVERT(VARCHAR(10), DATEADD(month, nCuotasPagadas, dFechaInicio), 103) AS fechaVencimiento\n" +
                         "    FROM FINANZAS.DEUDAS\n" +
                         "    WHERE idUsuario = @idUsuarioOwner \n" +
                         "      AND lEsActivo = 1 \n" +
                         "      AND nCuotasPagadas < nCuotasTotales;\n" +
                         "END;");

            System.out.println("Setup Completed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
