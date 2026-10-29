package service;

import dao.IUsuarioDAO;
import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;
import java.util.List;

public class UsuarioService implements IUsuarioService {
    private final IUsuarioDAO dao;

    public UsuarioService() {
        this(new UsuarioDAO());
    }

    public UsuarioService(IUsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario obtenerUsuario(int id) {
        return dao.obtenerUsuario(id);
    }

    public List<UsuarioCompartido> listarUsuariosCompartidos(int idUsuarioOwner) {
        return dao.listarUsuariosCompartidos(idUsuarioOwner);
    }

    public String invitarUsuario(UsuarioCompartido uc) {
        return dao.invitarUsuario(uc);
    }

    public String actualizarPermiso(int idCompartido, String permiso) {
        return dao.actualizarPermiso(idCompartido, permiso);
    }

    public String revocarAcceso(int idCompartido) {
        return dao.revocarAcceso(idCompartido);
    }

    public UsuariosResumen obtenerResumen(int idUsuarioOwner) {
        return dao.obtenerResumen(idUsuarioOwner);
    }

    public Usuario validarLogin(String email, String password) throws Exception {
        return dao.validarLogin(email, password);
    }

    public String registrarUsuario(String nombre, String email, String password) throws Exception {
        return dao.registrarUsuario(nombre, email, password);
    }

    public String registrarUsuario(String nombre, String apellidos, String email, String password) throws Exception {
        return dao.registrarUsuario(nombre, apellidos, email, password);
    }
}