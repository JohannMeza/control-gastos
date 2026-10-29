package dao;

import java.util.List;
import model.Ahorro;
import model.ComboItem;
import model.MetaAhorro;
import model.MetasResumen;

public interface IAhorroDAO {
    List<ComboItem> listarMetas(int idUsuario);
    List<Ahorro> listarAhorros(int idUsuario);
    Ahorro obtenerPorId(int idAhorro);
    String guardar(Ahorro ahorro);
    String actualizar(Ahorro ahorro);
    String eliminar(int idAhorro);
    MetasResumen obtenerResumen(int idUsuario);
    List<MetaAhorro> obtenerMetasDetalle(int idUsuario);
    String guardarMeta(MetaAhorro meta, int idUsuario);
    double obtenerAhorradoMesPasado(int idUsuario);
}
