package service;

import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class UsuarioService {
    private UsuarioDAO dao;

    public UsuarioService() {
        this.dao = new UsuarioDAO();
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

    public void exportarCSV(JTable tabla) {
        try {
            File file = new File("ReporteUsuarios.csv");
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
                TableModel model = tabla.getModel();
                
                // Write Header
                for (int i = 0; i < model.getColumnCount(); i++) {
                    pw.print(model.getColumnName(i));
                    if (i < model.getColumnCount() - 1) pw.print(",");
                }
                pw.println();

                // Write Data
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object val = model.getValueAt(i, j);
                        String strVal = (val != null) ? val.toString().replace(",", ";") : "";
                        pw.print(strVal);
                        if (j < model.getColumnCount() - 1) pw.print(",");
                    }
                    pw.println();
                }
            }

            JOptionPane.showMessageDialog(
                null,
                "CSV exportado correctamente en " + file.getAbsolutePath(),
                "Sistema",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error al exportar CSV",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void registrarLogAuditoria(int idUsuarioOwner, String accion, String detalle) {
        dao.registrarLogAuditoria(idUsuarioOwner, accion, detalle);
    }

    public Usuario validarLogin(String email, String password) throws Exception {
        return dao.validarLogin(email, password);
    }

    public String registrarUsuario(String nombre, String email, String password) throws Exception {
        return dao.registrarUsuario(nombre, email, password);
    }
}