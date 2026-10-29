package dao;

import java.util.List;
import model.ComboItem;
import model.Presupuesto;
import model.PresupuestoActividad;

public interface IPresupuestoDAO {
    List<ComboItem> listarCategorias();
    List<ComboItem> listarPeriodo();
    List<Presupuesto> listarPresupuesto(int idUsuario, int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idEstado);
    List<PresupuestoActividad> listarPresupuestoActividades(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin);
    String guardarPresupuesto(Presupuesto presupuesto);
    String eliminarPresupuesto(int idPresupuesto);
    List<Integer> obtenerAniosConDatos(int idUsuario);
}
