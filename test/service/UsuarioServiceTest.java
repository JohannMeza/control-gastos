package service;

import dao.IUsuarioDAO;
import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para UsuarioService (TDD).
 * Valida el registro de usuarios, invitaciones y gestión de permisos en memoria.
 */
public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private MockUsuarioDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockUsuarioDAO();
        usuarioService = new UsuarioService(mockDao);
    }

    @Test
    public void testRegistrarUsuarioExitoso() throws Exception {
        String res = usuarioService.registrarUsuario("Juan", "Perez", "juan@test.com", "Password123!");
        assertEquals("USUARIO_REGISTRADO_OK", res);
    }

    @Test
    public void testInvitarUsuarioExitoso() {
        UsuarioCompartido uc = new UsuarioCompartido();
        uc.setEmailInvitado("colab@test.com");
        uc.setPermiso("LECTURA");
        String res = usuarioService.invitarUsuario(uc);
        assertEquals("INVITACION_ENVIADA_OK", res);
    }

    @Test
    public void testActualizarPermisoExitoso() {
        String res = usuarioService.actualizarPermiso(5, "ESCRITURA");
        assertEquals("PERMISO_ACTUALIZADO_OK", res);
    }

    @Test
    public void testRevocarAccesoExitoso() {
        String res = usuarioService.revocarAcceso(5);
        assertEquals("ACCESO_REVOCADO_OK", res);
    }

    @Test
    public void testValidarLoginExitoso() throws Exception {
        Usuario u = usuarioService.validarLogin("juan@test.com", "Password123!");
        assertNotNull(u);
        assertEquals("juan@test.com", u.getEmail());
    }

    private static class MockUsuarioDAO implements IUsuarioDAO {
        @Override
        public Usuario obtenerUsuario(int id) {
            Usuario u = new Usuario();
            u.setId(id);
            u.setEmail("juan@test.com");
            return u;
        }

        @Override
        public List<UsuarioCompartido> listarUsuariosCompartidos(int idUsuarioOwner) {
            return new ArrayList<>();
        }

        @Override
        public String invitarUsuario(UsuarioCompartido uc) {
            return "INVITACION_ENVIADA_OK";
        }

        @Override
        public String actualizarPermiso(int idCompartido, String permiso) {
            return "PERMISO_ACTUALIZADO_OK";
        }

        @Override
        public String revocarAcceso(int idCompartido) {
            return "ACCESO_REVOCADO_OK";
        }

        @Override
        public UsuariosResumen obtenerResumen(int idUsuarioOwner) {
            return new UsuariosResumen();
        }

        @Override
        public Usuario validarLogin(String email, String password) throws Exception {
            Usuario u = new Usuario();
            u.setId(1);
            u.setEmail(email);
            return u;
        }

        @Override
        public String registrarUsuario(String nombre, String email, String password) throws Exception {
            return "USUARIO_REGISTRADO_OK";
        }

        @Override
        public String registrarUsuario(String nombre, String apellidos, String email, String password) throws Exception {
            return "USUARIO_REGISTRADO_OK";
        }
    }
}
