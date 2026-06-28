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
    
    public List<Presupuesto> listarPresupuesto(int idUsuario, int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idEstado) {
        List<Presupuesto> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaPresupuestosActivos_SP(?, ?, ?, ?, ?, ?, ?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idUsuarioOwner);
            stmt.setInt(3, idFiltradoUsuario);
            stmt.setString(4, fechaInicio);
            stmt.setString(5, fechaFin);
            stmt.setInt(6, idCategoria);
            stmt.setInt(7, idEstado);

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

    public List<model.PresupuestoActividad> listarPresupuestoActividades(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin) {
        List<model.PresupuestoActividad> lista = new ArrayList<>();
        String sql = "{CALL CG_ObtenerPresupuestoActividades_SP(?, ?, ?, ?)}";

        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setInt(1, idUsuarioOwner);
            stmt.setInt(2, idFiltradoUsuario);
            stmt.setString(3, fechaInicio);
            stmt.setString(4, fechaFin);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                model.PresupuestoActividad act = new model.PresupuestoActividad();
                act.setIdGasto(rs.getInt("idGasto"));
                act.setcGasto(rs.getString("cGasto"));
                act.setNombreCategoria(rs.getString("nombreCategoria"));
                act.setMontoGasto(rs.getDouble("montoGasto"));
                act.setcFechaGasto(rs.getString("cFechaGasto"));
                act.setLimitePresupuesto(rs.getDouble("limitePresupuesto"));
                act.setUmbralAlerta(rs.getInt("umbralAlerta"));
                act.setTotalGastadoMes(rs.getDouble("totalGastadoMes"));
                lista.add(act);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public String guardarPresupuesto(Presupuesto presupuesto) {
        String sql = "{CALL CG_GuardaPresupuestoIngresado_SP(?, ?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {

            String xml = XmlUtil.convertirAXml(presupuesto);

            stmt.setString(1, xml);
            stmt.setInt(2, presupuesto.getIdUsuario());

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

    public List<Integer> obtenerAniosConDatos(int idUsuario) {
        List<Integer> anios = new ArrayList<>();
        String sql = "SELECT anio FROM (" +
                     "  SELECT DISTINCT YEAR(dFecha) AS anio FROM FINANZAS.AHORROS WHERE idUsuReg = ? AND lEsActivo = 1" +
                     "  UNION" +
                     "  SELECT DISTINCT YEAR(cFechaGasto) AS anio FROM FINANZAS.GASTOS WHERE idUsuReg = ? AND lEsActivo = 1" +
                     "  UNION" +
                     "  SELECT DISTINCT YEAR(dFecha) AS anio FROM FINANZAS.INGRESOS WHERE idUsuario = ? AND lEsActivo = 1" +
                     ") T WHERE anio IS NOT NULL ORDER BY anio DESC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idUsuario);
            stmt.setInt(3, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    anios.add(rs.getInt("anio"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anios;
    }
}
