package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Gasto;

public class AuditoriaDAO {

    public List<ComboItem> obtenerCategoriasBase() {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaCboCategorias_SP";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new ComboItem(rs.getInt("value"), rs.getString("label")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Gasto> obtenerGastosBase(int idUsuario) {
        List<Gasto> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaGastosActivos_SP(?, ?, ?)}";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {
            stmt.setNull(1, java.sql.Types.DATE);
            stmt.setNull(2, java.sql.Types.DATE);
            stmt.setInt(3, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Gasto g = new Gasto();
                    g.setIdGasto(rs.getInt("idGasto"));
                    g.setDescripcion(rs.getString("descripGasto"));
                    g.setFechaGastoFormat(rs.getString("fechaGastoFormat"));
                    g.setCategoria(rs.getString("descripCategoria"));
                    g.setTipoGasto(rs.getString("descripTipoGasto"));
                    g.setMonto(rs.getDouble("monto"));
                    g.setUsuario(rs.getString("nombreUsuario"));
                    lista.add(g);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
