package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import view.components.CardPanel;
import view.components.HeaderBar;
import view.components.KpiCard;
import view.components.SidebarPanel;
import view.components.ThemeColor;

/**
 * Vista de Dashboard Principal - Sistema Finandes.
 * Paridad con el mockup Dashboard de Finanzas.jpg y compatible 100% con NetBeans GUI Builder.
 */
public class DashboardView extends javax.swing.JFrame {

    private controller.DashboardController controller;

    // Componentes adaptadores para compatibilidad total con DashboardController
    public JButton btnAddTransaction;
    public JLabel lblAvatarIcon;
    public JLabel lblCardIncomeValue;
    public JLabel lblCardIncomeChange;
    public JLabel lblCardExpensesValue;
    public JLabel lblCardExpensesChange;
    public JLabel lblCardSavingsValue;
    public JLabel lblCardSavingsChange;
    public JLabel lblCardBalanceValue;
    public JLabel lblCardBalanceChange;

    public DashboardView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Dashboard Principal");
        setLocationRelativeTo(null);
        this.controller = new controller.DashboardController(this);
    }

    private void initCustomStyles() {
        sidebarPanel.setActiveModule("Panel");
        headerBar.setModuloActual("Dashboard Principal");
        headerBar.setAccionBoton("+ + Transacción");
        headerBar.setMostrarBotonAccion(true);
        btnAddTransaction = headerBar.getBtnAccion();
        lblAvatarIcon = headerBar.getLblAvatar();

        // Proxies para actualizar dinámicamente las KpiCards cuando el controlador establezca los valores
        lblCardIncomeValue = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiIngresos != null) {
                    kpiIngresos.setMonto(text);
                }
            }
        };
        lblCardIncomeChange = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiIngresos != null) {
                    kpiIngresos.setVariacion(text);
                }
            }
        };

        lblCardExpensesValue = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiGastos != null) {
                    kpiGastos.setMonto(text);
                }
            }
        };
        lblCardExpensesChange = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiGastos != null) {
                    kpiGastos.setVariacion(text);
                }
            }
        };

        lblCardSavingsValue = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiAhorros != null) {
                    kpiAhorros.setMonto(text);
                }
            }
        };
        lblCardSavingsChange = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiAhorros != null) {
                    kpiAhorros.setVariacion(text);
                }
            }
        };

        lblCardBalanceValue = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiBalance != null) {
                    kpiBalance.setMonto(text);
                }
            }
        };
        lblCardBalanceChange = new JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (kpiBalance != null) {
                    kpiBalance.setVariacion(text);
                }
            }
        };

        // Configurar botones de filtro
        btnMoreFilters.setIconTextGap(6);
        btnExportPDF.setIconTextGap(6);

        // Configurar layout vertical para la lista dinámica de pagos
        if (pnlPaymentsList != null) {
            pnlPaymentsList.setLayout(new javax.swing.BoxLayout(pnlPaymentsList, javax.swing.BoxLayout.Y_AXIS));
        }

        // Scroll suave
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        cardFilters = new view.components.CardPanel();
        lblTimePeriod = new javax.swing.JLabel();
        cbTimePeriod = new javax.swing.JComboBox<>();
        lblCollaborator = new javax.swing.JLabel();
        cbCollaborator = new javax.swing.JComboBox<>();
        btnMoreFilters = new javax.swing.JButton();
        btnExportPDF = new javax.swing.JButton();
        kpiIngresos = new view.components.KpiCard();
        kpiGastos = new view.components.KpiCard();
        kpiAhorros = new view.components.KpiCard();
        kpiBalance = new view.components.KpiCard();
        cardBudgetVsSpending = new view.components.CardPanel();
        lblBudgetVsSpendingTitle = new javax.swing.JLabel();
        lblBudgetSubtitle = new javax.swing.JLabel();
        lblViewAllCategories = new javax.swing.JLabel();
        pnlBudgetVsSpending = new javax.swing.JPanel();
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
        cardQuickInsights = new view.components.CardPanel();
        lblQuickInsightsTitle = new javax.swing.JLabel();
        lblQuickInsightsSub = new javax.swing.JLabel();
        pnlSavingsBanner = new javax.swing.JPanel();
        lblSavingsBannerIcon = new javax.swing.JLabel();
        lblSavingsBannerTitle = new javax.swing.JLabel();
        lblSavingsBannerDesc = new javax.swing.JLabel();
        lblUpcomingPayments = new javax.swing.JLabel();
        pnlPaymentsList = new javax.swing.JPanel();
        lblInternetTitle = new javax.swing.JLabel();
        lblInternetDate = new javax.swing.JLabel();
        lblInternetAmount = new javax.swing.JLabel();
        lblHealthTitle = new javax.swing.JLabel();
        lblHealthDate = new javax.swing.JLabel();
        lblHealthAmount = new javax.swing.JLabel();
        btnViewScheduled = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Dashboard Principal");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Panel");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ + Transacción");
        headerBar.setModuloActual("Dashboard Principal");
        headerBar.setMostrarBotonAccion(true);
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setPreferredSize(new java.awt.Dimension(1020, 725));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardFilters.setCornerRadius(10);
        cardFilters.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTimePeriod.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTimePeriod.setForeground(new java.awt.Color(100, 116, 139));
        lblTimePeriod.setText("Período de Tiempo");
        cardFilters.add(lblTimePeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 8, 130, 16));

        cbTimePeriod.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbTimePeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Octubre 2023", "Septiembre 2023", "Todo el tiempo" }));
        cardFilters.add(cbTimePeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 26, 140, 28));

        lblCollaborator.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCollaborator.setForeground(new java.awt.Color(100, 116, 139));
        lblCollaborator.setText("Colaborador");
        cardFilters.add(lblCollaborator, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 8, 130, 16));

        cbCollaborator.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbCollaborator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los usuarios" }));
        cardFilters.add(cbCollaborator, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 26, 155, 28));

        btnMoreFilters.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnMoreFilters.setForeground(new java.awt.Color(51, 65, 85));
        btnMoreFilters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filtrar.png"))); // NOI18N
        btnMoreFilters.setText(" Más Filtros");
        btnMoreFilters.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnMoreFilters.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoreFilters.setFocusPainted(false);
        cardFilters.add(btnMoreFilters, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 16, 105, 32));

        btnExportPDF.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportPDF.setForeground(new java.awt.Color(51, 65, 85));
        btnExportPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/descargar.png"))); // NOI18N
        btnExportPDF.setText(" Exportar PDF");
        btnExportPDF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnExportPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportPDF.setFocusPainted(false);
        cardFilters.add(btnExportPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 16, 115, 32));

        pnlMainContent.add(cardFilters, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 14, 970, 66));

        kpiIngresos.setColorAcento(new java.awt.Color(66, 173, 77));
        kpiIngresos.setMonto("S/. 12,450.00");
        kpiIngresos.setTitulo("Ingresos Totales");
        kpiIngresos.setVariacion("+12% vs mes anterior");
        pnlMainContent.add(kpiIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 92, 235, 115));

        kpiGastos.setColorAcento(new java.awt.Color(0, 159, 227));
        kpiGastos.setMonto("S/. 5,230.45");
        kpiGastos.setTitulo("Gastos Totales");
        kpiGastos.setVariacion("+4% vs mes anterior");
        pnlMainContent.add(kpiGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 92, 235, 115));

        kpiAhorros.setColorAcento(new java.awt.Color(245, 158, 11));
        kpiAhorros.setMonto("S/. 2,100.00");
        kpiAhorros.setTitulo("Ahorros Totales");
        kpiAhorros.setVariacion("84% de la meta mensual");
        pnlMainContent.add(kpiAhorros, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 92, 235, 115));

        kpiBalance.setColorAcento(new java.awt.Color(148, 163, 184));
        kpiBalance.setMonto("S/. 34,819.55");
        kpiBalance.setTitulo("Balance Total");
        kpiBalance.setVariacion("Todas las cuentas sincronizadas");
        pnlMainContent.add(kpiBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 92, 235, 115));

        cardBudgetVsSpending.setCornerRadius(12);
        cardBudgetVsSpending.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBudgetVsSpendingTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblBudgetVsSpendingTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblBudgetVsSpendingTitle.setText("Presupuesto vs Gastos");
        cardBudgetVsSpending.add(lblBudgetVsSpendingTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 250, 22));

        lblBudgetSubtitle.setText("");
        cardBudgetVsSpending.add(lblBudgetSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        lblViewAllCategories.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblViewAllCategories.setForeground(new java.awt.Color(0, 159, 227));
        lblViewAllCategories.setText("Ver todas las categorías →");
        lblViewAllCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardBudgetVsSpending.add(lblViewAllCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 18, 160, 20));

        pnlBudgetVsSpending.setBackground(new java.awt.Color(255, 255, 255));
        pnlBudgetVsSpending.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHousingTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHousingTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblHousingTitle.setText("Vivienda");
        pnlBudgetVsSpending.add(lblHousingTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 18));

        lblHousingDesc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblHousingDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblHousingDesc.setText("Alquiler, Seguro, Servicios");
        pnlBudgetVsSpending.add(lblHousingDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 28, 250, 16));

        lblHousingValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHousingValue.setForeground(new java.awt.Color(15, 23, 42));
        lblHousingValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHousingValue.setText("S/. 1,850.00 / S/. 2,000.00");
        pnlBudgetVsSpending.add(lblHousingValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 200, 18));

        pbHousing.setBackground(new java.awt.Color(234, 241, 255));
        pbHousing.setForeground(new java.awt.Color(0, 159, 227));
        pbHousing.setValue(92);
        pbHousing.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbHousing, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 555, 8));

        lblHousingStatus.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblHousingStatus.setForeground(new java.awt.Color(0, 108, 73));
        lblHousingStatus.setText("ZONA SEGURA");
        pnlBudgetVsSpending.add(lblHousingStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 16));

        lblHousingPercent.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblHousingPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblHousingPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHousingPercent.setText("92% utilizado");
        pnlBudgetVsSpending.add(lblHousingPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 200, 16));

        lblFoodTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFoodTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblFoodTitle.setText("Alimentación y Restaurantes");
        pnlBudgetVsSpending.add(lblFoodTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 220, 18));

        lblFoodDesc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblFoodDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblFoodDesc.setText("Supermercado, Restaurantes");
        pnlBudgetVsSpending.add(lblFoodDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 113, 250, 16));

        lblFoodValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFoodValue.setForeground(new java.awt.Color(239, 68, 68));
        lblFoodValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFoodValue.setText("<html><b style='color:#EF4444;'>S/. 745.00</b> <span style='color:#64748B;'>/ S/. 600.00</span></html>");
        pnlBudgetVsSpending.add(lblFoodValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 95, 200, 18));

        pbFood.setBackground(new java.awt.Color(254, 226, 226));
        pbFood.setForeground(new java.awt.Color(239, 68, 68));
        pbFood.setValue(100);
        pbFood.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 133, 555, 8));

        lblFoodStatus.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblFoodStatus.setForeground(new java.awt.Color(239, 68, 68));
        lblFoodStatus.setText("EXCEDIDO DEL PRESUPUESTO");
        pnlBudgetVsSpending.add(lblFoodStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 180, 16));

        lblFoodExcess.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblFoodExcess.setForeground(new java.awt.Color(239, 68, 68));
        lblFoodExcess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFoodExcess.setText("S/. 145.00 de exceso");
        pnlBudgetVsSpending.add(lblFoodExcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 145, 200, 16));

        lblTransportTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTransportTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTransportTitle.setText("Transporte");
        pnlBudgetVsSpending.add(lblTransportTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 200, 18));

        lblTransportDesc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTransportDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblTransportDesc.setText("Combustible, Transporte Público, Mantenimiento");
        pnlBudgetVsSpending.add(lblTransportDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 198, 300, 16));

        lblTransportValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTransportValue.setForeground(new java.awt.Color(15, 23, 42));
        lblTransportValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTransportValue.setText("S/. 210.00 / S/. 450.00");
        pnlBudgetVsSpending.add(lblTransportValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 200, 18));

        pbTransport.setBackground(new java.awt.Color(234, 241, 255));
        pbTransport.setForeground(new java.awt.Color(0, 108, 73));
        pbTransport.setValue(46);
        pbTransport.setBorderPainted(false);
        pnlBudgetVsSpending.add(pbTransport, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 218, 555, 8));

        lblTransportStatus.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblTransportStatus.setForeground(new java.awt.Color(0, 108, 73));
        lblTransportStatus.setText("EN ORDEN");
        pnlBudgetVsSpending.add(lblTransportStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 16));

        lblTransportPercent.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTransportPercent.setForeground(new java.awt.Color(71, 85, 105));
        lblTransportPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTransportPercent.setText("46% utilizado");
        pnlBudgetVsSpending.add(lblTransportPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 200, 16));

        cardBudgetVsSpending.add(pnlBudgetVsSpending, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, 580, 395));

        pnlMainContent.add(cardBudgetVsSpending, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 227, 610, 470));

        cardQuickInsights.setCornerRadius(12);
        cardQuickInsights.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQuickInsightsTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblQuickInsightsTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblQuickInsightsTitle.setText("Análisis Rápido");
        cardQuickInsights.add(lblQuickInsightsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 200, 22));

        lblQuickInsightsSub.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblQuickInsightsSub.setForeground(new java.awt.Color(100, 116, 139));
        lblQuickInsightsSub.setText("Recomendaciones y próximos vencimientos");
        cardQuickInsights.add(lblQuickInsightsSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 38, 300, 16));

        pnlSavingsBanner.setBackground(new java.awt.Color(224, 242, 254));
        pnlSavingsBanner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(186, 230, 253)));
        pnlSavingsBanner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSavingsBannerIcon.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        lblSavingsBannerIcon.setForeground(new java.awt.Color(2, 132, 199));
        lblSavingsBannerIcon.setText("💡");
        pnlSavingsBanner.add(lblSavingsBannerIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 22, 22));

        lblSavingsBannerTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSavingsBannerTitle.setForeground(new java.awt.Color(3, 105, 161));
        lblSavingsBannerTitle.setText("Potencial de Ahorro");
        pnlSavingsBanner.add(lblSavingsBannerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 10, 265, 20));

        lblSavingsBannerDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSavingsBannerDesc.setForeground(new java.awt.Color(51, 65, 85));
        lblSavingsBannerDesc.setText("<html>Gastaste un 25% más en comer fuera comparado con el mes anterior. Podrías ahorrar hasta S/. 280 optimizando este rubro.</html>");
        pnlSavingsBanner.add(lblSavingsBannerDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 32, 290, 52));

        cardQuickInsights.add(pnlSavingsBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 62, 315, 92));

        lblUpcomingPayments.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblUpcomingPayments.setForeground(new java.awt.Color(100, 116, 139));
        lblUpcomingPayments.setText("PRÓXIMOS PAGOS");
        cardQuickInsights.add(lblUpcomingPayments, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 166, 200, 20));

        pnlPaymentsList.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaymentsList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInternetTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblInternetTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblInternetTitle.setText("Proveedor de Internet");
        pnlPaymentsList.add(lblInternetTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 18));

        lblInternetDate.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInternetDate.setForeground(new java.awt.Color(100, 116, 139));
        lblInternetDate.setText("24 Oct, 2023");
        pnlPaymentsList.add(lblInternetDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 28, 150, 16));

        lblInternetAmount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblInternetAmount.setForeground(new java.awt.Color(0, 159, 227));
        lblInternetAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInternetAmount.setText("S/. 79.99");
        pnlPaymentsList.add(lblInternetAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 18));

        lblHealthTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHealthTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblHealthTitle.setText("Seguro Médico Privado");
        pnlPaymentsList.add(lblHealthTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 190, 18));

        lblHealthDate.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblHealthDate.setForeground(new java.awt.Color(100, 116, 139));
        lblHealthDate.setText("26 Oct, 2023");
        pnlPaymentsList.add(lblHealthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 83, 150, 16));

        lblHealthAmount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHealthAmount.setForeground(new java.awt.Color(0, 159, 227));
        lblHealthAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHealthAmount.setText("S/. 212.00");
        pnlPaymentsList.add(lblHealthAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 65, 90, 18));

        cardQuickInsights.add(pnlPaymentsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 190, 315, 205));

        btnViewScheduled.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnViewScheduled.setForeground(new java.awt.Color(0, 159, 227));
        btnViewScheduled.setText("Ver Transacciones Programadas");
        btnViewScheduled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 159, 227)));
        btnViewScheduled.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewScheduled.setFocusPainted(false);
        cardQuickInsights.add(btnViewScheduled, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 410, 315, 40));

        pnlMainContent.add(cardQuickInsights, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 227, 345, 470));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // fallback
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnExportPDF;
    public javax.swing.JButton btnMoreFilters;
    public javax.swing.JButton btnViewScheduled;
    public view.components.CardPanel cardBudgetVsSpending;
    public view.components.CardPanel cardFilters;
    public view.components.CardPanel cardQuickInsights;
    public javax.swing.JComboBox<Object> cbCollaborator;
    public javax.swing.JComboBox<String> cbTimePeriod;
    public view.components.HeaderBar headerBar;
    public view.components.KpiCard kpiAhorros;
    public view.components.KpiCard kpiBalance;
    public view.components.KpiCard kpiGastos;
    public view.components.KpiCard kpiIngresos;
    public javax.swing.JLabel lblBudgetSubtitle;
    public javax.swing.JLabel lblBudgetVsSpendingTitle;
    public javax.swing.JLabel lblCollaborator;
    public javax.swing.JLabel lblFoodDesc;
    public javax.swing.JLabel lblFoodExcess;
    public javax.swing.JLabel lblFoodStatus;
    public javax.swing.JLabel lblFoodTitle;
    public javax.swing.JLabel lblFoodValue;
    public javax.swing.JLabel lblHealthAmount;
    public javax.swing.JLabel lblHealthDate;
    public javax.swing.JLabel lblHealthTitle;
    public javax.swing.JLabel lblHousingDesc;
    public javax.swing.JLabel lblHousingPercent;
    public javax.swing.JLabel lblHousingStatus;
    public javax.swing.JLabel lblHousingTitle;
    public javax.swing.JLabel lblHousingValue;
    public javax.swing.JLabel lblInternetAmount;
    public javax.swing.JLabel lblInternetDate;
    public javax.swing.JLabel lblInternetTitle;
    public javax.swing.JLabel lblQuickInsightsSub;
    public javax.swing.JLabel lblQuickInsightsTitle;
    public javax.swing.JLabel lblSavingsBannerDesc;
    public javax.swing.JLabel lblSavingsBannerIcon;
    public javax.swing.JLabel lblSavingsBannerTitle;
    public javax.swing.JLabel lblSubtitle;
    public javax.swing.JLabel lblTimePeriod;
    public javax.swing.JLabel lblTitle;
    public javax.swing.JLabel lblTransportDesc;
    public javax.swing.JLabel lblTransportPercent;
    public javax.swing.JLabel lblTransportStatus;
    public javax.swing.JLabel lblTransportTitle;
    public javax.swing.JLabel lblTransportValue;
    public javax.swing.JLabel lblUpcomingPayments;
    public javax.swing.JLabel lblViewAllCategories;
    public javax.swing.JProgressBar pbFood;
    public javax.swing.JProgressBar pbHousing;
    public javax.swing.JProgressBar pbTransport;
    public javax.swing.JPanel pnlBudgetVsSpending;
    public javax.swing.JPanel pnlMainContent;
    public javax.swing.JPanel pnlPaymentsList;
    public javax.swing.JPanel pnlSavingsBanner;
    public javax.swing.JScrollPane scrollContent;
    public view.components.SidebarPanel sidebarPanel;
    // End of variables declaration//GEN-END:variables

    // Métodos Getters públicos para integración
    public SidebarPanel getSidebarPanel() { return sidebarPanel; }
    public HeaderBar getHeaderBar() { return headerBar; }
    public KpiCard getKpiIngresos() { return kpiIngresos; }
    public KpiCard getKpiGastos() { return kpiGastos; }
    public KpiCard getKpiAhorros() { return kpiAhorros; }
    public KpiCard getKpiBalance() { return kpiBalance; }
    public CardPanel getCardBudgetVsSpending() { return cardBudgetVsSpending; }
    public CardPanel getCardQuickInsights() { return cardQuickInsights; }
    public JPanel getPnlBudgetVsSpending() { return pnlBudgetVsSpending; }
    public JPanel getPnlPaymentsList() { return pnlPaymentsList; }
    public JComboBox<String> getCbTimePeriod() { return cbTimePeriod; }
    public JComboBox<Object> getCbCollaborator() { return cbCollaborator; }
}
