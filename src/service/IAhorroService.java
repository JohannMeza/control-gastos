package service;

import java.util.List;
import model.Ahorro;
import model.ComboItem;
import model.MetaAhorro;
import model.MetasResumen;

public interface IAhorroService {
    List<ComboItem> listarMetas(int idUsuario);
    List<Ahorro> listarAhorros(int idUsuario);
    Ahorro obtenerPorId(int idAhorro);
    String guardar(Ahorro ahorro);
    String actualizar(Ahorro ahorro);
    String eliminar(int idAhorro);
    MetasResumen obtenerResumen(int idUsuario);
    double obtenerAhorradoMesPasado(int idUsuario);
    List<MetaAhorro> obtenerMetasDetalle(int idUsuario);
    String guardarMeta(MetaAhorro meta, int idUsuario);
    double calcularPorcentajeProgreso(double montoActual, double montoMeta);
    double calcularMontoFaltante(double montoActual, double montoMeta);
}
