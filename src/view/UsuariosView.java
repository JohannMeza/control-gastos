package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Vista de Control de Usuarios y Acceso Colaborativo.
 */
public class UsuariosView extends javax.swing.JFrame {

    private controller.UsuarioController controller;

    public UsuariosView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        setupTableStyle();
        this.controller = new controller.UsuarioController(this);
        
        // Programmatic addition of the Auditoria button in Sidebar
        Sidebar.setLayout(null);
        jPanelProducts.setBounds(0, 10, 200, 35);
        pnlGasto.setBounds(0, 70, 200, 35);
        jPanelCustomers.setBounds(0, 130, 200, 35);
        jPanelEmployes.setBounds(0, 190, 200, 35);
        jPanelSupplimers.setBounds(0, 250, 200, 35);
        jPanelCategories.setBounds(0, 310, 200, 35);

        javax.swing.JPanel pnlAuditoria = new javax.swing.JPanel();
        pnlAuditoria.setBackground(new java.awt.Color(248, 250, 252));
        pnlAuditoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlAuditoria.setBounds(0, 370, 200, 35);
        javax.swing.JLabel lblAuditoria = new javax.swing.JLabel();
        lblAuditoria.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblAuditoria.setForeground(new java.awt.Color(71, 85, 105));
        lblAuditoria.setText("Auditor\u00eda");
        lblAuditoria.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        try {
            lblAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/auditoria.png")));
        } catch (Exception ex) {}
        pnlAuditoria.setLayout(new java.awt.BorderLayout());
        pnlAuditoria.add(lblAuditoria, java.awt.BorderLayout.CENTER);
        pnlAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new AuditoriaView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(pnlAuditoria);
        Sidebar.revalidate();
        Sidebar.repaint();
        util.MenuHelper.addAvatarToHeader(this, Header);
    }

    private void setupTableStyle() {
        tblUsuarios.setRowHeight(50); // Tall row height for avatar + name/email
        tblUsuarios.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tblUsuarios.getTableHeader().setBackground(new Color(248, 250, 252));
        tblUsuarios.getTableHeader().setForeground(new Color(71, 85, 105));
        
        // Column 0 (Usuario) - Avatar circle + Name & Email
        tblUsuarios.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String valStr = (value != null) ? value.toString() : "";
                
                // Parse initials, name, email
                // Format: "Initials|Name|Email"
                if (valStr.contains("|")) {
                    String[] parts = valStr.split("\\|");
                    String initials = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    
                    String avatarColor = "#dbeafe"; // Light blue for avatar bg
                    String avatarText = "#1e40af";  // Dark blue for avatar fg
                    
                    if (initials.equals("❓")) {
                        avatarColor = "#f1f5f9";
                        avatarText = "#64748b";
                    } else if (initials.equals("MS")) {
                        avatarColor = "#d1fae5";
                        avatarText = "#065f46";
                    } else if (initials.equals("LW")) {
                        avatarColor = "#ffedd5";
                        avatarText = "#9a3412";
                    }
                    
                    label.setText("<html><table style='margin: 4px; padding: 0;'><tr>"
                        + "<td><div style='background-color:" + avatarColor + "; color:" + avatarText 
                        + "; border-radius:14px; width:28px; height:28px; text-align:center; line-height:28px; font-weight:bold; font-size:11px;'>" 
                        + initials + "</div></td>"
                        + "<td style='padding-left:8px;'><b style='color:#0b1c30; font-size:11px;'>" + name + "</b><br/>"
                        + "<span style='color:#64748b; font-size:9px;'>" + email + "</span></td>"
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
        
        // Column 1 (Rol) - Capsule Pill
        tblUsuarios.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";
                
                String bg = "#f1f5f9";
                String fg = "#334155";
                
                if (valStr.equals("ADMIN")) {
                    bg = "#e0e7ff";
                    fg = "#3730a3";
                } else if (valStr.equals("EDITOR")) {
                    bg = "#d1fae5";
                    fg = "#065f46";
                } else if (valStr.equals("VIEWER")) {
                    bg = "#f1f5f9";
                    fg = "#334155";
                }
                
                label.setText("<html><body style='padding: 2px 8px; background-color:" + bg + "; color:" + fg + "; border-radius:10px; font-weight:bold; font-size:9px;'>" + valStr + "</body></html>");
                
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        // Column 2 (Estado) - Colored bullet dot
        tblUsuarios.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";
                
                String dotColor = "#10b981"; // Green for Active
                String text = "Activo";
                
                if (valStr.equalsIgnoreCase("Pending") || valStr.equalsIgnoreCase("Pendiente")) {
                    dotColor = "#f59e0b"; // Orange for Pending
                    text = "Pendiente";
                }
                
                label.setText("<html><span style='color:" + dotColor + "; font-size:12px;'>&#9679;</span> <span style='color:#334155; font-size:11px;'>" + text + "</span></html>");
                
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        // Column 3 (Acciones) - Text Links or icon
        tblUsuarios.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String valStr = (value != null) ? value.toString() : "";
                
                if (valStr.equals("SETTINGS")) {
                    label.setText("<html><span style='font-size:16px; color:#64748b;'>&#9881;</span></html>"); // Gear emoji/symbol
                } else if (valStr.equals("CHANGE_REMOVE")) {
                    label.setText("<html><a href='#' style='color:#2563eb; text-decoration:none; font-size:11px; font-weight:bold;'>Opciones</a></html>");
                } else if (valStr.equals("RESEND_REVOKE")) {
                    label.setText("<html><a href='#' style='color:#2563eb; text-decoration:none; font-size:11px; font-weight:bold;'>Opciones</a></html>");
                } else {
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

    private void addMockData() {
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        model.addRow(new Object[]{"JD|Jane Doe (Propietario)|jane.doe@company.com", "ADMIN", "Activo", "SETTINGS"});
        model.addRow(new Object[]{"MS|Mark Smith|m.smith@finance.dev", "EDITOR", "Activo", "CHANGE_REMOVE"});
        model.addRow(new Object[]{"LW|Lucas Wright|lucas.w@partners.net", "VIEWER", "Activo", "CHANGE_REMOVE"});
        model.addRow(new Object[]{"❓|pending.invite@acme.com|Invitado hace 2 días", "VIEWER", "Pendiente", "RESEND_REVOKE"});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sidebar = new javax.swing.JPanel();
        jPanelProducts = new javax.swing.JPanel();
        jLabelProducts = new javax.swing.JLabel();
        pnlGasto = new javax.swing.JPanel();
        jLabelPurchases = new javax.swing.JLabel();
        jPanelCustomers = new javax.swing.JPanel();
        jLabelCustomers = new javax.swing.JLabel();
        jPanelEmployes = new javax.swing.JPanel();
        jLabelEmployes = new javax.swing.JLabel();
        jPanelSupplimers = new javax.swing.JPanel();
        jLabelSupplimers = new javax.swing.JLabel();
        jPanelCategories = new javax.swing.JPanel();
        jLabelCategories = new javax.swing.JLabel();
        Body = new javax.swing.JTabbedPane();
        jPanelTabUsuarios = new javax.swing.JPanel();
        lblTabTitle = new javax.swing.JLabel();
        lblTabDesc = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();
        pnlInvitarUsuario = new javax.swing.JPanel();
        lblInvitarUsuarioTitle = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox();
        btnEnviarInvitacion = new javax.swing.JButton();
        lblAsientosTitle = new javax.swing.JLabel();
        lblAsientosValue = new javax.swing.JLabel();
        pbAsientos = new javax.swing.JProgressBar();
        lblLicenciaDesc = new javax.swing.JLabel();
        pnlUsuariosAutorizados = new javax.swing.JPanel();
        lblUsuariosAutorizadosTitle = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPaneUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lblConexionesCount = new javax.swing.JLabel();
        btnPagPrev = new javax.swing.JButton();
        btnPagActive = new javax.swing.JButton();
        btnPagNext = new javax.swing.JButton();
        pnlCardSincronizacion = new javax.swing.JPanel();
        lblCardSincronizacionTitle = new javax.swing.JLabel();
        lblCardSincronizacionValue = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pnlCardMfa = new javax.swing.JPanel();
        lblCardMfaTitle = new javax.swing.JLabel();
        lblCardMfaValue = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlCardAuditoria = new javax.swing.JPanel();
        lblCardAuditoriaTitle = new javax.swing.JLabel();
        lblCardAuditoriaValue = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Logo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sidebar.setBackground(new java.awt.Color(248, 250, 252));
        Sidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));

        jPanelProducts.setBackground(new java.awt.Color(248, 250, 252));
        jPanelProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelProductsMouseClicked(evt);
            }
        });

        jLabelProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelProducts.setForeground(new java.awt.Color(71, 85, 105));
        jLabelProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/aplicaciones.png"))); // NOI18N
        jLabelProducts.setText("Dashboard");

        javax.swing.GroupLayout jPanelProductsLayout = new javax.swing.GroupLayout(jPanelProducts);
        jPanelProducts.setLayout(jPanelProductsLayout);
        jPanelProductsLayout.setHorizontalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabelProducts)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProductsLayout.setVerticalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlGasto.setBackground(new java.awt.Color(248, 250, 252));
        pnlGasto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGastoMouseClicked(evt);
            }
        });

        jLabelPurchases.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPurchases.setForeground(new java.awt.Color(71, 85, 105));
        jLabelPurchases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/gastos.png"))); // NOI18N
        jLabelPurchases.setText("Gastos");

        javax.swing.GroupLayout pnlGastoLayout = new javax.swing.GroupLayout(pnlGasto);
        pnlGasto.setLayout(pnlGastoLayout);
        pnlGastoLayout.setHorizontalGroup(
            pnlGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGastoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelPurchases)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGastoLayout.setVerticalGroup(
            pnlGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPurchases, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelCustomers.setBackground(new java.awt.Color(248, 250, 252));
        jPanelCustomers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCustomersMouseClicked(evt);
            }
        });

        jLabelCustomers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCustomers.setForeground(new java.awt.Color(71, 85, 105));
        jLabelCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/presupuesto.png"))); // NOI18N
        jLabelCustomers.setText("Presupuesto");

        javax.swing.GroupLayout jPanelCustomersLayout = new javax.swing.GroupLayout(jPanelCustomers);
        jPanelCustomers.setLayout(jPanelCustomersLayout);
        jPanelCustomersLayout.setHorizontalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelCustomers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelEmployes.setBackground(new java.awt.Color(248, 250, 252));
        jPanelEmployes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelEmployes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEmployesMouseClicked(evt);
            }
        });

        jLabelEmployes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelEmployes.setForeground(new java.awt.Color(71, 85, 105));
        jLabelEmployes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/ahorros.png"))); // NOI18N
        jLabelEmployes.setText("Ahorros");

        javax.swing.GroupLayout jPanelEmployesLayout = new javax.swing.GroupLayout(jPanelEmployes);
        jPanelEmployes.setLayout(jPanelEmployesLayout);
        jPanelEmployesLayout.setHorizontalGroup(
            jPanelEmployesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelEmployes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEmployesLayout.setVerticalGroup(
            jPanelEmployesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelEmployes, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelSupplimers.setBackground(new java.awt.Color(248, 250, 252));
        jPanelSupplimers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelSupplimers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSupplimersMouseClicked(evt);
            }
        });

        jLabelSupplimers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSupplimers.setForeground(new java.awt.Color(71, 85, 105));
        jLabelSupplimers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/deudas.png"))); // NOI18N
        jLabelSupplimers.setText("Deudas");

        javax.swing.GroupLayout jPanelSupplimersLayout = new javax.swing.GroupLayout(jPanelSupplimers);
        jPanelSupplimers.setLayout(jPanelSupplimersLayout);
        jPanelSupplimersLayout.setHorizontalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSupplimersLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelSupplimers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSupplimersLayout.setVerticalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSupplimers, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelCategories.setBackground(new java.awt.Color(244, 248, 254));
        jPanelCategories.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));

        jLabelCategories.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategories.setForeground(new java.awt.Color(37, 99, 235));
        jLabelCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/usuario.png"))); // NOI18N
        jLabelCategories.setText("Usuarios");

        javax.swing.GroupLayout jPanelCategoriesLayout = new javax.swing.GroupLayout(jPanelCategories);
        jPanelCategories.setLayout(jPanelCategoriesLayout);
        jPanelCategoriesLayout.setHorizontalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoriesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabelCategories)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCategoriesLayout.setVerticalGroup(
            jPanelCategoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelEmployes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelSupplimers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelEmployes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelSupplimers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 600));

        Body.setBackground(new java.awt.Color(248, 249, 255));

        jPanelTabUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTabTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTabTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblTabTitle.setText("Acceso Colaborativo");
        jPanelTabUsuarios.add(lblTabTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblTabDesc.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblTabDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblTabDesc.setText("Gestiona quién puede ver y editar este espacio de trabajo financiero.");
        jPanelTabUsuarios.add(lblTabDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        btnExportar.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(71, 85, 105));
        btnExportar.setText("Exportar Lista");
        btnExportar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jPanelTabUsuarios.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 140, 28));

        pnlInvitarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlInvitarUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 234, 241), 1, true));
        pnlInvitarUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInvitarUsuarioTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblInvitarUsuarioTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblInvitarUsuarioTitle.setText("Invitar Nuevo Usuario");
        pnlInvitarUsuario.add(lblInvitarUsuarioTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCorreo.setForeground(new java.awt.Color(71, 85, 105));
        lblCorreo.setText("Correo Electrónico");
        pnlInvitarUsuario.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        txtCorreo.setText("colega@ejemplo.com");
        txtCorreo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 226, 226), 1, true));
        pnlInvitarUsuario.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 63, 280, 28));

        lblRol.setForeground(new java.awt.Color(71, 85, 105));
        lblRol.setText("Rol de Acceso");
        pnlInvitarUsuario.add(lblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 105, -1, -1));

        cbRol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lector (Solo Lectura)", "Editor (Modificar)", "Administrador (Acceso Total)" }));
        pnlInvitarUsuario.add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 123, 280, 28));

        btnEnviarInvitacion.setBackground(new java.awt.Color(37, 99, 235));
        btnEnviarInvitacion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEnviarInvitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarInvitacion.setText("Enviar Invitación");
        btnEnviarInvitacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviarInvitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarInvitacionActionPerformed(evt);
            }
        });
        pnlInvitarUsuario.add(btnEnviarInvitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 175, 280, 34));

        lblAsientosTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblAsientosTitle.setText("Usuarios compartidos");
        pnlInvitarUsuario.add(lblAsientosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 235, -1, -1));

        lblAsientosValue.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblAsientosValue.setForeground(new java.awt.Color(11, 28, 48));
        lblAsientosValue.setText("4 / 10");
        pnlInvitarUsuario.add(lblAsientosValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 235, -1, -1));

        pbAsientos.setForeground(new java.awt.Color(37, 99, 235));
        pbAsientos.setValue(40);
        pbAsientos.setBorderPainted(false);
        pnlInvitarUsuario.add(pbAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 255, 280, 8));

        lblLicenciaDesc.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblLicenciaDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblLicenciaDesc.setText("<html><body style='width: 250px;'>La licencia estándar incluye hasta 10 asientos colaborativos.</body></html>");
        pnlInvitarUsuario.add(lblLicenciaDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 275, -1, -1));

        jPanelTabUsuarios.add(pnlInvitarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 310, 360));

        pnlUsuariosAutorizados.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuariosAutorizados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 234, 241), 1, true));
        pnlUsuariosAutorizados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuariosAutorizadosTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUsuariosAutorizadosTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblUsuariosAutorizadosTitle.setText("Usuarios Autorizados");
        pnlUsuariosAutorizados.add(lblUsuariosAutorizadosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        txtBuscar.setText("Buscar usuarios...");
        txtBuscar.setToolTipText("");
        txtBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 226, 226), 1, true));
        pnlUsuariosAutorizados.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 12, 195, 26));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
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
        tblUsuarios.setSelectionBackground(new java.awt.Color(234, 241, 255));
        tblUsuarios.setSelectionForeground(new java.awt.Color(11, 28, 48));
        jScrollPaneUsuarios.setViewportView(tblUsuarios);
        if (tblUsuarios.getColumnModel().getColumnCount() > 0) {
            tblUsuarios.getColumnModel().getColumn(0).setMinWidth(200);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(260);
            tblUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tblUsuarios.getColumnModel().getColumn(1).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tblUsuarios.getColumnModel().getColumn(2).setMinWidth(90);
            tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tblUsuarios.getColumnModel().getColumn(3).setMinWidth(150);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(160);
        }

        pnlUsuariosAutorizados.add(jScrollPaneUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 55, 610, 255));

        lblConexionesCount.setForeground(new java.awt.Color(100, 116, 139));
        lblConexionesCount.setText("Mostrando 4 de 4 conexiones activas");
        pnlUsuariosAutorizados.add(lblConexionesCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 320, -1, -1));

        btnPagPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPagPrev.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPagPrev.setForeground(new java.awt.Color(100, 116, 139));
        btnPagPrev.setText("<");
        btnPagPrev.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPagPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagPrevActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 318, 22, 22));

        btnPagActive.setBackground(new java.awt.Color(37, 99, 235));
        btnPagActive.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPagActive.setForeground(new java.awt.Color(255, 255, 255));
        btnPagActive.setText("1");
        btnPagActive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagActiveActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 318, 22, 22));

        btnPagNext.setBackground(new java.awt.Color(255, 255, 255));
        btnPagNext.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPagNext.setForeground(new java.awt.Color(100, 116, 139));
        btnPagNext.setText(">");
        btnPagNext.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnPagNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagNextActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 318, 22, 22));

        jPanelTabUsuarios.add(pnlUsuariosAutorizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 640, 360));

        pnlCardSincronizacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardSincronizacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 234, 241), 1, true));
        pnlCardSincronizacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardSincronizacionTitle.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardSincronizacionTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardSincronizacionTitle.setText("ÚLTIMA SINCRONIZACIÓN");
        pnlCardSincronizacion.add(lblCardSincronizacionTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardSincronizacionValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblCardSincronizacionValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardSincronizacionValue.setText("Hace 2 minutos por Jane Doe");
        pnlCardSincronizacion.add(lblCardSincronizacionValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanel8.setBackground(new java.awt.Color(108, 248, 187));
        jPanel8.setName(""); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel8.setLayout(new java.awt.CardLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setRequestFocusEnabled(false);
        jPanel8.add(jLabel10, "card2");

        pnlCardSincronizacion.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        jPanelTabUsuarios.add(pnlCardSincronizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 310, 90));

        pnlCardMfa.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardMfa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 234, 241), 1, true));
        pnlCardMfa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardMfaTitle.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardMfaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardMfaTitle.setText("MFA ACTIVADO");
        pnlCardMfa.add(lblCardMfaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardMfaValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblCardMfaValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardMfaValue.setText("100% del equipo protegido");
        pnlCardMfa.add(lblCardMfaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanel7.setBackground(new java.awt.Color(211, 228, 254));
        jPanel7.setName(""); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel7.setLayout(new java.awt.CardLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/usuario.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setRequestFocusEnabled(false);
        jPanel7.add(jLabel9, "card2");

        pnlCardMfa.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        jPanelTabUsuarios.add(pnlCardMfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 310, 90));

        pnlCardAuditoria.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardAuditoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 234, 241), 1, true));
        pnlCardAuditoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardAuditoriaTitle.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardAuditoriaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardAuditoriaTitle.setText("LOGS DE AUDITORÍA");
        pnlCardAuditoria.add(lblCardAuditoriaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardAuditoriaValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblCardAuditoriaValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardAuditoriaValue.setText("Ver historial de seguridad completo");
        pnlCardAuditoria.add(lblCardAuditoriaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 218, 214));
        jPanel6.setName(""); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/deudas.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setRequestFocusEnabled(false);
        jPanel6.add(jLabel8, "card2");

        pnlCardAuditoria.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        jPanelTabUsuarios.add(pnlCardAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 310, 90));

        Body.addTab("Usuarios", jPanelTabUsuarios);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        Logo.setBackground(new java.awt.Color(248, 250, 252));
        Logo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(248, 250, 252));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo.png"))); // NOI18N
        Logo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(234, 241, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 28, 48));
        jLabel2.setText("Usuarios");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) {
            controller.exportarCSV();
        }
    }

    private void btnEnviarInvitacionActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) {
            controller.enviarInvitacion();
        }
    }

    private void btnPagPrevActionPerformed(java.awt.event.ActionEvent evt) {}
    private void btnPagActiveActionPerformed(java.awt.event.ActionEvent evt) {}
    private void btnPagNextActionPerformed(java.awt.event.ActionEvent evt) {}

    private void pnlGastoMouseClicked(java.awt.event.MouseEvent evt) {
        SystemView viewSystem = new SystemView();
        viewSystem.setVisible(true);
        dispose();
    }

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {
        DashboardView viewDashboard = new DashboardView();
        viewDashboard.setVisible(true);
        dispose();
    }

    private void jPanelCustomersMouseClicked(java.awt.event.MouseEvent evt) {
        PresupuestoView viewPresupuesto = new PresupuestoView();
        viewPresupuesto.setVisible(true);
        dispose();
    }

    private void jPanelEmployesMouseClicked(java.awt.event.MouseEvent evt) {
        AhorrosView viewAhorros = new AhorrosView();
        viewAhorros.setVisible(true);
        dispose();
    }

    private void jPanelSupplimersMouseClicked(java.awt.event.MouseEvent evt) {
        DeudasView viewDeudas = new DeudasView();
        viewDeudas.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuariosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane Body;
    public javax.swing.JPanel Header;
    public javax.swing.JPanel Logo;
    public javax.swing.JPanel Sidebar;
    public javax.swing.JButton btnEnviarInvitacion;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnPagActive;
    public javax.swing.JButton btnPagNext;
    public javax.swing.JButton btnPagPrev;
    public javax.swing.JComboBox cbRol;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelCategories;
    public javax.swing.JLabel jLabelCustomers;
    public javax.swing.JLabel jLabelEmployes;
    public javax.swing.JLabel jLabelProducts;
    public javax.swing.JLabel jLabelPurchases;
    public javax.swing.JLabel jLabelSupplimers;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanelCategories;
    public javax.swing.JPanel jPanelCustomers;
    public javax.swing.JPanel jPanelEmployes;
    public javax.swing.JPanel jPanelProducts;
    public javax.swing.JPanel jPanelSupplimers;
    public javax.swing.JPanel jPanelTabUsuarios;
    public javax.swing.JScrollPane jScrollPaneUsuarios;
    public javax.swing.JLabel lblAsientosTitle;
    public javax.swing.JLabel lblAsientosValue;
    public javax.swing.JLabel lblCardAuditoriaTitle;
    public javax.swing.JLabel lblCardAuditoriaValue;
    public javax.swing.JLabel lblCardMfaTitle;
    public javax.swing.JLabel lblCardMfaValue;
    public javax.swing.JLabel lblCardSincronizacionTitle;
    public javax.swing.JLabel lblCardSincronizacionValue;
    public javax.swing.JLabel lblConexionesCount;
    public javax.swing.JLabel lblCorreo;
    public javax.swing.JLabel lblInvitarUsuarioTitle;
    public javax.swing.JLabel lblLicenciaDesc;
    public javax.swing.JLabel lblRol;
    public javax.swing.JLabel lblTabDesc;
    public javax.swing.JLabel lblTabTitle;
    public javax.swing.JLabel lblUsuariosAutorizadosTitle;
    public javax.swing.JProgressBar pbAsientos;
    public javax.swing.JPanel pnlCardAuditoria;
    public javax.swing.JPanel pnlCardMfa;
    public javax.swing.JPanel pnlCardSincronizacion;
    public javax.swing.JPanel pnlGasto;
    public javax.swing.JPanel pnlInvitarUsuario;
    public javax.swing.JPanel pnlUsuariosAutorizados;
    public javax.swing.JTable tblUsuarios;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
