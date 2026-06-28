package model;

public class MetasResumen {
    private double totalAhorrado;
    private double ahorradoEsteMes;
    private double metaMensualTarget;
    private int totalMetasActivas;

    public double getTotalAhorrado() {
        return totalAhorrado;
    }

    public void setTotalAhorrado(double totalAhorrado) {
        this.totalAhorrado = totalAhorrado;
    }

    public double getAhorradoEsteMes() {
        return ahorradoEsteMes;
    }

    public void setAhorradoEsteMes(double ahorradoEsteMes) {
        this.ahorradoEsteMes = ahorradoEsteMes;
    }

    public double getMetaMensualTarget() {
        return metaMensualTarget;
    }

    public void setMetaMensualTarget(double metaMensualTarget) {
        this.metaMensualTarget = metaMensualTarget;
    }

    public int getTotalMetasActivas() {
        return totalMetasActivas;
    }

    public void setTotalMetasActivas(int totalMetasActivas) {
        this.totalMetasActivas = totalMetasActivas;
    }
}
