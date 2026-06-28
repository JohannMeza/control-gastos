package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dashboardGraficos")
public class DashboardGraficos {
    private String categoria;
    private String descripcion;
    private double limite;
    private double gasto;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }

    public double getGasto() { return gasto; }
    public void setGasto(double gasto) { this.gasto = gasto; }
}
