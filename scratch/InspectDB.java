package scratch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InspectDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        String sql = "{CALL CG_ObtenerPresupuestoActividades_SP(?, ?, ?, ?)}";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareCall(sql)) {
            
            stmt.setInt(1, 1);
            stmt.setInt(2, 0);
            stmt.setString(3, "2026-06-01");
            stmt.setString(4, "2026-06-30");

            System.out.println("--- CALLING CG_ObtenerPresupuestoActividades_SP ---");
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasResults = false;
                while (rs.next()) {
                    hasResults = true;
                    System.out.printf("idGasto: %d, cGasto: %s, nombreCategoria: %s, montoGasto: %.2f, totalGastadoMes: %.2f, limite: %.2f%n",
                        rs.getInt("idGasto"),
                        rs.getString("cGasto"),
                        rs.getString("nombreCategoria"),
                        rs.getDouble("montoGasto"),
                        rs.getDouble("totalGastadoMes"),
                        rs.getDouble("limitePresupuesto")
                    );
                }
                if (!hasResults) {
                    System.out.println("No activities returned.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




