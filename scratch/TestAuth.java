package scratch;

import model.Usuario;
import service.UsuarioService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestAuth {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("TESTING LOGIN AND REGISTRATION FLOW");
        System.out.println("==================================================");

        UsuarioService service = new UsuarioService();

        // 1. Test Valid Login for Existing User
        try {
            System.out.println("1. Testing valid login for Jane Doe...");
            Usuario u = service.validarLogin("jane.doe@company.com", "123456");
            if (u != null && u.getEmail().equals("jane.doe@company.com")) {
                System.out.println("SUCCESS: Jane Doe logged in. Name: " + u.getNombre() + ", Role: " + u.getRol());
            } else {
                System.out.println("FAILURE: Login succeeded but user object is null or incorrect.");
            }
        } catch (Exception e) {
            System.out.println("FAILURE: Expected successful login, but got exception: " + e.getMessage());
        }

        // 2. Test Invalid Password Login
        try {
            System.out.println("\n2. Testing invalid password for Jane Doe...");
            service.validarLogin("jane.doe@company.com", "wrongpassword");
            System.out.println("FAILURE: Login succeeded with invalid password.");
        } catch (Exception e) {
            System.out.println("SUCCESS: Got expected error message: " + e.getMessage());
        }

        // 3. Test Non-existent Email Login
        try {
            System.out.println("\n3. Testing non-existent email login...");
            service.validarLogin("unknown@company.com", "1234");
            System.out.println("FAILURE: Login succeeded for non-existent email.");
        } catch (Exception e) {
            System.out.println("SUCCESS: Got expected error message: " + e.getMessage());
        }

        // 4. Test User Registration
        String testEmail = "auth.tester@company.com";
        String testName = "Auth Tester";
        String testPass = "SecretPass123";

        // Clean up from database first to make test idempotent
        cleanUpUser(testEmail);

        try {
            System.out.println("\n4. Testing user registration...");
            String result = service.registrarUsuario(testName, testEmail, testPass);
            System.out.println("SUCCESS: Registration result: " + result);

            // Verify the user can log in
            System.out.println("Verifying login of newly registered user...");
            Usuario registered = service.validarLogin(testEmail, testPass);
            if (registered != null && registered.getEmail().equals(testEmail)) {
                System.out.println("SUCCESS: New user logged in. Name: " + registered.getNombre() + ", Role: " + registered.getRol());
            } else {
                System.out.println("FAILURE: New user login failed or returned incorrect object.");
            }
        } catch (Exception e) {
            System.out.println("FAILURE: Registration/Verification failed with exception: " + e.getMessage());
        }

        // 5. Test Duplicate Registration
        try {
            System.out.println("\n5. Testing duplicate email registration...");
            service.registrarUsuario(testName, testEmail, testPass);
            System.out.println("FAILURE: Duplicate registration succeeded.");
        } catch (Exception e) {
            System.out.println("SUCCESS: Got expected error message: " + e.getMessage());
        }

        // Clean up database
        cleanUpUser(testEmail);

        System.out.println("\n==================================================");
        System.out.println("TEST RUN COMPLETED");
        System.out.println("==================================================");
    }

    private static void cleanUpUser(String email) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // Get user ID
            int id = -1;
            try (var rs = stmt.executeQuery("SELECT id FROM dbo.usuarios WHERE email = '" + email + "'")) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }

            if (id != -1) {
                // Delete references
                stmt.execute("DELETE FROM FINANZAS.CONFIGURACION_SEGURIDAD WHERE idUsuario = " + id);
                stmt.execute("DELETE FROM FINANZAS.LOGS_AUDITORIA WHERE idUsuarioOwner = " + id);
                stmt.execute("DELETE FROM FINANZAS.USUARIOS_COMPARTIDOS WHERE idUsuarioOwner = " + id + " OR idUsuarioInvitado = " + id);
                stmt.execute("DELETE FROM dbo.usuarios WHERE id = " + id);
                System.out.println("Cleaned up database for user: " + email);
            }

        } catch (Exception e) {
            System.err.println("Clean up failed: " + e.getMessage());
        }
    }
}
