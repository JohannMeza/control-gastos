package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuariosResumen")
public class UsuariosResumen {
    private int asientosUsados;
    private int asientosTotales;
    private String ultimaSincronizacion;
    private String mfaActivoStatus;
    private String logsAuditoriaCount;

    public int getAsientosUsados() {
        return asientosUsados;
    }

    public void setAsientosUsados(int asientosUsados) {
        this.asientosUsados = asientosUsados;
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public void setAsientosTotales(int asientosTotales) {
        this.asientosTotales = asientosTotales;
    }

    public String getUltimaSincronizacion() {
        return ultimaSincronizacion;
    }

    public void setUltimaSincronizacion(String ultimaSincronizacion) {
        this.ultimaSincronizacion = ultimaSincronizacion;
    }

    public String getMfaActivoStatus() {
        return mfaActivoStatus;
    }

    public void setMfaActivoStatus(String mfaActivoStatus) {
        this.mfaActivoStatus = mfaActivoStatus;
    }

    public String getLogsAuditoriaCount() {
        return logsAuditoriaCount;
    }

    public void setLogsAuditoriaCount(String logsAuditoriaCount) {
        this.logsAuditoriaCount = logsAuditoriaCount;
    }
}
