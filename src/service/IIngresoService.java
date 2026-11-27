package service;

import java.util.Date;
import java.util.List;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;

public interface IIngresoService {
    List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, Date fechaDesde, Date fechaHasta, String categoria, String cuenta);
    List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, String fechaDesde, String fechaHasta, String categoria, String cuenta);
    String guardar(Ingreso ingreso);
    String actualizar(Ingreso ingreso);
    String eliminar(int idIngreso, int idUsuario);
    Ingreso obtenerPorId(int idIngreso, int idUsuario);
    IngresoResumen obtenerResumenKpi(int idUsuario, Date fechaInicio, Date fechaFin, Date fechaInicioPrev, Date fechaFinPrev);
    IngresoResumen obtenerResumenKpi(int idUsuario, String fechaInicio, String fechaFin, String fechaInicioPrev, String fechaFinPrev);
    List<ComboItem> listarCategorias();
    List<ComboItem> listarCuentasDestino(int idUsuario);
    double calcularTotalIngresos(List<Ingreso> ingresos);
    double calcularBalanceNeto(double totalIngresos, double totalGastos);
    List<Ingreso> filtrarPorEstado(List<Ingreso> ingresos, String estado);
}
