package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GastoAuditoria;
import model.RegistroAuditoria;
import service.AuditoriaService;
import util.estructuras.*;
import view.AuditoriaView;

public class AuditoriaController {
    private final AuditoriaView view;
    private final AuditoriaService service;

    public AuditoriaController(AuditoriaView view) {
        this.view = view;
        this.service = new AuditoriaService();
    }

    public void inicializarVista() {
        refrescarTodo();
    }

    public void refrescarTodo() {
        refrescarVector();
        refrescarMatriz();
        refrescarListaSimple();
        refrescarLogs();
        refrescarPila();
        refrescarCola();
        refrescarArbolVista();
    }

    // === 1. CONTROL DE ARREGLOS Y MATRICES ===

    public void refrescarVector() {
        double[] vec = service.getVectorSaldos();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vec.length; i++) {
            sb.append("[").append(i).append("] S/ ").append(String.format("%.2f", vec[i]));
            if (i < vec.length - 1) sb.append("  |  ");
        }
        view.txtVectorMostrar.setText(sb.toString());
    }

    public void insertarVector(double valor, int pos) {
        try {
            service.insertarSaldo(valor, pos);
            refrescarVector();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarVector(int index, double valor) {
        try {
            service.actualizarSaldo(index, valor);
            refrescarVector();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarVector(int pos) {
        try {
            service.eliminarSaldo(pos);
            refrescarVector();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void compararVector(String inputStr) {
        try {
            String[] parts = inputStr.split(",");
            double[] userVec = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                userVec[i] = Double.parseDouble(parts[i].trim());
            }
            boolean result = service.compararSaldos(userVec);
            JOptionPane.showMessageDialog(view, "\u00bfVectores id\u00e9nticos?: " + (result ? "S\u00cd" : "NO"), "Comparaci\u00f3n", JOptionPane.INFORMATION_MESSAGE);
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Formato incorrecto. Ejemplo: 1500,2300,1850", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double[] copiarVector() {
        double[] copia = service.copiarSaldos();
        refrescarLogs();
        return copia;
    }

    public void refrescarMatriz() {
        double[][] mat = service.getMatrizGastos();
        DefaultTableModel model = (DefaultTableModel) view.tblMatriz.getModel();
        model.setRowCount(0);
        for (int i = 0; i < mat.length; i++) {
            Object[] row = new Object[mat[i].length];
            for (int j = 0; j < mat[i].length; j++) {
                row[j] = String.format("%.2f", mat[i][j]);
            }
            model.addRow(row);
        }
    }

    public void registrarDatoMatriz(int f, int c, double val) {
        try {
            service.registrarDatoMatriz(f, c, val);
            refrescarMatriz();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void transponerMatriz() {
        service.transponerMatriz();
        refrescarMatriz();
        refrescarLogs();
    }

    public void determinanteMatriz() {
        try {
            double det = service.calcularDeterminanteMatriz();
            JOptionPane.showMessageDialog(view, "Determinante de la matriz: " + String.format("%.4f", det), "Resultado", JOptionPane.INFORMATION_MESSAGE);
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void invertirMatriz() {
        try {
            service.invertirMatriz();
            refrescarMatriz();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void checkSimetria() {
        boolean sim = service.esMatrizSimetrica();
        JOptionPane.showMessageDialog(view, "\u00bfEs sim\u00e9trica?: " + (sim ? "S\u00cd" : "NO"), "Chequeo Simetr\u00eda", JOptionPane.INFORMATION_MESSAGE);
        refrescarLogs();
    }

    public void checkAsimetria() {
        boolean asim = service.esMatrizAsimetrica();
        JOptionPane.showMessageDialog(view, "\u00bfEs asim\u00e9trica?: " + (asim ? "S\u00cd" : "NO"), "Chequeo Asimetr\u00eda", JOptionPane.INFORMATION_MESSAGE);
        refrescarLogs();
    }

    // === 2. CONTROL DE LISTAS ENLAZADAS ===

    public void refrescarListaSimple() {
        DefaultTableModel model = (DefaultTableModel) view.tblListaSimple.getModel();
        model.setRowCount(0);
        ListaSimple.Nodo<GastoAuditoria> actual = service.getListaGastos().getCabeza();
        while (actual != null) {
            model.addRow(new Object[]{
                actual.dato.getIdGasto(),
                actual.dato.getDescripcion(),
                String.format("%.2f", actual.dato.getMonto()),
                actual.dato.getCategoria(),
                actual.dato.getFecha()
            });
            actual = actual.siguiente;
        }
    }

    public void insertarGastoLista(String desc, double monto, String cat, boolean inicio) {
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ingrese descripción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (inicio) {
            service.insertarGastoInicio(desc, monto, cat);
        } else {
            service.insertarGastoFinal(desc, monto, cat);
        }
        refrescarListaSimple();
        refrescarLogs();
    }

    public void eliminarGastoLista(String desc) {
        boolean res = service.eliminarGasto(desc);
        if (res) {
            refrescarListaSimple();
            refrescarLogs();
        } else {
            JOptionPane.showMessageDialog(view, "No se encontr\u00f3 ning\u00fan gasto con esa descripci\u00f3n", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void buscarGastoLista(String desc) {
        GastoAuditoria g = service.buscarGasto(desc);
        if (g != null) {
            JOptionPane.showMessageDialog(view, "Encontrado:\nID: " + g.getIdGasto() + "\nDescripci\u00f3n: " + g.getDescripcion() + "\nMonto: S/ " + g.getMonto() + "\nCategor\u00eda: " + g.getCategoria(), "B\u00fasqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "No se encontr\u00f3 coincidencia.", "B\u00fasqueda", JOptionPane.INFORMATION_MESSAGE);
        }
        refrescarLogs();
    }

    public void ordenarGastosLista() {
        service.ordenarGastosBubble();
        refrescarListaSimple();
        refrescarLogs();
    }

    public void refrescarLogs() {
        RegistroAuditoria actual = service.getListaLogs().getActual();
        if (actual != null) {
            view.lblLogActual.setText(actual.toString());
        } else {
            view.lblLogActual.setText("Sin registros contables");
        }
        view.btnAnteriorLog.setEnabled(service.getListaLogs().tieneAnterior());
        view.btnSiguienteLog.setEnabled(service.getListaLogs().tieneSiguiente());
    }

    public void navegarLog(boolean siguiente) {
        if (siguiente) {
            service.getListaLogs().irSiguiente();
        } else {
            service.getListaLogs().irAnterior();
        }
        refrescarLogs();
    }

    // === 3. CONTROL DE PILAS Y COLAS ===

    public void refrescarPila() {
        DefaultTableModel model = (DefaultTableModel) view.tblPila.getModel();
        model.setRowCount(0);
        
        PilaTransacciones<GastoAuditoria> pila = service.getPilaDeshacer();
        int cant = pila.getCantidad();
        
        for (int i = cant - 1; i >= 0; i--) {
            GastoAuditoria g = pila.get(i);
            if (g != null) {
                model.addRow(new Object[]{
                    g.getDescripcion(),
                    String.format("%.2f", g.getMonto()),
                    g.getCategoria()
                });
            }
        }
        
        view.lblPilaStatus.setText("Asientos en pila: " + cant + " / " + pila.getCapacidad() + " (LIFO)");
        view.btnDeshacer.setEnabled(!pila.isEmpty());
        view.btnRehacer.setEnabled(!service.getPilaRehacer().isEmpty());
    }

    public void registrarAccionSimulada(String desc, double monto, String cat) {
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Ingrese descripción", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            service.registrarAccionSimulada(desc, monto, cat);
            refrescarListaSimple();
            refrescarPila();
            refrescarLogs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deshacerAccion() {
        boolean ok = service.deshacer();
        if (ok) {
            refrescarListaSimple();
            refrescarPila();
            refrescarLogs();
        }
    }

    public void rehacerAccion() {
        boolean ok = service.rehacer();
        if (ok) {
            refrescarListaSimple();
            refrescarPila();
            refrescarLogs();
        }
    }

    public void refrescarCola() {
        DefaultTableModel model = (DefaultTableModel) view.tblColaAlertas.getModel();
        model.setRowCount(0);
        
        ColaPrioridadAlertas.NodoPrioridad<String> temp = service.getColaAlertas().getFrente();
        while (temp != null) {
            model.addRow(new Object[]{
                temp.dato,
                temp.prioridad
            });
            temp = temp.siguiente;
        }
        view.btnDesencolarAlerta.setEnabled(!service.getColaAlertas().isEmpty());
    }

    public void encolarAlerta(String mensaje, int prioridad) {
        if (mensaje.isEmpty()) return;
        service.encolarAlerta(mensaje, prioridad);
        refrescarCola();
        refrescarLogs();
    }

    public void desencolarAlerta() {
        String msg = service.desencolarAlerta();
        if (msg != null) {
            JOptionPane.showMessageDialog(view, "Alerta despachada:\n" + msg, "Atención", JOptionPane.WARNING_MESSAGE);
            refrescarCola();
            refrescarLogs();
        }
    }

    // === 4. CONTROL DE ARBOLES ===

    public void refrescarArbolVista() {
        List<String> inorden = service.getArbolCategorias().recorrerInorden();
        DefaultTableModel model = (DefaultTableModel) view.tblArbolCategorias.getModel();
        model.setRowCount(0);
        for (String cat : inorden) {
            model.addRow(new Object[]{cat});
        }
    }

    public void insertarCategoriaArbol(String cat) {
        if (cat.isEmpty()) return;
        service.insertarCategoria(cat);
        refrescarArbolVista();
        refrescarLogs();
    }

    public void eliminarCategoriaArbol(String cat) {
        if (cat.isEmpty()) return;
        service.eliminarCategoria(cat);
        refrescarArbolVista();
        refrescarLogs();
    }

    public void buscarCategoriaArbol(String cat) {
        if (cat.isEmpty()) return;
        boolean res = service.buscarCategoria(cat);
        JOptionPane.showMessageDialog(view, "\u00bfCategor\u00eda '" + cat + "' existe en el ABB?: " + (res ? "S\u00cd" : "NO"), "ABB B\u00fasqueda", JOptionPane.INFORMATION_MESSAGE);
        refrescarLogs();
    }

    public void ejecutarRecorridoABB(String tipo) {
        List<String> nodos;
        String desc;
        if (tipo.equalsIgnoreCase("INORDEN")) {
            nodos = service.getArbolCategorias().recorrerInorden();
            desc = "Inorden (LNR - Izquierda -> Ra\u00edz -> Derecha):";
        } else if (tipo.equalsIgnoreCase("PREORDEN")) {
            nodos = service.getArbolCategorias().recorrerPreorden();
            desc = "Preorden (NLR - Ra\u00edz -> Izquierda -> Derecha):";
        } else {
            nodos = service.getArbolCategorias().recorrerPostorden();
            desc = "Postorden (LRN - Izquierda -> Derecha -> Ra\u00edz):";
        }

        StringBuilder sb = new StringBuilder(desc).append("\n");
        for (int i = 0; i < nodos.size(); i++) {
            sb.append(nodos.get(i));
            if (i < nodos.size() - 1) sb.append(" ➜ ");
        }
        view.txtAreaRecorridos.setText(sb.toString());
        service.registrarLog("RECORRIDO_ABB", "Recorrido " + tipo + " ejecutado.");
        refrescarLogs();
    }
}
