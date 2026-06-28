package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PdfGenerator {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private final List<Long> offsets = new ArrayList<>();

    public PdfGenerator() {
        write("%PDF-1.4\n");
    }

    private void write(String s) {
        try {
            bos.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(byte[] b) {
        try {
            bos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] generatePdf(String periodName, String collaboratorName, String income, String expenses, String savings, String balance, List<String[]> budgetDetails) {
        // Build the Content Stream first to dynamically compute its length
        StringBuilder content = new StringBuilder();

        // 1. Draw Header Background (Blue color: #004AC6 -> RGB 0.0, 0.29, 0.78)
        content.append("0 0.29 0.78 rg\n");
        content.append("40 730 515 60 re f\n");

        // 2. Draw Header Text (White)
        content.append("BT\n");
        content.append("/F2 16 Tf\n"); // Bold
        content.append("1 1 1 rg\n"); // White text
        content.append("60 752 Td\n");
        content.append("(REPORTE RESUMEN DE FINANZAS PERSONALES) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F1 10 Tf\n"); // Regular
        content.append("1 1 1 rg\n");
        content.append("60 740 Td\n");
        content.append("(Generado por el sistema de Control de Gastos) Tj\n");
        content.append("ET\n");

        // 3. Draw Report Metadata (Y = 670)
        content.append("0 0 0 rg\n"); // Black text
        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("40 685 Td\n");
        content.append("(DETALLES DEL REPORTE) Tj\n");
        content.append("ET\n");

        // Draw light horizontal divider line
        content.append("0.8 0.8 0.8 RG\n");
        content.append("1 w\n");
        content.append("40 675 m 555 675 l S\n");

        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 655 Td\n");
        content.append("(Periodo de Tiempo: " + escapePdf(periodName) + ") Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 640 Td\n");
        content.append("(Colaborador: " + escapePdf(collaboratorName) + ") Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("350 655 Td\n");
        java.text.SimpleDateFormat timeSdf = new java.text.SimpleDateFormat("HH:mm:ss");
        String formattedEmissionDate = util.DateUtil.formatLongDate(new java.util.Date()) + " " + timeSdf.format(new java.util.Date());
        content.append("(Fecha de Emision: " + escapePdf(formattedEmissionDate) + ") Tj\n");
        content.append("ET\n");

        // 4. Financial Cards Section (Y = 590)
        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("40 595 Td\n");
        content.append("(RESUMEN TOTAL FINANCIERO) Tj\n");
        content.append("ET\n");

        content.append("40 585 m 555 585 l S\n");

        // Ingresos
        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 565 Td\n");
        content.append("(Ingresos Totales:) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("0 0.42 0.28 rg\n"); // Green
        content.append("180 565 Td\n");
        content.append("(" + escapePdf(income) + ") Tj\n");
        content.append("ET\n");

        // Gastos
        content.append("0 0 0 rg\n");
        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 545 Td\n");
        content.append("(Gastos Totales:) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("0.15 0.39 0.92 rg\n"); // Blue
        content.append("180 545 Td\n");
        content.append("(" + escapePdf(expenses) + ") Tj\n");
        content.append("ET\n");

        // Ahorros
        content.append("0 0 0 rg\n");
        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 525 Td\n");
        content.append("(Ahorros Totales:) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("0.4 0.25 0.05 rg\n"); // Brown
        content.append("180 525 Td\n");
        content.append("(" + escapePdf(savings) + ") Tj\n");
        content.append("ET\n");

        // Saldo / Balance
        content.append("0 0 0 rg\n");
        content.append("BT\n");
        content.append("/F1 10 Tf\n");
        content.append("40 505 Td\n");
        content.append("(Saldo Neto / Balance:) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("0 0 0 rg\n"); // Black
        content.append("180 505 Td\n");
        content.append("(" + escapePdf(balance) + ") Tj\n");
        content.append("ET\n");

        // 5. Budget vs Spending Section (Y = 450)
        content.append("BT\n");
        content.append("/F2 11 Tf\n");
        content.append("40 460 Td\n");
        content.append("(PRESUPUESTO VS GASTOS POR CATEGORIA) Tj\n");
        content.append("ET\n");

        content.append("40 450 m 555 450 l S\n");

        // Draw Table Header
        content.append("BT\n");
        content.append("/F2 10 Tf\n");
        content.append("40 430 Td\n");
        content.append("(Categoria) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 10 Tf\n");
        content.append("180 430 Td\n");
        content.append("(Gasto) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 10 Tf\n");
        content.append("280 430 Td\n");
        content.append("(Presupuesto) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 10 Tf\n");
        content.append("380 430 Td\n");
        content.append("(Porcentaje) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F2 10 Tf\n");
        content.append("470 430 Td\n");
        content.append("(Estado) Tj\n");
        content.append("ET\n");

        content.append("40 422 m 555 422 l S\n");

        // Write budgetDetails table contents (Vivienda, Comida, Transporte)
        int tableY = 405;
        for (String[] detail : budgetDetails) {
            String cat = detail[0];
            String spent = detail[1];
            String limit = detail[2];
            String pct = detail[3];
            String status = detail[4];

            content.append("BT\n");
            content.append("/F1 10 Tf\n");
            content.append("40 " + tableY + " Td\n");
            content.append("(" + escapePdf(cat) + ") Tj\n");
            content.append("ET\n");

            content.append("BT\n");
            content.append("/F1 10 Tf\n");
            content.append("180 " + tableY + " Td\n");
            content.append("(" + escapePdf(spent) + ") Tj\n");
            content.append("ET\n");

            content.append("BT\n");
            content.append("/F1 10 Tf\n");
            content.append("280 " + tableY + " Td\n");
            content.append("(" + escapePdf(limit) + ") Tj\n");
            content.append("ET\n");

            content.append("BT\n");
            content.append("/F1 10 Tf\n");
            content.append("380 " + tableY + " Td\n");
            content.append("(" + escapePdf(pct) + ") Tj\n");
            content.append("ET\n");

            // Set color for status: Excedido (Red) vs Al dia (Green)
            if (status.equalsIgnoreCase("Excedido")) {
                content.append("0.73 0.1 0.1 rg\n"); // Dark red
            } else {
                content.append("0 0.42 0.28 rg\n"); // Green
            }

            content.append("BT\n");
            content.append("/F2 10 Tf\n");
            content.append("470 " + tableY + " Td\n");
            content.append("(" + escapePdf(status) + ") Tj\n");
            content.append("ET\n");

            content.append("0 0 0 rg\n"); // Reset to black

            // Line separation
            content.append("40 " + (tableY - 6) + " m 555 " + (tableY - 6) + " l S\n");

            tableY -= 20;
        }

        // 6. Footer section (Y = 50)
        content.append("0.5 0.5 0.5 rg\n");
        content.append("BT\n");
        content.append("/F1 8 Tf\n");
        content.append("40 45 Td\n");
        content.append("(Este documento es un reporte generado de forma automatica por ControlGastos y sirve como resumen total financiero.) Tj\n");
        content.append("ET\n");

        content.append("BT\n");
        content.append("/F1 8 Tf\n");
        content.append("510 45 Td\n");
        content.append("(Pagina 1/1) Tj\n");
        content.append("ET\n");

        byte[] contentBytes = content.toString().getBytes(StandardCharsets.UTF_8);

        // Catalog
        startObject(1);
        write("<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");

        // Pages List
        startObject(2);
        write("<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n");

        // Page 1
        startObject(3);
        write("<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Resources << /Font << /F1 4 0 R /F2 6 0 R >> >> /Contents 5 0 R >>\nendobj\n");

        // Font F1 (Helvetica - Regular)
        startObject(4);
        write("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\nendobj\n");

        // Content Stream
        startObject(5);
        write("<< /Length " + contentBytes.length + " >>\nstream\n");
        write(contentBytes);
        write("\nendstream\nendobj\n");

        // Font F2 (Helvetica - Bold)
        startObject(6);
        write("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold >>\nendobj\n");

        // Xref Table
        long xrefOffset = bos.size();
        write("xref\n");
        write("0 7\n");
        write("0000000000 65535 f \n");
        for (int i = 0; i < offsets.size(); i++) {
            write(String.format("%010d 00000 n \n", offsets.get(i)));
        }
        write("trailer\n");
        write("<< /Size 7 /Root 1 0 R >>\n");
        write("startxref\n");
        write(xrefOffset + "\n");
        write("%%EOF\n");

        return bos.toByteArray();
    }

    private void startObject(int num) {
        while (offsets.size() < num) {
            offsets.add(0L);
        }
        offsets.set(num - 1, (long) bos.size());
        write(num + " 0 obj\n");
    }

    private String escapePdf(String s) {
        if (s == null) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')' || c == '\\') {
                sb.append('\\').append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
