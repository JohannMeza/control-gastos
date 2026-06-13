package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * Vista de Dashboard de Finanzas Personales.
 * Actúa como la pantalla principal para la visualización del resumen financiero.
 */
public class DashboardView extends javax.swing.JFrame {

    public DashboardView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sidebar = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JPanel();
        lblDashboardMenu = new javax.swing.JLabel();
        pnlGasto = new javax.swing.JPanel();
        lblGastosMenu = new javax.swing.JLabel();
        pnlPresupuesto = new javax.swing.JPanel();
        lblPresupuestoMenu = new javax.swing.JLabel();
        pnlAhorros = new javax.swing.JPanel();
        lblAhorrosMenu = new javax.swing.JLabel();
        pnlDeudas = new javax.swing.JPanel();
        lblDeudasMenu = new javax.swing.JLabel();
        pnlUsuarios = new javax.swing.JPanel();
        lblUsuariosMenu = new javax.swing.JLabel();
        Logo = new javax.swing.JPanel();
        lblLogoImg = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        lblHeaderTitle = new javax.swing.JLabel();
        btnAddTransaction = new javax.swing.JButton();
        lblCalendarIcon = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        lblNotificationIcon = new javax.swing.JLabel();
        lblAvatarIcon = new javax.swing.JLabel();
        Body = new javax.swing.JTabbedPane();
        jPanelTabDashboard = new javax.swing.JPanel();
        
        // Filtros y Cabecera de Tab
        lblTimePeriod = new javax.swing.JLabel();
        cbTimePeriod = new javax.swing.JComboBox<>();
        lblCollaborator = new javax.swing.JLabel();
        cbCollaborator = new javax.swing.JComboBox<>();
        btnMoreFilters = new javax.swing.JButton();
        btnExportPDF = new javax.swing.JButton();

        // Tarjetas (Cards)
        pnlCardIncome = new javax.swing.JPanel();
        lblCardIncomeTitle = new javax.swing.JLabel();
        lblCardIncomeValue = new javax.swing.JLabel();
        lblCardIncomeChange = new javax.swing.JLabel();
        lblCardIncomeIcon = new javax.swing.JLabel();

        pnlCardExpenses = new javax.swing.JPanel();
        lblCardExpensesTitle = new javax.swing.JLabel();
        lblCardExpensesValue = new javax.swing.JLabel();
        lblCardExpensesChange = new javax.swing.JLabel();
        lblCardExpensesIcon = new javax.swing.JLabel();

        pnlCardSavings = new javax.swing.JPanel();
        lblCardSavingsTitle = new javax.swing.JLabel();
        lblCardSavingsValue = new javax.swing.JLabel();
        lblCardSavingsChange = new javax.swing.JLabel();
        lblCardSavingsIcon = new javax.swing.JLabel();

        pnlCardBalance = new javax.swing.JPanel();
        lblCardBalanceTitle = new javax.swing.JLabel();
        lblCardBalanceValue = new javax.swing.JLabel();
        lblCardBalanceChange = new javax.swing.JLabel();
        lblCardBalanceIcon = new javax.swing.JLabel();

        // Columna Izquierda: Budget vs Spending
        pnlBudgetVsSpending = new javax.swing.JPanel();
        lblBudgetVsSpendingTitle = new javax.swing.JLabel();
        lblViewAllCategories = new javax.swing.JLabel();
        
        lblHousingTitle = new javax.swing.JLabel();
        lblHousingDesc = new javax.swing.JLabel();
        lblHousingValue = new javax.swing.JLabel();
        pbHousing = new javax.swing.JProgressBar();
        lblHousingStatus = new javax.swing.JLabel();
        lblHousingPercent = new javax.swing.JLabel();

        lblFoodTitle = new javax.swing.JLabel();
        lblFoodDesc = new javax.swing.JLabel();
        lblFoodValue = new javax.swing.JLabel();
        pbFood = new javax.swing.JProgressBar();
        lblFoodStatus = new javax.swing.JLabel();
        lblFoodExcess = new javax.swing.JLabel();

