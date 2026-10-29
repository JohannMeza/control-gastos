package dao;

import java.util.List;
import model.AbonoDeuda;
import model.ComboItem;
import model.Deuda;
import model.DeudaResumen;

public interface IDeudaDAO {
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
}
