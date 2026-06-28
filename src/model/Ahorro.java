package model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ahorro")
public class Ahorro {
    private int idAhorro;
    private int idMeta;
    private String nombreMeta;
    private double monto;
    private Date fechaAhorro;
    private String fechaAhorroFormat;
    private String descripcion;
    private int idUsuario;
    private String nombreUsuario;

    public int getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(int idAhorro) {
        this.idAhorro = idAhorro;
    }

    public int getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(int idMeta) {
        this.idMeta = idMeta;
    }

    public String getNombreMeta() {
        return nombreMeta;
    }

    public void setNombreMeta(String nombreMeta) {
        this.nombreMeta = nombreMeta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaAhorro() {
        return fechaAhorro;
    }

    public void setFechaAhorro(Date fechaAhorro) {
        this.fechaAhorro = fechaAhorro;
    }

    public String getFechaAhorroFormat() {
        return fechaAhorroFormat;
    }

    public void setFechaAhorroFormat(String fechaAhorroFormat) {
        this.fechaAhorroFormat = fechaAhorroFormat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
