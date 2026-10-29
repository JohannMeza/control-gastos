package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Barra lateral unificada para todo el sistema Finandes.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class SidebarPanel extends JPanel {

    private String activeModule = "Panel"; // "Panel", "Ingresos", "Gastos", "Presupuesto", "Ahorros", "Deudas", "Usuarios"

    private JPanel pnlItems;
    private final String[] MODULOS = {
        "Panel", "Ingresos", "Gastos", "Presupuesto", "Ahorros", "Deudas", "Usuarios"
    };

    public SidebarPanel() {
        initComponents();
    }

    public SidebarPanel(String activeModule) {
        this.activeModule = activeModule;
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(230, 720));
        setBackground(ThemeColor.VERDE_PRIMARIO);
        setLayout(new BorderLayout(0, 0));

        // 1. Cabecera (Logo Finandes)
        JPanel pnlHeader = new JPanel();
        pnlHeader.setOpaque(false);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(24, 20, 20, 20));

        JPanel pnlLogoRow = new JPanel(new BorderLayout(10, 0));
        pnlLogoRow.setOpaque(false);

        JLabel lblLogoIcon = new JLabel();
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(getClass().getResource("/images/finandes_mountain.png"));
            if (img != null) {
                lblLogoIcon.setIcon(new ImageIcon(img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH)));
            }
        } catch (Exception ex) {
            lblLogoIcon.setText("▲");
            lblLogoIcon.setFont(new Font("Segoe UI", Font.BOLD, 22));
            lblLogoIcon.setForeground(Color.WHITE);
        }
        lblLogoIcon.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pnlBrandText = new JPanel();
        pnlBrandText.setOpaque(false);
        pnlBrandText.setLayout(new BoxLayout(pnlBrandText, BoxLayout.Y_AXIS));

        JLabel lblBrandName = new JLabel("Finandes");
        lblBrandName.setFont(new Font("Segoe UI", Font.BOLD, 19));
        lblBrandName.setForeground(Color.WHITE);

        JLabel lblBrandSub = new JLabel("Financiera Andina");
        lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblBrandSub.setForeground(ThemeColor.BADGE_SUCCESS_BG);

        pnlBrandText.add(lblBrandName);
        pnlBrandText.add(Box.createVerticalStrut(2));
        pnlBrandText.add(lblBrandSub);

        pnlLogoRow.add(lblLogoIcon, BorderLayout.WEST);
        pnlLogoRow.add(pnlBrandText, BorderLayout.CENTER);

        pnlHeader.add(pnlLogoRow);
        add(pnlHeader, BorderLayout.NORTH);

        // 2. Menú de navegación
        pnlItems = new JPanel();
        pnlItems.setOpaque(false);
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));
        pnlItems.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        construirMenu();
        add(pnlItems, BorderLayout.CENTER);

        // 3. Pie del Sidebar
        JPanel pnlFooter = new JPanel();
        pnlFooter.setOpaque(false);
        pnlFooter.setLayout(new BoxLayout(pnlFooter, BoxLayout.Y_AXIS));
        pnlFooter.setBorder(BorderFactory.createEmptyBorder(10, 16, 20, 16));

        JPanel pnlDivider = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(ThemeColor.VERDE_SEPARADOR);
                g.drawLine(0, 0, getWidth(), 0);
            }
        };
        pnlDivider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        pnlDivider.setPreferredSize(new Dimension(200, 1));
        pnlDivider.setOpaque(false);

        JLabel lblPerfil = crearItemSecundario("perfil", "Perfil");
        JLabel lblConfig = crearItemSecundario("configuracion", "Configuración");

        lblPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(SidebarPanel.this);
                if (currentFrame != null) {
                    JOptionPane.showMessageDialog(currentFrame, "Perfil del usuario: " + 
                        (model.Session.getInstance().getUsuarioActivo() != null ? model.Session.getInstance().getUsuarioActivo().getNombre() : "Usuario"), 
                        "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        lblConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(SidebarPanel.this);
                if (currentFrame != null) {
                    JOptionPane.showMessageDialog(currentFrame, "Panel de preferencias y configuración del sistema.", 
                        "Configuración", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JLabel lblVersion = new JLabel("Java Desktop v1.0");
        lblVersion.setFont(new Font("Consolas", Font.PLAIN, 10));
        lblVersion.setForeground(ThemeColor.VERDE_TEXTO_MUTED);
        lblVersion.setBorder(BorderFactory.createEmptyBorder(12, 8, 4, 8));

        pnlFooter.add(pnlDivider);
        pnlFooter.add(Box.createVerticalStrut(10));
        pnlFooter.add(lblPerfil);
        pnlFooter.add(Box.createVerticalStrut(4));
        pnlFooter.add(lblConfig);
        pnlFooter.add(Box.createVerticalStrut(8));
        pnlFooter.add(lblVersion);

        add(pnlFooter, BorderLayout.SOUTH);
    }

    private void construirMenu() {
        pnlItems.removeAll();
        for (String modulo : MODULOS) {
            boolean isActive = modulo.equalsIgnoreCase(activeModule);
            JPanel btnItem = crearItemModulo(modulo, isActive);
            pnlItems.add(btnItem);
            pnlItems.add(Box.createVerticalStrut(6));
        }
        pnlItems.revalidate();
        pnlItems.repaint();
    }

    private JPanel crearItemModulo(String nombre, boolean isActive) {
        JPanel item = new JPanel(new BorderLayout(12, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                if (isActive) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.WHITE);
                    g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                    g2.dispose();
                } else {
                    super.paintComponent(g);
                }
            }
        };
        item.setOpaque(false);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        item.setPreferredSize(new Dimension(206, 40));
        item.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color iconColor = isActive ? ThemeColor.VERDE_PRIMARIO : Color.WHITE;
        JLabel lblIcono = new JLabel(new MenuIcon(nombre, iconColor));

        JLabel lblTexto = new JLabel(nombre);
        lblTexto.setFont(new Font("Segoe UI", isActive ? Font.BOLD : Font.PLAIN, 13));
        lblTexto.setForeground(isActive ? ThemeColor.VERDE_PRIMARIO : Color.WHITE);

        item.add(lblIcono, BorderLayout.WEST);
        item.add(lblTexto, BorderLayout.CENTER);

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegarHacia(nombre);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isActive) {
                    item.setBackground(ThemeColor.VERDE_HOVER);
                    item.setOpaque(true);
                    item.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isActive) {
                    item.setOpaque(false);
                    item.repaint();
                }
            }
        });

        return item;
    }

    private JLabel crearItemSecundario(String iconKey, String texto) {
        JLabel lbl = new JLabel(" " + texto);
        lbl.setIcon(new MenuIcon(iconKey, new Color(226, 232, 240)));
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(241, 245, 249));
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        return lbl;
    }

    private static class MenuIcon implements javax.swing.Icon {
        private final String modulo;
        private final Color color;

        public MenuIcon(String modulo, Color color) {
            this.modulo = modulo.toLowerCase();
            this.color = color;
        }

        @Override
        public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);

            switch (modulo) {
                case "panel":
                    g2.fillRoundRect(x, y, 6, 6, 2, 2);
                    g2.fillRoundRect(x + 9, y, 6, 6, 2, 2);
                    g2.fillRoundRect(x, y + 9, 6, 6, 2, 2);
                    g2.fillRoundRect(x + 9, y + 9, 6, 6, 2, 2);
                    break;
                case "ingresos":
                    g2.setStroke(new java.awt.BasicStroke(1.8f));
                    g2.drawOval(x, y, 15, 15);
                    g2.drawLine(x + 7, y + 11, x + 7, y + 4);
                    g2.drawLine(x + 4, y + 7, x + 7, y + 4);
                    g2.drawLine(x + 10, y + 7, x + 7, y + 4);
                    break;
                case "gastos":
                    g2.setStroke(new java.awt.BasicStroke(1.8f));
                    g2.drawOval(x, y, 15, 15);
                    g2.drawLine(x + 7, y + 4, x + 7, y + 11);
                    g2.drawLine(x + 4, y + 8, x + 7, y + 11);
                    g2.drawLine(x + 10, y + 8, x + 7, y + 11);
                    break;
                case "presupuesto":
                    g2.fillRoundRect(x, y + 1, 15, 3, 2, 2);
                    g2.fillRoundRect(x, y + 6, 11, 3, 2, 2);
                    g2.fillRoundRect(x, y + 11, 15, 3, 2, 2);
                    break;
                case "ahorros":
                    g2.setStroke(new java.awt.BasicStroke(1.8f));
                    int[] rx = {x + 7, x + 15, x + 7, x};
                    int[] ry = {y, y + 7, y + 15, y + 7};
                    g2.drawPolygon(rx, ry, 4);
                    g2.fillOval(x + 5, y + 5, 5, 5);
                    break;
                case "deudas":
                    g2.setStroke(new java.awt.BasicStroke(1.6f));
                    g2.drawRoundRect(x + 1, y, 13, 15, 2, 2);
                    g2.drawLine(x + 4, y + 4, x + 11, y + 4);
                    g2.drawLine(x + 4, y + 8, x + 11, y + 8);
                    g2.drawLine(x + 4, y + 12, x + 8, y + 12);
                    break;
                case "usuarios":
                    g2.fillOval(x + 3, y, 6, 6);
                    g2.fillArc(x, y + 7, 12, 10, 0, 180);
                    g2.fillOval(x + 10, y + 2, 5, 5);
                    g2.fillArc(x + 8, y + 8, 9, 8, 0, 180);
                    break;
                case "perfil":
                    g2.fillOval(x + 5, y + 1, 6, 6);
                    g2.fillArc(x + 2, y + 8, 12, 10, 0, 180);
                    break;
                case "configuracion":
                    g2.setStroke(new java.awt.BasicStroke(1.5f));
                    g2.drawOval(x + 3, y + 2, 10, 10);
                    g2.drawLine(x + 8, y, x + 8, y + 14);
                    g2.drawLine(x + 1, y + 7, x + 15, y + 7);
                    g2.drawLine(x + 3, y + 2, x + 13, y + 12);
                    g2.drawLine(x + 3, y + 12, x + 13, y + 2);
                    g2.fillOval(x + 6, y + 5, 4, 4);
                    break;
                default:
                    g2.fillOval(x + 4, y + 4, 7, 7);
                    break;
            }
            g2.dispose();
        }

        @Override
        public int getIconWidth() { return 18; }

        @Override
        public int getIconHeight() { return 16; }
    }

    private void navegarHacia(String modulo) {
        if (modulo.equalsIgnoreCase(activeModule)) {
            return;
        }

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String targetClassName = "";

        switch (modulo.toLowerCase()) {
            case "panel": targetClassName = "view.DashboardView"; break;
            case "ingresos": targetClassName = "view.IngresosView"; break;
            case "gastos": targetClassName = "view.SystemView"; break;
            case "presupuesto": targetClassName = "view.PresupuestoView"; break;
            case "ahorros": targetClassName = "view.AhorrosView"; break;
            case "deudas": targetClassName = "view.DeudasView"; break;
            case "usuarios": targetClassName = "view.UsuariosView"; break;
        }

        try {
            Class<?> targetClass = Class.forName(targetClassName);
            JFrame nextFrame = (JFrame) targetClass.getDeclaredConstructor().newInstance();
            nextFrame.setLocationRelativeTo(currentFrame);
            nextFrame.setVisible(true);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(currentFrame, 
                "Abriendo módulo: " + modulo, 
                "Navegación", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getActiveModule() {
        return activeModule;
    }

    public void setActiveModule(String activeModule) {
        this.activeModule = activeModule;
        construirMenu();
    }
}
