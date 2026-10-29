/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.GastoDAO;
import dao.IGastoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ComboItem;
import model.Gasto;

public class GastoService implements IGastoService {
    private final IGastoDAO dao;

    public GastoService() {
        this(new GastoDAO());
    }

    public GastoService(IGastoDAO dao) {
        this.dao = dao;
    }
    
    public void guardar(Gasto gasto) {
        dao.guardar(gasto);
    }
    
    public String actualizar(Gasto gasto) {
        return dao.actualizar(gasto);
    }
    
    public String eliminar(int idGasto) {
        return dao.eliminar(idGasto);
    }
    
    public List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario) {
        return dao.listar(fechaDesde, fechaHasta, idUsuario);
    }
    
    public List<ComboItem> listarCategorias() {
        return dao.listarCategorias();
    }
    
    public List<ComboItem> listarTipoGastos() {
        return dao.listarTipoGastos();
    }
    
    public List<ComboItem> listarUsuarios() {
        return dao.listarUsuarios();
    }
    
    public Gasto obtenerPorId(int idGasto) {
        return dao.obtenerPorId(idGasto);
    }
    
    public void validarGasto(Gasto gasto) {
        if (gasto == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo.");
        }
        if (gasto.getMonto() <= 0 || Double.isNaN(gasto.getMonto()) || Double.isInfinite(gasto.getMonto())) {
            throw new IllegalArgumentException("El monto del gasto debe ser un número válido mayor a cero.");
        }
        if (gasto.getDescripcion() == null || gasto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del gasto es obligatoria.");
        }
        if (gasto.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El ID de usuario asociado debe ser válido.");
        }
    }

    public double calcularTotalGastos(List<Gasto> gastos) {
        if (gastos == null || gastos.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Gasto g : gastos) {
            if (g != null && g.getMonto() > 0 && !Double.isNaN(g.getMonto()) && !Double.isInfinite(g.getMonto())) {
                total += g.getMonto();
            }
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public List<Gasto> filtrarPorCategoria(List<Gasto> gastos, int idCategoria) {
        List<Gasto> resultado = new ArrayList<>();
        if (gastos == null || gastos.isEmpty()) {
            return resultado;
        }
        for (Gasto g : gastos) {
            if (g != null && g.getIdCategoria() == idCategoria) {
                resultado.add(g);
            }
        }
        return resultado;
    }
}
