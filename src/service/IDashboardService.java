package service;

import java.util.List;
import model.DashboardGraficos;
import model.DashboardResumen;
import model.PagoProgramado;

public interface IDashboardService {
    DashboardResumen obtenerResumen(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax);
    List<DashboardGraficos> obtenerGraficos(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax);
    List<PagoProgramado> obtenerProximosPagos(int idUsuarioOwner);
}
