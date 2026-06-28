package controller;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Session;
import model.Usuario;
import model.ComboItem;
import model.DashboardResumen;
import model.DashboardGraficos;
import service.DashboardService;
import view.DashboardView;
import view.SystemView;
import view.DeudasView;

public final class DashboardController {
    private DashboardView view;
    private DashboardService service;

    // Advanced Filter variables
    private int idCategoriaFiltro = 0;
    private int idTipoGastoFiltro = 0;
    private double montoMinFiltro = 0;
    private double montoMaxFiltro = 99999999;
    private List<DashboardGraficos> ultimoGraficoData = new ArrayList<>();

    public DashboardController(DashboardView view) {
        this.view = view;
        this.service = new DashboardService();

        popularColaboradores();
        popularPeriodos();
        cargarDatos();
        setupListeners();
    }

    private void popularPeriodos() {
        try {
            view.cbTimePeriod.removeAllItems();
            view.cbTimePeriod.addItem(util.DateUtil.getCurrentPeriodLabel());
            view.cbTimePeriod.addItem(util.DateUtil.getPreviousPeriodLabel());
            view.cbTimePeriod.addItem("Todo el tiempo");
            view.cbTimePeriod.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popularColaboradores() {
        try {
            view.cbCollaborator.removeAllItems();
            view.cbCollaborator.addItem(new model.ComboItem(0, "Todos los usuarios"));

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            service.UsuarioService uService = new service.UsuarioService();
            Usuario owner = uService.obtenerUsuario(idUsuarioOwner);
            if (owner != null) {
                view.cbCollaborator.addItem(new model.ComboItem(owner.getId(), owner.getNombre() + " (Yo)"));
            }

            List<model.UsuarioCompartido> list = uService.listarUsuariosCompartidos(idUsuarioOwner);
            for (model.UsuarioCompartido uc : list) {
                if (uc.getEstado().equalsIgnoreCase("Activo")) {
                    view.cbCollaborator.addItem(new model.ComboItem(uc.getUsuarioInvitadoId(), uc.getNombreInvitado()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            // Get selected collaborator
            int idFiltradoUsuario = 0;
            if (view.cbCollaborator.getSelectedItem() != null) {
                ComboItem itemColab = (ComboItem) view.cbCollaborator.getSelectedItem();
                idFiltradoUsuario = itemColab.getValue();
            }

            // Get selected period
            String selectionPeriod = (view.cbTimePeriod.getSelectedItem() != null) 
                ? view.cbTimePeriod.getSelectedItem().toString() 
                : util.DateUtil.getCurrentPeriodLabel();
            
            String[] range = util.DateUtil.getPeriodDateRange(selectionPeriod);
            String fechaInicio = range[0];
            String fechaFin = range[1];

            // 1. Cargar Resumen (Tarjetas + Insights)
            DashboardResumen r = service.obtenerResumen(
                idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin,
                idCategoriaFiltro, idTipoGastoFiltro, montoMinFiltro, montoMaxFiltro
            );
            
            view.lblCardIncomeValue.setText("S/. " + String.format("%.2f", r.getIngresosMes()));
            view.lblCardIncomeChange.setText(r.getIngresosCambio());
            
            view.lblCardExpensesValue.setText("S/. " + String.format("%.2f", r.getGastosMes()));
            view.lblCardExpensesChange.setText(r.getGastosCambio());
            
            view.lblCardSavingsValue.setText("S/. " + String.format("%.2f", r.getAhorrosMes()));
            view.lblCardSavingsChange.setText(r.getAhorrosCambio());
            
            view.lblCardBalanceValue.setText("S/. " + String.format("%.2f", r.getBalanceMes()));
            view.lblCardBalanceChange.setText(r.getBalanceCambio());
            
            view.lblSavingsBannerTitle.setText(r.getBannerTitle());
            view.lblSavingsBannerDesc.setText(r.getBannerDesc());
            
            view.lblInternetAmount.setText("S/. " + String.format("%.2f", r.getUpcomingPayInternetAmount()));
            view.lblInternetDate.setText(util.DateUtil.formatLongDate(r.getUpcomingPayInternetDate()));
            
            view.lblHealthAmount.setText("S/. " + String.format("%.2f", r.getUpcomingPayHealthAmount()));
            view.lblHealthDate.setText(util.DateUtil.formatLongDate(r.getUpcomingPayHealthDate()));

            // 2. Cargar Gráficos (Presupuesto vs Gastos)
            List<DashboardGraficos> listGraficos = service.obtenerGraficos(
                idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin,
                idCategoriaFiltro, idTipoGastoFiltro, montoMinFiltro, montoMaxFiltro
            );

            if (listGraficos != null) {
                listGraficos.sort((g1, g2) -> {
                    int pct1 = (g1.getLimite() > 0) ? (int)((g1.getGasto() * 100) / g1.getLimite()) : 0;
                    int pct2 = (g2.getLimite() > 0) ? (int)((g2.getGasto() * 100) / g2.getLimite()) : 0;
                    boolean exceeded1 = pct1 >= 100;
                    boolean exceeded2 = pct2 >= 100;

                    if (exceeded1 && !exceeded2) {
                        return -1; // Exceeded first
                    } else if (!exceeded1 && exceeded2) {
                        return 1;
                    } else {
                        return Integer.compare(pct2, pct1); // Higher percentage first
                    }
                });
            }
            ultimoGraficoData = listGraficos;

            // Hide the hardcoded static labels
            view.lblHousingTitle.setVisible(false);
            view.lblHousingDesc.setVisible(false);
            view.lblHousingValue.setVisible(false);
            view.pbHousing.setVisible(false);
            view.lblHousingStatus.setVisible(false);
            view.lblHousingPercent.setVisible(false);
            
            view.lblFoodTitle.setVisible(false);
            view.lblFoodDesc.setVisible(false);
            view.lblFoodValue.setVisible(false);
            view.pbFood.setVisible(false);
            view.lblFoodStatus.setVisible(false);
            view.lblFoodExcess.setVisible(false);
            
            view.lblTransportTitle.setVisible(false);
            view.lblTransportDesc.setVisible(false);
            view.lblTransportValue.setVisible(false);
            view.pbTransport.setVisible(false);
            view.lblTransportStatus.setVisible(false);
            view.lblTransportPercent.setVisible(false);

            // Find or create scroll container
            javax.swing.JScrollPane scrollPane = null;
            for (java.awt.Component comp : view.pnlBudgetVsSpending.getComponents()) {
                if (comp instanceof javax.swing.JScrollPane) {
                    scrollPane = (javax.swing.JScrollPane) comp;
                    break;
                }
            }
            if (scrollPane == null) {
                scrollPane = new javax.swing.JScrollPane();
                scrollPane.setBorder(null);
                scrollPane.setBackground(Color.WHITE);
                scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                view.pnlBudgetVsSpending.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 50, 560, 305));
            }

            javax.swing.JPanel containerPanel = new javax.swing.JPanel();
            containerPanel.setBackground(Color.WHITE);
            containerPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 15));
            containerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15));

            int count = 0;
            for (DashboardGraficos gItem : listGraficos) {
                if (count >= 3) break;
                javax.swing.JPanel card = crearTarjetaGasto(gItem);
                containerPanel.add(card);
                count++;
            }

            // Wrap containerPanel in a top-aligned wrapper panel so it doesn't stretch vertically
            javax.swing.JPanel wrapperPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
            wrapperPanel.setBackground(Color.WHITE);
            wrapperPanel.add(containerPanel, java.awt.BorderLayout.NORTH);

            scrollPane.setViewportView(wrapperPanel);
            view.pnlBudgetVsSpending.revalidate();
            view.pnlBudgetVsSpending.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupListeners() {
        // "+ Agregar Transacción" button click
        view.btnAddTransaction.addActionListener(e -> {
            SystemView viewSystem = new SystemView();
            viewSystem.setVisible(true);
            view.dispose();
        });

        // "Ver Transacciones Programadas" button click (navigates to Deudas)
        view.btnViewScheduled.addActionListener(e -> {
            DeudasView viewDeudas = new DeudasView();
            viewDeudas.setVisible(true);
            view.dispose();
        });

        // "Exportar PDF" button click
        view.btnExportPDF.addActionListener(e -> {
            exportarPDF();
        });

        // "Más Filtros" button click
        view.btnMoreFilters.addActionListener(e -> {
            mostrarFiltrosAvanzados();
        });

        // Combo box value changes reload data
        view.cbTimePeriod.addActionListener(e -> cargarDatos());
        view.cbCollaborator.addActionListener(e -> cargarDatos());

        // "Ver todas las categorías" label click
        view.lblViewAllCategories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        view.lblViewAllCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.CategoriasView dialog = new view.CategoriasView(view, true, ultimoGraficoData);
                dialog.setVisible(true);
            }
        });
    }

    private void mostrarFiltrosAvanzados() {
        javax.swing.JDialog dialog = new javax.swing.JDialog(view, "Filtros Avanzados", true);
        dialog.setLayout(new java.awt.GridLayout(6, 2, 10, 10));
        dialog.setSize(380, 260);
        dialog.setLocationRelativeTo(view);

        // Load categories and types
        service.GastoService gService = new service.GastoService();
        
        dialog.add(new javax.swing.JLabel("  Categoría:"));
        javax.swing.JComboBox<ComboItem> cbCat = new javax.swing.JComboBox<>();
        cbCat.addItem(new ComboItem(0, "Todas las categorías"));
        try {
            gService.listarCategorias().forEach(cbCat::addItem);
        } catch (Exception e) {}
        // Select currently selected
        for (int i = 0; i < cbCat.getItemCount(); i++) {
            if (cbCat.getItemAt(i).getValue() == idCategoriaFiltro) {
                cbCat.setSelectedIndex(i);
                break;
            }
        }
        dialog.add(cbCat);

        dialog.add(new javax.swing.JLabel("  Tipo de Gasto:"));
        javax.swing.JComboBox<ComboItem> cbType = new javax.swing.JComboBox<>();
        cbType.addItem(new ComboItem(0, "Todos los tipos"));
        try {
            gService.listarTipoGastos().forEach(cbType::addItem);
        } catch (Exception e) {}
        for (int i = 0; i < cbType.getItemCount(); i++) {
            if (cbType.getItemAt(i).getValue() == idTipoGastoFiltro) {
                cbType.setSelectedIndex(i);
                break;
            }
        }
        dialog.add(cbType);

        dialog.add(new javax.swing.JLabel("  Monto Mínimo:"));
        javax.swing.JTextField txtMin = new javax.swing.JTextField(String.valueOf(montoMinFiltro));
        dialog.add(txtMin);

        dialog.add(new javax.swing.JLabel("  Monto Máximo:"));
        javax.swing.JTextField txtMax = new javax.swing.JTextField(montoMaxFiltro == 99999999 ? "" : String.valueOf(montoMaxFiltro));
        dialog.add(txtMax);

        javax.swing.JButton btnClear = new javax.swing.JButton("Limpiar Filtros");
        btnClear.addActionListener(e -> {
            idCategoriaFiltro = 0;
            idTipoGastoFiltro = 0;
            montoMinFiltro = 0;
            montoMaxFiltro = 99999999;
            dialog.dispose();
            cargarDatos();
        });
        dialog.add(btnClear);

        javax.swing.JButton btnApply = new javax.swing.JButton("Aplicar Filtros");
        btnApply.addActionListener(e -> {
            try {
                idCategoriaFiltro = ((ComboItem) cbCat.getSelectedItem()).getValue();
                idTipoGastoFiltro = ((ComboItem) cbType.getSelectedItem()).getValue();
                
                String minStr = txtMin.getText().trim();
                montoMinFiltro = minStr.isEmpty() ? 0 : Double.parseDouble(minStr);
                
                String maxStr = txtMax.getText().trim();
                montoMaxFiltro = maxStr.isEmpty() ? 99999999 : Double.parseDouble(maxStr);
                
                dialog.dispose();
                cargarDatos();
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Los montos ingresados deben ser numéricos.", "Error de Validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btnApply);

        dialog.setVisible(true);
    }

    private void exportarPDF() {
        String colabName = (view.cbCollaborator.getSelectedItem() != null) 
            ? view.cbCollaborator.getSelectedItem().toString() 
            : "Todos";
        String periodName = (view.cbTimePeriod.getSelectedItem() != null)
            ? view.cbTimePeriod.getSelectedItem().toString()
            : "Todo el tiempo";

        List<String[]> budgetDetails = new ArrayList<>();
        
        for (DashboardGraficos g : ultimoGraficoData) {
            int percent = (g.getLimite() > 0) ? (int)((g.getGasto() * 100) / g.getLimite()) : 0;
            String status = percent >= 100 ? "Excedido" : "Al dia";
            budgetDetails.add(new String[]{
                g.getCategoria(),
                "S/. " + String.format("%.2f", g.getGasto()),
                "S/. " + String.format("%.2f", g.getLimite()),
                percent + "%",
                status
            });
        }

        try {
            util.PdfGenerator pdfGen = new util.PdfGenerator();
            byte[] pdfBytes = pdfGen.generatePdf(
                periodName, colabName,
                view.lblCardIncomeValue.getText(),
                view.lblCardExpensesValue.getText(),
                view.lblCardSavingsValue.getText(),
                view.lblCardBalanceValue.getText(),
                budgetDetails
            );

            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte PDF");
            fileChooser.setSelectedFile(new java.io.File("Reporte_Finanzas.pdf"));
            int userSelection = fileChooser.showSaveDialog(view);
            if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (java.io.FileOutputStream fos = new java.io.FileOutputStream(fileToSave)) {
                    fos.write(pdfBytes);
                }
                javax.swing.JOptionPane.showMessageDialog(view, "Reporte PDF exportado exitosamente en:\n" + fileToSave.getAbsolutePath(), "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(view, "Error al generar el reporte PDF: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private javax.swing.JPanel crearTarjetaGasto(DashboardGraficos gItem) {
        javax.swing.JPanel card = new javax.swing.JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new javax.swing.border.MatteBorder(0, 0, 1, 0, new Color(234, 241, 255)));
        card.setPreferredSize(new java.awt.Dimension(530, 80));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Title (Category Name) - Left Aligned
        javax.swing.JLabel lblTitle = new javax.swing.JLabel(gItem.getCategoria());
        lblTitle.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
        lblTitle.setForeground(new Color(11, 28, 48));
        card.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 250, 18));

        // Description Subtitle - Left Aligned
        String desc = gItem.getDescripcion();
        if (desc == null || desc.isEmpty()) desc = "Presupuesto de la categoría";
        javax.swing.JLabel lblDesc = new javax.swing.JLabel("<html>" + desc + "</html>");
        lblDesc.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
        lblDesc.setForeground(new Color(71, 85, 105));
        card.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 23, 300, 15));

        // Spent / Limit Value - Right Aligned
        javax.swing.JLabel lblVal = new javax.swing.JLabel("S/. " + String.format("%.0f", gItem.getGasto()) + " / S/. " + String.format("%.0f", gItem.getLimite()));
        lblVal.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
        lblVal.setForeground(new Color(11, 28, 48));
        lblVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        card.add(lblVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 5, 205, 18));

        // Progress Bar - Fills the middle width
        javax.swing.JProgressBar pb = new javax.swing.JProgressBar();
        pb.setBackground(new java.awt.Color(234, 241, 255));
        int percent = (gItem.getLimite() > 0) ? (int)((gItem.getGasto() * 100) / gItem.getLimite()) : 0;
        pb.setValue(Math.min(percent, 100));
        pb.setBorderPainted(false);
        if (percent >= 100) {
            pb.setForeground(new java.awt.Color(186, 26, 26)); // Red
        } else {
            pb.setForeground(new java.awt.Color(37, 99, 235)); // Blue
        }
        card.add(pb, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 43, 520, 8));

        // Status - Left Aligned Bottom
        javax.swing.JLabel lblStatus = new javax.swing.JLabel(percent >= 100 ? "EXCEDIDO" : "AL DIA");
        lblStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
        if (percent >= 100) {
            lblStatus.setForeground(new java.awt.Color(186, 26, 26));
        } else {
            lblStatus.setForeground(new java.awt.Color(0, 108, 73));
        }
        card.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 55, 150, 15));

        // Percent - Right Aligned Bottom
        javax.swing.JLabel lblPct = new javax.swing.JLabel(percent + "% utilizado");
        lblPct.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
        lblPct.setForeground(new Color(71, 85, 105));
        lblPct.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        card.add(lblPct, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 55, 205, 15));

        return card;
    }
}
