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

    private String titulo = "TÍTULO KPI";
    private String monto = "$0.00";
    private String variacion = "+0.0% vs mes anterior";
    private String subtexto = "";
    private Color colorAcento = ThemeColor.CIAN_ACCION;

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
        setLayout(new BorderLayout(10, 8));
        setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        setPreferredSize(new Dimension(240, 115));

        JPanel pnlCentro = new JPanel();
        pnlCentro.setOpaque(false);
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.Y_AXIS));

        lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitulo.setForeground(ThemeColor.TEXTO_SECUNDARIO);

        lblMonto = new JLabel(monto);
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblMonto.setForeground(ThemeColor.TEXTO_TITULO);

        lblVariacion = new JLabel(variacion);
        lblVariacion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblVariacion.setForeground(ThemeColor.BADGE_SUCCESS_TEXT);

        pnlCentro.add(lblTitulo);
        pnlCentro.add(Box.createVerticalStrut(4));
        pnlCentro.add(lblMonto);
        pnlCentro.add(Box.createVerticalStrut(6));
        pnlCentro.add(lblVariacion);

        add(pnlCentro, BorderLayout.CENTER);

        lblIcono = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                g2.setColor(new Color(colorAcento.getRed(), colorAcento.getGreen(), colorAcento.getBlue(), 35));
                g2.fillRoundRect(0, 0, w, h, 8, 8);
                g2.setColor(colorAcento);
                g2.drawRoundRect(0, 0, w - 1, h - 1, 8, 8);
                g2.dispose();
            }
        };
        lblIcono.setPreferredSize(new Dimension(36, 36));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pnlEste = new JPanel(new BorderLayout());
        pnlEste.setOpaque(false);
        pnlEste.add(lblIcono, BorderLayout.NORTH);
        add(pnlEste, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.setColor(ThemeColor.FONDO_CARD);
        g2.fillRoundRect(0, 0, w - 1, h - 1, 12, 12);

        g2.setColor(ThemeColor.BORDE_CARD);
        g2.drawRoundRect(0, 0, w - 1, h - 1, 12, 12);

        g2.dispose();
        super.paintComponent(g);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        if (lblTitulo != null) lblTitulo.setText(titulo != null ? titulo.toUpperCase() : "");
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
        if (lblMonto != null) lblMonto.setText(monto);
    }

    public String getVariacion() {
        return variacion;
    }

    public void setVariacion(String variacion) {
        this.variacion = variacion;
        if (lblVariacion != null) {
            lblVariacion.setText(variacion);
            if (variacion != null && variacion.contains("-")) {
                lblVariacion.setForeground(ThemeColor.BADGE_DANGER_TEXT);
            } else if (variacion != null && variacion.contains("+")) {
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
        repaint();
    }
}
