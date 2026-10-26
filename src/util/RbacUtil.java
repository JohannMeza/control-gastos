package util;

import model.Session;
import model.Usuario;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Utilitario centralizado de Control de Acceso basado en Roles (RBAC).
 * Roles soportados:
 * - ADMIN: Acceso total a todas las operaciones y configuraciones.
 * - EDITOR: Puede registrar, editar y consultar transacciones y presupuestos.
 * - LECTOR: Solo lectura; no puede registrar, modificar ni eliminar datos.
 */
public final class RbacUtil {

    private RbacUtil() {}

    /**
     * Retorna el rol del usuario actualmente autenticado en la sesión.
     */
    public static String getRolActivo() {
        Usuario u = Session.getInstance().getUsuarioActivo();
        return (u != null && u.getRol() != null) ? u.getRol().trim().toUpperCase() : "LECTOR";
    }

    /**
     * Valida si el usuario actual tiene permisos de edición (ADMIN o EDITOR).
     */
    public static boolean puedeEditar() {
        Usuario u = Session.getInstance().getUsuarioActivo();
        if (u == null) return false;
        return u.puedeEditar();
    }

    /**
     * Valida si el usuario actual es ADMINISTRADOR.
     */
    public static boolean esAdmin() {
        Usuario u = Session.getInstance().getUsuarioActivo();
        if (u == null) return false;
        return u.esAdmin();
    }

    /**
     * Valida el permiso de escritura antes de ejecutar una acción transaccional.
     * Si el usuario tiene rol LECTOR, muestra una alerta informativa y retorna false.
     */
    public static boolean verificarPermisoEscritura(Component parent, String accion) {
        if (!puedeEditar()) {
            JOptionPane.showMessageDialog(
                parent,
                "Acción restringida: Su cuenta tiene rol LECTOR (Solo lectura).\n" +
                "No cuenta con permisos para " + accion + ".\n\n" +
                "Para solicitar permisos de edición, contacte al Administrador.",
                "Control de Acceso (RBAC)",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    /**
     * Aplica la política de rol LECTOR sobre botones de edición en una vista:
     * Si el usuario es LECTOR, deshabilita y coloca tooltip informativo.
     */
    public static void aplicarRestriccionBotones(JButton... botones) {
        if (!puedeEditar()) {
            for (JButton b : botones) {
                if (b != null) {
                    b.setEnabled(false);
                    b.setToolTipText("Acción no disponible para rol LECTOR (Solo lectura)");
                }
            }
        }
    }
}
