package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroAuditoria {
    private int idLog;
    private String accion;
    private String detalle;
    private String timestamp;

    public RegistroAuditoria(int idLog, String accion, String detalle) {
        this.idLog = idLog;
        this.accion = accion;
        this.detalle = detalle;
        this.timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public int getIdLog() { return idLog; }
    public String getAccion() { return accion; }
    public String getDetalle() { return detalle; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + accion + ": " + detalle;
    }
}
