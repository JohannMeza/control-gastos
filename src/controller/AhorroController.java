package controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Ahorro;
import model.MetaAhorro;
import model.MetasResumen;
import model.Session;
import model.Usuario;
import service.AhorroService;
import view.AhorrosView;
import view.NuevaMetaDialog;

public final class AhorroController {
    private AhorrosView view;
    private AhorroService service;
    private javax.swing.JPanel pnlMetasContenedor;
    private List<MetaAhorro> currentMetas;

    public AhorroController(AhorrosView view) {
        this.view = view;
        this.service = new AhorroService();

        setupContenedorMetas();
        listarMetas();
        listarAhorros();
        cargarResumenYMetas();
    }

    public void listarMetas() {
        try {
            view.cbMetaFondo.removeAllItems();
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            // Pre-load currentMetas to have it available for selection action listeners
            this.currentMetas = service.obtenerMetasDetalle(idUsuario);

            List<ComboItem> metas = service.listarMetas(idUsuario);
            for (ComboItem item : metas) {
                view.cbMetaFondo.addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarAhorros() {
        try {
            // Default to user 1 if session is null
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            List<Ahorro> lista = service.listarAhorros(idUsuario);
            DefaultTableModel modelo = (DefaultTableModel) view.tblAhorros.getModel();
            modelo.setRowCount(0);

            for (Ahorro a : lista) {
                modelo.addRow(new Object[]{
                    util.DateUtil.formatLongDate(a.getFechaAhorroFormat()),
                    a.getNombreMeta(),
                    "S/. " + String.format("%.2f", a.getMonto()),
                    a.getNombreUsuario(),
                    a.getIdAhorro() // Store ID in Action/hidden col
                });
            }
            
            view.lblMostrarTransacciones.setText("Mostrando " + lista.size() + " de " + lista.size() + " transacciones");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupContenedorMetas() {
        // Configure dynamic click listener for "Nueva Meta"
        view.lblVerTodasMetas.setText("+ Nueva Meta");
        
        // Remove existing mouse listeners to avoid duplicate bindings
        for (java.awt.event.MouseListener ml : view.lblVerTodasMetas.getMouseListeners()) {
            view.lblVerTodasMetas.removeMouseListener(ml);
        }
        
        view.lblVerTodasMetas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirDialogoNuevaMeta();
            }
        });

        // Add action listener to cbMetaFondo to auto-update txtMonto on selection
        for (java.awt.event.ActionListener al : view.cbMetaFondo.getActionListeners()) {
            view.cbMetaFondo.removeActionListener(al);
        }
        view.cbMetaFondo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboItem selected = (ComboItem) view.cbMetaFondo.getSelectedItem();
                if (selected != null && currentMetas != null) {
                    for (MetaAhorro m : currentMetas) {
                        if (m.getIdMeta() == selected.getValue()) {
                            view.txtMonto.setText(String.format("%.2f", m.getAsignacionMensual()));
                            break;
                        }
                    }
                }
            }
        });

        // Remove static hardcoded panels
        view.pnlMetasAhorro.remove(view.pnlMetaFondoEmergencia);
        view.pnlMetasAhorro.remove(view.pnlMetaVacaciones);
        view.pnlMetasAhorro.remove(view.pnlMetaAuto);

        // Create the scrollable container panel for dynamic cards
        pnlMetasContenedor = new javax.swing.JPanel();
        pnlMetasContenedor.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
        pnlMetasContenedor.setBackground(java.awt.Color.WHITE);

        javax.swing.JScrollPane scrollMetas = new javax.swing.JScrollPane(pnlMetasContenedor);
        scrollMetas.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollMetas.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollMetas.setBorder(null);
        scrollMetas.setBackground(java.awt.Color.WHITE);
        scrollMetas.getViewport().setBackground(java.awt.Color.WHITE);

