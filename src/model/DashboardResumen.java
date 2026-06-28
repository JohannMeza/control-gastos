package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dashboardResumen")
public class DashboardResumen {
    private double ingresosMes;
    private String ingresosCambio;
    private double gastosMes;
    private String gastosCambio;
    private double ahorrosMes;
    private String ahorrosCambio;
    private double balanceMes;
    private String balanceCambio;
    
    private double upcomingPayInternetAmount;
    private String upcomingPayInternetDate;
    private double upcomingPayHealthAmount;
    private String upcomingPayHealthDate;
    
    private String bannerTitle;
    private String bannerDesc;

    public double getIngresosMes() { return ingresosMes; }
    public void setIngresosMes(double ingresosMes) { this.ingresosMes = ingresosMes; }

    public String getIngresosCambio() { return ingresosCambio; }
    public void setIngresosCambio(String ingresosCambio) { this.ingresosCambio = ingresosCambio; }

    public double getGastosMes() { return gastosMes; }
    public void setGastosMes(double gastosMes) { this.gastosMes = gastosMes; }

    public String getGastosCambio() { return gastosCambio; }
    public void setGastosCambio(String gastosCambio) { this.gastosCambio = gastosCambio; }

    public double getAhorrosMes() { return ahorrosMes; }
    public void setAhorrosMes(double ahorrosMes) { this.ahorrosMes = ahorrosMes; }

    public String getAhorrosCambio() { return ahorrosCambio; }
    public void setAhorrosCambio(String ahorrosCambio) { this.ahorrosCambio = ahorrosCambio; }

    public double getBalanceMes() { return balanceMes; }
    public void setBalanceMes(double balanceMes) { this.balanceMes = balanceMes; }

    public String getBalanceCambio() { return balanceCambio; }
    public void setBalanceCambio(String balanceCambio) { this.balanceCambio = balanceCambio; }

    public double getUpcomingPayInternetAmount() { return upcomingPayInternetAmount; }
    public void setUpcomingPayInternetAmount(double upcomingPayInternetAmount) { this.upcomingPayInternetAmount = upcomingPayInternetAmount; }

    public String getUpcomingPayInternetDate() { return upcomingPayInternetDate; }
    public void setUpcomingPayInternetDate(String upcomingPayInternetDate) { this.upcomingPayInternetDate = upcomingPayInternetDate; }

    public double getUpcomingPayHealthAmount() { return upcomingPayHealthAmount; }
    public void setUpcomingPayHealthAmount(double upcomingPayHealthAmount) { this.upcomingPayHealthAmount = upcomingPayHealthAmount; }

    public String getUpcomingPayHealthDate() { return upcomingPayHealthDate; }
    public void setUpcomingPayHealthDate(String upcomingPayHealthDate) { this.upcomingPayHealthDate = upcomingPayHealthDate; }

    public String getBannerTitle() { return bannerTitle; }
    public void setBannerTitle(String bannerTitle) { this.bannerTitle = bannerTitle; }

    public String getBannerDesc() { return bannerDesc; }
    public void setBannerDesc(String bannerDesc) { this.bannerDesc = bannerDesc; }
}
