package dao;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Gasto;
import util.XmlUtil;

public class GastoDAO {

    public String guardar(Gasto gasto) {
        String sql = "{CALL CG_GuardaGastoIngresado_SP(?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            String xml = XmlUtil.convertirAXml(gasto);

            stmt.setString(1, xml);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               return rs.getString("cMsje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "No se recibió respuesta";
    }

    public String actualizar(Gasto gasto) {
       String sql = "{CALL CG_ActualizaGastoIngresado_SP(?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            String xml = XmlUtil.convertirAXml(gasto);

            stmt.setString(1, xml);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("cMsje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No se recibió respuesta";
    }
    
    public String eliminar(int idGasto) {
        String sql = "{CALL CG_EliminarGastoPorId_SP(?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            stmt.setInt(1, idGasto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               return rs.getString("cMsje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No se recibió respuesta";
    }
    
    public List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario) {
        List<Gasto> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaGastosActivos_SP(?, ?, ?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            if (fechaDesde != null) {
                stmt.setDate(1, new java.sql.Date(fechaDesde.getTime()));
            } else {
                stmt.setNull(1, java.sql.Types.DATE);
            }

            if (fechaHasta != null) {
                stmt.setDate(2, new java.sql.Date(fechaHasta.getTime()));
            } else {
                stmt.setNull(2, java.sql.Types.DATE);
            }

            stmt.setInt(3, idUsuario);

            ResultSet rs = stmt.executeQuery();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public Gasto obtenerPorId(int idGasto) {
        Gasto gasto = null;
        String sql = "{CALL CG_ObtenerGastoPorId_SP(?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            stmt.setInt(1, idGasto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                gasto = new Gasto();

                gasto.setIdGasto(rs.getInt("idGasto"));
                gasto.setIdCategoria(rs.getInt("idCategoria"));
                gasto.setIdTipoGasto(rs.getInt("idTipoGasto"));
                gasto.setDescripcion(rs.getString("descripcion"));
                gasto.setMonto(rs.getDouble("monto"));
                gasto.setFechaGasto(rs.getDate("fechaGasto"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gasto;
    }
    
    public List<ComboItem> listarCategorias() {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaCboCategorias_SP";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ComboItem item = new ComboItem(
                    rs.getInt("value"),
                    rs.getString("label")
                );

                lista.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<ComboItem> listarTipoGastos() {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaCboTipoGastos_SP";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ComboItem item = new ComboItem(
                    rs.getInt("value"),
                    rs.getString("label")
                );

                lista.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public List<ComboItem> listarUsuarios() {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaCboUsuarios_SP";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ComboItem item = new ComboItem(
                    rs.getInt("value"),
                    rs.getString("label")
                );

                lista.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}