package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarioCompartido")
public class UsuarioCompartido {
    private int id;
    private int usuarioOwnerId;
    private int usuarioInvitadoId;
    private String permiso;
    
    // Auxiliary fields for displaying details
    private String nombreOwner;
    private String nombreInvitado;
    private String emailInvitado;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioOwnerId() {
        return usuarioOwnerId;
    }

    public void setUsuarioOwnerId(int usuarioOwnerId) {
        this.usuarioOwnerId = usuarioOwnerId;
    }

    public int getUsuarioInvitadoId() {
        return usuarioInvitadoId;
    }

    public void setUsuarioInvitadoId(int usuarioInvitadoId) {
        this.usuarioInvitadoId = usuarioInvitadoId;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getNombreOwner() {
        return nombreOwner;
    }

    public void setNombreOwner(String nombreOwner) {
        this.nombreOwner = nombreOwner;
    }

    public String getNombreInvitado() {
        return nombreInvitado;
    }

    public void setNombreInvitado(String nombreInvitado) {
        this.nombreInvitado = nombreInvitado;
    }

    public String getEmailInvitado() {
        return emailInvitado;
    }

    public void setEmailInvitado(String emailInvitado) {
        this.emailInvitado = emailInvitado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
