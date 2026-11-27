package util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PdfGeneratorTest {

    @Test
    public void testPdfEncodingWinAnsiYCaracteresEspeciales() {
        PdfGenerator pdfGen = new PdfGenerator();
        List<String[]> details = new ArrayList<>();
        details.add(new String[]{"Víveres", "S/ 500.00", "S/ 500.00", "100%", "Límite alcanzado"});
        details.add(new String[]{"Telefonía", "S/ 29.90", "S/ 30.00", "99%", "Al día"});

        byte[] pdfBytes = pdfGen.generatePdf(
            "Noviembre 2026", "Johann Meza (Yo)",
            "S/ 5,000.00", "S/ 1,500.00", "S/ 1,000.00", "S/ 2,500.00",
            details
        );

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);

        String pdfString = new String(pdfBytes, StandardCharsets.ISO_8859_1);

        assertTrue(pdfString.contains("/BaseFont /Helvetica /Encoding /WinAnsiEncoding"));
        assertTrue(pdfString.contains("/BaseFont /Helvetica-Bold /Encoding /WinAnsiEncoding"));

        assertTrue(pdfString.contains("V\\355veres"));
        assertTrue(pdfString.contains("Telefon\\355a"));
        assertTrue(pdfString.contains("L\\355mite alcanzado"));
        assertTrue(pdfString.contains("Al d\\355a"));
        assertTrue(pdfString.contains("CATEGOR\\315A"));
        assertTrue(pdfString.contains("Categor\\355a"));
        assertTrue(pdfString.contains("Per\\355odo de Tiempo:"));
        assertTrue(pdfString.contains("Fecha de Emisi\\363n:"));
        assertTrue(pdfString.contains("autom\\341tica"));
        assertTrue(pdfString.contains("P\\341gina 1/1"));
    }
}
