package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.DashboardResumen;
import model.DashboardGraficos;

public class DashboardDAO {

    public DashboardResumen obtenerResumen(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax) {
        DashboardResumen r = new DashboardResumen();
        String sql = "{CALL CG_ObtenerDashboardResumen_SP(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuarioOwner);
            stmt.setInt(2, idFiltradoUsuario);
            stmt.setString(3, fechaInicio);
            stmt.setString(4, fechaFin);
            stmt.setInt(5, idCategoria);
            stmt.setInt(6, idTipoGasto);
            stmt.setDouble(7, montoMin);
            stmt.setDouble(8, montoMax);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    r.setIngresosMes(rs.getDouble("ingresosMes"));
                    r.setIngresosCambio(rs.getString("ingresosCambio"));
                    r.setGastosMes(rs.getDouble("gastosMes"));
                    r.setGastosCambio(rs.getString("gastosCambio"));
                    r.setAhorrosMes(rs.getDouble("ahorrosMes"));
                    r.setAhorrosCambio(rs.getString("ahorrosCambio"));
                    r.setBalanceMes(rs.getDouble("balanceMes"));
                    r.setBalanceCambio(rs.getString("balanceCambio"));
                    
                    r.setUpcomingPayInternetAmount(rs.getDouble("upcomingPayInternetAmount"));
                    r.setUpcomingPayInternetDate(rs.getString("upcomingPayInternetDate"));
                    r.setUpcomingPayHealthAmount(rs.getDouble("upcomingPayHealthAmount"));
                    r.setUpcomingPayHealthDate(rs.getString("upcomingPayHealthDate"));
                    
                    r.setBannerTitle(rs.getString("bannerTitle"));
                    r.setBannerDesc(rs.getString("bannerDesc"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public List<DashboardGraficos> obtenerGraficos(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax) {
        List<DashboardGraficos> lista = new ArrayList<>();
        String sql = "{CALL CG_ObtenerDashboardGraficos_SP(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuarioOwner);
            stmt.setInt(2, idFiltradoUsuario);
            stmt.setString(3, fechaInicio);
            stmt.setString(4, fechaFin);
            stmt.setInt(5, idCategoria);
            stmt.setInt(6, idTipoGasto);
            stmt.setDouble(7, montoMin);
            stmt.setDouble(8, montoMax);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DashboardGraficos g = new DashboardGraficos();
                    g.setCategoria(rs.getString("categoria"));
                    g.setDescripcion(rs.getString("descripcion"));
                    g.setLimite(rs.getDouble("limite"));
                    g.setGasto(rs.getDouble("gasto"));
                    lista.add(g);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
