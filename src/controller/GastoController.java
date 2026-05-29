/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import model.Gasto;
import service.GastoService;
import view.SystemView;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public final class GastoController {
    private SystemView view;
    private GastoService service;
    
    public GastoController(SystemView view) {
        this.view = view;
        this.service = new GastoService();
        
        //listarGastos();
        listarCategorias();
        listarTipoGastos();
        listarUsuarios();
    }
    
    public void exportarExcel() {
        service.exportarExcel(view.tblGastos);
    }
    
    public void guardar() {
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
            gasto.setFechaGasto(view.dateFechaNuevo.getDate());
            
            service.guardar(gasto);
            
        } catch (NumberFormatException e) {
            
        }
    }
    
    public void actualizar() {
        try {
            ComboItem categoria = (ComboItem) view.cbCategoria.getSelectedItem();
            ComboItem tipo = (ComboItem) view.cbTipoGastos.getSelectedItem();

            Gasto gasto = new Gasto();

            gasto.setIdGasto(view.idGastoSeleccionado);
            gasto.setMonto(Double.parseDouble(view.txtMonto.getText()));
            gasto.setIdCategoria(categoria.getValue());
            gasto.setIdTipoGasto(tipo.getValue());
            gasto.setDescripcion(view.txtDescripcion.getText());
            gasto.setFechaGasto(view.dateFechaNuevo.getDate());

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
        Date fechaDesde = view.dateFiltroDesde.getDate();
        Date fechaHasta = view.dateFiltroHasta.getDate();
        int idUsuario = cbUsuario.getValue();
        
        List<Gasto> lista = service.listar(fechaDesde, fechaHasta, idUsuario);

        DefaultTableModel modelo = (DefaultTableModel) view.tblGastos.getModel();
        modelo.setRowCount(0);

        lista.forEach(g -> {
            modelo.addRow(new Object[]{
                g.getIdGasto(),
                g.getFechaGastoFormat(),
                g.getDescripcion(),
                g.getCategoria(),
                g.getTipoGasto(),
                g.getMonto(),
                g.getUsuario()
            });
        });
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

            List<ComboItem> usuarios = service.listarUsuarios();

            usuarios.forEach(item -> {
                view.cbUser.addItem(item);
            });

        } catch (Exception e) {}
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
