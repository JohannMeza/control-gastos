package dao;

import java.util.List;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;

public interface IIngresoDAO {
    List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, String fechaDesde, String fechaHasta, String categoria, String cuenta);
    String guardar(Ingreso ingreso);
    String actualizar(Ingreso ingreso);
    String eliminar(int idIngreso, int idUsuario);
    Ingreso obtenerPorId(int idIngreso, int idUsuario);
    IngresoResumen obtenerResumenKpi(int idUsuario, String fechaInicio, String fechaFin, String fechaInicioPrev, String fechaFinPrev);
    List<ComboItem> listarCategorias();
    List<ComboItem> listarCuentasDestino(int idUsuario);
}
