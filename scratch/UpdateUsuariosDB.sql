-- ==========================================================
-- SCRIPT DE BASE DE DATOS: GESTIÓN DE USUARIOS Y AUDITORÍA
-- ==========================================================

-- 1. Crear tabla de Configuración de Seguridad para MFA si no existe
IF OBJECT_ID('FINANZAS.CONFIGURACION_SEGURIDAD', 'U') IS NULL
BEGIN
    CREATE TABLE FINANZAS.CONFIGURACION_SEGURIDAD (
        idUsuario INT PRIMARY KEY,
        lMfaHabilitado BIT NOT NULL DEFAULT 0,
        FOREIGN KEY (idUsuario) REFERENCES dbo.usuarios(id)
    );
    PRINT 'Tabla FINANZAS.CONFIGURACION_SEGURIDAD creada exitosamente.';
END;
GO

-- Registrar configuraciones por defecto (Jane Doe ID=1 tiene MFA activo, los demás no)
INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado)
SELECT id, CASE WHEN id = 1 OR id = 1002 THEN 1 ELSE 0 END
FROM dbo.usuarios u
WHERE NOT EXISTS (SELECT 1 FROM FINANZAS.CONFIGURACION_SEGURIDAD cs WHERE cs.idUsuario = u.id);
GO

-- 2. Crear tabla de Logs de Auditoría si no existe
IF OBJECT_ID('FINANZAS.LOGS_AUDITORIA', 'U') IS NULL
BEGIN
    CREATE TABLE FINANZAS.LOGS_AUDITORIA (
        idLog INT IDENTITY(1,1) PRIMARY KEY,
        idUsuarioOwner INT NOT NULL,
        cAccion VARCHAR(100) NOT NULL,
        cDetalle VARCHAR(255) NOT NULL,
        dFecha DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (idUsuarioOwner) REFERENCES dbo.usuarios(id)
    );
    PRINT 'Tabla FINANZAS.LOGS_AUDITORIA creada exitosamente.';
END;
GO

-- Cargar registros iniciales de auditoría para pruebas
IF (SELECT COUNT(*) FROM FINANZAS.LOGS_AUDITORIA) = 0
BEGIN
    INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha) VALUES
    (1, 'INVITAR', 'Invitación enviada a m.smith@finance.dev', DATEADD(minute, -120, GETDATE())),
    (1, 'INVITAR', 'Invitación enviada a lucas.w@partners.net', DATEADD(minute, -90, GETDATE())),
    (1, 'INVITAR', 'Invitación enviada a pending.invite@acme.com', DATEADD(minute, -60, GETDATE())),
    (1, 'INVITAR', 'Invitación enviada a colega@gmail.com', DATEADD(minute, -30, GETDATE()));
    PRINT 'Registros de prueba insertados en FINANZAS.LOGS_AUDITORIA.';
END;
GO

