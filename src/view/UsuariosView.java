package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import controller.UsuarioController;
import view.components.ModernTableHelper;

/**
 * Vista de Control de Usuarios y Acceso Colaborativo con soporte para NetBeans GUI Builder.
 */
public class UsuariosView extends javax.swing.JFrame {

    private controller.UsuarioController controller;

    public UsuariosView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Gestión de Usuarios Colaborativos");
        setLocationRelativeTo(null);

        this.controller = new controller.UsuarioController(this);
    }

    private void initCustomStyles() {
        ModernTableHelper.styleTable(tblUsuarios);
        ModernTableHelper.styleScrollPane(jScrollPaneUsuarios);
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);

        setupTableStyle();

        headerBar.getBtnAccion().addActionListener(evt -> {
            txtCorreo.requestFocus();
            txtCorreo.selectAll();
        });

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 18);
        lblIconSincr.setFont(emojiFont);
        lblIconMfa.setFont(emojiFont);
        lblIconAuditoria.setFont(emojiFont);
    }

    private void setupTableStyle() {
        tblUsuarios.setRowHeight(48);

        // Columna 0 (Usuario) - Avatar circular + Nombre y Correo
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(260);
        tblUsuarios.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String valStr = (value != null) ? value.toString() : "";

                if (valStr.contains("|")) {
                    String[] parts = valStr.split("\\|");
                    String initials = parts[0];
                    String name = parts[1];
                    String email = parts[2];

                    String avatarBg = "#E0F2FE";
                    String avatarFg = "#0369A1";

                    if (initials.equals("?") || initials.equals("❓")) {
                        avatarBg = "#FEF3C7";
                        avatarFg = "#D97706";
                    } else if (initials.equals("MS")) {
                        avatarBg = "#ECFDF5";
                        avatarFg = "#047857";
                    } else if (initials.equals("LW")) {
                        avatarBg = "#FFEDD5";
                        avatarFg = "#9A3412";
                    }

                    label.setText("<html><table style='margin: 2px 4px; padding: 0;'><tr>"
                            + "<td><div style='background-color:" + avatarBg + "; color:" + avatarFg
                            + "; border-radius:14px; width:28px; height:28px; text-align:center; line-height:28px; font-weight:bold; font-size:10px; font-family: Segoe UI;'>"
                            + initials + "</div></td>"
                            + "<td style='padding-left:10px;'><b style='color:#0F172A; font-size:11px; font-family: Segoe UI;'>" + name + "</b><br/>"
                            + "<span style='color:#64748B; font-size:10px; font-family: Segoe UI;'>" + email + "</span></td>"
                            + "</tr></table></html>");
                }

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });

        // Columna 1 (Rol) - Badge tipo cápsula
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblUsuarios.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";

                String bg = "#F1F5F9";
                String fg = "#475569";

                if (valStr.equalsIgnoreCase("ADMIN") || valStr.equalsIgnoreCase("ADMINISTRADOR")) {
                    bg = "#E0F2FE";
                    fg = "#0369A1";
                } else if (valStr.equalsIgnoreCase("EDITOR")) {
                    bg = "#ECFDF5";
                    fg = "#047857";
                } else if (valStr.equalsIgnoreCase("VIEWER") || valStr.equalsIgnoreCase("LECTOR")) {
                    bg = "#F1F5F9";
                    fg = "#475569";
                }

                label.setText("<html><body style='padding: 3px 10px; background-color:" + bg + "; color:" + fg + "; border-radius:10px; font-weight:bold; font-size:9px; font-family: Segoe UI;'>" + valStr + "</body></html>");

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });

        // Columna 2 (Estado) - Viñeta semafórica
        tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblUsuarios.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";

                String dotColor = "#10B981";
                String text = "Activo";

                if (valStr.equalsIgnoreCase("Pending") || valStr.equalsIgnoreCase("Pendiente")) {
                    dotColor = "#D97706";
                    text = "Pendiente";
                }

                label.setText("<html><span style='color:" + dotColor + "; font-size:12px;'>&#9679;</span> <span style='color:#334155; font-size:11px; font-family: Segoe UI;'>" + text + "</span></html>");

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });

        // Columna 3 (Acciones) - Enlace de acción
        tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblUsuarios.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";

                if (valStr.equals("SETTINGS")) {
                    Font gearFont = new Font("Segoe UI Emoji", Font.PLAIN, 15);
                    if (!gearFont.canDisplay(0x2699)) {
                        gearFont = new Font("Segoe UI Symbol", Font.PLAIN, 15);
                    }
                    label.setFont(gearFont);
                    label.setForeground(new Color(100, 116, 139));
                    label.setText("⚙");
                } else if (valStr.equals("CHANGE_REMOVE") || valStr.equals("RESEND_REVOKE")) {
                    label.setFont(new Font("Segoe UI", Font.BOLD, 11));
                    label.setText("<html><a href='#' style='color:#009FE3; text-decoration:none; font-size:11px; font-weight:bold; font-family: Segoe UI;'>Opciones</a></html>");
                } else {
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                    label.setText(valStr);
                }

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        pnlInvitarUsuario = new javax.swing.JPanel();
        lblInvitarUsuarioTitle = new javax.swing.JLabel();
        lblBadgeInvitar = new javax.swing.JLabel();
        lblInvitarSub = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        btnEnviarInvitacion = new javax.swing.JButton();
        pnlLicencias = new javax.swing.JPanel();
        lblAsientosTitle = new javax.swing.JLabel();
        lblAsientosValue = new javax.swing.JLabel();
        pbAsientos = new javax.swing.JProgressBar();
        lblLicenciaDesc = new javax.swing.JLabel();
        lblAsientosBadge = new javax.swing.JLabel();
        pnlUsuariosAutorizados = new javax.swing.JPanel();
        lblUsuariosAutorizadosTitle = new javax.swing.JLabel();
        lblTotalBadge = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnExportar = new javax.swing.JButton();
        jScrollPaneUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lblConexionesCount = new javax.swing.JLabel();
        btnPagPrev = new javax.swing.JButton();
        btnPagActive = new javax.swing.JButton();
        btnPagNext = new javax.swing.JButton();
        pnlCardSincronizacion = new javax.swing.JPanel();
        lblIconSincr = new javax.swing.JLabel();
        lblCardSincronizacionTitle = new javax.swing.JLabel();
        lblCardSincronizacionValue = new javax.swing.JLabel();
        lblCardSincronizacionSub = new javax.swing.JLabel();
        pnlCardMfa = new javax.swing.JPanel();
        lblIconMfa = new javax.swing.JLabel();
        lblCardMfaTitle = new javax.swing.JLabel();
        lblCardMfaValue = new javax.swing.JLabel();
        lblCardMfaSub = new javax.swing.JLabel();
        pnlCardAuditoria = new javax.swing.JPanel();
        lblIconAuditoria = new javax.swing.JLabel();
        lblCardAuditoriaTitle = new javax.swing.JLabel();
        lblCardAuditoriaValue = new javax.swing.JLabel();
        lblCardAuditoriaSub = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Gestión de Usuarios Colaborativos");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Usuarios");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Transacción");
        headerBar.setModuloActual("Gestión de Usuarios Colaborativos");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlInvitarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlInvitarUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlInvitarUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInvitarUsuarioTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblInvitarUsuarioTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblInvitarUsuarioTitle.setText("INVITAR NUEVO USUARIO");
        pnlInvitarUsuario.add(lblInvitarUsuarioTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 14, 200, 16));

        lblBadgeInvitar.setBackground(new java.awt.Color(224, 242, 254));
        lblBadgeInvitar.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBadgeInvitar.setForeground(new java.awt.Color(3, 105, 161));
        lblBadgeInvitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeInvitar.setText("ACCESO");
        lblBadgeInvitar.setOpaque(true);
        pnlInvitarUsuario.add(lblBadgeInvitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 12, 64, 18));

        lblInvitarSub.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInvitarSub.setForeground(new java.awt.Color(100, 116, 139));
        lblInvitarSub.setText("Comparte el acceso a tus finanzas");
        pnlInvitarUsuario.add(lblInvitarSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 14));

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(100, 116, 139));
        lblCorreo.setText("Correo Electrónico *");
        pnlInvitarUsuario.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 62, 280, 14));

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCorreo.setText("colega@ejemplo.com");
        txtCorreo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlInvitarUsuario.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 78, 283, 30));

        lblRol.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblRol.setForeground(new java.awt.Color(100, 116, 139));
        lblRol.setText("Rol Asignado *");
        pnlInvitarUsuario.add(lblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 118, 280, 14));

        cbRol.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lector (Sólo Lectura)", "Editor (Lectura y Edición)", "Administrador (Control Total)" }));
        pnlInvitarUsuario.add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 134, 283, 30));

        btnEnviarInvitacion.setBackground(new java.awt.Color(0, 159, 227));
        btnEnviarInvitacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnviarInvitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarInvitacion.setText("Enviar Invitación");
        btnEnviarInvitacion.setBorder(null);
        btnEnviarInvitacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviarInvitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarInvitacionActionPerformed(evt);
            }
        });
        pnlInvitarUsuario.add(btnEnviarInvitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 180, 283, 34));

        pnlLicencias.setBackground(new java.awt.Color(248, 250, 252));
        pnlLicencias.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlLicencias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAsientosTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblAsientosTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblAsientosTitle.setText("LICENCIAS USADAS");
        pnlLicencias.add(lblAsientosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, 150, 14));

        lblAsientosValue.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblAsientosValue.setForeground(new java.awt.Color(15, 23, 42));
        lblAsientosValue.setText("4 / 10");
        pnlLicencias.add(lblAsientosValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 34, 250, 24));

        pbAsientos.setForeground(new java.awt.Color(0, 159, 227));
        pbAsientos.setValue(40);
        pnlLicencias.add(pbAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 66, 255, 8));

        lblLicenciaDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblLicenciaDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblLicenciaDesc.setText("<html>Plan Profesional Finandes.<br/><b style='color:#10b981;'>6 asientos disponibles</b> para tu equipo de trabajo.</html>");
        pnlLicencias.add(lblLicenciaDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 86, 255, 45));

        lblAsientosBadge.setBackground(new java.awt.Color(209, 250, 229));
        lblAsientosBadge.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblAsientosBadge.setForeground(new java.awt.Color(16, 185, 129));
        lblAsientosBadge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsientosBadge.setText("ACTIVO");
        lblAsientosBadge.setOpaque(true);
        pnlLicencias.add(lblAsientosBadge, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 14, 58, 16));

        pnlInvitarUsuario.add(pnlLicencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 240, 283, 220));

        pnlMainContent.add(pnlInvitarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 315, 480));

        pnlUsuariosAutorizados.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuariosAutorizados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlUsuariosAutorizados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuariosAutorizadosTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblUsuariosAutorizadosTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblUsuariosAutorizadosTitle.setText("USUARIOS AUTORIZADOS");
        pnlUsuariosAutorizados.add(lblUsuariosAutorizadosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 14, 200, 18));

        lblTotalBadge.setBackground(new java.awt.Color(241, 245, 249));
        lblTotalBadge.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblTotalBadge.setForeground(new java.awt.Color(71, 85, 105));
        lblTotalBadge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalBadge.setText("4 en Total");
        lblTotalBadge.setOpaque(true);
        pnlUsuariosAutorizados.add(lblTotalBadge, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 12, 76, 18));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(158, 158, 158));
        txtBuscar.setText("Buscar usuarios...");
        txtBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlUsuariosAutorizados.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 42, 485, 30));

        btnExportar.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(71, 85, 105));
        btnExportar.setText("Exportar CSV");
        btnExportar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 42, 131, 30));

        jScrollPaneUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 232, 226)));

        tblUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "USUARIO", "ROL", "ESTADO", "ACCIONES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setGridColor(new java.awt.Color(226, 232, 240));
        tblUsuarios.setRowHeight(48);
        tblUsuarios.setSelectionBackground(new java.awt.Color(224, 240, 250));
        tblUsuarios.setSelectionForeground(new java.awt.Color(15, 23, 42));
        jScrollPaneUsuarios.setViewportView(tblUsuarios);

        pnlUsuariosAutorizados.add(jScrollPaneUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 84, 625, 345));

        lblConexionesCount.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblConexionesCount.setForeground(new java.awt.Color(100, 116, 139));
        lblConexionesCount.setText("Mostrando 4 de 4 conexiones activas");
        pnlUsuariosAutorizados.add(lblConexionesCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 440, 300, 24));

        btnPagPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPagPrev.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnPagPrev.setForeground(new java.awt.Color(71, 85, 105));
        btnPagPrev.setText("<");
        btnPagPrev.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnPagPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagPrevActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 440, 30, 24));

        btnPagActive.setBackground(new java.awt.Color(0, 159, 227));
        btnPagActive.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnPagActive.setForeground(new java.awt.Color(255, 255, 255));
        btnPagActive.setText("1");
        btnPagActive.setBorder(null);
        btnPagActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagActiveActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 30, 24));

        btnPagNext.setBackground(new java.awt.Color(255, 255, 255));
        btnPagNext.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnPagNext.setForeground(new java.awt.Color(71, 85, 105));
        btnPagNext.setText(">");
        btnPagNext.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnPagNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagNextActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 440, 30, 24));

        pnlMainContent.add(pnlUsuariosAutorizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 15, 657, 480));

        pnlCardSincronizacion.setBackground(new java.awt.Color(240, 249, 255));
        pnlCardSincronizacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(186, 230, 253), 1, true));
        pnlCardSincronizacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIconSincr.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        lblIconSincr.setText("🕒");
        pnlCardSincronizacion.add(lblIconSincr, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 24, 24));

        lblCardSincronizacionTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardSincronizacionTitle.setForeground(new java.awt.Color(3, 105, 161));
        lblCardSincronizacionTitle.setText("ÚLTIMA SINCRONIZACIÓN");
        pnlCardSincronizacion.add(lblCardSincronizacionTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 14, 250, 14));

        lblCardSincronizacionValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardSincronizacionValue.setForeground(new java.awt.Color(15, 23, 42));
        lblCardSincronizacionValue.setText("Hace 2 minutos por Jane Doe");
        pnlCardSincronizacion.add(lblCardSincronizacionValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 32, 250, 18));

        lblCardSincronizacionSub.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblCardSincronizacionSub.setForeground(new java.awt.Color(100, 116, 139));
        lblCardSincronizacionSub.setText("Datos actualizados en tiempo real");
        pnlCardSincronizacion.add(lblCardSincronizacionSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 52, 250, 14));

        pnlMainContent.add(pnlCardSincronizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 510, 315, 95));

        pnlCardMfa.setBackground(new java.awt.Color(236, 253, 245));
        pnlCardMfa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(167, 243, 208), 1, true));
        pnlCardMfa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIconMfa.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        lblIconMfa.setText("🛡️");
        pnlCardMfa.add(lblIconMfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 24, 24));

        lblCardMfaTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardMfaTitle.setForeground(new java.awt.Color(4, 120, 87));
        lblCardMfaTitle.setText("AUTENTICACIÓN MFA");
        pnlCardMfa.add(lblCardMfaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 14, 250, 14));

        lblCardMfaValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardMfaValue.setForeground(new java.awt.Color(15, 23, 42));
        lblCardMfaValue.setText("100% del equipo protegido");
        pnlCardMfa.add(lblCardMfaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 32, 250, 18));

        lblCardMfaSub.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblCardMfaSub.setForeground(new java.awt.Color(100, 116, 139));
        lblCardMfaSub.setText("Políticas de seguridad activas");
        pnlCardMfa.add(lblCardMfaSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 52, 250, 14));

        pnlMainContent.add(pnlCardMfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 510, 315, 95));

        pnlCardAuditoria.setBackground(new java.awt.Color(238, 242, 255));
        pnlCardAuditoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(199, 210, 254), 1, true));
        pnlCardAuditoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIconAuditoria.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        lblIconAuditoria.setText("🔒");
        pnlCardAuditoria.add(lblIconAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 24, 24));

        lblCardAuditoriaTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardAuditoriaTitle.setForeground(new java.awt.Color(55, 48, 163));
        lblCardAuditoriaTitle.setText("REGISTROS DE AUDITORÍA");
        pnlCardAuditoria.add(lblCardAuditoriaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 14, 250, 14));

        lblCardAuditoriaValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardAuditoriaValue.setForeground(new java.awt.Color(67, 56, 202));
        lblCardAuditoriaValue.setText("Ver historial de seguridad →");
        lblCardAuditoriaValue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCardAuditoria.add(lblCardAuditoriaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 32, 250, 18));

        lblCardAuditoriaSub.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblCardAuditoriaSub.setForeground(new java.awt.Color(100, 116, 139));
        lblCardAuditoriaSub.setText("Control total de accesos y cambios");
        pnlCardAuditoria.add(lblCardAuditoriaSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 52, 250, 14));

        pnlMainContent.add(pnlCardAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 510, 326, 95));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        if (controller != null) {
            controller.exportarCSV();
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnEnviarInvitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarInvitacionActionPerformed
        if (controller != null) {
            controller.enviarInvitacion();
        }
    }//GEN-LAST:event_btnEnviarInvitacionActionPerformed

    private void btnPagPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagPrevActionPerformed
    }//GEN-LAST:event_btnPagPrevActionPerformed

    private void btnPagActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagActiveActionPerformed
    }//GEN-LAST:event_btnPagActiveActionPerformed

    private void btnPagNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagNextActionPerformed
    }//GEN-LAST:event_btnPagNextActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new UsuariosView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEnviarInvitacion;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnPagActive;
    public javax.swing.JButton btnPagNext;
    public javax.swing.JButton btnPagPrev;
    public javax.swing.JComboBox<String> cbRol;
    private view.components.HeaderBar headerBar;
    public javax.swing.JScrollPane jScrollPaneUsuarios;
    private javax.swing.JLabel lblAsientosBadge;
    public javax.swing.JLabel lblAsientosTitle;
    public javax.swing.JLabel lblAsientosValue;
    private javax.swing.JLabel lblBadgeInvitar;
    private javax.swing.JLabel lblCardAuditoriaSub;
    private javax.swing.JLabel lblCardAuditoriaTitle;
    private javax.swing.JLabel lblCardAuditoriaValue;
    private javax.swing.JLabel lblCardMfaSub;
    public javax.swing.JLabel lblCardMfaTitle;
    public javax.swing.JLabel lblCardMfaValue;
    private javax.swing.JLabel lblCardSincronizacionSub;
    public javax.swing.JLabel lblCardSincronizacionTitle;
    public javax.swing.JLabel lblCardSincronizacionValue;
    public javax.swing.JLabel lblConexionesCount;
    public javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblIconAuditoria;
    private javax.swing.JLabel lblIconMfa;
    private javax.swing.JLabel lblIconSincr;
    public javax.swing.JLabel lblInvitarSub;
    public javax.swing.JLabel lblInvitarUsuarioTitle;
    public javax.swing.JLabel lblLicenciaDesc;
    public javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTotalBadge;
    public javax.swing.JLabel lblUsuariosAutorizadosTitle;
    public javax.swing.JProgressBar pbAsientos;
    private javax.swing.JPanel pnlCardAuditoria;
    public javax.swing.JPanel pnlCardMfa;
    public javax.swing.JPanel pnlCardSincronizacion;
    public javax.swing.JPanel pnlInvitarUsuario;
    private javax.swing.JPanel pnlLicencias;
    public javax.swing.JPanel pnlMainContent;
    public javax.swing.JPanel pnlUsuariosAutorizados;
    private javax.swing.JScrollPane scrollContent;
    private view.components.SidebarPanel sidebarPanel;
    public javax.swing.JTable tblUsuarios;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
