package model;

import java.util.Date;

public class MetaAhorro {
    private int idMeta;
    private String descripcion;
    private double montoObjetivo;
    private double montoActual;
    private Date fechaEstimada;
    private String iconPath;

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMontoObjetivo() {
        return montoObjetivo;
    }

    public void setMontoObjetivo(double montoObjetivo) {
        this.montoObjetivo = montoObjetivo;
    }

    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    private double asignacionMensual;

    public double getAsignacionMensual() {
        return asignacionMensual;
    }

    public void setAsignacionMensual(double asignacionMensual) {
        this.asignacionMensual = asignacionMensual;
    }
}
