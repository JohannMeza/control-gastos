package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GastoAuditoria implements Comparable<GastoAuditoria> {
    private int idGasto;
    private String descripcion;
    private double monto;
    private String categoria;
    private String fecha;

    public GastoAuditoria(int idGasto, String descripcion, double monto, String categoria) {
        this.idGasto = idGasto;
        this.descripcion = descripcion;
        this.monto = monto;
        this.categoria = categoria;
        this.fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public int getIdGasto() { return idGasto; }
    public String getDescripcion() { return descripcion; }
    public double getMonto() { return monto; }
    public String getCategoria() { return categoria; }
    public String getFecha() { return fecha; }

    public void setMonto(double monto) { this.monto = monto; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public int compareTo(GastoAuditoria o) {
        return Double.compare(this.monto, o.monto); // Ordenación por monto (menor a mayor)
    }

    @Override
    public String toString() {
        return descripcion + " - S/ " + String.format("%.2f", monto);
    }
}
