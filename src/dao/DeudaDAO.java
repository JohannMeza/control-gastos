package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Deuda;
import model.AbonoDeuda;
import model.DeudaResumen;
import util.XmlUtil;

public class DeudaDAO {

    public List<ComboItem> listarDeudasCombo(int idUsuario) {
        List<ComboItem> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaCboDeudasActivas_SP(?)}";

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

    public List<Deuda> listarDeudas(int idUsuario) {
        List<Deuda> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaDeudasPorUsuario_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Deuda d = new Deuda();
                    d.setIdDeuda(rs.getInt("idDeuda"));
                    d.setAcreedor(rs.getString("acreedor"));
                    d.setMontoTotal(rs.getDouble("montoTotal"));
                    d.setTasaInteres(rs.getDouble("tasaInteres"));
                    d.setCuotasTotales(rs.getInt("cuotasTotales"));
                    d.setCuotasPagadas(rs.getInt("cuotasPagadas"));
                    d.setFechaInicio(rs.getDate("fechaInicio"));
                    d.setFechaInicioFormat(rs.getString("fechaInicioFormat"));
                    d.setMontoPagado(rs.getDouble("montoPagado"));
                    d.setMontoRestante(rs.getDouble("montoRestante"));
                    d.setEstado(rs.getString("estado"));
                    d.setIdUsuario(rs.getInt("idUsuario"));
                    lista.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Deuda obtenerPorId(int idDeuda) {
        Deuda d = null;
        String sql = "{CALL CG_ObtenerDeudaPorId_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idDeuda);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    d = new Deuda();
                    d.setIdDeuda(rs.getInt("idDeuda"));
                    d.setAcreedor(rs.getString("acreedor"));
                    d.setMontoTotal(rs.getDouble("montoTotal"));
                    d.setTasaInteres(rs.getDouble("tasaInteres"));
                    d.setCuotasTotales(rs.getInt("cuotasTotales"));
                    d.setCuotasPagadas(rs.getInt("cuotasPagadas"));
                    d.setFechaInicio(rs.getDate("fechaInicio"));
                    d.setIdUsuario(rs.getInt("idUsuario"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public String guardar(Deuda deuda) {
        String sql = "{CALL CG_GuardaDeudaIngresada_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(deuda);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta de guardado";
    }

    public String actualizar(Deuda deuda) {
        String sql = "{CALL CG_ActualizaDeudaIngresada_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(deuda);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta de actualización";
    }

    public String eliminar(int idDeuda) {
        String sql = "{CALL CG_EliminarDeudaPorId_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idDeuda);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta de eliminación";
    }

    public String registrarAbono(AbonoDeuda abono) {
        String sql = "{CALL CG_GuardaAbonoIngresado_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(abono);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta del registro de abono";
    }

    public DeudaResumen obtenerResumen(int idUsuario) {
        DeudaResumen r = new DeudaResumen();
        String sql = "{CALL CG_ObtenerDeudaResumen_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    r.setTotalDeuda(rs.getDouble("totalDeuda"));
                    r.setProximoVencimiento(rs.getString("proximoVencimiento"));
                    r.setTotalPagadoEsteMes(rs.getDouble("totalPagadoEsteMes"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public List<AbonoDeuda> listarHistorialAbonos(int idUsuario) {
        List<AbonoDeuda> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaHistorialAbonos_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AbonoDeuda a = new AbonoDeuda();
                    a.setFechaPago(rs.getDate("fecha"));
                    a.setFechaPagoFormat(rs.getString("fechaFormat"));
                    a.setNombreDeuda(rs.getString("deuda"));
                    a.setMontoAbono(rs.getDouble("montoPagado"));
                    a.setSaldoRestante(rs.getDouble("saldoRestante"));
                    lista.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
