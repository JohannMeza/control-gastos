package view;

import controller.AuditoriaController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;

public class AuditoriaView extends javax.swing.JFrame {

    private final AuditoriaController controller;

    // Componentes del Sidebar
    private javax.swing.JPanel Sidebar;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployes;
    private javax.swing.JPanel jPanelSupplimers;
    private javax.swing.JPanel jPanelCategories;
    private javax.swing.JPanel jPanelAuditoria;

    // Componentes del Header y Logo
    private javax.swing.JPanel Header;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JPanel Logo;
    private javax.swing.JLabel lblLogoImg;

    // JTabbedPane Principal (Body)
    private javax.swing.JTabbedPane Body;

    // Sub-pestañas
    private javax.swing.JPanel tabVectoresMatrices;
    private javax.swing.JPanel tabListas;
    private javax.swing.JPanel tabPilasColas;
    private javax.swing.JPanel tabArboles;

    // Sub-pestaña 1: Vectores y Matrices
    public javax.swing.JTextField txtVectorMostrar;
    public javax.swing.JTextField txtVectorValor;
    public javax.swing.JTextField txtVectorPos;
    public javax.swing.JTextField txtVectorComparar;
    public javax.swing.JTable tblMatriz;
    public javax.swing.JTextField txtMatrizFila;
    public javax.swing.JTextField txtMatrizCol;
    public javax.swing.JTextField txtMatrizValor;

    // Sub-pestaña 2: Listas Enlazadas
    public javax.swing.JTable tblListaSimple;
    public javax.swing.JTextField txtListaDesc;
    public javax.swing.JTextField txtListaMonto;
    public javax.swing.JComboBox<String> cbListaCat;
    public javax.swing.JLabel lblLogActual;
    public javax.swing.JButton btnAnteriorLog;
    public javax.swing.JButton btnSiguienteLog;

    // Sub-pestaña 3: Pilas y Colas
    public javax.swing.JTable tblPila;
    public javax.swing.JLabel lblPilaStatus;
    public javax.swing.JButton btnDeshacer;
    public javax.swing.JButton btnRehacer;
    public javax.swing.JTable tblColaAlertas;
    public javax.swing.JTextField txtColaMensaje;
    public javax.swing.JComboBox<Integer> cbColaPrioridad;
    public javax.swing.JButton btnDesencolarAlerta;

    // Sub-pestaña 4: Árboles
    public javax.swing.JTable tblArbolCategorias;
    public javax.swing.JTextField txtArbolNombreCat;
    public javax.swing.JTextArea txtAreaRecorridos;

    public AuditoriaView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administraci\u00f3n - Auditor\u00eda Financiera");
        setLocationRelativeTo(null);

