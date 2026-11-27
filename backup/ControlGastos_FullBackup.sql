-- =====================================================================
-- SCRIPT DE RESTAURACIÓN COMPLETA - BASE DE DATOS: ControlGastos
-- Generado automáticamente: Fri Nov 27 11:10:17 PET 2026
-- Contiene: Esquemas, Tablas, Restricciones, Datos (DML) y Stored Procedures
-- Compatible con: SQL Server 2017, 2019, 2022, Express y Azure SQL
-- =====================================================================

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'ControlGastos')
BEGIN
    CREATE DATABASE [ControlGastos];
END
GO

USE [ControlGastos];
GO

-- =====================================================================
-- 1. CREACIÓN DE ESQUEMAS
-- =====================================================================
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'MANTENEDOR')
BEGIN
    EXEC('CREATE SCHEMA [MANTENEDOR]');
END
GO
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'FINANZAS')
BEGIN
    EXEC('CREATE SCHEMA [FINANZAS]');
END
GO
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'SEGURIDAD')
BEGIN
    EXEC('CREATE SCHEMA [SEGURIDAD]');
END
GO

-- =====================================================================
-- 2. CREACIÓN DE TABLAS (DDL)
-- =====================================================================

-- ---------------------------------------------------------------------
-- Tabla: MANTENEDOR.TIPO_PERIODO
-- ---------------------------------------------------------------------
IF OBJECT_ID('MANTENEDOR.TIPO_PERIODO', 'U') IS NULL
BEGIN
    CREATE TABLE [MANTENEDOR].[TIPO_PERIODO] (
        [idTipoPeriodo] tinyint IDENTITY(1,1) NOT NULL,
        [cDescripcion] nvarchar(50) NULL,
        [cDescripcionLarga] nvarchar(150) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__TIPO_PER__789704098461C06F] PRIMARY KEY CLUSTERED ([idTipoPeriodo])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: MANTENEDOR.TIPO_GASTOS
-- ---------------------------------------------------------------------
IF OBJECT_ID('MANTENEDOR.TIPO_GASTOS', 'U') IS NULL
BEGIN
    CREATE TABLE [MANTENEDOR].[TIPO_GASTOS] (
        [idTipoGasto] int IDENTITY(1,1) NOT NULL,
        [cDescripcion] nvarchar(50) NULL,
        [cDescripcionLarga] nvarchar(100) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__TIPO_GAS__DD5D1914590AEEBE] PRIMARY KEY CLUSTERED ([idTipoGasto])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: MANTENEDOR.ESTADO_PRESUPUESTO
-- ---------------------------------------------------------------------
IF OBJECT_ID('MANTENEDOR.ESTADO_PRESUPUESTO', 'U') IS NULL
BEGIN
    CREATE TABLE [MANTENEDOR].[ESTADO_PRESUPUESTO] (
        [idEstadoPresupuesto] tinyint IDENTITY(1,1) NOT NULL,
        [cDescripcion] nvarchar(50) NULL,
        [cDescripcionLarga] nvarchar(100) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__ESTADO_P__5AC7745CF44F59CB] PRIMARY KEY CLUSTERED ([idEstadoPresupuesto])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: MANTENEDOR.CATEGORIAS
-- ---------------------------------------------------------------------
IF OBJECT_ID('MANTENEDOR.CATEGORIAS', 'U') IS NULL
BEGIN
    CREATE TABLE [MANTENEDOR].[CATEGORIAS] (
        [idCategoria] int IDENTITY(1,1) NOT NULL,
        [cDescripcion] nvarchar(50) NULL,
        [cDescripcionLarga] nvarchar(150) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__CATEGORI__8A3D240C6BF09518] PRIMARY KEY CLUSTERED ([idCategoria])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: dbo.usuarios
