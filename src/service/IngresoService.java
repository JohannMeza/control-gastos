package service;

import dao.IIngresoDAO;
import dao.IngresoDAO;
import java.util.ArrayList;
import java.util.List;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;

public class IngresoService implements IIngresoService {
    private final IIngresoDAO dao;

    public IngresoService() {
        this(new IngresoDAO());
    }

    public IngresoService(IIngresoDAO dao) {
        this.dao = dao;
    }

    public List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, String fechaDesde, String fechaHasta, String categoria, String cuenta) {
        return dao.listarIngresos(idUsuario, tipoFlujo, estado, fechaDesde, fechaHasta, categoria, cuenta);
    }

    public String guardar(Ingreso ingreso) {
        if (ingreso == null) {
            return "El registro de ingreso no puede ser nulo.";
        }
        if (ingreso.getMonto() <= 0) {
            return "El monto del ingreso debe ser mayor a 0.";
        }
        if (ingreso.getDescripcion() == null || ingreso.getDescripcion().trim().isEmpty()) {
            return "Debe ingresar una descripción o fuente de pago.";
        }
        if (ingreso.getFecha() == null) {
            return "Debe seleccionar una fecha de recepción válida.";
        }
        return dao.guardar(ingreso);
    }

    public String actualizar(Ingreso ingreso) {
        if (ingreso == null) {
            return "El registro de ingreso no puede ser nulo.";
        }
        if (ingreso.getId() <= 0) {
            return "Identificador de ingreso inválido.";
        }
        if (ingreso.getMonto() <= 0) {
            return "El monto del ingreso debe ser mayor a 0.";
        }
        if (ingreso.getDescripcion() == null || ingreso.getDescripcion().trim().isEmpty()) {
            return "Debe ingresar una descripción o fuente de pago.";
        }
        return dao.actualizar(ingreso);
    }

    public String eliminar(int idIngreso, int idUsuario) {
        if (idIngreso <= 0) {
            return "Identificador de ingreso inválido.";
        }
        return dao.eliminar(idIngreso, idUsuario);
    }

    public Ingreso obtenerPorId(int idIngreso, int idUsuario) {
        return dao.obtenerPorId(idIngreso, idUsuario);
    }

    public IngresoResumen obtenerResumenKpi(int idUsuario, String fechaInicio, String fechaFin, String fechaInicioPrev, String fechaFinPrev) {
        return dao.obtenerResumenKpi(idUsuario, fechaInicio, fechaFin, fechaInicioPrev, fechaFinPrev);
    }

    public List<ComboItem> listarCategorias() {
        return dao.listarCategorias();
    }

    public List<ComboItem> listarCuentasDestino(int idUsuario) {
        return dao.listarCuentasDestino(idUsuario);
    }

    public double calcularTotalIngresos(List<Ingreso> ingresos) {
        if (ingresos == null || ingresos.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Ingreso ing : ingresos) {
            if (ing != null && ing.getMonto() > 0) {
                total += ing.getMonto();
            }
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public double calcularBalanceNeto(double totalIngresos, double totalGastos) {
        if (Double.isNaN(totalIngresos) || Double.isInfinite(totalIngresos) ||
            Double.isNaN(totalGastos) || Double.isInfinite(totalGastos)) {
            throw new IllegalArgumentException("Los montos para el balance neto deben ser números válidos.");
        }
        return Math.round((totalIngresos - totalGastos) * 100.0) / 100.0;
    }

    public List<Ingreso> filtrarPorEstado(List<Ingreso> ingresos, String estado) {
        List<Ingreso> resultado = new ArrayList<>();
        if (ingresos == null || ingresos.isEmpty() || estado == null) {
            return resultado;
        }
        for (Ingreso ing : ingresos) {
            if (ing != null && estado.equalsIgnoreCase(ing.getEstado())) {
                resultado.add(ing);
            }
        }
        return resultado;
    }
}
