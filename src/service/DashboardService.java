package service;

import java.util.List;
import dao.DashboardDAO;
import dao.IDashboardDAO;
import model.DashboardResumen;
import model.DashboardGraficos;

public class DashboardService implements IDashboardService {
    private final IDashboardDAO dao;

    public DashboardService() {
        this(new DashboardDAO());
    }

    public DashboardService(IDashboardDAO dao) {
        this.dao = dao;
    }

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
