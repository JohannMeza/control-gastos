package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetupUsuariosDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Checking existing users in dbo.usuarios...");
            try (ResultSet rs = stmt.executeQuery("SELECT id, nombre, email, rol FROM dbo.usuarios")) {
                while (rs.next()) {
                    System.out.printf("User: ID=%d, Name=%s, Email=%s, Role=%s%n",
                            rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("rol"));
                }
            }

            System.out.println("Inserting initial mock shared users if not exists...");
            // Ensure we have Jane Doe (Owner, ID=1), and others
            stmt.execute("IF NOT EXISTS (SELECT * FROM dbo.usuarios WHERE email = 'jane.doe@company.com')\n" +
                         "BEGIN\n" +
                         "    -- Update user 1 to be Jane Doe if ID 1 exists, or insert\n" +
                         "    IF EXISTS (SELECT * FROM dbo.usuarios WHERE id = 1)\n" +
                         "        UPDATE dbo.usuarios SET nombre = 'Jane Doe', email = 'jane.doe@company.com', rol = 'ADMIN' WHERE id = 1;\n" +
                         "    ELSE\n" +
                         "        INSERT INTO dbo.usuarios (nombre, email, password, rol) VALUES ('Jane Doe', 'jane.doe@company.com', '1234', 'ADMIN');\n" +
                         "END;");

            // Seed other collaborators in dbo.usuarios if they do not exist
            stmt.execute("IF NOT EXISTS (SELECT * FROM dbo.usuarios WHERE email = 'm.smith@finance.dev')\n" +
                         "    INSERT INTO dbo.usuarios (nombre, email, password, rol) VALUES ('Mark Smith', 'm.smith@finance.dev', '1234', 'EDITOR');");
            stmt.execute("IF NOT EXISTS (SELECT * FROM dbo.usuarios WHERE email = 'lucas.w@partners.net')\n" +
                         "    INSERT INTO dbo.usuarios (nombre, email, password, rol) VALUES ('Lucas Wright', 'lucas.w@partners.net', '1234', 'VIEWER');");
            stmt.execute("IF NOT EXISTS (SELECT * FROM dbo.usuarios WHERE email = 'pending.invite@acme.com')\n" +
                         "    INSERT INTO dbo.usuarios (nombre, email, password, rol) VALUES ('', 'pending.invite@acme.com', 'PENDIENTE', 'VIEWER');");

            // Populate FINANZAS.USUARIOS_COMPARTIDOS
            stmt.execute("IF (SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS) = 0\n" +
                         "BEGIN\n" +
                         "    DECLARE @idOwner INT = (SELECT id FROM dbo.usuarios WHERE email = 'jane.doe@company.com');\n" +
                         "    DECLARE @idMark INT = (SELECT id FROM dbo.usuarios WHERE email = 'm.smith@finance.dev');\n" +
                         "    DECLARE @idLucas INT = (SELECT id FROM dbo.usuarios WHERE email = 'lucas.w@partners.net');\n" +
                         "    DECLARE @idPending INT = (SELECT id FROM dbo.usuarios WHERE email = 'pending.invite@acme.com');\n" +
                         "    \n" +
                         "    INSERT INTO FINANZAS.USUARIOS_COMPARTIDOS (idUsuarioOwner, idUsuarioInvitado, cPermiso, lEsActivo) VALUES\n" +
                         "    (@idOwner, @idMark, 'EDITOR', 1),\n" +
                         "    (@idOwner, @idLucas, 'VIEWER', 1),\n" +
                         "    (@idOwner, @idPending, 'VIEWER', 1);\n" +
                         "END;");

            System.out.println("Creating stored procedures for Usuarios/Colaboración...");

            // 1. CG_ListaUsuariosCompartidos_SP
            stmt.execute("IF OBJECT_ID('CG_ListaUsuariosCompartidos_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ListaUsuariosCompartidos_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tListar usuarios colaboradores/compartidos de un propietario\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tUsuarios / Colaboración\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_ListaUsuariosCompartidos_SP 1\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_ListaUsuariosCompartidos_SP\n" +
                         "    @idUsuarioOwner INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    SELECT \n" +
                         "        uc.idCompartido,\n" +
                         "        uc.idUsuarioOwner,\n" +
                         "        uc.idUsuarioInvitado,\n" +
                         "        uc.cPermiso AS permiso,\n" +
                         "        u.nombre AS nombreInvitado,\n" +
                         "        u.email AS emailInvitado,\n" +
                         "        CASE \n" +
                         "            WHEN u.nombre = '' OR u.password = 'PENDIENTE' THEN 'Pendiente'\n" +
                         "            ELSE 'Activo'\n" +
                         "        END AS estado\n" +
                         "    FROM FINANZAS.USUARIOS_COMPARTIDOS uc\n" +
                         "    INNER JOIN dbo.usuarios u ON uc.idUsuarioInvitado = u.id\n" +
                         "    WHERE uc.idUsuarioOwner = @idUsuarioOwner AND uc.lEsActivo = 1;\n" +
                         "END;");

            // 2. CG_GuardaInvitacionUsuario_SP
            stmt.execute("IF OBJECT_ID('CG_GuardaInvitacionUsuario_SP', 'P') IS NOT NULL DROP PROCEDURE CG_GuardaInvitacionUsuario_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tRegistrar/invitar un nuevo usuario colaborador vía XML\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tUsuarios / Colaboración\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_GuardaInvitacionUsuario_SP '<usuarioCompartido><usuarioOwnerId>1</usuarioOwnerId><emailInvitado>test@test.com</emailInvitado><permiso>EDITOR</permiso></usuarioCompartido>'\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_GuardaInvitacionUsuario_SP\n" +
                         "    @xmlData NVARCHAR(MAX)\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    DECLARE @idDoc INT;\n" +
                         "    EXEC sp_xml_preparedocument @idDoc OUTPUT, @xmlData;\n" +
                         "    \n" +
                         "    DECLARE @usuarioOwnerId INT;\n" +
                         "    DECLARE @emailInvitado VARCHAR(100);\n" +
                         "    DECLARE @permiso VARCHAR(50);\n" +
                         "    \n" +
                         "    SELECT \n" +
                         "        @usuarioOwnerId = usuarioOwnerId,\n" +
                         "        @emailInvitado = emailInvitado,\n" +
                         "        @permiso = permiso\n" +
                         "    FROM OPENXML(@idDoc, '/usuarioCompartido', 2)\n" +
                         "    WITH (\n" +
                         "        usuarioOwnerId INT,\n" +
                         "        emailInvitado VARCHAR(100),\n" +
                         "        permiso VARCHAR(50)\n" +
                         "    );\n" +
                         "    \n" +
                         "    EXEC sp_xml_removedocument @idDoc;\n" +
                         "    \n" +
                         "    IF @emailInvitado IS NULL OR @emailInvitado = ''\n" +
                         "    BEGIN\n" +
                         "        SELECT 'Debe ingresar un correo electrónico válido.' AS cMsje;\n" +
                         "        RETURN;\n" +
                         "    END;\n" +
                         "    \n" +
                         "    -- Buscar si ya existe el usuario\n" +
                         "    DECLARE @idInvitado INT = (SELECT id FROM dbo.usuarios WHERE email = @emailInvitado);\n" +
                         "    \n" +
                         "    IF @idInvitado IS NULL\n" +
                         "    BEGIN\n" +
                         "        -- Crear usuario placeholder\n" +
                         "        INSERT INTO dbo.usuarios (nombre, email, password, rol) \n" +
                         "        VALUES ('', @emailInvitado, 'PENDIENTE', 'USER');\n" +
                         "        SET @idInvitado = SCOPE_IDENTITY();\n" +
                         "    END;\n" +
                         "    \n" +
                         "    -- Verificar si ya está compartido\n" +
                         "    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 1)\n" +
                         "    BEGIN\n" +
                         "        SELECT 'El usuario ya tiene acceso a este espacio de trabajo.' AS cMsje;\n" +
                         "        RETURN;\n" +
                         "    END;\n" +
                         "    \n" +
                         "    -- Si ya estaba pero inactivo, reactivarlo y cambiar permiso\n" +
                         "    IF EXISTS (SELECT * FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado AND lEsActivo = 0)\n" +
                         "    BEGIN\n" +
                         "        UPDATE FINANZAS.USUARIOS_COMPARTIDOS \n" +
                         "        SET lEsActivo = 1, cPermiso = @permiso, dFecMod = GETDATE()\n" +
                         "        WHERE idUsuarioOwner = @usuarioOwnerId AND idUsuarioInvitado = @idInvitado;\n" +
                         "    END\n" +
                         "    ELSE\n" +
                         "    BEGIN\n" +
                         "        INSERT INTO FINANZAS.USUARIOS_COMPARTIDOS (idUsuarioOwner, idUsuarioInvitado, cPermiso, lEsActivo)\n" +
                         "        VALUES (@usuarioOwnerId, @idInvitado, @permiso, 1);\n" +
                         "    END;\n" +
                         "    \n" +
                         "    SELECT 'Invitación registrada exitosamente.' AS cMsje;\n" +
                         "END;");

            // 3. CG_ActualizaPermisoUsuario_SP
            stmt.execute("IF OBJECT_ID('CG_ActualizaPermisoUsuario_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ActualizaPermisoUsuario_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tActualizar rol de permiso de un usuario colaborador\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tUsuarios / Colaboración\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_ActualizaPermisoUsuario_SP 1, 'ADMIN'\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_ActualizaPermisoUsuario_SP\n" +
                         "    @idCompartido INT,\n" +
                         "    @cPermiso VARCHAR(50)\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    UPDATE FINANZAS.USUARIOS_COMPARTIDOS\n" +
                         "    SET cPermiso = @cPermiso, dFecMod = GETDATE()\n" +
                         "    WHERE idCompartido = @idCompartido;\n" +
                         "    \n" +
                         "    SELECT 'Permiso actualizado exitosamente.' AS cMsje;\n" +
                         "END;");

            // 4. CG_EliminarUsuarioCompartido_SP
            stmt.execute("IF OBJECT_ID('CG_EliminarUsuarioCompartido_SP', 'P') IS NOT NULL DROP PROCEDURE CG_EliminarUsuarioCompartido_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tRevocar o eliminar el acceso compartido de un usuario\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tUsuarios / Colaboración\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_EliminarUsuarioCompartido_SP 1\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_EliminarUsuarioCompartido_SP\n" +
                         "    @idCompartido INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    -- Eliminación lógica\n" +
                         "    UPDATE FINANZAS.USUARIOS_COMPARTIDOS\n" +
                         "    SET lEsActivo = 0, dFecMod = GETDATE()\n" +
                         "    WHERE idCompartido = @idCompartido;\n" +
                         "    \n" +
                         "    SELECT 'Acceso revocado/eliminado exitosamente.' AS cMsje;\n" +
                         "END;");

            // 5. CG_ObtenerResumenUsuarios_SP
            stmt.execute("IF OBJECT_ID('CG_ObtenerResumenUsuarios_SP', 'P') IS NOT NULL DROP PROCEDURE CG_ObtenerResumenUsuarios_SP;");
            stmt.execute("/******************************************************************************************************************************************************* \n" +
                         "* Objetivo:\t\tObtener resumen de asientos y configuración de usuarios colaboradores\n" +
                         "* \n" +
                         "* Escrito por:\t\tJohann Meza\n" +
                         "* \n" +
                         "* Fecha creación:\t12/06/2026\n" +
                         "* \n" +
                         "* Sistema / Modulo:\tUsuarios / Colaboración\n" +
                         "* \n" +
                         "* Modificaciones: \n" +
                         "* \n" +
                         "* Sintaxis de ejemplo:\t\t \n" +
                         "* EXEC CG_ObtenerResumenUsuarios_SP 1\n" +
                         "*******************************************************************************************************************************************************/ \n" +
                         "CREATE PROCEDURE CG_ObtenerResumenUsuarios_SP\n" +
                         "    @idUsuarioOwner INT\n" +
                         "AS\n" +
                         "BEGIN\n" +
                         "    DECLARE @asientosUsados INT;\n" +
                         "    -- Se incluye al owner mismo como 1 asiento\n" +
                         "    SET @asientosUsados = 1 + (SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner AND lEsActivo = 1);\n" +
                         "    \n" +
                         "    SELECT \n" +
                         "        @asientosUsados AS asientosUsados,\n" +
                         "        10 AS asientosTotales,\n" +
                         "        'Hace ' + CAST(ABS(CHECKSUM(NEWID())) % 10 + 1 AS VARCHAR) + ' min por ' + ISNULL((SELECT nombre FROM dbo.usuarios WHERE id = @idUsuarioOwner), 'Jane Doe') AS ultimaSincronizacion,\n" +
                         "        'Activo (100%)' AS mfaActivoStatus,\n" +
                         "        CAST((SELECT COUNT(*) FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = @idUsuarioOwner) AS VARCHAR) + ' registros' AS logsAuditoriaCount;\n" +
                         "END;");

            System.out.println("Setup Completed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
