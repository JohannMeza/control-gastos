package dao;

import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;
import util.XmlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public Usuario obtenerUsuario(int id) {
        Usuario usuario = null;
        String sql = "{CALL CG_ObtenerDatosUsuarioPorId_SP(?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setRol(rs.getString("rol"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         
         return usuario;
    }

    public List<UsuarioCompartido> listarUsuariosCompartidos(int idUsuarioOwner) {
        List<UsuarioCompartido> lista = new ArrayList<>();
        String sql = "{CALL CG_ListaUsuariosCompartidos_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuarioOwner);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UsuarioCompartido uc = new UsuarioCompartido();
                    uc.setId(rs.getInt("idCompartido"));
                    uc.setUsuarioOwnerId(rs.getInt("idUsuarioOwner"));
                    uc.setUsuarioInvitadoId(rs.getInt("idUsuarioInvitado"));
                    uc.setPermiso(rs.getString("permiso"));
                    uc.setNombreInvitado(rs.getString("nombreInvitado"));
                    uc.setEmailInvitado(rs.getString("emailInvitado"));
                    
                    // We map the database active/pending calculation to this local field
                    uc.setEstado(rs.getString("estado"));
                    lista.add(uc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String invitarUsuario(UsuarioCompartido uc) {
        String sql = "{CALL CG_GuardaInvitacionUsuario_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            String xml = XmlUtil.convertirAXml(uc);
            stmt.setString(1, xml);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta del registro de invitación.";
    }

    public String actualizarPermiso(int idCompartido, String permiso) {
        String sql = "{CALL CG_ActualizaPermisoUsuario_SP(?, ?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idCompartido);
            stmt.setString(2, permiso);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta de la actualización de permiso.";
    }

    public String revocarAcceso(int idCompartido) {
        String sql = "{CALL CG_EliminarUsuarioCompartido_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idCompartido);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No se recibió respuesta de la revocación de acceso.";
    }

    public UsuariosResumen obtenerResumen(int idUsuarioOwner) {
        UsuariosResumen r = new UsuariosResumen();
        String sql = "{CALL CG_ObtenerResumenUsuarios_SP(?)}";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idUsuarioOwner);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    r.setAsientosUsados(rs.getInt("asientosUsados"));
                    r.setAsientosTotales(rs.getInt("asientosTotales"));
                    r.setUltimaSincronizacion(rs.getString("ultimaSincronizacion"));
                    r.setMfaActivoStatus(rs.getString("mfaActivoStatus"));
                    r.setLogsAuditoriaCount(rs.getString("logsAuditoriaCount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public void registrarLogAuditoria(int idUsuarioOwner, String accion, String detalle) {
        String sql = "INSERT INTO FINANZAS.LOGS_AUDITORIA (idUsuarioOwner, cAccion, cDetalle, dFecha) VALUES (?, ?, ?, GETDATE())";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuarioOwner);
            stmt.setString(2, accion);
            stmt.setString(3, detalle);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario validarLogin(String email, String password) throws Exception {
        Usuario usuario = null;
        String sql = "{CALL CG_ValidarLogin_SP(?, ?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        }
        return usuario;
    }

    public String registrarUsuario(String nombre, String email, String password) throws Exception {
        String sql = "{CALL CG_RegistrarUsuario_SP(?, ?, ?)}";
        
        try (
            Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(sql)
        ) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cMsje");
                }
            }
        }
        return "No se recibió respuesta del registro de usuario.";
    }
}