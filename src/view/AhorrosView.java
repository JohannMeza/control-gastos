package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.ComboItem;
import controller.AhorroController;
import view.components.ModernTableHelper;

/**
 * Vista de Gestión de Ahorros con soporte para NetBeans GUI Builder.
 */
public class AhorrosView extends javax.swing.JFrame {

    public AhorroController controller;
    public int idAhorroSeleccionado = 0;

    public AhorrosView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Gestión de Ahorros");
        setLocationRelativeTo(null);

        controller = new AhorroController(this);
    }

    private void initCustomStyles() {
        ModernTableHelper.styleTable(tblAhorros);
        ModernTableHelper.styleScrollPane(jScrollPane1);
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);

        setupTableRenderers();
        setupFormDate();

        headerBar.getBtnAccion().addActionListener(evt -> {
            limpiarFormulario();
            txtMonto.requestFocus();
        });

        lblConsejoIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblFondoEmergenciaIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
        lblVacacionesIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
        lblAutoIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
    }

    private void setupFormDate() {
        if (dateFecha != null) {
            dateFecha.setDateFormatString("dd/MM/yyyy");
            dateFecha.setDate(new Date());
        }
    }

    private void setupTableRenderers() {
        tblAhorros.setRowHeight(38);

        // Col 0: Fecha
        tblAhorros.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblAhorros.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                l.setForeground(new Color(51, 65, 85));
                return l;
            }
        });

        // Col 1: Meta / Fondo con badge temático
        tblAhorros.getColumnModel().getColumn(1).setPreferredWidth(160);
        tblAhorros.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                String text = (value != null) ? value.toString() : "";

                if (text.equalsIgnoreCase("Fondo Emergencia")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #d1fae5; color: #065f46; font-weight: bold; font-family: Segoe UI; font-size: 10px;'>" + text + "</body></html>");
                } else if (text.equalsIgnoreCase("Vacaciones")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #dbeafe; color: #1e40af; font-weight: bold; font-family: Segoe UI; font-size: 10px;'>" + text + "</body></html>");
                } else if (text.equalsIgnoreCase("Auto Nuevo")) {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #ffedd5; color: #9a3412; font-weight: bold; font-family: Segoe UI; font-size: 10px;'>" + text + "</body></html>");
                } else {
                    label.setText("<html><body style='padding: 3px 10px; background-color: #f1f5f9; color: #334155; font-weight: bold; font-family: Segoe UI; font-size: 10px;'>" + text + "</body></html>");
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

        // Col 2: Monto
        tblAhorros.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblAhorros.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.RIGHT);
                l.setFont(new Font("Segoe UI", Font.BOLD, 12));
                l.setForeground(new Color(15, 23, 42));
                return l;
            }
        });

        // Col 3: Usuario
        tblAhorros.getColumnModel().getColumn(3).setPreferredWidth(130);
        tblAhorros.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.LEFT);
                l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                try {
                    l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png")));
                    l.setIconTextGap(8);
                } catch (Exception ignored) {
                }
                return l;
            }
        });

        // Col 4: Acción / ID (oculto)
        tblAhorros.getColumnModel().getColumn(4).setMinWidth(0);
        tblAhorros.getColumnModel().getColumn(4).setPreferredWidth(0);
        tblAhorros.getColumnModel().getColumn(4).setMaxWidth(0);
    }

    public void limpiarFormulario() {
        txtMonto.setText("");
        txtDescripcion.setText("");
        if (dateFecha != null) {
            dateFecha.setDate(new Date());
        }
        if (cbMetaFondo.getItemCount() > 0) {
            cbMetaFondo.setSelectedIndex(0);
        }
        txtMonto.setText("");
        btnRegistrarAhorro.setText("Registrar Ahorro");
        idAhorroSeleccionado = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        pnlCardTotalAhorrado = new javax.swing.JPanel();
        lblCardTotalAhorradoTitle = new javax.swing.JLabel();
        lblBadgeActivo = new javax.swing.JLabel();
        lblCardTotalAhorradoValue = new javax.swing.JLabel();
        lblCardTotalAhorradoChange = new javax.swing.JLabel();
        pnlCardMetaMensual = new javax.swing.JPanel();
        lblCardMetaMensualTitle = new javax.swing.JLabel();
        lblCardMetaMensualValue = new javax.swing.JLabel();
        pbMetaMensual = new javax.swing.JProgressBar();
        lblCardMetaMensualDesc = new javax.swing.JLabel();
        pnlCardEficiencia = new javax.swing.JPanel();
        lblEficienciaTitle = new javax.swing.JLabel();
        lblLevelUp = new javax.swing.JLabel();
        lblEficienciaValue = new javax.swing.JLabel();
        lblMetasGlobalesTitle = new javax.swing.JLabel();
        pbMetasGlobales = new javax.swing.JProgressBar();
        lblFondoEmergenciaTitle = new javax.swing.JLabel();
        pbFondoEmergencia = new javax.swing.JProgressBar();
        pnlRegistrarAhorro = new javax.swing.JPanel();
        lblRegistrarAhorroTitle = new javax.swing.JLabel();
        lblBadgeForm = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        lblMetaFondo = new javax.swing.JLabel();
        cbMetaFondo = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnRegistrarAhorro = new javax.swing.JButton();
        pnlConsejo = new javax.swing.JPanel();
        lblConsejoIcon = new javax.swing.JLabel();
        lblConsejoTitle = new javax.swing.JLabel();
        lblConsejoText = new javax.swing.JLabel();
        pnlMetasAhorro = new javax.swing.JPanel();
        lblMetasAhorroTitle = new javax.swing.JLabel();
        lblVerTodasMetas = new javax.swing.JLabel();
        pnlMetaFondoEmergencia = new javax.swing.JPanel();
        lblFondoEmergenciaIcon = new javax.swing.JLabel();
        lblFondoEmergenciaName = new javax.swing.JLabel();
        lblFondoEmergenciaPercent = new javax.swing.JLabel();
        lblFondoEmergenciaProgressText = new javax.swing.JLabel();
        pbFondoEmergenciaMeta = new javax.swing.JProgressBar();
        lblFondoEmergenciaEst = new javax.swing.JLabel();
        pnlMetaVacaciones = new javax.swing.JPanel();
        lblVacacionesIcon = new javax.swing.JLabel();
        lblVacacionesName = new javax.swing.JLabel();
        lblVacacionesPercent = new javax.swing.JLabel();
        lblVacacionesProgressText = new javax.swing.JLabel();
        pbVacacionesMeta = new javax.swing.JProgressBar();
        lblVacacionesEst = new javax.swing.JLabel();
        pnlMetaAuto = new javax.swing.JPanel();
        lblAutoIcon = new javax.swing.JLabel();
        lblAutoName = new javax.swing.JLabel();
        lblAutoPercent = new javax.swing.JLabel();
        lblAutoProgressText = new javax.swing.JLabel();
        pbAutoMeta = new javax.swing.JProgressBar();
        lblAutoEst = new javax.swing.JLabel();
        pnlHistorialAhorros = new javax.swing.JPanel();
        lblHistorialAhorrosTitle = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Gestión de Ahorros");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Ahorros");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Transacción");
        headerBar.setModuloActual("Gestión de Ahorros");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCardTotalAhorrado.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardTotalAhorrado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardTotalAhorrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardTotalAhorradoTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardTotalAhorradoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardTotalAhorradoTitle.setText("TOTAL AHORRADO");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 160, 14));

        lblBadgeActivo.setBackground(new java.awt.Color(209, 250, 229));
        lblBadgeActivo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBadgeActivo.setForeground(new java.awt.Color(16, 185, 129));
        lblBadgeActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeActivo.setText("Activo");
        lblBadgeActivo.setOpaque(true);
        pnlCardTotalAhorrado.add(lblBadgeActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 10, 54, 18));

        lblCardTotalAhorradoValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCardTotalAhorradoValue.setForeground(new java.awt.Color(15, 23, 42));
        lblCardTotalAhorradoValue.setText("S/. 12,450.00");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 32));

        lblCardTotalAhorradoChange.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCardTotalAhorradoChange.setForeground(new java.awt.Color(16, 185, 129));
        lblCardTotalAhorradoChange.setText("+100.0% desde el mes pasado");
        pnlCardTotalAhorrado.add(lblCardTotalAhorradoChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 280, 16));

        pnlMainContent.add(pnlCardTotalAhorrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 315, 115));

        pnlCardMetaMensual.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardMetaMensual.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardMetaMensual.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardMetaMensualTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardMetaMensualTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardMetaMensualTitle.setText("META MENSUAL");
        pnlCardMetaMensual.add(lblCardMetaMensualTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 160, 14));

        lblCardMetaMensualValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCardMetaMensualValue.setForeground(new java.awt.Color(15, 23, 42));
        lblCardMetaMensualValue.setText("S/. 1,500.00");
        pnlCardMetaMensual.add(lblCardMetaMensualValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 32));

        pbMetaMensual.setForeground(new java.awt.Color(0, 159, 227));
        pbMetaMensual.setValue(75);
        pnlCardMetaMensual.add(pbMetaMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 280, 8));

        lblCardMetaMensualDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCardMetaMensualDesc.setForeground(new java.awt.Color(100, 116, 139));
        lblCardMetaMensualDesc.setText("S/. 1,125.00 ahorrados hoy");
        pnlCardMetaMensual.add(lblCardMetaMensualDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 84, 280, 16));

        pnlMainContent.add(pnlCardMetaMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 15, 315, 115));

        pnlCardEficiencia.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardEficiencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardEficiencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEficienciaTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblEficienciaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblEficienciaTitle.setText("EFICIENCIA DE AHORRO");
        pnlCardEficiencia.add(lblEficienciaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 160, 14));

        lblLevelUp.setBackground(new java.awt.Color(230, 255, 250));
        lblLevelUp.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblLevelUp.setForeground(new java.awt.Color(13, 148, 136));
        lblLevelUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLevelUp.setText("NIVEL SUPERIOR");
        lblLevelUp.setOpaque(true);
        pnlCardEficiencia.add(lblLevelUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 10, 94, 18));

        lblEficienciaValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblEficienciaValue.setForeground(new java.awt.Color(15, 23, 42));
        lblEficienciaValue.setText("82%");
        pnlCardEficiencia.add(lblEficienciaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 70, 32));

        lblMetasGlobalesTitle.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblMetasGlobalesTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblMetasGlobalesTitle.setText("Metas Globales");
        pnlCardEficiencia.add(lblMetasGlobalesTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 32, 90, 14));

        pbMetasGlobales.setForeground(new java.awt.Color(16, 185, 129));
        pbMetasGlobales.setValue(82);
        pnlCardEficiencia.add(pbMetasGlobales, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 48, 215, 6));

        lblFondoEmergenciaTitle.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblFondoEmergenciaTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblFondoEmergenciaTitle.setText("Fondo Emergencia");
        pnlCardEficiencia.add(lblFondoEmergenciaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 62, 100, 14));

        pbFondoEmergencia.setForeground(new java.awt.Color(0, 159, 227));
        pbFondoEmergencia.setValue(95);
        pnlCardEficiencia.add(pbFondoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 78, 215, 6));

        pnlMainContent.add(pnlCardEficiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 15, 326, 115));

        pnlRegistrarAhorro.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistrarAhorro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlRegistrarAhorro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistrarAhorroTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRegistrarAhorroTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblRegistrarAhorroTitle.setText("REGISTRAR AHORRO");
        pnlRegistrarAhorro.add(lblRegistrarAhorroTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 180, 16));

        lblBadgeForm.setBackground(new java.awt.Color(224, 242, 254));
        lblBadgeForm.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBadgeForm.setForeground(new java.awt.Color(3, 105, 161));
        lblBadgeForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeForm.setText("NUEVO");
        lblBadgeForm.setOpaque(true);
        pnlRegistrarAhorro.add(lblBadgeForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 10, 54, 18));

        lblMonto.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(100, 116, 139));
        lblMonto.setText("Monto (S/.) *");
        pnlRegistrarAhorro.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 280, 14));

        txtMonto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMonto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
                if (c == '.' && txtMonto.getText().contains(".")) {
                    evt.consume();
                }
            }
        });
        pnlRegistrarAhorro.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 54, 283, 30));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(100, 116, 139));
        lblFecha.setText("Fecha *");
        pnlRegistrarAhorro.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 90, 280, 14));
        pnlRegistrarAhorro.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 106, 283, 30));

        lblMetaFondo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMetaFondo.setForeground(new java.awt.Color(100, 116, 139));
        lblMetaFondo.setText("Meta / Fondo *");
        pnlRegistrarAhorro.add(lblMetaFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 142, 280, 14));

        cbMetaFondo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        pnlRegistrarAhorro.add(cbMetaFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 158, 283, 30));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(100, 116, 139));
        lblDescripcion.setText("Descripción");
        pnlRegistrarAhorro.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 194, 280, 14));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(3);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescripcion);

        pnlRegistrarAhorro.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 210, 283, 58));

        btnRegistrarAhorro.setBackground(new java.awt.Color(0, 159, 227));
        btnRegistrarAhorro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarAhorro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAhorro.setText("Registrar Ahorro");
        btnRegistrarAhorro.setBorder(null);
        btnRegistrarAhorro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarAhorro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAhorroActionPerformed(evt);
            }
        });
        pnlRegistrarAhorro.add(btnRegistrarAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 280, 283, 34));

        pnlMainContent.add(pnlRegistrarAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 145, 315, 335));

        pnlConsejo.setBackground(new java.awt.Color(66, 173, 77));
        pnlConsejo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(53, 147, 63), 1, true));
        pnlConsejo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsejoIcon.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        lblConsejoIcon.setForeground(new java.awt.Color(255, 255, 255));
        lblConsejoIcon.setText("💡");
        pnlConsejo.add(lblConsejoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 24, 24));

        lblConsejoTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblConsejoTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblConsejoTitle.setText("CONSEJO FINANCIERO");
        pnlConsejo.add(lblConsejoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 16, 250, 16));

        lblConsejoText.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblConsejoText.setForeground(new java.awt.Color(255, 255, 255));
        lblConsejoText.setText("<html>Aumentar tu ahorro un 5% adicional este mes te acercará a tu meta de Vacaciones 2 meses antes.</html>");
        pnlConsejo.add(lblConsejoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 42, 280, 55));

        pnlMainContent.add(pnlConsejo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 492, 315, 115));

        pnlMetasAhorro.setBackground(new java.awt.Color(255, 255, 255));
        pnlMetasAhorro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlMetasAhorro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMetasAhorroTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblMetasAhorroTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblMetasAhorroTitle.setText("METAS DE AHORRO");
        pnlMetasAhorro.add(lblMetasAhorroTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 200, 18));

        lblVerTodasMetas.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVerTodasMetas.setForeground(new java.awt.Color(0, 159, 227));
        lblVerTodasMetas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVerTodasMetas.setText("+ Nueva Meta");
        lblVerTodasMetas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMetasAhorro.add(lblVerTodasMetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 12, 105, 18));

        pnlMetaFondoEmergencia.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaFondoEmergencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlMetaFondoEmergencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFondoEmergenciaIcon.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        lblFondoEmergenciaIcon.setText("🛡️");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 20, 20));

        lblFondoEmergenciaName.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFondoEmergenciaName.setForeground(new java.awt.Color(15, 23, 42));
        lblFondoEmergenciaName.setText("Fondo Emergencia");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaName, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 8, 115, 18));

        lblFondoEmergenciaPercent.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFondoEmergenciaPercent.setForeground(new java.awt.Color(16, 185, 129));
        lblFondoEmergenciaPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFondoEmergenciaPercent.setText("95%");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 8, 40, 18));

        lblFondoEmergenciaProgressText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblFondoEmergenciaProgressText.setForeground(new java.awt.Color(100, 116, 139));
        lblFondoEmergenciaProgressText.setText("S/. 4,750 / S/. 5,000");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 14));

        pbFondoEmergenciaMeta.setForeground(new java.awt.Color(16, 185, 129));
        pbFondoEmergenciaMeta.setValue(95);
        pnlMetaFondoEmergencia.add(pbFondoEmergenciaMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 180, 6));

        lblFondoEmergenciaEst.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblFondoEmergenciaEst.setForeground(new java.awt.Color(148, 163, 184));
        lblFondoEmergenciaEst.setText("Est. Ago 2024");
        pnlMetaFondoEmergencia.add(lblFondoEmergenciaEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 14));

        pnlMetasAhorro.add(pnlMetaFondoEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 200, 85));

        pnlMetaVacaciones.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaVacaciones.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlMetaVacaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVacacionesIcon.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        lblVacacionesIcon.setText("✈️");
        pnlMetaVacaciones.add(lblVacacionesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 20, 20));

        lblVacacionesName.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVacacionesName.setForeground(new java.awt.Color(15, 23, 42));
        lblVacacionesName.setText("Vacaciones");
        pnlMetaVacaciones.add(lblVacacionesName, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 8, 115, 18));

        lblVacacionesPercent.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblVacacionesPercent.setForeground(new java.awt.Color(0, 159, 227));
        lblVacacionesPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVacacionesPercent.setText("45%");
        pnlMetaVacaciones.add(lblVacacionesPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 8, 40, 18));

        lblVacacionesProgressText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblVacacionesProgressText.setForeground(new java.awt.Color(100, 116, 139));
        lblVacacionesProgressText.setText("S/. 1,350 / S/. 3,000");
        pnlMetaVacaciones.add(lblVacacionesProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 14));

        pbVacacionesMeta.setForeground(new java.awt.Color(0, 159, 227));
        pbVacacionesMeta.setValue(45);
        pnlMetaVacaciones.add(pbVacacionesMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 180, 6));

        lblVacacionesEst.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblVacacionesEst.setForeground(new java.awt.Color(148, 163, 184));
        lblVacacionesEst.setText("Est. Dic 2024");
        pnlMetaVacaciones.add(lblVacacionesEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 14));

        pnlMetasAhorro.add(pnlMetaVacaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 38, 200, 85));

        pnlMetaAuto.setBackground(new java.awt.Color(248, 250, 252));
        pnlMetaAuto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlMetaAuto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAutoIcon.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        lblAutoIcon.setText("🚗");
        pnlMetaAuto.add(lblAutoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 20, 20));

        lblAutoName.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblAutoName.setForeground(new java.awt.Color(15, 23, 42));
        lblAutoName.setText("Auto Nuevo");
        pnlMetaAuto.add(lblAutoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 8, 115, 18));

        lblAutoPercent.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblAutoPercent.setForeground(new java.awt.Color(217, 119, 6));
        lblAutoPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAutoPercent.setText("15%");
        pnlMetaAuto.add(lblAutoPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 8, 40, 18));

        lblAutoProgressText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblAutoProgressText.setForeground(new java.awt.Color(100, 116, 139));
        lblAutoProgressText.setText("S/. 3,750 / S/. 25,000");
        pnlMetaAuto.add(lblAutoProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 14));

        pbAutoMeta.setForeground(new java.awt.Color(245, 158, 11));
        pbAutoMeta.setValue(15);
        pnlMetaAuto.add(pbAutoMeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 180, 6));

        lblAutoEst.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblAutoEst.setForeground(new java.awt.Color(148, 163, 184));
        lblAutoEst.setText("Est. Jun 2026");
        pnlMetaAuto.add(lblAutoEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 14));

        pnlMetasAhorro.add(pnlMetaAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 38, 200, 85));

        pnlMainContent.add(pnlMetasAhorro, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 145, 657, 135));

        pnlHistorialAhorros.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorialAhorros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHistorialAhorrosTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblHistorialAhorrosTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblHistorialAhorrosTitle.setText("HISTORIAL DE AHORROS");
        pnlHistorialAhorros.add(lblHistorialAhorrosTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 220, 18));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(239, 68, 68));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlHistorialAhorros.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 8, 85, 26));

        btnFiltros.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltros.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnFiltros.setForeground(new java.awt.Color(71, 85, 105));
        btnFiltros.setText("Filtros");
        btnFiltros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnFiltros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHistorialAhorros.add(btnFiltros, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 8, 85, 26));

        btnExportarCSV.setBackground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(71, 85, 105));
        btnExportarCSV.setText("Exportar CSV");
        btnExportarCSV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarCSVActionPerformed(evt);
            }
        });
        pnlHistorialAhorros.add(btnExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 8, 130, 26));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 232, 226)));

        tblAhorros.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblAhorros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
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
        tblAhorros.setGridColor(new java.awt.Color(226, 232, 240));
        tblAhorros.setRowHeight(38);
        tblAhorros.setSelectionBackground(new java.awt.Color(224, 240, 250));
        tblAhorros.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tblAhorros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAhorrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAhorros);

        pnlHistorialAhorros.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 42, 625, 230));

        lblMostrarTransacciones.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMostrarTransacciones.setForeground(new java.awt.Color(100, 116, 139));
        lblMostrarTransacciones.setText("Mostrando 0 de 0 transacciones");
        pnlHistorialAhorros.add(lblMostrarTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 280, 280, 24));

        btnAnterior.setBackground(new java.awt.Color(255, 255, 255));
        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(71, 85, 105));
        btnAnterior.setText("<");
        btnAnterior.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 30, 24));

        btnPag1.setBackground(new java.awt.Color(0, 159, 227));
        btnPag1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnPag1.setForeground(new java.awt.Color(255, 255, 255));
        btnPag1.setText("1");
        btnPag1.setBorder(null);
        pnlHistorialAhorros.add(btnPag1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 280, 30, 24));

        btnPag2.setBackground(new java.awt.Color(255, 255, 255));
        btnPag2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnPag2.setForeground(new java.awt.Color(71, 85, 105));
        btnPag2.setText("2");
        btnPag2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnPag2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 30, 24));

        btnPag3.setBackground(new java.awt.Color(255, 255, 255));
        btnPag3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnPag3.setForeground(new java.awt.Color(71, 85, 105));
        btnPag3.setText("3");
        btnPag3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnPag3, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 280, 30, 24));

        btnSiguiente.setBackground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(71, 85, 105));
        btnSiguiente.setText(">");
        btnSiguiente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialAhorros.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 30, 24));

        pnlMainContent.add(pnlHistorialAhorros, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 292, 657, 315));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarAhorroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAhorroActionPerformed
        if (idAhorroSeleccionado == 0) {
            controller.guardar();
        } else {
            controller.actualizar(idAhorroSeleccionado);
        }
    }//GEN-LAST:event_btnRegistrarAhorroActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idAhorroSeleccionado == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro de ahorro de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este registro de ahorro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.eliminar(idAhorroSeleccionado);
            idAhorroSeleccionado = 0;
            limpiarFormulario();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCSVActionPerformed
        controller.exportarCSV();
    }//GEN-LAST:event_btnExportarCSVActionPerformed

    private void tblAhorrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAhorrosMouseClicked
        int fila = tblAhorros.getSelectedRow();
        if (fila != -1) {
            Object idVal = tblAhorros.getValueAt(fila, 4);
            if (idVal != null) {
                idAhorroSeleccionado = Integer.parseInt(idVal.toString());
                controller.obtenerAhorroSeleccionado(idAhorroSeleccionado);
            }
        }
    }//GEN-LAST:event_tblAhorrosMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AhorrosView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarCSV;
    public javax.swing.JButton btnFiltros;
    private javax.swing.JButton btnPag1;
    private javax.swing.JButton btnPag2;
    private javax.swing.JButton btnPag3;
    public javax.swing.JButton btnRegistrarAhorro;
    private javax.swing.JButton btnSiguiente;
    public javax.swing.JComboBox<ComboItem> cbMetaFondo;
    public com.toedter.calendar.JDateChooser dateFecha;
    private view.components.HeaderBar headerBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAutoEst;
    private javax.swing.JLabel lblAutoIcon;
    private javax.swing.JLabel lblAutoName;
    public javax.swing.JLabel lblAutoPercent;
    public javax.swing.JLabel lblAutoProgressText;
    private javax.swing.JLabel lblBadgeActivo;
    private javax.swing.JLabel lblBadgeForm;
    public javax.swing.JLabel lblCardMetaMensualDesc;
    private javax.swing.JLabel lblCardMetaMensualTitle;
    public javax.swing.JLabel lblCardMetaMensualValue;
    public javax.swing.JLabel lblCardTotalAhorradoChange;
    private javax.swing.JLabel lblCardTotalAhorradoTitle;
    public javax.swing.JLabel lblCardTotalAhorradoValue;
    private javax.swing.JLabel lblConsejoIcon;
    private javax.swing.JLabel lblConsejoText;
    private javax.swing.JLabel lblConsejoTitle;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEficienciaTitle;
    public javax.swing.JLabel lblEficienciaValue;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFondoEmergenciaEst;
    private javax.swing.JLabel lblFondoEmergenciaIcon;
    private javax.swing.JLabel lblFondoEmergenciaName;
    public javax.swing.JLabel lblFondoEmergenciaPercent;
    public javax.swing.JLabel lblFondoEmergenciaProgressText;
    private javax.swing.JLabel lblFondoEmergenciaTitle;
    private javax.swing.JLabel lblHistorialAhorrosTitle;
    private javax.swing.JLabel lblLevelUp;
    private javax.swing.JLabel lblMetaFondo;
    private javax.swing.JLabel lblMetasAhorroTitle;
    private javax.swing.JLabel lblMetasGlobalesTitle;
    private javax.swing.JLabel lblMonto;
    public javax.swing.JLabel lblMostrarTransacciones;
    private javax.swing.JLabel lblRegistrarAhorroTitle;
    private javax.swing.JLabel lblVacacionesEst;
    private javax.swing.JLabel lblVacacionesIcon;
    private javax.swing.JLabel lblVacacionesName;
    public javax.swing.JLabel lblVacacionesPercent;
    public javax.swing.JLabel lblVacacionesProgressText;
    public javax.swing.JLabel lblVerTodasMetas;
    public javax.swing.JProgressBar pbAutoMeta;
    public javax.swing.JProgressBar pbFondoEmergencia;
    public javax.swing.JProgressBar pbFondoEmergenciaMeta;
    public javax.swing.JProgressBar pbMetaMensual;
    public javax.swing.JProgressBar pbMetasGlobales;
    public javax.swing.JProgressBar pbVacacionesMeta;
    private javax.swing.JPanel pnlCardEficiencia;
    private javax.swing.JPanel pnlCardMetaMensual;
    private javax.swing.JPanel pnlCardTotalAhorrado;
    private javax.swing.JPanel pnlConsejo;
    private javax.swing.JPanel pnlHistorialAhorros;
    public javax.swing.JPanel pnlMainContent;
    public javax.swing.JPanel pnlMetaAuto;
    public javax.swing.JPanel pnlMetaFondoEmergencia;
    public javax.swing.JPanel pnlMetaVacaciones;
    public javax.swing.JPanel pnlMetasAhorro;
    private javax.swing.JPanel pnlRegistrarAhorro;
    private javax.swing.JScrollPane scrollContent;
    private view.components.SidebarPanel sidebarPanel;
    public javax.swing.JTable tblAhorros;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
