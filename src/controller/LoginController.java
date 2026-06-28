package controller;

import model.Usuario;
import model.Session;
import service.UsuarioService;
import view.LoginView;
import view.RegisterView;
import view.DashboardView;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginView loginView;
    private UsuarioService service;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.service = new UsuarioService();
    }

    public void procesarLogin() {
        String email = loginView.getTxtUserName().getText().trim();
        String password = new String(loginView.getTxtPassword().getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, 
                "Por favor, ingrese su correo electrónico y contraseña.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Usuario usuario = service.validarLogin(email, password);
            if (usuario != null) {
                // Establecer el usuario activo en la sesión
                Session.getInstance().setUsuarioActivo(usuario);
                
                // Mostrar Dashboard y cerrar Login
                DashboardView dashboard = new DashboardView();
                dashboard.setVisible(true);
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, 
                    "No se pudo iniciar sesión. Verifique sus credenciales.", 
                    "Error de Acceso", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            // Limpiar los prefijos de excepciones típicas de SQL Server si se desea, o mostrar completo
            if (msg.contains("State:")) {
                // Opcional: limpiar mensajes de SQL Server THROW
                int idx = msg.lastIndexOf("State:");
                if (idx != -1) {
                    msg = msg.substring(idx + 8).trim();
                }
            }
            JOptionPane.showMessageDialog(loginView, 
                msg, 
                "Error de Acceso", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirRegistro() {
        loginView.setVisible(false);
        RegisterView regView = new RegisterView(this);
        regView.setVisible(true);
    }

    public void procesarRegistro(RegisterView regView) {
        String nombre = regView.getTxtNombre().getText().trim();
        String email = regView.getTxtEmail().getText().trim();
        String password = new String(regView.getTxtPassword().getPassword());
        String confirmPassword = new String(regView.getTxtConfirmPassword().getPassword());

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(regView, 
                "Todos los campos son obligatorios.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(regView, 
                "Las contraseñas ingresadas no coinciden.", 
                "Error de Contraseña", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String cMsje = service.registrarUsuario(nombre, email, password);
            JOptionPane.showMessageDialog(regView, 
                cMsje, 
                "Registro Exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Volver al Login
            regView.dispose();
            loginView.setVisible(true);
            loginView.getTxtUserName().setText(email); // Autocompletar el correo en el login
            loginView.getTxtPassword().setText("");
        } catch (Exception e) {
            String msg = e.getMessage();
            JOptionPane.showMessageDialog(regView, 
                msg, 
                "Error de Registro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void volverALogin(RegisterView regView) {
        regView.dispose();
        loginView.setVisible(true);
    }
}
