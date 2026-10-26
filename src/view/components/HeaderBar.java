package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import util.MenuHelper;

/**
 * Barra superior unificada con breadcrumb, botón primario de acción y avatar con menú de sesión.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class HeaderBar extends JPanel {

    private String moduloActual = "Dashboard";
    private String accionBoton = "+ + Transacción";
    private boolean mostrarBotonAccion = true;

    private JLabel lblModulo;
    private JButton btnAccion;
    private JLabel lblAvatar;

    public HeaderBar() {
        initComponents();
    }

    public HeaderBar(String moduloActual, String accionBoton) {
        this.moduloActual = moduloActual;
        this.accionBoton = accionBoton;
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(800, 60));
        setBackground(ThemeColor.FONDO_CARD);
        setLayout(new BorderLayout(0, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ThemeColor.BORDE_CARD));

        // 1. Izquierda: Breadcrumb
        JPanel pnlBreadcrumb = new JPanel();
        pnlBreadcrumb.setOpaque(false);
        pnlBreadcrumb.setLayout(new BoxLayout(pnlBreadcrumb, BoxLayout.X_AXIS));
        pnlBreadcrumb.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        JLabel lblRutaBase = new JLabel("Finanzas Personales ");
        lblRutaBase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRutaBase.setForeground(ThemeColor.TEXTO_SECUNDARIO);

        JLabel lblSeparador = new JLabel("/ ");
        lblSeparador.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSeparador.setForeground(ThemeColor.TEXTO_MUTED);

        lblModulo = new JLabel(moduloActual);
        lblModulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblModulo.setForeground(ThemeColor.TEXTO_TITULO);

        pnlBreadcrumb.add(lblRutaBase);
        pnlBreadcrumb.add(lblSeparador);
        pnlBreadcrumb.add(lblModulo);

        add(pnlBreadcrumb, BorderLayout.WEST);

        // 2. Derecha: Botón de acción + Íconos rápidos + Avatar
        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 12));
        pnlAcciones.setOpaque(false);
        pnlAcciones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        btnAccion = new JButton(accionBoton) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(ThemeColor.CIAN_HOVER.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(ThemeColor.CIAN_HOVER);
                } else {
                    g2.setColor(ThemeColor.CIAN_ACCION);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAccion.setForeground(Color.WHITE);
        btnAccion.setContentAreaFilled(false);
        btnAccion.setBorderPainted(false);
        btnAccion.setFocusPainted(false);
        btnAccion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAccion.setPreferredSize(new Dimension(140, 36));
        btnAccion.setVisible(mostrarBotonAccion);

        JLabel lblCal = crearIconoCabecera("📅");
        JLabel lblUsers = crearIconoCabecera("👥");
        JLabel lblBell = crearIconoCabecera("🔔");

        lblAvatar = new JLabel("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(241, 245, 249));
                g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
                g2.setColor(ThemeColor.BORDE_CARD);
                g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        lblAvatar.setPreferredSize(new Dimension(34, 34));
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(HeaderBar.this);
                if (currentFrame != null) {
                    MenuHelper.setupUserMenu(currentFrame, lblAvatar);
                }
            }
        });

        pnlAcciones.add(btnAccion);
        pnlAcciones.add(Box.createHorizontalStrut(4));
        pnlAcciones.add(lblCal);
        pnlAcciones.add(lblUsers);
        pnlAcciones.add(lblBell);
        pnlAcciones.add(Box.createHorizontalStrut(4));
        pnlAcciones.add(lblAvatar);

        add(pnlAcciones, BorderLayout.EAST);
    }

    private JLabel crearIconoCabecera(String icono) {
        JLabel lbl = new JLabel(icono);
        lbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        lbl.setForeground(ThemeColor.TEXTO_SECUNDARIO);
        lbl.setPreferredSize(new Dimension(30, 30));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return lbl;
    }

    public String getModuloActual() {
        return moduloActual;
    }

    public void setModuloActual(String moduloActual) {
        this.moduloActual = moduloActual;
        if (lblModulo != null) {
            lblModulo.setText(moduloActual);
        }
    }

    public String getAccionBoton() {
        return accionBoton;
    }

    public void setAccionBoton(String accionBoton) {
        this.accionBoton = accionBoton;
        if (btnAccion != null) {
            btnAccion.setText(accionBoton);
        }
    }

    public boolean isMostrarBotonAccion() {
        return mostrarBotonAccion;
    }

    public void setMostrarBotonAccion(boolean mostrarBotonAccion) {
        this.mostrarBotonAccion = mostrarBotonAccion;
        if (btnAccion != null) {
            btnAccion.setVisible(mostrarBotonAccion);
        }
    }

    public void addActionButtonListener(ActionListener l) {
        if (btnAccion != null) {
            btnAccion.addActionListener(l);
        }
    }

    public JButton getBtnAccion() {
        return btnAccion;
    }

    public JLabel getLblAvatar() {
        return lblAvatar;
    }
}
