package view;

import controller.PresupuestoController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.ComboItem;
import view.components.ModernTableHelper;
import view.components.SidebarPanel;
import view.components.HeaderBar;
import view.components.ThemeColor;

public class PresupuestoView extends javax.swing.JFrame {

    private PresupuestoController controller;
    public int idPresupuestoSeleccionado = 0;

    public PresupuestoView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Control de Presupuesto");
        setLocationRelativeTo(null);

        controller = new PresupuestoController(this);

        setupEventListeners();
    }

    private void initCustomStyles() {
        ModernTableHelper.styleTable(tblPresupuestos);
        ModernTableHelper.styleScrollPane(jScrollPane1);
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);

        setupTableRenderers();

        headerBar.getBtnAccion().addActionListener(evt -> {
            limpiarFormularioGasto();
            txtPresupuestoMensual.requestFocus();
        });
    }

    private void setupTableRenderers() {
        tblPresupuestos.setRowHeight(38);

        // Col 0: ID (oculto)
        tblPresupuestos.getColumnModel().getColumn(0).setMinWidth(0);
        tblPresupuestos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblPresupuestos.getColumnModel().getColumn(0).setMaxWidth(0);

        // Col 2: Monto Asignado
        tblPresupuestos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 12));
                if (value instanceof Number) {
                    lbl.setText(String.format("S/. %,.2f", ((Number) value).doubleValue()));
                } else if (value != null) {
                    try {
                        lbl.setText(String.format("S/. %,.2f", Double.parseDouble(value.toString())));
                    } catch (Exception e) {}
                }
                return lbl;
            }
        });

        // Col 3: Monto Gastado
        tblPresupuestos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 12));
                if (value instanceof Number) {
                    lbl.setText(String.format("S/. %,.2f", ((Number) value).doubleValue()));
                } else if (value != null) {
                    try {
                        lbl.setText(String.format("S/. %,.2f", Double.parseDouble(value.toString())));
                    } catch (Exception e) {}
                }
                return lbl;
            }
        });

        // Col 4: Estado (Badges semafóricos Finandes)
        tblPresupuestos.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String text = (value != null) ? value.toString().trim() : "";

                if (text.equalsIgnoreCase("EXCEDIDO")) {
                    label.setText("<html><body style='padding: 2px 10px; background-color: #fee2e2; color: #ef4444; border-radius: 10px; font-weight: bold;'>EXCEDIDO</body></html>");
                } else if (text.equalsIgnoreCase("ADVERTENCIA") || text.equalsIgnoreCase("ALERTA")) {
                    label.setText("<html><body style='padding: 2px 10px; background-color: #fef3c7; color: #d97706; border-radius: 10px; font-weight: bold;'>ALERTA</body></html>");
                } else if (text.equalsIgnoreCase("BAJO") || text.equalsIgnoreCase("BAJO LÍMITE") || text.equalsIgnoreCase("BAJO LIMITE")) {
                    label.setText("<html><body style='padding: 2px 10px; background-color: #e6fffa; color: #0d9488; border-radius: 10px; font-weight: bold;'>BAJO LÍMITE</body></html>");
                } else {
                    label.setText("<html><body style='padding: 2px 10px; background-color: #d1fae5; color: #10b981; border-radius: 10px; font-weight: bold;'>EN REGLA</body></html>");
                }
                label.setOpaque(true);
                label.setBackground(isSelected ? ThemeColor.TABLA_ROW_SELECTED : ThemeColor.FONDO_CARD);
                return label;
            }
        });

        // Col 5: Barra de Progreso
        tblPresupuestos.getColumnModel().getColumn(5).setCellRenderer(new ProgressBarRenderer());
    }

    private void setupEventListeners() {
        tblPresupuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tblPresupuestos.getSelectedRow();
                if (fila != -1) {
                    try {
                        idPresupuestoSeleccionado = Integer.parseInt(tblPresupuestos.getValueAt(fila, 0).toString());
                        String catNombre = tblPresupuestos.getValueAt(fila, 1).toString();
                        Object valAsig = tblPresupuestos.getValueAt(fila, 2);
                        txtPresupuestoMensual.setText(valAsig != null ? valAsig.toString() : "");

                        lblRegistro.setText("EDITAR PRESUPUESTO");
                        btnAgregarEditarPresupuesto.setText("Actualizar Presupuesto");

                        for (int i = 0; i < cbCategoria.getItemCount(); i++) {
                            if (cbCategoria.getItemAt(i).toString().equalsIgnoreCase(catNombre)) {
                                cbCategoria.setSelectedIndex(i);
                                break;
                            }
                        }
                    } catch (Exception e) {}
                }
            }
        });
    }

    public void limpiarFormularioGasto() {
        txtPresupuestoMensual.setText("");
        txtUmbral.setText("");
        if (cbCategoria.getItemCount() > 0) cbCategoria.setSelectedIndex(0);
        if (cbPeriodo.getItemCount() > 0) cbPeriodo.setSelectedIndex(0);
        idPresupuestoSeleccionado = 0;
        lblRegistro.setText("EDITAR PRESUPUESTO");
        btnAgregarEditarPresupuesto.setText("Actualizar Presupuesto");
    }

    private void guardarGasto() {
        controller.guardarPresupuesto();
    }

    private void actualizarGasto() {
        controller.actualizarPresupuesto();
        idPresupuestoSeleccionado = 0;
        lblRegistro.setText("EDITAR PRESUPUESTO");
        btnAgregarEditarPresupuesto.setText("Actualizar Presupuesto");
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
        btnExportarExcel = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        jPanelTotalGastos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanelTotalGastos1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanelTotalGastos2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPresupuestos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblRegistro = new javax.swing.JLabel();
        lblBadgeForm = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<ComboItem>();
        jLabel7 = new javax.swing.JLabel();
        txtPresupuestoMensual = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtUmbral = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        cbPeriodo = new javax.swing.JComboBox<ComboItem>();
        btnAgregarEditarPresupuesto = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanelPeriodoSiguiente1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanelStatus = new javax.swing.JPanel();
        lblRegistro2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Control de Presupuesto");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Presupuesto");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Nuevo Presupuesto");
        headerBar.setModuloActual("Control de Presupuesto");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setPreferredSize(new java.awt.Dimension(1020, 715));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Control de Presupuesto");
        pnlMainContent.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 14, 280, 26));

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Monitoreo y asignación de límites financieros por categoría.");
        pnlMainContent.add(lblSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 40, 420, 18));

        btnExportarExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarExcel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportarExcel.setForeground(new java.awt.Color(71, 85, 105));
        btnExportarExcel.setText("Exportar Informe");
        btnExportarExcel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportarExcel.setFocusPainted(false);
        pnlMainContent.add(btnExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 16, 135, 32));

        btnFiltrar.setBackground(new java.awt.Color(0, 159, 227));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtros");
        btnFiltrar.setBorderPainted(false);
        btnFiltrar.setFocusPainted(false);
        pnlMainContent.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 16, 130, 32));

        jPanelTotalGastos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelTotalGastos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(239, 246, 254));
        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/blue/presupuesto.png"))); // NOI18N
        jPanel6.add(jLabel8, "card2");

        jPanelTotalGastos.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 22, 50, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(100, 116, 139));
        jLabel20.setText("PRESUPUESTO TOTAL");
        jPanelTotalGastos.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 18, 225, 16));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(15, 23, 42));
        jLabel21.setText("S/. 0.00");
        jPanelTotalGastos.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 38, 225, 26));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(4, 120, 87));
        jLabel31.setText("0 presupuestos activos");
        jPanelTotalGastos.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 66, 225, 16));

        pnlMainContent.add(jPanelTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 66, 315, 95));

        jPanelTotalGastos1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelTotalGastos1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(254, 226, 226));
        jPanel12.setLayout(new java.awt.CardLayout());

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/red/chat-flecha-abajo.png"))); // NOI18N
        jPanel12.add(jLabel19, "card2");

        jPanelTotalGastos1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 22, 50, 50));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(100, 116, 139));
        jLabel22.setText("GASTADO A LA FECHA");
        jPanelTotalGastos1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 18, 225, 16));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(15, 23, 42));
        jLabel23.setText("S/. 0.00");
        jPanelTotalGastos1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 38, 225, 26));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(239, 68, 68));
        jLabel32.setText("0.0% de total usado");
        jPanelTotalGastos1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 66, 225, 16));

        pnlMainContent.add(jPanelTotalGastos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 66, 315, 95));

        jPanelTotalGastos2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTotalGastos2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelTotalGastos2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(209, 250, 229));
        jPanel14.setLayout(new java.awt.CardLayout());

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon/green/controlar.png"))); // NOI18N
        jPanel14.add(jLabel35, "card2");

        jPanelTotalGastos2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 22, 50, 50));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(100, 116, 139));
        jLabel33.setText("RESTANTE");
        jPanelTotalGastos2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 18, 225, 16));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 108, 73));
        jLabel34.setText("S/. 0.00");
        jPanelTotalGastos2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 38, 225, 26));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(100, 116, 139));
        jLabel36.setText("Quedan días en el período");
        jPanelTotalGastos2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 66, 225, 16));

        pnlMainContent.add(jPanelTotalGastos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 66, 315, 95));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(15, 23, 42));
        jLabel14.setText("RENDIMIENTO POR CATEGORÍAS");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 250, 22));

        jLabel1.setBackground(new java.awt.Color(224, 243, 254));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(2, 132, 199));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SEPTIEMBRE 2023");
        jLabel1.setOpaque(true);
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 15, 130, 20));

        jScrollPane1.setBorder(null);

        tblPresupuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA", "ASIGNADO", "GASTADO", "ESTADO", "CONSUMO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class
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
        tblPresupuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPresupuestos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPresupuestos);
        if (tblPresupuestos.getColumnModel().getColumnCount() > 0) {
            tblPresupuestos.getColumnModel().getColumn(0).setMinWidth(0);
            tblPresupuestos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPresupuestos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPresupuestos.getColumnModel().getColumn(1).setPreferredWidth(140);
            tblPresupuestos.getColumnModel().getColumn(2).setResizable(false);
            tblPresupuestos.getColumnModel().getColumn(2).setPreferredWidth(95);
            tblPresupuestos.getColumnModel().getColumn(3).setResizable(false);
            tblPresupuestos.getColumnModel().getColumn(3).setPreferredWidth(95);
            tblPresupuestos.getColumnModel().getColumn(4).setResizable(false);
            tblPresupuestos.getColumnModel().getColumn(4).setPreferredWidth(110);
            tblPresupuestos.getColumnModel().getColumn(5).setPreferredWidth(165);
        }

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 46, 606, 455));

        pnlMainContent.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 173, 642, 520));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistro.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(15, 23, 42));
        lblRegistro.setText("EDITAR PRESUPUESTO");
        jPanel2.add(lblRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 190, 20));

        lblBadgeForm.setBackground(new java.awt.Color(239, 246, 255));
        lblBadgeForm.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblBadgeForm.setForeground(new java.awt.Color(2, 132, 199));
        lblBadgeForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeForm.setText("CATEGORÍA");
        lblBadgeForm.setOpaque(true);
        jPanel2.add(lblBadgeForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 13, 85, 18));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 116, 139));
        jLabel3.setText("Categoría *");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 280, 14));

        cbCategoria.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel2.add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, 283, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(100, 116, 139));
        jLabel7.setText("Límite Mensual (S/.) *");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 84, 135, 14));

        txtPresupuestoMensual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtPresupuestoMensual.setForeground(new java.awt.Color(15, 23, 42));
        txtPresupuestoMensual.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        txtPresupuestoMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresupuestoMensualKeyTyped(evt);
            }
        });
        jPanel2.add(txtPresupuestoMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 100, 135, 30));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(100, 116, 139));
        jLabel37.setText("Umbral Alerta (%) *");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 84, 135, 14));

        txtUmbral.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtUmbral.setForeground(new java.awt.Color(15, 23, 42));
        txtUmbral.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        jPanel2.add(txtUmbral, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 139, 30));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(100, 116, 139));
        jLabel38.setText("Período *");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 134, 280, 14));

        cbPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jPanel2.add(cbPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 150, 283, 30));

        btnAgregarEditarPresupuesto.setBackground(new java.awt.Color(0, 159, 227));
        btnAgregarEditarPresupuesto.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnAgregarEditarPresupuesto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarEditarPresupuesto.setText("Actualizar Presupuesto");
        btnAgregarEditarPresupuesto.setBorderPainted(false);
        btnAgregarEditarPresupuesto.setFocusPainted(false);
        btnAgregarEditarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEditarPresupuestoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarEditarPresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 186, 230, 30));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(239, 68, 68));
        btnEliminar.setText("×");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 186, 44, 30));

        pnlMainContent.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 173, 315, 225));

        jPanelPeriodoSiguiente1.setBackground(new java.awt.Color(15, 23, 42));
        jPanelPeriodoSiguiente1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 41, 59), 1, true));
        jPanelPeriodoSiguiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(56, 189, 248));
        jLabel39.setText("RECOMENDACIÓN DE IA");
        jPanelPeriodoSiguiente1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 280, 16));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(148, 163, 184));
        jLabel41.setText("<html>Ahorra hasta S/. 150 este mes optimizando tus suscripciones de comida.</html>");
        jPanelPeriodoSiguiente1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, 280, 36));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 159, 227));
        jLabel42.setText("Ver detalles →");
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelPeriodoSiguiente1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 150, 18));

        pnlMainContent.add(jPanelPeriodoSiguiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 408, 315, 100));

        jPanelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        jPanelStatus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistro2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRegistro2.setForeground(new java.awt.Color(15, 23, 42));
        lblRegistro2.setText("ACTIVIDAD RECIENTE DEL PRESUPUESTO");
        jPanelStatus.add(lblRegistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 280, 18));

        pnlMainContent.add(jPanelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 518, 315, 175));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPresupuestoMensualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoMensualKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txtPresupuestoMensualKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblPresupuestos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Aún no ha seleccionado ninguna fila", "Aviso", JOptionPane.WARNING_MESSAGE);
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

        if (categoria == null || categoria.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría", "Campo requerido", JOptionPane.WARNING_MESSAGE);
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

        if (tipo == null || tipo.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de período", "Campo requerido", JOptionPane.WARNING_MESSAGE);
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

    public SidebarPanel getSidebarPanel() {
        return sidebarPanel;
    }

    public HeaderBar getHeaderBar() {
        return headerBar;
    }

    public static class ProgressBarRenderer extends JProgressBar implements javax.swing.table.TableCellRenderer {
        public ProgressBarRenderer() {
            super(0, 100);
            setStringPainted(true);
            setBorderPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 11));
            setBackground(new Color(241, 245, 249));
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

            // Semáforo Finandes:
            // Excedido (> 100%) = Rojo #EF4444
            // Alerta (70% - 100%) = Ámbar #F59E0B
            // En orden (< 70%) = Verde #10B981
            if (pct > 100.0) {
                setForeground(new Color(239, 68, 68));
            } else if (pct >= 70.0) {
                setForeground(new Color(245, 158, 11));
            } else {
                setForeground(new Color(16, 185, 129));
            }

            return this;
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PresupuestoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarEditarPresupuesto;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarExcel;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JComboBox<ComboItem> cbCategoria;
    public javax.swing.JComboBox<ComboItem> cbPeriodo;
    private view.components.HeaderBar headerBar;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanelPeriodoSiguiente1;
    public javax.swing.JPanel jPanelStatus;
    public javax.swing.JPanel jPanelTotalGastos;
    public javax.swing.JPanel jPanelTotalGastos1;
    public javax.swing.JPanel jPanelTotalGastos2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBadgeForm;
    public javax.swing.JLabel lblRegistro;
    public javax.swing.JLabel lblRegistro2;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JScrollPane scrollContent;
    private view.components.SidebarPanel sidebarPanel;
    public javax.swing.JTable tblPresupuestos;
    public javax.swing.JTextField txtPresupuestoMensual;
    public javax.swing.JTextField txtUmbral;
    // End of variables declaration//GEN-END:variables
}
