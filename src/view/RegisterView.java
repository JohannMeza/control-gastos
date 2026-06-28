package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.netbeans.lib.awtextra.*;

public class RegisterView extends javax.swing.JFrame {

    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblTengoCuenta;
    private javax.swing.JPanel pnlTopBar;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtConfirmPassword;

    private controller.LoginController controller;

    public RegisterView(controller.LoginController controller) {
        this.controller = controller;
        initComponents();
        setSize(400, 600);
        setResizable(false);
        setTitle("Registro de Usuario - Control de Gastos");
        setLocationRelativeTo(null);
        this.repaint();
    }

    private void initComponents() {
        pnlBackground = new javax.swing.JPanel();
        pnlTopBar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnRegistrar = new javax.swing.JButton();
        lblTengoCuenta = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Top thin blue bar
        pnlTopBar.setBackground(new java.awt.Color(0, 74, 198));
        pnlBackground.add(pnlTopBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 10));

        // Logo
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/limpio vertical.png")));
        pnlBackground.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 15, 90, 100));

        // Nombre Completo
        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 16));
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png")));
        lblNombre.setText(" Nombre Completo");
        pnlBackground.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        pnlBackground.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 145, 340, 35));

        // Correo Electrónico
        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16));
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email.png")));
        lblEmail.setText(" Correo Electrónico");
        pnlBackground.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        pnlBackground.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 215, 340, 35));

        // Contraseña
        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 16));
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png")));
        lblPassword.setText(" Contraseña");
        pnlBackground.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtPassword.setForeground(new java.awt.Color(51, 51, 51));
        pnlBackground.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 285, 340, 35));

        // Confirmar Contraseña
        lblConfirmPassword.setFont(new java.awt.Font("Tahoma", 0, 16));
        lblConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblConfirmPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png")));
        lblConfirmPassword.setText(" Confirmar Contraseña");
        pnlBackground.add(lblConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        txtConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtConfirmPassword.setForeground(new java.awt.Color(51, 51, 51));
        pnlBackground.add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 355, 340, 35));

        // Registrar Button
        btnRegistrar.setBackground(new java.awt.Color(0, 74, 198));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar-usuario.png")));
        btnRegistrar.setText("REGISTRAR CUENTA");
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 340, 40));

        // Text
        lblTengoCuenta.setText("¿Ya tienes una cuenta?");
        pnlBackground.add(lblTengoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 480, -1, -1));

        // Volver Button
        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setFont(new java.awt.Font("Tahoma", 0, 18));
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar-sesion.png")));
        btnVolver.setText("VOLVER AL LOGIN");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        pnlBackground.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 340, 35));

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 600));

        pack();
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        controller.procesarRegistro(this);
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        controller.volverALogin(this);
    }

    // Getters
    public javax.swing.JTextField getTxtNombre() {
        return txtNombre;
    }

    public javax.swing.JTextField getTxtEmail() {
        return txtEmail;
    }

    public javax.swing.JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public javax.swing.JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }
}
