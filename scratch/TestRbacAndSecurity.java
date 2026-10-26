package scratch;

import model.Session;
import model.Usuario;
import util.RbacUtil;
import dao.UsuarioDAO;

import javax.swing.JButton;

public class TestRbacAndSecurity {

    public static void main(String[] args) {
        System.out.println("=== TEST SEGURIDAD Y CONTROL DE ROLES RBAC ===");
        int errors = 0;

        try {
            // 1. Probar modelo Usuario y helpers
            Usuario uAdmin = new Usuario(1, "Carlos", "Perez", "carlos@finandes.pe", "ADMIN");
            if (!uAdmin.esAdmin() || !uAdmin.puedeEditar() || uAdmin.esLector()) {
                System.err.println("ERROR: Evaluación de rol ADMIN incorrecta");
                errors++;
            }
            if (!"Carlos Perez".equals(uAdmin.getNombreCompleto())) {
                System.err.println("ERROR: Nombre completo incorrecto: " + uAdmin.getNombreCompleto());
                errors++;
            }

            Usuario uLector = new Usuario(2, "Maria", "Gomez", "maria@finandes.pe", "LECTOR");
            if (uLector.esAdmin() || uLector.puedeEditar() || !uLector.esLector()) {
                System.err.println("ERROR: Evaluación de rol LECTOR incorrecta");
                errors++;
            }

            // 2. Probar RbacUtil con Sesión activa de LECTOR
            Session.getInstance().setUsuarioActivo(uLector);
            if (RbacUtil.puedeEditar()) {
                System.err.println("ERROR: RbacUtil no debe permitir editar a rol LECTOR!");
                errors++;
            }

            JButton btnTest = new JButton("Guardar Transacción");
            btnTest.setEnabled(true);
            RbacUtil.aplicarRestriccionBotones(btnTest);
            if (btnTest.isEnabled()) {
                System.err.println("ERROR: El botón de prueba debió ser deshabilitado para rol LECTOR!");
                errors++;
            } else {
                System.out.println("Restricción de botones para rol LECTOR: OK (botón deshabilitado con tooltip: '" + btnTest.getToolTipText() + "')");
            }

            // 3. Probar RbacUtil con Sesión activa de EDITOR
            Usuario uEditor = new Usuario(3, "Roberto", "Silva", "roberto@finandes.pe", "EDITOR");
            Session.getInstance().setUsuarioActivo(uEditor);
            if (!RbacUtil.puedeEditar()) {
                System.err.println("ERROR: RbacUtil debe permitir editar a rol EDITOR!");
                errors++;
            }

            JButton btnEditorTest = new JButton("Guardar Gasto");
            btnEditorTest.setEnabled(true);
            RbacUtil.aplicarRestriccionBotones(btnEditorTest);
            if (!btnEditorTest.isEnabled()) {
                System.err.println("ERROR: El botón no debió ser deshabilitado para rol EDITOR!");
                errors++;
            } else {
                System.out.println("Permisos de escritura para rol EDITOR: OK");
            }

            // 4. Probar consulta a Base de Datos de usuario existente y persistencia de apellidos
            UsuarioDAO dao = new UsuarioDAO();
            Usuario uDb = dao.obtenerUsuario(1);
            if (uDb != null) {
                System.out.println("Usuario en BD: ID=" + uDb.getId() + ", Nombre=" + uDb.getNombre() + ", Apellidos=" + uDb.getApellidos() + ", Rol=" + uDb.getRol());
            } else {
                System.err.println("ERROR: No se pudo obtener el usuario ID 1 de la BD!");
                errors++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            errors++;
        }

        System.out.println("\n=== RESULTADO FINAL TEST ===");
        if (errors == 0) {
            System.out.println("SEGURIDAD Y CONTROL DE ROLES RBAC VALIDADO EXITOSAMENTE (0 ERRORES)");
        } else {
            System.err.println("SE ENCONTRARON " + errors + " ERRORES");
        }
        System.exit(errors == 0 ? 0 : 1);
    }
}
