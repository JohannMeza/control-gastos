package view;

import controller.PresupuestoController;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import model.ComboItem;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;

public class PresupuestoView extends javax.swing.JFrame {
    private PresupuestoController controller;
    public int idPresupuestoSeleccionado = 0;
    
    public PresupuestoView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        setupTableStyle();
        this.repaint();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Date inicioMes = cal.getTime();
        
        controller = new PresupuestoController(this);
        controller.listarCategorias();
        
        // Programmatic addition of the Auditoria button in Sidebar
        Sidebar.setLayout(null);
        jPanelProducts.setBounds(0, 10, 200, 35);
        jPanelProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new DashboardView().setVisible(true);
                dispose();
            }
        });
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
    
    public void limpiarFormularioGasto() {
        txtPresupuestoMensual.setText("");
        cbCategoria.setSelectedIndex(0);
        cbPeriodo.setSelectedIndex(3);
        txtUmbral.setText("");
    }

    private void guardarGasto() {
        controller.guardarPresupuesto();
    }
    
    private void actualizarGasto() {
        controller.actualizarPresupuesto();
        idPresupuestoSeleccionado = 0;
        lblRegistro.setText("REGISTRAR NUEVO GASTO");
        btnAgregarEditarPresupuesto.setText("Agregar Transacción");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPresupuestos = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRegistro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPresupuestoMensual = new javax.swing.JTextField();
        btnAgregarEditarPresupuesto = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        txtUmbral = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        cbCategoria = new javax.swing.JComboBox<>();
        cbPeriodo = new javax.swing.JComboBox<>();
        jPanelTotalGastos = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanelStatus = new javax.swing.JPanel();
        lblRegistro2 = new javax.swing.JLabel();
        jPanelPeriodoSiguiente1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanelTotalGastos1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblRegistro1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        jPanelTotalGastos2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sidebar.setBackground(new java.awt.Color(248, 250, 252));
        Sidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));

        jPanelProducts.setBackground(new java.awt.Color(248, 250, 252));
        jPanelProducts.setForeground(new java.awt.Color(71, 85, 105));

        jLabelProducts.setBackground(new java.awt.Color(255, 255, 255));
        jLabelProducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aplicaciones.png"))); // NOI18N
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
        pnlGasto.setBorder(null);
        pnlGasto.setForeground(new java.awt.Color(71, 85, 105));
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

        jPanelCustomers.setBackground(new java.awt.Color(244, 248, 254));
        jPanelCustomers.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));
        jPanelCustomers.setForeground(new java.awt.Color(71, 85, 105));
        jPanelCustomers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelCustomers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCustomers.setForeground(new java.awt.Color(37, 99, 235));
        jLabelCustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/presupuesto.png"))); // NOI18N
        jLabelCustomers.setText("Presupuesto");

        javax.swing.GroupLayout jPanelCustomersLayout = new javax.swing.GroupLayout(jPanelCustomers);
        jPanelCustomers.setLayout(jPanelCustomersLayout);
        jPanelCustomersLayout.setHorizontalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelCustomers)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelEmployes.setBackground(new java.awt.Color(248, 250, 252));
        jPanelEmployes.setForeground(new java.awt.Color(71, 85, 105));
        jPanelEmployes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelEmployes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEmployesMouseClicked(evt);
            }
        });

        jLabelEmployes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelEmployes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ahorros.png"))); // NOI18N
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
        jPanelSupplimers.setForeground(new java.awt.Color(71, 85, 105));
        jPanelSupplimers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelSupplimers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSupplimersMouseClicked(evt);
            }
        });

        jLabelSupplimers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSupplimers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deudas.png"))); // NOI18N
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

        jPanelCategories.setBackground(new java.awt.Color(248, 250, 252));
        jPanelCategories.setForeground(new java.awt.Color(71, 85, 105));
        jPanelCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCategoriesMouseClicked(evt);
            }
        });

        jLabelCategories.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jLabel2.setText("Presupuesto");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        tblPresupuestos.setBackground(new java.awt.Color(255, 255, 255));
        tblPresupuestos.setForeground(new java.awt.Color(51, 51, 51));
        tblPresupuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA", "ASIGNADO", "GASTADO", "ESTADO", "PROGRESO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPresupuestos.setDoubleBuffered(true);
        tblPresupuestos.setDragEnabled(true);
        tblPresupuestos.setGridColor(new java.awt.Color(255, 255, 255));
        tblPresupuestos.setSelectionBackground(new java.awt.Color(0, 74, 198));
        tblPresupuestos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPresupuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPresupuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblPresupuestos);
        if (tblPresupuestos.getColumnModel().getColumnCount() > 0) {
            tblPresupuestos.getColumnModel().getColumn(0).setMinWidth(0);
            tblPresupuestos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPresupuestos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 640, 230));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(11, 28, 48));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/historial.png"))); // NOI18N
        jLabel14.setText("PRESUPUESTO DE LAS CATEGORIAS");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 270, 20));

        jLabel1.setBackground(new java.awt.Color(220, 233, 255));
        jLabel1.setForeground(new java.awt.Color(67, 70, 85));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SETIEMBRE 2023");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 120, 30));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 640, 280));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        lblRegistro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistro.setText("Agregar Presupuesto");

        jLabel3.setForeground(new java.awt.Color(11, 28, 48));
        jLabel3.setText("Seleccionar Categoria");

        jLabel7.setForeground(new java.awt.Color(11, 28, 48));
        jLabel7.setText("Límite de presupuesto mensual");

        txtPresupuestoMensual.setBackground(new java.awt.Color(255, 255, 255));
        txtPresupuestoMensual.setPreferredSize(new java.awt.Dimension(15, 25));
        txtPresupuestoMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresupuestoMensualKeyTyped(evt);
            }
        });

        btnAgregarEditarPresupuesto.setBackground(new java.awt.Color(37, 99, 235));
        btnAgregarEditarPresupuesto.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnAgregarEditarPresupuesto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarEditarPresupuesto.setText("Agregar Presupuesto");
        btnAgregarEditarPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarEditarPresupuesto.setPreferredSize(new java.awt.Dimension(131, 25));
        btnAgregarEditarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEditarPresupuestoActionPerformed(evt);
            }
        });

        jLabel37.setForeground(new java.awt.Color(11, 28, 48));
        jLabel37.setText("Umbral de Alerta");

        txtUmbral.setBackground(new java.awt.Color(255, 255, 255));
        txtUmbral.setPreferredSize(new java.awt.Dimension(15, 25));

        jLabel38.setForeground(new java.awt.Color(11, 28, 48));
        jLabel38.setText("Periodo");

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(186, 26, 26));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/limpio.png"))); // NOI18N
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<ComboItem>());

        cbPeriodo.setModel(new javax.swing.DefaultComboBoxModel<ComboItem>());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtUmbral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregarEditarPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(lblRegistro)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPresupuestoMensual, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblRegistro)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPresupuestoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbPeriodo)
                    .addComponent(txtUmbral, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarEditarPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 330, 340));

        jPanelTotalGastos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 198, 215), 1, true));

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(11, 28, 48));
        jLabel20.setText("Total Presupuesto");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(11, 28, 48));
        jLabel21.setText("S/. 0.00");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/gastos.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setRequestFocusEnabled(false);

        jLabel31.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 108, 73));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jLabel31.setText("2.4% vs mes anterior");

        javax.swing.GroupLayout jPanelTotalGastosLayout = new javax.swing.GroupLayout(jPanelTotalGastos);
        jPanelTotalGastos.setLayout(jPanelTotalGastosLayout);
        jPanelTotalGastosLayout.setHorizontalGroup(
            jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanelTotalGastosLayout.setVerticalGroup(
            jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, 200));

        jPanelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        lblRegistro2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblRegistro2.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistro2.setText("ACTIVIDAD PRESUPUESTO RECIENTE");

        jPanelPeriodoSiguiente1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPeriodoSiguiente1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(11, 28, 48));
        jLabel39.setText("Presupuesto Excedido");

        jPanel14.setBackground(new java.awt.Color(108, 248, 187));
        jPanel14.setName(""); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel14.setLayout(new java.awt.CardLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setMaximumSize(new java.awt.Dimension(9, 9));
        jLabel11.setMinimumSize(new java.awt.Dimension(9, 9));
        jLabel11.setPreferredSize(new java.awt.Dimension(9, 9));
        jLabel11.setRequestFocusEnabled(false);
        jPanel14.add(jLabel11, "card2");

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(11, 28, 48));
        jLabel41.setText("Food & Dinning went over limit by 142.30");

        jLabel42.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(11, 28, 48));
        jLabel42.setText("hace 2 horas");

        javax.swing.GroupLayout jPanelPeriodoSiguiente1Layout = new javax.swing.GroupLayout(jPanelPeriodoSiguiente1);
        jPanelPeriodoSiguiente1.setLayout(jPanelPeriodoSiguiente1Layout);
        jPanelPeriodoSiguiente1Layout.setHorizontalGroup(
            jPanelPeriodoSiguiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeriodoSiguiente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPeriodoSiguiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanelPeriodoSiguiente1Layout.setVerticalGroup(
            jPanelPeriodoSiguiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeriodoSiguiente1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanelPeriodoSiguiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPeriodoSiguiente1Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistro2)
                    .addComponent(jPanelPeriodoSiguiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblRegistro2)
                .addGap(18, 18, 18)
                .addComponent(jPanelPeriodoSiguiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 330, 140));

        jPanelTotalGastos1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 198, 215), 1, true));

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(11, 28, 48));
        jLabel22.setText("Gastado hasta hoy");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(11, 28, 48));
        jLabel23.setText("S/. 0.00");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setRequestFocusEnabled(false);

        jLabel32.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(11, 28, 48));
        jLabel32.setText("57% de total usado");

        javax.swing.GroupLayout jPanelTotalGastos1Layout = new javax.swing.GroupLayout(jPanelTotalGastos1);
        jPanelTotalGastos1.setLayout(jPanelTotalGastos1Layout);
        jPanelTotalGastos1Layout.setHorizontalGroup(
            jPanelTotalGastos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastos1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelTotalGastos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTotalGastos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(jPanelTotalGastos1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanelTotalGastos1Layout.setVerticalGroup(
            jPanelTotalGastos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastos1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTotalGastos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelTotalGastos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 200, 200));

        lblRegistro1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRegistro1.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistro1.setText("Resumen del Presupuesto");
        jPanel1.add(lblRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(11, 28, 48));
        jLabel30.setText("Gestiona tus límites de gasto mensuales y realiza un seguimiento del rendimiento de cada categoría.");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        btnFiltrar.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(11, 28, 48));
        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/filtrar.png"))); // NOI18N
        btnFiltrar.setText("Filtros");
        btnFiltrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnFiltrar.setBorderPainted(false);
        btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 100, 35));

        btnExportarExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(11, 28, 48));
        btnExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/grey/descargar.png"))); // NOI18N
        btnExportarExcel.setText("Exportar Reporte");
        btnExportarExcel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnExportarExcel.setBorderPainted(false);
        btnExportarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 140, 35));

        jPanelTotalGastos2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(195, 198, 215), 1, true));
        jPanelTotalGastos2.setForeground(new java.awt.Color(11, 28, 48));

        jLabel33.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(11, 28, 48));
        jLabel33.setText("Restante");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(11, 28, 48));
        jLabel34.setText("S/. 0.00");

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png"))); // NOI18N
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel35.setRequestFocusEnabled(false);

        jLabel36.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(11, 28, 48));
        jLabel36.setText(" Quedan 12 días en el período");

        javax.swing.GroupLayout jPanelTotalGastos2Layout = new javax.swing.GroupLayout(jPanelTotalGastos2);
        jPanelTotalGastos2.setLayout(jPanelTotalGastos2Layout);
        jPanelTotalGastos2Layout.setHorizontalGroup(
            jPanelTotalGastos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastos2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelTotalGastos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addGroup(jPanelTotalGastos2Layout.createSequentialGroup()
                        .addGroup(jPanelTotalGastos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTotalGastos2Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTotalGastos2Layout.setVerticalGroup(
            jPanelTotalGastos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastos2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTotalGastos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelTotalGastos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 200, 200));

        Body.addTab("Presupuesto", jPanel1);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlGastoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGastoMouseClicked
        SystemView viewSystem = new SystemView();
        viewSystem.setVisible(true);
        dispose();
    }//GEN-LAST:event_pnlGastoMouseClicked

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {
        DashboardView viewDashboard = new DashboardView();
        viewDashboard.setVisible(true);
        dispose();
    }

    private void jPanelEmployesMouseClicked(java.awt.event.MouseEvent evt) {
        AhorrosView viewAhorros = new AhorrosView();
        viewAhorros.setVisible(true);
        dispose();
    }

    private void txtPresupuestoMensualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoMensualKeyTyped

    }//GEN-LAST:event_txtPresupuestoMensualKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblPresupuestos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Aún no ha seleccionado ninguna fila",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas eliminar el presupuesto seleccionado?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            controller.eliminarPresupuestoSeleccionado();
            controller.listarPresupuesto();
            limpiarFormularioGasto();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarEditarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEditarPresupuestoActionPerformed
        ComboItem categoria = (ComboItem) cbCategoria.getSelectedItem();
        ComboItem tipo = (ComboItem) cbPeriodo.getSelectedItem();

        if (categoria.getValue() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una categoría",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (txtPresupuestoMensual.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese presupuesto");
            return;
        }

        if (txtUmbral.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese umbral de alerta");
            return;
        }

        if (tipo.getValue() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una tipo de periodo",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (idPresupuestoSeleccionado == 0) {
            guardarGasto();
        } else {
            actualizarGasto();
        }

        controller.listarPresupuesto();
        limpiarFormularioGasto();
    }//GEN-LAST:event_btnAgregarEditarPresupuestoActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PresupuestoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PresupuestoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PresupuestoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PresupuestoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PresupuestoView().setVisible(true);
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
    public javax.swing.JButton btnAgregarEditarPresupuesto;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarExcel;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JComboBox<ComboItem> cbCategoria;
    public javax.swing.JComboBox<ComboItem> cbPeriodo;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabelCategories;
    public javax.swing.JLabel jLabelCustomers;
    public javax.swing.JLabel jLabelEmployes;
    public javax.swing.JLabel jLabelProducts;
    public javax.swing.JLabel jLabelPurchases;
    public javax.swing.JLabel jLabelSupplimers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelCategories;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployes;
    private javax.swing.JPanel jPanelPeriodoSiguiente1;
    private javax.swing.JPanel jPanelProducts;
    public javax.swing.JPanel jPanelStatus;
    private javax.swing.JPanel jPanelSupplimers;
    private javax.swing.JPanel jPanelTotalGastos;
    private javax.swing.JPanel jPanelTotalGastos1;
    private javax.swing.JPanel jPanelTotalGastos2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblRegistro;
    public javax.swing.JLabel lblRegistro1;
    public javax.swing.JLabel lblRegistro2;
    private javax.swing.JPanel pnlGasto;
    public javax.swing.JTable tblPresupuestos;
    public javax.swing.JTextField txtPresupuestoMensual;
    public javax.swing.JTextField txtUmbral;
    // End of variables declaration//GEN-END:variables

    private void setupTableStyle() {
        tblPresupuestos.setRowHeight(30);
        tblPresupuestos.getTableHeader().setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
        tblPresupuestos.getTableHeader().setBackground(new java.awt.Color(248, 250, 252));
        tblPresupuestos.getTableHeader().setForeground(new java.awt.Color(71, 85, 105));
        
        // Columna 4 (ESTADO) - Pill badges
        tblPresupuestos.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String text = (value != null) ? value.toString() : "";
                
                // Color badges matching AhorrosView estilo y colores:
                // Red for EXCEDIDO, Orange for ADVERTENCIA, Green for BAJO, Blue for EN CAMINO
                if (text.equalsIgnoreCase("EXCEDIDO")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #fee2e2; color: #991b1b; border-radius: 12px; font-weight: bold;'>EXCEDIDO</body></html>");
                } else if (text.equalsIgnoreCase("ADVERTENCIA")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #ffedd5; color: #9a3412; border-radius: 12px; font-weight: bold;'>ADVERTENCIA</body></html>");
                } else if (text.equalsIgnoreCase("BAJO")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #d1fae5; color: #065f46; border-radius: 12px; font-weight: bold;'>BAJO</body></html>");
                } else if (text.equalsIgnoreCase("EN CAMINO")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #dbeafe; color: #1e40af; border-radius: 12px; font-weight: bold;'>EN CAMINO</body></html>");
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
        
        // Columna 5 (PROGRESO) - ProgressBarRenderer
        tblPresupuestos.getColumnModel().getColumn(5).setCellRenderer(new ProgressBarRenderer());

        // Columna 0 (ID) - Center aligned
        tblPresupuestos.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.CENTER); }
        });
        
        // Align amounts right and format to Soles S/.
        tblPresupuestos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.RIGHT); setFont(new Font("Dialog", Font.BOLD, 12)); }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (value instanceof Double) {
                    setText("S/. " + String.format("%.2f", (Double)value));
                } else if (value != null) {
                    try {
                        double val = Double.parseDouble(value.toString());
                        setText("S/. " + String.format("%.2f", val));
                    } catch (Exception e) {}
                }
                return c;
            }
        });
        tblPresupuestos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            { setHorizontalAlignment(SwingConstants.RIGHT); setFont(new Font("Dialog", Font.BOLD, 12)); }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (value instanceof Double) {
                    setText("S/. " + String.format("%.2f", (Double)value));
                } else if (value != null) {
                    try {
                        double val = Double.parseDouble(value.toString());
                        setText("S/. " + String.format("%.2f", val));
                    } catch (Exception e) {}
                }
                return c;
            }
        });
    }

    public static class ProgressBarRenderer extends JProgressBar implements javax.swing.table.TableCellRenderer {
        public ProgressBarRenderer() {
            super(0, 100);
            setStringPainted(true);
            setBorderPainted(false);
            setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 11));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            double pct = 0;
            if (value instanceof Number) {
                pct = ((Number) value).doubleValue();
            } else if (value != null) {
                String valStr = value.toString().replace("%", "").replace(",", ".").trim();
                try {
                    pct = Double.parseDouble(valStr);
                } catch (NumberFormatException e) {
                    pct = 0.0;
                }
            }

            setValue((int) Math.round(pct));
            setString(String.format("%.1f%%", pct));

            // Color logic:
            // Exceeded (> 100%) = Red
            // Low (<= 30%) = Green
            // Normal (30% - 100%) = Blue
            if (pct > 100.0) {
                setForeground(new Color(186, 26, 26));
            } else if (pct <= 30.0) {
                setForeground(new Color(34, 197, 94));
            } else {
                setForeground(new Color(37, 99, 235));
            }

            setBackground(new Color(241, 245, 249));

            return this;
        }
    }
}
