package view;

import controller.GastoController;
import java.util.Date;
import javax.swing.JOptionPane;
import model.ComboItem;
import java.util.Calendar;

public class SystemView extends javax.swing.JFrame {
    private GastoController controller;
    public int idGastoSeleccionado = 0;
    
    public SystemView() {
        initComponents();
        setResizable(false);
        setTitle("Panel de Administración");
        setLocationRelativeTo(null);
        this.repaint();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Date inicioMes = cal.getTime();
        dateFiltroDesde.setDate(inicioMes);
        dateFechaNuevo.setDate(new java.util.Date());
        dateFiltroHasta.setDate(new java.util.Date());
        
        controller = new GastoController(this);
        controller.listarGastos();
        
        dateFiltroDesde.addPropertyChangeListener("date", evt -> {
            controller.listarGastos();
        });
        
        dateFiltroHasta.addPropertyChangeListener("date", evt -> {
            controller.listarGastos();
        });
        
        cbUser.addActionListener(evt -> {
            controller.listarGastos();
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
        pnlPresupuesto = new javax.swing.JPanel();
        menuPresupuesto = new javax.swing.JLabel();
        jPanelEmployes = new javax.swing.JPanel();
        jLabelEmployes = new javax.swing.JLabel();
        jPanelSupplimers = new javax.swing.JPanel();
        jLabelSupplimers = new javax.swing.JLabel();
        jPanelCategories = new javax.swing.JPanel();
        jLabelCategories = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Logo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Body = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        btnExportarExcel = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateFiltroDesde = new com.toedter.calendar.JDateChooser();
        dateFiltroHasta = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        cbUser = new javax.swing.JComboBox<ComboItem>();
        jPanel2 = new javax.swing.JPanel();
        lblRegistro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateFechaNuevo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregarGasto = new javax.swing.JButton();
        cbCategoria = new javax.swing.JComboBox<ComboItem>();
        cbTipoGastos = new javax.swing.JComboBox<ComboItem>();
        jPanelTotalGastos = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanelPromedioMensual = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelStatus = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanelPeriodoSiguiente = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(248, 249, 255));
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

        pnlGasto.setBackground(new java.awt.Color(244, 248, 254));
        pnlGasto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(37, 99, 235)));
        pnlGasto.setForeground(new java.awt.Color(71, 85, 105));
        pnlGasto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelPurchases.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPurchases.setForeground(new java.awt.Color(37, 99, 235));
        jLabelPurchases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/gastos.png"))); // NOI18N
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

