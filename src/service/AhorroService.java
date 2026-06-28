package service;

import dao.AhorroDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.ComboItem;
import model.Ahorro;
import model.MetaAhorro;
import model.MetasResumen;

public class AhorroService {
    private AhorroDAO dao = new AhorroDAO();

    public List<ComboItem> listarMetas(int idUsuario) {
        return dao.listarMetas(idUsuario);
    }

    public List<Ahorro> listarAhorros(int idUsuario) {
        return dao.listarAhorros(idUsuario);
    }

    public Ahorro obtenerPorId(int idAhorro) {
        return dao.obtenerPorId(idAhorro);
    }

    public String guardar(Ahorro ahorro) {
        return dao.guardar(ahorro);
    }

    public String actualizar(Ahorro ahorro) {
        return dao.actualizar(ahorro);
    }

    public String eliminar(int idAhorro) {
        return dao.eliminar(idAhorro);
    }

    public MetasResumen obtenerResumen(int idUsuario) {
        return dao.obtenerResumen(idUsuario);
    }

    public List<MetaAhorro> obtenerMetasDetalle(int idUsuario) {
        return dao.obtenerMetasDetalle(idUsuario);
    }

    public String guardarMeta(MetaAhorro meta, int idUsuario) {
        return dao.guardarMeta(meta, idUsuario);
    }

    public void exportarCSV(JTable tabla) {
        try {
            File file = new File("ReporteAhorros.csv");
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
}
