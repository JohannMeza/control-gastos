package controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Deuda;
import model.AbonoDeuda;
import model.DeudaResumen;
import model.Session;
import model.Usuario;
import service.DeudaService;
import view.DeudasView;

public final class DeudaController {
    private DeudasView view;
    private DeudaService service;
    private javax.swing.JPanel pnlDeudasContenedor;

    public DeudaController(DeudasView view) {
        this.view = view;
        this.service = new DeudaService();

        setupContenedorDeudas();
        listarDeudasCombo();
        listarHistorialAbonos();
        cargarResumenYDeudas();
        
        setupListeners();
    }

    public void listarDeudasCombo() {
        try {
            view.cbSeleccionarDeuda.removeAllItems();
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            List<ComboItem> deudas = service.listarDeudasCombo(idUsuario);
            for (ComboItem item : deudas) {
                view.cbSeleccionarDeuda.addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarHistorialAbonos() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            List<AbonoDeuda> lista = service.listarHistorialAbonos(idUsuario);
            DefaultTableModel modelo = (DefaultTableModel) view.tblHistorial.getModel();
            modelo.setRowCount(0);

            for (AbonoDeuda a : lista) {
                modelo.addRow(new Object[]{
                    util.DateUtil.formatLongDate(a.getFechaPagoFormat()),
                    a.getNombreDeuda(),
                    "+S/. " + String.format("%.2f", a.getMontoAbono()),
                    "S/. " + String.format("%.2f", a.getSaldoRestante())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarResumenYDeudas() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            // Obtener hoy a medianoche
            java.util.Calendar todayCal = java.util.Calendar.getInstance();
            todayCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
            todayCal.set(java.util.Calendar.MINUTE, 0);
            todayCal.set(java.util.Calendar.SECOND, 0);
            todayCal.set(java.util.Calendar.MILLISECOND, 0);
            long todayMs = todayCal.getTimeInMillis();

            DeudaResumen r = service.obtenerResumen(idUsuario);
            view.lblCardDeudaTotalValue.setText("S/. " + String.format("%.2f", r.getTotalDeuda()));
            view.lblCardProximoVencimientoValue.setText(util.DateUtil.formatLongDate(r.getProximoVencimiento()));
            view.lblCardTotalPagadoValue.setText("S/. " + String.format("%.2f", r.getTotalPagadoEsteMes()));

            List<Deuda> deudas = service.listarDeudas(idUsuario);
            int size = deudas.size();

            pnlDeudasContenedor.removeAll();

            for (Deuda d : deudas) {
                // Calculate next due date
                java.util.Calendar dueCal = java.util.Calendar.getInstance();
                if (d.getFechaInicio() != null) {
                    dueCal.setTime(d.getFechaInicio());
                }
                dueCal.add(java.util.Calendar.MONTH, d.getCuotasPagadas());
                dueCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
                dueCal.set(java.util.Calendar.MINUTE, 0);
                dueCal.set(java.util.Calendar.SECOND, 0);
                dueCal.set(java.util.Calendar.MILLISECOND, 0);
                long dueMs = dueCal.getTimeInMillis();
                long diffDays = (dueMs - todayMs) / (1000 * 60 * 60 * 24);

                int day = dueCal.get(java.util.Calendar.DAY_OF_MONTH);
                int month = dueCal.get(java.util.Calendar.MONTH);
                String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", 
                                  "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
                String nombreMes = meses[month];

                String estadoStr = "";
                String badgeStyle = "";
                String warningText = "";
                java.awt.Color progressColor = new java.awt.Color(16, 185, 129); // Green for AL DIA
                java.awt.Color cardBg = java.awt.Color.WHITE;
                java.awt.Color cardBorder = new java.awt.Color(234, 234, 241);
                java.awt.Font textFont = new java.awt.Font("Dialog", java.awt.Font.PLAIN, 9);
                java.awt.Color textColor = new java.awt.Color(71, 85, 105);

                int cuotaSiguiente = d.getCuotasPagadas() + 1;

                if (diffDays < 0) {
                    estadoStr = "VENCIDO";
                    badgeStyle = "padding: 2px 8px; background-color: #fee2e2; color: #991b1b; border-radius: 8px; font-weight: bold;";
                    warningText = "Venció el " + day + " de " + nombreMes + " (" + cuotaSiguiente + " de " + d.getCuotasTotales() + ")";
                    progressColor = new java.awt.Color(239, 68, 68); // Red
                    cardBg = new java.awt.Color(254, 242, 242); // Light red (#fef2f2)
                    cardBorder = new java.awt.Color(252, 165, 165); // Red border (#fca5a5)
                    textFont = new java.awt.Font("Dialog", java.awt.Font.BOLD, 10);
                    textColor = new java.awt.Color(153, 27, 27); // Red (#991b1b)
                } else if (diffDays == 0) {
                    estadoStr = "ULTIMO DIA";
                    badgeStyle = "padding: 2px 8px; background-color: #fee2e2; color: #991b1b; border-radius: 8px; font-weight: bold;";
                    warningText = "Último día de pago (Cuota " + cuotaSiguiente + " de " + d.getCuotasTotales() + ")";
                    progressColor = new java.awt.Color(239, 68, 68); // Red
                    cardBg = new java.awt.Color(254, 242, 242); // Light red (#fef2f2)
                    cardBorder = new java.awt.Color(252, 165, 165); // Red border (#fca5a5)
                    textFont = new java.awt.Font("Dialog", java.awt.Font.BOLD, 10);
                    textColor = new java.awt.Color(153, 27, 27); // Red (#991b1b)
                } else if (diffDays <= 3) {
                    estadoStr = "PRÓXIMO A VENCER";
                    badgeStyle = "padding: 2px 8px; background-color: #ffedd5; color: #c2410c; border-radius: 8px; font-weight: bold;";
                    warningText = "Vence el " + day + " de " + nombreMes + " (" + cuotaSiguiente + " de " + d.getCuotasTotales() + ")";
                    progressColor = new java.awt.Color(249, 115, 22); // Orange
                } else if (diffDays <= 10) {
                    estadoStr = "PENDIENTE";
                    badgeStyle = "padding: 2px 8px; background-color: #dbeafe; color: #1e40af; border-radius: 8px; font-weight: bold;";
                    warningText = "Vence el " + day + " de " + nombreMes + " (" + cuotaSiguiente + " de " + d.getCuotasTotales() + ")";
                    progressColor = new java.awt.Color(37, 99, 235); // Blue
                } else {
                    estadoStr = "AL DIA";
                    badgeStyle = "padding: 2px 8px; background-color: #d1fae5; color: #065f46; border-radius: 8px; font-weight: bold;";
                    warningText = "Vence el " + day + " de " + nombreMes + " (" + cuotaSiguiente + " de " + d.getCuotasTotales() + ")";
                    progressColor = new java.awt.Color(16, 185, 129); // Green
                }

                // Create Card JPanel (100% width, conditional background and border)
                javax.swing.JPanel card = new javax.swing.JPanel();
                card.setLayout(null);
                card.setPreferredSize(new java.awt.Dimension(630, 75));
                card.setMaximumSize(new java.awt.Dimension(630, 75));
                card.setMinimumSize(new java.awt.Dimension(630, 75));
                card.setBackground(cardBg);
                card.setBorder(new javax.swing.border.LineBorder(cardBorder, 1, true));

                // Title
                javax.swing.JLabel lblTitle = new javax.swing.JLabel(d.getAcreedor());
                lblTitle.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
                lblTitle.setForeground(new java.awt.Color(11, 28, 48));
                lblTitle.setBounds(12, 8, 300, 16);
                card.add(lblTitle);

                // Description
                javax.swing.JLabel lblDesc = new javax.swing.JLabel("Monto Inicial: S/. " + String.format("%.2f", d.getMontoTotal()) + " | Tasa: " + d.getTasaInteres() + "%");
                lblDesc.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
                lblDesc.setForeground(new java.awt.Color(100, 116, 139));
                lblDesc.setBounds(12, 24, 300, 14);
                card.add(lblDesc);

                // Warning Text (RED and BOLD only for VENCIDO/ULTIMO DIA)
                javax.swing.JLabel lblProg = new javax.swing.JLabel(warningText);
                lblProg.setFont(textFont);
                lblProg.setForeground(textColor);
                lblProg.setBounds(12, 38, 320, 14);
                card.add(lblProg);

                // Progress Bar
                javax.swing.JProgressBar pb = new javax.swing.JProgressBar();
                int pct = (d.getMontoTotal() > 0) ? (int) ((d.getMontoPagado() * 100.0) / d.getMontoTotal()) : 0;
                pb.setValue(Math.min(pct, 100));
                pb.setBorderPainted(false);
                pb.setBackground(new java.awt.Color(226, 232, 240));
                pb.setForeground(progressColor);
                pb.setBounds(12, 54, 320, 6);
                card.add(pb);

                // Percent
                javax.swing.JLabel lblPct = new javax.swing.JLabel(pct + "%");
                lblPct.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
                lblPct.setForeground(new java.awt.Color(11, 28, 48));
                lblPct.setBounds(340, 38, 40, 14);
                card.add(lblPct);

                // Badge (Dynamic Alert Box text: AL DIA, PENDIENTE, ULTIMO DIA, VENCIDO)
                javax.swing.JLabel lblBdg = new javax.swing.JLabel("<html><body style='" + badgeStyle + "'>" + estadoStr + "</body></html>");
                lblBdg.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
                lblBdg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblBdg.setBounds(350, 10, 130, 18);
                card.add(lblBdg);

                // Saldo Pendiente Title
                javax.swing.JLabel lblSalT = new javax.swing.JLabel("Saldo Pendiente");
                lblSalT.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 9));
                lblSalT.setForeground(new java.awt.Color(100, 116, 139));
                lblSalT.setBounds(495, 10, 95, 14);
                card.add(lblSalT);

                // Saldo Pendiente Value
                javax.swing.JLabel lblSalV = new javax.swing.JLabel("S/. " + String.format("%.2f", d.getMontoRestante()));
                lblSalV.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
                lblSalV.setForeground(new java.awt.Color(11, 28, 48));
                lblSalV.setBounds(495, 24, 95, 16);
                card.add(lblSalV);

                // Pagar Ahora Button/Link (Only show for VENCIDO and ULTIMO DIA statuses)
                if (diffDays <= 0) {
                    javax.swing.JLabel lblPay = new javax.swing.JLabel("Pagar Ahora");
                    lblPay.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 10));
                    lblPay.setForeground(new java.awt.Color(37, 99, 235));
                    lblPay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    lblPay.setBounds(495, 45, 95, 16);
                    lblPay.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            prepararPagoRapidoParaDeuda(d);
                        }
                    });
                    card.add(lblPay);
                }

