package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}