        this.controller = new AuditoriaController(this);
        this.controller.inicializarVista();
        this.repaint();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(248, 249, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Font mainFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 14);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 18);

        // ==================== LOGO ====================
        Logo = new javax.swing.JPanel();
        Logo.setBackground(new java.awt.Color(248, 250, 252));
        Logo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogoImg = new javax.swing.JLabel();
        lblLogoImg.setBackground(new java.awt.Color(248, 250, 252));
        lblLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        try {
            lblLogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo.png")));
        } catch (Exception e) {}
        Logo.add(lblLogoImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        // ==================== HEADER ====================
        Header = new javax.swing.JPanel();
        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(234, 241, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderTitle.setFont(headerFont);
        lblHeaderTitle.setForeground(new java.awt.Color(11, 28, 48));
        lblHeaderTitle.setText("Auditor\u00eda Financiera");
        Header.add(lblHeaderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        // ==================== SIDEBAR ====================
        Sidebar = new javax.swing.JPanel();
        Sidebar.setBackground(new java.awt.Color(248, 250, 252));
        Sidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Sidebar.setLayout(null);

        // Dashboard
        jPanelProducts = createSidebarButton("Dashboard", "/images/icon/grey/ahorros.png", false);
        jPanelProducts.setBounds(0, 10, 200, 35);
        jPanelProducts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new DashboardView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(jPanelProducts);

        // Gastos
        pnlGasto = createSidebarButton("Gastos", "/images/icon/grey/deudas.png", false);
        pnlGasto.setBounds(0, 70, 200, 35);
        pnlGasto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new SystemView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(pnlGasto);

        // Presupuesto
        jPanelCustomers = createSidebarButton("Presupuesto", "/images/icon/grey/presupuesto.png", false);
        jPanelCustomers.setBounds(0, 130, 200, 35);
        jPanelCustomers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new PresupuestoView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(jPanelCustomers);

        // Ahorros
        jPanelEmployes = createSidebarButton("Ahorros", "/images/icon/grey/ahorros.png", false);
        jPanelEmployes.setBounds(0, 190, 200, 35);
        jPanelEmployes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new AhorrosView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(jPanelEmployes);

        // Deudas
        jPanelSupplimers = createSidebarButton("Deudas", "/images/icon/grey/deudas.png", false);
        jPanelSupplimers.setBounds(0, 250, 200, 35);
        jPanelSupplimers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new DeudasView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(jPanelSupplimers);

        // Colaboradores
        jPanelCategories = createSidebarButton("Usuarios", "/images/icon/grey/usuario.png", false);
        jPanelCategories.setBounds(0, 310, 200, 35);
        jPanelCategories.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new UsuariosView().setVisible(true);
                dispose();
            }
        });
        Sidebar.add(jPanelCategories);

        // Auditoría Financiera (Selected)
        jPanelAuditoria = createSidebarButton("Auditor\u00eda", "/images/icon/grey/auditoria.png", true);
        jPanelAuditoria.setBounds(0, 370, 200, 35);
        Sidebar.add(jPanelAuditoria);

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 600));

        // ==================== BODY (JTabbedPane) ====================
        Body = new javax.swing.JTabbedPane();
        Body.setBackground(new java.awt.Color(248, 249, 255));
        Body.setFont(titleFont);

        // --- SUB-PESTAÑA 1: VECTORES Y MATRICES ---
        tabVectoresMatrices = new javax.swing.JPanel();
        tabVectoresMatrices.setBackground(new java.awt.Color(241, 245, 249));
        tabVectoresMatrices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Título del Formulario (Fuera de la tarjeta)
        JLabel lblTitleVec = new JLabel("1. Control de Vectores (Arreglos 1D)");
        lblTitleVec.setFont(titleFont);
        lblTitleVec.setForeground(new Color(11, 28, 48));
        tabVectoresMatrices.add(lblTitleVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 500, 20));

        // Vectores Panel (Tarjeta Blanca sin Borde)
        JPanel pnlVec = new JPanel();
        pnlVec.setBackground(java.awt.Color.WHITE);
        pnlVec.setBorder(null);
        pnlVec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel lblVec = new JLabel("Arreglo en Memoria:");
        lblVec.setFont(mainFont);
        pnlVec.add(lblVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 120, 20));

        txtVectorMostrar = new javax.swing.JTextField();
        txtVectorMostrar.setEditable(false);
        txtVectorMostrar.setFont(new Font("Consolas", Font.BOLD, 12));
        pnlVec.add(txtVectorMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 12, 790, 25));

        JLabel lblVal = new JLabel("Valor (double):");
        pnlVec.add(lblVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 52, 90, 20));
        txtVectorValor = new javax.swing.JTextField();
        pnlVec.add(txtVectorValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 49, 80, 25));

        JLabel lblPos = new JLabel("Posici\u00f3n (int):");
        pnlVec.add(lblPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 52, 80, 20));
        txtVectorPos = new javax.swing.JTextField();
        pnlVec.add(txtVectorPos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 49, 50, 25));

        JButton btnInsVec = new JButton("Insertar");
        btnInsVec.addActionListener(e -> {
            try {
                controller.insertarVector(Double.parseDouble(txtVectorValor.getText()), Integer.parseInt(txtVectorPos.getText()));
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Valores incorrectos"); }
        });
        pnlVec.add(btnInsVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 49, 90, 25));

        JButton btnUpdVec = new JButton("Actualizar");
        btnUpdVec.addActionListener(e -> {
            try {
                controller.actualizarVector(Integer.parseInt(txtVectorPos.getText()), Double.parseDouble(txtVectorValor.getText()));
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Valores incorrectos"); }
        });
        pnlVec.add(btnUpdVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 49, 90, 25));

        JButton btnDelVec = new JButton("Eliminar");
        btnDelVec.addActionListener(e -> {
            try {
                controller.eliminarVector(Integer.parseInt(txtVectorPos.getText()));
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Posici\u00f3n incorrecta"); }
        });
        pnlVec.add(btnDelVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 49, 90, 25));

        JButton btnCopyVec = new JButton("Copiar Arreglo");
        btnCopyVec.addActionListener(e -> {
            double[] copia = controller.copiarVector();
            JOptionPane.showMessageDialog(this, "Arreglo copiado con \u00e9xito.");
        });
        pnlVec.add(btnCopyVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 49, 120, 25));

        JLabel lblComp = new JLabel("Comparar con (separado por comas):");
        pnlVec.add(lblComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 90, 230, 20));
        txtVectorComparar = new javax.swing.JTextField();
        pnlVec.add(txtVectorComparar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 87, 300, 25));

        JButton btnCompVec = new JButton("Comparar");
        btnCompVec.addActionListener(e -> controller.compararVector(txtVectorComparar.getText()));
        pnlVec.add(btnCompVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 87, 100, 25));

        tabVectoresMatrices.add(pnlVec, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 960, 130));

        // Título del Formulario (Fuera de la tarjeta)
        JLabel lblTitleMat = new JLabel("2. \u00c1lgebra de Matrices Contables (Arreglos 2D)");
        lblTitleMat.setFont(titleFont);
        lblTitleMat.setForeground(new Color(11, 28, 48));
        tabVectoresMatrices.add(lblTitleMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 182, 500, 20));

        // Matrices Panel (Tarjeta Blanca sin Borde)
        JPanel pnlMat = new JPanel();
        pnlMat.setBackground(java.awt.Color.WHITE);
        pnlMat.setBorder(null);
        pnlMat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMatriz = new javax.swing.JTable();
        tblMatriz.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Enero", "Febrero", "Marzo"}));
        tblMatriz.setRowHeight(35);
        tblMatriz.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollMat = new JScrollPane(tblMatriz);
        pnlMat.add(scrollMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 400, 130));

        JLabel lblMatFila = new JLabel("Fila (0-2):");
        pnlMat.add(lblMatFila, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 70, 20));
        txtMatrizFila = new javax.swing.JTextField();
        pnlMat.add(txtMatrizFila, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 17, 40, 25));

        JLabel lblMatCol = new JLabel("Col (0-2):");
        pnlMat.add(lblMatCol, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 70, 20));
        txtMatrizCol = new javax.swing.JTextField();
        pnlMat.add(txtMatrizCol, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 17, 40, 25));

        JLabel lblMatVal = new JLabel("Valor:");
        pnlMat.add(lblMatVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 50, 20));
        txtMatrizValor = new javax.swing.JTextField();
        pnlMat.add(txtMatrizValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 17, 60, 25));

        JButton btnRegMat = new JButton("Modificar Celda");
        btnRegMat.addActionListener(e -> {
            try {
                controller.registrarDatoMatriz(Integer.parseInt(txtMatrizFila.getText()), Integer.parseInt(txtMatrizCol.getText()), Double.parseDouble(txtMatrizValor.getText()));
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Valores incorrectos."); }
        });
        pnlMat.add(btnRegMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 17, 120, 25));

        JButton btnTransMat = new JButton("Transponer (Aᵀ)");
        btnTransMat.addActionListener(e -> controller.transponerMatriz());
        pnlMat.add(btnTransMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 62, 150, 30));

        JButton btnDetMat = new JButton("Determinante");
        btnDetMat.addActionListener(e -> controller.determinanteMatriz());
        pnlMat.add(btnDetMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 62, 150, 30));

        JButton btnInvMat = new JButton("Invertir Matriz (A⁻¹)");
        btnInvMat.addActionListener(e -> controller.invertirMatriz());
        pnlMat.add(btnInvMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 62, 180, 30));

        JButton btnSimMat = new JButton("\u00bfEs Sim\u00e9trica?");
        btnSimMat.addActionListener(e -> controller.checkSimetria());
        pnlMat.add(btnSimMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 107, 150, 30));

        JButton btnAsimMat = new JButton("\u00bfEs Asim\u00e9trica?");
        btnAsimMat.addActionListener(e -> controller.checkAsimetria());
        pnlMat.add(btnAsimMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 107, 150, 30));

        tabVectoresMatrices.add(pnlMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 207, 960, 160));

        Body.addTab("Vectores y Matrices", tabVectoresMatrices);

        // --- SUB-PESTAÑA 2: LISTAS ENLAZADAS ---
        tabListas = new javax.swing.JPanel();
        tabListas.setBackground(new java.awt.Color(241, 245, 249));
        tabListas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Título del Formulario (Fuera de la tarjeta)
        JLabel lblTitleSimple = new JLabel("1. Lista Enlazada Simple (Gastos Contables)");
        lblTitleSimple.setFont(titleFont);
        lblTitleSimple.setForeground(new Color(11, 28, 48));
        tabListas.add(lblTitleSimple, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 500, 20));

        // Panel Lista Simple (Tarjeta Blanca sin Borde)
        JPanel pnlSimple = new JPanel();
        pnlSimple.setBackground(java.awt.Color.WHITE);
        pnlSimple.setBorder(null);
        pnlSimple.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblListaSimple = new javax.swing.JTable();
        tblListaSimple.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Descripci\u00f3n", "Monto", "Categor\u00eda", "Fecha"}));
        tblListaSimple.setRowHeight(25);
        JScrollPane scrollSimple = new JScrollPane(tblListaSimple);
        pnlSimple.add(scrollSimple, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 520, 180));

        // Formulario
        JLabel lblLDesc = new JLabel("Descripci\u00f3n:");
        pnlSimple.add(lblLDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 80, 20));
        txtListaDesc = new javax.swing.JTextField();
        pnlSimple.add(txtListaDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 17, 300, 25));

        JLabel lblLMonto = new JLabel("Monto (S/):");
        pnlSimple.add(lblLMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 80, 20));
        txtListaMonto = new javax.swing.JTextField();
        pnlSimple.add(txtListaMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 57, 100, 25));

        JLabel lblLCat = new JLabel("Categor\u00eda:");
        pnlSimple.add(lblLCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 70, 20));
        cbListaCat = new javax.swing.JComboBox<>(new String[]{"Alimentos", "Servicios", "Transporte", "Entretenimiento", "Otros"});
        pnlSimple.add(cbListaCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 57, 110, 25));

        JButton btnInsLIni = new JButton("Insertar Inicio");
        btnInsLIni.addActionListener(e -> {
            try {
                controller.insertarGastoLista(txtListaDesc.getText(), Double.parseDouble(txtListaMonto.getText()), cbListaCat.getSelectedItem().toString(), true);
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Datos inv\u00e1lidos"); }
        });
        pnlSimple.add(btnInsLIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 120, 30));

        JButton btnInsLFin = new JButton("Insertar Final");
        btnInsLFin.addActionListener(e -> {
            try {
                controller.insertarGastoLista(txtListaDesc.getText(), Double.parseDouble(txtListaMonto.getText()), cbListaCat.getSelectedItem().toString(), false);
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Datos inv\u00e1lidos"); }
        });
        pnlSimple.add(btnInsLFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 120, 30));

        JButton btnDelL = new JButton("Eliminar por Desc.");
        btnDelL.addActionListener(e -> controller.eliminarGastoLista(txtListaDesc.getText()));
        pnlSimple.add(btnDelL, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 120, 30));

        JButton btnSearchL = new JButton("Buscar Gasto");
        btnSearchL.addActionListener(e -> controller.buscarGastoLista(txtListaDesc.getText()));
        pnlSimple.add(btnSearchL, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 145, 180, 30));

        JButton btnSortL = new JButton("Bubble Sort (Montos)");
        btnSortL.addActionListener(e -> controller.ordenarGastosLista());
        pnlSimple.add(btnSortL, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 145, 180, 30));

        tabListas.add(pnlSimple, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 960, 210));

        // Título del Formulario 2 (Fuera de la tarjeta)
        JLabel lblTitleDoble = new JLabel("2. Lista Doblemente Enlazada (Bit\u00e1cora de Sesi\u00f3n)");
        lblTitleDoble.setFont(titleFont);
        lblTitleDoble.setForeground(new Color(11, 28, 48));
        tabListas.add(lblTitleDoble, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 262, 500, 20));

        // Panel Lista Doble (Tarjeta Blanca sin Borde)
        JPanel pnlDoble = new JPanel();
        pnlDoble.setBackground(java.awt.Color.WHITE);
        pnlDoble.setBorder(null);
        pnlDoble.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogActual = new javax.swing.JLabel("Inicializando...");
        lblLogActual.setFont(new Font("Consolas", Font.BOLD, 13));
        lblLogActual.setForeground(new Color(37, 99, 235));
        lblLogActual.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        pnlDoble.add(lblLogActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 650, 45));

        btnAnteriorLog = new JButton("◀ Registro Anterior");
        btnAnteriorLog.addActionListener(e -> controller.navegarLog(false));
        pnlDoble.add(btnAnteriorLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 15, 130, 45));

        btnSiguienteLog = new JButton("Registro Siguiente ▶");
        btnSiguienteLog.addActionListener(e -> controller.navegarLog(true));
        pnlDoble.add(btnSiguienteLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 15, 130, 45));

        tabListas.add(pnlDoble, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 287, 960, 75));

        Body.addTab("Listas Enlazadas", tabListas);

        // --- SUB-PESTAÑA 3: PILAS Y COLAS ---
        tabPilasColas = new javax.swing.JPanel();
        tabPilasColas.setBackground(new java.awt.Color(241, 245, 249));
        tabPilasColas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Título del Formulario (Fuera de la tarjeta)
        JLabel lblTitlePila = new JLabel("1. Pila de Auditor\u00eda Contable (LIFO - Deshacer / Rehacer)");
        lblTitlePila.setFont(titleFont);
        lblTitlePila.setForeground(new Color(11, 28, 48));
        tabPilasColas.add(lblTitlePila, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 500, 20));

        // Pila (Tarjeta Blanca sin Borde)
        JPanel pnlPila = new JPanel();
        pnlPila.setBackground(java.awt.Color.WHITE);
        pnlPila.setBorder(null);
        pnlPila.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPila = new javax.swing.JTable();
        tblPila.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Descripci\u00f3n", "Monto", "Categor\u00eda"}));
        JScrollPane scrollPila = new JScrollPane(tblPila);
        pnlPila.add(scrollPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 420, 150));

        lblPilaStatus = new JLabel("Asientos en pila: 0 / 20");
        lblPilaStatus.setFont(mainFont);
        pnlPila.add(lblPilaStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 175, 420, 20));

        JLabel lblPDesc = new JLabel("Concepto:");
        pnlPila.add(lblPDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 70, 20));
        JTextField txtPilaDesc = new JTextField();
        pnlPila.add(txtPilaDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 17, 400, 25));

        JLabel lblPMonto = new JLabel("Monto:");
        pnlPila.add(lblPMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 70, 20));
        JTextField txtPilaMonto = new JTextField();
        pnlPila.add(txtPilaMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 57, 100, 25));

        JLabel lblPCat = new JLabel("Categor\u00eda:");
        pnlPila.add(lblPCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 75, 20));
        JComboBox<String> cbPilaCat = new JComboBox<>(new String[]{"Alimentos", "Servicios", "Otros"});
        pnlPila.add(cbPilaCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 57, 200, 25));

        JButton btnPush = new JButton("Registrar Asiento (Push)");
        btnPush.addActionListener(e -> {
            try {
                controller.registrarAccionSimulada(txtPilaDesc.getText(), Double.parseDouble(txtPilaMonto.getText()), cbPilaCat.getSelectedItem().toString());
                txtPilaDesc.setText("");
                txtPilaMonto.setText("");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Monto inv\u00e1lido"); }
        });
        pnlPila.add(btnPush, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 470, 30));

        btnDeshacer = new JButton("Deshacer Operaci\u00f3n (Pop)");
        btnDeshacer.addActionListener(e -> controller.deshacerAccion());
        pnlPila.add(btnDeshacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 220, 30));

        btnRehacer = new JButton("Rehacer Operaci\u00f3n");
        btnRehacer.addActionListener(e -> controller.rehacerAccion());
        pnlPila.add(btnRehacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 220, 30));

        tabPilasColas.add(pnlPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 960, 210));

        // Título del Formulario 2 (Fuera de la tarjeta)
        JLabel lblTitleCola = new JLabel("2. Cola de Alertas de Riesgos (Prioridad sobre FIFO)");
        lblTitleCola.setFont(titleFont);
        lblTitleCola.setForeground(new Color(11, 28, 48));
        tabPilasColas.add(lblTitleCola, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 262, 500, 20));

        // Cola (Tarjeta Blanca sin Borde)
        JPanel pnlCola = new JPanel();
        pnlCola.setBackground(java.awt.Color.WHITE);
        pnlCola.setBorder(null);
        pnlCola.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblColaAlertas = new javax.swing.JTable();
        tblColaAlertas.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Descripci\u00f3n de Alerta Financiera", "Prioridad"}));
        JScrollPane scrollCola = new JScrollPane(tblColaAlertas);
        pnlCola.add(scrollCola, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 420, 75));

        JLabel lblCMsg = new JLabel("Alerta:");
        pnlCola.add(lblCMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 50, 20));
        txtColaMensaje = new JTextField();
        pnlCola.add(txtColaMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 17, 260, 25));

        JLabel lblCPrio = new JLabel("Prioridad (1-5):");
        pnlCola.add(lblCPrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 20, 90, 20));
        cbColaPrioridad = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        pnlCola.add(cbColaPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 17, 50, 25));

        JButton btnEncolar = new JButton("Encolar Alerta");
        btnEncolar.addActionListener(e -> {
            controller.encolarAlerta(txtColaMensaje.getText(), (Integer) cbColaPrioridad.getSelectedItem());
            txtColaMensaje.setText("");
        });
        pnlCola.add(btnEncolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 55, 230, 30));

        btnDesencolarAlerta = new JButton("Atender Alerta de Prioridad");
        btnDesencolarAlerta.addActionListener(e -> controller.desencolarAlerta());
        pnlCola.add(btnDesencolarAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 55, 230, 30));

        tabPilasColas.add(pnlCola, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 287, 960, 100));

        Body.addTab("Pilas y Colas", tabPilasColas);

        // --- SUB-PESTAÑA 4: ÁRBOLES ---
        tabArboles = new javax.swing.JPanel();
        tabArboles.setBackground(new java.awt.Color(241, 245, 249));
        tabArboles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Título del Formulario (Fuera de la tarjeta)
        JLabel lblTitleArbol = new JLabel("Estructura de \u00c1rbol Binario de B\u00fasqueda (ABB - Categor\u00edas)");
        lblTitleArbol.setFont(titleFont);
        lblTitleArbol.setForeground(new Color(11, 28, 48));
        tabArboles.add(lblTitleArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 500, 20));

        // Panel ABB (Tarjeta Blanca sin Borde)
        JPanel pnlArbol = new JPanel();
        pnlArbol.setBackground(java.awt.Color.WHITE);
        pnlArbol.setBorder(null);
        pnlArbol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblArbolCategorias = new javax.swing.JTable();
        tblArbolCategorias.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Categor\u00edas Indexadas en ABB"}));
        JScrollPane scrollArbol = new JScrollPane(tblArbolCategorias);
        pnlArbol.add(scrollArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 400, 300));

        JLabel lblArbCat = new JLabel("Categor\u00eda:");
        pnlArbol.add(lblArbCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 80, 20));
        txtArbolNombreCat = new JTextField();
        pnlArbol.add(txtArbolNombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 17, 200, 25));

        JButton btnInsArb = new JButton("Insertar (ABB)");
        btnInsArb.addActionListener(e -> {
            controller.insertarCategoriaArbol(txtArbolNombreCat.getText());
            txtArbolNombreCat.setText("");
        });
        pnlArbol.add(btnInsArb, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 17, 100, 25));

        JButton btnDelArb = new JButton("Eliminar");
        btnDelArb.addActionListener(e -> {
            controller.eliminarCategoriaArbol(txtArbolNombreCat.getText());
            txtArbolNombreCat.setText("");
        });
        pnlArbol.add(btnDelArb, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 17, 90, 25));

        JButton btnSearchArb = new JButton("Buscar en ABB");
        btnSearchArb.addActionListener(e -> controller.buscarCategoriaArbol(txtArbolNombreCat.getText()));
        pnlArbol.add(btnSearchArb, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 57, 495, 30));

        // Recorridos
        JLabel lblRec = new JLabel("Inspeccionar Recorridos Jer\u00e1rquicos:");
        lblRec.setFont(titleFont);
        pnlArbol.add(lblRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 300, 20));

        JButton btnIn = new JButton("Inorden (LNR)");
        btnIn.addActionListener(e -> controller.ejecutarRecorridoABB("INORDEN"));
        pnlArbol.add(btnIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 125, 150, 30));

        JButton btnPre = new JButton("Preorden (NLR)");
        btnPre.addActionListener(e -> controller.ejecutarRecorridoABB("PREORDEN"));
        pnlArbol.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 125, 150, 30));

        JButton btnPost = new JButton("Postorden (LRN)");
        btnPost.addActionListener(e -> controller.ejecutarRecorridoABB("POSTORDEN"));
        pnlArbol.add(btnPost, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 125, 150, 30));

        txtAreaRecorridos = new javax.swing.JTextArea();
        txtAreaRecorridos.setEditable(false);
        txtAreaRecorridos.setFont(new Font("Consolas", Font.BOLD, 13));
        txtAreaRecorridos.setForeground(new Color(11, 28, 48));
        txtAreaRecorridos.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        JScrollPane scrollArea = new JScrollPane(txtAreaRecorridos);
        pnlArbol.add(scrollArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 495, 145));

        tabArboles.add(pnlArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 37, 960, 330));

        Body.addTab("\u00c1rboles (ABB)", tabArboles);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));
        util.MenuHelper.addAvatarToHeader(this, Header);

        pack();
    }

    private javax.swing.JPanel createSidebarButton(String text, String iconPath, boolean active) {
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setBackground(active ? new java.awt.Color(244, 248, 254) : new java.awt.Color(248, 250, 252));
        if (active) {
            panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));
        }
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.JLabel label = new javax.swing.JLabel();
        label.setFont(new java.awt.Font("Tahoma", active ? Font.BOLD : Font.PLAIN, 14));
        label.setForeground(active ? new java.awt.Color(37, 99, 235) : new java.awt.Color(71, 85, 105));
        label.setText(text);
        
        try {
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath.replace("grey", active ? "blue" : "grey"))));
        } catch(Exception e) {
            // Fallback
        }

        panel.setLayout(new java.awt.BorderLayout());
        label.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        panel.add(label, java.awt.BorderLayout.CENTER);
        return panel;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AuditoriaView().setVisible(true));
    }
}
