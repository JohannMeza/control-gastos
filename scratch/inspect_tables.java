package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class inspect_tables {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== columns of FINANZAS.GASTOS ===");
            try (ResultSet rs = stmt.executeQuery("SELECT name FROM sys.columns WHERE object_id = OBJECT_ID('FINANZAS.GASTOS')")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }

            System.out.println("=== columns and types of FINANZAS.AHORROS ===");
            try (ResultSet rs = stmt.executeQuery("SELECT c.name, t.name AS type FROM sys.columns c JOIN sys.types t ON c.user_type_id = t.user_type_id WHERE c.object_id = OBJECT_ID('FINANZAS.AHORROS')")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " (" + rs.getString("type") + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
