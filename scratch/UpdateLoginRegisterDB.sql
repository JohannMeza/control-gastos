-- ==========================================================
-- SCRIPT DE BASE DE DATOS: ENCRIPTACIÓN Y LOGIC DE INICIO/REGISTRO
-- ==========================================================

-- 1. Crear stored procedure CG_EncriptarTexto_SP
IF OBJECT_ID('CG_EncriptarTexto_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_EncriptarTexto_SP;
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

-- 2. Crear stored procedure CG_DesencriptarTexto_SP
IF OBJECT_ID('CG_DesencriptarTexto_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_DesencriptarTexto_SP;
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

-- 3. Migración de Contraseñas Existentes (Actualizar y encriptar en dbo.usuarios)
PRINT 'Iniciando migración de contraseñas existentes...';
GO

DECLARE @id INT;
DECLARE @pass VARCHAR(255);
DECLARE @encPass VARCHAR(255);

DECLARE cursor_usuarios CURSOR FOR
SELECT id, password
FROM dbo.usuarios
WHERE password NOT LIKE '0x%' AND password <> 'PENDIENTE';

OPEN cursor_usuarios;
FETCH NEXT FROM cursor_usuarios INTO @id, @pass;

WHILE @@FETCH_STATUS = 0
BEGIN
    EXEC CG_EncriptarTexto_SP @pass, @encPass OUTPUT;
    
    UPDATE dbo.usuarios
    SET password = @encPass
    WHERE id = @id;
    
    FETCH NEXT FROM cursor_usuarios INTO @id, @pass;
END;

CLOSE cursor_usuarios;
DEALLOCATE cursor_usuarios;
GO

PRINT 'Migración de contraseñas completada.';
GO

-- 4. Crear/Reemplazar stored procedure CG_ValidarLogin_SP
IF OBJECT_ID('CG_ValidarLogin_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_ValidarLogin_SP;
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

-- 5. Crear/Reemplazar stored procedure CG_RegistrarUsuario_SP
IF OBJECT_ID('CG_RegistrarUsuario_SP', 'P') IS NOT NULL 
    DROP PROCEDURE CG_RegistrarUsuario_SP;
GO

CREATE PROCEDURE CG_RegistrarUsuario_SP
    @nombre VARCHAR(100),
    @email VARCHAR(150),
    @password VARCHAR(255)
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
    
    -- Encriptar contraseña
    DECLARE @passwordEncriptada VARCHAR(255);
    EXEC CG_EncriptarTexto_SP @password, @passwordEncriptada OUTPUT;
    
    DECLARE @idUsuario INT;
    
    -- Verificar si existe el correo
    IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email)
    BEGIN
        -- Si existe y está pendiente, actualizar
        IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email AND password = 'PENDIENTE')
        BEGIN
            UPDATE dbo.usuarios
            SET nombre = @nombre, password = @passwordEncriptada, fecha_creacion = GETDATE()
            WHERE email = @email;
            
            SELECT @idUsuario = id FROM dbo.usuarios WHERE email = @email;
            
            -- Asegurar configuración de seguridad
            IF NOT EXISTS (SELECT 1 FROM FINANZAS.CONFIGURACION_SEGURIDAD WHERE idUsuario = @idUsuario)
            BEGIN
                INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado)
                VALUES (@idUsuario, 0);
            END;
            
            -- Registrar auditoría
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
        -- Insertar nuevo usuario
        INSERT INTO dbo.usuarios (nombre, email, password, rol, fecha_creacion)
        VALUES (@nombre, @email, @passwordEncriptada, 'USER', GETDATE());
        
        SET @idUsuario = SCOPE_IDENTITY();
        
        -- Configuración de seguridad por defecto
        INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado)
        VALUES (@idUsuario, 0);
        
        -- Registrar auditoría
        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)
        VALUES (@idUsuario, 'REGISTRO', 'Nuevo usuario registrado en la aplicación.', GETDATE());
    END;
    
    SELECT 'Usuario registrado exitosamente.' AS cMsje;
END;
GO
