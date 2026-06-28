package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Ahorro;
import model.MetaAhorro;
import model.MetasResumen;
import util.XmlUtil;

public class AhorroDAO {

    public List<ComboItem> listarMetas(int idUsuario) {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaCboMetasAhorro_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ComboItem item = new ComboItem(
                        rs.getInt("value"),
                        rs.getString("label")
                    );
                    lista.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Ahorro> listarAhorros(int idUsuario) {
        List<Ahorro> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaAhorrosActivos_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ahorro a = new Ahorro();
                    a.setIdAhorro(rs.getInt("idAhorro"));
                    a.setIdMeta(rs.getInt("idMeta"));
                    a.setNombreMeta(rs.getString("nombreMeta"));
                    a.setMonto(rs.getDouble("monto"));
                    a.setFechaAhorro(rs.getDate("fechaAhorro"));
                    a.setFechaAhorroFormat(rs.getString("fechaAhorroFormat"));
                    a.setDescripcion(rs.getString("descripcion"));
                    a.setNombreUsuario(rs.getString("nombreUsuario"));
                    lista.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Ahorro obtenerPorId(int idAhorro) {
        Ahorro a = null;
        String sql = "{CALL CG_ObtenerAhorroPorId_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idAhorro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    a = new Ahorro();
                    a.setIdAhorro(rs.getInt("idAhorro"));
                    a.setIdMeta(rs.getInt("idMeta"));
                    a.setMonto(rs.getDouble("monto"));
                    a.setFechaAhorro(rs.getDate("fechaAhorro"));
                    a.setDescripcion(rs.getString("descripcion"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public String guardar(Ahorro ahorro) {
        String sql = "{CALL CG_GuardaAhorroIngresado_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(ahorro);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta";
    }

    public String actualizar(Ahorro ahorro) {
        String sql = "{CALL CG_ActualizaAhorroIngresado_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(ahorro);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta";
    }

    public String eliminar(int idAhorro) {
        String sql = "{CALL CG_EliminarAhorroPorId_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idAhorro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta";
    }

    public MetasResumen obtenerResumen(int idUsuario) {
        MetasResumen r = new MetasResumen();
        String sql = "{CALL CG_ObtenerMetasResumen_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    r.setTotalAhorrado(rs.getDouble("totalAhorrado"));
                    r.setAhorradoEsteMes(rs.getDouble("ahorradoEsteMes"));
                    r.setMetaMensualTarget(rs.getDouble("metaMensualTarget"));
                    r.setTotalMetasActivas(rs.getInt("totalMetasActivas"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public List<MetaAhorro> obtenerMetasDetalle(int idUsuario) {
        List<MetaAhorro> lista = new ArrayList<>();
        String sql = "{CALL CG_ObtenerMetasDetalle_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MetaAhorro m = new MetaAhorro();
                    m.setIdMeta(rs.getInt("idMeta"));
                    m.setDescripcion(rs.getString("cDescripcion"));
                    m.setMontoObjetivo(rs.getDouble("nMontoObjetivo"));
                    m.setMontoActual(rs.getDouble("nMontoActual"));
                    m.setFechaEstimada(rs.getDate("dFechaEstimada"));
                    m.setIconPath(rs.getString("cIconPath"));
                    m.setAsignacionMensual(rs.getDouble("nAsignacionMensual"));
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String guardarMeta(MetaAhorro meta, int idUsuario) {
        String sql = "{CALL CG_GuardaMetaAhorro_SP(?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, meta.getDescripcion());
            stmt.setDouble(2, meta.getMontoObjetivo());
            stmt.setDouble(3, meta.getAsignacionMensual());
            if (meta.getFechaEstimada() != null) {
                stmt.setDate(4, new java.sql.Date(meta.getFechaEstimada().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            stmt.setInt(5, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta al guardar la meta.";
    }
}
