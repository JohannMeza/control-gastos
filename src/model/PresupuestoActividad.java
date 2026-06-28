package model;

public class PresupuestoActividad {
    private int idGasto;
    private String cGasto;
    private String nombreCategoria;
    private double montoGasto;
    private String cFechaGasto;
    private double limitePresupuesto;
    private int umbralAlerta;
    private double totalGastadoMes;

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getcGasto() {
        return cGasto;
    }

    public void setcGasto(String cGasto) {
        this.cGasto = cGasto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public double getMontoGasto() {
        return montoGasto;
    }

    public void setMontoGasto(double montoGasto) {
        this.montoGasto = montoGasto;
    }

    public String getcFechaGasto() {
        return cFechaGasto;
    }

    public void setcFechaGasto(String cFechaGasto) {
        this.cFechaGasto = cFechaGasto;
    }

    public double getLimitePresupuesto() {
        return limitePresupuesto;
    }

    public void setLimitePresupuesto(double limitePresupuesto) {
        this.limitePresupuesto = limitePresupuesto;
    }

    public int getUmbralAlerta() {
        return umbralAlerta;
    }

    public void setUmbralAlerta(int umbralAlerta) {
        this.umbralAlerta = umbralAlerta;
    }

    public double getTotalGastadoMes() {
        return totalGastadoMes;
    }

    public void setTotalGastadoMes(double totalGastadoMes) {
        this.totalGastadoMes = totalGastadoMes;
    }
}
