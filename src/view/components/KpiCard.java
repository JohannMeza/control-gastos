package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Tarjeta KPI reutilizable para indicadores monetarios y variaciones porcentuales.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class KpiCard extends JPanel {

    private String titulo = "Ingresos Totales";
    private String monto = "S/. 0.00";
    private String variacion = "+0.0% vs mes anterior";
    private String subtexto = "";
    private Color colorAcento = ThemeColor.CIAN_ACCION;
    private Color colorMonto;
    private String tipoIcono = "AUTO";

    private JLabel lblTitulo;
    private JLabel lblMonto;
    private JLabel lblVariacion;
    private JLabel lblIcono;

    public KpiCard() {
        initComponents();
    }

    public KpiCard(String titulo, String monto, String variacion, Color colorAcento) {
        this.titulo = titulo;
        this.monto = monto;
        this.variacion = variacion;
        this.colorAcento = colorAcento;
        initComponents();
    }

    private void initComponents() {
        setOpaque(false);
        setLayout(new BorderLayout(0, 4));
        setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        setPreferredSize(new Dimension(235, 115));

        JPanel pnlTop = new JPanel(new BorderLayout(6, 0));
        pnlTop.setOpaque(false);

        lblTitulo = new JLabel(formatTitleCase(titulo));
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitulo.setForeground(ThemeColor.TEXTO_SECUNDARIO);

        lblIcono = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                int w = getWidth();
                int h = getHeight();

                // Fondo suave del badge
                g2.setColor(new Color(colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 32));
                g2.fillRoundRect(0, 0, w - 1, h - 1, 8, 8);
                g2.setColor(new Color(colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 90));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 8, 8);

                // Dibujar icono vectorial representativo según el tipo
                g2.setColor(colorAcento);
                String tipo = resolveIconType();

                if ("ARROW".equals(tipo)) {
                    // Flecha en diagonal hacia arriba-derecha (Ingresos)
                    g2.setStroke(new java.awt.BasicStroke(2.0f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    g2.drawLine(10, 21, 20, 10);
                    g2.drawLine(14, 10, 20, 10);
                    g2.drawLine(20, 10, 20, 16);
                } else if ("CASH".equals(tipo)) {
                    // Billete / Tarjeta de gastos
                    g2.setStroke(new java.awt.BasicStroke(1.5f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    g2.drawRoundRect(7, 10, 17, 11, 3, 3);
                    g2.drawOval(13, 13, 5, 5);
                } else if ("PIGGY".equals(tipo)) {
                    // Hucha / Moneda de ahorro
                    g2.setStroke(new java.awt.BasicStroke(1.5f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    g2.drawOval(8, 9, 15, 13);
                    g2.drawLine(13, 8, 18, 8); // ranura
                    g2.drawArc(7, 13, 5, 5, 90, 180); // hocico/detalle
                } else {
                    // Banco / Templo con columnas (Balance)
                    g2.setStroke(new java.awt.BasicStroke(1.4f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    // Techo triangular
                    g2.drawLine(8, 12, 15, 8);
                    g2.drawLine(15, 8, 23, 12);
                    g2.drawLine(7, 13, 24, 13);
                    // Columnas
                    g2.drawLine(10, 14, 10, 20);
                    g2.drawLine(15, 14, 15, 20);
                    g2.drawLine(21, 14, 21, 20);
                    // Base
                    g2.drawLine(7, 21, 24, 21);
                }

                g2.dispose();
            }
        };
        lblIcono.setPreferredSize(new Dimension(31, 31));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);

        pnlTop.add(lblTitulo, BorderLayout.CENTER);
        pnlTop.add(lblIcono, BorderLayout.EAST);

        JPanel pnlBody = new JPanel();
        pnlBody.setOpaque(false);
        pnlBody.setLayout(new BoxLayout(pnlBody, BoxLayout.Y_AXIS));

        lblMonto = new JLabel(monto);
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 22));
        updateMontoColor();

        lblVariacion = new JLabel();
        lblVariacion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        setVariacion(variacion);

        pnlBody.add(Box.createVerticalStrut(2));
        pnlBody.add(lblMonto);
        pnlBody.add(Box.createVerticalStrut(3));
        pnlBody.add(lblVariacion);

        add(pnlTop, BorderLayout.NORTH);
        add(pnlBody, BorderLayout.CENTER);
    }

    private String resolveIconType() {
        if (!"AUTO".equals(tipoIcono)) {
            return tipoIcono;
        }
        if (titulo == null) return "ARROW";
        String t = titulo.toLowerCase();
        if (t.contains("ingreso")) return "ARROW";
        if (t.contains("gasto")) return "CASH";
        if (t.contains("ahorro")) return "PIGGY";
        if (t.contains("balance")) return "BANK";
        return "ARROW";
    }

    private String formatTitleCase(String raw) {
        if (raw == null || raw.trim().isEmpty()) return "";
        if (raw.equalsIgnoreCase("INGRESOS TOTALES")) return "Ingresos Totales";
        if (raw.equalsIgnoreCase("GASTOS TOTALES")) return "Gastos Totales";
        if (raw.equalsIgnoreCase("AHORROS TOTALES")) return "Ahorros Totales";
        if (raw.equalsIgnoreCase("BALANCE TOTAL")) return "Balance Total";
        return raw;
    }

    private void updateMontoColor() {
        if (lblMonto == null) return;
        if (colorMonto != null) {
            lblMonto.setForeground(colorMonto);
            return;
        }
        if (colorAcento != null) {
            int g = colorAcento.getGreen();
            int b = colorAcento.getBlue();
            int r = colorAcento.getRed();
            if (g > 160 && r < 100) {
                lblMonto.setForeground(new Color(16, 185, 129)); // Verde del mockup
            } else if (b > 180 && r < 80) {
                lblMonto.setForeground(new Color(2, 132, 199)); // Azul del mockup
            } else {
                lblMonto.setForeground(ThemeColor.TEXTO_TITULO); // Oscuro del mockup
            }
        } else {
            lblMonto.setForeground(ThemeColor.TEXTO_TITULO);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Fondo blanco card
        g2.setColor(ThemeColor.FONDO_CARD);
        g2.fillRoundRect(0, 0, w - 1, h - 1, 10, 10);

        // Borde perimetral en color de acento del mockup
        g2.setColor(colorAcento != null ? colorAcento : ThemeColor.BORDE_CARD);
        g2.setStroke(new java.awt.BasicStroke(1.2f));
        g2.drawRoundRect(1, 1, w - 3, h - 3, 10, 10);

        g2.dispose();
        super.paintComponent(g);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        if (lblTitulo != null) {
            lblTitulo.setText(formatTitleCase(titulo));
        }
        repaint();
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
        if (lblMonto != null) {
            lblMonto.setText(monto);
        }
    }

    public void setValor(String valor) {
        setMonto(valor);
    }

    public void setSubtitulo(String subtitulo) {
        setVariacion(subtitulo);
    }

    public String getVariacion() {
        return variacion;
    }

    public void setVariacion(String variacion) {
        this.variacion = variacion;
        if (lblVariacion == null || variacion == null) return;

        if (variacion.startsWith("<html>")) {
            lblVariacion.setText(variacion);
            return;
        }

        // Formato con texto enriquecido según diseño del mockup
        if (variacion.contains("vs mes anterior")) {
            String parts[] = variacion.split("vs mes anterior", 2);
            String prefix = parts[0].replace("↑", "").replace("↓", "").trim();
            boolean esGasto = titulo != null && titulo.toLowerCase().contains("gasto");
            String colorHex = esGasto ? "#EF4444" : "#10B981";
            lblVariacion.setText("<html><b style='color:" + colorHex + ";'>" + prefix + "</b> <span style='color:#64748B;'>vs mes anterior</span></html>");
        } else if (variacion.contains("de la meta mensual")) {
            String parts[] = variacion.split("de la meta mensual", 2);
            String prefix = parts[0].trim();
            lblVariacion.setText("<html><b style='color:#10B981;'>" + prefix + "</b> <span style='color:#64748B;'>de la meta mensual</span></html>");
        } else if (variacion.toLowerCase().contains("sincronizadas")) {
            lblVariacion.setText("<html><b style='color:#0284C7;'>&#10003;</b> <span style='color:#64748B;'>Todas las cuentas sincronizadas</span></html>");
        } else {
            lblVariacion.setText(variacion);
            if (variacion.contains("-")) {
                lblVariacion.setForeground(ThemeColor.BADGE_DANGER_TEXT);
            } else if (variacion.contains("+")) {
                lblVariacion.setForeground(ThemeColor.BADGE_SUCCESS_TEXT);
            } else {
                lblVariacion.setForeground(ThemeColor.TEXTO_SECUNDARIO);
            }
        }
    }

    public String getSubtexto() {
        return subtexto;
    }

    public void setSubtexto(String subtexto) {
        this.subtexto = subtexto;
        setVariacion(subtexto);
    }

    public Color getColorAcento() {
        return colorAcento;
    }

    public void setColorAcento(Color colorAcento) {
        this.colorAcento = colorAcento != null ? colorAcento : ThemeColor.CIAN_ACCION;
        updateMontoColor();
        repaint();
    }

    public Color getColorMonto() {
        return colorMonto;
    }

    public void setColorMonto(Color colorMonto) {
        this.colorMonto = colorMonto;
        updateMontoColor();
    }

    public String getTipoIcono() {
        return tipoIcono;
    }

    public void setTipoIcono(String tipoIcono) {
        this.tipoIcono = tipoIcono != null ? tipoIcono : "AUTO";
        repaint();
    }
}