                pnlDeudasContenedor.add(card);
                pnlDeudasContenedor.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));
            }

            view.lblDeudasActivasSubtitle.setText(size + (size == 1 ? " Deuda en curso" : " Deudas en curso"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupContenedorDeudas() {
        // Remove old static cards
        view.pnlDeudasActivas.remove(view.pnlDebt1);
        view.pnlDeudasActivas.remove(view.pnlDebt2);
        view.pnlDeudasActivas.remove(view.pnlDebt3);

        pnlDeudasContenedor = new javax.swing.JPanel();
        pnlDeudasContenedor.setLayout(new javax.swing.BoxLayout(pnlDeudasContenedor, javax.swing.BoxLayout.Y_AXIS));
        pnlDeudasContenedor.setBackground(java.awt.Color.WHITE);

        javax.swing.JScrollPane scrollDeudas = new javax.swing.JScrollPane(pnlDeudasContenedor);
        scrollDeudas.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDeudas.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollDeudas.setBorder(null);
        scrollDeudas.setBackground(java.awt.Color.WHITE);
        scrollDeudas.getViewport().setBackground(java.awt.Color.WHITE);

        // Place JScrollPane at the exact coordinates of the old static cards
        view.pnlDeudasActivas.add(scrollDeudas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 630, 210));

        view.pnlDeudasActivas.revalidate();
        view.pnlDeudasActivas.repaint();
    }

    public void prepararPagoRapidoParaDeuda(Deuda d) {
        try {
            int idDeuda = d.getIdDeuda();
            // Seleccionar deuda en el combo box
            for (int i = 0; i < view.cbSeleccionarDeuda.getItemCount(); i++) {
                Object item = view.cbSeleccionarDeuda.getItemAt(i);
                if (item instanceof ComboItem) {
                    ComboItem ci = (ComboItem) item;
                    if (ci.getValue() == idDeuda) {
                        view.cbSeleccionarDeuda.setSelectedIndex(i);
                        break;
                    }
                }
            }

            // Obtener último abono registrado para esta deuda si existe
            double cuota = service.obtenerUltimoAbono(idDeuda);
            if (cuota <= 0) {
                if (d.getCuotasTotales() > 0) {
                    cuota = d.getMontoTotal() / d.getCuotasTotales();
                }
            }
            if (cuota > d.getMontoRestante() || cuota <= 0) {
                cuota = d.getMontoRestante();
            }

            view.txtMontoAbono.setText(String.format(java.util.Locale.US, "%.2f", cuota));
            view.txtMontoAbono.requestFocus();
            view.txtMontoAbono.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getEstadoBadge(String estado) {
        if (estado == null) return "";
        if (estado.equalsIgnoreCase("AL DÍA")) {
            return "<html><body style='padding: 2px 8px; background-color: #d1fae5; color: #065f46; border-radius: 8px; font-weight: bold;'>AL DÍA</body></html>";
        } else if (estado.equalsIgnoreCase("PRÓXIMO A VENCER")) {
            return "<html><body style='padding: 2px 8px; background-color: #fee2e2; color: #991b1b; border-radius: 8px; font-weight: bold;'>PRÓXIMO A VENCER</body></html>";
        } else if (estado.equalsIgnoreCase("PENDIENTE")) {
            return "<html><body style='padding: 2px 8px; background-color: #dbeafe; color: #1e40af; border-radius: 8px; font-weight: bold;'>PENDIENTE</body></html>";
        } else {
            return "<html><body style='padding: 2px 8px; background-color: #f1f5f9; color: #475569; border-radius: 8px; font-weight: bold;'>" + estado + "</body></html>";
        }
    }

    public void guardarDeuda() {
        try {
            String acreedor = view.txtAcreedor.getText().trim();
            if (acreedor.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe ingresar el acreedor.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String montoStr = view.txtMontoTotal.getText().trim();
            String tasaStr = view.txtTasaInteres.getText().trim();
            String cuotasStr = view.txtCuotasTotales.getText().trim();

            if (montoStr.isEmpty() || tasaStr.isEmpty() || cuotasStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe completar todos los campos del formulario.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto debe ser mayor que 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double tasa = Double.parseDouble(tasaStr);
            int cuotas = Integer.parseInt(cuotasStr);
            if (cuotas <= 0) {
                JOptionPane.showMessageDialog(view, "Las cuotas totales deben ser mayores que 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date fecha = view.dateFechaInicio.getDate();
            if (fecha == null) {
                fecha = new Date();
            }

            Deuda d = new Deuda();
            d.setAcreedor(acreedor);
            d.setMontoTotal(monto);
            d.setTasaInteres(tasa);
            d.setCuotasTotales(cuotas);
            d.setFechaInicio(fecha);

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            d.setFechaInicioFormat(sdf.format(fecha));

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            d.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);

            String msg = service.guardar(d);
            JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormularioDeuda();
            listarDeudasCombo();
            listarHistorialAbonos();
            cargarResumenYDeudas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor verifique que los campos de Monto, Tasa y Cuotas sean numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al registrar deuda.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrarAbono() {
        try {
            ComboItem selectedDebt = (ComboItem) view.cbSeleccionarDeuda.getSelectedItem();
            if (selectedDebt == null) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar una deuda para abonar.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String abonoStr = view.txtMontoAbono.getText().trim();
            if (abonoStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe ingresar el monto del abono.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double monto = Double.parseDouble(abonoStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto de abono debe ser mayor que 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date fecha = view.dateFechaPago.getDate();
            if (fecha == null) {
                fecha = new Date();
            }

            AbonoDeuda abono = new AbonoDeuda();
            abono.setIdDeuda(selectedDebt.getValue());
            abono.setMontoAbono(monto);
            abono.setFechaPago(fecha);

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            abono.setFechaPagoFormat(sdf.format(fecha));

            String msg = service.registrarAbono(abono);
            JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormularioAbono();
            listarHistorialAbonos();
            cargarResumenYDeudas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Monto de abono ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al registrar abono.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportarCSV() {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new java.util.Date());
            String defaultName = "Deudas_y_Prestamos_" + timestamp + ".xlsx";

            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Deudas");
            fileChooser.setSelectedFile(new java.io.File(defaultName));
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Excel (*.xlsx)", "xlsx"));

            int userSelection = fileChooser.showSaveDialog(view);
            if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    fileToSave = new java.io.File(filePath + ".xlsx");
                }
                service.exportarExcel(view.tblHistorial, fileToSave);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al abrir el selector de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarFormularioDeuda() {
        view.txtAcreedor.setText("");
        view.txtMontoTotal.setText("");
        view.txtTasaInteres.setText("");
        view.txtCuotasTotales.setText("");
        view.dateFechaInicio.setDate(new Date());
    }

    public void limpiarFormularioAbono() {
        view.txtMontoAbono.setText("");
        view.dateFechaPago.setDate(new Date());
        if (view.cbSeleccionarDeuda.getItemCount() > 0) {
            view.cbSeleccionarDeuda.setSelectedIndex(0);
        }
        view.txtMontoAbono.setText(""); // Forzar a vacío después de disparar el action listener
    }

    private void setupListeners() {
        // cbSeleccionarDeuda index change listener to autocomplete txtMontoAbono
        for (java.awt.event.ActionListener al : view.cbSeleccionarDeuda.getActionListeners()) {
            view.cbSeleccionarDeuda.removeActionListener(al);
        }
        view.cbSeleccionarDeuda.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object item = view.cbSeleccionarDeuda.getSelectedItem();
                if (item instanceof ComboItem) {
                    ComboItem selected = (ComboItem) item;
                    int idDeuda = selected.getValue();
                    double ultimoAbono = service.obtenerUltimoAbono(idDeuda);
                    view.txtMontoAbono.setText(String.format(java.util.Locale.US, "%.2f", ultimoAbono));
                }
            }
        });

        // Configurar cursor y click listener para Exportar CSV
        view.lblExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        for (java.awt.event.MouseListener ml : view.lblExportarCSV.getMouseListeners()) {
            view.lblExportarCSV.removeMouseListener(ml);
        }
        view.lblExportarCSV.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportarCSV();
            }
        });
    }

    public void mostrarDetalleDeudaTotal() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;
            List<Deuda> deudas = service.listarDeudas(idUsuario);

            if (deudas.isEmpty()) {
                JOptionPane.showMessageDialog(view, "No tiene deudas activas actualmente.", "Detalle de Deuda Total", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("<html><body style='font-family: Arial, sans-serif; width: 320px;'>");
            sb.append("<h2 style='color: #0b1c30; margin: 0 0 8px 0;'>Desglose de Deuda Total</h2>");
            sb.append("<p style='color: #64748b; margin: 0 0 12px 0;'>A continuación se detallan sus deudas activas:</p>");
            sb.append("<table style='width: 100%; border-collapse: collapse;'>");
            sb.append("<tr style='background-color: #f1f5f9;'><th style='text-align: left; padding: 6px; border-bottom: 2px solid #cbd5e1;'>Acreedor</th><th style='text-align: right; padding: 6px; border-bottom: 2px solid #cbd5e1;'>Saldo Pendiente</th></tr>");
            
            double total = 0;
            for (Deuda d : deudas) {
                sb.append("<tr>");
                sb.append("<td style='padding: 6px; border-bottom: 1px solid #e2e8f0;'>").append(d.getAcreedor()).append("</td>");
                sb.append("<td style='padding: 6px; border-bottom: 1px solid #e2e8f0; text-align: right; font-weight: bold;'>S/. ").append(String.format("%.2f", d.getMontoRestante())).append("</td>");
                sb.append("</tr>");
                total += d.getMontoRestante();
            }
            
            sb.append("<tr style='background-color: #f8fafc;'>");
            sb.append("<td style='padding: 6px; font-weight: bold;'>Total</td>");
            sb.append("<td style='padding: 6px; text-align: right; font-weight: bold; color: #ba1a1a;'>S/. ").append(String.format("%.2f", total)).append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</body></html>");

            JOptionPane.showMessageDialog(view, sb.toString(), "Resumen de Deudas", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al cargar el desglose de deudas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
