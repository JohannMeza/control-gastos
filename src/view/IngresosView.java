package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import view.components.CardPanel;
import view.components.HeaderBar;
import view.components.KpiCard;
import view.components.ModernTableHelper;
import view.components.PaginationBar;
import view.components.SegmentedProgressBar;
import view.components.SidebarPanel;
import view.components.ThemeColor;

/**
 * Vista de Gestión de Ingresos - Sistema Finandes.
 * Paridad matemática con el mockup Html → Body.jpg y compatible 100% con NetBeans GUI Builder.
 */
public class IngresosView extends javax.swing.JFrame {

    private ButtonGroup grupoTipoFlujo;

    public IngresosView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setLocationRelativeTo(null);
    }

    private void initCustomStyles() {
        // 1. Agrupar Radio Buttons de Tipo de Flujo
        grupoTipoFlujo = new ButtonGroup();
        grupoTipoFlujo.add(rbFijo);
        grupoTipoFlujo.add(rbVariable);
        rbFijo.setSelected(true);

        // 2. Configurar barra segmentada de distribución de fuentes
        progressBarFuentes.limpiarSegmentos();
        progressBarFuentes.agregarSegmento(68.2, ThemeColor.VERDE_PRIMARIO); // Nómina 68.2%
        progressBarFuentes.agregarSegmento(21.1, ThemeColor.CIAN_ACCION);    // Freelance 21.1%
        progressBarFuentes.agregarSegmento(10.7, new Color(245, 158, 11));   // Dividendos 10.7%

        // 3. Estilizar Tabla con ModernTableHelper
        ModernTableHelper.styleTable(tblIngresos);
        ModernTableHelper.setBadgeColumn(tblIngresos, 2, 4, 6);
        ModernTableHelper.setMoneyColumn(tblIngresos, 5);
        ModernTableHelper.styleScrollPane(scrollTabla);

        // 4. Configurar barra de paginación inicial
        paginationBar.updatePagination(1, 48, 6);

        // 5. Ajustar velocidad de scroll vertical suave
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblBadgeCiclo = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        btnFiltroOct = new javax.swing.JButton();
        btnFiltroSep = new javax.swing.JButton();
        btnFiltroAnual = new javax.swing.JButton();
        btnAjustarVista = new javax.swing.JButton();
        kpiTotales = new view.components.KpiCard();
        kpiFijo = new view.components.KpiCard();
        kpiVariable = new view.components.KpiCard();
        kpiPromedio = new view.components.KpiCard();
        cardFormulario = new view.components.CardPanel();
        lblHeaderForm = new javax.swing.JLabel();
        lblBadgeManual = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        lblTipoFlujo = new javax.swing.JLabel();
        rbFijo = new javax.swing.JRadioButton();
        rbVariable = new javax.swing.JRadioButton();
        lblCuenta = new javax.swing.JLabel();
        cbCuentaDestino = new javax.swing.JComboBox<>();
        lblDesc = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        chkRecordar = new javax.swing.JCheckBox();
        btnRegistrarIngreso = new javax.swing.JButton();
        cardFiltroRapido = new view.components.CardPanel();
        lblTitleFiltro = new javax.swing.JLabel();
        lblResetFiltro = new javax.swing.JLabel();
        txtRangoDesde = new javax.swing.JTextField();
        txtRangoHasta = new javax.swing.JTextField();
        cbFiltroCategoria = new javax.swing.JComboBox<>();
        cbFiltroCuenta = new javax.swing.JComboBox<>();
        lblTitleDist = new javax.swing.JLabel();
        lblTotalDist = new javax.swing.JLabel();
        progressBarFuentes = new view.components.SegmentedProgressBar();
        lblNominaItem = new javax.swing.JLabel();
        lblNominaVal = new javax.swing.JLabel();
        lblFreelanceItem = new javax.swing.JLabel();
        lblFreelanceVal = new javax.swing.JLabel();
        lblRendItem = new javax.swing.JLabel();
        lblRendVal = new javax.swing.JLabel();
        cardHistorial = new view.components.CardPanel();
        lblTitleHistorial = new javax.swing.JLabel();
        lblBadgeRegistros = new javax.swing.JLabel();
        btnTabTodos = new javax.swing.JButton();
        btnTabFijos = new javax.swing.JButton();
        btnTabVariables = new javax.swing.JButton();
        btnTabPendientes = new javax.swing.JButton();
        btnExportarCsv = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tblIngresos = new javax.swing.JTable();
        paginationBar = new view.components.PaginationBar();
        cardProyeccion = new view.components.CardPanel();
        lblProyIcon = new javax.swing.JLabel();
        lblProyTitle = new javax.swing.JLabel();
        lblProyMonto = new javax.swing.JLabel();
        lblProyBadge = new javax.swing.JLabel();
        lblProySub = new javax.swing.JLabel();
        btnProyDetalles = new javax.swing.JButton();
        cardCiclo = new view.components.CardPanel();
        lblCicloIcon = new javax.swing.JLabel();
        lblCicloTitle = new javax.swing.JLabel();
        lblCicloCobrado = new javax.swing.JLabel();
        lblCicloPendiente = new javax.swing.JLabel();
        lblCicloSub = new javax.swing.JLabel();
        lblProxLiqTitle = new javax.swing.JLabel();
        lblProxLiqFecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finandes - Gestión de Ingresos");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Ingresos");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Nuevo Ingreso");
        headerBar.setModuloActual("Gestión de Ingresos");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setPreferredSize(new java.awt.Dimension(1020, 1060));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Gestión de Ingresos");
        pnlMainContent.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 14, 200, 26));

        lblBadgeCiclo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBadgeCiclo.setForeground(new java.awt.Color(4, 120, 87));
        lblBadgeCiclo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeCiclo.setText("Ciclo Octubre 2023");
        lblBadgeCiclo.setOpaque(true);
        lblBadgeCiclo.setBackground(new java.awt.Color(209, 250, 229));
        pnlMainContent.add(lblBadgeCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 16, 130, 22));

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Control de entradas de capital, flujos salariales, cobros freelance y retornos de inversión.");
        pnlMainContent.add(lblSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 550, 18));

        btnFiltroOct.setBackground(new java.awt.Color(0, 159, 227));
        btnFiltroOct.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnFiltroOct.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroOct.setText("Oct 2023");
        btnFiltroOct.setBorderPainted(false);
        btnFiltroOct.setFocusPainted(false);
        pnlMainContent.add(btnFiltroOct, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 16, 82, 30));

        btnFiltroSep.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltroSep.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnFiltroSep.setForeground(new java.awt.Color(71, 85, 105));
        btnFiltroSep.setText("Sep 2023");
        btnFiltroSep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnFiltroSep.setFocusPainted(false);
        pnlMainContent.add(btnFiltroSep, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 16, 80, 30));

        btnFiltroAnual.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltroAnual.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnFiltroAnual.setForeground(new java.awt.Color(71, 85, 105));
        btnFiltroAnual.setText("Anual 2023");
        btnFiltroAnual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnFiltroAnual.setFocusPainted(false);
        pnlMainContent.add(btnFiltroAnual, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 16, 82, 30));

        btnAjustarVista.setBackground(new java.awt.Color(255, 255, 255));
        btnAjustarVista.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAjustarVista.setForeground(new java.awt.Color(71, 85, 105));
        btnAjustarVista.setText("Ajustar Vista");
        btnAjustarVista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnAjustarVista.setFocusPainted(false);
        pnlMainContent.add(btnAjustarVista, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 16, 84, 30));

        kpiTotales.setColorAcento(new java.awt.Color(66, 173, 77));
        kpiTotales.setMonto("$12,450.00");
        kpiTotales.setTitulo("INGRESOS TOTALES DEL MES");
        kpiTotales.setVariacion("↑ +12.4% vs mes anterior ($11,075.00)");
        pnlMainContent.add(kpiTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, 235, 115));

        kpiFijo.setColorAcento(new java.awt.Color(0, 159, 227));
        kpiFijo.setMonto("$8,500.00");
        kpiFijo.setTitulo("INGRESO FIJO / SALARIO");
        kpiFijo.setVariacion("68.2% del volumen total (Garantizado)");
        pnlMainContent.add(kpiFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 68, 235, 115));

        kpiVariable.setColorAcento(new java.awt.Color(245, 158, 11));
        kpiVariable.setMonto("$3,950.00");
        kpiVariable.setTitulo("INGRESOS VARIABLES / EXTRA");
        kpiVariable.setVariacion("● Freelance, Dividendos y Bonos");
        pnlMainContent.add(kpiVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 68, 235, 115));

        kpiPromedio.setColorAcento(new java.awt.Color(126, 34, 206));
        kpiPromedio.setMonto("$11,800.00");
        kpiPromedio.setTitulo("PROMEDIO MENSUAL");
        kpiPromedio.setVariacion("Últimos 6 meses (+5.5% YoY)");
        pnlMainContent.add(kpiPromedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 68, 240, 115));

        cardFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderForm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHeaderForm.setForeground(new java.awt.Color(15, 23, 42));
        lblHeaderForm.setText("+  Registrar Nuevo Ingreso");
        cardFormulario.add(lblHeaderForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 240, 24));

        lblBadgeManual.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblBadgeManual.setForeground(new java.awt.Color(71, 85, 105));
        lblBadgeManual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeManual.setText("ENTRADA MANUAL");
        lblBadgeManual.setOpaque(true);
        lblBadgeManual.setBackground(new java.awt.Color(241, 245, 249));
        cardFormulario.add(lblBadgeManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 18, 120, 20));

        lblMonto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(71, 85, 105));
        lblMonto.setText("Monto del Ingreso ($) *");
        cardFormulario.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 260, 18));

        txtMonto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(15, 23, 42));
        txtMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        cardFormulario.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 72, 260, 34));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(71, 85, 105));
        lblFecha.setText("Fecha de Recepción *");
        cardFormulario.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 52, 260, 18));

        txtFecha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(15, 23, 42));
        txtFecha.setText("28/10/2023");
        txtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        cardFormulario.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 72, 260, 34));

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(71, 85, 105));
        lblCategoria.setText("Categoría *");
        cardFormulario.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 114, 260, 18));

        cbCategoria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salario / Nómina Directa", "Proyectos Freelance & Tech", "Rendimientos & Dividendos", "Consultoría UX/UI", "Otros Ingresos" }));
        cardFormulario.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 134, 260, 34));

        lblTipoFlujo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTipoFlujo.setForeground(new java.awt.Color(71, 85, 105));
        lblTipoFlujo.setText("Tipo de Flujo *");
        cardFormulario.add(lblTipoFlujo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 114, 260, 18));

        rbFijo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbFijo.setForeground(new java.awt.Color(66, 173, 77));
        rbFijo.setSelected(true);
        rbFijo.setText("Fijo / Recurrente");
        rbFijo.setOpaque(false);
        cardFormulario.add(rbFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 138, 130, 24));

        rbVariable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbVariable.setForeground(new java.awt.Color(71, 85, 105));
        rbVariable.setText("Variable / Ocasional");
        rbVariable.setOpaque(false);
        cardFormulario.add(rbVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 138, 140, 24));

        lblCuenta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(71, 85, 105));
        lblCuenta.setText("Cuenta Destino *");
        cardFormulario.add(lblCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 176, 260, 18));

        cbCuentaDestino.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbCuentaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cuenta Corriente BBVA (Nómina)", "Cuenta Dólares", "Santander", "Stripe / Banco" }));
        cardFormulario.add(cbCuentaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 196, 260, 34));

        lblDesc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDesc.setForeground(new java.awt.Color(71, 85, 105));
        lblDesc.setText("Descripción o Fuente de Pago *");
        cardFormulario.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 176, 260, 18));

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(15, 23, 42));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        cardFormulario.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 196, 295, 34));

        chkRecordar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        chkRecordar.setForeground(new java.awt.Color(71, 85, 105));
        chkRecordar.setText("Recordar periódicamente cada mes");
        chkRecordar.setOpaque(false);
        cardFormulario.add(chkRecordar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 246, 250, 24));

        btnRegistrarIngreso.setBackground(new java.awt.Color(0, 159, 227));
        btnRegistrarIngreso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarIngreso.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarIngreso.setText("+  Registrar Ingreso");
        btnRegistrarIngreso.setBorderPainted(false);
        btnRegistrarIngreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarIngreso.setFocusPainted(false);
        cardFormulario.add(btnRegistrarIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 242, 165, 38));

        pnlMainContent.add(cardFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 620, 300));

        cardFiltroRapido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleFiltro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitleFiltro.setForeground(new java.awt.Color(15, 23, 42));
        lblTitleFiltro.setText("FILTRADO RÁPIDO");
        cardFiltroRapido.add(lblTitleFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 16, 160, 20));

        lblResetFiltro.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblResetFiltro.setForeground(new java.awt.Color(0, 159, 227));
        lblResetFiltro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResetFiltro.setText("Restablecer");
        lblResetFiltro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardFiltroRapido.add(lblResetFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 16, 90, 20));

        txtRangoDesde.setFont(new java.awt.Font("Segoe UI", 11, 0)); // NOI18N
        txtRangoDesde.setText("10/01/2023");
        txtRangoDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 213, 203)));
        cardFiltroRapido.add(txtRangoDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 44, 145, 30));

        txtRangoHasta.setFont(new java.awt.Font("Segoe UI", 11, 0)); // NOI18N
        txtRangoHasta.setText("10/31/2023");
        txtRangoHasta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 213, 203)));
        cardFiltroRapido.add(txtRangoHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 44, 145, 30));

        cbFiltroCategoria.setFont(new java.awt.Font("Segoe UI", 11, 0)); // NOI18N
        cbFiltroCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las categorías", "Salario", "Freelance" }));
        cardFiltroRapido.add(cbFiltroCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 82, 145, 30));

        cbFiltroCuenta.setFont(new java.awt.Font("Segoe UI", 11, 0)); // NOI18N
        cbFiltroCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las cuentas", "BBVA Nómina", "Santander" }));
        cardFiltroRapido.add(cbFiltroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 82, 145, 30));

        lblTitleDist.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTitleDist.setForeground(new java.awt.Color(15, 23, 42));
        lblTitleDist.setText("DISTRIBUCIÓN DE FUENTES");
        cardFiltroRapido.add(lblTitleDist, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 132, 190, 20));

        lblTotalDist.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTotalDist.setForeground(new java.awt.Color(71, 85, 105));
        lblTotalDist.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalDist.setText("Total: $12,450.00");
        cardFiltroRapido.add(lblTotalDist, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 132, 120, 20));
        cardFiltroRapido.add(progressBarFuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 158, 312, 12));

        lblNominaItem.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblNominaItem.setForeground(new java.awt.Color(30, 41, 59));
        lblNominaItem.setText("■ Nómina & Salario Fijo");
        cardFiltroRapido.add(lblNominaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 180, 180, 22));

        lblNominaVal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNominaVal.setForeground(new java.awt.Color(15, 23, 42));
        lblNominaVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNominaVal.setText("$8,500.00  (68.2%)");
        cardFiltroRapido.add(lblNominaVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 140, 22));

        lblFreelanceItem.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFreelanceItem.setForeground(new java.awt.Color(30, 41, 59));
        lblFreelanceItem.setText("■ Proyectos Freelance & Tech");
        cardFiltroRapido.add(lblFreelanceItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 206, 180, 22));

        lblFreelanceVal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFreelanceVal.setForeground(new java.awt.Color(15, 23, 42));
        lblFreelanceVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFreelanceVal.setText("$2,630.00  (21.1%)");
        cardFiltroRapido.add(lblFreelanceVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 206, 140, 22));

        lblRendItem.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblRendItem.setForeground(new java.awt.Color(30, 41, 59));
        lblRendItem.setText("■ Rendimientos & Dividendos");
        cardFiltroRapido.add(lblRendItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 232, 180, 22));

        lblRendVal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRendVal.setForeground(new java.awt.Color(15, 23, 42));
        lblRendVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRendVal.setText("$1,320.00  (10.7%)");
        cardFiltroRapido.add(lblRendVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 232, 140, 22));

        pnlMainContent.add(cardFiltroRapido, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 350, 300));

        cardHistorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitleHistorial.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblTitleHistorial.setForeground(new java.awt.Color(15, 23, 42));
        lblTitleHistorial.setText("Historial de Ingresos");
        cardHistorial.add(lblTitleHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 190, 24));

        lblBadgeRegistros.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBadgeRegistros.setForeground(new java.awt.Color(71, 85, 105));
        lblBadgeRegistros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeRegistros.setText("48 registros");
        lblBadgeRegistros.setOpaque(true);
        lblBadgeRegistros.setBackground(new java.awt.Color(241, 245, 249));
        cardHistorial.add(lblBadgeRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 18, 95, 20));

        btnTabTodos.setBackground(new java.awt.Color(0, 159, 227));
        btnTabTodos.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnTabTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnTabTodos.setText("Todos");
        btnTabTodos.setBorderPainted(false);
        btnTabTodos.setFocusPainted(false);
        cardHistorial.add(btnTabTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 14, 70, 28));

        btnTabFijos.setBackground(new java.awt.Color(255, 255, 255));
        btnTabFijos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnTabFijos.setForeground(new java.awt.Color(71, 85, 105));
        btnTabFijos.setText("Fijos");
        btnTabFijos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnTabFijos.setFocusPainted(false);
        cardHistorial.add(btnTabFijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 14, 60, 28));

        btnTabVariables.setBackground(new java.awt.Color(255, 255, 255));
        btnTabVariables.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnTabVariables.setForeground(new java.awt.Color(71, 85, 105));
        btnTabVariables.setText("Variables");
        btnTabVariables.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnTabVariables.setFocusPainted(false);
        cardHistorial.add(btnTabVariables, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 14, 70, 28));

        btnTabPendientes.setBackground(new java.awt.Color(255, 255, 255));
        btnTabPendientes.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnTabPendientes.setForeground(new java.awt.Color(71, 85, 105));
        btnTabPendientes.setText("Pendientes");
        btnTabPendientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnTabPendientes.setFocusPainted(false);
        cardHistorial.add(btnTabPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 14, 75, 28));

        btnExportarCsv.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarCsv.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportarCsv.setForeground(new java.awt.Color(71, 85, 105));
        btnExportarCsv.setText("CSV");
        btnExportarCsv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnExportarCsv.setFocusPainted(false);
        cardHistorial.add(btnExportarCsv, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 14, 55, 28));

        btnExportarExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(71, 85, 105));
        btnExportarExcel.setText("Excel");
        btnExportarExcel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnExportarExcel.setFocusPainted(false);
        cardHistorial.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 14, 55, 28));

        scrollTabla.setBorder(null);

        tblIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"28 Oct, 2023", "Nómina Quincenal Empresa Tech", "Salario", "BBVA Nómina", "Fijo", "+$4,250.00", "Confirmado", "Editar  Eliminar"},
                {"25 Oct, 2023", "Consultoría UX/UI Cliente USA", "Freelance", "Cuenta Dólares", "Variable", "+$1,850.00", "Confirmado", "Editar  Eliminar"},
                {"20 Oct, 2023", "Rentabilidad Fondo Indexado S&P", "Inversiones", "Santander", "Variable", "+$320.00", "Confirmado", "Editar  Eliminar"},
                {"15 Oct, 2023", "Nómina Quincenal Empresa Tech", "Salario", "BBVA Nómina", "Fijo", "+$4,250.00", "Confirmado", "Editar  Eliminar"},
                {"12 Oct, 2023", "Venta de Curso / Asesoría", "Servicios", "Stripe / Banco", "Variable", "+$780.00", "Confirmado", "Editar  Eliminar"},
                {"05 Oct, 2023", "Reembolso de Gastos de Viaje", "Reembolso", "BBVA", "Variable", "+$1,000.00", "Confirmado", "Editar  Eliminar"}
            },
            new String [] {
                "FECHA", "DESCRIPCIÓN / ORIGEN", "CATEGORÍA", "CUENTA DESTINO", "TIPO", "MONTO", "ESTADO", "ACCIONES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTabla.setViewportView(tblIngresos);

        cardHistorial.add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, 960, 280));
        cardHistorial.add(paginationBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 336, 960, 42));

        pnlMainContent.add(cardHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 515, 990, 390));

        cardProyeccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProyIcon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProyIcon.setForeground(new java.awt.Color(0, 159, 227));
        lblProyIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProyIcon.setText("PROY");
        cardProyeccion.add(lblProyIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 45, 45));

        lblProyTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblProyTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblProyTitle.setText("PROYECCIÓN DE INGRESOS PRÓXIMO MES");
        cardProyeccion.add(lblProyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 14, 280, 18));

        lblProyMonto.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblProyMonto.setForeground(new java.awt.Color(15, 23, 42));
        lblProyMonto.setText("$13,200.00");
        cardProyeccion.add(lblProyMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 34, 120, 28));

        lblProyBadge.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblProyBadge.setForeground(new java.awt.Color(66, 173, 77));
        lblProyBadge.setText("+6.0% estimado");
        cardProyeccion.add(lblProyBadge, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 40, 100, 20));

        lblProySub.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblProySub.setForeground(new java.awt.Color(100, 116, 139));
        lblProySub.setText("Incluye 2 contratos firmados y ajuste de retención.");
        cardProyeccion.add(lblProySub, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 66, 310, 18));

        btnProyDetalles.setBackground(new java.awt.Color(255, 255, 255));
        btnProyDetalles.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnProyDetalles.setForeground(new java.awt.Color(30, 41, 59));
        btnProyDetalles.setText("Detalles →");
        btnProyDetalles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        btnProyDetalles.setFocusPainted(false);
        cardProyeccion.add(btnProyDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 36, 80, 30));

        pnlMainContent.add(cardProyeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 915, 485, 100));

        cardCiclo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCicloIcon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCicloIcon.setForeground(new java.awt.Color(66, 173, 77));
        lblCicloIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCicloIcon.setText("OK");
        cardCiclo.add(lblCicloIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 45, 45));

        lblCicloTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCicloTitle.setForeground(new java.awt.Color(71, 85, 105));
        lblCicloTitle.setText("ESTADO DEL CICLO SALARIAL");
        cardCiclo.add(lblCicloTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 14, 220, 18));

        lblCicloCobrado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCicloCobrado.setForeground(new java.awt.Color(66, 173, 77));
        lblCicloCobrado.setText("100% Cobrado");
        cardCiclo.add(lblCicloCobrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 34, 130, 26));

        lblCicloPendiente.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblCicloPendiente.setForeground(new java.awt.Color(100, 116, 139));
        lblCicloPendiente.setText("Sin pendientes");
        cardCiclo.add(lblCicloPendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 38, 100, 20));

        lblCicloSub.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCicloSub.setForeground(new java.awt.Color(100, 116, 139));
        lblCicloSub.setText("Todos los ingresos fijos del período han sido acreditados.");
        cardCiclo.add(lblCicloSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 64, 280, 18));

        lblProxLiqTitle.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblProxLiqTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblProxLiqTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProxLiqTitle.setText("Próxima liquidación:");
        cardCiclo.add(lblProxLiqTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 125, 16));

        lblProxLiqFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProxLiqFecha.setForeground(new java.awt.Color(15, 23, 42));
        lblProxLiqFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProxLiqFecha.setText("15 Nov, 2023");
        cardCiclo.add(lblProxLiqFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 46, 125, 20));

        pnlMainContent.add(cardCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 915, 490, 100));

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
                new IngresosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjustarVista;
    private javax.swing.JButton btnExportarCsv;
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnFiltroAnual;
    private javax.swing.JButton btnFiltroOct;
    private javax.swing.JButton btnFiltroSep;
    private javax.swing.JButton btnProyDetalles;
    private javax.swing.JButton btnRegistrarIngreso;
    private javax.swing.JButton btnTabFijos;
    private javax.swing.JButton btnTabPendientes;
    private javax.swing.JButton btnTabTodos;
    private javax.swing.JButton btnTabVariables;
    private view.components.CardPanel cardCiclo;
    private view.components.CardPanel cardFiltroRapido;
    private view.components.CardPanel cardFormulario;
    private view.components.CardPanel cardHistorial;
    private view.components.CardPanel cardProyeccion;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbCuentaDestino;
    private javax.swing.JComboBox<String> cbFiltroCategoria;
    private javax.swing.JComboBox<String> cbFiltroCuenta;
    private javax.swing.JCheckBox chkRecordar;
    private view.components.HeaderBar headerBar;
    private view.components.KpiCard kpiFijo;
    private view.components.KpiCard kpiPromedio;
    private view.components.KpiCard kpiTotales;
    private view.components.KpiCard kpiVariable;
    private javax.swing.JLabel lblBadgeCiclo;
    private javax.swing.JLabel lblBadgeManual;
    private javax.swing.JLabel lblBadgeRegistros;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCicloCobrado;
    private javax.swing.JLabel lblCicloIcon;
    private javax.swing.JLabel lblCicloPendiente;
    private javax.swing.JLabel lblCicloSub;
    private javax.swing.JLabel lblCicloTitle;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFreelanceItem;
    private javax.swing.JLabel lblFreelanceVal;
    private javax.swing.JLabel lblHeaderForm;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNominaItem;
    private javax.swing.JLabel lblNominaVal;
    private javax.swing.JLabel lblProxLiqFecha;
    private javax.swing.JLabel lblProxLiqTitle;
    private javax.swing.JLabel lblProyBadge;
    private javax.swing.JLabel lblProyIcon;
    private javax.swing.JLabel lblProyMonto;
    private javax.swing.JLabel lblProySub;
    private javax.swing.JLabel lblProyTitle;
    private javax.swing.JLabel lblRendItem;
    private javax.swing.JLabel lblRendVal;
    private javax.swing.JLabel lblResetFiltro;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTipoFlujo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleDist;
    private javax.swing.JLabel lblTitleFiltro;
    private javax.swing.JLabel lblTitleHistorial;
    private javax.swing.JLabel lblTotalDist;
    private view.components.PaginationBar paginationBar;
    private javax.swing.JPanel pnlMainContent;
    private view.components.SegmentedProgressBar progressBarFuentes;
    private javax.swing.JRadioButton rbFijo;
    private javax.swing.JRadioButton rbVariable;
    private javax.swing.JScrollPane scrollContent;
    private javax.swing.JScrollPane scrollTabla;
    private view.components.SidebarPanel sidebarPanel;
    private javax.swing.JTable tblIngresos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtRangoDesde;
    private javax.swing.JTextField txtRangoHasta;
    // End of variables declaration//GEN-END:variables

    // Métodos Getters para la integración con MVC
    public JTextField getTxtMonto() { return txtMonto; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<String> getCbCategoria() { return cbCategoria; }
    public JComboBox<String> getCbCuentaDestino() { return cbCuentaDestino; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }
    public JRadioButton getRbFijo() { return rbFijo; }
    public JRadioButton getRbVariable() { return rbVariable; }
    public JCheckBox getChkRecordar() { return chkRecordar; }
    public JButton getBtnRegistrarIngreso() { return btnRegistrarIngreso; }
    public JTable getTblIngresos() { return tblIngresos; }
    public PaginationBar getPaginationBar() { return paginationBar; }
    public HeaderBar getHeaderBar() { return headerBar; }
    public SidebarPanel getSidebarPanel() { return sidebarPanel; }
    public SegmentedProgressBar getProgressBarFuentes() { return progressBarFuentes; }
    public KpiCard getKpiTotales() { return kpiTotales; }
    public KpiCard getKpiFijo() { return kpiFijo; }
    public KpiCard getKpiVariable() { return kpiVariable; }
    public KpiCard getKpiPromedio() { return kpiPromedio; }
    public JPanel getPnlMainContent() { return pnlMainContent; }
    public JScrollPane getScrollContent() { return scrollContent; }
}
