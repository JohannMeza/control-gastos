package model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ingreso")
public class Ingreso {
    private int id;
    private double monto;
    private String descripcion;
    private Date fecha;
    private String fechaFormat;
    private int usuarioId;

    private int idCategoria;
    private String categoria;
    private int idCuentaDestino;
    private String cuentaDestino;
    private String tipoFlujo; // "Fijo", "Variable"
    private String estado;    // "Confirmado", "Pendiente"
    private boolean recurrente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormat() {
        return fechaFormat;
    }

    public void setFechaFormat(String fechaFormat) {
        this.fechaFormat = fechaFormat;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getTipoFlujo() {
        return tipoFlujo;
    }

    public void setTipoFlujo(String tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }
}
