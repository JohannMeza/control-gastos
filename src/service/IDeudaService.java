package service;

import java.util.List;
import model.AbonoDeuda;
import model.ComboItem;
import model.Deuda;
import model.DeudaResumen;

public interface IDeudaService {
    List<ComboItem> listarDeudasCombo(int idUsuario);
    List<Deuda> listarDeudas(int idUsuario);
    Deuda obtenerPorId(int idDeuda);
    String guardar(Deuda deuda);
    String actualizar(Deuda deuda);
    String eliminar(int idDeuda);
    String registrarAbono(AbonoDeuda abono);
    DeudaResumen obtenerResumen(int idUsuario);
    List<AbonoDeuda> listarHistorialAbonos(int idUsuario);
    double obtenerUltimoAbono(int idDeuda);
    double calcularSaldoPendiente(double montoTotal, double montoPagado);
    double calcularPorcentajeAmortizacion(double montoTotal, double montoPagado);
    void validarAbono(AbonoDeuda abono, double saldoPendiente);
}
