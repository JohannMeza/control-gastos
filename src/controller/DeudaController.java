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

    public DeudaController(DeudasView view) {
        this.view = view;
        this.service = new DeudaService();

        listarDeudasCombo();
        listarHistorialAbonos();
        cargarResumenYDeudas();
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

            // 1. Resumen Cards
            DeudaResumen r = service.obtenerResumen(idUsuario);
            view.lblCardDeudaTotalValue.setText("S/. " + String.format("%.2f", r.getTotalDeuda()));
            view.lblCardProximoVencimientoValue.setText(util.DateUtil.formatLongDate(r.getProximoVencimiento()));
            view.lblCardTotalPagadoValue.setText("S/. " + String.format("%.2f", r.getTotalPagadoEsteMes()));

            // 2. Active Debt Progress Cards (Top 3)
            List<Deuda> deudas = service.listarDeudas(idUsuario);
            int size = deudas.size();

            // Card 1
            if (size > 0) {
                Deuda d1 = deudas.get(0);
                view.lblDebt1Title.setText(d1.getAcreedor());
                view.lblDebt1Desc.setText("Monto Inicial: S/. " + String.format("%.2f", d1.getMontoTotal()) + " | Tasa: " + d1.getTasaInteres() + "%");
                view.lblDebt1Badge.setText(getEstadoBadge(d1.getEstado()));
                
                int percent = (d1.getMontoTotal() > 0) ? (int) ((d1.getMontoPagado() * 100.0) / d1.getMontoTotal()) : 0;
                view.pbDebt1.setValue(Math.min(percent, 100));
                view.lblDebt1Percent.setText(percent + "%");
                view.lblDebt1ProgressText.setText("Progreso de Pago (" + d1.getCuotasPagadas() + " de " + d1.getCuotasTotales() + " cuotas)");
                view.lblDebt1SaldoValue.setText("S/. " + String.format("%.2f", d1.getMontoRestante()));
                view.pnlDebt1.setVisible(true);
            } else {
                view.pnlDebt1.setVisible(false);
            }

            // Card 2
            if (size > 1) {
                Deuda d2 = deudas.get(1);
                view.lblDebt2Title.setText(d2.getAcreedor());
                view.lblDebt2Desc.setText("Monto Inicial: S/. " + String.format("%.2f", d2.getMontoTotal()) + " | Tasa: " + d2.getTasaInteres() + "%");
                view.lblDebt2Badge.setText(getEstadoBadge(d2.getEstado()));
                
                // Card 2 matches the Visa Oro styling layout in DeudasView.
                view.lblDebt2WarningText.setText("Vence el 15 de este mes (Cuota " + (d2.getCuotasPagadas() + 1) + " de " + d2.getCuotasTotales() + ")");
                view.pnlDebt2.setVisible(true);
            } else {
                view.pnlDebt2.setVisible(false);
            }

            // Card 3
            if (size > 2) {
                Deuda d3 = deudas.get(2);
                view.lblDebt3Title.setText(d3.getAcreedor());
                view.lblDebt3Desc.setText("Monto Inicial: S/. " + String.format("%.2f", d3.getMontoTotal()) + " | Tasa: " + d3.getTasaInteres() + "%");
                view.lblDebt3Badge.setText(getEstadoBadge(d3.getEstado()));
                
                int percent = (d3.getMontoTotal() > 0) ? (int) ((d3.getMontoPagado() * 100.0) / d3.getMontoTotal()) : 0;
                view.pbDebt3.setValue(Math.min(percent, 100));
                view.lblDebt3Percent.setText(percent + "%");
                view.lblDebt3ProgressText.setText("Progreso de Pago (" + d3.getCuotasPagadas() + " de " + d3.getCuotasTotales() + " cuotas)");
                view.lblDebt3SaldoValue.setText("S/. " + String.format("%.2f", d3.getMontoRestante()));
                view.pnlDebt3.setVisible(true);
            } else {
                view.pnlDebt3.setVisible(false);
            }

            view.lblDeudasActivasSubtitle.setText(size + " deudas en curso");

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

            double monto = Double.parseDouble(view.txtMontoTotal.getText().trim());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto debe ser mayor que 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double tasa = Double.parseDouble(view.txtTasaInteres.getText().trim());
            int cuotas = Integer.parseInt(view.txtCuotasTotales.getText().trim());
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

            double monto = Double.parseDouble(view.txtMontoAbono.getText().trim());
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

    public void prepararPagoRapido() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;
            List<Deuda> deudas = service.listarDeudas(idUsuario);
            if (deudas.size() > 1) {
                Deuda d2 = deudas.get(1);
                int idDeuda = d2.getIdDeuda();

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

                // Calcular cuota sugerida
                double cuota = 0.0;
                if (d2.getCuotasTotales() > 0) {
                    cuota = d2.getMontoTotal() / d2.getCuotasTotales();
                }
                if (cuota > d2.getMontoRestante() || cuota <= 0) {
                    cuota = d2.getMontoRestante();
                }

                view.txtMontoAbono.setText(String.format(java.util.Locale.US, "%.2f", cuota));
                view.txtMontoAbono.requestFocus();
                view.txtMontoAbono.selectAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportarCSV() {
        service.exportarCSV(view.tblHistorial);
    }

    public void limpiarFormularioDeuda() {
        view.txtAcreedor.setText("");
        view.txtMontoTotal.setText("0.00");
        view.txtTasaInteres.setText("0.0");
        view.txtCuotasTotales.setText("12");
        view.dateFechaInicio.setDate(new Date());
    }

    public void limpiarFormularioAbono() {
        view.txtMontoAbono.setText("0.00");
        view.dateFechaPago.setDate(new Date());
        if (view.cbSeleccionarDeuda.getItemCount() > 0) {
            view.cbSeleccionarDeuda.setSelectedIndex(0);
        }
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
