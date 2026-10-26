package view;

import controller.GastoController;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import model.ComboItem;
import model.Session;
import model.Usuario;
import view.components.ModernTableHelper;
import view.components.SidebarPanel;
import view.components.HeaderBar;
import view.components.PaginationBar;
import view.components.ThemeColor;

public class SystemView extends javax.swing.JFrame {

    private GastoController controller;
    public int idGastoSeleccionado = 0;

    public SystemView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Gestión de Gastos");
        setLocationRelativeTo(null);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date inicioMes = cal.getTime();
        dateFiltroDesde.setDate(inicioMes);
        dateFechaNuevo.setDate(new Date());
        dateFiltroHasta.setDate(new Date());

        controller = new GastoController(this);
        controller.listarGastos();

        setupEvents();
    }

    private void initCustomStyles() {
        ModernTableHelper.styleTable(tblGastos);
        ModernTableHelper.setBadgeColumn(tblGastos, 3, 4);
        ModernTableHelper.setMoneyColumn(tblGastos, 5);
        ModernTableHelper.styleScrollPane(jScrollPane1);

        scrollContent.getVerticalScrollBar().setUnitIncrement(16);

        dateFiltroDesde.setDateFormatString("dd/MM/yyyy");
        dateFiltroHasta.setDateFormatString("dd/MM/yyyy");
        dateFechaNuevo.setDateFormatString("dd/MM/yyyy");

        headerBar.getBtnAccion().addActionListener(evt -> txtMonto.requestFocus());
    }

    private void setupEvents() {
        dateFiltroDesde.addPropertyChangeListener("date", evt -> {
            if (controller != null) {
                controller.listarGastos();
                actualizarPaginacion();
            }
        });

        dateFiltroHasta.addPropertyChangeListener("date", evt -> {
            if (controller != null) {
                controller.listarGastos();
                actualizarPaginacion();
            }
        });

        cbUser.addActionListener(evt -> {
            if (controller != null) {
                controller.listarGastos();
                actualizarPaginacion();
            }
        });

        actualizarPaginacion();
    }

    public void actualizarPaginacion() {
        int total = tblGastos.getRowCount();
        paginationBar.updatePagination(1, total > 0 ? total : 1, 10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRegistro = new javax.swing.JLabel();
        lblBadgeForm = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateFechaNuevo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<ComboItem>();
        jLabel6 = new javax.swing.JLabel();
        cbTipoGastos = new javax.swing.JComboBox<ComboItem>();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregarGasto = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dateFiltroDesde = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        dateFiltroHasta = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        cbUser = new javax.swing.JComboBox<ComboItem>();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblBadgePeriodo = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        paginationBar = new view.components.PaginationBar();
        jPanelTotalGastos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanelPromedioMensual = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanelStatus = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanelPeriodoSiguiente = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Gestión de Gastos");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Gastos");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Nuevo Gasto");
        headerBar.setModuloActual("Gestión de Gastos");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setPreferredSize(new java.awt.Dimension(1020, 710));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Gestión de Gastos");
        pnlMainContent.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 14, 260, 26));

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Control y seguimiento detallado de egresos transaccionales.");
        pnlMainContent.add(lblSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 40, 450, 18));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistro.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(15, 23, 42));
        lblRegistro.setText("REGISTRAR NUEVO GASTO");
        jPanel2.add(lblRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 12, 260, 22));

        lblBadgeForm.setBackground(new java.awt.Color(239, 246, 255));
        lblBadgeForm.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblBadgeForm.setForeground(new java.awt.Color(2, 132, 199));
        lblBadgeForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeForm.setText("TRANSACCIÓN");
        lblBadgeForm.setOpaque(true);
        jPanel2.add(lblBadgeForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 14, 100, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 116, 139));
        jLabel3.setText("Monto (S/.) *");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 38, 115, 16));

        txtMonto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(15, 23, 42));
        txtMonto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });
        jPanel2.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 56, 115, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 116, 139));
        jLabel4.setText("Fecha *");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 38, 125, 16));

        dateFechaNuevo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel2.add(dateFechaNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 56, 125, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 116, 139));
        jLabel5.setText("Categoría *");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 38, 135, 16));

        cbCategoria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel2.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 56, 135, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(100, 116, 139));
        jLabel6.setText("Tipo Gasto *");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 38, 135, 16));

        cbTipoGastos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel2.add(cbTipoGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 56, 135, 32));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(100, 116, 139));
        jLabel7.setText("Descripción *");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 96, 200, 16));

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(15, 23, 42));
        txtDescripcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        jPanel2.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 116, 355, 34));

        btnAgregarGasto.setBackground(new java.awt.Color(0, 159, 227));
        btnAgregarGasto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarGasto.setText("Agregar Transacción");
        btnAgregarGasto.setBorderPainted(false);
        btnAgregarGasto.setFocusPainted(false);
        btnAgregarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarGastoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 116, 175, 34));

        pnlMainContent.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 66, 580, 175));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(15, 23, 42));
        jLabel15.setText("FILTROS");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 12, 180, 22));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 159, 227));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Limpiar todo");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 14, 95, 18));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(100, 116, 139));
        jLabel16.setText("Desde");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 38, 80, 16));

        dateFiltroDesde.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        dateFiltroDesde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateFiltroDesdeMouseEntered(evt);
            }
        });
        jPanel8.add(dateFiltroDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 56, 160, 32));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(100, 116, 139));
        jLabel17.setText("Hasta");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 38, 80, 16));

        dateFiltroHasta.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel8.add(dateFiltroHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 56, 160, 32));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(100, 116, 139));
        jLabel18.setText("Filtrar por Usuario");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 96, 150, 16));

        cbUser.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbUser.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cbUserInputMethodTextChanged(evt);
            }
        });
        jPanel8.add(cbUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 116, 337, 34));

        pnlMainContent.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 66, 375, 175));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(15, 23, 42));
        jLabel14.setText("HISTORIAL DE GASTOS");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 180, 22));

        lblBadgePeriodo.setBackground(new java.awt.Color(224, 243, 254));
        lblBadgePeriodo.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblBadgePeriodo.setForeground(new java.awt.Color(2, 132, 199));
        lblBadgePeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgePeriodo.setText("PERÍODO ACTUAL");
        lblBadgePeriodo.setOpaque(true);
        jPanel7.add(lblBadgePeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 15, 115, 20));

        btnBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(30, 41, 51));
        btnBuscar1.setText("Editar");
        btnBuscar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnBuscar1.setFocusPainted(false);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 12, 85, 30));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(239, 68, 68));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel7.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 12, 90, 30));

        btnExportarExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(35, 51, 71));
        btnExportarExcel.setText("Export CSV");
        btnExportarExcel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportarExcel.setFocusPainted(false);
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });
        jPanel7.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 12, 115, 30));

        jScrollPane1.setBorder(null);

        tblGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "DESCRIPCIÓN", "CATEGORIA", "TIPO GASTO", "MONTO", "CREADO POR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGastos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblGastos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblGastos);
        if (tblGastos.getColumnModel().getColumnCount() > 0) {
            tblGastos.getColumnModel().getColumn(0).setMinWidth(0);
            tblGastos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblGastos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblGastos.getColumnModel().getColumn(1).setResizable(false);
            tblGastos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblGastos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblGastos.getColumnModel().getColumn(3).setPreferredWidth(140);
            tblGastos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblGastos.getColumnModel().getColumn(5).setResizable(false);
            tblGastos.getColumnModel().getColumn(5).setPreferredWidth(110);
            tblGastos.getColumnModel().getColumn(6).setResizable(false);
            tblGastos.getColumnModel().getColumn(6).setPreferredWidth(110);
        }

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 48, 934, 230));
        jPanel7.add(paginationBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 285, 934, 36));

        pnlMainContent.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 255, 970, 330));

        jPanelTotalGastos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelTotalGastos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(254, 226, 226));
        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/chat-flecha-abajo.png"))); // NOI18N
        jPanel6.add(jLabel8, "card2");

        jPanelTotalGastos.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 18, 46, 46));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(100, 116, 139));
        jLabel20.setText("GASTOS TOTALES");
        jPanelTotalGastos.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 16, 145, 18));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(15, 23, 42));
        jLabel21.setText("S/. 0.00");
        jPanelTotalGastos.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 145, 26));

        pnlMainContent.add(jPanelTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 598, 230, 88));

        jPanelPromedioMensual.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPromedioMensual.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelPromedioMensual.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(239, 246, 254));
        jPanel12.setLayout(new java.awt.CardLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/estadisticas.png"))); // NOI18N
        jPanel12.add(jLabel10, "card2");

        jPanelPromedioMensual.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 18, 46, 46));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(100, 116, 139));
        jLabel24.setText("PROMEDIO MENSUAL");
        jPanelPromedioMensual.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 16, 145, 18));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(15, 23, 42));
        jLabel25.setText("S/. 0.00");
        jPanelPromedioMensual.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 145, 26));

        pnlMainContent.add(jPanelPromedioMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 598, 230, 88));

        jPanelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelStatus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(209, 250, 229));
        jPanel14.setLayout(new java.awt.CardLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jPanel14.add(jLabel11, "card2");

        jPanelStatus.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 18, 46, 46));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(100, 116, 139));
        jLabel26.setText("ESTADO");
        jPanelStatus.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 16, 145, 18));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 108, 73));
        jLabel27.setText("DENTRO DEL PRESUPUESTO");
        jPanelStatus.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 150, 26));

        pnlMainContent.add(jPanelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 598, 230, 88));

        jPanelPeriodoSiguiente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPeriodoSiguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelPeriodoSiguiente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(254, 243, 199));
        jPanel16.setLayout(new java.awt.CardLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png"))); // NOI18N
        jPanel16.add(jLabel12, "card2");

        jPanelPeriodoSiguiente.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 18, 46, 46));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(100, 116, 139));
        jLabel28.setText("PRÓXIMO RECURRENTE");
        jPanelPeriodoSiguiente.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 16, 145, 18));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(15, 23, 42));
        jLabel29.setText("28 Oct, 2023");
        jPanelPeriodoSiguiente.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 38, 145, 26));

        pnlMainContent.add(jPanelPeriodoSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 598, 230, 88));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    public void limpiarFormularioGasto() {
        txtMonto.setText("");
        dateFechaNuevo.setDate(new Date());
        if (cbCategoria.getItemCount() > 0) cbCategoria.setSelectedIndex(0);
        if (cbTipoGastos.getItemCount() > 0) cbTipoGastos.setSelectedIndex(0);
        txtDescripcion.setText("");
        idGastoSeleccionado = 0;
        lblRegistro.setText("REGISTRAR NUEVO GASTO");
        btnAgregarGasto.setText("Agregar Transacción");
    }

    private void guardarGasto() {
        controller.guardar();
        JOptionPane.showMessageDialog(this, "Gasto registrado correctamente");
    }

    private void actualizarGasto() {
        controller.actualizar();
        idGastoSeleccionado = 0;
        lblRegistro.setText("REGISTRAR NUEVO GASTO");
        btnAgregarGasto.setText("Agregar Transacción");
    }

    private void dateFiltroDesdeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFiltroDesdeMouseEntered
    }//GEN-LAST:event_dateFiltroDesdeMouseEntered

    private void cbUserInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbUserInputMethodTextChanged
    }//GEN-LAST:event_cbUserInputMethodTextChanged

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Calendar cal = Calendar.getInstance();
        dateFiltroHasta.setDate(cal.getTime());

        cal.set(Calendar.DAY_OF_MONTH, 1);
        dateFiltroDesde.setDate(cal.getTime());

        if (cbUser.getItemCount() > 0) cbUser.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        controller.obtenerGastoSeleccionado();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblGastos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Aún no ha seleccionado ninguna fila", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas eliminar el gasto seleccionado?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            controller.eliminarGastoSeleccionado();
            controller.listarGastos();
            actualizarPaginacion();
            limpiarFormularioGasto();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        controller.exportarExcel();
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    private void btnAgregarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarGastoActionPerformed
        ComboItem categoria = (ComboItem) cbCategoria.getSelectedItem();
        ComboItem tipo = (ComboItem) cbTipoGastos.getSelectedItem();

        if (txtMonto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto");
            return;
        }

        if (dateFechaNuevo.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (categoria == null || categoria.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipo == null || tipo.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de Gasto", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una descripción");
            return;
        }

        if (idGastoSeleccionado == 0) {
            guardarGasto();
        } else {
            actualizarGasto();
        }

        controller.listarGastos();
        actualizarPaginacion();
        limpiarFormularioGasto();
    }//GEN-LAST:event_btnAgregarGastoActionPerformed

    public void limpiarCampos() {
        limpiarFormularioGasto();
    }

    public SidebarPanel getSidebarPanel() {
        return sidebarPanel;
    }

    public HeaderBar getHeaderBar() {
        return headerBar;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new SystemView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarGasto;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportarExcel;
    public javax.swing.JComboBox<ComboItem> cbCategoria;
    public javax.swing.JComboBox<ComboItem> cbTipoGastos;
    public javax.swing.JComboBox<ComboItem> cbUser;
    public com.toedter.calendar.JDateChooser dateFechaNuevo;
    public com.toedter.calendar.JDateChooser dateFiltroDesde;
    public com.toedter.calendar.JDateChooser dateFiltroHasta;
    private view.components.HeaderBar headerBar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanelPeriodoSiguiente;
    public javax.swing.JPanel jPanelPromedioMensual;
    public javax.swing.JPanel jPanelStatus;
    public javax.swing.JPanel jPanelTotalGastos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBadgeForm;
    private javax.swing.JLabel lblBadgePeriodo;
    public javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private view.components.PaginationBar paginationBar;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JScrollPane scrollContent;
    private view.components.SidebarPanel sidebarPanel;
    public javax.swing.JTable tblGastos;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
