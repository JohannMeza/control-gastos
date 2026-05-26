package service;

import dao.PresupuestoDAO;
import java.util.List;
import model.ComboItem;
import model.Presupuesto;

public class PresupuestoService {
    private PresupuestoDAO dao = new PresupuestoDAO();
    
    public List<ComboItem> listarCategorias() {
        return dao.listarCategorias();
    }
    
    public List<ComboItem> listarPeriodo() {
        return dao.listarPeriodo();
    }
    
    public List<Presupuesto> listarPresupuesto(int idUsuario) {
        return dao.listarPresupuesto(idUsuario);
    }
    
    public String guardarPresupuesto(Presupuesto presupuesto) {
        return dao.guardarPresupuesto(presupuesto);
    }
    
    public String eliminarPresupuesto(int idPresupuesto) {
        return dao.eliminarPresupuesto(idPresupuesto);
    }
}
