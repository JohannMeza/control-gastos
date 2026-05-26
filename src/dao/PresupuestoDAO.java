package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Presupuesto;
import util.XmlUtil;

public class PresupuestoDAO {
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
    
    public List<ComboItem> listarPeriodo() {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaCboTipoPeriodo_SP";

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
    
    public List<Presupuesto> listarPresupuesto(int idUsuario) {
        List<Presupuesto> lista = new ArrayList<>();
        String sql = "EXEC CG_ListaPresupuestosActivos_SP";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Presupuesto g = new Presupuesto();

                g.setIdPresupuesto(rs.getInt("idPresupuesto"));
                g.setCategoria(rs.getString("nombreCategoria"));
                g.setMontoAsignado(rs.getDouble("montoAsignado"));
                g.setMontoGastado(rs.getDouble("montoGastado"));
                g.setEstadoPresupuesto(rs.getString("estadoPresupuesto"));
                g.setProgresoPresupuesto(rs.getString("progresoPresupuesto"));
                
                lista.add(g);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public String guardarPresupuesto(Presupuesto presupuesto) {
        String sql = "{CALL CG_GuardaPresupuestoIngresado_SP(?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            String xml = XmlUtil.convertirAXml(presupuesto);

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
    
    public String eliminarPresupuesto(int idPresupuesto) {
        String sql = "{CALL CG_EliminarPresupuestoPorId_SP(?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            stmt.setInt(1, idPresupuesto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               return rs.getString("cMsje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No se recibió respuesta";
    }
}