        lblTransportTitle = new javax.swing.JLabel();
        lblTransportDesc = new javax.swing.JLabel();
        lblTransportValue = new javax.swing.JLabel();
        pbTransport = new javax.swing.JProgressBar();
        lblTransportStatus = new javax.swing.JLabel();
        lblTransportPercent = new javax.swing.JLabel();

        // Columna Derecha: Quick Insights
        pnlQuickInsights = new javax.swing.JPanel();
        lblQuickInsightsTitle = new javax.swing.JLabel();
        
        pnlSavingsBanner = new javax.swing.JPanel();
        lblSavingsBannerIcon = new javax.swing.JLabel();
        lblSavingsBannerTitle = new javax.swing.JLabel();
        lblSavingsBannerDesc = new javax.swing.JLabel();
        
        lblUpcomingPayments = new javax.swing.JLabel();
        
        pnlInternetIcon = new javax.swing.JPanel();
        lblInternetIconSymbol = new javax.swing.JLabel();
        lblInternetTitle = new javax.swing.JLabel();
        lblInternetDate = new javax.swing.JLabel();
        lblInternetAmount = new javax.swing.JLabel();

        pnlHealthIcon = new javax.swing.JPanel();
        lblHealthIconSymbol = new javax.swing.JLabel();
        lblHealthTitle = new javax.swing.JLabel();
        lblHealthDate = new javax.swing.JLabel();
        lblHealthAmount = new javax.swing.JLabel();

