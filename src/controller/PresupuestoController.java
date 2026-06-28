package controller;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Session;
import model.Usuario;
import model.UsuarioCompartido;
import service.PresupuestoService;
import service.UsuarioService;
import service.GastoService;
import view.PresupuestoView;
import model.Presupuesto;
import model.PresupuestoActividad;

public class PresupuestoController {
    private PresupuestoView view;
    private PresupuestoService service;
    
    // Filter variables
    private int idFiltradoUsuario = 0;
    private String selectionPeriod = util.DateUtil.getCurrentPeriodLabel();
    private int idCategoriaFiltro = 0;
    private int idEstadoFiltro = 0;

    public PresupuestoController (PresupuestoView view) {
        this.view = view;
        this.service = new PresupuestoService();
        
        listarCategorias();
        listarPeriodos();
        listarPresupuesto();
        cargarActividadesRecientes();
        setupListeners();
    }
    
    private void setupListeners() {
        // "Filtros" button click
        view.btnFiltrar.addActionListener(e -> {
            mostrarFiltrosAvanzados();
        });
    }
    
    public void listarPresupuesto() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuario = (activeUser != null) ? activeUser.getId() : 1;
            int idUsuarioOwner = idUsuario;

            // Resolve date range based on selected period dynamically
            String[] range = util.DateUtil.getPeriodDateRange(selectionPeriod);
            String fechaInicio = range[0];
            String fechaFin = range[1];

            // Fetch budgets
            List<Presupuesto> lista = service.listarPresupuesto(
                idUsuario, idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin, idCategoriaFiltro, idEstadoFiltro
            );

            // Populate table
            DefaultTableModel modelo = (DefaultTableModel) view.tblPresupuestos.getModel();
            modelo.setRowCount(0);

            double totalPresupuesto = 0;
            double totalGastado = 0;

            for (Presupuesto g : lista) {
                modelo.addRow(new Object[]{
                    g.getIdPresupuesto(),
                    g.getCategoria(),
                    g.getMontoAsignado(),
                    g.getMontoGastado(),
                    g.getEstadoPresupuesto(),
                    g.getProgresoPresupuesto(),
                });
                totalPresupuesto += g.getMontoAsignado();
                totalGastado += g.getMontoGastado();
            }

            // Compute remaining budget
            double restante = totalPresupuesto - totalGastado;

            // Set formatted text in Soles (S/.)
            view.jLabel21.setText("S/. " + String.format("%.2f", totalPresupuesto));
            view.jLabel23.setText("S/. " + String.format("%.2f", totalGastado));
            view.jLabel34.setText("S/. " + String.format("%.2f", restante));

            // Mark Remaining in Red if negative, Green if positive
            if (restante < 0) {
                view.jLabel34.setForeground(new Color(186, 26, 26)); // Red
            } else {
                view.jLabel34.setForeground(new Color(0, 108, 73)); // Green
            }

            // Update sub-labels dynamically
            view.jLabel1.setText(selectionPeriod.toUpperCase());
            double pct = totalPresupuesto > 0 ? (totalGastado * 100.0 / totalPresupuesto) : 0.0;
            view.jLabel32.setText(String.format("%.1f%% de total usado", pct));
            view.jLabel31.setText(lista.size() + " presupuestos activos");

            // Calculate remaining days dynamically
            Calendar cal = Calendar.getInstance();
            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            int daysRemaining = maxDay - currentDay;
            view.jLabel36.setText(" Quedan " + daysRemaining + " días en el período");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al obtener el presupuesto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void cargarActividadesRecientes() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            // Get default/current month period range dynamically
            String[] defaultRange = util.DateUtil.getPeriodDateRange(util.DateUtil.getCurrentPeriodLabel());
            String fIni = defaultRange[0];
            String fFin = defaultRange[1];

            // Recent activities are always loaded without filter (idFiltradoUsuario = 0) and for current month
            List<PresupuestoActividad> activities = service.listarPresupuestoActividades(
                idUsuarioOwner, 0, fIni, fFin
            );

