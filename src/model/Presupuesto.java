/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "presupuesto")
public class Presupuesto {
    private int idPresupuesto;
    private int idCategoria;
    private String categoria;
    private int idTipoPeriodo;
    private String tipoPeriodo;
    private int idEstadoPresupuesto;
    private String estadoPresupuesto;
    private double limPresuMens;
    private int umbralAlerta;
    
    private double montoAsignado;
    private double montoGastado;
    private String progresoPresupuesto;

    public String getProgresoPresupuesto() {
        return progresoPresupuesto;
    }

    public void setProgresoPresupuesto(String progresoPresupuesto) {
        this.progresoPresupuesto = progresoPresupuesto;
    }

    public double getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(double montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public double getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(double montoGastado) {
        this.montoGastado = montoGastado;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(int idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public int getIdEstadoPresupuesto() {
        return idEstadoPresupuesto;
    }

    public void setIdEstadoPresupuesto(int idEstadoPresupuesto) {
        this.idEstadoPresupuesto = idEstadoPresupuesto;
    }

    public String getEstadoPresupuesto() {
        return estadoPresupuesto;
    }

    public void setEstadoPresupuesto(String estadoPresupuesto) {
        this.estadoPresupuesto = estadoPresupuesto;
    }

    public double getLimPresuMens() {
        return limPresuMens;
    }

    public void setLimPresuMens(double limPresuMens) {
        this.limPresuMens = limPresuMens;
    }

    private int idUsuario;

    public int getUmbralAlerta() {
        return umbralAlerta;
    }

    public void setUmbralAlerta(int umbralAlerta) {
        this.umbralAlerta = umbralAlerta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
