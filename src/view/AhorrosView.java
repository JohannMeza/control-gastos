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
import com.toedter.calendar.JDateChooser;

/**
 * Vista de Control de Ahorros.
 * Permite visualizar el total ahorrado, las metas de ahorro, registrar nuevos ahorros y ver el historial.
 */
public class AhorrosView extends javax.swing.JFrame {

    public AhorrosView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        setupTableStyle();
        setupFormDate();
    }

    private void setupFormDate() {
        if (dateFecha != null) {
            dateFecha.setDate(new Date());
        }
    }

    private void setupTableStyle() {
        tblAhorros.setRowHeight(35);
        tblAhorros.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tblAhorros.getTableHeader().setBackground(new Color(248, 250, 252));
        tblAhorros.getTableHeader().setForeground(new Color(71, 85, 105));
        
        // Custom renderer for META / FONDO (column 1) to show colored badges/pills
        tblAhorros.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String text = (value != null) ? value.toString() : "";
                
                if (text.equals("Fondo Emergencia")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #d1fae5; color: #065f46; border-radius: 12px; font-weight: bold;'>" + text + "</body></html>");
                } else if (text.equals("Vacaciones")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #dbeafe; color: #1e40af; border-radius: 12px; font-weight: bold;'>" + text + "</body></html>");
                } else if (text.equals("Auto Nuevo")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #ffedd5; color: #9a3412; border-radius: 12px; font-weight: bold;'>" + text + "</body></html>");
                } else {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #f1f5f9; color: #334155; border-radius: 12px; font-weight: bold;'>" + text + "</body></html>");
                }
                label.setOpaque(true);
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        // Custom renderer for Username and Avatar (column 3)
        tblAhorros.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.LEADING);
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png")));
                label.setIconTextGap(8);
                return label;
            }
        });
        
        // Align actions and others
        tblAhorros.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.CENTER); }
        });
        tblAhorros.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.RIGHT); setFont(new Font("Dialog", Font.BOLD, 12)); }
        });
        tblAhorros.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.CENTER); setForeground(Color.GRAY); }
        });
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
        jPanelTabAhorros = new javax.swing.JPanel();
        pnlCardTotalAhorrado = new javax.swing.JPanel();
        lblCardTotalAhorradoTitle = new javax.swing.JLabel();
        lblCardTotalAhorradoValue = new javax.swing.JLabel();
        lblCardTotalAhorradoChange = new javax.swing.JLabel();
        pnlCardMetaMensual = new javax.swing.JPanel();
        lblCardMetaMensualTitle = new javax.swing.JLabel();
        lblCardMetaMensualValue = new javax.swing.JLabel();
        pbMetaMensual = new javax.swing.JProgressBar();
        lblCardMetaMensualDesc = new javax.swing.JLabel();
        pnlCardEficiencia = new javax.swing.JPanel();
        lblEficienciaTitle = new javax.swing.JLabel();
        lblEficienciaValue = new javax.swing.JLabel();
        lblLevelUp = new javax.swing.JLabel();
        lblMetasGlobalesTitle = new javax.swing.JLabel();
        pbMetasGlobales = new javax.swing.JProgressBar();
        lblFondoEmergenciaTitle = new javax.swing.JLabel();
        pbFondoEmergencia = new javax.swing.JProgressBar();
        pnlRegistrarAhorro = new javax.swing.JPanel();
        lblRegistrarAhorroTitle = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        lblMetaFondo = new javax.swing.JLabel();
        cbMetaFondo = new javax.swing.JComboBox();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnRegistrarAhorro = new javax.swing.JButton();
        pnlConsejo = new javax.swing.JPanel();
        lblConsejoTitle = new javax.swing.JLabel();
        lblConsejoText = new javax.swing.JLabel();
        lblConsejoIcon = new javax.swing.JLabel();
        pnlMetasAhorro = new javax.swing.JPanel();
        lblMetasAhorroTitle = new javax.swing.JLabel();
        lblVerTodasMetas = new javax.swing.JLabel();
        pnlMetaFondoEmergencia = new javax.swing.JPanel();
        lblFondoEmergenciaPercent = new javax.swing.JLabel();
        lblFondoEmergenciaIcon = new javax.swing.JLabel();
        lblFondoEmergenciaName = new javax.swing.JLabel();
        lblFondoEmergenciaProgressText = new javax.swing.JLabel();
        pbFondoEmergenciaMeta = new javax.swing.JProgressBar();
        lblFondoEmergenciaEst = new javax.swing.JLabel();
        pnlMetaVacaciones = new javax.swing.JPanel();
        lblVacacionesPercent = new javax.swing.JLabel();
        lblVacacionesIcon = new javax.swing.JLabel();
        lblVacacionesName = new javax.swing.JLabel();
        lblVacacionesProgressText = new javax.swing.JLabel();
        pbVacacionesMeta = new javax.swing.JProgressBar();
        lblVacacionesEst = new javax.swing.JLabel();
        pnlMetaAuto = new javax.swing.JPanel();
        lblAutoPercent = new javax.swing.JLabel();
        lblAutoIcon = new javax.swing.JLabel();
        lblAutoName = new javax.swing.JLabel();
        lblAutoProgressText = new javax.swing.JLabel();
        pbAutoMeta = new javax.swing.JProgressBar();
        lblAutoEst = new javax.swing.JLabel();
        pnlHistorialAhorros = new javax.swing.JPanel();
        lblHistorialAhorrosTitle = new javax.swing.JLabel();
        btnFiltros = new javax.swing.JButton();
        btnExportarCSV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAhorros = new javax.swing.JTable();
        lblMostrarTransacciones = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        btnPag1 = new javax.swing.JButton();
        btnPag2 = new javax.swing.JButton();
        btnPag3 = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        Header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Logo = new javax.swing.JPanel();
        lblLogoImg = new javax.swing.JLabel();

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

        jPanelEmployes.setBackground(new java.awt.Color(244, 248, 254));
        jPanelEmployes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));

        jLabelEmployes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelEmployes.setForeground(new java.awt.Color(37, 99, 235));
        jLabelEmployes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/ahorros.png"))); // NOI18N
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

        jPanelCategories.setBackground(new java.awt.Color(248, 250, 252));
        jPanelCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCategoriesMouseClicked(evt);
            }
        });

        jLabelCategories.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategories.setForeground(new java.awt.Color(71, 85, 105));
        jLabelCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
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

        jPanelTabAhorros.setBackground(new java.awt.Color(248, 249, 255));
        jPanelTabAhorros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCardTotalAhorrado.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardTotalAhorrado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardTotalAhorrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardTotalAhorradoTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardTotalAhorradoTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardTotalAhorradoTitle.setText("Total Ahorrado");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardTotalAhorradoValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardTotalAhorradoValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardTotalAhorradoValue.setText("$12,450.00");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        lblCardTotalAhorradoChange.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardTotalAhorradoChange.setForeground(new java.awt.Color(0, 108, 73));
        lblCardTotalAhorradoChange.setText("+4.2% desde el mes pasado");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        jPanelTabAhorros.add(pnlCardTotalAhorrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 100));

        pnlCardMetaMensual.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardMetaMensual.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardMetaMensual.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardMetaMensualTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardMetaMensualTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardMetaMensualTitle.setText("Meta Mensual");
        pnlCardMetaMensual.add(lblCardMetaMensualTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardMetaMensualValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardMetaMensualValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardMetaMensualValue.setText("$1,500.00");
        pnlCardMetaMensual.add(lblCardMetaMensualValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        pbMetaMensual.setBackground(new java.awt.Color(241, 245, 249));
        pbMetaMensual.setForeground(new java.awt.Color(249, 115, 22));
        pbMetaMensual.setValue(75);
        pbMetaMensual.setBorderPainted(false);
        pnlCardMetaMensual.add(pbMetaMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 70, 270, 6));

        lblCardMetaMensualDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblCardMetaMensualDesc.setText("$1,125.00 ahorrados hoy");
        pnlCardMetaMensual.add(lblCardMetaMensualDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 80, -1, -1));

        jPanelTabAhorros.add(pnlCardMetaMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 300, 100));

        pnlCardEficiencia.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardEficiencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardEficiencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEficienciaTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblEficienciaTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblEficienciaTitle.setText("Eficiencia de Ahorro");
        pnlCardEficiencia.add(lblEficienciaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, -1, -1));

        lblEficienciaValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEficienciaValue.setForeground(new java.awt.Color(11, 28, 48));
        lblEficienciaValue.setText("82%");
        pnlCardEficiencia.add(lblEficienciaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 28, -1, -1));

        lblLevelUp.setBackground(new java.awt.Color(34, 197, 94));
        lblLevelUp.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblLevelUp.setForeground(new java.awt.Color(255, 255, 255));
        lblLevelUp.setText("LEVEL UP");
        pnlCardEficiencia.add(lblLevelUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 10, 65, 18));

        lblMetasGlobalesTitle.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblMetasGlobalesTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblMetasGlobalesTitle.setText("METAS GLOBALES");
        pnlCardEficiencia.add(lblMetasGlobalesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 58, -1, -1));

        pbMetasGlobales.setBackground(new java.awt.Color(241, 245, 249));
        pbMetasGlobales.setForeground(new java.awt.Color(37, 99, 235));
        pbMetasGlobales.setValue(82);
        pbMetasGlobales.setBorderPainted(false);
        pnlCardEficiencia.add(pbMetasGlobales, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 70, 130, 8));

        lblFondoEmergenciaTitle.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblFondoEmergenciaTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblFondoEmergenciaTitle.setText("FONDO EMERGENCIA");
        pnlCardEficiencia.add(lblFondoEmergenciaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 58, -1, -1));

        pbFondoEmergencia.setBackground(new java.awt.Color(241, 245, 249));
        pbFondoEmergencia.setForeground(new java.awt.Color(34, 197, 94));
        pbFondoEmergencia.setValue(60);
        pbFondoEmergencia.setBorderPainted(false);
        pnlCardEficiencia.add(pbFondoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 70, 130, 8));

        jPanelTabAhorros.add(pnlCardEficiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 315, 100));

        pnlRegistrarAhorro.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistrarAhorro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlRegistrarAhorro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistrarAhorroTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRegistrarAhorroTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistrarAhorroTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/agregar.png"))); // NOI18N
        lblRegistrarAhorroTitle.setText(" Registrar Ahorro");
        pnlRegistrarAhorro.add(lblRegistrarAhorroTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        lblMonto.setForeground(new java.awt.Color(71, 85, 105));
        lblMonto.setText("Monto ($)");
        pnlRegistrarAhorro.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 48, -1, -1));

        txtMonto.setText("0.00");
        txtMonto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlRegistrarAhorro.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, 300, 30));

        lblFecha.setForeground(new java.awt.Color(71, 85, 105));
        lblFecha.setText("Fecha");
        pnlRegistrarAhorro.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, -1, -1));

        dateFecha.setBackground(new java.awt.Color(255, 255, 255));
        dateFecha.setDateFormatString("dd/MM/yyyy");
        pnlRegistrarAhorro.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 125, 140, 30));

        lblMetaFondo.setForeground(new java.awt.Color(71, 85, 105));
        lblMetaFondo.setText("Meta/Fondo");
        pnlRegistrarAhorro.add(lblMetaFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 105, -1, -1));

        cbMetaFondo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "", "" }));
        pnlRegistrarAhorro.add(cbMetaFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 125, 140, 30));

        lblDescripcion.setForeground(new java.awt.Color(71, 85, 105));
        lblDescripcion.setText("Descripción");
        pnlRegistrarAhorro.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 162, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        txtDescripcion.setText("Nota adicional...");
        txtDescripcion.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        txtDescripcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        jScrollPane2.setViewportView(txtDescripcion);

        pnlRegistrarAhorro.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 300, 50));

        btnRegistrarAhorro.setBackground(new java.awt.Color(37, 99, 235));
        btnRegistrarAhorro.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRegistrarAhorro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAhorro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/limpio.png"))); // NOI18N
        btnRegistrarAhorro.setText(" Registrar Ahorro");
        btnRegistrarAhorro.setBorder(null);
        btnRegistrarAhorro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarAhorro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAhorroActionPerformed(evt);
            }
        });
        pnlRegistrarAhorro.add(btnRegistrarAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 300, 30));

        jPanelTabAhorros.add(pnlRegistrarAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 340, 285));

        pnlConsejo.setBackground(new java.awt.Color(11, 28, 48));
        pnlConsejo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlConsejo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsejoTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblConsejoTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblConsejoTitle.setText("Consejo Financiero");
        pnlConsejo.add(lblConsejoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));

        lblConsejoText.setForeground(new java.awt.Color(199, 241, 232));
        lblConsejoText.setText("<html>Ahorrar el 20% de tus ingresos este mes aumentará tu proyección anual en $1,200.</html>");
        pnlConsejo.add(lblConsejoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 28, 240, 45));

        lblConsejoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConsejoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/estadisticas.png"))); // NOI18N
        pnlConsejo.add(lblConsejoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 40, 40));

        jPanelTabAhorros.add(pnlConsejo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 340, 85));

        pnlMetasAhorro.setBackground(new java.awt.Color(255, 255, 255));
        pnlMetasAhorro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlMetasAhorro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMetasAhorroTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblMetasAhorroTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblMetasAhorroTitle.setText("Metas de Ahorro");
        pnlMetasAhorro.add(lblMetasAhorroTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));

        lblVerTodasMetas.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblVerTodasMetas.setForeground(new java.awt.Color(37, 99, 235));
        lblVerTodasMetas.setText("Ver todas las metas");
        lblVerTodasMetas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMetasAhorro.add(lblVerTodasMetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 14, -1, -1));

        pnlMetaFondoEmergencia.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaFondoEmergencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlMetaFondoEmergencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFondoEmergenciaPercent.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblFondoEmergenciaPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblFondoEmergenciaPercent.setText("95%");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 10, -1, -1));

        lblFondoEmergenciaIcon.setBackground(new java.awt.Color(255, 255, 255));
        lblFondoEmergenciaIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondoEmergenciaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/ahorros.png"))); // NOI18N
        lblFondoEmergenciaIcon.setOpaque(true);
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 26, 26));

        lblFondoEmergenciaName.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblFondoEmergenciaName.setForeground(new java.awt.Color(11, 28, 48));
        lblFondoEmergenciaName.setText("Fondo Emergencia");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaName, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, -1, -1));

        lblFondoEmergenciaProgressText.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblFondoEmergenciaProgressText.setForeground(new java.awt.Color(71, 85, 105));
        lblFondoEmergenciaProgressText.setText("$4,750 / $5,000");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, -1, -1));

        pbFondoEmergenciaMeta.setBackground(new java.awt.Color(226, 232, 240));
        pbFondoEmergenciaMeta.setForeground(new java.awt.Color(34, 197, 94));
        pbFondoEmergenciaMeta.setValue(95);
        pbFondoEmergenciaMeta.setBorderPainted(false);
        pnlMetaFondoEmergencia.add(pbFondoEmergenciaMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, 155, 4));

        lblFondoEmergenciaEst.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        lblFondoEmergenciaEst.setForeground(new java.awt.Color(100, 116, 139));
        lblFondoEmergenciaEst.setText("Est. Ago 2024");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 85, -1, -1));

        pnlMetasAhorro.add(pnlMetaFondoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 38, 180, 105));

        pnlMetaVacaciones.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaVacaciones.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlMetaVacaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVacacionesPercent.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblVacacionesPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblVacacionesPercent.setText("45%");
        pnlMetaVacaciones.add(lblVacacionesPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 10, -1, -1));

        lblVacacionesIcon.setBackground(new java.awt.Color(255, 255, 255));
        lblVacacionesIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVacacionesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aplicaciones.png"))); // NOI18N
        lblVacacionesIcon.setOpaque(true);
        pnlMetaVacaciones.add(lblVacacionesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 26, 26));

        lblVacacionesName.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblVacacionesName.setForeground(new java.awt.Color(11, 28, 48));
        lblVacacionesName.setText("Vacaciones");
        pnlMetaVacaciones.add(lblVacacionesName, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, -1, -1));

        lblVacacionesProgressText.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblVacacionesProgressText.setForeground(new java.awt.Color(71, 85, 105));
        lblVacacionesProgressText.setText("$1,350 / $3,000");
        pnlMetaVacaciones.add(lblVacacionesProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, -1, -1));

        pbVacacionesMeta.setBackground(new java.awt.Color(226, 232, 240));
        pbVacacionesMeta.setForeground(new java.awt.Color(37, 99, 235));
        pbVacacionesMeta.setValue(45);
        pbVacacionesMeta.setBorderPainted(false);
        pnlMetaVacaciones.add(pbVacacionesMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, 155, 4));

        lblVacacionesEst.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        lblVacacionesEst.setForeground(new java.awt.Color(100, 116, 139));
        lblVacacionesEst.setText("Est. Dic 2024");
        pnlMetaVacaciones.add(lblVacacionesEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 85, -1, -1));

        pnlMetasAhorro.add(pnlMetaVacaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 38, 180, 105));

        pnlMetaAuto.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaAuto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlMetaAuto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAutoPercent.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        lblAutoPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblAutoPercent.setText("15%");
        pnlMetaAuto.add(lblAutoPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 10, -1, -1));

        lblAutoIcon.setBackground(new java.awt.Color(255, 255, 255));
        lblAutoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAutoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deudas.png"))); // NOI18N
        lblAutoIcon.setOpaque(true);
        pnlMetaAuto.add(lblAutoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 26, 26));

        lblAutoName.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblAutoName.setForeground(new java.awt.Color(11, 28, 48));
        lblAutoName.setText("Auto Nuevo");
        pnlMetaAuto.add(lblAutoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, -1, -1));

        lblAutoProgressText.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblAutoProgressText.setForeground(new java.awt.Color(71, 85, 105));
        lblAutoProgressText.setText("$3,750 / $25,000");
        pnlMetaAuto.add(lblAutoProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, -1, -1));

        pbAutoMeta.setBackground(new java.awt.Color(226, 232, 240));
        pbAutoMeta.setForeground(new java.awt.Color(249, 115, 22));
        pbAutoMeta.setValue(15);
        pbAutoMeta.setBorderPainted(false);
        pnlMetaAuto.add(pbAutoMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, 155, 4));

        lblAutoEst.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        lblAutoEst.setForeground(new java.awt.Color(100, 116, 139));
        lblAutoEst.setText("Est. Jun 2026");
        pnlMetaAuto.add(lblAutoEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 85, -1, -1));

        pnlMetasAhorro.add(pnlMetaAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 38, 180, 105));

        jPanelTabAhorros.add(pnlMetasAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 610, 150));

        pnlHistorialAhorros.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorialAhorros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlHistorialAhorros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHistorialAhorrosTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHistorialAhorrosTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHistorialAhorrosTitle.setText("Historial de Ahorros");
        pnlHistorialAhorros.add(lblHistorialAhorrosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        btnFiltros.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltros.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnFiltros.setForeground(new java.awt.Color(71, 85, 105));
        btnFiltros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/filtrar.png"))); // NOI18N
        btnFiltros.setText(" Filtros");
        btnFiltros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnFiltros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHistorialAhorros.add(btnFiltros, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 85, 28));

        btnExportarCSV.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(71, 85, 105));
        btnExportarCSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/descargar.png"))); // NOI18N
        btnExportarCSV.setText(" Exportar CSV");
        btnExportarCSV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHistorialAhorros.add(btnExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 10, 95, 28));

        tblAhorros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "META / FONDO", "MONTO", "USUARIO", "ACCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAhorros.setSelectionBackground(new java.awt.Color(234, 241, 255));
        tblAhorros.setSelectionForeground(new java.awt.Color(11, 28, 48));
        jScrollPane1.setViewportView(tblAhorros);

        pnlHistorialAhorros.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 48, 580, 115));

        lblMostrarTransacciones.setForeground(new java.awt.Color(100, 116, 139));
        lblMostrarTransacciones.setText("Mostrando 5 de 128 transacciones");
        pnlHistorialAhorros.add(lblMostrarTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 172, -1, -1));

        btnAnterior.setBackground(new java.awt.Color(255, 255, 255));
        btnAnterior.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(100, 116, 139));
        btnAnterior.setText("Anterior");
        btnAnterior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 168, 55, 22));

        btnPag1.setBackground(new java.awt.Color(37, 99, 235));
        btnPag1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnPag1.setForeground(new java.awt.Color(255, 255, 255));
        btnPag1.setText("1");
        btnPag1.setBorder(null);
        pnlHistorialAhorros.add(btnPag1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 168, 22, 22));

        btnPag2.setBackground(new java.awt.Color(255, 255, 255));
        btnPag2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        btnPag2.setForeground(new java.awt.Color(100, 116, 139));
        btnPag2.setText("2");
        btnPag2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnPag2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 168, 22, 22));

        btnPag3.setBackground(new java.awt.Color(255, 255, 255));
        btnPag3.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        btnPag3.setForeground(new java.awt.Color(100, 116, 139));
        btnPag3.setText("3");
        btnPag3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnPag3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 168, 22, 22));

        btnSiguiente.setBackground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(100, 116, 139));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 168, 60, 22));

        jPanelTabAhorros.add(pnlHistorialAhorros, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 305, 610, 205));

        Body.addTab("Ahorros", jPanelTabAhorros);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(234, 241, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 28, 48));
        jLabel2.setText("Gastos");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        Logo.setBackground(new java.awt.Color(248, 250, 252));
        Logo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogoImg.setBackground(new java.awt.Color(248, 250, 252));
        lblLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo.png"))); // NOI18N
        Logo.add(lblLogoImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarAhorroActionPerformed(java.awt.event.ActionEvent evt) {
        String monto = txtMonto.getText().trim();
        Date fecha = dateFecha.getDate();
        String meta = cbMetaFondo.getSelectedItem().toString();
        String desc = txtDescripcion.getText().trim();

        if (monto.isEmpty() || monto.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Add mock row dynamically to table
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        DefaultTableModel model = (DefaultTableModel) tblAhorros.getModel();
        model.insertRow(0, new Object[]{sdf.format(fecha), meta, "$" + monto, "Admin_Finance", "..."});

        // Show confirmation dialog
        JOptionPane.showMessageDialog(this, "¡Ahorro registrado exitosamente para: " + meta + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Clear input form
        txtMonto.setText("0.00");
        txtDescripcion.setText("Nota adicional...");
        dateFecha.setDate(new Date());
    }

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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AhorrosView().setVisible(true);
            }
        });
    }

        private void jPanelSupplimersMouseClicked(java.awt.event.MouseEvent evt) {
        DeudasView viewDeudas = new DeudasView();
        viewDeudas.setVisible(true);
        this.dispose();
    }

        private void jPanelCategoriesMouseClicked(java.awt.event.MouseEvent evt) {
        UsuariosView viewUsuarios = new UsuariosView();
        viewUsuarios.setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnExportarCSV;
    private javax.swing.JButton btnFiltros;
    private javax.swing.JButton btnPag1;
    private javax.swing.JButton btnPag2;
    private javax.swing.JButton btnPag3;
    private javax.swing.JButton btnRegistrarAhorro;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox cbMetaFondo;
    private com.toedter.calendar.JDateChooser dateFecha;
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
    private javax.swing.JPanel jPanelTabAhorros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAutoEst;
    private javax.swing.JLabel lblAutoIcon;
    private javax.swing.JLabel lblAutoName;
    private javax.swing.JLabel lblAutoPercent;
    private javax.swing.JLabel lblAutoProgressText;
    private javax.swing.JLabel lblCardMetaMensualDesc;
    private javax.swing.JLabel lblCardMetaMensualTitle;
    private javax.swing.JLabel lblCardMetaMensualValue;
    private javax.swing.JLabel lblCardTotalAhorradoChange;
    private javax.swing.JLabel lblCardTotalAhorradoTitle;
    private javax.swing.JLabel lblCardTotalAhorradoValue;
    private javax.swing.JLabel lblConsejoIcon;
    private javax.swing.JLabel lblConsejoText;
    private javax.swing.JLabel lblConsejoTitle;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEficienciaTitle;
    private javax.swing.JLabel lblEficienciaValue;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFondoEmergenciaEst;
    private javax.swing.JLabel lblFondoEmergenciaIcon;
    private javax.swing.JLabel lblFondoEmergenciaName;
    private javax.swing.JLabel lblFondoEmergenciaPercent;
    private javax.swing.JLabel lblFondoEmergenciaProgressText;
    private javax.swing.JLabel lblFondoEmergenciaTitle;
    private javax.swing.JLabel lblHistorialAhorrosTitle;
    private javax.swing.JLabel lblLevelUp;
    private javax.swing.JLabel lblLogoImg;
    private javax.swing.JLabel lblMetaFondo;
    private javax.swing.JLabel lblMetasAhorroTitle;
    private javax.swing.JLabel lblMetasGlobalesTitle;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMostrarTransacciones;
    private javax.swing.JLabel lblRegistrarAhorroTitle;
    private javax.swing.JLabel lblVacacionesEst;
    private javax.swing.JLabel lblVacacionesIcon;
    private javax.swing.JLabel lblVacacionesName;
    private javax.swing.JLabel lblVacacionesPercent;
    private javax.swing.JLabel lblVacacionesProgressText;
    private javax.swing.JLabel lblVerTodasMetas;
    private javax.swing.JProgressBar pbAutoMeta;
    private javax.swing.JProgressBar pbFondoEmergencia;
    private javax.swing.JProgressBar pbFondoEmergenciaMeta;
    private javax.swing.JProgressBar pbMetaMensual;
    private javax.swing.JProgressBar pbMetasGlobales;
    private javax.swing.JProgressBar pbVacacionesMeta;
    private javax.swing.JPanel pnlCardEficiencia;
    private javax.swing.JPanel pnlCardMetaMensual;
    private javax.swing.JPanel pnlCardTotalAhorrado;
    private javax.swing.JPanel pnlConsejo;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JPanel pnlHistorialAhorros;
    private javax.swing.JPanel pnlMetaAuto;
    private javax.swing.JPanel pnlMetaFondoEmergencia;
    private javax.swing.JPanel pnlMetaVacaciones;
    private javax.swing.JPanel pnlMetasAhorro;
    private javax.swing.JPanel pnlRegistrarAhorro;
    private javax.swing.JTable tblAhorros;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
