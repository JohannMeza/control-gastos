package service;

import java.util.List;
import dao.DashboardDAO;
import model.DashboardResumen;
import model.DashboardGraficos;

public class DashboardService {
    private DashboardDAO dao = new DashboardDAO();

    public DashboardResumen obtenerResumen(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax) {
        return dao.obtenerResumen(idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin, idCategoria, idTipoGasto, montoMin, montoMax);
    }

    public List<DashboardGraficos> obtenerGraficos(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idTipoGasto, double montoMin, double montoMax) {
        return dao.obtenerGraficos(idUsuarioOwner, idFiltradoUsuario, fechaInicio, fechaFin, idCategoria, idTipoGasto, montoMin, montoMax);
    }

    public List<model.PagoProgramado> obtenerProximosPagos(int idUsuarioOwner) {
        return dao.obtenerProximosPagos(idUsuarioOwner);
    }
}
