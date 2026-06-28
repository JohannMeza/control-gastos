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

            System.out.println("=== Definition of CG_EliminarPresupuestoPorId_SP ===");
            try (ResultSet rs = stmt.executeQuery("exec sp_helptext 'CG_EliminarPresupuestoPorId_SP'")) {
                while (rs.next()) {
                    System.out.print(rs.getString(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
