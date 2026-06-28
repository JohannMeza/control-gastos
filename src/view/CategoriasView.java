package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.DashboardGraficos;

/**
 * Vista modal que muestra la comparación de Presupuesto vs Gastos de todas las categorías.
 */
public class CategoriasView extends javax.swing.JDialog {

    private List<DashboardGraficos> listaGraficos;

    public CategoriasView(java.awt.Frame parent, boolean modal, List<DashboardGraficos> listaGraficos) {
        super(parent, modal);
        this.listaGraficos = listaGraficos;
        initComponents();
        setResizable(false);
        setTitle("Presupuesto vs Gastos");
        setLocationRelativeTo(parent);
        this.repaint();
    }

    private void initComponents() {
        setSize(600, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JLabel lblTitle = new JLabel("Presupuesto vs Gastos - Todas las Categorías");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTitle.setForeground(new Color(11, 28, 48));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 15));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        for (DashboardGraficos gItem : listaGraficos) {
            JPanel card = crearTarjetaGasto(gItem);
            containerPanel.add(card);
        }

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(containerPanel, BorderLayout.NORTH);

        scrollPane.setViewportView(wrapperPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaGasto(DashboardGraficos gItem) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new javax.swing.border.MatteBorder(0, 0, 1, 0, new Color(234, 241, 255)));
        card.setPreferredSize(new java.awt.Dimension(530, 80));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Title (Category Name) - Left Aligned
        JLabel lblTitle = new JLabel(gItem.getCategoria());
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        lblTitle.setForeground(new Color(11, 28, 48));
        card.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 250, 18));

        // Description Subtitle - Left Aligned
        String desc = gItem.getDescripcion();
        if (desc == null || desc.isEmpty()) desc = "Presupuesto de la categoría";
        JLabel lblDesc = new JLabel("<html>" + desc + "</html>");
        lblDesc.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblDesc.setForeground(new Color(71, 85, 105));
        card.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 23, 300, 15));

        // Spent / Limit Value - Right Aligned
        JLabel lblVal = new JLabel("S/. " + String.format("%.0f", gItem.getGasto()) + " / S/. " + String.format("%.0f", gItem.getLimite()));
        lblVal.setFont(new Font("Dialog", Font.BOLD, 12));
        lblVal.setForeground(new Color(11, 28, 48));
        lblVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        card.add(lblVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 5, 205, 18));

        // Progress Bar - Fills the middle width
        javax.swing.JProgressBar pb = new javax.swing.JProgressBar();
        pb.setBackground(new java.awt.Color(234, 241, 255));
        int percent = (gItem.getLimite() > 0) ? (int)((gItem.getGasto() * 100) / gItem.getLimite()) : 0;
        pb.setValue(Math.min(percent, 100));
        pb.setBorderPainted(false);
        if (percent >= 100) {
            pb.setForeground(new java.awt.Color(186, 26, 26)); // Red
        } else {
            pb.setForeground(new java.awt.Color(37, 99, 235)); // Blue
        }
        card.add(pb, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 43, 520, 8));

        // Status - Left Aligned Bottom
        JLabel lblStatus = new JLabel(percent >= 100 ? "EXCEDIDO" : "AL DIA");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 9));
        if (percent >= 100) {
            lblStatus.setForeground(new java.awt.Color(186, 26, 26));
        } else {
            lblStatus.setForeground(new java.awt.Color(0, 108, 73));
        }
        card.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 55, 150, 15));

        // Percent - Right Aligned Bottom
        JLabel lblPct = new JLabel(percent + "% utilizado");
        lblPct.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblPct.setForeground(new Color(71, 85, 105));
        lblPct.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        card.add(lblPct, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 55, 205, 15));

        return card;
    }
}
