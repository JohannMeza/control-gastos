package service;

import dao.DeudaDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.ComboItem;
import model.Deuda;
import model.AbonoDeuda;
import model.DeudaResumen;

public class DeudaService {
    private DeudaDAO dao = new DeudaDAO();

    public List<ComboItem> listarDeudasCombo(int idUsuario) {
        return dao.listarDeudasCombo(idUsuario);
    }

    public List<Deuda> listarDeudas(int idUsuario) {
        return dao.listarDeudas(idUsuario);
    }

    public Deuda obtenerPorId(int idDeuda) {
        return dao.obtenerPorId(idDeuda);
    }

    public String guardar(Deuda deuda) {
        return dao.guardar(deuda);
    }

    public String actualizar(Deuda deuda) {
        return dao.actualizar(deuda);
    }

    public String eliminar(int idDeuda) {
        return dao.eliminar(idDeuda);
    }

    public String registrarAbono(AbonoDeuda abono) {
        return dao.registrarAbono(abono);
    }

    public DeudaResumen obtenerResumen(int idUsuario) {
        return dao.obtenerResumen(idUsuario);
    }

    public List<AbonoDeuda> listarHistorialAbonos(int idUsuario) {
        return dao.listarHistorialAbonos(idUsuario);
    }

    public void exportarCSV(JTable tabla) {
        try {
            File file = new File("ReporteDeudas.csv");
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