        // Place JScrollPane at the exact coordinates of the old static panel block
        view.pnlMetasAhorro.add(scrollMetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 38, 580, 105));

        view.pnlMetasAhorro.revalidate();
        view.pnlMetasAhorro.repaint();
    }

    private void abrirDialogoNuevaMeta() {
        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(view);
        java.awt.Frame parentFrame = (win instanceof java.awt.Frame) ? (java.awt.Frame) win : null;
        NuevaMetaDialog dialog = new NuevaMetaDialog(parentFrame);
        dialog.setVisible(true);

        if (dialog.isGuardado()) {
            MetaAhorro meta = dialog.getNuevaMeta();
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            String msg = service.guardarMeta(meta, idUsuario);
            if (msg != null && msg.startsWith("ERROR:")) {
                JOptionPane.showMessageDialog(view, msg.replace("ERROR:", "").trim(), "Validación", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);
                // Reload UI
                listarMetas();
                cargarResumenYMetas();
            }
        }
    }

    public void cargarResumenYMetas() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;

            // 1. Resumen Cards
            MetasResumen r = service.obtenerResumen(idUsuario);
            view.lblCardTotalAhorradoValue.setText("S/. " + String.format("%.2f", r.getTotalAhorrado()));
            view.lblCardMetaMensualValue.setText("S/. " + String.format("%.2f", r.getMetaMensualTarget()));
            
            double ahorradoMes = r.getAhorradoEsteMes();
            double targetMes = r.getMetaMensualTarget();
            int progressMes = (targetMes > 0) ? (int) ((ahorradoMes * 100.0) / targetMes) : 0;
            view.pbMetaMensual.setValue(Math.min(progressMes, 100));
            view.lblCardMetaMensualDesc.setText("S/. " + String.format("%.2f", ahorradoMes) + " ahorrados hoy");

            // Metas globales eficiencia
            view.lblEficienciaValue.setText(progressMes + "%");
            view.pbMetasGlobales.setValue(Math.min(progressMes, 100));

            // 2. Metas Detalle (dynamic rendering of cards in pnlMetasContenedor)
            List<MetaAhorro> metas = service.obtenerMetasDetalle(idUsuario);
            
            // Render specific properties on the static top-left panel (e.g. for "Fondo Emergencia" if it exists)
            for (MetaAhorro m : metas) {
                if (m.getDescripcion().equalsIgnoreCase("Fondo Emergencia")) {
                    int percent = (m.getMontoObjetivo() > 0) ? (int) ((m.getMontoActual() * 100.0) / m.getMontoObjetivo()) : 0;
                    String progressText = "S/. " + String.format("%.0f", m.getMontoActual()) + " / S/. " + String.format("%.0f", m.getMontoObjetivo());
                    view.pbFondoEmergencia.setValue(Math.min(percent, 100));
                    view.lblFondoEmergenciaPercent.setText(percent + "%");
                    view.lblFondoEmergenciaProgressText.setText(progressText);
                    view.pbFondoEmergenciaMeta.setValue(Math.min(percent, 100));
                    break;
                }
            }

            pnlMetasContenedor.removeAll();

            for (MetaAhorro m : metas) {
                int percent = (m.getMontoObjetivo() > 0) ? (int) ((m.getMontoActual() * 100.0) / m.getMontoObjetivo()) : 0;
                String progressText = "S/. " + String.format("%.0f", m.getMontoActual()) + " / S/. " + String.format("%.0f", m.getMontoObjetivo());
                
                // Calculate projection
                double faltante = m.getMontoObjetivo() - m.getMontoActual();
                String proyeccionText = "Sin asignación";
                if (m.getAsignacionMensual() > 0) {
                    double mesesEstimados = Math.ceil(faltante / m.getAsignacionMensual());
                    if (mesesEstimados <= 0) {
                        proyeccionText = "Meta cumplida";
                    } else {
                        int anios = (int) (mesesEstimados / 12);
                        int meses = (int) (mesesEstimados % 12);
                        if (anios > 0) {
                            proyeccionText = "Meta en " + anios + (anios == 1 ? " año" : " años");
                            if (meses > 0) {
                                proyeccionText += " y " + meses + (meses == 1 ? " mes" : " meses");
                            }
                        } else {
                            proyeccionText = "Meta en " + meses + (meses == 1 ? " mes" : " meses");
                        }
                    }
                }

                // Build card panel
                javax.swing.JPanel card = new javax.swing.JPanel();
                card.setPreferredSize(new java.awt.Dimension(180, 95));
                card.setBackground(new java.awt.Color(248, 250, 252));
                card.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 241, 255), 1, true));
                card.setLayout(null);

                // Goal Name
                javax.swing.JLabel lblName = new javax.swing.JLabel(m.getDescripcion());
                lblName.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 11));
                lblName.setForeground(new java.awt.Color(11, 28, 48));
                lblName.setBounds(12, 10, 120, 16);
                card.add(lblName);

                // Percent Label
                javax.swing.JLabel lblPercent = new javax.swing.JLabel(percent + "%");
                lblPercent.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
                lblPercent.setForeground(new java.awt.Color(71, 85, 105));
                lblPercent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblPercent.setBounds(135, 10, 35, 16);
                card.add(lblPercent);

                // Progress Subtext
                javax.swing.JLabel lblProg = new javax.swing.JLabel(progressText);
                lblProg.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
                lblProg.setForeground(new java.awt.Color(71, 85, 105));
                lblProg.setBounds(12, 32, 155, 16);
                card.add(lblProg);

                // Progress Bar
                javax.swing.JProgressBar pb = new javax.swing.JProgressBar();
                pb.setBackground(new java.awt.Color(226, 232, 240));
                if (percent >= 100) {
                    pb.setForeground(new java.awt.Color(34, 197, 94)); // Emerald green
                } else {
                    pb.setForeground(new java.awt.Color(37, 99, 235)); // Royal blue
                }
                pb.setValue(Math.min(percent, 100));
                pb.setBorderPainted(false);
                pb.setBounds(12, 54, 155, 4);
                card.add(pb);

                // Monthly allocation and Time projection Label
                javax.swing.JLabel lblEst = new javax.swing.JLabel(proyeccionText + " (" + String.format("%.0f", m.getAsignacionMensual()) + "/m)");
                lblEst.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 9));
                lblEst.setForeground(new java.awt.Color(100, 116, 139));
                lblEst.setBounds(12, 65, 155, 16);
                card.add(lblEst);

                pnlMetasContenedor.add(card);
            }

            pnlMetasContenedor.revalidate();
            pnlMetasContenedor.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        try {
            ComboItem meta = (ComboItem) view.cbMetaFondo.getSelectedItem();
            if (meta == null) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar una meta válida.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double monto = Double.parseDouble(view.txtMonto.getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto debe ser mayor que 0.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Ahorro a = new Ahorro();
            a.setIdMeta(meta.getValue());
            a.setMonto(monto);
            a.setDescripcion(view.txtDescripcion.getText());
            
            Date fecha = view.dateFecha.getDate();
            if (fecha == null) {
                fecha = new Date();
            }
            a.setFechaAhorro(fecha);
            
            // Format date for XML
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            a.setFechaAhorroFormat(sdf.format(fecha));

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            a.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);

            String msg = service.guardar(a);
            JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            listarAhorros();
            cargarResumenYMetas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Monto ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al guardar ahorro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizar(int idAhorro) {
        try {
            ComboItem meta = (ComboItem) view.cbMetaFondo.getSelectedItem();
            if (meta == null) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar una meta válida.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double monto = Double.parseDouble(view.txtMonto.getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto debe ser mayor que 0.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Ahorro a = new Ahorro();
            a.setIdAhorro(idAhorro);
            a.setIdMeta(meta.getValue());
            a.setMonto(monto);
            a.setDescripcion(view.txtDescripcion.getText());
            
            Date fecha = view.dateFecha.getDate();
            if (fecha == null) {
                fecha = new Date();
            }
            a.setFechaAhorro(fecha);
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            a.setFechaAhorroFormat(sdf.format(fecha));

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            a.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);

            String msg = service.actualizar(a);
            JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            listarAhorros();
            cargarResumenYMetas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Monto ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al actualizar ahorro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminar(int idAhorro) {
        try {
            int confirm = JOptionPane.showConfirmDialog(view, "¿Está seguro de eliminar este registro de ahorro?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String msg = service.eliminar(idAhorro);
                JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);
                listarAhorros();
                cargarResumenYMetas();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al eliminar ahorro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void obtenerAhorroSeleccionado(int idAhorro) {
        try {
            Ahorro a = service.obtenerPorId(idAhorro);
            if (a != null) {
                view.txtMonto.setText(String.valueOf(a.getMonto()));
                view.txtDescripcion.setText(a.getDescripcion());
                view.dateFecha.setDate(a.getFechaAhorro());

                for (int i = 0; i < view.cbMetaFondo.getItemCount(); i++) {
                    ComboItem item = (ComboItem) view.cbMetaFondo.getItemAt(i);
                    if (item.getValue() == a.getIdMeta()) {
                        view.cbMetaFondo.setSelectedIndex(i);
                        break;
                    }
                }
                
                view.btnRegistrarAhorro.setText("Actualizar Ahorro");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportarCSV() {
        service.exportarCSV(view.tblAhorros);
    }

    public void limpiarFormulario() {
        view.txtMonto.setText("0.00");
        view.txtDescripcion.setText("");
        view.dateFecha.setDate(new Date());
        if (view.cbMetaFondo.getItemCount() > 0) {
            view.cbMetaFondo.setSelectedIndex(0);
        }
        view.btnRegistrarAhorro.setText("Registrar Ahorro");
    }
}
