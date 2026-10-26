package model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public Usuario(int id, String nombre, String apellidos, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNombreCompleto() {
        if (apellidos != null && !apellidos.trim().isEmpty()) {
            return (nombre != null ? nombre.trim() : "") + " " + apellidos.trim();
        }
        return nombre != null ? nombre : "";
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean esAdmin() {
        return "ADMIN".equalsIgnoreCase(rol) || "ADMINISTRADOR".equalsIgnoreCase(rol);
    }

    public boolean esLector() {
        return "LECTOR".equalsIgnoreCase(rol) || "LECTURA".equalsIgnoreCase(rol);
    }

    public boolean puedeEditar() {
        return !esLector();
    }
}