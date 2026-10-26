package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * Barra de progreso horizontal con soporte para porcentaje simple o múltiples segmentos de color.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class SegmentedProgressBar extends JComponent {

    public static class Segment {
        private double porcentaje; // 0.0 a 100.0
        private Color color;

        public Segment(double porcentaje, Color color) {
            this.porcentaje = porcentaje;
            this.color = color;
        }

        public double getPorcentaje() { return porcentaje; }
        public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
        public Color getColor() { return color; }
        public void setColor(Color color) { this.color = color; }
    }

    private double porcentaje = 50.0;
    private Color progressColor = ThemeColor.VERDE_PRIMARIO;
    private Color trackColor = ThemeColor.BORDE_CARD;
    private int cornerRadius = 6;
    private List<Segment> segmentos = new ArrayList<>();

    public SegmentedProgressBar() {
        setPreferredSize(new Dimension(200, 10));
        setOpaque(false);
    }

    public SegmentedProgressBar(double porcentaje, Color progressColor) {
        this();
        this.porcentaje = porcentaje;
        this.progressColor = progressColor;
    }

    public void agregarSegmento(double porcentaje, Color color) {
        segmentos.add(new Segment(porcentaje, color));
        repaint();
    }

    public void limpiarSegmentos() {
        segmentos.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // 1. Pista de fondo
        g2.setColor(trackColor != null ? trackColor : ThemeColor.BORDE_CARD);
        g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);

        // 2. Si hay segmentos definidos, pintarlos en secuencia
        if (!segmentos.isEmpty()) {
            int startX = 0;
            for (Segment seg : segmentos) {
                int segWidth = (int) Math.round((seg.getPorcentaje() / 100.0) * w);
                if (segWidth > 0) {
                    g2.setColor(seg.getColor());
                    g2.fillRoundRect(startX, 0, Math.min(segWidth, w - startX), h, cornerRadius, cornerRadius);
                    startX += segWidth;
                }
            }
        } else {
            // 3. Modo simple: barra de porcentaje
            double pct = Math.max(0.0, Math.min(100.0, porcentaje));
            int fillWidth = (int) Math.round((pct / 100.0) * w);
            if (fillWidth > 0) {
                g2.setColor(progressColor != null ? progressColor : ThemeColor.VERDE_PRIMARIO);
                g2.fillRoundRect(0, 0, fillWidth, h, cornerRadius, cornerRadius);
            }
        }

        g2.dispose();
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
        repaint();
    }

    public Color getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
        repaint();
    }

    public Color getTrackColor() {
        return trackColor;
    }

    public void setTrackColor(Color trackColor) {
        this.trackColor = trackColor;
        repaint();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}
