package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 * Renderer de celda para renderizar estados y categorías como badges/píldoras redondeadas.
 * Cumple con los colores exactos definidos en AGENTS.md.
 */
public class StatusBadgeRenderer extends JLabel implements TableCellRenderer {

    private Color badgeBackground = ThemeColor.BADGE_SUCCESS_BG;
    private Color badgeForeground = ThemeColor.BADGE_SUCCESS_TEXT;

    public StatusBadgeRenderer() {
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Segoe UI", Font.BOLD, 11));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        String text = (value != null) ? value.toString().trim() : "";
        setText(text);
        asignarColoresPorTexto(text.toUpperCase());
        return this;
    }

    private void asignarColoresPorTexto(String valor) {
        if (valor.contains("EN REGLA") || valor.contains("CONFIRMADO") || valor.contains("AL DÍA") || valor.contains("AL DIA") || valor.contains("ACTIVO") || valor.contains("PAGADO")) {
            badgeBackground = ThemeColor.BADGE_SUCCESS_BG;
            badgeForeground = ThemeColor.BADGE_SUCCESS_TEXT;
        } else if (valor.contains("ALERTA") || valor.contains("PENDIENTE")) {
            badgeBackground = ThemeColor.BADGE_WARNING_BG;
            badgeForeground = ThemeColor.BADGE_WARNING_TEXT;
        } else if (valor.contains("EXCEDIDO") || valor.contains("VENCER") || valor.contains("VENCIDO")) {
            badgeBackground = ThemeColor.BADGE_DANGER_BG;
            badgeForeground = ThemeColor.BADGE_DANGER_TEXT;
        } else if (valor.contains("BAJO LÍMITE") || valor.contains("BAJO LIMITE")) {
            badgeBackground = ThemeColor.BADGE_TEAL_BG;
            badgeForeground = ThemeColor.BADGE_TEAL_TEXT;
        } else if (valor.equals("ADMIN") || valor.contains("SERVICIOS")) {
            badgeBackground = ThemeColor.BADGE_INFO_BG;
            badgeForeground = ThemeColor.BADGE_INFO_TEXT;
        } else if (valor.equals("EDITOR") || valor.contains("SALUD") || valor.contains("SALARIO")) {
            badgeBackground = new Color(236, 253, 245);
            badgeForeground = new Color(4, 120, 87);
        } else if (valor.equals("LECTOR") || valor.contains("VIVIENDA")) {
            badgeBackground = ThemeColor.BADGE_NEUTRAL_BG;
            badgeForeground = ThemeColor.BADGE_NEUTRAL_TEXT;
        } else if (valor.contains("ALIMENTACIÓN") || valor.contains("ALIMENTACION") || valor.contains("MERCADO")) {
            badgeBackground = new Color(255, 237, 213);
            badgeForeground = new Color(194, 65, 12);
        } else if (valor.contains("TRANSPORTE")) {
            badgeBackground = new Color(243, 232, 255);
            badgeForeground = new Color(126, 34, 206);
        } else if (valor.contains("FREELANCE")) {
            badgeBackground = ThemeColor.CIAN_LIGHT;
            badgeForeground = ThemeColor.CIAN_HOVER;
        } else if (valor.contains("INVERSIONES") || valor.contains("EXTRA")) {
            badgeBackground = new Color(254, 243, 199);
            badgeForeground = new Color(180, 83, 9);
        } else {
            badgeBackground = ThemeColor.BADGE_NEUTRAL_BG;
            badgeForeground = ThemeColor.BADGE_NEUTRAL_TEXT;
        }
        setForeground(badgeForeground);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getText() != null && !getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int badgeHeight = Math.min(22, height - 6);
            int badgeY = (height - badgeHeight) / 2;
            int badgeWidth = Math.min(width - 12, (int) (getFontMetrics(getFont()).stringWidth(getText()) + 20));
            int badgeX = (width - badgeWidth) / 2;

            g2.setColor(badgeBackground);
            g2.fillRoundRect(badgeX, badgeY, badgeWidth, badgeHeight, 12, 12);

            g2.dispose();
        }
        super.paintComponent(g);
    }
}