-- ---------------------------------------------------------------------
IF OBJECT_ID('dbo.usuarios', 'U') IS NULL
BEGIN
    CREATE TABLE [dbo].[usuarios] (
        [id] int IDENTITY(1,1) NOT NULL,
        [nombre] varchar(100) NOT NULL,
        [email] varchar(150) NOT NULL,
        [password] varchar(255) NOT NULL,
        [rol] varchar(50) NOT NULL,
        [fecha_creacion] datetime NULL,
        CONSTRAINT [PK__usuarios__3213E83F0B6BD6BA] PRIMARY KEY CLUSTERED ([id])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.CONFIGURACION_SEGURIDAD
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.CONFIGURACION_SEGURIDAD', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[CONFIGURACION_SEGURIDAD] (
        [idUsuario] int NOT NULL,
        [lMfaHabilitado] bit NOT NULL,
        CONSTRAINT [PK__CONFIGUR__645723A6088888BC] PRIMARY KEY CLUSTERED ([idUsuario])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.CATEGORIAS_INGRESOS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.CATEGORIAS_INGRESOS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[CATEGORIAS_INGRESOS] (
        [idCategoria] int IDENTITY(1,1) NOT NULL,
        [cNombre] nvarchar(100) NOT NULL,
        [cDescripcion] nvarchar(255) NULL,
        [lEsActivo] bit NULL,
        [idUsuario] int NULL,
        CONSTRAINT [PK__CATEGORI__8A3D240C4497AFDE] PRIMARY KEY CLUSTERED ([idCategoria])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.CUENTAS_DESTINO
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.CUENTAS_DESTINO', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[CUENTAS_DESTINO] (
        [idCuenta] int IDENTITY(1,1) NOT NULL,
        [cNombre] nvarchar(100) NOT NULL,
        [cBanco] nvarchar(50) NULL,
        [lEsActivo] bit NULL,
        [idUsuario] int NULL,
        CONSTRAINT [PK__CUENTAS___BBC6DF3285E0AA71] PRIMARY KEY CLUSTERED ([idCuenta])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.DEUDAS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.DEUDAS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[DEUDAS] (
        [idDeuda] int IDENTITY(1,1) NOT NULL,
        [cAcreedor] nvarchar(150) NOT NULL,
        [nMontoTotal] decimal(18,2) NOT NULL,
        [nTasaInteres] decimal(5,2) NOT NULL,
        [nCuotasTotales] int NOT NULL,
        [nCuotasPagadas] int NOT NULL,
        [dFechaInicio] date NOT NULL,
        [lEsActivo] bit NOT NULL,
        [idUsuario] int NOT NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK_DEUDAS] PRIMARY KEY CLUSTERED ([idDeuda])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.ABONO_DEUDAS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.ABONO_DEUDAS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[ABONO_DEUDAS] (
        [idAbono] int IDENTITY(1,1) NOT NULL,
        [idDeuda] int NOT NULL,
        [nMontoAbono] decimal(18,2) NOT NULL,
        [dFechaPago] date NOT NULL,
        [lEsActivo] bit NOT NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK_ABONO_DEUDAS] PRIMARY KEY CLUSTERED ([idAbono])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.INGRESOS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.INGRESOS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[INGRESOS] (
        [idIngreso] int IDENTITY(1,1) NOT NULL,
        [nMonto] decimal(18,2) NOT NULL,
        [cDescripcion] nvarchar(255) NULL,
        [dFecha] date NOT NULL,
        [idUsuario] int NOT NULL,
        [lEsActivo] bit NOT NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        [idCategoria] int NULL,
        [cCategoria] nvarchar(100) NULL,
        [idCuentaDestino] int NULL,
        [cCuentaDestino] nvarchar(100) NULL,
        [cTipoFlujo] nvarchar(20) NULL,
        [cEstado] nvarchar(20) NULL,
        [lEsRecurrente] bit NULL,
        CONSTRAINT [PK_INGRESOS] PRIMARY KEY CLUSTERED ([idIngreso])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.GASTOS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.GASTOS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[GASTOS] (
        [idGasto] int IDENTITY(1,1) NOT NULL,
        [idCategoria] int NOT NULL,
        [idTipoGasto] int NOT NULL,
        [cGasto] nvarchar(50) NULL,
        [cDescripcion] nvarchar(150) NULL,
        [nMonto] decimal(15,2) NULL,
        [cFechaGasto] date NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__GASTOS__F25CC3216C161EC6] PRIMARY KEY CLUSTERED ([idGasto])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.PRESUPUESTO
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.PRESUPUESTO', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[PRESUPUESTO] (
        [idPresupuesto] int IDENTITY(1,1) NOT NULL,
        [idCategoria] int NOT NULL,
        [idTipoPeriodo] tinyint NOT NULL,
        [idEstadoPresupuesto] tinyint NOT NULL,
        [nLimPresuMens] decimal(15,2) NOT NULL,
        [nUmbralAlerta] tinyint NOT NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__PRESUPUE__99893A4E11ADA773] PRIMARY KEY CLUSTERED ([idPresupuesto])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.AHORROS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.AHORROS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[AHORROS] (
        [idAhorro] int IDENTITY(1,1) NOT NULL,
        [idMeta] int NULL,
        [nMonto] decimal(15,2) NOT NULL,
        [dFecha] date NOT NULL,
        [cDescripcion] nvarchar(300) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK__AHORROS__7F1EB238BA3CA7EC] PRIMARY KEY CLUSTERED ([idAhorro])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.METAS_AHORRO
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.METAS_AHORRO', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[METAS_AHORRO] (
        [idMeta] int IDENTITY(1,1) NOT NULL,
        [cDescripcion] nvarchar(100) NOT NULL,
        [nMontoObjetivo] decimal(15,2) NOT NULL,
        [nMontoActual] decimal(15,2) NULL,
        [dFechaEstimada] date NULL,
        [cIconPath] nvarchar(200) NULL,
        [lEsActivo] bit NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        [nAsignacionMensual] decimal(15,2) NOT NULL,
        CONSTRAINT [PK__METAS_AH__C26D05DE7D842BE9] PRIMARY KEY CLUSTERED ([idMeta])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.USUARIOS_COMPARTIDOS
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.USUARIOS_COMPARTIDOS', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[USUARIOS_COMPARTIDOS] (
        [idCompartido] int IDENTITY(1,1) NOT NULL,
        [idUsuarioOwner] int NOT NULL,
        [idUsuarioInvitado] int NOT NULL,
        [cPermiso] nvarchar(50) NOT NULL,
        [lEsActivo] bit NOT NULL,
        [idUsuReg] int NULL,
        [dFecReg] datetime NULL,
        [idUsuMod] int NULL,
        [dFecMod] datetime NULL,
        CONSTRAINT [PK_USUARIOS_COMPARTIDOS] PRIMARY KEY CLUSTERED ([idCompartido])
    );
END
GO

-- ---------------------------------------------------------------------
-- Tabla: FINANZAS.LOGS_AUDITORIA
-- ---------------------------------------------------------------------
IF OBJECT_ID('FINANZAS.LOGS_AUDITORIA', 'U') IS NULL
BEGIN
    CREATE TABLE [FINANZAS].[LOGS_AUDITORIA] (
        [idLog] int IDENTITY(1,1) NOT NULL,
        [idUsuarioOwner] int NOT NULL,
        [cAccion] varchar(100) NOT NULL,
        [cDetalle] varchar(255) NOT NULL,
        [dFecha] datetime NULL,
        CONSTRAINT [PK__LOGS_AUD__3C7153CA507B2604] PRIMARY KEY CLUSTERED ([idLog])
    );
END
GO

-- =====================================================================
-- 3. INSERCIÓN DE DATOS (DML)
-- =====================================================================

-- Datos para MANTENEDOR.TIPO_PERIODO (4 registros)
SET IDENTITY_INSERT [MANTENEDOR].[TIPO_PERIODO] ON;
INSERT INTO [MANTENEDOR].[TIPO_PERIODO] ([idTipoPeriodo], [cDescripcion], [cDescripcionLarga], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(1, N'DIARIO', N'DIARIO', 1, 1, '2026-06-29 18:56:19.51', NULL, NULL),
(2, N'QUINCENAL', N'QUINCENAL', 1, 1, '2026-06-29 18:56:19.51', NULL, NULL),
(3, N'MENSUAL', N'MENSUAL', 1, 1, '2026-06-29 18:56:19.51', NULL, NULL),
(4, N'ANUAL', N'ANUAL', 1, 1, '2026-06-29 18:56:19.51', NULL, NULL);
SET IDENTITY_INSERT [MANTENEDOR].[TIPO_PERIODO] OFF;
GO

-- Datos para MANTENEDOR.TIPO_GASTOS (2 registros)
SET IDENTITY_INSERT [MANTENEDOR].[TIPO_GASTOS] ON;
INSERT INTO [MANTENEDOR].[TIPO_GASTOS] ([idTipoGasto], [cDescripcion], [cDescripcionLarga], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(1, N'Variable', N'Gastos Variables', 1, 1, '2026-06-29 18:56:00.063', NULL, NULL),
(2, N'Fijo', N'Gastos Fijos', 1, 1, '2026-06-29 18:56:00.063', NULL, NULL);
SET IDENTITY_INSERT [MANTENEDOR].[TIPO_GASTOS] OFF;
GO

-- Datos para MANTENEDOR.ESTADO_PRESUPUESTO (4 registros)
SET IDENTITY_INSERT [MANTENEDOR].[ESTADO_PRESUPUESTO] ON;
INSERT INTO [MANTENEDOR].[ESTADO_PRESUPUESTO] ([idEstadoPresupuesto], [cDescripcion], [cDescripcionLarga], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(1, N'EN CAMINO', N'EN CAMINO', 1, 1, '2026-06-29 18:57:05.243', NULL, NULL),
(2, N'EXCEDIDO', N'EXCEDIDO', 1, 1, '2026-06-29 18:57:05.243', NULL, NULL),
(3, N'BAJO', N'BAJO', 1, 1, '2026-06-29 18:57:05.243', NULL, NULL),
(4, N'ADVERTENCIA', N'ADVERTENCIA', 1, 1, '2026-06-29 18:57:05.243', NULL, NULL);
SET IDENTITY_INSERT [MANTENEDOR].[ESTADO_PRESUPUESTO] OFF;
GO

-- Datos para MANTENEDOR.CATEGORIAS (13 registros)
SET IDENTITY_INSERT [MANTENEDOR].[CATEGORIAS] ON;
INSERT INTO [MANTENEDOR].[CATEGORIAS] ([idCategoria], [cDescripcion], [cDescripcionLarga], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(9, N'Mercado', N'Compras de Alimentos y Supermercado', 1, 1009, '2026-06-29 18:45:19.43', NULL, NULL),
(10, N'Vivienda', N'Gastos de Vivienda y Servicios', 1, 1009, '2026-06-29 18:45:30.53', NULL, NULL),
(11, N'Transporte', N'Gastos de Transporte y Combustible', 1, 1009, '2026-06-29 18:45:30.533', NULL, NULL),
(12, N'Ahorro', N'Presupuesto destinado a Ahorros', 1, 1009, '2026-06-29 18:45:30.537', NULL, NULL),
(13, N'Internet', N'Gastos de Internet y Telecomunicaciones', 1, 1009, '2026-06-29 18:45:30.54', NULL, NULL),
(16, N'Víveres', N'Abastecimiento general del hogar y alimentos', 1, 1009, '2026-06-29 19:17:03.127', NULL, NULL),
(17, N'Telefonía', N'Servicio de Telefonia Movil y Celular', 1, 2011, '2026-11-27 01:42:58.42', NULL, NULL),
(18, N'Cooperativa', N'Aportes y Cuotas Cooperativa', 1, 2011, '2026-11-27 01:42:58.42', NULL, NULL),
(24, N'Educación', N'Gastos de Universidad, Matrícula y Estudios', 1, 2013, '2026-11-27 04:35:23.72', NULL, NULL),
(25, N'Entretenimiento', N'Servicios de Streaming, Música, Video y Eventos', 1, 2013, '2026-11-27 04:35:23.72', NULL, NULL),
(26, N'Servicios Financieros', N'Pagos de Tarjetas de Crédito y Saldos Financieros', 1, 2013, '2026-11-27 04:35:23.723', NULL, NULL),
(27, N'Familia', N'Apoyo familiar y aportes del hogar', 1, 2013, '2026-11-27 04:35:23.723', NULL, NULL),
(28, N'Seguros', N'Seguros de Vida, Sepelios y Protección Patrimonial', 1, 2013, '2026-11-27 04:35:23.723', NULL, NULL);
SET IDENTITY_INSERT [MANTENEDOR].[CATEGORIAS] OFF;
GO

-- Datos para dbo.usuarios (4 registros)
SET IDENTITY_INSERT [dbo].[usuarios] ON;
INSERT INTO [dbo].[usuarios] ([id], [nombre], [email], [password], [rol], [fecha_creacion]) VALUES
(1009, N'Johann Rafael Meza Salazar', N'johan@gmail.com', N'0x02000000CFEE7862D9583EE40745400ECB0AF4CAA9794F527CD8C16D416D1754CCAD27FD', N'USER', '2026-06-29 18:04:09.22'),
(1010, N'Johann1', N'johan1@gmail.com', N'0x02000000C395AF14C27D06026EB63269293D00D3B096CCE950948230C9E80B297A593CDED590EA6B5E516CC844D6ECBCA3902DF0', N'USER', '2026-09-01 03:41:50.51'),
(2011, N'Johann Meza', N'johann@hogar.pe', N'0x020000002BA608F12C6DC9BCBFDA2E4293D65FB3EE414B4688593FD2672CE11E708321AE', N'USER', '2026-11-27 01:01:04.39'),
(2013, N'Johann Meza', N'johann@personal.pe', N'0x02000000837966C96BF39D0400E491625097C7D6B2C676D530F2D5B06E03D195A7EAE0B6', N'USER', '2026-11-27 04:31:13.343');
SET IDENTITY_INSERT [dbo].[usuarios] OFF;
GO

-- Datos para FINANZAS.CONFIGURACION_SEGURIDAD (3 registros)
INSERT INTO [FINANZAS].[CONFIGURACION_SEGURIDAD] ([idUsuario], [lMfaHabilitado]) VALUES
(1010, 0),
(2011, 0),
(2013, 0);
GO

-- Datos para FINANZAS.CATEGORIAS_INGRESOS (7 registros)
SET IDENTITY_INSERT [FINANZAS].[CATEGORIAS_INGRESOS] ON;
INSERT INTO [FINANZAS].[CATEGORIAS_INGRESOS] ([idCategoria], [cNombre], [cDescripcion], [lEsActivo], [idUsuario]) VALUES
(1, N'Salario / Nómina Directa', N'Ingreso por planilla o relación de dependencia laboral', 1, NULL),
(2, N'Proyectos Freelance & Tech', N'Servicios independientes de desarrollo y consultoría técnica', 1, NULL),
(3, N'Rendimientos & Dividendos', N'Rendimientos de inversiones, depósitos a plazo o bolsa', 1, NULL),
(4, N'Consultoría & Asesoría', N'Servicios profesionales y asesoramiento externo', 1, NULL),
(5, N'Alquileres & Rentas', N'Arrendamiento de inmuebles o activos', 1, NULL),
(6, N'Reembolsos & Bonos', N'Devoluciones, gratificaciones o premios', 1, NULL),
(7, N'Otros Ingresos', N'Ingresos varios no clasificados', 1, NULL);
SET IDENTITY_INSERT [FINANZAS].[CATEGORIAS_INGRESOS] OFF;
GO

-- Datos para FINANZAS.CUENTAS_DESTINO (5 registros)
SET IDENTITY_INSERT [FINANZAS].[CUENTAS_DESTINO] ON;
INSERT INTO [FINANZAS].[CUENTAS_DESTINO] ([idCuenta], [cNombre], [cBanco], [lEsActivo], [idUsuario]) VALUES
(1, N'Cuenta Corriente BBVA (Nómina)', N'BBVA', 1, NULL),
(2, N'Cuenta Dólares', N'BCP', 1, NULL),
(3, N'Santander', N'Santander', 1, NULL),
(4, N'Stripe / Banco', N'Stripe', 1, NULL),
(5, N'BBVA', N'BBVA', 1, NULL);
SET IDENTITY_INSERT [FINANZAS].[CUENTAS_DESTINO] OFF;
GO

-- Datos para FINANZAS.DEUDAS (3 registros)
SET IDENTITY_INSERT [FINANZAS].[DEUDAS] ON;
INSERT INTO [FINANZAS].[DEUDAS] ([idDeuda], [cAcreedor], [nMontoTotal], [nTasaInteres], [nCuotasTotales], [nCuotasPagadas], [dFechaInicio], [lEsActivo], [idUsuario], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(4, N'Pacífico Seguros - Vida Devolución (Prop. 10917175)', 15792.00, 0.00, 84, 7, '2026-05-03', 1, 2013, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(5, N'Espíritu Eterno - Lote 1 (Cuota S/ 236.11)', 17000.00, 0.00, 72, 7, '2026-05-04', 1, 2013, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(6, N'Espíritu Eterno - Lote 2 (Cuota S/ 186.81)', 13450.00, 0.00, 72, 5, '2026-07-04', 1, 2013, 2013, '2026-11-27 04:36:17.43', NULL, NULL);
SET IDENTITY_INSERT [FINANZAS].[DEUDAS] OFF;
GO

-- Datos para FINANZAS.ABONO_DEUDAS (19 registros)
SET IDENTITY_INSERT [FINANZAS].[ABONO_DEUDAS] ON;
INSERT INTO [FINANZAS].[ABONO_DEUDAS] ([idAbono], [idDeuda], [nMontoAbono], [dFechaPago], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(20, 4, 188.00, '2026-05-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(21, 4, 188.00, '2026-06-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(22, 4, 188.00, '2026-07-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(23, 4, 188.00, '2026-08-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(24, 4, 188.00, '2026-09-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(25, 4, 188.00, '2026-10-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(26, 4, 188.00, '2026-11-03', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(27, 5, 236.11, '2026-05-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(28, 5, 236.11, '2026-06-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(29, 5, 236.11, '2026-07-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(30, 5, 236.11, '2026-08-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(31, 5, 236.11, '2026-09-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(32, 5, 236.11, '2026-10-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(33, 5, 236.11, '2026-11-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(34, 6, 186.81, '2026-07-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(35, 6, 186.81, '2026-08-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(36, 6, 186.81, '2026-09-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(37, 6, 186.81, '2026-10-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(38, 6, 186.81, '2026-11-04', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL);
SET IDENTITY_INSERT [FINANZAS].[ABONO_DEUDAS] OFF;
GO

-- Datos para FINANZAS.INGRESOS (17 registros)
SET IDENTITY_INSERT [FINANZAS].[INGRESOS] ON;
INSERT INTO [FINANZAS].[INGRESOS] ([idIngreso], [nMonto], [cDescripcion], [dFecha], [idUsuario], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod], [idCategoria], [cCategoria], [idCuentaDestino], [cCuentaDestino], [cTipoFlujo], [cEstado], [lEsRecurrente]) VALUES
(1, 1200.00, N'Sueldo Johann - Abril 2026', '2026-04-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(3, 1200.00, N'Sueldo Johann - Mayo 2026', '2026-05-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(5, 1200.00, N'Sueldo Johann - Junio 2026', '2026-06-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(7, 1200.00, N'Sueldo Johann - Julio 2026', '2026-07-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(9, 1200.00, N'Sueldo Johann - Agosto 2026', '2026-08-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(11, 1200.00, N'Sueldo Johann - Septiembre 2026', '2026-09-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(12, 1200.00, N'Sueldo Johann - Octubre 2026', '2026-10-01', 2011, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(14, 1200.00, N'Sueldo Johann - Enero 2026', '2026-01-01', 2011, 1, 2011, '2026-11-27 01:54:54.15', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(15, 1200.00, N'Sueldo Johann - Febrero 2026', '2026-02-01', 2011, 1, 2011, '2026-11-27 01:54:54.15', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(16, 1200.00, N'Sueldo Johann - Marzo 2026', '2026-03-01', 2011, 1, 2011, '2026-11-27 01:54:54.15', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(17, 1200.00, N'Sueldo Johann - Noviembre 2026', '2026-11-01', 2011, 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(24, 1711.00, N'Sueldo Johann - Abril 2026', '2026-04-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(25, 4965.57, N'Sueldo Johann - Julio 2026', '2026-07-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(26, 2000.00, N'Sueldo Johann - Agosto 2026', '2026-08-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(27, 2000.00, N'Sueldo Johann - Septiembre 2026', '2026-09-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(28, 2000.00, N'Sueldo Johann - Octubre 2026', '2026-10-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1),
(29, 2000.00, N'Sueldo Johann - Noviembre 2026', '2026-11-01', 2013, 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL, 1, N'Salario / Nómina Directa', 1, N'Cuenta Corriente BBVA (Nómina)', N'Fijo', N'Confirmado', 1);
SET IDENTITY_INSERT [FINANZAS].[INGRESOS] OFF;
GO

-- Datos para FINANZAS.GASTOS (125 registros)
SET IDENTITY_INSERT [FINANZAS].[GASTOS] ON;
INSERT INTO [FINANZAS].[GASTOS] ([idGasto], [idCategoria], [idTipoGasto], [cGasto], [cDescripcion], [nMonto], [cFechaGasto], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(1, 9, 2, N'Mercado', N'Pago de Mercado - Abril 2026', 500.00, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(2, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Abril 2026', 29.90, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(3, 13, 2, N'Internet', N'Pago de Internet - Abril 2026', 55.00, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(4, 10, 2, N'Luz', N'Pago de Luz - Abril 2026', 195.00, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(5, 10, 2, N'Agua', N'Pago de Agua - Abril 2026', 30.30, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(6, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Abril 2026', 22.00, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(7, 16, 2, N'Víveres', N'Pago de Víveres - Abril 2026', 560.37, '2026-04-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(8, 9, 2, N'Mercado', N'Pago de Mercado - Mayo 2026', 500.00, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(9, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Mayo 2026', 29.90, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(10, 13, 2, N'Internet', N'Pago de Internet - Mayo 2026', 55.00, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(11, 10, 2, N'Luz', N'Pago de Luz - Mayo 2026', 175.50, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(12, 10, 2, N'Agua', N'Pago de Agua - Mayo 2026', 30.80, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(13, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Mayo 2026', 22.00, '2026-05-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(14, 9, 2, N'Mercado', N'Pago de Mercado - Junio 2026', 500.00, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(15, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Junio 2026', 29.90, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(16, 13, 2, N'Internet', N'Pago de Internet - Junio 2026', 55.00, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(17, 10, 2, N'Luz', N'Pago de Luz - Junio 2026', 131.00, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(18, 10, 2, N'Agua', N'Pago de Agua - Junio 2026', 33.80, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(19, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Junio 2026', 22.00, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(20, 16, 2, N'Víveres', N'Pago de Víveres - Junio 2026', 457.80, '2026-06-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(21, 9, 2, N'Mercado', N'Pago de Mercado - Julio 2026', 500.00, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(22, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Julio 2026', 29.90, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(23, 13, 2, N'Internet', N'Pago de Internet - Julio 2026', 55.00, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(24, 10, 2, N'Luz', N'Pago de Luz - Julio 2026', 111.40, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(25, 10, 2, N'Agua', N'Pago de Agua - Julio 2026', 26.50, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(26, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Julio 2026', 22.00, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(27, 16, 2, N'Víveres', N'Pago de Víveres - Julio 2026', 391.33, '2026-07-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(28, 9, 2, N'Mercado', N'Pago de Mercado - Agosto 2026', 500.00, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(29, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Agosto 2026', 29.90, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(30, 13, 2, N'Internet', N'Pago de Internet - Agosto 2026', 55.00, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(31, 10, 2, N'Luz', N'Pago de Luz - Agosto 2026', 137.20, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(32, 10, 2, N'Agua', N'Pago de Agua - Agosto 2026', 24.60, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(33, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Agosto 2026', 27.00, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(34, 16, 2, N'Víveres', N'Pago de Víveres - Agosto 2026', 500.00, '2026-08-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(35, 9, 2, N'Mercado', N'Pago de Mercado - Septiembre 2026', 500.00, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(36, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Septiembre 2026', 29.90, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(37, 13, 2, N'Internet', N'Pago de Internet - Septiembre 2026', 55.00, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(38, 10, 2, N'Luz', N'Pago de Luz - Septiembre 2026', 141.40, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(39, 10, 2, N'Agua', N'Pago de Agua - Septiembre 2026', 39.00, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(40, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Septiembre 2026', 27.00, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(41, 16, 2, N'Víveres', N'Pago de Víveres - Septiembre 2026', 486.60, '2026-09-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(42, 9, 2, N'Mercado', N'Pago de Mercado - Octubre 2026', 500.00, '2026-10-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(43, 13, 2, N'Internet', N'Pago de Internet - Octubre 2026', 55.00, '2026-10-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(44, 10, 2, N'Luz', N'Pago de Luz - Octubre 2026', 159.20, '2026-10-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(45, 10, 2, N'Agua', N'Pago de Agua - Octubre 2026', 25.00, '2026-10-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(46, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Octubre 2026', 27.00, '2026-10-15', 1, 2011, '2026-11-27 01:44:24.943', NULL, NULL),
(47, 9, 2, N'Mercado', N'Pago de Mercado - Enero 2026', 500.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(48, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Enero 2026', 29.90, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(49, 13, 2, N'Internet', N'Pago de Internet - Enero 2026', 55.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(50, 10, 2, N'Luz', N'Pago de Luz - Enero 2026', 185.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL);
INSERT INTO [FINANZAS].[GASTOS] ([idGasto], [idCategoria], [idTipoGasto], [cGasto], [cDescripcion], [nMonto], [cFechaGasto], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(51, 10, 2, N'Agua', N'Pago de Agua - Enero 2026', 30.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(52, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Enero 2026', 22.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(53, 16, 2, N'Víveres', N'Pago de Víveres - Enero 2026', 500.00, '2026-01-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(54, 9, 2, N'Mercado', N'Pago de Mercado - Febrero 2026', 500.00, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(55, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Febrero 2026', 29.90, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(56, 13, 2, N'Internet', N'Pago de Internet - Febrero 2026', 55.00, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(57, 10, 2, N'Luz', N'Pago de Luz - Febrero 2026', 170.00, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(58, 10, 2, N'Agua', N'Pago de Agua - Febrero 2026', 28.50, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(59, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Febrero 2026', 22.00, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(60, 16, 2, N'Víveres', N'Pago de Víveres - Febrero 2026', 500.00, '2026-02-15', 1, 2011, '2026-11-27 01:54:54.153', NULL, NULL),
(61, 9, 2, N'Mercado', N'Pago de Mercado - Marzo 2026', 500.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(62, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Marzo 2026', 29.90, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(63, 13, 2, N'Internet', N'Pago de Internet - Marzo 2026', 55.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(64, 10, 2, N'Luz', N'Pago de Luz - Marzo 2026', 190.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(65, 10, 2, N'Agua', N'Pago de Agua - Marzo 2026', 32.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(66, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Marzo 2026', 22.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(67, 16, 2, N'Víveres', N'Pago de Víveres - Marzo 2026', 500.00, '2026-03-15', 1, 2011, '2026-11-27 01:54:54.157', NULL, NULL),
(68, 9, 2, N'Mercado', N'Pago de Mercado - Noviembre 2026', 500.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', 2011, '2026-11-27 02:30:17.997'),
(69, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Noviembre 2026', 29.90, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(70, 13, 2, N'Internet', N'Pago de Internet - Noviembre 2026', 55.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(71, 10, 2, N'Luz', N'Pago de Luz - Noviembre 2026', 165.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(72, 10, 2, N'Agua', N'Pago de Agua - Noviembre 2026', 28.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(73, 18, 2, N'Coop Hvca Mamá', N'Pago de Coop Hvca Mamá - Noviembre 2026', 27.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(74, 16, 2, N'Víveres', N'Pago de Víveres - Noviembre 2026', 500.00, '2026-11-15', 1, 2011, '2026-11-27 01:58:01.87', NULL, NULL),
(75, 17, 2, N'Claro Mamá', N'Pago de Claro Mamá - Octubre 2026', 29.90, '2026-10-15', 1, 2011, '2026-11-27 02:13:04.597', NULL, NULL),
(76, 16, 2, N'Víveres', N'Pago de Víveres - Octubre 2026', 500.00, '2026-10-15', 1, 2011, '2026-11-27 02:13:04.6', NULL, NULL),
(87, 24, 2, N'Universidad', N'Pago de Universidad - Abril 2026', 675.00, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(88, 26, 2, N'Saldo Cerrado', N'Pago Saldo Cerrado - Abril 2026', 302.00, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(89, 25, 2, N'Netflix', N'Suscripción Netflix - Abril 2026', 52.90, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(90, 25, 2, N'Spotify', N'Suscripción Spotify - Abril 2026', 11.90, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(91, 18, 2, N'COOP Hvca', N'Aporte Cooperativa Huancavelica - Abril 2026', 22.00, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(92, 25, 1, N'Concierto', N'Entrada a Concierto - Abril 2026', 107.00, '2026-04-20', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(93, 28, 2, N'Esp. Eterna', N'Pago Cuota Esp. Eterna - Abril 2026', 236.11, '2026-04-15', 1, 2013, '2026-11-27 04:36:17.43', NULL, NULL),
(94, 10, 2, N'Casa Víveres', N'Gasto Casa Víveres - Julio 2026', 500.00, '2026-07-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(95, 28, 2, N'Seguro Vida Devol', N'Cuota Seguro Vida Devolución - Julio 2026', 188.00, '2026-07-03', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(96, 28, 2, N'Esp. Eterna 1', N'Cuota 3 Esp. Eterna Lote 1 - Julio 2026', 236.11, '2026-07-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(97, 28, 2, N'Esp. Eterna 2', N'Cuota 1 Esp. Eterna Lote 2 - Julio 2026', 186.81, '2026-07-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(98, 17, 2, N'Claro', N'Plan Móvil Claro - Julio 2026', 30.45, '2026-07-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(99, 25, 2, N'YouTube Premium', N'Suscripción YouTube Premium - Julio 2026', 26.90, '2026-07-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(100, 18, 2, N'Coop Huanca.', N'Aporte Coop Huancavelica - Julio 2026', 22.00, '2026-07-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(101, 10, 2, N'Pago Muni', N'Pago Arbitrios Municipalidad - Julio 2026', 15.00, '2026-07-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(102, 24, 2, N'UTP', N'Pensión Universidad UTP - Agosto 2026', 3121.85, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(103, 10, 2, N'Casa', N'Aporte Casa - Agosto 2026', 1000.00, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(104, 24, 2, N'Matrícula Universidad', N'Matrícula Universidad - Agosto 2026', 315.00, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(105, 28, 2, N'Esp. Eterna 1', N'Cuota 4 Esp. Eterna Lote 1 - Agosto 2026', 236.11, '2026-08-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(106, 28, 2, N'Esp. Eterna 2', N'Cuota 2 Esp. Eterna Lote 2 - Agosto 2026', 186.81, '2026-08-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(107, 28, 2, N'Seguro Vida Devol', N'Cuota Seguro Vida Devolución - Agosto 2026', 188.00, '2026-08-03', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(108, 17, 2, N'Claro', N'Plan Móvil Claro - Agosto 2026', 30.45, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(109, 26, 2, N'Tarjeta American Exp', N'Pago Tarjeta American Express - Agosto 2026', 27.87, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(110, 18, 2, N'Coop Huanca.', N'Aporte Coop Huancavelica - Agosto 2026', 27.00, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL);
INSERT INTO [FINANZAS].[GASTOS] ([idGasto], [idCategoria], [idTipoGasto], [cGasto], [cDescripcion], [nMonto], [cFechaGasto], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(111, 25, 2, N'YouTube Premium', N'Suscripción YouTube Premium - Agosto 2026', 26.90, '2026-08-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(112, 10, 2, N'Casa', N'Aporte Casa - Septiembre 2026', 1100.00, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(113, 28, 2, N'Esp. Eterna 1', N'Cuota 5 Esp. Eterna Lote 1 - Septiembre 2026', 236.11, '2026-09-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(114, 28, 2, N'Seguro Vida Devol', N'Cuota Seguro Vida Devolución - Septiembre 2026', 188.00, '2026-09-03', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(115, 28, 2, N'Esp. Eterna 2', N'Cuota 3 Esp. Eterna Lote 2 - Septiembre 2026', 186.81, '2026-09-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(116, 27, 2, N'Mamá', N'Apoyo Familiar Mamá - Septiembre 2026', 100.00, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(117, 26, 2, N'Tarjeta American Exp', N'Pago Tarjeta American Express - Septiembre 2026', 43.38, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(118, 17, 2, N'Claro', N'Plan Móvil Claro - Septiembre 2026', 30.45, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(119, 25, 2, N'YouTube Premium', N'Suscripción YouTube Premium - Septiembre 2026', 30.45, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(120, 18, 2, N'Coop Huanca.', N'Aporte Coop Huancavelica - Septiembre 2026', 27.00, '2026-09-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(121, 10, 2, N'Casa', N'Aporte Casa - Octubre 2026', 1100.00, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(122, 28, 2, N'Esp. Eterna 1', N'Cuota 6 Esp. Eterna Lote 1 - Octubre 2026', 236.11, '2026-10-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(123, 28, 2, N'Seguro Vida Devol', N'Cuota Seguro Vida Devolución - Octubre 2026', 188.00, '2026-10-03', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(124, 28, 2, N'Esp. Eterna 2', N'Cuota 4 Esp. Eterna Lote 2 - Octubre 2026', 186.81, '2026-10-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(125, 27, 2, N'Mamá', N'Apoyo Familiar Mamá - Octubre 2026', 100.00, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(126, 26, 2, N'Tarjeta American Exp', N'Pago Tarjeta American Express - Octubre 2026', 43.38, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(127, 25, 2, N'YouTube Premium', N'Suscripción YouTube Premium - Octubre 2026', 30.45, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(128, 17, 2, N'Claro', N'Plan Móvil Claro - Octubre 2026', 30.45, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(129, 18, 2, N'Coop Huanca.', N'Aporte Coop Huancavelica - Octubre 2026', 27.00, '2026-10-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(130, 10, 2, N'Casa', N'Aporte Casa - Noviembre 2026', 1000.00, '2026-11-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(131, 28, 2, N'Esp. Eterna 1', N'Cuota 7 Esp. Eterna Lote 1 - Noviembre 2026', 236.11, '2026-11-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(132, 28, 2, N'Seguro Vida Devol', N'Cuota Seguro Vida Devolución - Noviembre 2026', 188.00, '2026-11-03', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(133, 28, 2, N'Esp. Eterna 2', N'Cuota 5 Esp. Eterna Lote 2 - Noviembre 2026', 186.81, '2026-11-04', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(134, 17, 2, N'Claro', N'Plan Móvil Claro - Noviembre 2026', 30.45, '2026-11-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL),
(135, 18, 2, N'Coop Huanca.', N'Aporte Coop Huancavelica - Noviembre 2026', 27.00, '2026-11-15', 1, 2013, '2026-11-27 04:36:17.433', NULL, NULL);
SET IDENTITY_INSERT [FINANZAS].[GASTOS] OFF;
GO

-- Datos para FINANZAS.PRESUPUESTO (13 registros)
SET IDENTITY_INSERT [FINANZAS].[PRESUPUESTO] ON;
INSERT INTO [FINANZAS].[PRESUPUESTO] ([idPresupuesto], [idCategoria], [idTipoPeriodo], [idEstadoPresupuesto], [nLimPresuMens], [nUmbralAlerta], [lEsActivo], [idUsuReg], [dFecReg], [idUsuMod], [dFecMod]) VALUES
(1, 9, 3, 1, 500.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(2, 16, 3, 1, 500.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(3, 13, 3, 1, 55.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(4, 10, 3, 1, 250.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(5, 17, 3, 1, 30.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(6, 18, 3, 1, 30.00, 80, 1, 2011, '2026-11-27 01:44:24.94', NULL, NULL),
(9, 10, 3, 1, 1100.00, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(10, 28, 3, 1, 610.92, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(11, 17, 3, 1, 30.45, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(12, 18, 3, 1, 27.00, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(13, 26, 3, 1, 43.38, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(14, 25, 3, 1, 30.45, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL),
(15, 27, 3, 1, 100.00, 80, 1, 2013, '2026-11-27 04:44:18.8', NULL, NULL);
SET IDENTITY_INSERT [FINANZAS].[PRESUPUESTO] OFF;
GO

-- Datos para FINANZAS.LOGS_AUDITORIA (7 registros)
SET IDENTITY_INSERT [FINANZAS].[LOGS_AUDITORIA] ON;
INSERT INTO [FINANZAS].[LOGS_AUDITORIA] ([idLog], [idUsuarioOwner], [cAccion], [cDetalle], [dFecha]) VALUES
(4087, 2011, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 02:15:34.53'),
(4088, 2011, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 02:25:32.443'),
(4089, 2011, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 02:36:51.39'),
(4090, 2011, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 02:54:24.02'),
(4091, 2011, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 03:33:37.84'),
(4092, 2013, N'REGISTRO', N'Nuevo usuario registrado en la aplicación.', '2026-11-27 04:31:13.347'),
(4093, 2013, N'LOGIN', N'Inicio de sesión exitoso.', '2026-11-27 04:31:16.86');
SET IDENTITY_INSERT [FINANZAS].[LOGS_AUDITORIA] OFF;
GO

-- =====================================================================
-- 4. PROCEDIMIENTOS ALMACENADOS (STORED PROCEDURES)
-- =====================================================================

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ActualizaAhorroIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ActualizaAhorroIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ActualizaAhorroIngresado_SP];
GO

CREATE PROCEDURE CG_ActualizaAhorroIngresado_SP
    @xmlAhorro XML
AS
BEGIN
    DECLARE @idoc INT;
    DECLARE @tmpAhorro TABLE (
        idAhorro INT,
        idMeta INT,
        monto DECIMAL(15,2),
        fechaAhorro DATE,
        descripcion NVARCHAR(300),
        idUsuario INT
    );
    
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAhorro;
    
    INSERT INTO @tmpAhorro
    SELECT
        idAhorro
        , idMeta
        , monto
        , CAST(CAST(fechaAhorro AS DATETIMEOFFSET) AS DATE)
        , descripcion
        , idUsuario
    FROM OPENXML(@idoc, '/ahorro', 2)
    WITH (
        idAhorro INT,
        idMeta INT,
        monto DECIMAL(15,2),
        fechaAhorro DATE,
        descripcion VARCHAR(300),
        idUsuario INT
    );
    
    EXEC sp_xml_removedocument @idoc;
    
    BEGIN TRANSACTION;
    BEGIN TRY
        DECLARE @idAhorro INT = (SELECT idAhorro FROM @tmpAhorro);
        DECLARE @idMetaOld INT = (SELECT idMeta FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);
        DECLARE @montoOld DECIMAL(15,2) = (SELECT nMonto FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);
        
        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual - @montoOld WHERE idMeta = @idMetaOld;
        
        UPDATE a
        SET a.idMeta = t.idMeta,
            a.nMonto = t.monto,
            a.dFecha = t.fechaAhorro,
            a.cDescripcion = t.descripcion,
            a.idUsuMod = t.idUsuario,
            a.dFecMod = GETDATE()
        FROM FINANZAS.AHORROS a
        INNER JOIN @tmpAhorro t
            ON a.idAhorro = t.idAhorro;
        
        DECLARE @idMetaNew INT = (SELECT idMeta FROM @tmpAhorro);
        DECLARE @montoNew DECIMAL(15,2) = (SELECT monto FROM @tmpAhorro);
        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual + @montoNew WHERE idMeta = @idMetaNew;
        
        COMMIT TRANSACTION;
        SELECT cMsje = 'Se ha realizado la operaciÃ³n correctamente.';
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ActualizaDeudaIngresada_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ActualizaDeudaIngresada_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ActualizaDeudaIngresada_SP];
GO

CREATE PROCEDURE CG_ActualizaDeudaIngresada_SP
    @xmlDeuda XML
AS
BEGIN
    DECLARE @idoc INT;
    DECLARE @tmpDeuda TABLE (
        idDeuda INT,
        acreedor NVARCHAR(150),
        montoTotal DECIMAL(15,2),
        tasaInteres DECIMAL(5,2),
        cuotasTotales INT,
        cuotasPagadas INT,
        fechaInicio DATE,
        idUsuario INT
    );
    
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlDeuda;
    INSERT INTO @tmpDeuda
    SELECT idDeuda, acreedor, montoTotal, tasaInteres, cuotasTotales, cuotasPagadas, CAST(CAST(fechaInicio AS DATETIMEOFFSET) AS DATE), idUsuario
    FROM OPENXML(@idoc, '/deuda', 2)
    WITH (
        idDeuda INT,
        acreedor VARCHAR(150),
        montoTotal DECIMAL(15,2),
        tasaInteres DECIMAL(5,2),
        cuotasTotales INT,
        cuotasPagadas INT,
        fechaInicio DATE,
        idUsuario INT
    );
    EXEC sp_xml_removedocument @idoc;
    
    BEGIN TRANSACTION;
    BEGIN TRY
        UPDATE d
        SET d.cAcreedor = t.acreedor,
            d.nMontoTotal = t.montoTotal,
            d.nTasaInteres = t.tasaInteres,
            d.nCuotasTotales = t.cuotasTotales,
            d.nCuotasPagadas = t.cuotasPagadas,
            d.dFechaInicio = t.fechaInicio,
            d.idUsuMod = t.idUsuario,
            d.dFecMod = GETDATE()
        FROM FINANZAS.DEUDAS d
        INNER JOIN @tmpDeuda t ON d.idDeuda = t.idDeuda;
        
        COMMIT TRANSACTION;
        SELECT cMsje = 'Se ha realizado la operación correctamente.';
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ActualizaGastoIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ActualizaGastoIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ActualizaGastoIngresado_SP];
GO

CREATE   PROCEDURE [dbo].[CG_ActualizaGastoIngresado_SP] (
    @xmlGasto XML
) AS
BEGIN
    DECLARE @nTrancount INT = @@TRANCOUNT;
    DECLARE @idoc INT;
    DECLARE @tmpGasto TABLE (
        idGasto INT
        , monto DECIMAL(15,2)
        , idCategoria INT
        , idTipoGasto INT
        , descripcion NVARCHAR(255)
        , fechaGasto DATE
        , idUsuario INT
    );
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlGasto;
    INSERT INTO @tmpGasto
    SELECT
        idGasto
        , monto
        , idCategoria
        , idTipoGasto
        , descripcion
        , CAST(CAST(fechaGasto AS DATETIMEOFFSET) AS DATE)
        , idUsuario
    FROM OPENXML(@idoc, '/gasto', 2)
    WITH (
        idGasto INT
        , monto DECIMAL(10,2)
        , idCategoria INT
        , idTipoGasto INT
        , descripcion VARCHAR(255)
        , fechaGasto DATE
        , idUsuario INT
    );
    IF @nTrancount = 0
    BEGIN
        BEGIN TRANSACTION;
    END;
    BEGIN TRY
        UPDATE fgas
        SET
            fgas.idCategoria = tmp.idCategoria
            , fgas.idTipoGasto = tmp.idTipoGasto
            , fgas.cDescripcion = tmp.descripcion
            , fgas.nMonto = tmp.monto
            , fgas.cFechaGasto = tmp.fechaGasto
            , fgas.idUsuMod = tmp.idUsuario
            , fgas.dFecMod = GETDATE()
        FROM FINANZAS.GASTOS fgas
        INNER JOIN @tmpGasto tmp
            ON fgas.idGasto = tmp.idGasto;
        IF @nTrancount = 0
        BEGIN
            COMMIT TRANSACTION;
        END;
        SELECT cMsje = 'Se actualizÃ³ correctamente el gasto.';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        SELECT 
            ERROR_NUMBER()      AS errorNumber,
            ERROR_MESSAGE()     AS message,
            ERROR_SEVERITY()    AS severity,
            ERROR_STATE()       AS state,
            ERROR_PROCEDURE()   AS procedureName,
            ERROR_LINE()        AS line;
    END CATCH;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ActualizaPermisoUsuario_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ActualizaPermisoUsuario_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ActualizaPermisoUsuario_SP];
GO

CREATE   PROCEDURE CG_ActualizaPermisoUsuario_SP
    @idCompartido INT,
    @cPermiso VARCHAR(50)
AS
BEGIN
    DECLARE @idOwner INT = (SELECT idUsuarioOwner FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idCompartido = @idCompartido);
    DECLARE @emailInvitado VARCHAR(100) = (SELECT u.email FROM dbo.usuarios u INNER JOIN FINANZAS.USUARIOS_COMPARTIDOS uc ON uc.idUsuarioInvitado = u.id WHERE uc.idCompartido = @idCompartido);
    UPDATE FINANZAS.USUARIOS_COMPARTIDOS
    SET cPermiso = @cPermiso, idUsuMod = @idOwner, dFecMod = GETDATE()
    WHERE idCompartido = @idCompartido;
    IF @idOwner IS NOT NULL
    BEGIN
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idOwner, 'ACTUALIZAR ROL', 'Rol actualizado a ' + @cPermiso + ' para ' + ISNULL(@emailInvitado, 'usuario'), GETDATE());
    END;
    SELECT 'Permiso actualizado exitosamente.' AS cMsje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ActualizarIngreso_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ActualizarIngreso_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ActualizarIngreso_SP];
GO

CREATE   PROCEDURE CG_ActualizarIngreso_SP
    @idIngreso INT,
    @nMonto DECIMAL(18,2),
    @cDescripcion NVARCHAR(255),
    @dFecha DATE,
    @cCategoria NVARCHAR(100),
    @cCuentaDestino NVARCHAR(100),
    @cTipoFlujo NVARCHAR(20),
    @cEstado NVARCHAR(20),
    @lEsRecurrente BIT,
    @idUsuario INT
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @idCat INT = (SELECT TOP 1 idCategoria FROM FINANZAS.CATEGORIAS_INGRESOS WHERE cNombre = @cCategoria);
    IF @idCat IS NULL
        SET @idCat = (SELECT TOP 1 idCategoria FROM FINANZAS.CATEGORIAS_INGRESOS WHERE @cCategoria LIKE '%' + cNombre + '%' OR cNombre LIKE '%' + @cCategoria + '%');
    IF @idCat IS NULL
        SET @idCat = 1;
    DECLARE @idCta INT = (SELECT TOP 1 idCuenta FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = @cCuentaDestino);
    IF @idCta IS NULL
        SET @idCta = (SELECT TOP 1 idCuenta FROM FINANZAS.CUENTAS_DESTINO WHERE @cCuentaDestino LIKE '%' + cNombre + '%' OR cNombre LIKE '%' + @cCuentaDestino + '%');
    IF @idCta IS NULL
        SET @idCta = 1;
    UPDATE FINANZAS.INGRESOS SET
        nMonto = @nMonto,
        cDescripcion = @cDescripcion,
        dFecha = @dFecha,
        idCategoria = @idCat,
        cCategoria = @cCategoria,
        idCuentaDestino = @idCta,
        cCuentaDestino = @cCuentaDestino,
        cTipoFlujo = @cTipoFlujo,
        cEstado = @cEstado,
        lEsRecurrente = @lEsRecurrente,
        idUsuMod = @idUsuario,
        dFecMod = GETDATE()
    WHERE idIngreso = @idIngreso AND idUsuario = @idUsuario;
    SELECT @idIngreso AS idIngreso, 'Ingreso actualizado correctamente.' AS mensaje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_DesencriptarTexto_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_DesencriptarTexto_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_DesencriptarTexto_SP];
GO

CREATE PROCEDURE CG_DesencriptarTexto_SP
    @textoEncriptado VARCHAR(255),
    @textoPlano VARCHAR(255) OUTPUT
AS
BEGIN
    DECLARE @binary VARBINARY(255) = CONVERT(VARBINARY(255), @textoEncriptado, 1);
    SET @textoPlano = CONVERT(VARCHAR(255), DECRYPTBYPASSPHRASE('ClaveControlGastos2026', @binary));
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarAhorroPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarAhorroPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarAhorroPorId_SP];
GO

CREATE   PROCEDURE CG_EliminarAhorroPorId_SP @idAhorro INT AS
BEGIN
    BEGIN TRY
        DECLARE @idMeta INT = (SELECT idMeta FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);
        DECLARE @monto DECIMAL(15,2) = (SELECT nMonto FROM FINANZAS.AHORROS WHERE idAhorro = @idAhorro);
        UPDATE FINANZAS.AHORROS SET lEsActivo = 0, dFecMod = GETDATE() WHERE idAhorro = @idAhorro;
        UPDATE FINANZAS.METAS_AHORRO SET nMontoActual = nMontoActual - @monto, dFecMod = GETDATE() WHERE idMeta = @idMeta;
        SELECT cMsje = 'Se ha realizado la operaciÃ³n correctamente.';
    END TRY
    BEGIN CATCH
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarDeudaPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarDeudaPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarDeudaPorId_SP];
GO

CREATE   PROCEDURE CG_EliminarDeudaPorId_SP @idDeuda INT AS
BEGIN
    BEGIN TRY
        UPDATE FINANZAS.DEUDAS SET lEsActivo = 0, dFecMod = GETDATE() WHERE idDeuda = @idDeuda;
        UPDATE FINANZAS.ABONO_DEUDAS SET lEsActivo = 0, dFecMod = GETDATE() WHERE idDeuda = @idDeuda;
        SELECT cMsje = 'Se ha realizado la operaciÃ³n correctamente.';
    END TRY
    BEGIN CATCH
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarGastoPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarGastoPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarGastoPorId_SP];
GO

CREATE   PROCEDURE CG_EliminarGastoPorId_SP (@idGasto INT) AS
BEGIN
    UPDATE FINANZAS.GASTOS SET lEsActivo = 0, dFecMod = GETDATE() WHERE idGasto = @idGasto;
    SELECT cMsje = 'El gasto fue eliminado correctamente.';
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarIngreso_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarIngreso_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarIngreso_SP];
GO

CREATE PROCEDURE CG_EliminarIngreso_SP
    @idIngreso INT,
    @idUsuario INT
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE FINANZAS.INGRESOS SET
        lEsActivo = 0,
        idUsuMod = @idUsuario,
        dFecMod = GETDATE()
    WHERE idIngreso = @idIngreso AND idUsuario = @idUsuario;
    SELECT @idIngreso AS idIngreso, 'Ingreso eliminado correctamente.' AS mensaje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarPresupuestoPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarPresupuestoPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarPresupuestoPorId_SP];
GO

CREATE   PROCEDURE CG_EliminarPresupuestoPorId_SP (@idPresupuesto INT) AS
BEGIN
    UPDATE FINANZAS.PRESUPUESTO SET lEsActivo = 0, dFecMod = GETDATE() WHERE idPresupuesto = @idPresupuesto;
    SELECT cMsje = 'El presupuesto fue eliminado correctamente.';
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EliminarUsuarioCompartido_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EliminarUsuarioCompartido_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EliminarUsuarioCompartido_SP];
GO

CREATE   PROCEDURE CG_EliminarUsuarioCompartido_SP
    @idCompartido INT
AS
BEGIN
    DECLARE @idOwner INT = (SELECT idUsuarioOwner FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idCompartido = @idCompartido);
    DECLARE @emailInvitado VARCHAR(100) = (SELECT u.email FROM dbo.usuarios u INNER JOIN FINANZAS.USUARIOS_COMPARTIDOS uc ON uc.idUsuarioInvitado = u.id WHERE uc.idCompartido = @idCompartido);
    UPDATE FINANZAS.USUARIOS_COMPARTIDOS
    SET lEsActivo = 0, idUsuMod = @idOwner, dFecMod = GETDATE()
    WHERE idCompartido = @idCompartido;
    IF @idOwner IS NOT NULL
    BEGIN
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idOwner, 'ELIMINAR ACCESO', 'Acceso revocado/eliminado para ' + ISNULL(@emailInvitado, 'usuario'), GETDATE());
    END;
    SELECT 'Acceso revocado/eliminado exitosamente.' AS cMsje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_EncriptarTexto_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_EncriptarTexto_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_EncriptarTexto_SP];
GO

CREATE PROCEDURE CG_EncriptarTexto_SP
    @textoPlano VARCHAR(255),
    @textoEncriptado VARCHAR(255) OUTPUT
AS
BEGIN
    DECLARE @binary VARBINARY(255) = ENCRYPTBYPASSPHRASE('ClaveControlGastos2026', @textoPlano);
    SET @textoEncriptado = CONVERT(VARCHAR(255), @binary, 1);
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaAbonoIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaAbonoIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaAbonoIngresado_SP];
GO

CREATE PROCEDURE CG_GuardaAbonoIngresado_SP
    @xmlAbono XML
AS
BEGIN
    DECLARE @idoc INT;
    DECLARE @tmpAbono TABLE (
        idDeuda INT,
        montoAbono DECIMAL(15,2),
        fechaPago DATE,
        idUsuario INT
    );
    
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAbono;
    
    INSERT INTO @tmpAbono
    SELECT
        idDeuda
        , montoAbono
        , CAST(CAST(fechaPago AS DATETIMEOFFSET) AS DATE)
        , 1
    FROM OPENXML(@idoc, '/abonoDeuda', 2)
    WITH (
        idDeuda INT,
        montoAbono DECIMAL(15,2),
        fechaPago DATE
    );
    
    EXEC sp_xml_removedocument @idoc;
    
    BEGIN TRANSACTION;
    BEGIN TRY
        DECLARE @idDeuda INT = (SELECT idDeuda FROM @tmpAbono);
        DECLARE @monto DECIMAL(15,2) = (SELECT montoAbono FROM @tmpAbono);
        DECLARE @fecha DATE = (SELECT fechaPago FROM @tmpAbono);
        
        -- Obtener idUsuario de la deuda para asignarlo al abono
        DECLARE @idUsuario INT = (SELECT idUsuario FROM FINANZAS.DEUDAS WHERE idDeuda = @idDeuda);
        
        -- Registrar el abono con el usuario correcto
        INSERT INTO FINANZAS.ABONO_DEUDAS (idDeuda, nMontoAbono, dFechaPago, idUsuReg)
        VALUES (@idDeuda, @monto, @fecha, @idUsuario);
        
        -- Incrementar cuotas pagadas en DEUDAS
        UPDATE FINANZAS.DEUDAS 
        SET nCuotasPagadas = nCuotasPagadas + 1 
        WHERE idDeuda = @idDeuda;
        
        COMMIT TRANSACTION;
        SELECT cMsje = 'Se ha realizado la operación correctamente.';
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaAhorroIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaAhorroIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaAhorroIngresado_SP];
GO

CREATE PROCEDURE CG_GuardaAhorroIngresado_SP
    @xmlAhorro XML
AS
BEGIN
    DECLARE @idoc INT;
    DECLARE @tmpAhorro TABLE (
        idMeta INT,
        monto DECIMAL(15,2),
        fechaAhorro DATE,
        descripcion NVARCHAR(300),
        idUsuario INT
    );
    
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlAhorro;
    
    INSERT INTO @tmpAhorro
    SELECT
        idMeta
        , monto
        , CAST(CAST(fechaAhorro AS DATETIMEOFFSET) AS DATE)
        , descripcion
        , idUsuario
    FROM OPENXML(@idoc, '/ahorro', 2)
    WITH (
        idMeta INT,
        monto DECIMAL(15,2),
        fechaAhorro DATE,
        descripcion VARCHAR(300),
        idUsuario INT
    );
    
    EXEC sp_xml_removedocument @idoc;
    
    BEGIN TRANSACTION;
    BEGIN TRY
        INSERT INTO FINANZAS.AHORROS (idMeta, nMonto, dFecha, cDescripcion, idUsuReg)
        SELECT
            idMeta
            , monto
            , fechaAhorro
            , descripcion
            , idUsuario
        FROM @tmpAhorro;
        
        UPDATE m
        SET m.nMontoActual = m.nMontoActual + t.monto
        FROM FINANZAS.METAS_AHORRO m
        INNER JOIN @tmpAhorro t
            ON m.idMeta = t.idMeta;
        
        COMMIT TRANSACTION;
        SELECT cMsje = 'Se ha realizado la operaciÃ³n correctamente.';
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaDeudaIngresada_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaDeudaIngresada_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaDeudaIngresada_SP];
GO

CREATE PROCEDURE CG_GuardaDeudaIngresada_SP
    @xmlDeuda XML
AS
BEGIN
    DECLARE @idoc INT;
    DECLARE @tmpDeuda TABLE (
        acreedor NVARCHAR(150),
        montoTotal DECIMAL(15,2),
        tasaInteres DECIMAL(5,2),
        cuotasTotales INT,
        fechaInicio DATE,
        idUsuario INT
    );
    
    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlDeuda;
    INSERT INTO @tmpDeuda
    SELECT acreedor, montoTotal, tasaInteres, cuotasTotales, CAST(CAST(fechaInicio AS DATETIMEOFFSET) AS DATE), idUsuario
    FROM OPENXML(@idoc, '/deuda', 2)
    WITH (
        acreedor VARCHAR(150),
        montoTotal DECIMAL(15,2),
        tasaInteres DECIMAL(5,2),
        cuotasTotales INT,
        fechaInicio DATE,
        idUsuario INT
    );
    EXEC sp_xml_removedocument @idoc;
    
    BEGIN TRANSACTION;
    BEGIN TRY
        INSERT INTO FINANZAS.DEUDAS (cAcreedor, nMontoTotal, nTasaInteres, nCuotasTotales, nCuotasPagadas, dFechaInicio, idUsuario, idUsuReg)
        SELECT acreedor, montoTotal, tasaInteres, cuotasTotales, 0, fechaInicio, idUsuario, idUsuario
        FROM @tmpDeuda;
        
        COMMIT TRANSACTION;
        SELECT cMsje = 'Se ha realizado la operación correctamente.';
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaGastoIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaGastoIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaGastoIngresado_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Elimina el gasto ingresado
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
  EXEC CG_GuardaGastoIngresado_SP '<gasto><descripcion>WWWWWWWWWWWW</descripcion><fechaGasto>2026-06-07T00:00:00-05:00</fechaGasto><fechaGastoFormat>2026-06-07</fechaGastoFormat><idCategoria>1</idCategoria><idGasto>0</idGasto><idTipoGasto>1</idTipoGasto><idUsuario>0</idUsuario><monto>131.0</monto></gasto>'
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE [dbo].[CG_GuardaGastoIngresado_SP] (
    @xmlGasto XML 
) AS
BEGIN
    DECLARE @nTrancount INT = @@TRANCOUNT;
    DECLARE @idoc INT;
    DECLARE @tmpGasto TABLE (
        monto DECIMAL(15,2)
        , idCategoria INT
        , idTipoGasto INT
        , descripcion NVARCHAR(255)
        , fechaGasto DATE
        , idUsuario INT
    )

	---------------------------------------------------------------------- 
	--Preparamos xml
	---------------------------------------------------------------------- 
	EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlGasto 
    INSERT INTO @tmpGasto
	SELECT
        monto
        , idCategoria
        , idTipoGasto
        , descripcion
        , CAST(
            CAST(fechaGasto AS DATETIMEOFFSET)
            AS DATE
        )
        , idUsuario
    FROM OPENXML(@idoc, '/gasto', 2)
    WITH (
        monto DECIMAL(10,2)
        , idCategoria INT
        , idTipoGasto INT
        , descripcion VARCHAR(255)
        , fechaGasto DATE
        , idUsuario INT
    );

    IF @nTrancount = 0
    BEGIN
        BEGIN TRANSACTION;
    END;

    BEGIN TRY
        INSERT INTO FINANZAS.GASTOS (
            idCategoria
            , idTipoGasto
            , cGasto
            , cDescripcion
            , nMonto
            , cFechaGasto
            , idUsuReg
        ) SELECT
            tmp.idCategoria
            , idTipoGasto
            , cate.cDescripcion
            , tmp.descripcion
            , tmp.monto
            , tmp.fechaGasto
            , tmp.idUsuario
        FROM @tmpGasto tmp
        INNER JOIN MANTENEDOR.CATEGORIAS cate
            ON tmp.idCategoria = cate.idCategoria
        
        IF @nTrancount = 0
        BEGIN
            COMMIT TRANSACTION;
        END;

        SELECT cMsje = 'Se ha realizado la operación correctamente.';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        
        SELECT 
            ERROR_NUMBER()      AS 'errorNumber',
            ERROR_MESSAGE()     AS 'message',
            ERROR_SEVERITY()    AS 'severity',
            ERROR_STATE()       AS 'state',
            ERROR_PROCEDURE()   AS 'procedure',
            ERROR_LINE()        AS 'line';
    END CATCH;
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaInvitacionUsuario_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaInvitacionUsuario_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaInvitacionUsuario_SP];
GO

CREATE   PROCEDURE CG_GuardaInvitacionUsuario_SP
    @xmlData NVARCHAR(MAX)
AS
BEGIN
    DECLARE @idDoc INT;
    EXEC sp_xml_preparedocument @idDoc OUTPUT, @xmlData;
    DECLARE @usuarioOwnerId INT;
    DECLARE @emailInvitado VARCHAR(100);
    DECLARE @permiso VARCHAR(50);
    SELECT 
        @usuarioOwnerId = usuarioOwnerId,
        @emailInvitado = emailInvitado,
        @permiso = permiso
    FROM OPENXML(@idDoc, '/usuarioCompartido', 2)
    WITH (
        usuarioOwnerId INT,
        emailInvitado VARCHAR(100),
        permiso VARCHAR(50)
    );
    EXEC sp_xml_removedocument @idDoc;
    IF @emailInvitado IS NULL OR @emailInvitado = ''
    BEGIN
        SELECT 'Debe ingresar un correo electrÃ³nico vÃ¡lido.' AS cMsje;
        RETURN;
    END;
    DECLARE @idInvitado INT = (SELECT id FROM dbo.usuarios WHERE email = @emailInvitado);
    IF @idInvitado IS NULL
    BEGIN
        INSERT INTO dbo.usuarios (nombre, email, password, rol, fecha_creacion) 
        VALUES ('', @emailInvitado, 'PENDIENTE', 'USER', GETDATE());
        SET @idInvitado = SCOPE_IDENTITY();
        INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD(idUsuario, lMfaHabilitado)
        VALUES (@idInvitado, 0);
    END;
    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 1)
    BEGIN
        SELECT 'El usuario ya tiene acceso a este espacio de trabajo.' AS cMsje;
        RETURN;
    END;
    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 0)
    BEGIN
        UPDATE FINANZAS.USUARIOS_COMPARTIDOS 
        SET lEsActivo = 1, cPermiso = @permiso, idUsuMod = @usuarioOwnerId, dFecMod = GETDATE()
        WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado;
    END
    ELSE
    BEGIN
        INSERT INTO FINANZAS.USUARIOS_COMPARTIDOS (idUsuarioOwner, idUsuarioInvitado, cPermiso, lEsActivo, idUsuReg, dFecReg)
        VALUES (@usuarioOwnerId, @idInvitado, @permiso, 1, @usuarioOwnerId, GETDATE());
    END;
    INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
    VALUES (@usuarioOwnerId, 'INVITAR', 'InvitaciÃ³n enviada a ' + @emailInvitado + ' con permiso de ' + @permiso, GETDATE());
    SELECT 'InvitaciÃ³n registrada exitosamente.' AS cMsje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaMetaAhorro_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaMetaAhorro_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaMetaAhorro_SP];
GO

CREATE PROCEDURE CG_GuardaMetaAhorro_SP
    @cDescripcion NVARCHAR(100),
    @nMontoObjetivo DECIMAL(15,2),
    @nAsignacionMensual DECIMAL(15,2),
    @dFechaEstimada DATE,
    @idUsuario INT
AS
BEGIN
    IF UPPER(LTRIM(RTRIM(@cDescripcion))) = 'FONDO EMERGENCIA' AND EXISTS (
        SELECT 1 FROM FINANZAS.METAS_AHORRO
        WHERE UPPER(LTRIM(RTRIM(cDescripcion))) = 'FONDO EMERGENCIA'
          AND idUsuReg = @idUsuario
          AND lEsActivo = 1
    )
    BEGIN
        SELECT cMsje = 'ERROR: Ya existe una meta activa llamada "Fondo Emergencia". No se puede duplicar.';
        RETURN;
    END;

    IF UPPER(LTRIM(RTRIM(@cDescripcion))) IN ('AHORRO', 'AHORROS')
    BEGIN
        SELECT cMsje = 'ERROR: No puede usar el nombre "Ahorro" o "Ahorros" para una meta de ahorro individual, ya que estÃ¡ reservado para el presupuesto mensual general.';
        RETURN;
    END;

    INSERT INTO FINANZAS.METAS_AHORRO (cDescripcion, nMontoObjetivo, nMontoActual, dFechaEstimada, idUsuReg, nAsignacionMensual)
    VALUES (@cDescripcion, @nMontoObjetivo, 0.00, @dFechaEstimada, @idUsuario, @nAsignacionMensual);
    
    SELECT cMsje = 'Meta de ahorro guardada correctamente.';
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_GuardaPresupuestoIngresado_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_GuardaPresupuestoIngresado_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_GuardaPresupuestoIngresado_SP];
GO

CREATE PROCEDURE CG_GuardaPresupuestoIngresado_SP (
    @xmlPresupuesto XML,
    @idUsuario INT
) AS
BEGIN
    DECLARE @fecActual DATE = GETDATE();
    DECLARE @fecIniMes DATE = DATEADD(DAY, 1, EOMONTH(@fecActual, -1));
    DECLARE @fecFinMes DATE = EOMONTH(@fecActual);

    DECLARE @nTrancount INT = @@TRANCOUNT;
    DECLARE @idoc INT;
    DECLARE @tmpPresupuesto TABLE (
          limPresuMens DECIMAL(15,2)
        , umbralAlerta TINYINT
        , idCategoria INT
        , idTipoPeriodo INT
    )

    EXEC sp_xml_preparedocument @idoc OUTPUT, @xmlPresupuesto 
    INSERT INTO @tmpPresupuesto
    SELECT
        limPresuMens
        , umbralAlerta
        , idCategoria
        , idTipoPeriodo
    FROM OPENXML(@idoc, '/presupuesto', 2)
    WITH (
          limPresuMens DECIMAL(15,2)
        , umbralAlerta TINYINT
        , idCategoria INT
        , idTipoPeriodo INT
    );

    IF @nTrancount = 0
    BEGIN
        BEGIN TRANSACTION;
    END;

    BEGIN TRY
        IF EXISTS(
            SELECT 1 FROM @tmpPresupuesto TMP 
            INNER JOIN FINANZAS.PRESUPUESTO PR 
            ON PR.idCategoria = TMP.idCategoria
            AND PR.lEsActivo = 1
            AND PR.idUsuReg = @idUsuario)
        BEGIN
            SELECT cMsje = 'La categoria seleccionada ya existe en el presupuesto.';
            RETURN;
        END

        INSERT INTO FINANZAS.PRESUPUESTO (
              idCategoria
            , idEstadoPresupuesto
            , idTipoPeriodo
            , nLimPresuMens
            , nUmbralAlerta
            , idUsuReg
        ) SELECT
            tmp.idCategoria
            , CASE
                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) > 100
                    THEN 2 -- EXCEDIDO
                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) >= 70
                    THEN 4 -- ADVERTENCIA
                WHEN (ISNULL(SUM(GA.nMonto), 0) * 100.0 / NULLIF(limPresuMens,0)) <= 30
                    THEN 3 -- BAJO
                ELSE 1 -- EN CAMINO
            END AS idEstadoPresupuesto
            , idTipoPeriodo
            , limPresuMens
            , umbralAlerta
            , @idUsuario
        FROM @tmpPresupuesto tmp
        INNER JOIN MANTENEDOR.CATEGORIAS CA
            ON CA.idCategoria = tmp.idCategoria
        LEFT JOIN FINANZAS.GASTOS GA
            ON GA.idCategoria = tmp.idCategoria
                AND GA.cFechaGasto BETWEEN @fecIniMes AND @fecFinMes
                AND GA.lEsActivo = 1
        GROUP BY tmp.idCategoria
            , idTipoPeriodo
            , limPresuMens
            , umbralAlerta;
        
        IF @nTrancount = 0
        BEGIN
            COMMIT TRANSACTION;
        END;

        SELECT cMsje = 'Se ha realizado la operacion correctamente.';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        
        SELECT cMsje = ERROR_MESSAGE();
    END CATCH;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_InsertarIngreso_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_InsertarIngreso_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_InsertarIngreso_SP];
GO

CREATE   PROCEDURE CG_InsertarIngreso_SP
    @nMonto DECIMAL(18,2),
    @cDescripcion NVARCHAR(255),
    @dFecha DATE,
    @cCategoria NVARCHAR(100),
    @cCuentaDestino NVARCHAR(100),
    @cTipoFlujo NVARCHAR(20),
    @cEstado NVARCHAR(20),
    @lEsRecurrente BIT,
    @idUsuario INT
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @idCat INT = (SELECT TOP 1 idCategoria FROM FINANZAS.CATEGORIAS_INGRESOS WHERE cNombre = @cCategoria);
    IF @idCat IS NULL
        SET @idCat = (SELECT TOP 1 idCategoria FROM FINANZAS.CATEGORIAS_INGRESOS WHERE @cCategoria LIKE '%' + cNombre + '%' OR cNombre LIKE '%' + @cCategoria + '%');
    IF @idCat IS NULL
        SET @idCat = 1;
    DECLARE @idCta INT = (SELECT TOP 1 idCuenta FROM FINANZAS.CUENTAS_DESTINO WHERE cNombre = @cCuentaDestino);
    IF @idCta IS NULL
        SET @idCta = (SELECT TOP 1 idCuenta FROM FINANZAS.CUENTAS_DESTINO WHERE @cCuentaDestino LIKE '%' + cNombre + '%' OR cNombre LIKE '%' + @cCuentaDestino + '%');
    IF @idCta IS NULL
        SET @idCta = 1;
    INSERT INTO FINANZAS.INGRESOS (
        nMonto, cDescripcion, dFecha, idCategoria, cCategoria, idCuentaDestino, cCuentaDestino,
        cTipoFlujo, cEstado, lEsRecurrente, idUsuario, lEsActivo, idUsuReg, dFecReg
    ) VALUES (
        @nMonto, @cDescripcion, @dFecha, @idCat, @cCategoria, @idCta, @cCuentaDestino,
        @cTipoFlujo, @cEstado, @lEsRecurrente, @idUsuario, 1, @idUsuario, GETDATE()
    );
    SELECT SCOPE_IDENTITY() AS idIngreso, 'Ingreso registrado correctamente.' AS mensaje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaAhorrosActivos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaAhorrosActivos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaAhorrosActivos_SP];
GO

CREATE PROCEDURE CG_ListaAhorrosActivos_SP @idUsuario INT AS
BEGIN
    SELECT 
        a.idAhorro,
        a.idMeta,
        m.cDescripcion AS nombreMeta,
        a.nMonto AS monto,
        a.dFecha AS fechaAhorro,
        FORMAT(a.dFecha, 'dd/MM/yyyy') AS fechaAhorroFormat,
        a.cDescripcion AS descripcion,
        u.nombre AS nombreUsuario
    FROM FINANZAS.AHORROS a
    INNER JOIN FINANZAS.METAS_AHORRO m ON a.idMeta = m.idMeta
    INNER JOIN usuarios u ON a.idUsuReg = u.id
    WHERE a.lEsActivo = 1 AND a.idUsuReg = @idUsuario
    ORDER BY a.dFecha DESC;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboCategorias_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboCategorias_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboCategorias_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Lista combo categorias
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
*	EXEC CG_ListaCboCategorias_SP
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaCboCategorias_SP AS
BEGIN
    SELECT 0 value, '-- Seleccione --' label
    UNION ALL
    SELECT idCategoria value, cDescripcion label
    FROM MANTENEDOR.CATEGORIAS
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboDeudasActivas_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboDeudasActivas_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboDeudasActivas_SP];
GO

CREATE PROCEDURE CG_ListaCboDeudasActivas_SP
    @idUsuario INT
AS
BEGIN
    SELECT
        idDeuda AS value
        , cAcreedor + ' (S/. ' + CAST(CAST(nMontoTotal AS DECIMAL(10,2)) AS VARCHAR) + ')' AS label
    FROM FINANZAS.DEUDAS
    WHERE lEsActivo = 1
      AND idUsuario = @idUsuario
      AND nCuotasPagadas < nCuotasTotales;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboMetasAhorro_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboMetasAhorro_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboMetasAhorro_SP];
GO

CREATE PROCEDURE CG_ListaCboMetasAhorro_SP
    @idUsuario INT
AS
BEGIN
    SELECT
        idMeta AS value
        , cDescripcion AS label
    FROM FINANZAS.METAS_AHORRO
    WHERE lEsActivo = 1
      AND idUsuReg = @idUsuario;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboTipoGastos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboTipoGastos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboTipoGastos_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Lista combo tipo gastos
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
*	EXEC CG_ListaCboTipoGastos_SP '{"params": { "idUser": 1, "idUserContact": 1 }}', ''
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaCboTipoGastos_SP AS
BEGIN
    SELECT 0 value, '-- Seleccione --' label
    UNION ALL
    SELECT idTipoGasto value, cDescripcion label
    FROM MANTENEDOR.TIPO_GASTOS
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboTipoPeriodo_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboTipoPeriodo_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboTipoPeriodo_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Lista combo periodo
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Presupuesto
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
*	EXEC CG_ListaCboTipoPeriodo_SP
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaCboTipoPeriodo_SP AS
BEGIN
    SELECT 0 value, '-- Seleccione --' label
    UNION ALL
    SELECT idTipoPeriodo value, cDescripcion label
    FROM MANTENEDOR.TIPO_PERIODO
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaCboUsuarios_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaCboUsuarios_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaCboUsuarios_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Lista combo usuarios
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
*	EXEC CG_ListaCboTipoGastos_SP 
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaCboUsuarios_SP AS
BEGIN
    SELECT 0 value, '-- Seleccione --' label
    UNION ALL
    SELECT id value, nombre label
    FROM usuarios
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaDeudasPorUsuario_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaDeudasPorUsuario_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaDeudasPorUsuario_SP];
GO

CREATE PROCEDURE CG_ListaDeudasPorUsuario_SP
    @idUsuario INT
AS
BEGIN
    SELECT 
        d.idDeuda,
        d.cAcreedor AS acreedor,
        d.nMontoTotal AS montoTotal,
        d.nTasaInteres AS tasaInteres,
        d.nCuotasTotales AS cuotasTotales,
        d.nCuotasPagadas AS cuotasPagadas,
        d.dFechaInicio AS fechaInicio,
        FORMAT(d.dFechaInicio, 'dd/MM/yyyy') AS fechaInicioFormat,
        -- Calcular montos pagados y restantes basándose en cuotas o abonos reales
        ISNULL((SELECT SUM(nMontoAbono) FROM FINANZAS.ABONO_DEUDAS WHERE idDeuda = d.idDeuda AND lEsActivo = 1), 0) AS montoPagado,
        (d.nMontoTotal - ISNULL((SELECT SUM(nMontoAbono) FROM FINANZAS.ABONO_DEUDAS WHERE idDeuda = d.idDeuda AND lEsActivo = 1), 0)) AS montoRestante,
        -- Determinar el estado
        CASE 
            WHEN d.nCuotasPagadas >= d.nCuotasTotales THEN 'PAGADO'
            WHEN d.cAcreedor LIKE '%Visa%' THEN 'PRÓXIMO A VENCER'
            WHEN d.nCuotasPagadas % 10 = 0 THEN 'PENDIENTE'
            ELSE 'AL DÍA'
        END AS estado,
        d.idUsuario
    FROM FINANZAS.DEUDAS d
    WHERE d.lEsActivo = 1 AND d.idUsuario = @idUsuario
    ORDER BY d.dFechaInicio DESC;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaGastosActivos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaGastosActivos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaGastosActivos_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Lista combo categorias
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		10/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
* EXEC CG_ListaGastosActivos_SP
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaGastosActivos_SP 
    @fechaDesde DATE = NULL,
    @fechaHasta DATE = NULL,
    @idUsuario INT = 0
AS
BEGIN
    SELECT
        fgas.idGasto            AS idGasto
        , fgas.cDescripcion     AS descripGasto
        , cat.cDescripcion      AS descripCategoria
        , tg.cDescripcion       AS descripTipoGasto
        , fgas.nMonto           AS monto
        , usu.nombre            AS nombreUsuario
        , fgas.cFechaGasto      AS fechaGasto
        , FORMAT(fgas.cFechaGasto, 'MMM dd, yyyy', 'en-US') AS fechaGastoFormat
    FROM FINANZAS.GASTOS fgas
    INNER JOIN MANTENEDOR.CATEGORIAS cat
        ON fgas.idCategoria = cat.idCategoria
    INNER JOIN usuarios usu 
        ON usu.id = fgas.idUsuReg
    INNER JOIN MANTENEDOR.TIPO_GASTOS tg
        ON tg.idTipoGasto = fgas.idTipoGasto
    WHERE (@fechaDesde IS NULL OR fgas.cFechaGasto >= @fechaDesde)
    AND (@fechaHasta IS NULL OR fgas.cFechaGasto <= @fechaHasta)
    AND (@idUsuario = 0 OR fgas.idUsuReg = @idUsuario)
    AND fgas.lEsActivo = 1
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaHistorialAbonos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaHistorialAbonos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaHistorialAbonos_SP];
GO

CREATE PROCEDURE CG_ListaHistorialAbonos_SP
    @idUsuario INT
AS
BEGIN
    SELECT 
        a.dFechaPago AS fecha,
        FORMAT(a.dFechaPago, 'dd/MM/yyyy') AS fechaFormat,
        d.cAcreedor AS deuda,
        a.nMontoAbono AS montoPagado,
        -- Saldo restante estimado de esa deuda
        (d.nMontoTotal - ISNULL((
            SELECT SUM(sub.nMontoAbono) 
            FROM FINANZAS.ABONO_DEUDAS sub 
            WHERE sub.idDeuda = d.idDeuda 
              AND sub.dFechaPago <= a.dFechaPago 
              AND sub.lEsActivo = 1
         ), 0)) AS saldoRestante
    FROM FINANZAS.ABONO_DEUDAS a
    INNER JOIN FINANZAS.DEUDAS d
        ON a.idDeuda = d.idDeuda
    WHERE a.lEsActivo = 1
      AND d.idUsuario = @idUsuario
    ORDER BY a.dFechaPago DESC;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaIngresosActivos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaIngresosActivos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaIngresosActivos_SP];
GO

CREATE   PROCEDURE CG_ListaIngresosActivos_SP
    @idUsuario INT,
    @cTipoFlujo NVARCHAR(20) = NULL,
    @cEstado NVARCHAR(20) = NULL,
    @dFechaDesde DATE = NULL,
    @dFechaHasta DATE = NULL,
    @cCategoria NVARCHAR(100) = NULL,
    @cCuentaDestino NVARCHAR(100) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    SELECT 
        i.idIngreso,
        i.nMonto,
        i.cDescripcion,
        i.dFecha,
        CONVERT(VARCHAR(10), i.dFecha, 120) AS fechaFormat,
        i.idCategoria,
        ISNULL(i.cCategoria, 'Otros') AS cCategoria,
        i.idCuentaDestino,
        ISNULL(i.cCuentaDestino, 'General') AS cCuentaDestino,
        ISNULL(i.cTipoFlujo, 'Fijo') AS cTipoFlujo,
        ISNULL(i.cEstado, 'Confirmado') AS cEstado,
        ISNULL(i.lEsRecurrente, 0) AS lEsRecurrente,
        i.idUsuario,
        i.idUsuReg,
        i.dFecReg,
        i.idUsuMod,
        i.dFecMod
    FROM FINANZAS.INGRESOS i
    WHERE i.idUsuario = @idUsuario
      AND i.lEsActivo = 1
      AND (@cTipoFlujo IS NULL OR @cTipoFlujo = 'Todos' OR i.cTipoFlujo = @cTipoFlujo)
      AND (@cEstado IS NULL OR @cEstado = 'Todos' OR i.cEstado = @cEstado)
      AND (@dFechaDesde IS NULL OR i.dFecha >= @dFechaDesde)
      AND (@dFechaHasta IS NULL OR i.dFecha <= @dFechaHasta)
      AND (@cCategoria IS NULL OR @cCategoria = 'Todas' OR @cCategoria = 'Todas las Categorías' OR i.cCategoria = @cCategoria)
      AND (@cCuentaDestino IS NULL OR @cCuentaDestino = 'Todas' OR @cCuentaDestino = 'Todas las Cuentas' OR i.cCuentaDestino = @cCuentaDestino)
    ORDER BY i.dFecha DESC, i.idIngreso DESC;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaPresupuestosActivos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaPresupuestosActivos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaPresupuestosActivos_SP];
GO

CREATE PROCEDURE CG_ListaPresupuestosActivos_SP
    @idUsuario INT,
    @idUsuarioOwner INT,
    @idFiltradoUsuario INT = 0,
    @fechaInicio VARCHAR(10) = NULL,
    @fechaFin VARCHAR(10) = NULL,
    @idCategoria INT = 0,
    @idEstado INT = 0
AS
BEGIN
    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);
    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);

    DECLARE @UserIds TABLE (idUsuario INT);
    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);
        INSERT INTO @UserIds (idUsuario)
        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;
    END
    ELSE
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);
    END;

    ;WITH CTE AS (
        SELECT
            PR.idPresupuesto
            , CA.cDescripcion nombreCategoria
            , PR.nLimPresuMens montoAsignado
            , ISNULL((
                SELECT SUM(GA.nMonto)
                FROM FINANZAS.GASTOS GA
                WHERE GA.idCategoria = PR.idCategoria
                  AND GA.lEsActivo = 1
                  AND GA.idUsuReg IN (SELECT idUsuario FROM @UserIds)
                  AND GA.cFechaGasto BETWEEN @fechaInicio AND @fechaFin
              ), 0) AS montoGastado
            , CASE
                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) > 100
                      THEN 2 -- EXCEDIDO
                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) >= 70
                      THEN 4 -- ADVERTENCIA
                  WHEN (ISNULL((SELECT SUM(g.nMonto) FROM FINANZAS.GASTOS g WHERE g.idCategoria = PR.idCategoria AND g.lEsActivo = 1 AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin), 0) * 100.0 / NULLIF(PR.nLimPresuMens,0)) <= 30
                      THEN 3 -- BAJO
                  ELSE 1 -- EN CAMINO
              END AS idEstadoCalculado
            , CONCAT(
                ISNULL(CAST(ROUND((ISNULL((
                    SELECT SUM(GA.nMonto)
                    FROM FINANZAS.GASTOS GA
                    WHERE GA.idCategoria = PR.idCategoria
                      AND GA.lEsActivo = 1
                      AND GA.idUsuReg IN (SELECT idUsuario FROM @UserIds)
                      AND GA.cFechaGasto BETWEEN @fechaInicio AND @fechaFin
                  ), 0) * 100.0) / NULLIF(PR.nLimPresuMens,0), 2)
                        AS DECIMAL(5,1)), 0)
                , '%') AS progresoPresupuesto
        FROM FINANZAS.PRESUPUESTO PR
        INNER JOIN MANTENEDOR.CATEGORIAS CA
            ON CA.idCategoria = PR.idCategoria
        WHERE PR.lEsActivo = 1
          AND PR.idUsuReg = @idUsuarioOwner
          AND (@idCategoria = 0 OR PR.idCategoria = @idCategoria)
    )
    SELECT 
        C.idPresupuesto,
        C.nombreCategoria,
        C.montoAsignado,
        C.montoGastado,
        EP.cDescripcion AS estadoPresupuesto,
        C.progresoPresupuesto
    FROM CTE C
    INNER JOIN MANTENEDOR.ESTADO_PRESUPUESTO EP
        ON EP.idEstadoPresupuesto = C.idEstadoCalculado
    WHERE (@idEstado = 0 OR C.idEstadoCalculado = @idEstado);
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ListaUsuariosCompartidos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ListaUsuariosCompartidos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ListaUsuariosCompartidos_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		Listar usuarios colaboradores/compartidos de un propietario
* 
* Escrito por:		Johann Meza
* 
* Fecha creación:	12/06/2026
* 
* Sistema / Modulo:	Usuarios / Colaboración
* 
* Modificaciones: 
* 
* Sintaxis de ejemplo:		 
* EXEC CG_ListaUsuariosCompartidos_SP 1
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ListaUsuariosCompartidos_SP
    @idUsuarioOwner INT
AS
BEGIN
    SELECT 
        uc.idCompartido,
        uc.idUsuarioOwner,
        uc.idUsuarioInvitado,
        uc.cPermiso AS permiso,
        u.nombre AS nombreInvitado,
        u.email AS emailInvitado,
        CASE 
            WHEN u.nombre = '' OR u.password = 'PENDIENTE' THEN 'Pendiente'
            ELSE 'Activo'
        END AS estado
    FROM FINANZAS.USUARIOS_COMPARTIDOS uc
    INNER JOIN dbo.usuarios u ON uc.idUsuarioInvitado = u.id
    WHERE uc.idUsuarioOwner = @idUsuarioOwner AND uc.lEsActivo = 1;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerAhorroPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerAhorroPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerAhorroPorId_SP];
GO

CREATE PROCEDURE CG_ObtenerAhorroPorId_SP @idAhorro INT AS
BEGIN
    SELECT 
        idAhorro,
        idMeta,
        nMonto AS monto,
        dFecha AS fechaAhorro,
        cDescripcion AS descripcion
    FROM FINANZAS.AHORROS
    WHERE idAhorro = @idAhorro;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDashboardGraficos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDashboardGraficos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDashboardGraficos_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		Obtener datos de presupuestos vs gastos por categoría para gráficos del Dashboard con soporte a filtros
* 
* Escrito por:		Johann Meza
* 
* Fecha creación:	12/06/2026
* 
* Sistema / Modulo:	Dashboard
* 
* Modificaciones: 
* 
* Sintaxis de ejemplo:		 
* EXEC CG_ObtenerDashboardGraficos_SP 1, 0, '2026-06-01', '2026-06-30', 0, 0, 0, 9999999
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ObtenerDashboardGraficos_SP
    @idUsuarioOwner INT,
    @idFiltradoUsuario INT = 0,
    @fechaInicio VARCHAR(10) = NULL,
    @fechaFin VARCHAR(10) = NULL,
    @idCategoria INT = 0,
    @idTipoGasto INT = 0,
    @montoMin DECIMAL(15,2) = 0,
    @montoMax DECIMAL(15,2) = 99999999
AS
BEGIN
    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);
    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);
    IF @montoMin IS NULL SET @montoMin = 0;
    IF @montoMax IS NULL SET @montoMax = 99999999;
    
    DECLARE @UserIds TABLE (idUsuario INT);
    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);
        INSERT INTO @UserIds (idUsuario)
        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;
    END
    ELSE
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);
    END;
    
    SELECT 
        c.cDescripcion AS categoria,
        c.cDescripcionLarga AS descripcion,
        p.nLimPresuMens AS limite,
        ISNULL((
            SELECT SUM(g.nMonto) 
            FROM FINANZAS.GASTOS g 
            WHERE g.idCategoria = p.idCategoria 
              AND g.lEsActivo = 1 
              AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds) 
              AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin
              AND (@idCategoria = 0 OR g.idCategoria = @idCategoria)
              AND (@idTipoGasto = 0 OR g.idTipoGasto = @idTipoGasto)
              AND g.nMonto BETWEEN @montoMin AND @montoMax
        ), 0) AS gasto
    FROM FINANZAS.PRESUPUESTO p
    INNER JOIN MANTENEDOR.CATEGORIAS c ON p.idCategoria = c.idCategoria
    WHERE p.lEsActivo = 1 
      AND p.idUsuReg = @idUsuarioOwner
      AND (@idCategoria = 0 OR p.idCategoria = @idCategoria);
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDashboardPagos_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDashboardPagos_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDashboardPagos_SP];
GO

CREATE PROCEDURE CG_ObtenerDashboardPagos_SP
    @idUsuarioOwner INT
AS
BEGIN
    SELECT 
        cAcreedor AS concepto,
        CAST(nMontoTotal / nCuotasTotales AS DECIMAL(15,2)) AS monto,
        'Vence el ' + CONVERT(VARCHAR(10), DATEADD(month, nCuotasPagadas, dFechaInicio), 103) AS fechaVencimiento
    FROM FINANZAS.DEUDAS
    WHERE idUsuario = @idUsuarioOwner 
      AND lEsActivo = 1 
      AND nCuotasPagadas < nCuotasTotales;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDashboardResumen_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDashboardResumen_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDashboardResumen_SP];
GO

CREATE PROCEDURE CG_ObtenerDashboardResumen_SP
    @idUsuarioOwner INT,
    @idFiltradoUsuario INT = 0,
    @fechaInicio VARCHAR(10) = NULL,
    @fechaFin VARCHAR(10) = NULL,
    @idCategoria INT = 0,
    @idTipoGasto INT = 0,
    @montoMin DECIMAL(15,2) = 0,
    @montoMax DECIMAL(15,2) = 99999999
AS
BEGIN
    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);
    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);
    IF @montoMin IS NULL SET @montoMin = 0;
    IF @montoMax IS NULL SET @montoMax = 99999999;
    
    DECLARE @UserIds TABLE (idUsuario INT);
    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);
        INSERT INTO @UserIds (idUsuario)
        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;
    END
    ELSE
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);
    END;
    
    DECLARE @dInicio DATE = CONVERT(DATE, @fechaInicio);
    DECLARE @dFin DATE = CONVERT(DATE, @fechaFin);
    
    DECLARE @fechaInicioPrev DATE = DATEADD(month, -1, @dInicio);
    DECLARE @fechaFinPrev DATE = DATEADD(month, -1, @dFin);
    
    DECLARE @ingresosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.INGRESOS WHERE idUsuario IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicio AND @fechaFin AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @gastosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.GASTOS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND cFechaGasto BETWEEN @fechaInicio AND @fechaFin AND (@idCategoria = 0 OR idCategoria = @idCategoria) AND (@idTipoGasto = 0 OR idTipoGasto = @idTipoGasto) AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @ahorrosMes DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.AHORROS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicio AND @fechaFin AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @balanceMes DECIMAL(15,2) = @ingresosMes - @gastosMes - @ahorrosMes;
    
    DECLARE @ingresosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.INGRESOS WHERE idUsuario IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicioPrev AND @fechaFinPrev AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @gastosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.GASTOS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND cFechaGasto BETWEEN @fechaInicioPrev AND @fechaFinPrev AND (@idCategoria = 0 OR idCategoria = @idCategoria) AND (@idTipoGasto = 0 OR idTipoGasto = @idTipoGasto) AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @ahorrosMesPrev DECIMAL(15,2) = ISNULL((SELECT SUM(nMonto) FROM FINANZAS.AHORROS WHERE idUsuReg IN (SELECT idUsuario FROM @UserIds) AND lEsActivo = 1 AND dFecha BETWEEN @fechaInicioPrev AND @fechaFinPrev AND nMonto BETWEEN @montoMin AND @montoMax), 0);
    DECLARE @balanceMesPrev DECIMAL(15,2) = @ingresosMesPrev - @gastosMesPrev - @ahorrosMesPrev;
    
    DECLARE @ingresosCambio VARCHAR(50);
    IF @ingresosMesPrev = 0
    BEGIN
        IF @ingresosMes = 0 SET @ingresosCambio = '0.0% vs mes anterior';
        ELSE SET @ingresosCambio = '+100.0% vs mes anterior';
    END
    ELSE
    BEGIN
        DECLARE @pctIngresos DECIMAL(10,2) = ((@ingresosMes - @ingresosMesPrev) / @ingresosMesPrev) * 100;
        IF @pctIngresos >= 0 SET @ingresosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctIngresos AS DECIMAL(10,1))) + '% vs mes anterior';
        ELSE SET @ingresosCambio = CONVERT(VARCHAR(20), CAST(@pctIngresos AS DECIMAL(10,1))) + '% vs mes anterior';
    END;
    
    DECLARE @gastosCambio VARCHAR(50);
    IF @gastosMesPrev = 0
    BEGIN
        IF @gastosMes = 0 SET @gastosCambio = '0.0% vs mes anterior';
        ELSE SET @gastosCambio = '+100.0% vs mes anterior';
    END
    ELSE
    BEGIN
        DECLARE @pctGastos DECIMAL(10,2) = ((@gastosMes - @gastosMesPrev) / @gastosMesPrev) * 100;
        IF @pctGastos >= 0 SET @gastosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctGastos AS DECIMAL(10,1))) + '% vs mes anterior';
        ELSE SET @gastosCambio = CONVERT(VARCHAR(20), CAST(@pctGastos AS DECIMAL(10,1))) + '% vs mes anterior';
    END;
    
    DECLARE @ahorrosCambio VARCHAR(50);
    IF @ahorrosMesPrev = 0
    BEGIN
        IF @ahorrosMes = 0 SET @ahorrosCambio = '0.0% vs mes anterior';
        ELSE SET @ahorrosCambio = '+100.0% vs mes anterior';
    END
    ELSE
    BEGIN
        DECLARE @pctAhorros DECIMAL(10,2) = ((@ahorrosMes - @ahorrosMesPrev) / @ahorrosMesPrev) * 100;
        IF @pctAhorros >= 0 SET @ahorrosCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctAhorros AS DECIMAL(10,1))) + '% vs mes anterior';
        ELSE SET @ahorrosCambio = CONVERT(VARCHAR(20), CAST(@pctAhorros AS DECIMAL(10,1))) + '% vs mes anterior';
    END;
    
    DECLARE @balanceCambio VARCHAR(50);
    IF @balanceMesPrev = 0
    BEGIN
        IF @balanceMes = 0 SET @balanceCambio = '0.0% vs mes anterior';
        ELSE SET @balanceCambio = '+100.0% vs mes anterior';
    END
    ELSE
    BEGIN
        DECLARE @pctBalance DECIMAL(10,2) = ((@balanceMes - @balanceMesPrev) / @balanceMesPrev) * 100;
        IF @pctBalance >= 0 SET @balanceCambio = '+' + CONVERT(VARCHAR(20), CAST(@pctBalance AS DECIMAL(10,1))) + '% vs mes anterior';
        ELSE SET @balanceCambio = CONVERT(VARCHAR(20), CAST(@pctBalance AS DECIMAL(10,1))) + '% vs mes anterior';
    END;

    DECLARE @bannerTitle NVARCHAR(100);
    DECLARE @bannerDesc NVARCHAR(255);

    IF @ingresosMes = 0 AND @gastosMes = 0 AND @ahorrosMes = 0
    BEGIN
        SET @bannerTitle = N'Bienvenido';
        SET @bannerDesc = N'Registra tus ingresos y presupuestos para ver análisis de ahorro en tiempo real.';
    END
    ELSE IF @ahorrosMes > 0
    BEGIN
        SET @bannerTitle = N'¡Vas por buen camino!';
        SET @bannerDesc = N'Has acumulado S/ ' + CONVERT(VARCHAR(20), CAST(@ahorrosMes AS DECIMAL(15,2))) + N' en ahorros en este período.';
    END
    ELSE IF @gastosMes > @ingresosMes AND @ingresosMes > 0
    BEGIN
        SET @bannerTitle = N'Atención a tus gastos';
        SET @bannerDesc = N'Tus gastos han superado a tus ingresos en este período.';
    END
    ELSE
    BEGIN
        SET @bannerTitle = N'Control Financiero';
        SET @bannerDesc = N'Mantén tus presupuestos al día para maximizar tu ahorro.';
    END;
    
    SELECT 
        @ingresosMes AS ingresosMes,
        @ingresosCambio AS ingresosCambio,
        @gastosMes AS gastosMes,
        @gastosCambio AS gastosCambio,
        @ahorrosMes AS ahorrosMes,
        @ahorrosCambio AS ahorrosCambio,
        @balanceMes AS balanceMes,
        @balanceCambio AS balanceCambio,
        0.00 AS upcomingPayInternetAmount,
        '' AS upcomingPayInternetDate,
        0.00 AS upcomingPayHealthAmount,
        '' AS upcomingPayHealthDate,
        @bannerTitle AS bannerTitle,
        @bannerDesc AS bannerDesc;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDatosUsuarioPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDatosUsuarioPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDatosUsuarioPorId_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Obtener datos del usuario
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		15/05/2026
* 
* Sistema / Modulo:		LoginUseuario
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
* EXEC CG_ObtenerDatosUsuarioPorId_SP 1
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ObtenerDatosUsuarioPorId_SP
(
    @idUsuario INT
)
AS
BEGIN
    SELECT
        nombre
        , email
        , rol
        , id
    FROM usuarios
    WHERE id = @idUsuario
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDeudaPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDeudaPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDeudaPorId_SP];
GO

CREATE PROCEDURE CG_ObtenerDeudaPorId_SP
    @idDeuda INT
AS
BEGIN
    SELECT 
        idDeuda,
        cAcreedor AS acreedor,
        nMontoTotal AS montoTotal,
        nTasaInteres AS tasaInteres,
        nCuotasTotales AS cuotasTotales,
        nCuotasPagadas AS cuotasPagadas,
        dFechaInicio AS fechaInicio,
        idUsuario
    FROM FINANZAS.DEUDAS
    WHERE idDeuda = @idDeuda;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerDeudaResumen_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerDeudaResumen_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerDeudaResumen_SP];
GO

CREATE PROCEDURE CG_ObtenerDeudaResumen_SP
    @idUsuario INT
AS
BEGIN
    DECLARE @totalDeuda DECIMAL(15,2) = 0;
    DECLARE @totalPagadoEsteMes DECIMAL(15,2) = 0;
    
    -- Suma de saldos pendientes de todas las deudas activas del usuario
    SELECT @totalDeuda = ISNULL(SUM(d.nMontoTotal), 0) - 
        ISNULL((
            SELECT SUM(a.nMontoAbono)
            FROM FINANZAS.ABONO_DEUDAS a
            INNER JOIN FINANZAS.DEUDAS d2
                ON a.idDeuda = d2.idDeuda
            WHERE a.lEsActivo = 1
              AND d2.lEsActivo = 1
              AND d2.idUsuario = @idUsuario
        ), 0)
    FROM FINANZAS.DEUDAS d
    WHERE d.lEsActivo = 1
      AND d.idUsuario = @idUsuario;
    
    -- Suma de pagos realizados este mes
    SELECT @totalPagadoEsteMes = ISNULL(SUM(a.nMontoAbono), 0)
    FROM FINANZAS.ABONO_DEUDAS a
    INNER JOIN FINANZAS.DEUDAS d
        ON a.idDeuda = d.idDeuda
    WHERE a.lEsActivo = 1 
      AND d.idUsuario = @idUsuario
      AND MONTH(a.dFechaPago) = MONTH(GETDATE())
      AND YEAR(a.dFechaPago) = YEAR(GETDATE());
      
    -- Obtener la fecha del próximo vencimiento de la cuota activa más cercana
    DECLARE @proximoVencimiento DATE = (
        SELECT MIN(DATEADD(month, nCuotasPagadas, dFechaInicio)) 
        FROM FINANZAS.DEUDAS 
        WHERE idUsuario = @idUsuario 
          AND lEsActivo = 1 
          AND nCuotasPagadas < nCuotasTotales
    );
      
    SELECT 
        ISNULL(@totalDeuda, 0) AS totalDeuda,
        COALESCE(CONVERT(VARCHAR(10), @proximoVencimiento, 103), 'Sin vencimientos') AS proximoVencimiento,
        ISNULL(@totalPagadoEsteMes, 0) AS totalPagadoEsteMes;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerGastoPorId_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerGastoPorId_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerGastoPorId_SP];
GO

/******************************************************************************************************************************************************* 
* Objetivo:		        Obtiene gasto por id
* 
* Escrito por:			Johann Meza
* 
* Fecha creación:		14/05/2026
* 
* Sistema / Modulo:		Enviar solicitud
* 
* Modificaciones: 
*	Fecha		Responsable	 Descripcion del cambio 
*
* Sintaxis de ejemplo:		 
* EXEC CG_ListaGastosActivos_SP
*******************************************************************************************************************************************************/ 
CREATE PROCEDURE CG_ObtenerGastoPorId_SP
(
    @idGasto INT
)
AS
BEGIN
    SELECT
        fgas.idGasto                AS idGasto
        , fgas.idCategoria          AS idCategoria
        , fgas.idTipoGasto          AS idTipoGasto
        , fgas.cDescripcion         AS descripcion
        , fgas.nMonto               AS monto
        , fgas.cFechaGasto          AS fechaGasto
    FROM FINANZAS.GASTOS fgas
    WHERE fgas.idGasto = @idGasto
END
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerMetasDetalle_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerMetasDetalle_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerMetasDetalle_SP];
GO

CREATE PROCEDURE CG_ObtenerMetasDetalle_SP
    @idUsuario INT
AS
BEGIN
    SELECT
        idMeta
        , cDescripcion
        , nMontoObjetivo
        , nMontoActual
        , dFechaEstimada
        , cIconPath
        , nAsignacionMensual
    FROM FINANZAS.METAS_AHORRO
    WHERE lEsActivo = 1
      AND idUsuReg = @idUsuario;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerMetasResumen_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerMetasResumen_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerMetasResumen_SP];
GO

CREATE PROCEDURE CG_ObtenerMetasResumen_SP
    @idUsuario INT
AS
BEGIN
    DECLARE @metaMensualTarget DECIMAL(15,2);
    
    SELECT @metaMensualTarget = ISNULL(PR.nLimPresuMens, 0.00)
    FROM FINANZAS.PRESUPUESTO PR
    INNER JOIN MANTENEDOR.CATEGORIAS CA ON CA.idCategoria = PR.idCategoria
    WHERE PR.idUsuReg = @idUsuario
      AND UPPER(LTRIM(RTRIM(CA.cDescripcion))) = 'AHORRO'
      AND PR.lEsActivo = 1;
      
    IF @metaMensualTarget IS NULL SET @metaMensualTarget = 0.00;

    SELECT
        ISNULL(SUM(nMontoActual), 0) AS totalAhorrado
        , (
            SELECT ISNULL(SUM(nMonto), 0)
            FROM FINANZAS.AHORROS
            WHERE idUsuReg = @idUsuario
              AND MONTH(dFecha) = MONTH(GETDATE())
              AND YEAR(dFecha) = YEAR(GETDATE())
              AND lEsActivo = 1
          ) AS ahorradoEsteMes
        , @metaMensualTarget AS metaMensualTarget
        , (
            SELECT COUNT(*)
            FROM FINANZAS.METAS_AHORRO
            WHERE lEsActivo = 1
              AND idUsuReg = @idUsuario
          ) AS totalMetasActivas
    FROM FINANZAS.METAS_AHORRO
    WHERE lEsActivo = 1
      AND idUsuReg = @idUsuario;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerPresupuestoActividades_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerPresupuestoActividades_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerPresupuestoActividades_SP];
GO

CREATE PROCEDURE CG_ObtenerPresupuestoActividades_SP
    @idUsuarioOwner INT,
    @idFiltradoUsuario INT = 0,
    @fechaInicio VARCHAR(10) = NULL,
    @fechaFin VARCHAR(10) = NULL
AS
BEGIN
    IF @fechaInicio IS NULL SET @fechaInicio = CONVERT(VARCHAR(10), DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1), 120);
    IF @fechaFin IS NULL SET @fechaFin = CONVERT(VARCHAR(10), EOMONTH(GETDATE()), 120);

    DECLARE @UserIds TABLE (idUsuario INT);
    IF @idFiltradoUsuario IS NULL OR @idFiltradoUsuario = 0
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idUsuarioOwner);
        INSERT INTO @UserIds (idUsuario)
        SELECT idUsuarioInvitado FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1;
    END
    ELSE
    BEGIN
        INSERT INTO @UserIds (idUsuario) VALUES (@idFiltradoUsuario);
    END;

    SELECT TOP 3
        g.idGasto,
        g.cGasto,
        c.cDescripcion AS nombreCategoria,
        g.nMonto AS montoGasto,
        g.cFechaGasto,
        p.nLimPresuMens AS limitePresupuesto,
        p.nUmbralAlerta AS umbralAlerta,
        ISNULL((
            SELECT SUM(g2.nMonto)
            FROM FINANZAS.GASTOS g2
            WHERE g2.idCategoria = g.idCategoria
              AND g2.lEsActivo = 1
              AND g2.idUsuReg IN (SELECT idUsuario FROM @UserIds)
              AND g2.cFechaGasto BETWEEN @fechaInicio AND @fechaFin
        ), 0) AS totalGastadoMes
    FROM FINANZAS.GASTOS g
    INNER JOIN FINANZAS.PRESUPUESTO p ON g.idCategoria = p.idCategoria AND p.lEsActivo = 1
    INNER JOIN MANTENEDOR.CATEGORIAS c ON g.idCategoria = c.idCategoria
    WHERE g.lEsActivo = 1
      AND p.idUsuReg = @idUsuarioOwner
      AND g.idUsuReg = @idUsuarioOwner
      AND g.idUsuReg IN (SELECT idUsuario FROM @UserIds)
      AND g.cFechaGasto BETWEEN @fechaInicio AND @fechaFin
    ORDER BY g.cFechaGasto DESC, g.idGasto DESC;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ObtenerResumenUsuarios_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ObtenerResumenUsuarios_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ObtenerResumenUsuarios_SP];
GO

CREATE PROCEDURE CG_ObtenerResumenUsuarios_SP
    @idUsuarioOwner INT
AS
BEGIN
    -- Asientos Usados: propietario (1) + colaboradores activos (lEsActivo = 1)
    DECLARE @asientosUsados INT;
    SET @asientosUsados = 1 + (SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1);
    
    -- Ãšltima SincronizaciÃ³n: Ãºltima acciÃ³n registrada en logs
    DECLARE @ultimaSincronizacion VARCHAR(100) = 'Sin actividad';
    DECLARE @dFechaUltima DATETIME;
    DECLARE @idUsuUltimo INT;
    DECLARE @nombreUltimo VARCHAR(100);

    SELECT TOP 1 @dFechaUltima = dFecha, @idUsuUltimo = idUsuarioOwner 
    FROM FINANZAS.LOGS_AUDITORIA 
    WHERE idUsuarioOwner = @idUsuarioOwner 
    ORDER BY dFecha DESC;

    IF @dFechaUltima IS NOT NULL
    BEGIN
        SELECT @nombreUltimo = nombre FROM dbo.usuarios WHERE id = @idUsuUltimo;
        IF @nombreUltimo IS NULL SET @nombreUltimo = 'Sistema';

        -- Calcular diferencia en minutos
        DECLARE @diffMin INT = DATEDIFF(minute, @dFechaUltima, GETDATE());
        IF @diffMin < 1
            SET @ultimaSincronizacion = 'Hace instantes por ' + @nombreUltimo;
        ELSE IF @diffMin < 60
            SET @ultimaSincronizacion = 'Hace ' + CAST(@diffMin AS VARCHAR) + ' min por ' + @nombreUltimo;
        ELSE IF @diffMin < 1440
            SET @ultimaSincronizacion = 'Hace ' + CAST(@diffMin / 60 AS VARCHAR) + ' horas por ' + @nombreUltimo;
        ELSE
            SET @ultimaSincronizacion = 'El ' + FORMAT(@dFechaUltima, 'dd/MM/yyyy HH:mm') + ' por ' + @nombreUltimo;
    END;

    -- MFA Activado: porcentaje de usuarios activos del equipo con MFA habilitado
    -- Equipo = Propietario (1) + Colaboradores activos
    DECLARE @totalTeam INT = 0;
    DECLARE @mfaEnabledCount INT = 0;
    DECLARE @mfaStatus VARCHAR(50) = 'Activo (0%)';

    -- Propietario
    SET @totalTeam = 1;
    IF EXISTS (SELECT 1 FROM FINANZAS.CONFIGURACION_SEGURIDAD WHERE idUsuario = @idUsuarioOwner AND lMfaHabilitado = 1)
        SET @mfaEnabledCount = 1;

    -- Colaboradores activos
    DECLARE @colabTotal INT = (SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1);
    DECLARE @colabMfa INT = (
        SELECT COUNT(*) 
        FROM FINANZAS.USUARIOS_COMPARTIDOS uc
        INNER JOIN FINANZAS.CONFIGURACION_SEGURIDAD cs ON uc.idUsuarioInvitado = cs.idUsuario
        WHERE uc.idUsuarioOwner = @idUsuarioOwner AND uc.lEsActivo = 1 AND cs.lMfaHabilitado = 1
    );

    SET @totalTeam = @totalTeam + ISNULL(@colabTotal, 0);
    SET @mfaEnabledCount = @mfaEnabledCount + ISNULL(@colabMfa, 0);

    IF @totalTeam > 0
    BEGIN
        DECLARE @pct INT = (@mfaEnabledCount * 100) / @totalTeam;
        SET @mfaStatus = 'Activo (' + CAST(@pct AS VARCHAR) + '%)';
    END;

    -- Logs de AuditorÃ­a: cantidad total de registros en logs de este owner
    DECLARE @logsCount VARCHAR(50);
    DECLARE @cnt INT = (SELECT COUNT(*) FROM FINANZAS.LOGS_AUDITORIA WHERE idUsuarioOwner = @idUsuarioOwner);
    SET @logsCount = CAST(@cnt AS VARCHAR) + ' registros';

    SELECT 
        @asientosUsados AS asientosUsados,
        10 AS asientosTotales,
        @ultimaSincronizacion AS ultimaSincronizacion,
        @mfaStatus AS mfaActivoStatus,
        @logsCount AS logsAuditoriaCount;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_RegistrarUsuario_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_RegistrarUsuario_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_RegistrarUsuario_SP];
GO

CREATE PROCEDURE CG_RegistrarUsuario_SP
    @nombre VARCHAR(100),
    @email VARCHAR(150),
    @password VARCHAR(255),
    @apellidos VARCHAR(100) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    IF @nombre IS NULL OR @nombre = ''
    BEGIN
        THROW 50004, 'El nombre no puede estar vacío.', 1;
        RETURN;
    END;
    
    IF @email IS NULL OR @email = ''
    BEGIN
        THROW 50005, 'El correo electrónico no puede estar vacío.', 1;
        RETURN;
    END;
    
    IF @password IS NULL OR @password = ''
    BEGIN
        THROW 50006, 'La contraseña no puede estar vacía.', 1;
        RETURN;
    END;
    
    DECLARE @nombreCompleto VARCHAR(100) = CASE 
        WHEN @apellidos IS NOT NULL AND RTRIM(LTRIM(@apellidos)) <> '' 
        THEN RTRIM(LTRIM(@nombre)) + ' ' + RTRIM(LTRIM(@apellidos))
        ELSE RTRIM(LTRIM(@nombre))
    END;
    
    DECLARE @passwordEncriptada VARCHAR(255);
    EXEC CG_EncriptarTexto_SP @password, @passwordEncriptada OUTPUT;
    
    DECLARE @idUsuario INT;
    
    IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email)
    BEGIN
        IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email AND password = 'PENDIENTE')
        BEGIN
            UPDATE dbo.usuarios
            SET nombre = @nombreCompleto, password = @passwordEncriptada, fecha_creacion = GETDATE()
            WHERE email = @email;
            
            SELECT @idUsuario = id FROM dbo.usuarios WHERE email = @email;
            
            IF NOT EXISTS (SELECT 1 FROM FINANZAS.CONFIGURACION_SEGURIDAD WHERE idUsuario = @idUsuario)
            BEGIN
                INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado)
                VALUES (@idUsuario, 0);
            END;
            
            INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
            VALUES (@idUsuario, 'REGISTRO', 'Usuario completó su registro desde invitación.', GETDATE());
        END
        ELSE
        BEGIN
            THROW 50007, 'El correo electrónico ya se encuentra registrado.', 1;
            RETURN;
        END;
    END
    ELSE
    BEGIN
        INSERT INTO dbo.usuarios (nombre, email, password, rol, fecha_creacion)
        VALUES (@nombreCompleto, @email, @passwordEncriptada, 'USER', GETDATE());
        
        SET @idUsuario = SCOPE_IDENTITY();
        
        INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado)
        VALUES (@idUsuario, 0);
        
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idUsuario, 'REGISTRO', 'Nuevo usuario registrado en la aplicación.', GETDATE());
    END;
    
    SELECT 'Usuario registrado exitosamente.' AS cMsje;
END;
GO

-- ---------------------------------------------------------------------
-- Procedimiento: [dbo].[CG_ValidarLogin_SP]
-- ---------------------------------------------------------------------
IF OBJECT_ID('[dbo].[CG_ValidarLogin_SP]', 'P') IS NOT NULL
    DROP PROCEDURE [dbo].[CG_ValidarLogin_SP];
GO

CREATE PROCEDURE CG_ValidarLogin_SP
    @email VARCHAR(150),
    @passwordIngresada VARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @id INT;
    DECLARE @nombre VARCHAR(100);
    DECLARE @emailDB VARCHAR(150);
    DECLARE @passwordEncriptada VARCHAR(255);
    DECLARE @rol VARCHAR(50);
    
    -- Buscar usuario por email
    SELECT @id = id, @nombre = nombre, @emailDB = email, @passwordEncriptada = password, @rol = rol
    FROM dbo.usuarios
    WHERE email = @email;
    
    IF @id IS NULL
    BEGIN
        THROW 50001, 'El correo electrónico no está registrado.', 1;
        RETURN;
    END;
    
    IF @passwordEncriptada = 'PENDIENTE'
    BEGIN
        THROW 50002, 'La cuenta aún no está activa. Por favor complete su registro.', 1;
        RETURN;
    END;
    
    -- Desencriptar
    DECLARE @passwordDesencriptada VARCHAR(255);
    EXEC CG_DesencriptarTexto_SP @passwordEncriptada, @passwordDesencriptada OUTPUT;
    
    IF @passwordDesencriptada <> @passwordIngresada
    BEGIN
        THROW 50003, 'La contraseña ingresada es incorrecta.', 1;
        RETURN;
    END;
    
    -- Registrar log de auditoría del inicio de sesión
    INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
    VALUES (@id, 'LOGIN', 'Inicio de sesión exitoso.', GETDATE());

    -- Retornar datos del usuario
    SELECT id, nombre, email, rol
    FROM dbo.usuarios
    WHERE id = @id;
END;
GO

-- =====================================================================
-- FIN DE SCRIPT DE RESTAURACIÓN (48 procedimientos creados)
-- =====================================================================
