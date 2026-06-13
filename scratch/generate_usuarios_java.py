import os

path_usuarios_java = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\UsuariosView.java"

code_content = """package view;

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

    public UsuariosView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administraci\u00f3n");
        setLocationRelativeTo(null);
        setupTableStyle();
        addMockData();
        this.repaint();
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
                    String[] parts = valStr.split("\\\\|");
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
                    label.setText("<html><a href='#' style='color:#2563eb; text-decoration:none; font-size:11px; font-weight:bold;'>Cambiar Rol</a> &nbsp;|&nbsp; <a href='#' style='color:#ef4444; text-decoration:none; font-size:11px; font-weight:bold;'>Eliminar</a></html>");
                } else if (valStr.equals("RESEND_REVOKE")) {
                    label.setText("<html><a href='#' style='color:#2563eb; text-decoration:none; font-size:11px; font-weight:bold;'>Reenviar</a> &nbsp;|&nbsp; <a href='#' style='color:#ef4444; text-decoration:none; font-size:11px; font-weight:bold;'>Revocar</a></html>");
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
        Logo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        cbRol = new javax.swing.JComboBox<>();
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
        lblCardSincronizacionIcon = new javax.swing.JLabel();
        lblCardSincronizacionTitle = new javax.swing.JLabel();
        lblCardSincronizacionValue = new javax.swing.JLabel();
        
        pnlCardMfa = new javax.swing.JPanel();
        lblCardMfaIcon = new javax.swing.JLabel();
        lblCardMfaTitle = new javax.swing.JLabel();
        lblCardMfaValue = new javax.swing.JLabel();
        
        pnlCardAuditoria = new javax.swing.JPanel();
        lblCardAuditoriaIcon = new javax.swing.JLabel();
        lblCardAuditoriaTitle = new javax.swing.JLabel();
        lblCardAuditoriaValue = new javax.swing.JLabel();

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
                .addGap(29, 29, 29)
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
                .addGap(29, 29, 29)
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
                .addGap(27, 27, 27)
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
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelCategories, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSupplimers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEmployes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelCustomers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addGap(255, 255, 255))
        );

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 600));

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
        jLabel2.setText("Finanzas Personales / Gesti\u00f3n de Usuarios");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        jPanelTabUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Title and Description
        lblTabTitle.setFont(new java.awt.Font("Dialog", 1, 18));
        lblTabTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblTabTitle.setText("Acceso Colaborativo");
        jPanelTabUsuarios.add(lblTabTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        
        lblTabDesc.setFont(new java.awt.Font("Dialog", 0, 12));
        lblTabDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblTabDesc.setText("Gestiona qui\u00e9n puede ver y editar este espacio de trabajo financiero.");
        jPanelTabUsuarios.add(lblTabDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        btnExportar.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnExportar.setForeground(new java.awt.Color(71, 85, 105));
        btnExportar.setText("Exportar Lista");
        btnExportar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jPanelTabUsuarios.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 140, 28));

        // --- Box Invitar Usuario ---
        pnlInvitarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlInvitarUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlInvitarUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInvitarUsuarioTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblInvitarUsuarioTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblInvitarUsuarioTitle.setText("Invitar Nuevo Usuario");
        pnlInvitarUsuario.add(lblInvitarUsuarioTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCorreo.setFont(new java.awt.Font("Dialog", 0, 11));
        lblCorreo.setForeground(new java.awt.Color(71, 85, 105));
        lblCorreo.setText("Correo Electr\u00f3nico");
        pnlInvitarUsuario.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 45, -1, -1));

        txtCorreo.setText("colega@ejemplo.com");
        txtCorreo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlInvitarUsuario.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 63, 280, 28));

        lblRol.setFont(new java.awt.Font("Dialog", 0, 11));
        lblRol.setForeground(new java.awt.Color(71, 85, 105));
        lblRol.setText("Rol de Acceso");
        pnlInvitarUsuario.add(lblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 105, -1, -1));

        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lector (Solo Lectura)", "Editor (Modificar)", "Administrador (Acceso Total)" }));
        pnlInvitarUsuario.add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 123, 280, 28));

        btnEnviarInvitacion.setBackground(new java.awt.Color(37, 99, 235));
        btnEnviarInvitacion.setFont(new java.awt.Font("Dialog", 1, 12));
        btnEnviarInvitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarInvitacion.setText("Enviar Invitaci\u00f3n");
        btnEnviarInvitacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviarInvitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarInvitacionActionPerformed(evt);
            }
        });
        pnlInvitarUsuario.add(btnEnviarInvitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 175, 280, 34));

        lblAsientosTitle.setFont(new java.awt.Font("Dialog", 0, 11));
        lblAsientosTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblAsientosTitle.setText("Asientos Usados");
        pnlInvitarUsuario.add(lblAsientosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 235, -1, -1));

        lblAsientosValue.setFont(new java.awt.Font("Dialog", 1, 11));
        lblAsientosValue.setForeground(new java.awt.Color(11, 28, 48));
        lblAsientosValue.setText("4 / 10");
        pnlInvitarUsuario.add(lblAsientosValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 235, -1, -1));

        pbAsientos.setValue(40);
        pbAsientos.setBorderPainted(false);
        pbAsientos.setForeground(new java.awt.Color(37, 99, 235));
        pnlInvitarUsuario.add(pbAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 255, 280, 8));

        lblLicenciaDesc.setFont(new java.awt.Font("Dialog", 0, 10));
        lblLicenciaDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblLicenciaDesc.setText("La licencia est\u00e1ndar incluye hasta 10 asientos colaborativos.");
        pnlInvitarUsuario.add(lblLicenciaDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 275, 280, 40));

        jPanelTabUsuarios.add(pnlInvitarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 310, 360));

        // --- Box Usuarios Autorizados ---
        pnlUsuariosAutorizados.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuariosAutorizados.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlUsuariosAutorizados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuariosAutorizadosTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblUsuariosAutorizadosTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblUsuariosAutorizadosTitle.setText("Usuarios Autorizados");
        pnlUsuariosAutorizados.add(lblUsuariosAutorizadosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        txtBuscar.setText("Buscar usuarios...");
        txtBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlUsuariosAutorizados.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 12, 195, 26));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "USUARIO", "ROL", "ESTADO", "ACCIONES" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setSelectionBackground(new java.awt.Color(234, 241, 255));
        tblUsuarios.setSelectionForeground(new java.awt.Color(11, 28, 48));
        jScrollPaneUsuarios.setViewportView(tblUsuarios);
        pnlUsuariosAutorizados.add(jScrollPaneUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 55, 610, 255));

        lblConexionesCount.setFont(new java.awt.Font("Dialog", 0, 11));
        lblConexionesCount.setForeground(new java.awt.Color(100, 116, 139));
        lblConexionesCount.setText("Mostrando 4 de 4 conexiones activas");
        pnlUsuariosAutorizados.add(lblConexionesCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 320, -1, -1));

        btnPagPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPagPrev.setText("<");
        btnPagPrev.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnPagPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagPrevActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 318, 22, 22));

        btnPagActive.setBackground(new java.awt.Color(37, 99, 235));
        btnPagActive.setFont(new java.awt.Font("Dialog", 1, 10));
        btnPagActive.setForeground(new java.awt.Color(255, 255, 255));
        btnPagActive.setText("1");
        btnPagActive.setBorder(null);
        pnlUsuariosAutorizados.add(btnPagActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 318, 22, 22));

        btnPagNext.setBackground(new java.awt.Color(255, 255, 255));
        btnPagNext.setText(">");
        btnPagNext.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnPagNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagNextActionPerformed(evt);
            }
        });
        pnlUsuariosAutorizados.add(btnPagNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 318, 22, 22));

        jPanelTabUsuarios.add(pnlUsuariosAutorizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 640, 360));

        // --- Bottom Row: Summary Cards ---
        // Card 1: Last Sync
        pnlCardSincronizacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardSincronizacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardSincronizacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardSincronizacionIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/historial.png")));
        pnlCardSincronizacion.add(lblCardSincronizacionIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        lblCardSincronizacionTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardSincronizacionTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardSincronizacionTitle.setText("\u00daLTIMA SINCRONIZACI\u00d3N");
        pnlCardSincronizacion.add(lblCardSincronizacionTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardSincronizacionValue.setFont(new java.awt.Font("Dialog", 1, 13));
        lblCardSincronizacionValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardSincronizacionValue.setText("Hace 2 minutos por Jane Doe");
        pnlCardSincronizacion.add(lblCardSincronizacionValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanelTabUsuarios.add(pnlCardSincronizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 310, 90));

        // Card 2: MFA Enabled
        pnlCardMfa.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardMfa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardMfa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardMfaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png")));
        pnlCardMfa.add(lblCardMfaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        lblCardMfaTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardMfaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardMfaTitle.setText("MFA ACTIVADO");
        pnlCardMfa.add(lblCardMfaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardMfaValue.setFont(new java.awt.Font("Dialog", 1, 13));
        lblCardMfaValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardMfaValue.setText("100% del equipo protegido");
        pnlCardMfa.add(lblCardMfaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanelTabUsuarios.add(pnlCardMfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 310, 90));

        // Card 3: Audit Logs
        pnlCardAuditoria.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardAuditoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardAuditoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardAuditoriaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png")));
        pnlCardAuditoria.add(lblCardAuditoriaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        lblCardAuditoriaTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardAuditoriaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardAuditoriaTitle.setText("LOGS DE AUDITOR\u00cdA");
        pnlCardAuditoria.add(lblCardAuditoriaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        lblCardAuditoriaValue.setFont(new java.awt.Font("Dialog", 1, 13));
        lblCardAuditoriaValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardAuditoriaValue.setText("Ver historial de seguridad completo");
        pnlCardAuditoria.add(lblCardAuditoriaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jPanelTabUsuarios.add(pnlCardAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, 310, 90));

        Body.addTab("Usuarios", jPanelTabUsuarios);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Lista de usuarios exportada exitosamente.", "\u00c9xito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnEnviarInvitacionActionPerformed(java.awt.event.ActionEvent evt) {
        String correo = txtCorreo.getText().trim();
        String rol = cbRol.getSelectedItem().toString();

        if (correo.isEmpty() || correo.equals("colega@ejemplo.com")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un correo electr\u00f3nico v\u00e1lido.", "Validaci\u00f3n", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Add to mock rows in JTable
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        String initials = "\u2753"; // Question mark initials for pending
        if (correo.contains("@")) {
            String prefix = correo.split("@")[0];
            if (prefix.length() >= 2) {
                initials = prefix.substring(0, 2).toUpperCase();
            } else if (prefix.length() == 1) {
                initials = prefix.toUpperCase();
            }
        }
        
        String rolCode = "VIEWER";
        if (rol.contains("Editor")) {
            rolCode = "EDITOR";
        } else if (rol.contains("Admin")) {
            rolCode = "ADMIN";
        }

        model.addRow(new Object[]{initials + "|" + correo + "|Invitado recientemente", rolCode, "Pendiente", "RESEND_REVOKE"});

        JOptionPane.showMessageDialog(this, "Invitaci\u00f3n enviada exitosamente a: " + correo, "\u00c9xito", JOptionPane.INFORMATION_MESSAGE);
        txtCorreo.setText("colega@ejemplo.com");
    }

    private void btnPagPrevActionPerformed(java.awt.event.ActionEvent evt) {}
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
    private javax.swing.JTabbedPane Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JButton btnEnviarInvitacion;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnPagActive;
    private javax.swing.JButton btnPagNext;
    private javax.swing.JButton btnPagPrev;
    private javax.swing.JComboBox<String> cbRol;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCategories;
    private javax.swing.JLabel jLabelCustomers;
    private javax.swing.JLabel jLabelEmployes;
    private javax.swing.JLabel jLabelProducts;
    private javax.swing.JLabel jLabelPurchases;
    private javax.swing.JLabel jLabelSupplimers;
    private javax.swing.JPanel jPanelCategories;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployes;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JPanel jPanelSupplimers;
    private javax.swing.JPanel jPanelTabUsuarios;
    private javax.swing.JScrollPane jScrollPaneUsuarios;
    private javax.swing.JLabel lblAsientosTitle;
    private javax.swing.JLabel lblAsientosValue;
    private javax.swing.JLabel lblCardAuditoriaIcon;
    private javax.swing.JLabel lblCardAuditoriaTitle;
    private javax.swing.JLabel lblCardAuditoriaValue;
    private javax.swing.JLabel lblCardMfaIcon;
    private javax.swing.JLabel lblCardMfaTitle;
    private javax.swing.JLabel lblCardMfaValue;
    private javax.swing.JLabel lblCardSincronizacionIcon;
    private javax.swing.JLabel lblCardSincronizacionTitle;
    private javax.swing.JLabel lblCardSincronizacionValue;
    private javax.swing.JLabel lblConexionesCount;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblInvitarUsuarioTitle;
    private javax.swing.JLabel lblLicenciaDesc;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTabDesc;
    private javax.swing.JLabel lblTabTitle;
    private javax.swing.JLabel lblUsuariosAutorizadosTitle;
    private javax.swing.JProgressBar pbAsientos;
    private javax.swing.JPanel pnlCardAuditoria;
    private javax.swing.JPanel pnlCardMfa;
    private javax.swing.JPanel pnlCardSincronizacion;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JPanel pnlInvitarUsuario;
    private javax.swing.JPanel pnlUsuariosAutorizados;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
"""

with open(path_usuarios_java, "w", encoding="utf-8") as f:
    f.write(code_content)

print("UsuariosView.java generated successfully!")
