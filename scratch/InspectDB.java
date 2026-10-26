package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InspectDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                System.out.println("Adding apellidos column to dbo.usuarios if not exists...");
                stmt.execute(
                    "IF NOT EXISTS (SELECT 1 FROM sys.columns WHERE object_id = OBJECT_ID('dbo.usuarios') AND name = 'apellidos')\n" +
                    "    ALTER TABLE dbo.usuarios ADD apellidos VARCHAR(100) NULL;"
                );

                System.out.println("Updating CG_ValidarLogin_SP...");
                stmt.execute(
                    "CREATE OR ALTER PROCEDURE [dbo].[CG_ValidarLogin_SP]\n" +
                    "    @email VARCHAR(150),\n" +
                    "    @passwordIngresada VARCHAR(255)\n" +
                    "AS\n" +
                    "BEGIN\n" +
                    "    SET NOCOUNT ON;\n" +
                    "    DECLARE @id INT;\n" +
                    "    DECLARE @nombre VARCHAR(100);\n" +
                    "    DECLARE @apellidos VARCHAR(100);\n" +
                    "    DECLARE @emailDB VARCHAR(150);\n" +
                    "    DECLARE @passwordEncriptada VARCHAR(255);\n" +
                    "    DECLARE @rol VARCHAR(50);\n" +
                    "    \n" +
                    "    SELECT @id = id, @nombre = nombre, @apellidos = apellidos, @emailDB = email, @passwordEncriptada = password, @rol = rol\n" +
                    "    FROM dbo.usuarios\n" +
                    "    WHERE email = @email;\n" +
                    "    \n" +
                    "    IF @id IS NULL\n" +
                    "    BEGIN\n" +
                    "        THROW 50001, 'El correo electrónico no está registrado.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    \n" +
                    "    IF @passwordEncriptada = 'PENDIENTE'\n" +
                    "    BEGIN\n" +
                    "        THROW 50002, 'La cuenta aún no está activa. Por favor complete su registro.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    \n" +
                    "    DECLARE @passwordDesencriptada VARCHAR(255);\n" +
                    "    EXEC CG_DesencriptarTexto_SP @passwordEncriptada, @passwordDesencriptada OUTPUT;\n" +
                    "    \n" +
                    "    IF @passwordDesencriptada <> @passwordIngresada\n" +
                    "    BEGIN\n" +
                    "        THROW 50003, 'La contraseña ingresada es incorrecta.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    \n" +
                    "    INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)\n" +
                    "    VALUES (@id, 'LOGIN', 'Inicio de sesión exitoso.', GETDATE());\n" +
                    "    \n" +
                    "    SELECT id, nombre, ISNULL(apellidos, '') AS apellidos, email, rol\n" +
                    "    FROM dbo.usuarios\n" +
                    "    WHERE id = @id;\n" +
                    "END;"
                );

                System.out.println("Updating CG_ObtenerDatosUsuarioPorId_SP...");
                stmt.execute(
                    "CREATE OR ALTER PROCEDURE [dbo].[CG_ObtenerDatosUsuarioPorId_SP]\n" +
                    "    @id INT\n" +
                    "AS\n" +
                    "BEGIN\n" +
                    "    SET NOCOUNT ON;\n" +
                    "    SELECT id, nombre, ISNULL(apellidos, '') AS apellidos, email, rol\n" +
                    "    FROM dbo.usuarios\n" +
                    "    WHERE id = @id;\n" +
                    "END;"
                );

                System.out.println("Updating CG_RegistrarUsuario_SP...");
                stmt.execute(
                    "CREATE OR ALTER PROCEDURE [dbo].[CG_RegistrarUsuario_SP]\n" +
                    "    @nombre VARCHAR(100),\n" +
                    "    @email VARCHAR(150),\n" +
                    "    @password VARCHAR(255),\n" +
                    "    @apellidos VARCHAR(100) = NULL\n" +
                    "AS\n" +
                    "BEGIN\n" +
                    "    SET NOCOUNT ON;\n" +
                    "    IF @nombre IS NULL OR @nombre = ''\n" +
                    "    BEGIN\n" +
                    "        THROW 50004, 'El nombre no puede estar vacío.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    IF @email IS NULL OR @email = ''\n" +
                    "    BEGIN\n" +
                    "        THROW 50005, 'El correo electrónico no puede estar vacío.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    IF @password IS NULL OR @password = ''\n" +
                    "    BEGIN\n" +
                    "        THROW 50006, 'La contraseña no puede estar vacía.', 1;\n" +
                    "        RETURN;\n" +
                    "    END;\n" +
                    "    \n" +
                    "    DECLARE @passwordEncriptada VARCHAR(255);\n" +
                    "    EXEC CG_EncriptarTexto_SP @password, @passwordEncriptada OUTPUT;\n" +
                    "    DECLARE @idUsuario INT;\n" +
                    "    \n" +
                    "    IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email)\n" +
                    "    BEGIN\n" +
                    "        IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE email = @email AND password = 'PENDIENTE')\n" +
                    "        BEGIN\n" +
                    "            UPDATE dbo.usuarios\n" +
                    "            SET nombre = @nombre, apellidos = @apellidos, password = @passwordEncriptada, fecha_creacion = GETDATE()\n" +
                    "            WHERE email = @email;\n" +
                    "            SELECT @idUsuario = id FROM dbo.usuarios WHERE email = @email;\n" +
                    "            IF NOT EXISTS (SELECT 1 FROM FINANZAS.CONFIGURACION_SEGURIDAD WHERE idUsuario = @idUsuario)\n" +
                    "                INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado) VALUES (@idUsuario, 0);\n" +
                    "            INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)\n" +
                    "            VALUES (@idUsuario, 'REGISTRO', 'Usuario completó su registro desde invitación.', GETDATE());\n" +
                    "        END\n" +
                    "        ELSE\n" +
                    "        BEGIN\n" +
                    "            THROW 50007, 'El correo electrónico ya se encuentra registrado.', 1;\n" +
                    "            RETURN;\n" +
                    "        END;\n" +
                    "    END\n" +
                    "    ELSE\n" +
                    "    BEGIN\n" +
                    "        INSERT INTO dbo.usuarios (nombre, apellidos, email, password, rol, fecha_creacion)\n" +
                    "        VALUES (@nombre, @apellidos, @email, @passwordEncriptada, 'EDITOR', GETDATE());\n" +
                    "        SET @idUsuario = SCOPE_IDENTITY();\n" +
                    "        INSERT INTO FINANZAS.CONFIGURACION_SEGURIDAD (idUsuario, lMfaHabilitado) VALUES (@idUsuario, 0);\n" +
                    "        INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha)\n" +
                    "        VALUES (@idUsuario, 'REGISTRO', 'Nuevo usuario registrado en la aplicación.', GETDATE());\n" +
                    "    END;\n" +
                    "    SELECT 'Usuario registrado exitosamente.' AS cMsje;\n" +
                    "END;"
                );

                System.out.println("DB migration completed successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




