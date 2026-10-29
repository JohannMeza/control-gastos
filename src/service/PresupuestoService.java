package service;

import dao.IPresupuestoDAO;
import dao.PresupuestoDAO;
import java.util.List;
import model.ComboItem;
import model.Presupuesto;
import model.PresupuestoActividad;

public class PresupuestoService implements IPresupuestoService {
    private final IPresupuestoDAO dao;

    public PresupuestoService() {
        this(new PresupuestoDAO());
    }

    public PresupuestoService(IPresupuestoDAO dao) {
        this.dao = dao;
    }
    
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

    public double[] calcularDistribucion503020(double ingresoTotal) {
        if (ingresoTotal <= 0 || Double.isNaN(ingresoTotal) || Double.isInfinite(ingresoTotal)) {
            throw new IllegalArgumentException("El ingreso total debe ser un número válido mayor a cero.");
        }
        double necesidades = Math.round(ingresoTotal * 0.50 * 100.0) / 100.0;
        double deseos = Math.round(ingresoTotal * 0.30 * 100.0) / 100.0;
        double ahorro = Math.round(ingresoTotal * 0.20 * 100.0) / 100.0;
        return new double[] { necesidades, deseos, ahorro };
    }

    public double calcularPorcentajeEjecucion(double montoAsignado, double montoGastado) {
        if (montoAsignado <= 0 || Double.isNaN(montoAsignado) || Double.isInfinite(montoAsignado)) {
            throw new IllegalArgumentException("El monto asignado debe ser mayor a cero.");
        }
        if (montoGastado < 0 || Double.isNaN(montoGastado) || Double.isInfinite(montoGastado)) {
            throw new IllegalArgumentException("El monto gastado no puede ser negativo.");
        }
        return Math.round(((montoGastado / montoAsignado) * 100.0) * 100.0) / 100.0;
    }

    public double calcularSaldoDisponible(double montoAsignado, double montoGastado) {
        if (Double.isNaN(montoAsignado) || Double.isInfinite(montoAsignado) ||
            Double.isNaN(montoGastado) || Double.isInfinite(montoGastado)) {
            throw new IllegalArgumentException("Los montos deben ser valores numéricos válidos.");
        }
        return Math.round((montoAsignado - montoGastado) * 100.0) / 100.0;
    }

    public String evaluarAlertaPresupuesto(double porcentajeEjecucion) {
        if (Double.isNaN(porcentajeEjecucion) || porcentajeEjecucion < 0) {
            throw new IllegalArgumentException("El porcentaje de ejecución debe ser un valor válido no negativo.");
        }
        if (porcentajeEjecucion >= 100.0) {
            return "SOBREGIRO";
        } else if (porcentajeEjecucion >= 80.0) {
            return "ADVERTENCIA";
        } else {
            return "NORMAL";
        }
    }
}
