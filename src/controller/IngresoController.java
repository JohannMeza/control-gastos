package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;
import model.Session;
import model.Usuario;
import service.IIngresoService;
import service.IngresoService;
import view.IngresosView;
import view.components.ThemeColor;

public class IngresoController {

    private final IngresosView view;
    private final IIngresoService service;

    // Estado del controlador
    private int idIngresoSeleccionado = 0;
    private String periodoSeleccionado = "Oct 2023";
    private String fechaInicio = "2023-10-01";
    private String fechaFin = "2023-10-31";
    private String fechaInicioPrev = "2023-09-01";
    private String fechaFinPrev = "2023-09-30";
    private String filtroTabActual = "Todos";

    private List<Ingreso> listaIngresosActual = new ArrayList<>();
    private int paginaActual = 1;
    private final int filasPorPagina = 6;

    // Formateadores
    private final SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private final SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private final SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd MMM, yyyy", Locale.US);

    public IngresoController(IngresosView view) {
        this(view, new IngresoService());
    }

    public IngresoController(IngresosView view, IIngresoService service) {
        this.view = view;
        this.service = service;

        inicializarCombos();
        configurarListeners();
        inicializarFechaDefecto();
        cargarDatosCompletos();
    }

    private void inicializarFechaDefecto() {
        view.getTxtFecha().setText(sdfInput.format(new Date()));
    }

    private void inicializarCombos() {
        try {
            int idUsuario = getActiveUserId();

            // 1. Combo Categorías en Formulario
            view.getCbCategoria().removeAllItems();
            List<ComboItem> categorias = service.listarCategorias();
            if (categorias.isEmpty()) {
                view.getCbCategoria().addItem("Salario / Nómina Directa");
                view.getCbCategoria().addItem("Proyectos Freelance & Tech");
                view.getCbCategoria().addItem("Rendimientos & Dividendos");
                view.getCbCategoria().addItem("Servicios");
                view.getCbCategoria().addItem("Reembolsos");
            } else {
                for (ComboItem cat : categorias) {
                    view.getCbCategoria().addItem(cat.getLabel());
                }
            }

            // 2. Combo Cuentas Destino en Formulario
            view.getCbCuentaDestino().removeAllItems();
            List<ComboItem> cuentas = service.listarCuentasDestino(idUsuario);
            if (cuentas.isEmpty()) {
                view.getCbCuentaDestino().addItem("Cuenta Corriente BBVA (Nómina)");
                view.getCbCuentaDestino().addItem("Cuenta Dólares");
                view.getCbCuentaDestino().addItem("Santander");
                view.getCbCuentaDestino().addItem("Stripe / Banco");
                view.getCbCuentaDestino().addItem("BBVA");
            } else {
                for (ComboItem cta : cuentas) {
                    view.getCbCuentaDestino().addItem(cta.getLabel());
                }
            }

            // 3. Combos en Card de Filtrado Rápido
            view.getCbFiltroCategoria().removeAllItems();
            view.getCbFiltroCategoria().addItem("Todas las Categorías");
            for (int i = 0; i < view.getCbCategoria().getItemCount(); i++) {
                view.getCbFiltroCategoria().addItem(view.getCbCategoria().getItemAt(i));
            }

            view.getCbFiltroCuenta().removeAllItems();
            view.getCbFiltroCuenta().addItem("Todas las Cuentas");
            for (int i = 0; i < view.getCbCuentaDestino().getItemCount(); i++) {
                view.getCbFiltroCuenta().addItem(view.getCbCuentaDestino().getItemAt(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarListeners() {
        // Botón Registrar Ingreso
        view.getBtnRegistrarIngreso().addActionListener(e -> guardarOActualizar());

        // HeaderBar botón primario "+ Nuevo Ingreso"
        view.getHeaderBar().addAccionBotonListener(e -> {
            limpiarFormulario();
            view.getTxtMonto().requestFocus();
        });

        // Tabs de filtro: Todos, Fijos, Variables, Pendientes
        view.getBtnTabTodos().addActionListener(e -> setTabFiltro("Todos", view.getBtnTabTodos()));
        view.getBtnTabFijos().addActionListener(e -> setTabFiltro("Fijo", view.getBtnTabFijos()));
        view.getBtnTabVariables().addActionListener(e -> setTabFiltro("Variable", view.getBtnTabVariables()));
        view.getBtnTabPendientes().addActionListener(e -> setTabFiltro("Pendiente", view.getBtnTabPendientes()));

        // Botones de período
        view.getBtnFiltroOct().addActionListener(e -> setPeriodo("Oct 2023", "2023-10-01", "2023-10-31", "2023-09-01", "2023-09-30", view.getBtnFiltroOct()));
        view.getBtnFiltroSep().addActionListener(e -> setPeriodo("Sep 2023", "2023-09-01", "2023-09-30", "2023-08-01", "2023-08-31", view.getBtnFiltroSep()));
        view.getBtnFiltroAnual().addActionListener(e -> setPeriodo("Anual 2023", "2023-01-01", "2023-12-31", "2022-01-01", "2022-12-31", view.getBtnFiltroAnual()));
        view.getBtnAjustarVista().addActionListener(e -> ajustarVistaPersonalizada());

        // Filtros Rápidos
        view.getCbFiltroCategoria().addActionListener(e -> aplicarFiltrosRapidos());
        view.getCbFiltroCuenta().addActionListener(e -> aplicarFiltrosRapidos());
        view.getTxtRangoDesde().addActionListener(e -> aplicarFiltrosRapidos());
        view.getTxtRangoHasta().addActionListener(e -> aplicarFiltrosRapidos());

        // Reset filtros rápidos
        view.getLblResetFiltro().setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.getLblResetFiltro().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetearFiltros();
            }
        });

        // Exportar CSV
        view.getBtnExportarCsv().addActionListener(e -> exportarCSV());

        // Exportar Excel
        view.getBtnExportarExcel().addActionListener(e -> exportarExcel());

        // Acciones en la tabla (Click en Editar o Eliminar)
        view.getTblIngresos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClickTabla(e);
            }
        });

