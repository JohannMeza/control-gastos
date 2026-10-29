package dao;

import java.util.Date;
import java.util.List;
import model.ComboItem;
import model.Gasto;

public interface IGastoDAO {
    String guardar(Gasto gasto);
    String actualizar(Gasto gasto);
    String eliminar(int idGasto);
    List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario);
    Gasto obtenerPorId(int idGasto);
    List<ComboItem> listarCategorias();
    List<ComboItem> listarTipoGastos();
    List<ComboItem> listarUsuarios();
}
