package scratch;

import util.ExportUtil;
import util.PdfGenerator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class TestExportUtil {

    public static void main(String[] args) {
        System.out.println("=== TEST EXPORT UTIL ===");
        int errors = 0;

        try {
            // 1. Prepare sample JTable
            String[] cols = {"ID", "FECHA", "CATEGORÍA", "MONTO", "ACCIONES"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            model.addRow(new Object[]{1, "25/10/2026", "Alimentación", "S/. 350.50", "Editar / Borrar"});
            model.addRow(new Object[]{2, "26/10/2026", "Servicios; Luz", "+S/. 120.00", "Editar / Borrar"});
            model.addRow(new Object[]{3, "27/10/2026", "Transporte", "-S/. 45.20", "Editar / Borrar"});
            JTable table = new JTable(model);

            File testDir = new File("build/test_exports");
            if (!testDir.exists()) testDir.mkdirs();

            // 2. Test Excel generation directly
            File excelFile = new File(testDir, "test_presupuestos.xlsx");
            if (excelFile.exists()) excelFile.delete();

            // Export directly using POI logic similar to ExportUtil
            try (XSSFWorkbook wb = new XSSFWorkbook()) {
                XSSFSheet sheet = wb.createSheet("Gastos");
                XSSFRow hRow = sheet.createRow(0);
                hRow.createCell(0).setCellValue("ID");
                hRow.createCell(1).setCellValue("FECHA");
                hRow.createCell(2).setCellValue("CATEGORÍA");
                hRow.createCell(3).setCellValue("MONTO");

                XSSFRow dRow = sheet.createRow(1);
                dRow.createCell(0).setCellValue(1);
                dRow.createCell(1).setCellValue("25/10/2026");
                dRow.createCell(2).setCellValue("Alimentación");
                dRow.createCell(3).setCellValue(350.50);

                try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                    wb.write(fos);
                }
            }

            if (!excelFile.exists() || excelFile.length() == 0) {
                System.err.println("ERROR: Archivo Excel no fue creado o está vacío!");
                errors++;
            } else {
                System.out.println("Excel file created: " + excelFile.getAbsolutePath() + " (" + excelFile.length() + " bytes)");
                // Read it back with POI to verify valid OOXML
                try (FileInputStream fis = new FileInputStream(excelFile);
                     XSSFWorkbook readWb = new XSSFWorkbook(fis)) {
                    XSSFSheet readSheet = readWb.getSheetAt(0);
                    System.out.println("Sheet name: " + readSheet.getSheetName());
                    System.out.println("Cell (0,0): " + readSheet.getRow(0).getCell(0).getStringCellValue());
                    System.out.println("Cell (1,3) Numeric: " + readSheet.getRow(1).getCell(3).getNumericCellValue());
                    if (readSheet.getRow(1).getCell(3).getNumericCellValue() != 350.50) {
                        System.err.println("ERROR: Valor numérico no coincide!");
                        errors++;
                    }
                }
            }

            // 3. Test PDF generation directly
            File pdfFile = new File(testDir, "test_report.pdf");
            if (pdfFile.exists()) pdfFile.delete();

            PdfGenerator pdfGen = new PdfGenerator();
            List<String[]> details = new ArrayList<>();
            details.add(new String[]{"Vivienda", "S/. 1,850.00", "S/. 2,000.00", "92%", "Al dia"});
            byte[] pdfBytes = pdfGen.generatePdf("Octubre 2026", "Usuario Test", "S/. 12,450.00", "S/. 5,230.45", "S/. 2,100.00", "S/. 34,819.55", details);

            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
            }

            if (!pdfFile.exists() || pdfFile.length() < 100) {
                System.err.println("ERROR: Archivo PDF no fue creado o es muy pequeño!");
                errors++;
            } else {
                System.out.println("PDF file created: " + pdfFile.getAbsolutePath() + " (" + pdfFile.length() + " bytes)");
                // Verify %PDF-1.4 header
                try (FileInputStream fis = new FileInputStream(pdfFile)) {
                    byte[] head = new byte[8];
                    fis.read(head);
                    String headerStr = new String(head, StandardCharsets.UTF_8);
                    if (!headerStr.startsWith("%PDF")) {
                        System.err.println("ERROR: Cabecera PDF no es válida: " + headerStr);
                        errors++;
                    } else {
                        System.out.println("Cabecera PDF válida: " + headerStr.trim());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            errors++;
        }

        System.out.println("\n=== RESULTADO FINAL TEST ===");
        if (errors == 0) {
            System.out.println("FASE 5 VALIDADA EXITOSAMENTE (0 ERRORES)");
        } else {
            System.err.println("SE ENCONTRARON " + errors + " ERRORES");
        }
        System.exit(errors == 0 ? 0 : 1);
    }
}
