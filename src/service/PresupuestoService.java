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
    
    public List<Presupuesto> listarPresupuesto(int idUsuario, int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idEstado) {
        return dao.listarPresupuesto(idUsuario, idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin, idCategoria, idEstado);
    }

    public List<model.PresupuestoActividad> listarPresupuestoActividades(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin) {
        return dao.listarPresupuestoActividades(idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin);
    }

    
    public String guardarPresupuesto(Presupuesto presupuesto) {
        return dao.guardarPresupuesto(presupuesto);
    }
    
    public String eliminarPresupuesto(int idPresupuesto) {
        return dao.eliminarPresupuesto(idPresupuesto);
    }

    public List<Integer> obtenerAniosConDatos(int idUsuario) {
        return dao.obtenerAniosConDatos(idUsuario);
    }
}
