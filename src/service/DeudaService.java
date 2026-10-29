package service;

import dao.DeudaDAO;
import dao.IDeudaDAO;
import java.util.List;
import model.ComboItem;
import model.Deuda;
import model.AbonoDeuda;
import model.DeudaResumen;

public class DeudaService implements IDeudaService {
    private final IDeudaDAO dao;

    public DeudaService() {
        this(new DeudaDAO());
    }

    public DeudaService(IDeudaDAO dao) {
        this.dao = dao;
    }

    public List<ComboItem> listarDeudasCombo(int idUsuario) {
        return dao.listarDeudasCombo(idUsuario);
    }

    public List<Deuda> listarDeudas(int idUsuario) {
        return dao.listarDeudas(idUsuario);
    }

    public Deuda obtenerPorId(int idDeuda) {
        return dao.obtenerPorId(idDeuda);
    }

    public String guardar(Deuda deuda) {
        return dao.guardar(deuda);
    }

    public String actualizar(Deuda deuda) {
        return dao.actualizar(deuda);
    }

    public String eliminar(int idDeuda) {
        return dao.eliminar(idDeuda);
    }

    public String registrarAbono(AbonoDeuda abono) {
        return dao.registrarAbono(abono);
    }

    public DeudaResumen obtenerResumen(int idUsuario) {
        return dao.obtenerResumen(idUsuario);
    }

    public List<AbonoDeuda> listarHistorialAbonos(int idUsuario) {
        return dao.listarHistorialAbonos(idUsuario);
    }

    public double obtenerUltimoAbono(int idDeuda) {
        return dao.obtenerUltimoAbono(idDeuda);
    }

    public double calcularSaldoPendiente(double montoTotal, double montoPagado) {
        if (montoTotal <= 0 || Double.isNaN(montoTotal) || Double.isInfinite(montoTotal)) {
            throw new IllegalArgumentException("El monto total de la deuda debe ser mayor a cero.");
        }
        if (montoPagado < 0 || Double.isNaN(montoPagado) || Double.isInfinite(montoPagado)) {
            throw new IllegalArgumentException("El monto pagado no puede ser negativo.");
        }
        return Math.max(0.0, Math.round((montoTotal - montoPagado) * 100.0) / 100.0);
    }

    public double calcularPorcentajeAmortizacion(double montoTotal, double montoPagado) {
        if (montoTotal <= 0 || Double.isNaN(montoTotal) || Double.isInfinite(montoTotal)) {
            throw new IllegalArgumentException("El monto total de la deuda debe ser mayor a cero.");
        }
        if (montoPagado < 0 || Double.isNaN(montoPagado) || Double.isInfinite(montoPagado)) {
            throw new IllegalArgumentException("El monto pagado no puede ser negativo.");
        }
        double porcentaje = (montoPagado / montoTotal) * 100.0;
        return Math.min(100.0, Math.round(porcentaje * 100.0) / 100.0);
    }

    public void validarAbono(AbonoDeuda abono, double saldoPendiente) {
        if (abono == null) {
            throw new IllegalArgumentException("El abono no puede ser nulo.");
        }
        if (abono.getMontoAbono() <= 0 || Double.isNaN(abono.getMontoAbono()) || Double.isInfinite(abono.getMontoAbono())) {
            throw new IllegalArgumentException("El monto del abono debe ser mayor a cero.");
        }
        if (abono.getMontoAbono() > saldoPendiente) {
            throw new IllegalArgumentException("El monto del abono no puede exceder el saldo pendiente.");
        }
    }
}