        // Paginación
        view.getPaginationBar().addPaginationListener(pagina -> {
            paginaActual = pagina;
            mostrarPaginaTabla();
        });

        // Botón Detalles en Proyección
        view.getBtnProyDetalles().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                "Proyección basada en:\n" +
                "• Contratos de Nómina Fija asegurados: 100%\n" +
                "• Entregables Freelance confirmados con fecha estimada.\n" +
                "• Crecimiento proyectado del ciclo: +6.0%",
                "Detalles de Proyección",
                JOptionPane.INFORMATION_MESSAGE);
        });

        // Restricción RBAC para rol LECTOR
        util.RbacUtil.aplicarRestriccionBotones(view.getBtnRegistrarIngreso());
    }

    public void cargarDatosCompletos() {
        try {
            int idUsuario = getActiveUserId();

            // 1. Obtener lista de ingresos según filtros
            String tipo = null;
            String estado = null;
            if ("Fijo".equalsIgnoreCase(filtroTabActual)) {
                tipo = "Fijo";
            } else if ("Variable".equalsIgnoreCase(filtroTabActual)) {
                tipo = "Variable";
            } else if ("Pendiente".equalsIgnoreCase(filtroTabActual)) {
                estado = "Pendiente";
            }

            String catFiltro = (String) view.getCbFiltroCategoria().getSelectedItem();
            String ctaFiltro = (String) view.getCbFiltroCuenta().getSelectedItem();

            String rDesde = view.getTxtRangoDesde().getText().trim();
            String rHasta = view.getTxtRangoHasta().getText().trim();
            String fDesde = !rDesde.isEmpty() ? normalizarFechaAFechaIso(rDesde) : fechaInicio;
            String fHasta = !rHasta.isEmpty() ? normalizarFechaAFechaIso(rHasta) : fechaFin;

            listaIngresosActual = service.listarIngresos(idUsuario, tipo, estado, fDesde, fHasta, catFiltro, ctaFiltro);

            // 2. Actualizar Tabla
            paginaActual = 1;
            mostrarPaginaTabla();

            // 3. Obtener Resumen y KPIs del período
            IngresoResumen resumen = service.obtenerResumenKpi(idUsuario, fechaInicio, fechaFin, fechaInicioPrev, fechaFinPrev);
            actualizarKpisYCards(resumen);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarPaginaTabla() {
        DefaultTableModel model = (DefaultTableModel) view.getTblIngresos().getModel();
        model.setRowCount(0);

        int totalRegistros = listaIngresosActual.size();
        view.getLblBadgeRegistros().setText(totalRegistros + " registros");

        if (totalRegistros == 0) {
            view.getPaginationBar().updatePagination(1, 1, filasPorPagina);
            return;
        }

        int totalPaginas = (int) Math.ceil((double) totalRegistros / filasPorPagina);
        if (paginaActual > totalPaginas) paginaActual = totalPaginas;
        if (paginaActual < 1) paginaActual = 1;

        int inicio = (paginaActual - 1) * filasPorPagina;
        int fin = Math.min(inicio + filasPorPagina, totalRegistros);

        for (int i = inicio; i < fin; i++) {
            Ingreso ing = listaIngresosActual.get(i);
            String fechaTexto = formatFechaDisplay(ing.getFecha());
            String montoTexto = "+S/. " + String.format(Locale.US, "%,.2f", ing.getMonto());

            model.addRow(new Object[]{
                fechaTexto,
                ing.getDescripcion(),
                ing.getCategoria(),
                ing.getCuentaDestino(),
                ing.getTipoFlujo(),
                montoTexto,
                ing.getEstado(),
                "Editar  Eliminar"
            });
        }

        view.getPaginationBar().updatePagination(paginaActual, totalRegistros, filasPorPagina);
    }

    private void actualizarKpisYCards(IngresoResumen resumen) {
        // KPI 1: Ingresos Totales
        view.getKpiTotales().setValor("S/. " + String.format(Locale.US, "%,.2f", resumen.getIngresosTotales()));
        view.getKpiTotales().setSubtitulo(resumen.getVariacionMes());

        // KPI 2: Ingreso Fijo / Salario
        view.getKpiFijo().setValor("S/. " + String.format(Locale.US, "%,.2f", resumen.getIngresoFijo()));
        view.getKpiFijo().setSubtitulo(String.format(Locale.US, "%.1f%% del total • Garantizado", resumen.getPorcentajeFijo()));

        // KPI 3: Ingresos Variables / Extra
        view.getKpiVariable().setValor("S/. " + String.format(Locale.US, "%,.2f", resumen.getIngresoVariable()));
        view.getKpiVariable().setSubtitulo("Freelance, Dividendos y Bonos");

        // KPI 4: Promedio Mensual
        view.getKpiPromedio().setValor("S/. " + String.format(Locale.US, "%,.2f", resumen.getPromedioMensual()));
        view.getKpiPromedio().setSubtitulo(resumen.getVariacionYoY());

        // Distribución de fuentes
        view.getLblTotalDist().setText("S/. " + String.format(Locale.US, "%,.2f", resumen.getTotalDistribucion()));
        view.getProgressBarFuentes().limpiarSegmentos();

        double nomPct = resumen.getNominaPct();
        double freePct = resumen.getFreelancePct();
        double rendPct = resumen.getRendimientosPct();

        if (nomPct > 0) view.getProgressBarFuentes().agregarSegmento(nomPct, ThemeColor.VERDE_PRIMARIO);
        if (freePct > 0) view.getProgressBarFuentes().agregarSegmento(freePct, ThemeColor.CIAN_ACCION);
        if (rendPct > 0) view.getProgressBarFuentes().agregarSegmento(rendPct, new Color(245, 158, 11));

        view.getLblNominaVal().setText(String.format(Locale.US, "S/. %,.2f (%.1f%%)", resumen.getNominaMonto(), nomPct));
        view.getLblFreelanceVal().setText(String.format(Locale.US, "S/. %,.2f (%.1f%%)", resumen.getFreelanceMonto(), freePct));
        view.getLblRendVal().setText(String.format(Locale.US, "S/. %,.2f (%.1f%%)", resumen.getRendimientosMonto(), rendPct));

        // Proyección Próximo Mes
        view.getLblProyMonto().setText("S/. " + String.format(Locale.US, "%,.2f", resumen.getProyeccionMonto()));
        view.getLblProyBadge().setText(resumen.getProyeccionVariacion());
        view.getLblProySub().setText(resumen.getProyeccionDetalle());

        // Ciclo Salarial
        view.getLblCicloCobrado().setText(String.format(Locale.US, "%.0f%% Cobrado", resumen.getCicloCobradoPct()));
        view.getLblCicloSub().setText(String.format(Locale.US, "S/. %,.2f de S/. %,.2f recibidos este mes", resumen.getCicloCobradoMonto(), resumen.getCicloTotalMonto()));
        view.getLblCicloPendiente().setText(String.format(Locale.US, "S/. %,.2f Pendiente", resumen.getCicloPendienteMonto()));
        view.getLblProxLiqFecha().setText(String.format(Locale.US, "%s (En %d días)", resumen.getProximaLiquidacionFecha(), resumen.getProximaLiquidacionDias()));

        // Header Badge
        view.getLblBadgeCiclo().setText("Ciclo " + periodoSeleccionado);
    }

    private void guardarOActualizar() {
        if (!util.RbacUtil.verificarPermisoEscritura(view, "registrar o modificar ingresos")) {
            return;
        }
        try {
            String montoStr = view.getTxtMonto().getText().trim().replace("S/.", "").replace("S/", "").replace("$", "").replace(",", "").trim();
            if (montoStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe ingresar el monto del ingreso.", "Aviso", JOptionPane.WARNING_MESSAGE);
                view.getTxtMonto().requestFocus();
                return;
            }

            double monto;
            try {
                monto = Double.parseDouble(montoStr);
                if (monto <= 0) {
                    JOptionPane.showMessageDialog(view, "El monto debe ser un valor positivo mayor a 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(view, "El formato del monto no es válido. Ejemplo: 1500.00", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String desc = view.getTxtDescripcion().getText().trim();
            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe ingresar una descripción o fuente de pago.", "Aviso", JOptionPane.WARNING_MESSAGE);
                view.getTxtDescripcion().requestFocus();
                return;
            }

            String fechaStr = view.getTxtFecha().getText().trim();
            Date fecha = parseFechaInput(fechaStr);
            if (fecha == null) {
                JOptionPane.showMessageDialog(view, "Fecha inválida. Use el formato dd/MM/yyyy (ej. 28/10/2023).", "Aviso", JOptionPane.WARNING_MESSAGE);
                view.getTxtFecha().requestFocus();
                return;
            }

            String categoria = (String) view.getCbCategoria().getSelectedItem();
            if (categoria == null) categoria = "Otros";

            String cuenta = (String) view.getCbCuentaDestino().getSelectedItem();
            if (cuenta == null) cuenta = "General";

            String tipoFlujo = view.getRbFijo().isSelected() ? "Fijo" : "Variable";
            boolean recurrente = view.getChkRecordar().isSelected();
            int idUsuario = getActiveUserId();

            Ingreso ing = new Ingreso();
            ing.setMonto(monto);
            ing.setDescripcion(desc);
            ing.setFecha(fecha);
            ing.setCategoria(categoria);
            ing.setCuentaDestino(cuenta);
            ing.setTipoFlujo(tipoFlujo);
            ing.setEstado("Confirmado");
            ing.setRecurrente(recurrente);
            ing.setUsuarioId(idUsuario);

            String resultado;
            if (idIngresoSeleccionado == 0) {
                resultado = service.guardar(ing);
            } else {
                ing.setId(idIngresoSeleccionado);
                resultado = service.actualizar(ing);
            }

            JOptionPane.showMessageDialog(view, resultado, "Sistema Finandes", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            cargarDatosCompletos();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al procesar el ingreso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manejarClickTabla(MouseEvent e) {
        int row = view.getTblIngresos().rowAtPoint(e.getPoint());
        int col = view.getTblIngresos().columnAtPoint(e.getPoint());

        if (row < 0) return;

        int indexEnLista = (paginaActual - 1) * filasPorPagina + row;
        if (indexEnLista >= listaIngresosActual.size()) return;

        Ingreso ing = listaIngresosActual.get(indexEnLista);

        // Si se hace click en la columna de ACCIONES (columna 7)
        if (col == 7) {
            int colX = e.getX() - view.getTblIngresos().getCellRect(row, col, false).x;
            int cellWidth = view.getTblIngresos().getColumnModel().getColumn(col).getWidth();

            // Primera mitad es "Editar", segunda mitad es "Eliminar"
            if (colX < cellWidth / 2) {
                cargarParaEditar(ing);
            } else {
                confirmarYEliminar(ing);
            }
        } else if (e.getClickCount() == 2) {
            // Doble click edita el registro
            cargarParaEditar(ing);
        }
    }

    private void cargarParaEditar(Ingreso ing) {
        idIngresoSeleccionado = ing.getId();
        view.getTxtMonto().setText(String.format(Locale.US, "%.2f", ing.getMonto()));
        view.getTxtDescripcion().setText(ing.getDescripcion());
        view.getTxtFecha().setText(sdfInput.format(ing.getFecha()));

        if ("Fijo".equalsIgnoreCase(ing.getTipoFlujo())) {
            view.getRbFijo().setSelected(true);
        } else {
            view.getRbVariable().setSelected(true);
        }

        view.getChkRecordar().setSelected(ing.isRecurrente());

        // Seleccionar categoría
        for (int i = 0; i < view.getCbCategoria().getItemCount(); i++) {
            if (view.getCbCategoria().getItemAt(i).equalsIgnoreCase(ing.getCategoria())) {
                view.getCbCategoria().setSelectedIndex(i);
                break;
            }
        }

        // Seleccionar cuenta destino
        for (int i = 0; i < view.getCbCuentaDestino().getItemCount(); i++) {
            if (view.getCbCuentaDestino().getItemAt(i).equalsIgnoreCase(ing.getCuentaDestino())) {
                view.getCbCuentaDestino().setSelectedIndex(i);
                break;
            }
        }

        view.getBtnRegistrarIngreso().setText("Actualizar Ingreso");
        view.getTxtMonto().requestFocus();
    }

    private void confirmarYEliminar(Ingreso ing) {
        if (!util.RbacUtil.verificarPermisoEscritura(view, "eliminar ingresos")) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(
            view,
            "¿Está seguro de eliminar el ingreso \"" + ing.getDescripcion() + "\" de S/. " + String.format(Locale.US, "%.2f", ing.getMonto()) + "?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            String res = service.eliminar(ing.getId(), getActiveUserId());
            JOptionPane.showMessageDialog(view, res, "Sistema Finandes", JOptionPane.INFORMATION_MESSAGE);
            if (idIngresoSeleccionado == ing.getId()) {
                limpiarFormulario();
            }
            cargarDatosCompletos();
        }
    }

    public void limpiarFormulario() {
        idIngresoSeleccionado = 0;
        view.getTxtMonto().setText("");
        view.getTxtDescripcion().setText("");
        view.getTxtFecha().setText(sdfInput.format(new Date()));
        view.getRbFijo().setSelected(true);
        view.getChkRecordar().setSelected(false);
        if (view.getCbCategoria().getItemCount() > 0) view.getCbCategoria().setSelectedIndex(0);
        if (view.getCbCuentaDestino().getItemCount() > 0) view.getCbCuentaDestino().setSelectedIndex(0);
        view.getBtnRegistrarIngreso().setText("Registrar Ingreso");
    }

    private void setTabFiltro(String filtro, JButton btnActivo) {
        filtroTabActual = filtro;

        // Reset estilos de tabs
        JButton[] tabs = {view.getBtnTabTodos(), view.getBtnTabFijos(), view.getBtnTabVariables(), view.getBtnTabPendientes()};
        for (JButton b : tabs) {
            b.setBackground(Color.WHITE);
            b.setForeground(new Color(71, 85, 105));
        }

        // Tab activo
        btnActivo.setBackground(ThemeColor.CIAN_ACCION);
        btnActivo.setForeground(Color.WHITE);

        cargarDatosCompletos();
    }

    private void setPeriodo(String label, String fInicio, String fFin, String fInicioPrev, String fFinPrev, JButton btnActivo) {
        periodoSeleccionado = label;
        fechaInicio = fInicio;
        fechaFin = fFin;
        fechaInicioPrev = fInicioPrev;
        fechaFinPrev = fFinPrev;

        if ("Oct 2023".equalsIgnoreCase(label)) {
            view.getTxtRangoDesde().setText("10/01/2023");
            view.getTxtRangoHasta().setText("10/31/2023");
        } else if ("Sep 2023".equalsIgnoreCase(label)) {
            view.getTxtRangoDesde().setText("09/01/2023");
            view.getTxtRangoHasta().setText("09/30/2023");
        } else if ("Anual 2023".equalsIgnoreCase(label)) {
            view.getTxtRangoDesde().setText("01/01/2023");
            view.getTxtRangoHasta().setText("12/31/2023");
        }

        JButton[] pBtns = {view.getBtnFiltroOct(), view.getBtnFiltroSep(), view.getBtnFiltroAnual()};
        for (JButton b : pBtns) {
            b.setBackground(Color.WHITE);
            b.setForeground(new Color(71, 85, 105));
        }
        btnActivo.setBackground(new Color(224, 242, 254)); // #E0F2FE
        btnActivo.setForeground(ThemeColor.CIAN_ACCION);

        cargarDatosCompletos();
    }

    private void ajustarVistaPersonalizada() {
        String input = JOptionPane.showInputDialog(
            view,
            "Ingrese el año y mes para consultar (formato yyyy-MM, ej. 2023-10):",
            "Ajustar Vista",
            JOptionPane.QUESTION_MESSAGE
        );

        if (input != null && input.matches("\\d{4}-\\d{2}")) {
            String[] partes = input.split("-");
            int year = Integer.parseInt(partes[0]);
            int month = Integer.parseInt(partes[1]);

            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month - 1, 1);
            int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

            String fIni = String.format("%04d-%02d-01", year, month);
            String fFin = String.format("%04d-%02d-%02d", year, month, lastDay);

            cal.add(java.util.Calendar.MONTH, -1);
            int prevYear = cal.get(java.util.Calendar.YEAR);
            int prevMonth = cal.get(java.util.Calendar.MONTH) + 1;
            int prevLastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            String fIniPrev = String.format("%04d-%02d-01", prevYear, prevMonth);
            String fFinPrev = String.format("%04d-%02d-%02d", prevYear, prevMonth, prevLastDay);

            SimpleDateFormat mesNombre = new SimpleDateFormat("MMM yyyy", Locale.US);
            cal.set(year, month - 1, 1);
            String label = mesNombre.format(cal.getTime());

            periodoSeleccionado = label;
            fechaInicio = fIni;
            fechaFin = fFin;
            fechaInicioPrev = fIniPrev;
            fechaFinPrev = fFinPrev;

            view.getTxtRangoDesde().setText(String.format("%02d/01/%04d", month, year));
            view.getTxtRangoHasta().setText(String.format("%02d/%02d/%04d", month, lastDay, year));

            cargarDatosCompletos();
        }
    }

    private void aplicarFiltrosRapidos() {
        cargarDatosCompletos();
    }

    private void resetearFiltros() {
        view.getTxtRangoDesde().setText("10/01/2023");
        view.getTxtRangoHasta().setText("10/31/2023");
        if (view.getCbFiltroCategoria().getItemCount() > 0) view.getCbFiltroCategoria().setSelectedIndex(0);
        if (view.getCbFiltroCuenta().getItemCount() > 0) view.getCbFiltroCuenta().setSelectedIndex(0);
        cargarDatosCompletos();
    }

    private void exportarCSV() {
        util.ExportUtil.exportarTablaCSV(view, view.getTblIngresos(), "Ingresos_" + periodoSeleccionado.replace(" ", "_"));
    }

    private void exportarExcel() {
        util.ExportUtil.exportarTablaExcel(view, view.getTblIngresos(), "Historial de Ingresos", "Ingresos_" + periodoSeleccionado.replace(" ", "_"));
    }

    private Date parseFechaInput(String str) {
        if (str == null || str.trim().isEmpty()) return null;
        String s = str.trim();

        if (s.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
            String[] parts = s.split("/");
            int p1 = Integer.parseInt(parts[0]);
            int p2 = Integer.parseInt(parts[1]);

            if (p2 > 12 && p1 <= 12) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    sdf.setLenient(false);
                    return sdf.parse(s);
                } catch (Exception ignored) {}
            }

            if (p1 > 12 && p2 <= 12) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    sdf.setLenient(false);
                    return sdf.parse(s);
                } catch (Exception ignored) {}
            }

            int selectedMonth = 10;
            try {
                if (fechaInicio != null && fechaInicio.length() >= 7) {
                    selectedMonth = Integer.parseInt(fechaInicio.substring(5, 7));
                }
            } catch (Exception ignored) {}

            if (p1 == selectedMonth && p2 != selectedMonth) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    sdf.setLenient(false);
                    return sdf.parse(s);
                } catch (Exception ignored) {}
            }
        }

        String[] formats = {"dd/MM/yyyy", "MM/dd/yyyy", "yyyy-MM-dd", "dd-MM-yyyy"};
        for (String f : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(f, Locale.US);
                sdf.setLenient(false);
                return sdf.parse(s);
            } catch (Exception ignored) {}
        }
        return null;
    }

    private String normalizarFechaAFechaIso(String str) {
        Date d = parseFechaInput(str);
        if (d != null) {
            return sdfIso.format(d);
        }
        return str;
    }

    private String formatFechaDisplay(Date d) {
        if (d == null) return "";
        return sdfDisplay.format(d);
    }

    private int getActiveUserId() {
        Usuario activeUser = Session.getInstance().getUsuarioActivo();
        return (activeUser != null) ? activeUser.getId() : 1;
    }
}
