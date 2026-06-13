import os

path_deudas_java = r"d:\Estudios\Superiores\Universidad UTP\Ciclo 7\Algoritmos y Estructura de Datos\Semana 5\ControlGastos\src\view\DeudasView.java"

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
 * Vista de Control de Deudas y Préstamos.
 */
public class DeudasView extends javax.swing.JFrame {

    public DeudasView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        setupTableStyle();
        setupFormDates();
        addMockData();
        this.repaint();
    }

    private void setupTableStyle() {
        tblHistorial.setRowHeight(35);
        tblHistorial.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tblHistorial.getTableHeader().setBackground(new Color(248, 250, 252));
        tblHistorial.getTableHeader().setForeground(new Color(71, 85, 105));
        
        // Custom renderer for Monto Pagado (Column 2) to show green bold text
        tblHistorial.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.BOLD, 12));
                label.setForeground(new Color(6, 95, 70)); // Green color
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        // Column alignments
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblHistorial.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // FECHA
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblHistorial.getColumnModel().getColumn(3).setCellRenderer(rightRenderer); // SALDO RESTANTE
        
        // Setup card badges using HTML formatting for pills
        lblDebt1Badge.setText("<html><body style='padding: 2px 8px; background-color: #d1fae5; color: #065f46; border-radius: 8px; font-weight: bold;'>AL D\u00cdA</body></html>");
        lblDebt2Badge.setText("<html><body style='padding: 2px 8px; background-color: #fee2e2; color: #991b1b; border-radius: 8px; font-weight: bold;'>PR\u00d3XIMO A VENCER</body></html>");
        lblDebt3Badge.setText("<html><body style='padding: 2px 8px; background-color: #dbeafe; color: #1e40af; border-radius: 8px; font-weight: bold;'>PENDIENTE</body></html>");
        
        // Compound border for card 2 (left red accent)
        pnlDebt2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(239, 68, 68)),
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true)
        ));
    }

    private void setupFormDates() {
        if (dateFechaInicio != null) {
            dateFechaInicio.setDate(new Date());
        }
        if (dateFechaPago != null) {
            dateFechaPago.setDate(new Date());
        }
    }

    private void addMockData() {
        DefaultTableModel model = (DefaultTableModel) tblHistorial.getModel();
        model.addRow(new Object[]{"08/10/2023", "Visa Oro - Santander", "+$1,200.00", "$3,300.00"});
        model.addRow(new Object[]{"05/10/2023", "Hipotecario - BBVA", "+$2,450.00", "$95,050.00"});
        model.addRow(new Object[]{"28/09/2023", "Automotriz - Ford", "+$480.00", "$9,855.00"});
        model.addRow(new Object[]{"15/09/2023", "Visa Oro - Santander", "+$350.00", "$4,500.00"});
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
        jPanelTabDeudas = new javax.swing.JPanel();
        pnlCardDeudaTotal = new javax.swing.JPanel();
        lblCardDeudaTotalIcon = new javax.swing.JLabel();
        lblCardDeudaTotalTitle = new javax.swing.JLabel();
        lblCardDeudaTotalValue = new javax.swing.JLabel();
        pnlCardProximoVencimiento = new javax.swing.JPanel();
        lblCardProximoVencimientoIcon = new javax.swing.JLabel();
        lblCardProximoVencimientoTitle = new javax.swing.JLabel();
        lblCardProximoVencimientoValue = new javax.swing.JLabel();
        pnlCardTotalPagado = new javax.swing.JPanel();
        lblCardTotalPagadoIcon = new javax.swing.JLabel();
        lblCardTotalPagadoTitle = new javax.swing.JLabel();
        lblCardTotalPagadoValue = new javax.swing.JLabel();
        pnlNuevaDeuda = new javax.swing.JPanel();
        lblNuevaDeudaTitle = new javax.swing.JLabel();
        lblAcreedor = new javax.swing.JLabel();
        txtAcreedor = new javax.swing.JTextField();
        lblMontoTotal = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        lblTasaInteres = new javax.swing.JLabel();
        txtTasaInteres = new javax.swing.JTextField();
        lblCuotasTotales = new javax.swing.JLabel();
        txtCuotasTotales = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        btnRegistrarDeuda = new javax.swing.JButton();
        pnlRegistrarAbono = new javax.swing.JPanel();
        lblRegistrarAbonoTitle = new javax.swing.JLabel();
        lblSeleccionarDeuda = new javax.swing.JLabel();
        cbSeleccionarDeuda = new javax.swing.JComboBox<>();
        lblMontoAbono = new javax.swing.JLabel();
        txtMontoAbono = new javax.swing.JTextField();
        lblFechaPago = new javax.swing.JLabel();
        dateFechaPago = new com.toedter.calendar.JDateChooser();
        btnConfirmarPago = new javax.swing.JButton();
        pnlDeudasActivas = new javax.swing.JPanel();
        lblDeudasActivasTitle = new javax.swing.JLabel();
        lblDeudasActivasSubtitle = new javax.swing.JLabel();
        pnlDebt1 = new javax.swing.JPanel();
        lblDebt1Title = new javax.swing.JLabel();
        lblDebt1Desc = new javax.swing.JLabel();
        lblDebt1Badge = new javax.swing.JLabel();
        pbDebt1 = new javax.swing.JProgressBar();
        lblDebt1ProgressText = new javax.swing.JLabel();
        lblDebt1Percent = new javax.swing.JLabel();
        lblDebt1SaldoTitle = new javax.swing.JLabel();
        lblDebt1SaldoValue = new javax.swing.JLabel();
        pnlDebt2 = new javax.swing.JPanel();
        lblDebt2Title = new javax.swing.JLabel();
        lblDebt2Desc = new javax.swing.JLabel();
        lblDebt2Badge = new javax.swing.JLabel();
        lblDebt2WarningIcon = new javax.swing.JLabel();
        lblDebt2WarningText = new javax.swing.JLabel();
        lblDebt2PayNow = new javax.swing.JLabel();
        pnlDebt3 = new javax.swing.JPanel();
        lblDebt3Title = new javax.swing.JLabel();
        lblDebt3Desc = new javax.swing.JLabel();
        lblDebt3Badge = new javax.swing.JLabel();
        pbDebt3 = new javax.swing.JProgressBar();
        lblDebt3ProgressText = new javax.swing.JLabel();
        lblDebt3Percent = new javax.swing.JLabel();
        lblDebt3SaldoTitle = new javax.swing.JLabel();
        lblDebt3SaldoValue = new javax.swing.JLabel();
        pnlHistorialReciente = new javax.swing.JPanel();
        lblHistorialRecienteTitle = new javax.swing.JLabel();
        lblExportarCSV = new javax.swing.JLabel();
        jScrollPaneDeudas = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();

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

        jPanelSupplimers.setBackground(new java.awt.Color(244, 248, 254));
        jPanelSupplimers.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));

        jLabelSupplimers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSupplimers.setForeground(new java.awt.Color(37, 99, 235));
        jLabelSupplimers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/deudas.png"))); // NOI18N
        jLabelSupplimers.setText("Deudas");

        javax.swing.GroupLayout jPanelSupplimersLayout = new javax.swing.GroupLayout(jPanelSupplimers);
        jPanelSupplimers.setLayout(jPanelSupplimersLayout);
        jPanelSupplimersLayout.setHorizontalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSupplimersLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelSupplimers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSupplimersLayout.setVerticalGroup(
            jPanelSupplimersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSupplimers, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelCategories.setBackground(new java.awt.Color(248, 250, 252));

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
        jLabel2.setText("Control de Deudas y Pr\u00e9stamos");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        jPanelTabDeudas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // --- Card Deuda Total ---
        pnlCardDeudaTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardDeudaTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardDeudaTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblCardDeudaTotalIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/deudas.png")));
        pnlCardDeudaTotal.add(lblCardDeudaTotalIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));
        
        lblCardDeudaTotalTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardDeudaTotalTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardDeudaTotalTitle.setText("DEUDA TOTAL");
        pnlCardDeudaTotal.add(lblCardDeudaTotalTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        
        lblCardDeudaTotalValue.setFont(new java.awt.Font("Dialog", 1, 24));
        lblCardDeudaTotalValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardDeudaTotalValue.setText("$45,280.00");
        pnlCardDeudaTotal.add(lblCardDeudaTotalValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));
        
        jPanelTabDeudas.add(pnlCardDeudaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 310, 90));

        // --- Card Próximo Vencimiento ---
        pnlCardProximoVencimiento.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardProximoVencimiento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardProximoVencimiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblCardProximoVencimientoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png")));
        pnlCardProximoVencimiento.add(lblCardProximoVencimientoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));
        
        lblCardProximoVencimientoTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardProximoVencimientoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardProximoVencimientoTitle.setText("PR\u00d3XIMO VENCIMIENTO");
        pnlCardProximoVencimiento.add(lblCardProximoVencimientoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        
        lblCardProximoVencimientoValue.setFont(new java.awt.Font("Dialog", 1, 24));
        lblCardProximoVencimientoValue.setForeground(new java.awt.Color(154, 52, 18));
        lblCardProximoVencimientoValue.setText("Oct 15, 2023");
        pnlCardProximoVencimiento.add(lblCardProximoVencimientoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));
        
        jPanelTabDeudas.add(pnlCardProximoVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 15, 310, 90));

        // --- Card Total Pagado ---
        pnlCardTotalPagado.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardTotalPagado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardTotalPagado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblCardTotalPagadoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png")));
        pnlCardTotalPagado.add(lblCardTotalPagadoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));
        
        lblCardTotalPagadoTitle.setFont(new java.awt.Font("Dialog", 1, 11));
        lblCardTotalPagadoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardTotalPagadoTitle.setText("TOTAL PAGADO ESTE MES");
        pnlCardTotalPagado.add(lblCardTotalPagadoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));
        
        lblCardTotalPagadoValue.setFont(new java.awt.Font("Dialog", 1, 24));
        lblCardTotalPagadoValue.setForeground(new java.awt.Color(6, 95, 70));
        lblCardTotalPagadoValue.setText("$3,450.00");
        pnlCardTotalPagado.add(lblCardTotalPagadoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));
        
        jPanelTabDeudas.add(pnlCardTotalPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 15, 320, 90));

        // --- Box Nueva Deuda ---
        pnlNuevaDeuda.setBackground(new java.awt.Color(255, 255, 255));
        pnlNuevaDeuda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlNuevaDeuda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblNuevaDeudaTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblNuevaDeudaTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblNuevaDeudaTitle.setText("Nueva Deuda");
        pnlNuevaDeuda.add(lblNuevaDeudaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));
        
        lblAcreedor.setFont(new java.awt.Font("Dialog", 0, 11));
        lblAcreedor.setForeground(new java.awt.Color(71, 85, 105));
        lblAcreedor.setText("Acreedor / Entidad");
        pnlNuevaDeuda.add(lblAcreedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));
        
        txtAcreedor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlNuevaDeuda.add(txtAcreedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 58, 280, 26));
        
        lblMontoTotal.setFont(new java.awt.Font("Dialog", 0, 11));
        lblMontoTotal.setForeground(new java.awt.Color(71, 85, 105));
        lblMontoTotal.setText("Monto Total");
        pnlNuevaDeuda.add(lblMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 92, -1, -1));
        
        txtMontoTotal.setText("0.00");
        txtMontoTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlNuevaDeuda.add(txtMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, 135, 26));
        
        lblTasaInteres.setFont(new java.awt.Font("Dialog", 0, 11));
        lblTasaInteres.setForeground(new java.awt.Color(71, 85, 105));
        lblTasaInteres.setText("Tasa de Inter\u00e9s (%)");
        pnlNuevaDeuda.add(lblTasaInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 92, -1, -1));
        
        txtTasaInteres.setText("0.0");
        txtTasaInteres.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlNuevaDeuda.add(txtTasaInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 135, 26));
        
        lblCuotasTotales.setFont(new java.awt.Font("Dialog", 0, 11));
        lblCuotasTotales.setForeground(new java.awt.Color(71, 85, 105));
        lblCuotasTotales.setText("Cuotas Totales");
        pnlNuevaDeuda.add(lblCuotasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 144, -1, -1));
        
        txtCuotasTotales.setText("12");
        txtCuotasTotales.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlNuevaDeuda.add(txtCuotasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 162, 135, 26));
        
        lblFechaInicio.setFont(new java.awt.Font("Dialog", 0, 11));
        lblFechaInicio.setForeground(new java.awt.Color(71, 85, 105));
        lblFechaInicio.setText("Fecha Inicio");
        pnlNuevaDeuda.add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 144, -1, -1));
        
        dateFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        dateFechaInicio.setDateFormatString("dd/MM/yyyy");
        pnlNuevaDeuda.add(dateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 162, 135, 26));
        
        btnRegistrarDeuda.setBackground(new java.awt.Color(37, 99, 235));
        btnRegistrarDeuda.setFont(new java.awt.Font("Dialog", 1, 12));
        btnRegistrarDeuda.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarDeuda.setText("Registrar Deuda");
        btnRegistrarDeuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarDeudaActionPerformed(evt);
            }
        });
        pnlNuevaDeuda.add(btnRegistrarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 208, 280, 34));
        
        jPanelTabDeudas.add(pnlNuevaDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 310, 260));

        // --- Box Registrar Abono ---
        pnlRegistrarAbono.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistrarAbono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlRegistrarAbono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblRegistrarAbonoTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblRegistrarAbonoTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistrarAbonoTitle.setText("Registrar Abono");
        pnlRegistrarAbono.add(lblRegistrarAbonoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));
        
        lblSeleccionarDeuda.setFont(new java.awt.Font("Dialog", 0, 11));
        lblSeleccionarDeuda.setForeground(new java.awt.Color(71, 85, 105));
        lblSeleccionarDeuda.setText("Seleccionar Deuda");
        pnlRegistrarAbono.add(lblSeleccionarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 36, -1, -1));
        
        cbSeleccionarDeuda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visa Oro - Santander", "Hipotecario - BBVA", "Automotriz - Ford" }));
        pnlRegistrarAbono.add(cbSeleccionarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 52, 280, 26));
        
        lblMontoAbono.setFont(new java.awt.Font("Dialog", 0, 11));
        lblMontoAbono.setForeground(new java.awt.Color(71, 85, 105));
        lblMontoAbono.setText("Monto del Abono");
        pnlRegistrarAbono.add(lblMontoAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 86, -1, -1));
        
        txtMontoAbono.setText("0.00");
        txtMontoAbono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlRegistrarAbono.add(txtMontoAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 102, 280, 26));
        
        lblFechaPago.setFont(new java.awt.Font("Dialog", 0, 11));
        lblFechaPago.setForeground(new java.awt.Color(71, 85, 105));
        lblFechaPago.setText("Fecha del Pago");
        pnlRegistrarAbono.add(lblFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 136, -1, -1));
        
        dateFechaPago.setBackground(new java.awt.Color(255, 255, 255));
        dateFechaPago.setDateFormatString("dd/MM/yyyy");
        pnlRegistrarAbono.add(dateFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 152, 280, 26));
        
        btnConfirmarPago.setBackground(new java.awt.Color(255, 255, 255));
        btnConfirmarPago.setFont(new java.awt.Font("Dialog", 1, 12));
        btnConfirmarPago.setForeground(new java.awt.Color(37, 99, 235));
        btnConfirmarPago.setText("Confirmar Pago");
        btnConfirmarPago.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(37, 99, 235), 1, true));
        btnConfirmarPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPagoActionPerformed(evt);
            }
        });
        pnlRegistrarAbono.add(btnConfirmarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 190, 280, 28));
        
        jPanelTabDeudas.add(pnlRegistrarAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 395, 310, 230));

        // --- Box Deudas Activas ---
        pnlDeudasActivas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDeudasActivas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlDeudasActivas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblDeudasActivasTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblDeudasActivasTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblDeudasActivasTitle.setText("Deudas Activas");
        pnlDeudasActivas.add(lblDeudasActivasTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));
        
        lblDeudasActivasSubtitle.setFont(new java.awt.Font("Dialog", 0, 11));
        lblDeudasActivasSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblDeudasActivasSubtitle.setText("3 Deudas en curso");
        pnlDeudasActivas.add(lblDeudasActivasSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 14, -1, -1));

        // Debt card 1
        pnlDebt1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDebt1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblDebt1Title.setFont(new java.awt.Font("Dialog", 1, 12));
        lblDebt1Title.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt1Title.setText("Pr\u00e9stamo Hipotecario - Banco BBVA");
        pnlDebt1.add(lblDebt1Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 8, -1, -1));
        
        lblDebt1Desc.setFont(new java.awt.Font("Dialog", 0, 10));
        lblDebt1Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt1Desc.setText("Monto Inicial: $120,000.00 | Tasa: 4.5%");
        pnlDebt1.add(lblDebt1Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, -1, -1));
        
        lblDebt1Badge.setFont(new java.awt.Font("Dialog", 1, 9));
        lblDebt1Badge.setForeground(new java.awt.Color(6, 95, 70));
        lblDebt1Badge.setText("   AL D\u00cdA");
        pnlDebt1.add(lblDebt1Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 8, 65, 18));
        
        pbDebt1.setValue(18);
        pbDebt1.setBorderPainted(false);
        pbDebt1.setForeground(new java.awt.Color(16, 185, 129));
        pnlDebt1.add(pbDebt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, 320, 6));
        
        lblDebt1ProgressText.setFont(new java.awt.Font("Dialog", 0, 9));
        lblDebt1ProgressText.setForeground(new java.awt.Color(71, 85, 105));
        lblDebt1ProgressText.setText("Progreso de Pago (45 de 240 cuotas)");
        pnlDebt1.add(lblDebt1ProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 35, -1, -1));
        
        lblDebt1Percent.setFont(new java.awt.Font("Dialog", 1, 9));
        lblDebt1Percent.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt1Percent.setText("18.7%");
        pnlDebt1.add(lblDebt1Percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 35, -1, -1));
        
        lblDebt1SaldoTitle.setFont(new java.awt.Font("Dialog", 0, 9));
        lblDebt1SaldoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt1SaldoTitle.setText("Saldo Pendiente");
        pnlDebt1.add(lblDebt1SaldoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 20, -1, -1));
        
        lblDebt1SaldoValue.setFont(new java.awt.Font("Dialog", 1, 13));
        lblDebt1SaldoValue.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt1SaldoValue.setText("$97,500.00");
        pnlDebt1.add(lblDebt1SaldoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 34, -1, -1));
        
        pnlDeudasActivas.add(pnlDebt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 38, 620, 68));

        // Debt card 2
        pnlDebt2.setBackground(new java.awt.Color(255, 255, 255));
        pnlDebt2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblDebt2Title.setFont(new java.awt.Font("Dialog", 1, 12));
        lblDebt2Title.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt2Title.setText("Tarjeta de Cr\u00e9dito Oro - Visa");
        pnlDebt2.add(lblDebt2Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 8, -1, -1));
        
        lblDebt2Desc.setFont(new java.awt.Font("Dialog", 0, 10));
        lblDebt2Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt2Desc.setText("Monto Inicial: $4,500.00 | Pago M\u00ednimo: $150.00");
        pnlDebt2.add(lblDebt2Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, -1, -1));
        
        lblDebt2Badge.setFont(new java.awt.Font("Dialog", 1, 8));
        lblDebt2Badge.setForeground(new java.awt.Color(153, 27, 27));
        lblDebt2Badge.setText(" PR\u00d3XIMO A VENCER");
        pnlDebt2.add(lblDebt2Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 8, 105, 18));
        
        lblDebt2WarningIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/chat-flecha-abajo.png")));
        pnlDebt2.add(lblDebt2WarningIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, 16, 16));
        
        lblDebt2WarningText.setFont(new java.awt.Font("Dialog", 1, 10));
        lblDebt2WarningText.setForeground(new java.awt.Color(153, 27, 27));
        lblDebt2WarningText.setText("Vence en 2 d\u00edas (Oct 12)");
        pnlDebt2.add(lblDebt2WarningText, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 42, -1, -1));
        
        lblDebt2PayNow.setFont(new java.awt.Font("Dialog", 1, 11));
        lblDebt2PayNow.setForeground(new java.awt.Color(37, 99, 235));
        lblDebt2PayNow.setText("Pagar ahora");
        lblDebt2PayNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlDebt2.add(lblDebt2PayNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 42, -1, -1));
        
        pnlDeudasActivas.add(pnlDebt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 112, 620, 68));

        // Debt card 3
        pnlDebt3.setBackground(new java.awt.Color(255, 255, 255));
        pnlDebt3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblDebt3Title.setFont(new java.awt.Font("Dialog", 1, 12));
        lblDebt3Title.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt3Title.setText("Pr\u00e9stamo Automotriz - Ford Credit");
        pnlDebt3.add(lblDebt3Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 8, -1, -1));
        
        lblDebt3Desc.setFont(new java.awt.Font("Dialog", 0, 10));
        lblDebt3Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt3Desc.setText("Monto Inicial: $25,000.00 | Tasa: 6.0%");
        pnlDebt3.add(lblDebt3Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, -1, -1));
        
        lblDebt3Badge.setFont(new java.awt.Font("Dialog", 1, 9));
        lblDebt3Badge.setForeground(new java.awt.Color(30, 58, 138));
        lblDebt3Badge.setText("   PENDIENTE");
        pnlDebt3.add(lblDebt3Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 8, 75, 18));
        
        pbDebt3.setValue(62);
        pbDebt3.setBorderPainted(false);
        pbDebt3.setForeground(new java.awt.Color(37, 99, 235));
        pnlDebt3.add(pbDebt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, 320, 6));
        
        lblDebt3ProgressText.setFont(new java.awt.Font("Dialog", 0, 9));
        lblDebt3ProgressText.setForeground(new java.awt.Color(71, 85, 105));
        lblDebt3ProgressText.setText("Progreso de Pago (30 de 48 cuotas)");
        pnlDebt3.add(lblDebt3ProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 35, -1, -1));
        
        lblDebt3Percent.setFont(new java.awt.Font("Dialog", 1, 9));
        lblDebt3Percent.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt3Percent.setText("62.5%");
        pnlDebt3.add(lblDebt3Percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 35, -1, -1));
        
        lblDebt3SaldoTitle.setFont(new java.awt.Font("Dialog", 0, 9));
        lblDebt3SaldoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt3SaldoTitle.setText("Saldo Pendiente");
        pnlDebt3.add(lblDebt3SaldoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 20, -1, -1));
        
        lblDebt3SaldoValue.setFont(new java.awt.Font("Dialog", 1, 13));
        lblDebt3SaldoValue.setForeground(new java.awt.Color(11, 28, 48));
        lblDebt3SaldoValue.setText("$9,375.00");
        pnlDebt3.add(lblDebt3SaldoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 34, -1, -1));
        
        pnlDeudasActivas.add(pnlDebt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 186, 620, 68));
        
        jPanelTabDeudas.add(pnlDeudasActivas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 650, 265));

        // --- Box Historial Reciente ---
        pnlHistorialReciente.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorialReciente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlHistorialReciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblHistorialRecienteTitle.setFont(new java.awt.Font("Dialog", 1, 14));
        lblHistorialRecienteTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHistorialRecienteTitle.setText("Historial Reciente");
        pnlHistorialReciente.add(lblHistorialRecienteTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));
        
        lblExportarCSV.setFont(new java.awt.Font("Dialog", 1, 11));
        lblExportarCSV.setForeground(new java.awt.Color(37, 99, 235));
        lblExportarCSV.setText("Exportar CSV");
        lblExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHistorialReciente.add(lblExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 14, -1, -1));
        
        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "FECHA", "DEUDA", "MONTO PAGADO", "SALDO RESTANTE" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistorial.setSelectionBackground(new java.awt.Color(234, 241, 255));
        tblHistorial.setSelectionForeground(new java.awt.Color(11, 28, 48));
        jScrollPaneDeudas.setViewportView(tblHistorial);
        pnlHistorialReciente.add(jScrollPaneDeudas, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 38, 620, 160));
        
        jPanelTabDeudas.add(pnlHistorialReciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 650, 215));

        Body.addTab("Deudas", jPanelTabDeudas);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarDeudaActionPerformed(java.awt.event.ActionEvent evt) {
        String acreedor = txtAcreedor.getText().trim();
        String monto = txtMontoTotal.getText().trim();
        Date fecha = dateFechaInicio.getDate();

        if (acreedor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el acreedor.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (monto.isEmpty() || monto.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de inicio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "¡Deuda con " + acreedor + " registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        txtAcreedor.setText("");
        txtMontoTotal.setText("0.00");
        txtTasaInteres.setText("0.0");
        txtCuotasTotales.setText("12");
        dateFechaInicio.setDate(new Date());
    }

    private void btnConfirmarPagoActionPerformed(java.awt.event.ActionEvent evt) {
        String deuda = cbSeleccionarDeuda.getSelectedItem().toString();
        String abono = txtMontoAbono.getText().trim();
        Date fecha = dateFechaPago.getDate();

        if (abono.isEmpty() || abono.equals("0.00")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto de abono válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de pago.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DefaultTableModel model = (DefaultTableModel) tblHistorial.getModel();
        model.insertRow(0, new Object[]{sdf.format(fecha), deuda, "+$" + abono, "$0.00"});

        JOptionPane.showMessageDialog(this, "¡Pago de $" + abono + " a " + deuda + " confirmado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        txtMontoAbono.setText("0.00");
        dateFechaPago.setDate(new Date());
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

    private void jPanelEmployesMouseClicked(java.awt.event.MouseEvent evt) {
        AhorrosView viewAhorros = new AhorrosView();
        viewAhorros.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeudasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JButton btnConfirmarPago;
    private javax.swing.JButton btnRegistrarDeuda;
    private javax.swing.JComboBox<String> cbSeleccionarDeuda;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private com.toedter.calendar.JDateChooser dateFechaPago;
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
    private javax.swing.JPanel jPanelTabDeudas;
    private javax.swing.JScrollPane jScrollPaneDeudas;
    private javax.swing.JLabel lblAcreedor;
    private javax.swing.JLabel lblCardDeudaTotalIcon;
    private javax.swing.JLabel lblCardDeudaTotalTitle;
    private javax.swing.JLabel lblCardDeudaTotalValue;
    private javax.swing.JLabel lblCardProximoVencimientoIcon;
    private javax.swing.JLabel lblCardProximoVencimientoTitle;
    private javax.swing.JLabel lblCardProximoVencimientoValue;
    private javax.swing.JLabel lblCardTotalPagadoIcon;
    private javax.swing.JLabel lblCardTotalPagadoTitle;
    private javax.swing.JLabel lblCardTotalPagadoValue;
    private javax.swing.JLabel lblCuotasTotales;
    private javax.swing.JLabel lblDebt1Badge;
    private javax.swing.JLabel lblDebt1Desc;
    private javax.swing.JLabel lblDebt1Percent;
    private javax.swing.JLabel lblDebt1ProgressText;
    private javax.swing.JLabel lblDebt1SaldoTitle;
    private javax.swing.JLabel lblDebt1SaldoValue;
    private javax.swing.JLabel lblDebt1Title;
    private javax.swing.JLabel lblDebt2Badge;
    private javax.swing.JLabel lblDebt2Desc;
    private javax.swing.JLabel lblDebt2PayNow;
    private javax.swing.JLabel lblDebt2Title;
    private javax.swing.JLabel lblDebt2WarningIcon;
    private javax.swing.JLabel lblDebt2WarningText;
    private javax.swing.JLabel lblDebt3Badge;
    private javax.swing.JLabel lblDebt3Desc;
    private javax.swing.JLabel lblDebt3Percent;
    private javax.swing.JLabel lblDebt3ProgressText;
    private javax.swing.JLabel lblDebt3SaldoTitle;
    private javax.swing.JLabel lblDebt3SaldoValue;
    private javax.swing.JLabel lblDebt3Title;
    private javax.swing.JLabel lblDeudasActivasSubtitle;
    private javax.swing.JLabel lblDeudasActivasTitle;
    private javax.swing.JLabel lblExportarCSV;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblHistorialRecienteTitle;
    private javax.swing.JLabel lblMontoAbono;
    private javax.swing.JLabel lblMontoTotal;
    private javax.swing.JLabel lblNuevaDeudaTitle;
    private javax.swing.JLabel lblRegistrarAbonoTitle;
    private javax.swing.JLabel lblSeleccionarDeuda;
    private javax.swing.JLabel lblTasaInteres;
    private javax.swing.JPanel pnlCardDeudaTotal;
    private javax.swing.JPanel pnlCardProximoVencimiento;
    private javax.swing.JPanel pnlCardTotalPagado;
    private javax.swing.JPanel pnlDebt1;
    private javax.swing.JPanel pnlDebt2;
    private javax.swing.JPanel pnlDebt3;
    private javax.swing.JPanel pnlDeudasActivas;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JPanel pnlHistorialReciente;
    private javax.swing.JPanel pnlNuevaDeuda;
    private javax.swing.JPanel pnlRegistrarAbono;
    private javax.swing.JProgressBar pbDebt1;
    private javax.swing.JProgressBar pbDebt3;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTextField txtAcreedor;
    private javax.swing.JTextField txtCuotasTotales;
    private javax.swing.JTextField txtMontoAbono;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtTasaInteres;
    // Variables declaration - do not modify//GEN-END:variables
}
"""

with open(path_deudas_java, "w", encoding="utf-8") as f:
    f.write(code_content)

print("DeudasView.java generated successfully!")
