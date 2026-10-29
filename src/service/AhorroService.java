package service;

import dao.AhorroDAO;
import dao.IAhorroDAO;
import java.util.List;
import model.ComboItem;
import model.Ahorro;
import model.MetaAhorro;
import model.MetasResumen;

public class AhorroService implements IAhorroService {
    private final IAhorroDAO dao;

    public AhorroService() {
        this(new AhorroDAO());
    }

    public AhorroService(IAhorroDAO dao) {
        this.dao = dao;
    }

    public List<ComboItem> listarMetas(int idUsuario) {
        return dao.listarMetas(idUsuario);
    }

    public List<Ahorro> listarAhorros(int idUsuario) {
        return dao.listarAhorros(idUsuario);
    }

    public Ahorro obtenerPorId(int idAhorro) {
        return dao.obtenerPorId(idAhorro);
    }

    public String guardar(Ahorro ahorro) {
        return dao.guardar(ahorro);
    }

    public String actualizar(Ahorro ahorro) {
        return dao.actualizar(ahorro);
    }

    public String eliminar(int idAhorro) {
        return dao.eliminar(idAhorro);
    }

    public MetasResumen obtenerResumen(int idUsuario) {
        return dao.obtenerResumen(idUsuario);
    }

    public double obtenerAhorradoMesPasado(int idUsuario) {
        return dao.obtenerAhorradoMesPasado(idUsuario);
    }

    public List<MetaAhorro> obtenerMetasDetalle(int idUsuario) {
        return dao.obtenerMetasDetalle(idUsuario);
    }

    public String guardarMeta(MetaAhorro meta, int idUsuario) {
        return dao.guardarMeta(meta, idUsuario);
    }

    public double calcularPorcentajeProgreso(double montoActual, double montoMeta) {
        if (montoMeta <= 0 || Double.isNaN(montoMeta) || Double.isInfinite(montoMeta)) {
            throw new IllegalArgumentException("El monto meta debe ser mayor a cero.");
        }
        if (montoActual < 0 || Double.isNaN(montoActual) || Double.isInfinite(montoActual)) {
            throw new IllegalArgumentException("El monto actual no puede ser negativo ni inválido.");
        }
        double porcentaje = (montoActual / montoMeta) * 100.0;
        return Math.min(100.0, Math.round(porcentaje * 100.0) / 100.0);
    }

    public double calcularMontoFaltante(double montoActual, double montoMeta) {
        if (montoMeta <= 0 || Double.isNaN(montoMeta) || Double.isInfinite(montoMeta)) {
            throw new IllegalArgumentException("El monto meta debe ser mayor a cero.");
        }
        if (montoActual < 0 || Double.isNaN(montoActual) || Double.isInfinite(montoActual)) {
            throw new IllegalArgumentException("El monto actual no puede ser negativo.");
        }
        return Math.max(0.0, Math.round((montoMeta - montoActual) * 100.0) / 100.0);
    }
}
