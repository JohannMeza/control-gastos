package model;

public class PagoProgramado {
    private String concepto;
    private double monto;
    private String fechaVencimiento;

    public PagoProgramado() {}

    public PagoProgramado(String concepto, double monto, String fechaVencimiento) {
        this.concepto = concepto;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}
