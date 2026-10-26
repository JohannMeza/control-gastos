package view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Panel contenedor con esquinas redondeadas y borde sutil.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class CardPanel extends JPanel {

    private int cornerRadius = 12;
    private Color cardBackground = ThemeColor.FONDO_CARD;
    private Color borderColor = ThemeColor.BORDE_CARD;
    private int borderThickness = 1;

    public CardPanel() {
        super();
        setOpaque(false);
    }

    public CardPanel(int cornerRadius) {
        this();
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Relleno de fondo redondeado
        g2.setColor(cardBackground != null ? cardBackground : ThemeColor.FONDO_CARD);
        g2.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Borde perimetral sutil
        if (borderThickness > 0 && borderColor != null) {
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public Color getCardBackground() {
        return cardBackground;
    }

    public void setCardBackground(Color cardBackground) {
        this.cardBackground = cardBackground;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }
}
