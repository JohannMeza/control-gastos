package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
    private UsuarioDAO dao;

    public UsuarioService() {
        this.dao = new UsuarioDAO();
    }

    public Usuario obtenerUsuario(int id) {
        return dao.obtenerUsuario(id);
    }
}