-- 3. Modificar stored procedure CG_ObtenerResumenUsuarios_SP
IF OBJECT_ID('CG_ObtenerResumenUsuarios_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_ObtenerResumenUsuarios_SP;
GO

CREATE PROCEDURE CG_ObtenerResumenUsuarios_SP
    @idUsuarioOwner INT
AS
BEGIN
    -- Asientos Usados: propietario (1) + colaboradores activos (lEsActivo = 1)
    DECLARE @asientosUsados INT;
    SET @asientosUsados = 1 + (SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1);
    
    -- Última Sincronización: última acción registrada en logs
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

    -- Logs de Auditoría: cantidad total de registros en logs de este owner
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

-- 4. Modificar stored procedure CG_GuardaInvitacionUsuario_SP para incluir logs
IF OBJECT_ID('CG_GuardaInvitacionUsuario_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_GuardaInvitacionUsuario_SP;
GO

CREATE PROCEDURE CG_GuardaInvitacionUsuario_SP
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
        SELECT 'Debe ingresar un correo electrónico válido.' AS cMsje;
        RETURN;
    END;
    
    -- Buscar si ya existe el usuario
    DECLARE @idInvitado INT = (SELECT id FROM dbo.usuarios WHERE email = @emailInvitado);
    
    IF @idInvitado IS NULL
    BEGIN
        -- Crear usuario placeholder
        INSERT INTO dbo.usuarios (nombre, email, password, rol) 
        VALUES ('', @emailInvitado, 'PENDIENTE', 'USER');
        SET @idInvitado = SCOPE_IDENTITY();
        
        -- Insertar config de seguridad por defecto para el nuevo usuario
        INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD(idUsuario, lMfaHabilitado)
        VALUES (@idInvitado, 0);
    END;
    
    -- Verificar si ya está compartido
    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 1)
    BEGIN
        SELECT 'El usuario ya tiene acceso a este espacio de trabajo.' AS cMsje;
        RETURN;
    END;
    
    -- Si ya estaba pero inactivo, reactivarlo y cambiar permiso
    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 0)
    BEGIN
        UPDATE FINANZAS.USUARIOS_COMPARTIDOS 
        SET lEsActivo = 1, cPermiso = @permiso, dFecMod = GETDATE()
        WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado;
    END
    ELSE
    BEGIN
        INSERT INTO FINANZAS.USUARIOS_COMPARTIDOS (idUsuarioOwner, idUsuarioInvitado, cPermiso, lEsActivo)
        VALUES (@usuarioOwnerId, @idInvitado, @permiso, 1);
    END;
    
    -- Registrar log de auditoría
    INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
    VALUES (@usuarioOwnerId, 'INVITAR', 'Invitación enviada a ' + @emailInvitado + ' con permiso de ' + @permiso, GETDATE());
    
    SELECT 'Invitación registrada exitosamente.' AS cMsje;
END;
GO

-- 5. Modificar stored procedure CG_ActualizaPermisoUsuario_SP para incluir logs
IF OBJECT_ID('CG_ActualizaPermisoUsuario_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_ActualizaPermisoUsuario_SP;
GO

CREATE PROCEDURE CG_ActualizaPermisoUsuario_SP
    @idCompartido INT,
    @cPermiso VARCHAR(50)
AS
BEGIN
    DECLARE @idOwner INT = (SELECT idUsuarioOwner FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idCompartido = @idCompartido);
    DECLARE @emailInvitado VARCHAR(100) = (SELECT u.email FROM dbo.usuarios u INNER JOIN FINANZAS.USUARIOS_COMPARTIDOS uc ON uc.idUsuarioInvitado = u.id WHERE uc.idCompartido = @idCompartido);

    UPDATE FINANZAS.USUARIOS_COMPARTIDOS
    SET cPermiso = @cPermiso, dFecMod = GETDATE()
    WHERE idCompartido = @idCompartido;
    
    -- Registrar log de auditoría
    IF @idOwner IS NOT NULL
    BEGIN
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idOwner, 'ACTUALIZAR ROL', 'Rol actualizado a ' + @cPermiso + ' para ' + ISNULL(@emailInvitado, 'usuario'), GETDATE());
    END;

    SELECT 'Permiso actualizado exitosamente.' AS cMsje;
END;
GO

-- 6. Modificar stored procedure CG_EliminarUsuarioCompartido_SP para incluir logs
IF OBJECT_ID('CG_EliminarUsuarioCompartido_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_EliminarUsuarioCompartido_SP;
GO

CREATE PROCEDURE CG_EliminarUsuarioCompartido_SP
    @idCompartido INT
AS
BEGIN
    DECLARE @idOwner INT = (SELECT idUsuarioOwner FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idCompartido = @idCompartido);
    DECLARE @emailInvitado VARCHAR(100) = (SELECT u.email FROM dbo.usuarios u INNER JOIN FINANZAS.USUARIOS_COMPARTIDOS uc ON uc.idUsuarioInvitado = u.id WHERE uc.idCompartido = @idCompartido);

    -- Eliminación lógica
    UPDATE FINANZAS.USUARIOS_COMPARTIDOS
    SET lEsActivo = 0, dFecMod = GETDATE()
    WHERE idCompartido = @idCompartido;
    
    -- Registrar log de auditoría
    IF @idOwner IS NOT NULL
    BEGIN
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idOwner, 'ELIMINAR ACCESO', 'Acceso revocado/eliminado para ' + ISNULL(@emailInvitado, 'usuario'), GETDATE());
    END;

    SELECT 'Acceso revocado/eliminado exitosamente.' AS cMsje;
END;
GO