        pnlPresupuesto.setBackground(new java.awt.Color(248, 250, 252));
        pnlPresupuesto.setForeground(new java.awt.Color(71, 85, 105));
        pnlPresupuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPresupuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPresupuestoMouseClicked(evt);
            }
        });

        menuPresupuesto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        menuPresupuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/presupuesto.png"))); // NOI18N
        menuPresupuesto.setText("Presupuesto");

        javax.swing.GroupLayout pnlPresupuestoLayout = new javax.swing.GroupLayout(pnlPresupuesto);
        pnlPresupuesto.setLayout(pnlPresupuestoLayout);
        pnlPresupuestoLayout.setHorizontalGroup(
            pnlPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresupuestoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(menuPresupuesto)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        pnlPresupuestoLayout.setVerticalGroup(
            pnlPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanelEmployes.setBackground(new java.awt.Color(248, 250, 252));
        jPanelEmployes.setForeground(new java.awt.Color(71, 85, 105));

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
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelCategories, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSupplimers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEmployes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(SidebarLayout.createSequentialGroup()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPresupuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pnlPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelEmployes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelSupplimers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );

        getContentPane().add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 600));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(234, 241, 255)));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 28, 48));
        jLabel2.setText("GASTOS");
        Header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 0, -1, 39));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1010, 40));

        Logo.setBackground(new java.awt.Color(248, 250, 252));
        Logo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(234, 241, 255)));
        Logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(248, 250, 252));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo.png"))); // NOI18N
        Logo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 80));

        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        tblGastos.setBackground(new java.awt.Color(255, 255, 255));
        tblGastos.setForeground(new java.awt.Color(51, 51, 51));
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
        tblGastos.setDoubleBuffered(true);
        tblGastos.setDragEnabled(true);
        tblGastos.setGridColor(new java.awt.Color(255, 255, 255));
        tblGastos.setSelectionBackground(new java.awt.Color(0, 74, 198));
        tblGastos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblGastos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblGastos);
        if (tblGastos.getColumnModel().getColumnCount() > 0) {
            tblGastos.getColumnModel().getColumn(0).setMinWidth(0);
            tblGastos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblGastos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblGastos.getColumnModel().getColumn(1).setResizable(false);
            tblGastos.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblGastos.getColumnModel().getColumn(2).setMinWidth(250);
            tblGastos.getColumnModel().getColumn(3).setMinWidth(200);
            tblGastos.getColumnModel().getColumn(4).setMinWidth(150);
            tblGastos.getColumnModel().getColumn(5).setResizable(false);
            tblGastos.getColumnModel().getColumn(5).setPreferredWidth(60);
            tblGastos.getColumnModel().getColumn(6).setResizable(false);
            tblGastos.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 990, 280));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(11, 28, 48));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/historial.png"))); // NOI18N
        jLabel14.setText("HISTORIAL DE TRANSACCIONES");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, 20));

        btnBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(11, 28, 48));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lapiz.png"))); // NOI18N
        btnBuscar1.setText("Editar");
        btnBuscar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 90, 35));

        btnExportarExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(11, 28, 48));
        btnExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/descargar.png"))); // NOI18N
        btnExportarExcel.setText("Export CSV");
        btnExportarExcel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnExportarExcel.setBorderPainted(false);
        btnExportarExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });
        jPanel7.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 120, 35));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(186, 26, 26));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/limpio.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel7.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 90, 35));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 990, 330));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(11, 28, 48));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/filtrar.png"))); // NOI18N
        jLabel15.setText("FILTRAR");

        jLabel16.setForeground(new java.awt.Color(11, 28, 48));
        jLabel16.setText("Desde");

        jLabel17.setForeground(new java.awt.Color(11, 28, 48));
        jLabel17.setText("Hasta");

        jLabel18.setForeground(new java.awt.Color(11, 28, 48));
        jLabel18.setText("Usuario");

        dateFiltroDesde.setBackground(new java.awt.Color(255, 255, 255));
        dateFiltroDesde.setPreferredSize(new java.awt.Dimension(85, 25));
        dateFiltroDesde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateFiltroDesdeMouseEntered(evt);
            }
        });

        dateFiltroHasta.setPreferredSize(new java.awt.Dimension(85, 25));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 74, 198));
        jLabel9.setText("LIMPIAR FILTRO");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        cbUser.setModel(new javax.swing.DefaultComboBoxModel<ComboItem>());
        cbUser.setPreferredSize(new java.awt.Dimension(110, 25));
        cbUser.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cbUserInputMethodTextChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFiltroDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addComponent(dateFiltroHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFiltroHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFiltroDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 440, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        lblRegistro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(11, 28, 48));
        lblRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/agregar.png"))); // NOI18N
        lblRegistro.setText("REGISTRAR NUEVO GASTO");

        jLabel3.setForeground(new java.awt.Color(11, 28, 48));
        jLabel3.setText("Monto");

        txtMonto.setBackground(new java.awt.Color(255, 255, 255));
        txtMonto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMonto.setMinimumSize(new java.awt.Dimension(15, 25));
        txtMonto.setPreferredSize(new java.awt.Dimension(15, 25));
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(11, 28, 48));
        jLabel4.setText("Fecha");

        dateFechaNuevo.setBackground(new java.awt.Color(255, 255, 255));
        dateFechaNuevo.setForeground(new java.awt.Color(51, 51, 51));
        dateFechaNuevo.setMinimumSize(new java.awt.Dimension(33, 25));
        dateFechaNuevo.setPreferredSize(new java.awt.Dimension(85, 25));

        jLabel5.setForeground(new java.awt.Color(11, 28, 48));
        jLabel5.setText("Categoria");

        jLabel6.setForeground(new java.awt.Color(11, 28, 48));
        jLabel6.setText("Tipo");

        jLabel7.setForeground(new java.awt.Color(11, 28, 48));
        jLabel7.setText("Descripción");

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setPreferredSize(new java.awt.Dimension(15, 25));

        btnAgregarGasto.setBackground(new java.awt.Color(37, 99, 235));
        btnAgregarGasto.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        btnAgregarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarGasto.setText("Agregar Transacción");
        btnAgregarGasto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarGasto.setPreferredSize(new java.awt.Dimension(131, 25));
        btnAgregarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarGastoActionPerformed(evt);
            }
        });

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<ComboItem>());
        cbCategoria.setPreferredSize(new java.awt.Dimension(110, 25));

        cbTipoGastos.setModel(new javax.swing.DefaultComboBoxModel<ComboItem>());
        cbTipoGastos.setPreferredSize(new java.awt.Dimension(110, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(dateFechaNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addContainerGap(102, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTipoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblRegistro)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFechaNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 160));

        jPanelTotalGastos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(11, 28, 48));
        jLabel20.setText("TOTAL EXPENSES");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(11, 28, 48));
        jLabel21.setText("$ 14,240.50");

        jPanel6.setBackground(new java.awt.Color(255, 218, 214));
        jPanel6.setName(""); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/chat-flecha-abajo.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setRequestFocusEnabled(false);
        jPanel6.add(jLabel8, "card2");

        javax.swing.GroupLayout jPanelTotalGastosLayout = new javax.swing.GroupLayout(jPanelTotalGastos);
        jPanelTotalGastos.setLayout(jPanelTotalGastosLayout);
        jPanelTotalGastosLayout.setHorizontalGroup(
            jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanelTotalGastosLayout.setVerticalGroup(
            jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelTotalGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTotalGastosLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 240, 80));

        jPanelPromedioMensual.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPromedioMensual.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(11, 28, 48));
        jLabel24.setText("MONTHLY AVERAGE");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(11, 28, 48));
        jLabel25.setText("$ 4,820.00");

        jPanel12.setBackground(new java.awt.Color(211, 228, 254));
        jPanel12.setName(""); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel12.setLayout(new java.awt.CardLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/estadisticas.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setRequestFocusEnabled(false);
        jPanel12.add(jLabel10, "card2");

        javax.swing.GroupLayout jPanelPromedioMensualLayout = new javax.swing.GroupLayout(jPanelPromedioMensual);
        jPanelPromedioMensual.setLayout(jPanelPromedioMensualLayout);
        jPanelPromedioMensualLayout.setHorizontalGroup(
            jPanelPromedioMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPromedioMensualLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPromedioMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanelPromedioMensualLayout.setVerticalGroup(
            jPanelPromedioMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPromedioMensualLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelPromedioMensualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPromedioMensualLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelPromedioMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 240, 80));

        jPanelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(11, 28, 48));
        jLabel26.setText("STATUS");

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 108, 73));
        jLabel27.setText("DENTRO DEL PRESUPUESTO");

        jPanel14.setBackground(new java.awt.Color(108, 248, 187));
        jPanel14.setName(""); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel14.setLayout(new java.awt.CardLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setRequestFocusEnabled(false);
        jPanel14.add(jLabel11, "card2");

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatusLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, 240, 80));

        jPanelPeriodoSiguiente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPeriodoSiguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(11, 28, 48));
        jLabel28.setText("NEXT RECURRING");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(11, 28, 48));
        jLabel29.setText("Oct 28, 2023");

        jPanel16.setBackground(new java.awt.Color(255, 219, 205));
        jPanel16.setName(""); // NOI18N
        jPanel16.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel16.setLayout(new java.awt.CardLayout());

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/brown/calendario.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setRequestFocusEnabled(false);
        jPanel16.add(jLabel12, "card2");

        javax.swing.GroupLayout jPanelPeriodoSiguienteLayout = new javax.swing.GroupLayout(jPanelPeriodoSiguiente);
        jPanelPeriodoSiguiente.setLayout(jPanelPeriodoSiguienteLayout);
        jPanelPeriodoSiguienteLayout.setHorizontalGroup(
            jPanelPeriodoSiguienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeriodoSiguienteLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPeriodoSiguienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanelPeriodoSiguienteLayout.setVerticalGroup(
            jPanelPeriodoSiguienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPeriodoSiguienteLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelPeriodoSiguienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPeriodoSiguienteLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelPeriodoSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 240, 80));

        Body.addTab("Gastos", jPanel1);

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1010, 640));
        Body.getAccessibleContext().setAccessibleName("gastos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    public void limpiarFormularioGasto() {
        txtMonto.setText("");
        dateFechaNuevo.setDate(new Date());
        cbCategoria.setSelectedIndex(0);
        cbTipoGastos.setSelectedIndex(0);
        txtDescripcion.setText("");
    }
    
    private void guardarGasto() {
        controller.guardar();

        JOptionPane.showMessageDialog(
            this,
            "Gasto registrado correctamente"
        );
    }
    
    private void actualizarGasto() {
        controller.actualizar();
        idGastoSeleccionado = 0;
        lblRegistro.setText("REGISTRAR NUEVO GASTO");
        btnAgregarGasto.setText("Agregar Transacción");
    }

    private void dateFiltroDesdeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFiltroDesdeMouseEntered
        // TODO add your handling code here:
                

    }//GEN-LAST:event_dateFiltroDesdeMouseEntered

    private void cbUserInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbUserInputMethodTextChanged
        
    }//GEN-LAST:event_cbUserInputMethodTextChanged

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Calendar cal = Calendar.getInstance();
        dateFiltroHasta.setDate(cal.getTime());

        cal.set(Calendar.DAY_OF_MONTH, 1);
        dateFiltroDesde.setDate(cal.getTime());

        cbUser.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        controller.obtenerGastoSeleccionado();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblGastos.getSelectedRow();

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
            "¿Deseas eliminar el gasto seleccionado?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            controller.eliminarGastoSeleccionado();
            controller.listarGastos();
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
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una fecha",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (categoria.getValue() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una categoría",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (tipo.getValue() == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una tipo Gasto",
                "Campo requerido",
                JOptionPane.WARNING_MESSAGE
            );
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
        limpiarFormularioGasto();
    }//GEN-LAST:event_btnAgregarGastoActionPerformed

    private void pnlPresupuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPresupuestoMouseClicked
        PresupuestoView viewPresupuesto = new PresupuestoView();
        viewPresupuesto.setVisible(true);
        dispose();
    }//GEN-LAST:event_pnlPresupuestoMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SystemView().setVisible(true);
            }
        });
        
    }
    
    public void limpiarCampos() {
        txtMonto.setText("");
        txtDescripcion.setText("");
        dateFechaNuevo.setDate(new Date());
        
        txtDescripcion.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel Sidebar;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCategories;
    private javax.swing.JLabel jLabelEmployes;
    private javax.swing.JLabel jLabelProducts;
    private javax.swing.JLabel jLabelPurchases;
    private javax.swing.JLabel jLabelSupplimers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCategories;
    private javax.swing.JPanel jPanelEmployes;
    private javax.swing.JPanel jPanelPeriodoSiguiente;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JPanel jPanelPromedioMensual;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JPanel jPanelSupplimers;
    private javax.swing.JPanel jPanelTotalGastos;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel menuPresupuesto;
    private javax.swing.JPanel pnlGasto;
    private javax.swing.JPanel pnlPresupuesto;
    public javax.swing.JTable tblGastos;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
