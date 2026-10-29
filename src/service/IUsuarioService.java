package service;

import java.util.List;
import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;

public interface IUsuarioService {
    Usuario obtenerUsuario(int id);
    List<UsuarioCompartido> listarUsuariosCompartidos(int idUsuarioOwner);
    String invitarUsuario(UsuarioCompartido uc);
    String actualizarPermiso(int idCompartido, String permiso);
    String revocarAcceso(int idCompartido);
    UsuariosResumen obtenerResumen(int idUsuarioOwner);
    Usuario validarLogin(String email, String password) throws Exception;
    String registrarUsuario(String nombre, String email, String password) throws Exception;
    String registrarUsuario(String nombre, String apellidos, String email, String password) throws Exception;
}
