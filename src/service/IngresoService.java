package service;

import dao.IngresoDAO;
import java.util.List;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;

public class IngresoService {
    private final IngresoDAO dao;

    public IngresoService() {
        this.dao = new IngresoDAO();
    }

    public List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, String fechaDesde, String fechaHasta, String categoria, String cuenta) {
        return dao.listarIngresos(idUsuario, tipoFlujo, estado, fechaDesde, fechaHasta, categoria, cuenta);
    }

    public String guardar(Ingreso ingreso) {
        if (ingreso.getMonto() <= 0) {
            return "El monto del ingreso debe ser mayor a 0.";
        }
        if (ingreso.getDescripcion() == null || ingreso.getDescripcion().trim().isEmpty()) {
            return "Debe ingresar una descripción o fuente de pago.";
        }
        if (ingreso.getFecha() == null) {
            return "Debe seleccionar una fecha de recepción válida.";
        }
        return dao.guardar(ingreso);
    }

    public String actualizar(Ingreso ingreso) {
        if (ingreso.getId() <= 0) {
            return "Identificador de ingreso inválido.";
        }
        if (ingreso.getMonto() <= 0) {
            return "El monto del ingreso debe ser mayor a 0.";
        }
        if (ingreso.getDescripcion() == null || ingreso.getDescripcion().trim().isEmpty()) {
            return "Debe ingresar una descripción o fuente de pago.";
        }
        return dao.actualizar(ingreso);
    }

    public String eliminar(int idIngreso, int idUsuario) {
        if (idIngreso <= 0) {
            return "Identificador de ingreso inválido.";
        }
        return dao.eliminar(idIngreso, idUsuario);
    }

    public Ingreso obtenerPorId(int idIngreso, int idUsuario) {
        return dao.obtenerPorId(idIngreso, idUsuario);
    }

    public IngresoResumen obtenerResumenKpi(int idUsuario, String fechaInicio, String fechaFin, String fechaInicioPrev, String fechaFinPrev) {
        return dao.obtenerResumenKpi(idUsuario, fechaInicio, fechaFin, fechaInicioPrev, fechaFinPrev);
    }

    public List<ComboItem> listarCategorias() {
        return dao.listarCategorias();
    }

    public List<ComboItem> listarCuentasDestino(int idUsuario) {
        return dao.listarCuentasDestino(idUsuario);
    }

    public void exportarCSV(javax.swing.JTable tabla, java.io.File file) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(file), java.nio.charset.StandardCharsets.UTF_8))) {
            javax.swing.table.TableModel model = tabla.getModel();
            int colCount = model.getColumnCount();

            // Encabezados (ignorar columna de acciones si existe)
            for (int col = 0; col < colCount; col++) {
                String colName = model.getColumnName(col);
                if (colName.equalsIgnoreCase("ACCIONES")) continue;
                pw.print("\"" + colName.replace("\"", "\"\"") + "\"");
                if (col < colCount - 2 || (col == colCount - 2 && !model.getColumnName(colCount - 1).equalsIgnoreCase("ACCIONES"))) {
                    pw.print(",");
                }
            }
            pw.println();

            // Filas
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int col = 0; col < colCount; col++) {
                    String colName = model.getColumnName(col);
                    if (colName.equalsIgnoreCase("ACCIONES")) continue;
                    Object val = model.getValueAt(r, col);
                    String strVal = (val != null) ? val.toString() : "";
                    pw.print("\"" + strVal.replace("\"", "\"\"") + "\"");
                    if (col < colCount - 2 || (col == colCount - 2 && !model.getColumnName(colCount - 1).equalsIgnoreCase("ACCIONES"))) {
                        pw.print(",");
                    }
                }
                pw.println();
            }

            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Reporte CSV exportado correctamente en:\n" + file.getAbsolutePath(),
                "Sistema",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Error al exportar a CSV: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void exportarExcel(javax.swing.JTable tabla, java.io.File file) {
        try (java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(file))) {
            // 1. [Content_Types].xml
            zos.putNextEntry(new java.util.zip.ZipEntry("[Content_Types].xml"));
            String contentTypes = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n"
                    + "  <Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>\n"
                    + "  <Default Extension=\"xml\" ContentType=\"application/xml\"/>\n"
                    + "  <Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>\n"
                    + "  <Override PartName=\"/xl/worksheets/sheet1.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>\n"
                    + "</Types>";
            zos.write(contentTypes.getBytes("UTF-8"));
            zos.closeEntry();

            // 2. _rels/.rels
            zos.putNextEntry(new java.util.zip.ZipEntry("_rels/.rels"));
            String rels = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n"
                    + "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/>\n"
                    + "</Relationships>";
            zos.write(rels.getBytes("UTF-8"));
            zos.closeEntry();

            // 3. xl/workbook.xml
            zos.putNextEntry(new java.util.zip.ZipEntry("xl/workbook.xml"));
            String workbook = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">\n"
                    + "  <sheets>\n"
                    + "    <sheet name=\"Ingresos\" sheetId=\"1\" r:id=\"rId1\"/>\n"
                    + "  </sheets>\n"
                    + "</workbook>";
            zos.write(workbook.getBytes("UTF-8"));
            zos.closeEntry();

            // 4. xl/_rels/workbook.xml.rels
            zos.putNextEntry(new java.util.zip.ZipEntry("xl/_rels/workbook.xml.rels"));
            String workbookRels = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                    + "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n"
                    + "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet1.xml\"/>\n"
                    + "</Relationships>";
            zos.write(workbookRels.getBytes("UTF-8"));
            zos.closeEntry();

            // 5. xl/worksheets/sheet1.xml
            zos.putNextEntry(new java.util.zip.ZipEntry("xl/worksheets/sheet1.xml"));
            StringBuilder sheetData = new StringBuilder();
            sheetData.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            sheetData.append("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">\n");
            sheetData.append("  <sheetData>\n");

            javax.swing.table.TableModel model = tabla.getModel();
            int colCount = model.getColumnCount();

            // Encabezados
            sheetData.append("    <row r=\"1\">\n");
            int excelCol = 1;
            for (int col = 0; col < colCount; col++) {
                String colName = model.getColumnName(col);
                if (colName.equalsIgnoreCase("ACCIONES")) continue;
                String colLetter = getColumnLetter(excelCol++);
                String cellRef = colLetter + "1";
                sheetData.append("      <c r=\"").append(cellRef).append("\" t=\"inlineStr\"><is><t>").append(escapeXml(colName)).append("</t></is></c>\n");
            }
            sheetData.append("    </row>\n");

            // Filas
            for (int rIdx = 0; rIdx < model.getRowCount(); rIdx++) {
                int excelRow = rIdx + 2;
                sheetData.append("    <row r=\"").append(excelRow).append("\">\n");
                excelCol = 1;
                for (int col = 0; col < colCount; col++) {
                    String colName = model.getColumnName(col);
                    if (colName.equalsIgnoreCase("ACCIONES")) continue;
                    String colLetter = getColumnLetter(excelCol++);
                    String cellRef = colLetter + excelRow;
                    Object cellVal = model.getValueAt(rIdx, col);
                    String val = (cellVal != null) ? escapeXml(cellVal.toString()) : "";
                    sheetData.append("      <c r=\"").append(cellRef).append("\" t=\"inlineStr\"><is><t>").append(val).append("</t></is></c>\n");
                }
                sheetData.append("    </row>\n");
            }

            sheetData.append("  </sheetData>\n");
            sheetData.append("</worksheet>");
            zos.write(sheetData.toString().getBytes("UTF-8"));
            zos.closeEntry();

            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Reporte Excel exportado correctamente en:\n" + file.getAbsolutePath(),
                "Sistema",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Error al exportar a Excel: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String getColumnLetter(int colNum) {
        StringBuilder colLetter = new StringBuilder();
        while (colNum > 0) {
            int rem = (colNum - 1) % 26;
            colLetter.insert(0, (char) (rem + 'A'));
            colNum = (colNum - 1) / 26;
        }
        return colLetter.toString();
    }

    private String escapeXml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&apos;");
    }
}