        btnViewScheduled = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(248, 249, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // --- SIDEBAR ---
        Sidebar.setBackground(new java.awt.Color(248, 250, 252));
        Sidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));

        // Dashboard (Activo)
        pnlDashboard.setBackground(new java.awt.Color(244, 248, 254));
        pnlDashboard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));
        pnlDashboard.setForeground(new java.awt.Color(37, 99, 235));

        lblDashboardMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDashboardMenu.setForeground(new java.awt.Color(37, 99, 235));
        lblDashboardMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/aplicaciones.png"))); // NOI18N
        lblDashboardMenu.setText("Dashboard");

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblDashboardMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDashboardMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Gastos (Inactivo - Clickable)
        pnlGasto.setBackground(new java.awt.Color(248, 250, 252));
        pnlGasto.setForeground(new java.awt.Color(71, 85, 105));
        pnlGasto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlGasto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGastoMouseClicked(evt);
            }
        });

        lblGastosMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGastosMenu.setForeground(new java.awt.Color(71, 85, 105));
        lblGastosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/gastos.png"))); // NOI18N
        lblGastosMenu.setText("Gastos");

        javax.swing.GroupLayout pnlGastoLayout = new javax.swing.GroupLayout(pnlGasto);
        pnlGasto.setLayout(pnlGastoLayout);
        pnlGastoLayout.setHorizontalGroup(
            pnlGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGastoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblGastosMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGastoLayout.setVerticalGroup(
            pnlGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGastosMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Presupuesto (Inactivo - Clickable)
        pnlPresupuesto.setBackground(new java.awt.Color(248, 250, 252));
        pnlPresupuesto.setForeground(new java.awt.Color(71, 85, 105));
        pnlPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPresupuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPresupuestoMouseClicked(evt);
            }
        });

        lblPresupuestoMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPresupuestoMenu.setForeground(new java.awt.Color(71, 85, 105));
        lblPresupuestoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/presupuesto.png"))); // NOI18N
        lblPresupuestoMenu.setText("Presupuesto");

        javax.swing.GroupLayout pnlPresupuestoLayout = new javax.swing.GroupLayout(pnlPresupuesto);
        pnlPresupuesto.setLayout(pnlPresupuestoLayout);
        pnlPresupuestoLayout.setHorizontalGroup(
            pnlPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresupuestoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblPresupuestoMenu)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        pnlPresupuestoLayout.setVerticalGroup(
            pnlPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPresupuestoMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Ahorros
        pnlAhorros.setBackground(new java.awt.Color(248, 250, 252));
        pnlAhorros.setForeground(new java.awt.Color(71, 85, 105));
        pnlAhorros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlAhorros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlAhorrosMouseClicked(evt);
            }
        });

        lblAhorrosMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAhorrosMenu.setForeground(new java.awt.Color(71, 85, 105));
        lblAhorrosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/ahorros.png"))); // NOI18N
        lblAhorrosMenu.setText("Ahorros");

        javax.swing.GroupLayout pnlAhorrosLayout = new javax.swing.GroupLayout(pnlAhorros);
        pnlAhorros.setLayout(pnlAhorrosLayout);
        pnlAhorrosLayout.setHorizontalGroup(
            pnlAhorrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAhorrosLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblAhorrosMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAhorrosLayout.setVerticalGroup(
            pnlAhorrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAhorrosMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Deudas
        pnlDeudas.setBackground(new java.awt.Color(248, 250, 252));
        pnlDeudas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlDeudas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDeudasMouseClicked(evt);
            }
        });
        pnlDeudas.setForeground(new java.awt.Color(71, 85, 105));

        lblDeudasMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeudasMenu.setForeground(new java.awt.Color(71, 85, 105));
        lblDeudasMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/deudas.png"))); // NOI18N
        lblDeudasMenu.setText("Deudas");

        javax.swing.GroupLayout pnlDeudasLayout = new javax.swing.GroupLayout(pnlDeudas);
        pnlDeudas.setLayout(pnlDeudasLayout);
        pnlDeudasLayout.setHorizontalGroup(
            pnlDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeudasLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblDeudasMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDeudasLayout.setVerticalGroup(
            pnlDeudasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDeudasMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Usuarios
        pnlUsuarios.setBackground(new java.awt.Color(248, 250, 252));
        pnlUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlUsuariosMouseClicked(evt);
            }
        });
        pnlUsuarios.setForeground(new java.awt.Color(71, 85, 105));

        lblUsuariosMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsuariosMenu.setForeground(new java.awt.Color(71, 85, 105));
        lblUsuariosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/usuario.png"))); // NOI18N
        lblUsuariosMenu.setText("Usuarios");

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblUsuariosMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsuariosMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        // Layout del Sidebar
        javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlAhorros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDeudas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlAhorros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlDeudas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 600));

        // --- LOGO PANEL ---
        Logo.setBackground(new java.awt.Color(248, 250, 252));
        Logo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogoImg.setBackground(new java.awt.Color(248, 250, 252));
        lblLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo.png"))); // NOI18N
        Logo.add(lblLogoImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        // --- HEADER PANEL ---
        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(234, 241, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHeaderTitle.setText("Finanzas Personales");
        Header.add(lblHeaderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, 200, 39));

        btnAddTransaction.setBackground(new java.awt.Color(37, 99, 235));
        btnAddTransaction.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnAddTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTransaction.setText("+ Agregar Transacción");
        btnAddTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.add(btnAddTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 5, 140, 30));

        lblCalendarIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCalendarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png"))); // NOI18N
        lblCalendarIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.add(lblCalendarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 5, 30, 30));

        lblUserIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/agregar-usuario.png"))); // NOI18N
        lblUserIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.add(lblUserIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 5, 30, 30));

        lblNotificationIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNotificationIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/email.png"))); // NOI18N
        lblNotificationIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.add(lblNotificationIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 5, 30, 30));

        lblAvatarIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvatarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/usuario.png"))); // NOI18N
        lblAvatarIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.add(lblAvatarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 5, 30, 30));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        // --- BODY (TABBED PANE) ---
        jPanelTabDashboard.setBackground(new java.awt.Color(248, 249, 255));
        jPanelTabDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // --- FILTROS ---
        lblTimePeriod.setForeground(new java.awt.Color(71, 85, 105));
        lblTimePeriod.setText("Periodo de Tiempo");
        jPanelTabDashboard.add(lblTimePeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cbTimePeriod.setBackground(new java.awt.Color(255, 255, 255));
        cbTimePeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Octubre 2023", "Noviembre 2023", "Diciembre 2023" }));
        jPanelTabDashboard.add(cbTimePeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 30));

        lblCollaborator.setForeground(new java.awt.Color(71, 85, 105));
        lblCollaborator.setText("Colaborador");
        jPanelTabDashboard.add(lblCollaborator, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 10, -1, -1));

        cbCollaborator.setBackground(new java.awt.Color(255, 255, 255));
        cbCollaborator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los usuarios", "Administrador", "Usuario 1" }));
        jPanelTabDashboard.add(cbCollaborator, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 30, 150, 30));

        btnMoreFilters.setBackground(new java.awt.Color(255, 255, 255));
        btnMoreFilters.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnMoreFilters.setForeground(new java.awt.Color(11, 28, 48));
        btnMoreFilters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/filtrar.png"))); // NOI18N
        btnMoreFilters.setText("Más Filtros");
        btnMoreFilters.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        jPanelTabDashboard.add(btnMoreFilters, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 120, 30));

        btnExportPDF.setBackground(new java.awt.Color(255, 255, 255));
        btnExportPDF.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnExportPDF.setForeground(new java.awt.Color(11, 28, 48));
        btnExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/descargar.png"))); // NOI18N
        btnExportPDF.setText("Exportar PDF");
        btnExportPDF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        jPanelTabDashboard.add(btnExportPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 30, 120, 30));

        // --- TARJETAS (CARDS ROW Y = 80) ---

        // Card 1: Total Income
        pnlCardIncome.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardIncome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardIncome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardIncomeTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardIncomeTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardIncomeTitle.setText("Ingresos Totales");
        pnlCardIncome.add(lblCardIncomeTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardIncomeValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardIncomeValue.setForeground(new java.awt.Color(0, 108, 73));
        lblCardIncomeValue.setText("$12,450.00");
        pnlCardIncome.add(lblCardIncomeValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        lblCardIncomeChange.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardIncomeChange.setForeground(new java.awt.Color(0, 108, 73));
        lblCardIncomeChange.setText("+12% vs mes anterior");
        pnlCardIncome.add(lblCardIncomeChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        lblCardIncomeIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardIncomeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        pnlCardIncome.add(lblCardIncomeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 15, 30, 30));

        jPanelTabDashboard.add(pnlCardIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 225, 100));

        // Card 2: Total Expenses
        pnlCardExpenses.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardExpenses.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardExpenses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardExpensesTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardExpensesTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardExpensesTitle.setText("Gastos Totales");
        pnlCardExpenses.add(lblCardExpensesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardExpensesValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardExpensesValue.setForeground(new java.awt.Color(37, 99, 235));
        lblCardExpensesValue.setText("$5,230.45");
        pnlCardExpenses.add(lblCardExpensesValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        lblCardExpensesChange.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardExpensesChange.setForeground(new java.awt.Color(186, 26, 26));
        lblCardExpensesChange.setText("+4% vs mes anterior");
        pnlCardExpenses.add(lblCardExpensesChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        lblCardExpensesIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardExpensesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/gastos.png"))); // NOI18N
        pnlCardExpenses.add(lblCardExpensesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 15, 30, 30));

        jPanelTabDashboard.add(pnlCardExpenses, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 225, 100));

        // Card 3: Total Savings
        pnlCardSavings.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardSavings.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardSavings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardSavingsTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardSavingsTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardSavingsTitle.setText("Ahorros Totales");
        pnlCardSavings.add(lblCardSavingsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardSavingsValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardSavingsValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardSavingsValue.setText("$2,100.00");
        pnlCardSavings.add(lblCardSavingsValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        lblCardSavingsChange.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardSavingsChange.setForeground(new java.awt.Color(0, 108, 73));
        lblCardSavingsChange.setText("84% de la meta mensual");
        pnlCardSavings.add(lblCardSavingsChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        lblCardSavingsIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardSavingsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png"))); // NOI18N
        pnlCardSavings.add(lblCardSavingsIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 15, 30, 30));

        jPanelTabDashboard.add(pnlCardSavings, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 225, 100));

        // Card 4: Current Balance
        pnlCardBalance.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardBalance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlCardBalance.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardBalanceTitle.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        lblCardBalanceTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCardBalanceTitle.setText("Saldo Actual");
        pnlCardBalance.add(lblCardBalanceTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblCardBalanceValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblCardBalanceValue.setForeground(new java.awt.Color(11, 28, 48));
        lblCardBalanceValue.setText("$34,819.55");
        pnlCardBalance.add(lblCardBalanceValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, -1, -1));

        lblCardBalanceChange.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCardBalanceChange.setForeground(new java.awt.Color(71, 85, 105));
        lblCardBalanceChange.setText("Todas las cuentas sincronizadas");
        pnlCardBalance.add(lblCardBalanceChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        lblCardBalanceIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardBalanceIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/estadisticas.png"))); // NOI18N
        pnlCardBalance.add(lblCardBalanceIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 15, 30, 30));

        jPanelTabDashboard.add(pnlCardBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 235, 100));

        // --- COLUMNA IZQUIERDA: BUDGET VS SPENDING ---
        pnlBudgetVsSpending.setBackground(new java.awt.Color(255, 255, 255));
        pnlBudgetVsSpending.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlBudgetVsSpending.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBudgetVsSpendingTitle.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblBudgetVsSpendingTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblBudgetVsSpendingTitle.setText("Presupuesto vs Gastos");
        pnlBudgetVsSpending.add(lblBudgetVsSpendingTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        lblViewAllCategories.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblViewAllCategories.setForeground(new java.awt.Color(37, 99, 235));
        lblViewAllCategories.setText("Ver Todas las Categorías");
        lblViewAllCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlBudgetVsSpending.add(lblViewAllCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 15, -1, -1));

        // Housing
        lblHousingTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblHousingTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHousingTitle.setText("Vivienda");
        pnlBudgetVsSpending.add(lblHousingTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 55, -1, -1));

        lblHousingDesc.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHousingDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblHousingDesc.setText("Alquiler, Seguro, Servicios");
        pnlBudgetVsSpending.add(lblHousingDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 73, -1, -1));

        lblHousingValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblHousingValue.setForeground(new java.awt.Color(11, 28, 48));
        lblHousingValue.setText("$1,850.00 / $2,000.00");
        pnlBudgetVsSpending.add(lblHousingValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 65, 150, -1));
        lblHousingValue.setHorizontalAlignment(SwingConstants.RIGHT);

        pbHousing.setBackground(new java.awt.Color(234, 241, 255));
        pbHousing.setForeground(new java.awt.Color(37, 99, 235));
        pbHousing.setValue(92);
        pbHousing.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbHousing, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, 555, 8));

        lblHousingStatus.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblHousingStatus.setForeground(new java.awt.Color(71, 85, 105));
        lblHousingStatus.setText("ZONA SEGURA");
        pnlBudgetVsSpending.add(lblHousingStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 108, -1, -1));

        lblHousingPercent.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHousingPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblHousingPercent.setText("92% utilizado");
        pnlBudgetVsSpending.add(lblHousingPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 108, 100, -1));
        lblHousingPercent.setHorizontalAlignment(SwingConstants.RIGHT);

        // Food & Dining
        lblFoodTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblFoodTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblFoodTitle.setText("Comida y Restaurantes");
        pnlBudgetVsSpending.add(lblFoodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 145, -1, -1));

        lblFoodDesc.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblFoodDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblFoodDesc.setText("Comestibles, Restaurantes");
        pnlBudgetVsSpending.add(lblFoodDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 163, -1, -1));

        lblFoodValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblFoodValue.setForeground(new java.awt.Color(11, 28, 48));
        lblFoodValue.setText("<html><font color='#ba1a1a'>$745.00</font> / $600.00</html>");
        pnlBudgetVsSpending.add(lblFoodValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 155, 150, -1));
        lblFoodValue.setHorizontalAlignment(SwingConstants.RIGHT);

        pbFood.setBackground(new java.awt.Color(255, 218, 214));
        pbFood.setForeground(new java.awt.Color(186, 26, 26));
        pbFood.setValue(100);
        pbFood.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 185, 555, 8));

        lblFoodStatus.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblFoodStatus.setForeground(new java.awt.Color(186, 26, 26));
        lblFoodStatus.setText("LÍMITE EXCEDIDO");
        pnlBudgetVsSpending.add(lblFoodStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 198, -1, -1));

        lblFoodExcess.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblFoodExcess.setForeground(new java.awt.Color(186, 26, 26));
        lblFoodExcess.setText("$145.00 de exceso");
        pnlBudgetVsSpending.add(lblFoodExcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 198, 100, -1));
        lblFoodExcess.setHorizontalAlignment(SwingConstants.RIGHT);

        // Transport
        lblTransportTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblTransportTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblTransportTitle.setText("Transporte");
        pnlBudgetVsSpending.add(lblTransportTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 235, -1, -1));

        lblTransportDesc.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblTransportDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblTransportDesc.setText("Combustible, Transporte Público, Mantenimiento");
        pnlBudgetVsSpending.add(lblTransportDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 253, -1, -1));

        lblTransportValue.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblTransportValue.setForeground(new java.awt.Color(11, 28, 48));
        lblTransportValue.setText("$210.00 / $450.00");
        pnlBudgetVsSpending.add(lblTransportValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 245, 150, -1));
        lblTransportValue.setHorizontalAlignment(SwingConstants.RIGHT);

        pbTransport.setBackground(new java.awt.Color(234, 241, 255));
        pbTransport.setForeground(new java.awt.Color(0, 108, 73));
        pbTransport.setValue(46);
        pbTransport.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbTransport, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 275, 555, 8));

        lblTransportStatus.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblTransportStatus.setForeground(new java.awt.Color(0, 108, 73));
        lblTransportStatus.setText("EN ORDEN");
        pnlBudgetVsSpending.add(lblTransportStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 288, -1, -1));

        lblTransportPercent.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblTransportPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblTransportPercent.setText("46% utilizado");
        pnlBudgetVsSpending.add(lblTransportPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 288, 100, -1));
        lblTransportPercent.setHorizontalAlignment(SwingConstants.RIGHT);

        jPanelTabDashboard.add(pnlBudgetVsSpending, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 590, 370));

        // --- COLUMNA DERECHA: QUICK INSIGHTS ---
        pnlQuickInsights.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuickInsights.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        pnlQuickInsights.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQuickInsightsTitle.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblQuickInsightsTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblQuickInsightsTitle.setText("Información Rápida");
        pnlQuickInsights.add(lblQuickInsightsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        // Savings Potential Banner
        pnlSavingsBanner.setBackground(new java.awt.Color(244, 248, 254));
        pnlSavingsBanner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSavingsBannerIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSavingsBannerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/estadisticas.png"))); // NOI18N
        pnlSavingsBanner.add(lblSavingsBannerIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 30, 30));

        lblSavingsBannerTitle.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSavingsBannerTitle.setForeground(new java.awt.Color(37, 99, 235));
        lblSavingsBannerTitle.setText("Potencial de Ahorro");
        pnlSavingsBanner.add(lblSavingsBannerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 12, -1, -1));

        lblSavingsBannerDesc.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblSavingsBannerDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblSavingsBannerDesc.setText("<html>Gastaste 25% más en comer fuera esta semana. ¡Reducir una comida podría ahorrarte $45!</html>");
        pnlSavingsBanner.add(lblSavingsBannerDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 250, 50));

        pnlQuickInsights.add(pnlSavingsBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 50, 310, 90));

        // Upcoming Payments
        lblUpcomingPayments.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblUpcomingPayments.setForeground(new java.awt.Color(71, 85, 105));
        lblUpcomingPayments.setText("PRÓXIMOS PAGOS");
        pnlQuickInsights.add(lblUpcomingPayments, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 155, -1, -1));

        // Internet Provider Item
        pnlInternetIcon.setBackground(new java.awt.Color(248, 250, 252));
        pnlInternetIcon.setLayout(new java.awt.BorderLayout());
        lblInternetIconSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        lblInternetIconSymbol.setText("📶");
        pnlInternetIcon.add(lblInternetIconSymbol, java.awt.BorderLayout.CENTER);
        pnlQuickInsights.add(pnlInternetIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 180, 40, 40));

        lblInternetTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblInternetTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblInternetTitle.setText("Proveedor de Internet");
        pnlQuickInsights.add(lblInternetTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 180, 150, -1));

        lblInternetDate.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblInternetDate.setForeground(new java.awt.Color(71, 85, 105));
        lblInternetDate.setText("24 Oct");
        pnlQuickInsights.add(lblInternetDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 198, 100, -1));

        lblInternetAmount.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblInternetAmount.setForeground(new java.awt.Color(11, 28, 48));
        lblInternetAmount.setText("$79.99");
        pnlQuickInsights.add(lblInternetAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 95, 40));
        lblInternetAmount.setHorizontalAlignment(SwingConstants.RIGHT);

        // Health Insurance Item
        pnlHealthIcon.setBackground(new java.awt.Color(248, 250, 252));
        pnlHealthIcon.setLayout(new java.awt.BorderLayout());
        lblHealthIconSymbol.setHorizontalAlignment(SwingConstants.CENTER);
        lblHealthIconSymbol.setText("🛡️");
        pnlHealthIcon.add(lblHealthIconSymbol, java.awt.BorderLayout.CENTER);
        pnlQuickInsights.add(pnlHealthIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 230, 40, 40));

        lblHealthTitle.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblHealthTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHealthTitle.setText("Seguro de Salud");
        pnlQuickInsights.add(lblHealthTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 230, 150, -1));

        lblHealthDate.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHealthDate.setForeground(new java.awt.Color(71, 85, 105));
        lblHealthDate.setText("26 Oct");
        pnlQuickInsights.add(lblHealthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 248, 100, -1));

        lblHealthAmount.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblHealthAmount.setForeground(new java.awt.Color(11, 28, 48));
        lblHealthAmount.setText("$212.00");
        pnlQuickInsights.add(lblHealthAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 95, 40));
        lblHealthAmount.setHorizontalAlignment(SwingConstants.RIGHT);

        // Action Button
        btnViewScheduled.setBackground(new java.awt.Color(255, 255, 255));
        btnViewScheduled.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnViewScheduled.setForeground(new java.awt.Color(11, 28, 48));
        btnViewScheduled.setText("Ver Transacciones Programadas");
        btnViewScheduled.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnViewScheduled.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlQuickInsights.add(btnViewScheduled, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 310, 310, 35));

        jPanelTabDashboard.add(pnlQuickInsights, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 340, 370));

        // Agregar la pestaña general de Dashboard
        Body.addTab("Resumen General", jPanelTabDashboard);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGastoMouseClicked
        SystemView viewSystem = new SystemView();
        viewSystem.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pnlGastoMouseClicked

    private void pnlPresupuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPresupuestoMouseClicked
        PresupuestoView viewPresupuesto = new PresupuestoView();
        viewPresupuesto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pnlPresupuestoMouseClicked

    private void pnlAhorrosMouseClicked(java.awt.event.MouseEvent evt) {
        AhorrosView viewAhorros = new AhorrosView();
        viewAhorros.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardView().setVisible(true);
        });
    }

        private void pnlDeudasMouseClicked(java.awt.event.MouseEvent evt) {
        DeudasView viewDeudas = new DeudasView();
        viewDeudas.setVisible(true);
        this.dispose();
    }

        private void pnlUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
        UsuariosView viewUsuarios = new UsuariosView();
        viewUsuarios.setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel Sidebar;
    
    // Menu Sidebar
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JLabel lblDashboardMenu;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JLabel lblGastosMenu;
    private javax.swing.JPanel pnlPresupuesto;
    private javax.swing.JLabel lblPresupuestoMenu;
    private javax.swing.JPanel pnlAhorros;
    private javax.swing.JLabel lblAhorrosMenu;
    private javax.swing.JPanel pnlDeudas;
    private javax.swing.JLabel lblDeudasMenu;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JLabel lblUsuariosMenu;

    // Header Components
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblLogoImg;
    private javax.swing.JButton btnAddTransaction;
    private javax.swing.JLabel lblCalendarIcon;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JLabel lblNotificationIcon;
    private javax.swing.JLabel lblAvatarIcon;

    // Tab Dashboard Components
    private javax.swing.JPanel jPanelTabDashboard;
    private javax.swing.JLabel lblTimePeriod;
    private javax.swing.JComboBox<String> cbTimePeriod;
    private javax.swing.JLabel lblCollaborator;
    private javax.swing.JComboBox<String> cbCollaborator;
    private javax.swing.JButton btnMoreFilters;
    private javax.swing.JButton btnExportPDF;

    // Cards
    private javax.swing.JPanel pnlCardIncome;
    private javax.swing.JLabel lblCardIncomeTitle;
    private javax.swing.JLabel lblCardIncomeValue;
    private javax.swing.JLabel lblCardIncomeChange;
    private javax.swing.JLabel lblCardIncomeIcon;

    private javax.swing.JPanel pnlCardExpenses;
    private javax.swing.JLabel lblCardExpensesTitle;
    private javax.swing.JLabel lblCardExpensesValue;
    private javax.swing.JLabel lblCardExpensesChange;
    private javax.swing.JLabel lblCardExpensesIcon;

    private javax.swing.JPanel pnlCardSavings;
    private javax.swing.JLabel lblCardSavingsTitle;
    private javax.swing.JLabel lblCardSavingsValue;
    private javax.swing.JLabel lblCardSavingsChange;
    private javax.swing.JLabel lblCardSavingsIcon;

    private javax.swing.JPanel pnlCardBalance;
    private javax.swing.JLabel lblCardBalanceTitle;
    private javax.swing.JLabel lblCardBalanceValue;
    private javax.swing.JLabel lblCardBalanceChange;
    private javax.swing.JLabel lblCardBalanceIcon;

    // Budget vs Spending
    private javax.swing.JPanel pnlBudgetVsSpending;
    private javax.swing.JLabel lblBudgetVsSpendingTitle;
    private javax.swing.JLabel lblViewAllCategories;
    
    private javax.swing.JLabel lblHousingTitle;
    private javax.swing.JLabel lblHousingDesc;
    private javax.swing.JLabel lblHousingValue;
    private javax.swing.JProgressBar pbHousing;
    private javax.swing.JLabel lblHousingStatus;
    private javax.swing.JLabel lblHousingPercent;

    private javax.swing.JLabel lblFoodTitle;
    private javax.swing.JLabel lblFoodDesc;
    private javax.swing.JLabel lblFoodValue;
    private javax.swing.JProgressBar pbFood;
    private javax.swing.JLabel lblFoodStatus;
    private javax.swing.JLabel lblFoodExcess;

    private javax.swing.JLabel lblTransportTitle;
    private javax.swing.JLabel lblTransportDesc;
    private javax.swing.JLabel lblTransportValue;
    private javax.swing.JProgressBar pbTransport;
    private javax.swing.JLabel lblTransportStatus;
    private javax.swing.JLabel lblTransportPercent;

    // Quick Insights
    private javax.swing.JPanel pnlQuickInsights;
    private javax.swing.JLabel lblQuickInsightsTitle;
    
    private javax.swing.JPanel pnlSavingsBanner;
    private javax.swing.JLabel lblSavingsBannerIcon;
    private javax.swing.JLabel lblSavingsBannerTitle;
    private javax.swing.JLabel lblSavingsBannerDesc;
    
    private javax.swing.JLabel lblUpcomingPayments;
    
    private javax.swing.JPanel pnlInternetIcon;
    private javax.swing.JLabel lblInternetIconSymbol;
    private javax.swing.JLabel lblInternetTitle;
    private javax.swing.JLabel lblInternetDate;
    private javax.swing.JLabel lblInternetAmount;

    private javax.swing.JPanel pnlHealthIcon;
    private javax.swing.JLabel lblHealthIconSymbol;
    private javax.swing.JLabel lblHealthTitle;
    private javax.swing.JLabel lblHealthDate;
    private javax.swing.JLabel lblHealthAmount;

    private javax.swing.JButton btnViewScheduled;
    // End of variables declaration//GEN-END:variables
}
