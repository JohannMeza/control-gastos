/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Gasto;
import model.Presupuesto;
import model.Session;
import model.Usuario;
import model.UsuarioCompartido;
import service.GastoService;
import service.IGastoService;
import service.IUsuarioService;
import service.UsuarioService;
import view.SystemView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public final class GastoController {
    private SystemView view;
    private IGastoService service;
    private int paginaActual = 1;
    private final int filasPorPagina = 10;
    private List<Gasto> listaGastosActual = new java.util.ArrayList<>();
    
    public GastoController(SystemView view) {
        this(view, new GastoService());
    }

    public GastoController(SystemView view, IGastoService service) {
        this.view = view;
        this.service = service;
        
        listarCategorias();
        listarTipoGastos();
        listarUsuarios();

        if (view.getPaginationBar() != null) {
            view.getPaginationBar().addPaginationListener(pagina -> {
                this.paginaActual = pagina;
                mostrarPaginaGastos();
            });
        }

        // Restricción RBAC para rol LECTOR
        util.RbacUtil.aplicarRestriccionBotones(view.btnAgregarGasto);
    }
    
    public void exportarExcel() {
        String[] opciones = {"CSV (*.csv)", "Excel (*.xlsx)", "Cancelar"};
        int sel = javax.swing.JOptionPane.showOptionDialog(
            view,
            "Seleccione el formato para exportar el historial de gastos:",
            "Exportar Historial de Gastos",
            javax.swing.JOptionPane.DEFAULT_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        if (sel == 0) {
            util.ExportUtil.exportarTablaCSV(view, view.tblGastos, "Gastos_Historial");
        } else if (sel == 1) {
            util.ExportUtil.exportarTablaExcel(view, view.tblGastos, "Historial de Gastos", "Gastos_Historial");
        }
    }
    
    public void guardar() {
        if (!util.RbacUtil.verificarPermisoEscritura(view, "registrar nuevos gastos")) {
            return;
        }
        try {
            Object selected = view.cbCategoria.getSelectedItem();
            System.out.println(selected);
            System.out.println(selected.getClass());
            ComboItem categoria = (ComboItem) view.cbCategoria.getSelectedItem();
            ComboItem tipo = (ComboItem) view.cbTipoGastos.getSelectedItem();

            Gasto gasto = new Gasto();
            gasto.setMonto(Double.parseDouble(view.txtMonto.getText()));
            gasto.setIdCategoria(categoria.getValue());
            gasto.setIdTipoGasto(tipo.getValue());
            gasto.setDescripcion(view.txtDescripcion.getText());
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(view.dateFechaNuevo.getDate());
            gasto.setFechaGastoFormat(fechaFormateada);
            gasto.setFechaGasto(java.sql.Date.valueOf(fechaFormateada));
            
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            gasto.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);
            
            service.guardar(gasto);
            
        } catch (NumberFormatException e) {
            
        }
    }
    
    public void actualizar() {
        if (!util.RbacUtil.verificarPermisoEscritura(view, "actualizar gastos")) {
            return;
        }
        try {
            ComboItem categoria = (ComboItem) view.cbCategoria.getSelectedItem();
            ComboItem tipo = (ComboItem) view.cbTipoGastos.getSelectedItem();

            Gasto gasto = new Gasto();

            gasto.setIdGasto(view.idGastoSeleccionado);
            gasto.setMonto(Double.parseDouble(view.txtMonto.getText()));
            gasto.setIdCategoria(categoria.getValue());
            gasto.setIdTipoGasto(tipo.getValue());
            gasto.setDescripcion(view.txtDescripcion.getText());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(view.dateFechaNuevo.getDate());
            gasto.setFechaGastoFormat(fechaFormateada);
            gasto.setFechaGasto(java.sql.Date.valueOf(fechaFormateada));

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            gasto.setIdUsuario((activeUser != null) ? activeUser.getId() : 1);

            String mensaje = service.actualizar(gasto);

            JOptionPane.showMessageDialog(
                view,
                mensaje,
                "Sistema",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                view,
                "El monto ingresado no es válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                view,
                "Ocurrió un error al actualizar",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void listarGastos() {
        ComboItem cbUsuario = (ComboItem) view.cbUser.getSelectedItem();
        if (cbUsuario == null) return;
        Date fechaDesde = view.dateFiltroDesde.getDate();
        Date fechaHasta = view.dateFiltroHasta.getDate();
        
        Usuario activeUser = Session.getInstance().getUsuarioActivo();
        int loggedInUserId = (activeUser != null) ? activeUser.getId() : 1;
        
        int idUsuario = cbUsuario.getValue();
        if (idUsuario == 0) {
            idUsuario = loggedInUserId;
        }
        
        this.listaGastosActual = service.listar(fechaDesde, fechaHasta, idUsuario);
        this.paginaActual = 1;
        mostrarPaginaGastos();

        double totalExpenses = 0;
        for (Gasto g : listaGastosActual) {
            totalExpenses += g.getMonto();
        }

        // Compute Monthly Average
        double monthlyAverage = totalExpenses;
        if (fechaDesde != null && fechaHasta != null) {
            long diffInMillies = Math.abs(fechaHasta.getTime() - fechaDesde.getTime());
            long diffInDays = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            double months = diffInDays / 30.0;
            if (months > 0.05) {
                monthlyAverage = totalExpenses / months;
            }
        }

        // Set labels dynamically in Soles (S/.)
        view.jLabel21.setText("S/. " + String.format("%.2f", totalExpenses));
        view.jLabel25.setText("S/. " + String.format("%.2f", monthlyAverage));

        // Compute dynamic budget status
        double totalBudget = 0;
        try {
            activeUser = Session.getInstance().getUsuarioActivo();
            int idOwner = (activeUser != null) ? activeUser.getId() : 1;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String[] defaultRange = util.DateUtil.getPeriodDateRange(null);
            String fIni = (fechaDesde != null) ? sdf.format(fechaDesde) : defaultRange[0];
            String fFin = (fechaHasta != null) ? sdf.format(fechaHasta) : defaultRange[1];
            List<Presupuesto> budgets = new service.PresupuestoService().listarPresupuesto(idOwner, idOwner, idUsuario, fIni, fFin, 0, 0);
            for (Presupuesto p : budgets) {
                totalBudget += p.getMontoAsignado();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (totalBudget > 0 && totalExpenses > totalBudget) {
            view.jLabel27.setText("EXCEDIDO");
            view.jLabel27.setForeground(new java.awt.Color(186, 26, 26)); // Red
        } else {
            view.jLabel27.setText("DENTRO DEL PRESUPUESTO");
            view.jLabel27.setForeground(new java.awt.Color(0, 108, 73)); // Green
        }

        // Calculate dynamic Next Recurring payment date (the 28th of current or next month)
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        if (currentDay > 28) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_MONTH, 28);
        String nextRecurringDate = util.DateUtil.formatLongDate(cal.getTime());
        view.jLabel29.setText(nextRecurringDate);
    }

    public void mostrarPaginaGastos() {
        DefaultTableModel modelo = (DefaultTableModel) view.tblGastos.getModel();
        modelo.setRowCount(0);

        int totalRegistros = (listaGastosActual != null) ? listaGastosActual.size() : 0;

        if (totalRegistros == 0) {
            if (view.getPaginationBar() != null) {
                view.getPaginationBar().updatePagination(1, 0, filasPorPagina);
            }
            return;
        }

        int totalPaginas = (int) Math.ceil((double) totalRegistros / filasPorPagina);
        if (paginaActual > totalPaginas) paginaActual = totalPaginas;
        if (paginaActual < 1) paginaActual = 1;

        int inicio = (paginaActual - 1) * filasPorPagina;
        int fin = Math.min(inicio + filasPorPagina, totalRegistros);

        for (int i = inicio; i < fin; i++) {
            Gasto g = listaGastosActual.get(i);
            modelo.addRow(new Object[]{
                g.getIdGasto(),
                util.DateUtil.formatLongDate(g.getFechaGastoFormat()),
                g.getDescripcion(),
                g.getCategoria(),
                g.getTipoGasto(),
                g.getMonto(),
                g.getUsuario()
            });
        }

        if (view.getPaginationBar() != null) {
            view.getPaginationBar().updatePagination(paginaActual, totalRegistros, filasPorPagina);
        }
    }
    
    public void listarCategorias() {
        try {
            view.cbCategoria.removeAllItems();

            List<ComboItem> categorias = service.listarCategorias();

            categorias.forEach(item -> {
                view.cbCategoria.addItem(item);
            });

        } catch (Exception e) {}
    }
    
    public void listarTipoGastos() {
        try {
            view.cbTipoGastos.removeAllItems();

            List<ComboItem> tipoGastos = service.listarTipoGastos();

            tipoGastos.forEach(item -> {
                view.cbTipoGastos.addItem(item);
            });

        } catch (Exception e) {}
    }
    
    public void listarUsuarios() {
        try {
            view.cbUser.removeAllItems();

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int loggedInUserId = (activeUser != null) ? activeUser.getId() : 1;

            view.cbUser.addItem(new ComboItem(0, "Todos los usuarios"));

            IUsuarioService uService = new UsuarioService();
            Usuario owner = uService.obtenerUsuario(loggedInUserId);
            if (owner != null) {
                view.cbUser.addItem(new ComboItem(owner.getId(), owner.getNombre() + " (Yo)"));
            }

            List<UsuarioCompartido> list = uService.listarUsuariosCompartidos(loggedInUserId);
            for (UsuarioCompartido uc : list) {
                if (uc.getEstado().equalsIgnoreCase("Activo")) {
                    view.cbUser.addItem(new ComboItem(uc.getUsuarioInvitadoId(), uc.getNombreInvitado()));
                }
            }
            
            if (view.cbUser.getItemCount() > 1) {
                view.cbUser.setSelectedIndex(1); // Select "Yo" by default
            } else {
                view.cbUser.setSelectedIndex(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void obtenerGastoSeleccionado() {
        int fila = view.tblGastos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                view,
                "Aún no ha seleccionado ninguna fila",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idGasto = Integer.parseInt(
            view.tblGastos.getValueAt(fila, 0).toString()
        );

        Gasto gasto = service.obtenerPorId(idGasto);

        if (gasto == null) {
            JOptionPane.showMessageDialog(
                view,
                "No se encontró el gasto",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        view.lblRegistro.setText("EDITAR GASTO SELECCIONADO");
        view.btnAgregarGasto.setText("Actualizar Transacción");

        view.txtMonto.setText(String.valueOf(gasto.getMonto()));
        view.txtDescripcion.setText(gasto.getDescripcion());
        view.dateFechaNuevo.setDate(gasto.getFechaGasto());
        view.idGastoSeleccionado = gasto.getIdGasto();

        for (int i = 0; i < view.cbCategoria.getItemCount(); i++) {
            ComboItem item = view.cbCategoria.getItemAt(i);

            if (item.getValue() == gasto.getIdCategoria()) {
                view.cbCategoria.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < view.cbTipoGastos.getItemCount(); i++) {

            ComboItem item = view.cbTipoGastos.getItemAt(i);

            if (item.getValue() == gasto.getIdTipoGasto()) {
                view.cbTipoGastos.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public void eliminarGastoSeleccionado() {
        if (!util.RbacUtil.verificarPermisoEscritura(view, "eliminar gastos")) {
            return;
        }
        try {
            int fila = view.tblGastos.getSelectedRow();

            int idGasto = Integer.parseInt(
                view.tblGastos.getValueAt(fila, 0).toString()
            );

            String mensaje = service.eliminar(idGasto);

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
                "Ocurrió un error al eliminar el gasto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