            // Re-initialize layout of jPanelStatus to AbsoluteLayout to override NetBeans GroupLayout
            view.jPanelStatus.removeAll();
            view.jPanelStatus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            view.jPanelStatus.add(view.lblRegistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 14, -1, -1));

            // Create scroll container
            javax.swing.JScrollPane actScrollPane = new javax.swing.JScrollPane();
            actScrollPane.setBorder(null);
            actScrollPane.setBackground(Color.WHITE);
            actScrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Set dynamic scrollbar policy
            if (activities.size() <= 1) {
                actScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            } else {
                actScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            }

            javax.swing.JPanel actContainer = new javax.swing.JPanel();
            actContainer.setBackground(Color.WHITE);
            actContainer.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

            int actCount = 0;
            for (PresupuestoActividad act : activities) {
                if (actCount >= 3) break;
                javax.swing.JPanel actCard = crearTarjetaActividad(act);
                actContainer.add(actCard);
                actCount++;
            }

            javax.swing.JPanel actWrapper = new javax.swing.JPanel(new java.awt.BorderLayout());
            actWrapper.setBackground(Color.WHITE);
            actWrapper.add(actContainer, java.awt.BorderLayout.NORTH);

            actScrollPane.setViewportView(actWrapper);
            view.jPanelStatus.add(actScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, 300, 85));

            view.jPanelStatus.revalidate();
            view.jPanelStatus.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private javax.swing.JPanel crearTarjetaActividad(PresupuestoActividad act) {
        javax.swing.JPanel card = new javax.swing.JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new javax.swing.border.MatteBorder(0, 0, 1, 0, new Color(234, 241, 255)));
        card.setPreferredSize(new java.awt.Dimension(280, 52));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Determine title, message, and color based on state
        String title = "Gasto Registrado";
        String message = "Se gastó S/. " + String.format("%.2f", act.getMontoGasto()) + " en " + act.getNombreCategoria();
        Color iconBg = new Color(37, 99, 235); // Blue by default

        double totalSpent = act.getTotalGastadoMes();
        double limit = act.getLimitePresupuesto();
        int threshold = act.getUmbralAlerta();

        if (totalSpent > limit) {
            title = "Presupuesto Excedido";
            double excess = totalSpent - limit;
            message = act.getNombreCategoria() + " superó el límite por S/. " + String.format("%.2f", excess);
            iconBg = new Color(186, 26, 26); // Red
        } else if (limit > 0 && (totalSpent * 100.0 / limit) >= threshold) {
            title = "Advertencia de Umbral";
            message = act.getNombreCategoria() + " alcanzó el " + threshold + "% del límite";
            iconBg = new Color(245, 158, 11); // Yellow/Orange
        }

        // Indicator Bullet
        javax.swing.JPanel pnlIndicator = new javax.swing.JPanel();
        pnlIndicator.setBackground(iconBg);
        card.add(pnlIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, 8, 8));

        // Title Label
        javax.swing.JLabel lblTitle = new javax.swing.JLabel(title);
        lblTitle.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 10));
        lblTitle.setForeground(new Color(11, 28, 48));
        card.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 2, 250, 14));

        // Message/Desc Label
        javax.swing.JLabel lblDesc = new javax.swing.JLabel("<html>" + message + "</html>");
        lblDesc.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 9));
        lblDesc.setForeground(new Color(71, 85, 105));
        card.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 16, 260, 15));

        // Date/Time Label
        javax.swing.JLabel lblDate = new javax.swing.JLabel("Fecha: " + util.DateUtil.formatLongDate(act.getcFechaGasto()));
        lblDate.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 8));
        lblDate.setForeground(new Color(148, 163, 184));
        card.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 32, 250, 12));

        return card;
    }

    private void mostrarFiltrosAvanzados() {
        javax.swing.JDialog dialog = new javax.swing.JDialog(view, "Filtros Avanzados", true);
        dialog.setLayout(new java.awt.GridLayout(5, 2, 10, 10));
        dialog.setSize(380, 240);
        dialog.setLocationRelativeTo(view);

        Usuario activeUser = Session.getInstance().getUsuarioActivo();
        int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

        // 1. Colaborador
        dialog.add(new javax.swing.JLabel("  Colaborador:"));
        javax.swing.JComboBox<ComboItem> cbColab = new javax.swing.JComboBox<>();
        cbColab.addItem(new ComboItem(0, "Todos los usuarios"));
        
        UsuarioService uService = new UsuarioService();
        Usuario owner = uService.obtenerUsuario(idUsuarioOwner);
        if (owner != null) {
            cbColab.addItem(new ComboItem(owner.getId(), owner.getNombre() + " (Yo)"));
        }
        try {
            List<UsuarioCompartido> listColab = uService.listarUsuariosCompartidos(idUsuarioOwner);
            for (UsuarioCompartido uc : listColab) {
                if (uc.getEstado().equalsIgnoreCase("Activo")) {
                    cbColab.addItem(new ComboItem(uc.getUsuarioInvitadoId(), uc.getNombreInvitado()));
                }
            }
        } catch (Exception e) {}
        
        for (int i = 0; i < cbColab.getItemCount(); i++) {
            if (cbColab.getItemAt(i).getValue() == idFiltradoUsuario) {
                cbColab.setSelectedIndex(i);
                break;
            }
        }
        dialog.add(cbColab);

        // 2. Periodo
        dialog.add(new javax.swing.JLabel("  Período:"));
        javax.swing.JComboBox<String> cbPeriod = new javax.swing.JComboBox<>();
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int currentYear = cal.get(java.util.Calendar.YEAR);
        int currentMonth = cal.get(java.util.Calendar.MONTH); // 0-indexed

        // 1. Agregar meses del año actual de mes actual a Enero
        for (int m = currentMonth; m >= 0; m--) {
            cbPeriod.addItem(util.DateUtil.MESES[m] + " " + currentYear);
        }

        // 2. Agregar "Todo el año"
        cbPeriod.addItem("Todo el año");

        // 3. Agregar años anteriores dinámicamente según BD
        try {
            List<Integer> anios = service.obtenerAniosConDatos(idUsuarioOwner);
            for (int anio : anios) {
                if (anio < currentYear) {
                    cbPeriod.addItem("Año " + anio);
                }
            }
        } catch (Exception e) {
            cbPeriod.addItem("Año " + (currentYear - 1));
            cbPeriod.addItem("Año " + (currentYear - 2));
        }
        cbPeriod.setSelectedItem(selectionPeriod);
        dialog.add(cbPeriod);

        // 3. Categoria
        dialog.add(new javax.swing.JLabel("  Categoría:"));
        javax.swing.JComboBox<ComboItem> cbCat = new javax.swing.JComboBox<>();
        cbCat.addItem(new ComboItem(0, "Todas las categorías"));
        try {
            List<ComboItem> cats = service.listarCategorias();
            cats.forEach(cbCat::addItem);
        } catch (Exception e) {}
        
        for (int i = 0; i < cbCat.getItemCount(); i++) {
            if (cbCat.getItemAt(i).getValue() == idCategoriaFiltro) {
                cbCat.setSelectedIndex(i);
                break;
            }
        }
        dialog.add(cbCat);

        // 4. Estado de Presupuesto
        dialog.add(new javax.swing.JLabel("  Estado de Presupuesto:"));
        javax.swing.JComboBox<ComboItem> cbEst = new javax.swing.JComboBox<>();
        cbEst.addItem(new ComboItem(0, "Todos los estados"));
        cbEst.addItem(new ComboItem(1, "En camino"));
        cbEst.addItem(new ComboItem(2, "Excedido"));
        cbEst.addItem(new ComboItem(3, "Bajo"));
        cbEst.addItem(new ComboItem(4, "Advertencia"));
        
        for (int i = 0; i < cbEst.getItemCount(); i++) {
            if (cbEst.getItemAt(i).getValue() == idEstadoFiltro) {
                cbEst.setSelectedIndex(i);
                break;
            }
        }
        dialog.add(cbEst);

        // 5. Buttons
        javax.swing.JButton btnClear = new javax.swing.JButton("Limpiar Filtros");
        btnClear.addActionListener(e -> {
            idFiltradoUsuario = 0;
            selectionPeriod = util.DateUtil.getCurrentPeriodLabel();
            idCategoriaFiltro = 0;
            idEstadoFiltro = 0;
            dialog.dispose();
            listarPresupuesto();
        });
        dialog.add(btnClear);

        javax.swing.JButton btnApply = new javax.swing.JButton("Aplicar Filtros");
        btnApply.addActionListener(e -> {
            idFiltradoUsuario = ((ComboItem) cbColab.getSelectedItem()).getValue();
            selectionPeriod = (String) cbPeriod.getSelectedItem();
            idCategoriaFiltro = ((ComboItem) cbCat.getSelectedItem()).getValue();
            idEstadoFiltro = ((ComboItem) cbEst.getSelectedItem()).getValue();
            dialog.dispose();
            listarPresupuesto();
        });
        dialog.add(btnApply);

        dialog.setVisible(true);
    }
    
    public void guardarPresupuesto() {
        try {
            ComboItem categoria = (ComboItem) view.cbCategoria.getSelectedItem();
            ComboItem periodo = (ComboItem) view.cbPeriodo.getSelectedItem();

            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setIdCategoria(categoria.getValue());
            presupuesto.setIdTipoPeriodo(periodo.getValue());
            presupuesto.setLimPresuMens(Double.parseDouble(view.txtPresupuestoMensual.getText()));
            presupuesto.setUmbralAlerta(Integer.parseInt(view.txtUmbral.getText()));
            
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            presupuesto.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);
            
            String msj = service.guardarPresupuesto(presupuesto);
            
            JOptionPane.showMessageDialog(
                view,
                msj,
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al guardar el presupuesto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void actualizarPresupuesto() {
        try {
            view.cbCategoria.removeAllItems();
            List<ComboItem> categorias = service.listarCategorias();
            categorias.forEach(item -> {
                view.cbCategoria.addItem(item);
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al actualizar el presupuesto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void listarCategorias() {
        try {
            view.cbCategoria.removeAllItems();
            List<ComboItem> categorias = service.listarCategorias();
            categorias.forEach(item -> {
                view.cbCategoria.addItem(item);
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al obtener las categorias",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void listarPeriodos() {
        try {
            view.cbPeriodo.removeAllItems();
            List<ComboItem> periodo = service.listarPeriodo();
            periodo.forEach(item -> {
                view.cbPeriodo.addItem(item);
            });
            
            if (view.cbPeriodo.getItemCount() > 0) {
                view.cbPeriodo.setSelectedIndex(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al obtener los períodos",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void eliminarPresupuestoSeleccionado() {
        try {
            int fila = view.tblPresupuestos.getSelectedRow();
            int idPresupuesto = Integer.parseInt(
                view.tblPresupuestos.getValueAt(fila, 0).toString()
            );

            String mensaje = service.eliminarPresupuesto(idPresupuesto);

            JOptionPane.showMessageDialog(
                view,
                mensaje,
                "Sistema",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al eliminar el presupuesto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

