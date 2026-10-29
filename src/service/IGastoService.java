package service;

import java.util.Date;
import java.util.List;
import model.ComboItem;
import model.Gasto;

public interface IGastoService {
    void guardar(Gasto gasto);
    String actualizar(Gasto gasto);
    String eliminar(int idGasto);
    List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario);
    List<ComboItem> listarCategorias();
    List<ComboItem> listarTipoGastos();
    List<ComboItem> listarUsuarios();
    Gasto obtenerPorId(int idGasto);
    void validarGasto(Gasto gasto);
    double calcularTotalGastos(List<Gasto> gastos);
    List<Gasto> filtrarPorCategoria(List<Gasto> gastos, int idCategoria);
}
