/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ComboItem;
import service.PresupuestoService;
import view.PresupuestoView;
import model.Presupuesto;

public class PresupuestoController {
    private PresupuestoView view;
    private PresupuestoService service;
    
    public PresupuestoController (PresupuestoView view) {
        this.view = view;
        this.service = new PresupuestoService();
        
        listarCategorias();
        listarPeriodos();
        listarPresupuesto();
    }
    
    public void listarPresupuesto() {
        try {
            int idUsuario = 1;

            List<Presupuesto> lista = service.listarPresupuesto(idUsuario);

            DefaultTableModel modelo = (DefaultTableModel) view.tblPresupuestos.getModel();
            modelo.setRowCount(0);

            lista.forEach(g -> {
                modelo.addRow(new Object[]{
                    g.getIdPresupuesto(),
                    g.getCategoria(),
                    g.getMontoAsignado(),
                    g.getMontoGastado(),
                    g.getEstadoPresupuesto(),
                    g.getProgresoPresupuesto(),
                });
            });
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
    
    public void guardarPresupuesto() {
        try {
            Object selected = view.cbCategoria.getSelectedItem();
            System.out.println(selected);
            System.out.println(selected.getClass());
            ComboItem categoria = (ComboItem) view.cbCategoria.getSelectedItem();
            ComboItem periodo = (ComboItem) view.cbPeriodo.getSelectedItem();

            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setIdCategoria(categoria.getValue());
            presupuesto.setIdTipoPeriodo(periodo.getValue());
            presupuesto.setLimPresuMens(Double.parseDouble(view.txtPresupuestoMensual.getText()));
            presupuesto.setUmbralAlerta(Integer.parseInt(view.txtUmbral.getText()));
            
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
                "Ocurrió un error al eliminar el gasto",
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
                "Ocurrió un error al eliminar el gasto",
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
                "Ocurrió un error al eliminar el gasto",
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
                "Ocurrió un error al eliminar el gasto",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
