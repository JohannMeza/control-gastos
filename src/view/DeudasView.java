package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.ComboItem;
import controller.DeudaController;
import view.components.ModernTableHelper;

/**
 * Vista de Control de Deudas y Préstamos con soporte para NetBeans GUI Builder.
 */
public class DeudasView extends javax.swing.JFrame {

    private controller.DeudaController controller;

    public DeudasView() {
        initComponents();
        initCustomStyles();
        setSize(1260, 780);
        setResizable(false);
        setTitle("Finandes - Gestión de Deudas");
        setLocationRelativeTo(null);

        this.controller = new controller.DeudaController(this);
    }

    private void initCustomStyles() {
        ModernTableHelper.styleTable(tblHistorial);
        ModernTableHelper.styleScrollPane(jScrollPaneDeudas);
        scrollContent.getVerticalScrollBar().setUnitIncrement(16);

        setupTableStyle();
        setupFormDates();
        setupInputRestrictions();

        headerBar.getBtnAccion().addActionListener(evt -> {
            txtAcreedor.requestFocus();
        });

        lblDebt2PayNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void setupFormDates() {
        if (dateFechaInicio != null) {
            dateFechaInicio.setDateFormatString("dd/MM/yyyy");
            dateFechaInicio.setDate(new Date());
        }
        if (dateFechaPago != null) {
            dateFechaPago.setDateFormatString("dd/MM/yyyy");
            dateFechaPago.setDate(new Date());
        }
    }

    private void setupInputRestrictions() {
        txtMontoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
                if (c == '.' && txtMontoTotal.getText().contains(".")) {
                    evt.consume();
                }
            }
        });

        txtTasaInteres.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
                if (c == '.' && txtTasaInteres.getText().contains(".")) {
                    evt.consume();
                }
            }
        });

        txtCuotasTotales.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        txtMontoAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
                if (c == '.' && txtMontoAbono.getText().contains(".")) {
                    evt.consume();
                }
            }
        });
    }

    private void setupTableStyle() {
        tblHistorial.setRowHeight(34);

        // FECHA (Col 0)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblHistorial.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // DEUDA (Col 1)
        DefaultTableCellRenderer debtRenderer = new DefaultTableCellRenderer();
        debtRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        debtRenderer.setForeground(new Color(15, 23, 42));
        tblHistorial.getColumnModel().getColumn(1).setCellRenderer(debtRenderer);

        // MONTO PAGADO (Col 2)
        tblHistorial.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(new Font("Segoe UI", Font.BOLD, 12));
                label.setForeground(new Color(16, 185, 129));
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });

        // SALDO RESTANTE (Col 3)
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        rightRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rightRenderer.setForeground(new Color(15, 23, 42));
        tblHistorial.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new view.components.SidebarPanel();
        headerBar = new view.components.HeaderBar();
        scrollContent = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        pnlCardDeudaTotal = new javax.swing.JPanel();
        lblCardDeudaTotalTitle = new javax.swing.JLabel();
        lblCardDeudaTotalValue = new javax.swing.JLabel();
        lblDeudaTotalSubtext = new javax.swing.JLabel();
        pnlCardProximoVencimiento = new javax.swing.JPanel();
        lblCardProximoVencimientoTitle = new javax.swing.JLabel();
        lblCardProximoVencimientoValue = new javax.swing.JLabel();
        lblProximoVencimientoSubtext = new javax.swing.JLabel();
        pnlCardTotalPagado = new javax.swing.JPanel();
        lblCardTotalPagadoTitle = new javax.swing.JLabel();
        lblCardTotalPagadoValue = new javax.swing.JLabel();
        lblTotalPagadoSubtext = new javax.swing.JLabel();
        pnlNuevaDeuda = new javax.swing.JPanel();
        lblNuevaDeudaTitle = new javax.swing.JLabel();
        lblBadgeRegistro = new javax.swing.JLabel();
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
        pnlDeudasActivas = new javax.swing.JPanel();
        lblDeudasActivasTitle = new javax.swing.JLabel();
        lblDeudasActivasSubtitle = new javax.swing.JLabel();
        pnlDebt1 = new javax.swing.JPanel();
        lblDebt1Title = new javax.swing.JLabel();
        lblDebt1Badge = new javax.swing.JLabel();
        lblDebt1Desc = new javax.swing.JLabel();
        pbDebt1 = new javax.swing.JProgressBar();
        lblDebt1Percent = new javax.swing.JLabel();
        lblDebt1ProgressText = new javax.swing.JLabel();
        lblDebt1SaldoTitle = new javax.swing.JLabel();
        lblDebt1SaldoValue = new javax.swing.JLabel();
        pnlDebt2 = new javax.swing.JPanel();
        lblDebt2Title = new javax.swing.JLabel();
        lblDebt2Badge = new javax.swing.JLabel();
        lblDebt2Desc = new javax.swing.JLabel();
        lblDebt2WarningIcon = new javax.swing.JLabel();
        lblDebt2WarningText = new javax.swing.JLabel();
        lblDebt2PayNow = new javax.swing.JLabel();
        pnlDebt3 = new javax.swing.JPanel();
        lblDebt3Title = new javax.swing.JLabel();
        lblDebt3Badge = new javax.swing.JLabel();
        lblDebt3Desc = new javax.swing.JLabel();
        pbDebt3 = new javax.swing.JProgressBar();
        lblDebt3Percent = new javax.swing.JLabel();
        lblDebt3ProgressText = new javax.swing.JLabel();
        lblDebt3SaldoTitle = new javax.swing.JLabel();
        lblDebt3SaldoValue = new javax.swing.JLabel();
        pnlRegistrarAbono = new javax.swing.JPanel();
        lblRegistrarAbonoTitle = new javax.swing.JLabel();
        lblBadgePago = new javax.swing.JLabel();
        lblSeleccionarDeuda = new javax.swing.JLabel();
        cbSeleccionarDeuda = new javax.swing.JComboBox<>();
        lblMontoAbono = new javax.swing.JLabel();
        txtMontoAbono = new javax.swing.JTextField();
        lblFechaPago = new javax.swing.JLabel();
        dateFechaPago = new com.toedter.calendar.JDateChooser();
        btnConfirmarPago = new javax.swing.JButton();
        pnlHistorialReciente = new javax.swing.JPanel();
        lblHistorialRecienteTitle = new javax.swing.JLabel();
        lblExportarCSV = new javax.swing.JLabel();
        jScrollPaneDeudas = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Gestión de Deudas");
        setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebarPanel.setActiveModule("Deudas");
        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 750));

        headerBar.setAccionBoton("+ Nueva Deuda");
        headerBar.setModuloActual("Gestión de Deudas");
        getContentPane().add(headerBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        scrollContent.setBorder(null);
        scrollContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMainContent.setBackground(new java.awt.Color(248, 250, 252));
        pnlMainContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCardDeudaTotal.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardDeudaTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardDeudaTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardDeudaTotalTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardDeudaTotalTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardDeudaTotalTitle.setText("DEUDA TOTAL");
        pnlCardDeudaTotal.add(lblCardDeudaTotalTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 160, 14));

        lblCardDeudaTotalValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCardDeudaTotalValue.setForeground(new java.awt.Color(15, 23, 42));
        lblCardDeudaTotalValue.setText("S/. 45,280.00");
        pnlCardDeudaTotal.add(lblCardDeudaTotalValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 32));

        lblDeudaTotalSubtext.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblDeudaTotalSubtext.setForeground(new java.awt.Color(0, 159, 227));
        lblDeudaTotalSubtext.setText("Total pendiente consolidado");
        pnlCardDeudaTotal.add(lblDeudaTotalSubtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 280, 16));

        pnlMainContent.add(pnlCardDeudaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 315, 110));

        pnlCardProximoVencimiento.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardProximoVencimiento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardProximoVencimiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardProximoVencimientoTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardProximoVencimientoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardProximoVencimientoTitle.setText("PRÓXIMO VENCIMIENTO");
        pnlCardProximoVencimiento.add(lblCardProximoVencimientoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 180, 14));

        lblCardProximoVencimientoValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCardProximoVencimientoValue.setForeground(new java.awt.Color(217, 119, 6));
        lblCardProximoVencimientoValue.setText("Oct 15, 2023");
        pnlCardProximoVencimiento.add(lblCardProximoVencimientoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 32));

        lblProximoVencimientoSubtext.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblProximoVencimientoSubtext.setForeground(new java.awt.Color(217, 119, 6));
        lblProximoVencimientoSubtext.setText("Vence en pocos días");
        pnlCardProximoVencimiento.add(lblProximoVencimientoSubtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 280, 16));

        pnlMainContent.add(pnlCardProximoVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 15, 315, 110));

        pnlCardTotalPagado.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardTotalPagado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlCardTotalPagado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCardTotalPagadoTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardTotalPagadoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblCardTotalPagadoTitle.setText("TOTAL PAGADO ESTE MES");
        pnlCardTotalPagado.add(lblCardTotalPagadoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 180, 14));

        lblCardTotalPagadoValue.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCardTotalPagadoValue.setForeground(new java.awt.Color(16, 185, 129));
        lblCardTotalPagadoValue.setText("S/. 3,450.00");
        pnlCardTotalPagado.add(lblCardTotalPagadoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 32, 280, 32));

        lblTotalPagadoSubtext.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTotalPagadoSubtext.setForeground(new java.awt.Color(16, 185, 129));
        lblTotalPagadoSubtext.setText("Abonos confirmados");
        pnlCardTotalPagado.add(lblTotalPagadoSubtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, 280, 16));

        pnlMainContent.add(pnlCardTotalPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 15, 326, 110));

        pnlNuevaDeuda.setBackground(new java.awt.Color(255, 255, 255));
        pnlNuevaDeuda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlNuevaDeuda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNuevaDeudaTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNuevaDeudaTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblNuevaDeudaTitle.setText("NUEVA DEUDA");
        pnlNuevaDeuda.add(lblNuevaDeudaTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 180, 16));

        lblBadgeRegistro.setBackground(new java.awt.Color(224, 242, 254));
        lblBadgeRegistro.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBadgeRegistro.setForeground(new java.awt.Color(3, 105, 161));
        lblBadgeRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgeRegistro.setText("REGISTRO");
        lblBadgeRegistro.setOpaque(true);
        pnlNuevaDeuda.add(lblBadgeRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 10, 64, 18));

        lblAcreedor.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblAcreedor.setForeground(new java.awt.Color(100, 116, 139));
        lblAcreedor.setText("Acreedor / Entidad *");
        pnlNuevaDeuda.add(lblAcreedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 280, 14));

        txtAcreedor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtAcreedor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlNuevaDeuda.add(txtAcreedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, 283, 28));

        lblMontoTotal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMontoTotal.setForeground(new java.awt.Color(100, 116, 139));
        lblMontoTotal.setText("Monto Total (S/.) *");
        pnlNuevaDeuda.add(lblMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 82, 135, 14));

        txtMontoTotal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMontoTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlNuevaDeuda.add(txtMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 98, 135, 28));

        lblTasaInteres.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTasaInteres.setForeground(new java.awt.Color(100, 116, 139));
        lblTasaInteres.setText("Tasa Anual (%) *");
        pnlNuevaDeuda.add(lblTasaInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 82, 135, 14));

        txtTasaInteres.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTasaInteres.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlNuevaDeuda.add(txtTasaInteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 98, 135, 28));

        lblCuotasTotales.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblCuotasTotales.setForeground(new java.awt.Color(100, 116, 139));
        lblCuotasTotales.setText("N° Cuotas *");
        pnlNuevaDeuda.add(lblCuotasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 135, 14));

        txtCuotasTotales.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCuotasTotales.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlNuevaDeuda.add(txtCuotasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 146, 135, 28));

        lblFechaInicio.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFechaInicio.setForeground(new java.awt.Color(100, 116, 139));
        lblFechaInicio.setText("Fecha Inicio *");
        pnlNuevaDeuda.add(lblFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 130, 135, 14));
        pnlNuevaDeuda.add(dateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 146, 135, 28));

        btnRegistrarDeuda.setBackground(new java.awt.Color(0, 159, 227));
        btnRegistrarDeuda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarDeuda.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarDeuda.setText("Registrar Deuda");
        btnRegistrarDeuda.setBorder(null);
        btnRegistrarDeuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarDeudaActionPerformed(evt);
            }
        });
        pnlNuevaDeuda.add(btnRegistrarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 196, 283, 34));

        pnlMainContent.add(pnlNuevaDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 140, 315, 255));

        pnlDeudasActivas.setBackground(new java.awt.Color(255, 255, 255));
        pnlDeudasActivas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDeudasActivas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDeudasActivasTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDeudasActivasTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblDeudasActivasTitle.setText("DEUDAS ACTIVAS");
        pnlDeudasActivas.add(lblDeudasActivasTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 200, 18));

        lblDeudasActivasSubtitle.setBackground(new java.awt.Color(241, 245, 249));
        lblDeudasActivasSubtitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblDeudasActivasSubtitle.setForeground(new java.awt.Color(71, 85, 105));
        lblDeudasActivasSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeudasActivasSubtitle.setText("3 Deudas en curso");
        lblDeudasActivasSubtitle.setOpaque(true);
        pnlDeudasActivas.add(lblDeudasActivasSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 10, 115, 18));

        pnlDebt1.setBackground(new java.awt.Color(248, 250, 252));
        pnlDebt1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDebt1Title.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDebt1Title.setForeground(new java.awt.Color(15, 23, 42));
        lblDebt1Title.setText("Préstamo Hipotecario");
        pnlDebt1.add(lblDebt1Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 130, 16));

        lblDebt1Badge.setBackground(new java.awt.Color(209, 250, 229));
        lblDebt1Badge.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblDebt1Badge.setForeground(new java.awt.Color(16, 185, 129));
        lblDebt1Badge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDebt1Badge.setText("AL DÍA");
        lblDebt1Badge.setOpaque(true);
        pnlDebt1.add(lblDebt1Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 8, 50, 16));

        lblDebt1Desc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDebt1Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt1Desc.setText("BBVA Banco");
        pnlDebt1.add(lblDebt1Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 180, 14));

        pbDebt1.setForeground(new java.awt.Color(0, 159, 227));
        pbDebt1.setValue(19);
        pnlDebt1.add(pbDebt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 180, 6));

        lblDebt1Percent.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblDebt1Percent.setForeground(new java.awt.Color(0, 159, 227));
        lblDebt1Percent.setText("18.7% completado");
        pnlDebt1.add(lblDebt1Percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 180, 14));

        lblDebt1ProgressText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDebt1ProgressText.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt1ProgressText.setText("45 de 240 cuotas pagadas");
        pnlDebt1.add(lblDebt1ProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 180, 14));

        lblDebt1SaldoTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblDebt1SaldoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt1SaldoTitle.setText("SALDO RESTANTE");
        pnlDebt1.add(lblDebt1SaldoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 180, 12));

        lblDebt1SaldoValue.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDebt1SaldoValue.setForeground(new java.awt.Color(15, 23, 42));
        lblDebt1SaldoValue.setText("S/. 97,500.00");
        pnlDebt1.add(lblDebt1SaldoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 180, 22));

        pnlDeudasActivas.add(pnlDebt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 200, 195));

        pnlDebt2.setBackground(new java.awt.Color(248, 250, 252));
        pnlDebt2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDebt2Title.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDebt2Title.setForeground(new java.awt.Color(15, 23, 42));
        lblDebt2Title.setText("Tarjeta Crédito Oro");
        pnlDebt2.add(lblDebt2Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 110, 16));

        lblDebt2Badge.setBackground(new java.awt.Color(254, 226, 226));
        lblDebt2Badge.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblDebt2Badge.setForeground(new java.awt.Color(239, 68, 68));
        lblDebt2Badge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDebt2Badge.setText("POR VENCER");
        lblDebt2Badge.setOpaque(true);
        pnlDebt2.add(lblDebt2Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 8, 70, 16));

        lblDebt2Desc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDebt2Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt2Desc.setText("Visa Internacional");
        pnlDebt2.add(lblDebt2Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 180, 14));

        lblDebt2WarningIcon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDebt2WarningIcon.setText("⚠️");
        pnlDebt2.add(lblDebt2WarningIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 18, 18));

        lblDebt2WarningText.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblDebt2WarningText.setForeground(new java.awt.Color(239, 68, 68));
        lblDebt2WarningText.setText("Vence en 2 días (Oct 15)");
        pnlDebt2.add(lblDebt2WarningText, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 52, 160, 16));

        lblDebt2PayNow.setBackground(new java.awt.Color(0, 159, 227));
        lblDebt2PayNow.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDebt2PayNow.setForeground(new java.awt.Color(255, 255, 255));
        lblDebt2PayNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDebt2PayNow.setText("Pagar Ahora");
        lblDebt2PayNow.setOpaque(true);
        pnlDebt2.add(lblDebt2PayNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 135, 180, 30));

        pnlDeudasActivas.add(pnlDebt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 38, 200, 195));

        pnlDebt3.setBackground(new java.awt.Color(248, 250, 252));
        pnlDebt3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlDebt3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDebt3Title.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDebt3Title.setForeground(new java.awt.Color(15, 23, 42));
        lblDebt3Title.setText("Préstamo Vehicular");
        pnlDebt3.add(lblDebt3Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 130, 16));

        lblDebt3Badge.setBackground(new java.awt.Color(241, 245, 249));
        lblDebt3Badge.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblDebt3Badge.setForeground(new java.awt.Color(71, 85, 105));
        lblDebt3Badge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDebt3Badge.setText("PENDIENTE");
        lblDebt3Badge.setOpaque(true);
        pnlDebt3.add(lblDebt3Badge, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 8, 58, 16));

        lblDebt3Desc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDebt3Desc.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt3Desc.setText("Ford Credit");
        pnlDebt3.add(lblDebt3Desc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 180, 14));

        pbDebt3.setForeground(new java.awt.Color(0, 159, 227));
        pbDebt3.setValue(62);
        pnlDebt3.add(pbDebt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 180, 6));

        lblDebt3Percent.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblDebt3Percent.setForeground(new java.awt.Color(0, 159, 227));
        lblDebt3Percent.setText("62.5% completado");
        pnlDebt3.add(lblDebt3Percent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 180, 14));

        lblDebt3ProgressText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDebt3ProgressText.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt3ProgressText.setText("30 de 48 cuotas pagadas");
        pnlDebt3.add(lblDebt3ProgressText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 180, 14));

        lblDebt3SaldoTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblDebt3SaldoTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblDebt3SaldoTitle.setText("SALDO RESTANTE");
        pnlDebt3.add(lblDebt3SaldoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 180, 12));

        lblDebt3SaldoValue.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDebt3SaldoValue.setForeground(new java.awt.Color(15, 23, 42));
        lblDebt3SaldoValue.setText("S/. 9,375.00");
        pnlDebt3.add(lblDebt3SaldoValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 180, 22));

        pnlDeudasActivas.add(pnlDebt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 38, 200, 195));

        pnlMainContent.add(pnlDeudasActivas, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 140, 657, 255));

        pnlRegistrarAbono.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistrarAbono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlRegistrarAbono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegistrarAbonoTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRegistrarAbonoTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblRegistrarAbonoTitle.setText("REGISTRAR ABONO");
        pnlRegistrarAbono.add(lblRegistrarAbonoTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 180, 16));

        lblBadgePago.setBackground(new java.awt.Color(209, 250, 229));
        lblBadgePago.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBadgePago.setForeground(new java.awt.Color(16, 185, 129));
        lblBadgePago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBadgePago.setText("PAGO");
        lblBadgePago.setOpaque(true);
        pnlRegistrarAbono.add(lblBadgePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 10, 54, 18));

        lblSeleccionarDeuda.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSeleccionarDeuda.setForeground(new java.awt.Color(100, 116, 139));
        lblSeleccionarDeuda.setText("Seleccionar Deuda *");
        pnlRegistrarAbono.add(lblSeleccionarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 280, 14));

        cbSeleccionarDeuda.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        pnlRegistrarAbono.add(cbSeleccionarDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, 283, 28));

        lblMontoAbono.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblMontoAbono.setForeground(new java.awt.Color(100, 116, 139));
        lblMontoAbono.setText("Monto del Abono (S/.) *");
        pnlRegistrarAbono.add(lblMontoAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 82, 135, 14));

        txtMontoAbono.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMontoAbono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 213, 225), 1, true));
        pnlRegistrarAbono.add(txtMontoAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 98, 135, 28));

        lblFechaPago.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFechaPago.setForeground(new java.awt.Color(100, 116, 139));
        lblFechaPago.setText("Fecha Pago *");
        pnlRegistrarAbono.add(lblFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 82, 135, 14));
        pnlRegistrarAbono.add(dateFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 98, 135, 28));

        btnConfirmarPago.setBackground(new java.awt.Color(66, 173, 77));
        btnConfirmarPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConfirmarPago.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarPago.setText("Confirmar Pago");
        btnConfirmarPago.setBorder(null);
        btnConfirmarPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPagoActionPerformed(evt);
            }
        });
        pnlRegistrarAbono.add(btnConfirmarPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 145, 283, 34));

        pnlMainContent.add(pnlRegistrarAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 410, 315, 230));

        pnlHistorialReciente.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorialReciente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlHistorialReciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHistorialRecienteTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblHistorialRecienteTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblHistorialRecienteTitle.setText("HISTORIAL RECIENTE");
        pnlHistorialReciente.add(lblHistorialRecienteTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 12, 220, 18));

        lblExportarCSV.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblExportarCSV.setForeground(new java.awt.Color(0, 159, 227));
        lblExportarCSV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblExportarCSV.setText("Exportar CSV");
        lblExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHistorialReciente.add(lblExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 12, 105, 18));

        jScrollPaneDeudas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 232, 226)));

        tblHistorial.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "FECHA", "DEUDA", "MONTO PAGADO", "SALDO RESTANTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistorial.setGridColor(new java.awt.Color(226, 232, 240));
        tblHistorial.setRowHeight(34);
        tblHistorial.setSelectionBackground(new java.awt.Color(224, 240, 250));
        tblHistorial.setSelectionForeground(new java.awt.Color(15, 23, 42));
        jScrollPaneDeudas.setViewportView(tblHistorial);

        pnlHistorialReciente.add(jScrollPaneDeudas, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 625, 175));

        pnlMainContent.add(pnlHistorialReciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 410, 657, 230));

        scrollContent.setViewportView(pnlMainContent);

        getContentPane().add(scrollContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarDeudaActionPerformed
        controller.guardarDeuda();
    }//GEN-LAST:event_btnRegistrarDeudaActionPerformed

    private void btnConfirmarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPagoActionPerformed
        controller.registrarAbono();
    }//GEN-LAST:event_btnConfirmarPagoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new DeudasView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConfirmarPago;
    public javax.swing.JButton btnRegistrarDeuda;
    public javax.swing.JComboBox<ComboItem> cbSeleccionarDeuda;
    public com.toedter.calendar.JDateChooser dateFechaInicio;
    public com.toedter.calendar.JDateChooser dateFechaPago;
    private view.components.HeaderBar headerBar;
    public javax.swing.JScrollPane jScrollPaneDeudas;
    public javax.swing.JLabel lblAcreedor;
    private javax.swing.JLabel lblBadgePago;
    private javax.swing.JLabel lblBadgeRegistro;
    public javax.swing.JLabel lblCardDeudaTotalTitle;
    public javax.swing.JLabel lblCardDeudaTotalValue;
    public javax.swing.JLabel lblCardProximoVencimientoTitle;
    public javax.swing.JLabel lblCardProximoVencimientoValue;
    public javax.swing.JLabel lblCardTotalPagadoTitle;
    public javax.swing.JLabel lblCardTotalPagadoValue;
    public javax.swing.JLabel lblCuotasTotales;
    public javax.swing.JLabel lblDebt1Badge;
    public javax.swing.JLabel lblDebt1Desc;
    public javax.swing.JLabel lblDebt1Percent;
    public javax.swing.JLabel lblDebt1ProgressText;
    public javax.swing.JLabel lblDebt1SaldoTitle;
    public javax.swing.JLabel lblDebt1SaldoValue;
    public javax.swing.JLabel lblDebt1Title;
    public javax.swing.JLabel lblDebt2Badge;
    public javax.swing.JLabel lblDebt2Desc;
    public javax.swing.JLabel lblDebt2PayNow;
    public javax.swing.JLabel lblDebt2Title;
    public javax.swing.JLabel lblDebt2WarningIcon;
    public javax.swing.JLabel lblDebt2WarningText;
    public javax.swing.JLabel lblDebt3Badge;
    public javax.swing.JLabel lblDebt3Desc;
    public javax.swing.JLabel lblDebt3Percent;
    public javax.swing.JLabel lblDebt3ProgressText;
    public javax.swing.JLabel lblDebt3SaldoTitle;
    public javax.swing.JLabel lblDebt3SaldoValue;
    public javax.swing.JLabel lblDebt3Title;
    public javax.swing.JLabel lblDeudaTotalSubtext;
    public javax.swing.JLabel lblDeudasActivasSubtitle;
    public javax.swing.JLabel lblDeudasActivasTitle;
    public javax.swing.JLabel lblExportarCSV;
    public javax.swing.JLabel lblFechaInicio;
    public javax.swing.JLabel lblFechaPago;
    public javax.swing.JLabel lblHistorialRecienteTitle;
    public javax.swing.JLabel lblMontoAbono;
    public javax.swing.JLabel lblMontoTotal;
    public javax.swing.JLabel lblNuevaDeudaTitle;
    private javax.swing.JLabel lblProximoVencimientoSubtext;
    public javax.swing.JLabel lblRegistrarAbonoTitle;
    public javax.swing.JLabel lblSeleccionarDeuda;
    public javax.swing.JLabel lblTasaInteres;
    private javax.swing.JLabel lblTotalPagadoSubtext;
    public javax.swing.JProgressBar pbDebt1;
    public javax.swing.JProgressBar pbDebt3;
    public javax.swing.JPanel pnlCardDeudaTotal;
    public javax.swing.JPanel pnlCardProximoVencimiento;
    public javax.swing.JPanel pnlCardTotalPagado;
    public javax.swing.JPanel pnlDebt1;
    public javax.swing.JPanel pnlDebt2;
    public javax.swing.JPanel pnlDebt3;
    public javax.swing.JPanel pnlDeudasActivas;
    public javax.swing.JPanel pnlHistorialReciente;
    public javax.swing.JPanel pnlMainContent;
    public javax.swing.JPanel pnlNuevaDeuda;
    public javax.swing.JPanel pnlRegistrarAbono;
    private javax.swing.JScrollPane scrollContent;
    private view.components.SidebarPanel sidebarPanel;
    public javax.swing.JTable tblHistorial;
    public javax.swing.JTextField txtAcreedor;
    public javax.swing.JTextField txtCuotasTotales;
    public javax.swing.JTextField txtMontoAbono;
    public javax.swing.JTextField txtMontoTotal;
    public javax.swing.JTextField txtTasaInteres;
    // End of variables declaration//GEN-END:variables
}
