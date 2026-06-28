package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deudaResumen")
public class DeudaResumen {
    private double totalDeuda;
    private String proximoVencimiento;
    private double totalPagadoEsteMes;

    public double getTotalDeuda() {
        return totalDeuda;
    }

    public void setTotalDeuda(double totalDeuda) {
        this.totalDeuda = totalDeuda;
    }

    public String getProximoVencimiento() {
        return proximoVencimiento;
    }

    public void setProximoVencimiento(String proximoVencimiento) {
        this.proximoVencimiento = proximoVencimiento;
    }

    public double getTotalPagadoEsteMes() {
        return totalPagadoEsteMes;
    }

    public void setTotalPagadoEsteMes(double totalPagadoEsteMes) {
        this.totalPagadoEsteMes = totalPagadoEsteMes;
    }
}
