package model;

import java.util.HashMap;
import java.util.Map;

public class IngresoResumen {
    private double ingresosTotales;
    private String variacionMes;
    private double ingresoFijo;
    private double porcentajeFijo;
    private double ingresoVariable;
    private double porcentajeVariable;
    private double promedioMensual;
    private String variacionYoY;

    // Distribución de fuentes
    private double totalDistribucion;
    private double nominaMonto;
    private double nominaPct;
    private double freelanceMonto;
    private double freelancePct;
    private double rendimientosMonto;
    private double rendimientosPct;
    private Map<String, Double> distribucionPorCategoria = new HashMap<>();

    // Proyección próximo mes
    private double proyeccionMonto;
    private String proyeccionVariacion;
    private String proyeccionDetalle;

    // Ciclo salarial
    private double cicloCobradoPct;
    private double cicloCobradoMonto;
    private double cicloTotalMonto;
    private double cicloPendienteMonto;
    private String proximaLiquidacionFecha;
    private int proximaLiquidacionDias;

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    public String getVariacionMes() {
        return variacionMes;
    }

    public void setVariacionMes(String variacionMes) {
        this.variacionMes = variacionMes;
    }

    public double getIngresoFijo() {
        return ingresoFijo;
    }

    public void setIngresoFijo(double ingresoFijo) {
        this.ingresoFijo = ingresoFijo;
    }

    public double getPorcentajeFijo() {
        return porcentajeFijo;
    }

    public void setPorcentajeFijo(double porcentajeFijo) {
        this.porcentajeFijo = porcentajeFijo;
    }

    public double getIngresoVariable() {
        return ingresoVariable;
    }

    public void setIngresoVariable(double ingresoVariable) {
        this.ingresoVariable = ingresoVariable;
    }

    public double getPorcentajeVariable() {
        return porcentajeVariable;
    }

    public void setPorcentajeVariable(double porcentajeVariable) {
        this.porcentajeVariable = porcentajeVariable;
    }

    public double getPromedioMensual() {
        return promedioMensual;
    }

    public void setPromedioMensual(double promedioMensual) {
        this.promedioMensual = promedioMensual;
    }

    public String getVariacionYoY() {
        return variacionYoY;
    }

    public void setVariacionYoY(String variacionYoY) {
        this.variacionYoY = variacionYoY;
    }

    public double getTotalDistribucion() {
        return totalDistribucion;
    }

    public void setTotalDistribucion(double totalDistribucion) {
        this.totalDistribucion = totalDistribucion;
    }

    public double getNominaMonto() {
        return nominaMonto;
    }

    public void setNominaMonto(double nominaMonto) {
        this.nominaMonto = nominaMonto;
    }

    public double getNominaPct() {
        return nominaPct;
    }

    public void setNominaPct(double nominaPct) {
        this.nominaPct = nominaPct;
    }

    public double getFreelanceMonto() {
        return freelanceMonto;
    }

    public void setFreelanceMonto(double freelanceMonto) {
        this.freelanceMonto = freelanceMonto;
    }

    public double getFreelancePct() {
        return freelancePct;
    }

    public void setFreelancePct(double freelancePct) {
        this.freelancePct = freelancePct;
    }

    public double getRendimientosMonto() {
        return rendimientosMonto;
    }

    public void setRendimientosMonto(double rendimientosMonto) {
        this.rendimientosMonto = rendimientosMonto;
    }

    public double getRendimientosPct() {
        return rendimientosPct;
    }

    public void setRendimientosPct(double rendimientosPct) {
        this.rendimientosPct = rendimientosPct;
    }

    public Map<String, Double> getDistribucionPorCategoria() {
        return distribucionPorCategoria;
    }

    public void setDistribucionPorCategoria(Map<String, Double> distribucionPorCategoria) {
        this.distribucionPorCategoria = distribucionPorCategoria;
    }

    public double getProyeccionMonto() {
        return proyeccionMonto;
    }

    public void setProyeccionMonto(double proyeccionMonto) {
        this.proyeccionMonto = proyeccionMonto;
    }

    public String getProyeccionVariacion() {
        return proyeccionVariacion;
    }

    public void setProyeccionVariacion(String proyeccionVariacion) {
        this.proyeccionVariacion = proyeccionVariacion;
    }

    public String getProyeccionDetalle() {
        return proyeccionDetalle;
    }

    public void setProyeccionDetalle(String proyeccionDetalle) {
        this.proyeccionDetalle = proyeccionDetalle;
    }

    public double getCicloCobradoPct() {
        return cicloCobradoPct;
    }

    public void setCicloCobradoPct(double cicloCobradoPct) {
        this.cicloCobradoPct = cicloCobradoPct;
    }

    public double getCicloCobradoMonto() {
        return cicloCobradoMonto;
    }

    public void setCicloCobradoMonto(double cicloCobradoMonto) {
        this.cicloCobradoMonto = cicloCobradoMonto;
    }

    public double getCicloTotalMonto() {
        return cicloTotalMonto;
    }

    public void setCicloTotalMonto(double cicloTotalMonto) {
        this.cicloTotalMonto = cicloTotalMonto;
    }

    public double getCicloPendienteMonto() {
        return cicloPendienteMonto;
    }

    public void setCicloPendienteMonto(double cicloPendienteMonto) {
        this.cicloPendienteMonto = cicloPendienteMonto;
    }

    public String getProximaLiquidacionFecha() {
        return proximaLiquidacionFecha;
    }

    public void setProximaLiquidacionFecha(String proximaLiquidacionFecha) {
        this.proximaLiquidacionFecha = proximaLiquidacionFecha;
    }

    public int getProximaLiquidacionDias() {
        return proximaLiquidacionDias;
    }

    public void setProximaLiquidacionDias(int proximaLiquidacionDias) {
        this.proximaLiquidacionDias = proximaLiquidacionDias;
    }
}
