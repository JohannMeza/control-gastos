/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.GastoDAO;
import java.io.File;
import java.util.Date;
import java.util.List;
import model.ComboItem;
import model.Gasto;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class GastoService {
    private GastoDAO dao = new GastoDAO();
    
    public void guardar(Gasto gasto) {
        dao.guardar(gasto);
    }
    
    public String actualizar(Gasto gasto) {
        return dao.actualizar(gasto);
    }
    
    public String eliminar(int idGasto) {
        return dao.eliminar(idGasto);
    }
    
    public List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario) {
        return dao.listar(fechaDesde, fechaHasta, idUsuario);
    }
    
    public List<ComboItem> listarCategorias() {
        return dao.listarCategorias();
    }
    
    public List<ComboItem> listarTipoGastos() {
        return dao.listarTipoGastos();
    }
    
    public List<ComboItem> listarUsuarios() {
        return dao.listarUsuarios();
    }
    
    public Gasto obtenerPorId(int idGasto) {
        return dao.obtenerPorId(idGasto);
    }
    
    public void exportarExcel(JTable tabla) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Gastos");

            TableModel model = tabla.getModel();

            // CABECERAS
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < model.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(
                    model.getColumnName(i)
                );
            }

            // DATOS
            for (int i = 0; i < model.getRowCount(); i++) {

                Row row = sheet.createRow(i + 1);

                for (int j = 0; j < model.getColumnCount(); j++) {

                    Object value = model.getValueAt(i, j);

                    row.createCell(j).setCellValue(
                        value != null ? value.toString() : ""
                    );
                }
            }

            // AUTO AJUSTAR COLUMNAS
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream out = new FileOutputStream("ReporteGastos.xlsx");

            workbook.write(out);

            out.close();
            workbook.close();
            File archivo = new File("ReporteGastos.xlsx");

            JOptionPane.showMessageDialog(
                null,
                "Excel exportado correctamente"
            );

        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                null,
                "Error al exportar Excel"
            );
        }
    }
}
