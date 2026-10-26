package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class LoginView extends javax.swing.JFrame {

    private controller.LoginController controller;
    private boolean mostrandoPassword = false;

    public LoginView() {
        initComponents();
        getContentPane().setPreferredSize(new Dimension(420, 480));
        pack();
        setResizable(false);
        setTitle("Finandes - Iniciar Sesión");
        setLocationRelativeTo(null);
        this.controller = new controller.LoginController(this);

        btn_logiin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                controller.abrirRegistro();
            }
        });

        btnTogglePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mostrandoPassword = !mostrandoPassword;
                if (mostrandoPassword) {
                    txt_password.setEchoChar((char) 0);
                    btnTogglePass.setIcon(new ImageIcon(getClass().getResource("/images/eye_slash.png")));
                } else {
                    txt_password.setEchoChar('•');
                    btnTogglePass.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
                }
            }
        });

        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblFinandes = new javax.swing.JLabel();
        lblIniciarSesion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_userName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblForgot = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        btnTogglePass = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnIniciarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_logiin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finandes - Iniciar Sesión");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(420, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 159, 227));
        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 4));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finandes_mountain.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 22, 70, 54));

        lblFinandes.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblFinandes.setForeground(new java.awt.Color(15, 23, 42));
        lblFinandes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFinandes.setText("Finandes");
        jPanel2.add(lblFinandes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 82, 200, 28));

        lblIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblIniciarSesion.setForeground(new java.awt.Color(30, 41, 59));
        lblIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIniciarSesion.setText("Iniciar Sesión");
        jPanel2.add(lblIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 112, 200, 24));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(71, 85, 105));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email.png"))); // NOI18N
        jLabel2.setText("  Correo Electrónico");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 148, 200, 20));

        txt_userName.setBackground(new java.awt.Color(255, 255, 255));
        txt_userName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_userName.setForeground(new java.awt.Color(15, 23, 42));
        txt_userName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        jPanel2.add(txt_userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 170, 350, 38));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(71, 85, 105));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); // NOI18N
        jLabel3.setText("  Contraseña");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 218, 130, 20));

        lblForgot.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblForgot.setForeground(new java.awt.Color(0, 159, 227));
        lblForgot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblForgot.setText("¿Olvidaste tu contraseña?");
        lblForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lblForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 218, 170, 20));

        txt_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_password.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_password.setForeground(new java.awt.Color(15, 23, 42));
        txt_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 240, 310, 38));

        btnTogglePass.setBackground(new java.awt.Color(255, 255, 255));
        btnTogglePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        btnTogglePass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        btnTogglePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTogglePass.setFocusPainted(false);
        jPanel2.add(btnTogglePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 240, 38, 38));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(71, 85, 105));
        jCheckBox1.setText("Recordar sesión");
        jCheckBox1.setFocusPainted(false);
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 288, 180, 24));

        btnIniciarSesion.setBackground(new java.awt.Color(0, 159, 227));
        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar-sesion.png"))); // NOI18N
        btnIniciarSesion.setText("  INICIAR SESIÓN");
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 324, 350, 42));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 116, 139));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¿No tienes una cuenta?");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 380, 350, 20));

        btn_logiin.setBackground(new java.awt.Color(255, 255, 255));
        btn_logiin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_logiin.setForeground(new java.awt.Color(0, 159, 227));
        btn_logiin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar-usuario.png"))); // NOI18N
        btn_logiin.setText("  Crear nueva cuenta");
        btn_logiin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 159, 227)));
        btn_logiin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_logiin.setFocusPainted(false);
        jPanel2.add(btn_logiin, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 408, 350, 38));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        controller.procesarLogin();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // fallback
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnTogglePass;
    private javax.swing.JButton btn_logiin;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFinandes;
    private javax.swing.JLabel lblForgot;
    private javax.swing.JLabel lblIniciarSesion;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_userName;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextField getTxtUserName() {
        return txt_userName;
    }

    public javax.swing.JPasswordField getTxtPassword() {
        return txt_password;
    }

    public javax.swing.JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public javax.swing.JButton getBtnCrearCuenta() {
        return btn_logiin;
    }
}
