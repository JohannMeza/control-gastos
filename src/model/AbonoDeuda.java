package model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "abonoDeuda")
public class AbonoDeuda {
    private int idAbono;
    private int idDeuda;
    private double montoAbono;
    private Date fechaPago;
    private String fechaPagoFormat;
    
    // Auxiliary fields for UI
    private String nombreDeuda;
    private double saldoRestante;

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public int getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(int idDeuda) {
        this.idDeuda = idDeuda;
    }

    public double getMontoAbono() {
        return montoAbono;
    }

    public void setMontoAbono(double montoAbono) {
        this.montoAbono = montoAbono;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPagoFormat() {
        return fechaPagoFormat;
    }

    public void setFechaPagoFormat(String fechaPagoFormat) {
        this.fechaPagoFormat = fechaPagoFormat;
    }

    public String getNombreDeuda() {
        return nombreDeuda;
    }

    public void setNombreDeuda(String nombreDeuda) {
        this.nombreDeuda = nombreDeuda;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }
}
