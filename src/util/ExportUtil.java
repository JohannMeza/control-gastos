package util;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utilitario centralizado del Sistema Finandes para exportación de tablas
 * a formatos CSV, Excel (.xlsx con Apache POI) y PDF imprimible.
 */
public final class ExportUtil {

    private static final Color VERDE_FINANDES = new Color(66, 173, 77);   // #42AD4D
    private static final Color BORDE_CARD = new Color(226, 232, 240);       // #E2E8F0
    private static final Color TEXTO_TITULO = new Color(15, 23, 42);        // #0F172A

    private ExportUtil() {}

    /**
     * Genera un nombre de archivo por defecto con marca de tiempo.
     */
    public static String generarNombreArchivo(String prefijo, String extension) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return prefijo + "_" + sdf.format(new Date()) + "." + extension;
    }

    /**
     * Abre un JFileChooser nativo configurado para guardar archivos.
     */
    public static File seleccionarDestino(Component parent, String nombreSugerido, String extension, String descripcion) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar Reporte - Sistema Finandes");
        chooser.setSelectedFile(new File(nombreSugerido));
        chooser.setFileFilter(new FileNameExtensionFilter(descripcion, extension));

        int res = chooser.showSaveDialog(parent);
        if (res == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (!f.getName().toLowerCase().endsWith("." + extension.toLowerCase())) {
                f = new File(f.getParentFile(), f.getName() + "." + extension);
            }
            if (f.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                    parent,
                    "El archivo ya existe:\n" + f.getName() + "\n\n¿Desea sobreescribirlo?",
                    "Confirmar Sobreescritura",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return null;
                }
            }
            return f;
        }
        return null;
    }

    private static boolean esColumnaExportable(String nombreColumna) {
        if (nombreColumna == null) return false;
        String upper = nombreColumna.trim().toUpperCase();
        return !upper.isEmpty() && !upper.contains("ACCION") && !upper.contains("ACTION");
    }

    /**
     * Exporta los datos de una JTable a un archivo CSV con codificación UTF-8 con BOM
     * para garantizar compatibilidad con acentos y el símbolo 'S/.'.
     */
    public static boolean exportarTablaCSV(Component parent, JTable table, String prefijoNombre) {
        if (table == null || table.getModel() == null) {
            JOptionPane.showMessageDialog(parent, "No hay datos para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        File destino = seleccionarDestino(parent, generarNombreArchivo(prefijoNombre, "csv"), "csv", "Archivos CSV (*.csv)");
        if (destino == null) return false;

        TableModel model = table.getModel();
        List<Integer> cols = new ArrayList<>();
        for (int c = 0; c < model.getColumnCount(); c++) {
            if (esColumnaExportable(model.getColumnName(c))) {
                cols.add(c);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(destino);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            // Escribir BOM UTF-8 (\uFEFF) para que Excel detecte tildes y caracteres especiales
            writer.write("\uFEFF");

            // Cabeceras
            for (int i = 0; i < cols.size(); i++) {
                if (i > 0) writer.write(";");
                writer.write(escaparCsv(model.getColumnName(cols.get(i))));
            }
            writer.newLine();

            // Filas
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int i = 0; i < cols.size(); i++) {
                    if (i > 0) writer.write(";");
                    Object val = model.getValueAt(r, cols.get(i));
                    writer.write(escaparCsv(val != null ? val.toString() : ""));
                }
                writer.newLine();
            }

            writer.flush();
            JOptionPane.showMessageDialog(
                parent,
                "Reporte CSV exportado exitosamente:\n" + destino.getAbsolutePath(),
                "Exportación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                parent,
                "Error al exportar archivo CSV:\n" + ex.getMessage(),
                "Error de Exportación",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private static String escaparCsv(String valor) {
        if (valor == null) return "";
        String v = valor.trim();
        if (v.contains(";") || v.contains("\"") || v.contains("\n") || v.contains("\r")) {
            v = "\"" + v.replace("\"", "\"\"") + "\"";
        }
        return v;
    }

    /**
     * Exporta los datos de una JTable a formato Excel (.xlsx) usando Apache POI,
     * aplicando el diseño corporativo de Finandes (#42AD4D, bordes finos, fuentes Segoe UI
     * y formato monetario numérico S/. #,##0.00).
     */
    public static boolean exportarTablaExcel(Component parent, JTable table, String tituloHoja, String prefijoNombre) {
        if (table == null || table.getModel() == null) {
            JOptionPane.showMessageDialog(parent, "No hay datos para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        File destino = seleccionarDestino(parent, generarNombreArchivo(prefijoNombre, "xlsx"), "xlsx", "Libro de Excel (*.xlsx)");
        if (destino == null) return false;

        TableModel model = table.getModel();
        List<Integer> cols = new ArrayList<>();
        for (int c = 0; c < model.getColumnCount(); c++) {
            if (esColumnaExportable(model.getColumnName(c))) {
                cols.add(c);
            }
        }

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(tituloHoja != null && !tituloHoja.isEmpty() ? tituloHoja : "Datos");
            sheet.setDisplayGridlines(true);

            DefaultIndexedColorMap colorMap = new DefaultIndexedColorMap();
            XSSFColor verdeFinandes = new XSSFColor(VERDE_FINANDES, colorMap);
            XSSFColor bordeColor = new XSSFColor(BORDE_CARD, colorMap);

            // Fuente y estilo de cabecera
            XSSFFont fontHeader = workbook.createFont();
            fontHeader.setFontName("Segoe UI");
            fontHeader.setFontHeightInPoints((short) 11);
            fontHeader.setBold(true);
            fontHeader.setColor(new XSSFColor(Color.WHITE, colorMap));

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(fontHeader);
            headerStyle.setFillForegroundColor(verdeFinandes);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            aplicarBordes(headerStyle, BorderStyle.THIN, bordeColor);

            // Fuentes de datos
            XSSFFont fontData = workbook.createFont();
            fontData.setFontName("Segoe UI");
            fontData.setFontHeightInPoints((short) 10);
            fontData.setColor(new XSSFColor(TEXTO_TITULO, colorMap));

            DataFormat df = workbook.createDataFormat();

            // Estilos de celda: Texto normal
            XSSFCellStyle cellText = workbook.createCellStyle();
            cellText.setFont(fontData);
            cellText.setAlignment(HorizontalAlignment.LEFT);
            cellText.setVerticalAlignment(VerticalAlignment.CENTER);
            aplicarBordes(cellText, BorderStyle.THIN, bordeColor);

            // Estilos de celda: Moneda (S/. #,##0.00)
            XSSFCellStyle cellMoney = workbook.createCellStyle();
            cellMoney.setFont(fontData);
            cellMoney.setAlignment(HorizontalAlignment.RIGHT);
            cellMoney.setVerticalAlignment(VerticalAlignment.CENTER);
            cellMoney.setDataFormat(df.getFormat("\"S/.\" #,##0.00"));
            aplicarBordes(cellMoney, BorderStyle.THIN, bordeColor);

            // Estilos de celda: Fecha centrada
            XSSFCellStyle cellCenter = workbook.createCellStyle();
            cellCenter.setFont(fontData);
            cellCenter.setAlignment(HorizontalAlignment.CENTER);
            cellCenter.setVerticalAlignment(VerticalAlignment.CENTER);
            aplicarBordes(cellCenter, BorderStyle.THIN, bordeColor);

            // Escribir fila de cabecera (Fila 0)
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(26);
            for (int i = 0; i < cols.size(); i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(cols.get(i)));
                cell.setCellStyle(headerStyle);
            }

            // Escribir filas de datos
            for (int r = 0; r < model.getRowCount(); r++) {
                XSSFRow row = sheet.createRow(r + 1);
                row.setHeightInPoints(20);

                for (int i = 0; i < cols.size(); i++) {
                    XSSFCell cell = row.createCell(i);
                    Object val = model.getValueAt(r, cols.get(i));

                    if (val == null) {
                        cell.setCellValue("");
                        cell.setCellStyle(cellText);
                        continue;
                    }

                    if (val instanceof Number) {
                        cell.setCellValue(((Number) val).doubleValue());
                        cell.setCellStyle(cellMoney);
                    } else {
                        String str = val.toString().trim();
                        // Detectar si representa un valor monetario
                        if (esMontoMonetario(str)) {
                            Double parsed = parsearMonto(str);
                            if (parsed != null) {
                                cell.setCellValue(parsed);
                                cell.setCellStyle(cellMoney);
                            } else {
                                cell.setCellValue(str);
                                cell.setCellStyle(cellText);
                            }
                        } else if (esFecha(str)) {
                            cell.setCellValue(str);
                            cell.setCellStyle(cellCenter);
                        } else {
                            cell.setCellValue(str);
                            cell.setCellStyle(cellText);
                        }
                    }
                }
            }

            // Ajustar ancho de columnas automáticamente con holgura
            for (int i = 0; i < cols.size(); i++) {
                sheet.autoSizeColumn(i);
                int currentWidth = sheet.getColumnWidth(i);
                sheet.setColumnWidth(i, Math.max(currentWidth + 1200, 3500));
            }

            // Guardar con buffer seguro
            try (FileOutputStream fos = new FileOutputStream(destino);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                workbook.write(bos);
                bos.flush();
            }

            JOptionPane.showMessageDialog(
                parent,
                "Reporte Excel exportado exitosamente:\n" + destino.getAbsolutePath(),
                "Exportación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                parent,
                "Error al exportar archivo Excel:\n" + ex.getMessage(),
                "Error de Exportación",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private static void aplicarBordes(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderBottom(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setTopBorderColor(color);
        style.setBottomBorderColor(color);
        style.setLeftBorderColor(color);
        style.setRightBorderColor(color);
    }

    private static boolean esMontoMonetario(String str) {
        if (str == null) return false;
        return str.contains("S/.") || str.contains("S/") || str.startsWith("$") || str.startsWith("+$") || str.startsWith("+$") || str.startsWith("+S/.") || str.startsWith("-S/.");
    }

    private static Double parsearMonto(String str) {
        try {
            boolean isNegative = str.contains("-");
            String clean = str.replace("S/.", "").replace("S/", "").replace("$", "")
                              .replace("+", "").replace("-", "").replace(",", "").trim();
            double val = Double.parseDouble(clean);
            return isNegative ? -val : val;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static boolean esFecha(String str) {
        if (str == null || str.length() < 8) return false;
        return str.matches("\\d{2}/\\d{2}/\\d{4}") || str.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * Exporta el resumen financiero del Dashboard o Presupuesto a un archivo PDF formateado.
     */
    public static boolean exportarPdfFinanciero(Component parent, String periodo, String colaborador,
                                                String ingresos, String gastos, String ahorros, String balance,
                                                List<String[]> detalles, String prefijoNombre) {
        File destino = seleccionarDestino(parent, generarNombreArchivo(prefijoNombre, "pdf"), "pdf", "Documento PDF (*.pdf)");
        if (destino == null) return false;

        try {
            PdfGenerator pdfGen = new PdfGenerator();
            byte[] pdfBytes = pdfGen.generatePdf(periodo, colaborador, ingresos, gastos, ahorros, balance, detalles);

            try (FileOutputStream fos = new FileOutputStream(destino);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                bos.write(pdfBytes);
                bos.flush();
            }

            JOptionPane.showMessageDialog(
                parent,
                "Reporte PDF exportado exitosamente:\n" + destino.getAbsolutePath(),
                "Exportación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                parent,
                "Error al exportar archivo PDF:\n" + ex.getMessage(),
                "Error de Exportación",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
}
