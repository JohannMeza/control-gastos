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

    public double obtenerAhorradoMesPasado(int idUsuario) {
        return dao.obtenerAhorradoMesPasado(idUsuario);
    }

    public List<MetaAhorro> obtenerMetasDetalle(int idUsuario) {
        return dao.obtenerMetasDetalle(idUsuario);
    }

    public String guardarMeta(MetaAhorro meta, int idUsuario) {
        return dao.guardarMeta(meta, idUsuario);
    }

    public void exportarExcel(JTable tabla, File file) {
        try {
            try (java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(new FileOutputStream(file))) {
                // 1. [Content_Types].xml
                zos.putNextEntry(new java.util.zip.ZipEntry("[Content_Types].xml"));
                String contentTypes = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n" +
                    "  <Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>\n" +
                    "  <Default Extension=\"xml\" ContentType=\"application/xml\"/>\n" +
                    "  <Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>\n" +
                    "  <Override PartName=\"/xl/worksheets/sheet1.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>\n" +
                    "</Types>";
                zos.write(contentTypes.getBytes("UTF-8"));
                zos.closeEntry();

                // 2. _rels/.rels
                zos.putNextEntry(new java.util.zip.ZipEntry("_rels/.rels"));
                String rels = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                    "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/>\n" +
                    "</Relationships>";
                zos.write(rels.getBytes("UTF-8"));
                zos.closeEntry();

                // 3. xl/workbook.xml
                zos.putNextEntry(new java.util.zip.ZipEntry("xl/workbook.xml"));
                String workbook = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">\n" +
                    "  <sheets>\n" +
                    "    <sheet name=\"Reporte\" sheetId=\"1\" r:id=\"rId1\"/>\n" +
                    "  </sheets>\n" +
                    "</workbook>";
                zos.write(workbook.getBytes("UTF-8"));
                zos.closeEntry();

                // 4. xl/_rels/workbook.xml.rels
                zos.putNextEntry(new java.util.zip.ZipEntry("xl/_rels/workbook.xml.rels"));
                String workbookRels = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                    "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet1.xml\"/>\n" +
                    "</Relationships>";
                zos.write(workbookRels.getBytes("UTF-8"));
                zos.closeEntry();

                // 5. xl/worksheets/sheet1.xml
                zos.putNextEntry(new java.util.zip.ZipEntry("xl/worksheets/sheet1.xml"));
                StringBuilder sheetData = new StringBuilder();
                sheetData.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
                sheetData.append("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">\n");
                sheetData.append("  <sheetData>\n");

                TableModel model = tabla.getModel();
                
                // Header Row (Row 1)
                sheetData.append("    <row r=\"1\">\n");
                for (int col = 0; col < model.getColumnCount(); col++) {
                    String colLetter = getColumnLetter(col + 1);
                    String cellRef = colLetter + "1";
                    String val = escapeXml(model.getColumnName(col));
                    sheetData.append("      <c r=\"").append(cellRef).append("\" t=\"inlineStr\"><is><t>").append(val).append("</t></is></c>\n");
                }
                sheetData.append("    </row>\n");

                // Data Rows (Row 2 to N)
                for (int rIdx = 0; rIdx < model.getRowCount(); rIdx++) {
                    int excelRow = rIdx + 2;
                    sheetData.append("    <row r=\"").append(excelRow).append("\">\n");
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        String colLetter = getColumnLetter(col + 1);
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
            }

            JOptionPane.showMessageDialog(
                null,
                "Reporte Excel exportado correctamente en:\n" + file.getAbsolutePath(),
                "Sistema",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error al exportar a Excel",
                "Error",
                JOptionPane.ERROR_MESSAGE
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
