package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class RegisterView extends javax.swing.JFrame {

    private controller.LoginController controller;
    private boolean mostrandoPass = false;
    private boolean mostrandoConfirm = false;

    public RegisterView() {
        initComponents();
        getContentPane().setPreferredSize(new Dimension(480, 510));
        pack();
        setResizable(false);
        setTitle("Finandes - Crear Usuario");
        setLocationRelativeTo(null);
        setupCustomListeners();
    }

    public RegisterView(controller.LoginController controller) {
        this();
        this.controller = controller;
    }

    private void setupCustomListeners() {
        btnTogglePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mostrandoPass = !mostrandoPass;
                if (mostrandoPass) {
                    txtPassword.setEchoChar((char) 0);
                    btnTogglePass.setIcon(new ImageIcon(getClass().getResource("/images/eye_slash.png")));
                } else {
                    txtPassword.setEchoChar('•');
                    btnTogglePass.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
                }
            }
        });

        btnToggleConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mostrandoConfirm = !mostrandoConfirm;
                if (mostrandoConfirm) {
                    txtConfirmPassword.setEchoChar((char) 0);
                    btnToggleConfirm.setIcon(new ImageIcon(getClass().getResource("/images/eye_slash.png")));
                } else {
                    txtConfirmPassword.setEchoChar('•');
                    btnToggleConfirm.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        pnlTopBar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblFinandes = new javax.swing.JLabel();
        lblCrearUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnTogglePass = new javax.swing.JButton();
        lblConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnToggleConfirm = new javax.swing.JButton();
        chkTerminos = new javax.swing.JCheckBox();
        btnRegistrar = new javax.swing.JButton();
        lblTengoCuenta = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finandes - Crear Usuario");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setPreferredSize(new java.awt.Dimension(480, 530));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTopBar.setBackground(new java.awt.Color(0, 159, 227));
        pnlBackground.add(pnlTopBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 4));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finandes_mountain.png"))); // NOI18N
        pnlBackground.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 18, 70, 54));

        lblFinandes.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblFinandes.setForeground(new java.awt.Color(15, 23, 42));
        lblFinandes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFinandes.setText("Finandes");
        pnlBackground.add(lblFinandes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 76, 200, 28));

        lblCrearUsuario.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblCrearUsuario.setForeground(new java.awt.Color(30, 41, 59));
        lblCrearUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCrearUsuario.setText("Crear Usuario");
        pnlBackground.add(lblCrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 106, 200, 24));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(71, 85, 105));
        lblNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
        lblNombre.setText("  Nombres");
        pnlBackground.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 136, 195, 20));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(15, 23, 42));
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlBackground.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 158, 195, 36));

        lblApellido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(71, 85, 105));
        lblApellido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N
        lblApellido.setText("  Apellidos");
        pnlBackground.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 136, 195, 20));

        txtApellido.setBackground(new java.awt.Color(255, 255, 255));
        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(15, 23, 42));
        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlBackground.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 158, 195, 36));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(71, 85, 105));
        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email.png"))); // NOI18N
        lblEmail.setText("  Email");
        pnlBackground.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 202, 200, 20));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(15, 23, 42));
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlBackground.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 224, 410, 36));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(71, 85, 105));
        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); // NOI18N
        lblPassword.setText("  Contraseña");
        pnlBackground.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 270, 195, 20));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(15, 23, 42));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlBackground.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 292, 157, 36));

        btnTogglePass.setBackground(new java.awt.Color(255, 255, 255));
        btnTogglePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        btnTogglePass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        btnTogglePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTogglePass.setFocusPainted(false);
        pnlBackground.add(btnTogglePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 292, 36, 36));

        lblConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblConfirmPassword.setForeground(new java.awt.Color(71, 85, 105));
        lblConfirmPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); // NOI18N
        lblConfirmPassword.setText("  Repite la contraseña");
        pnlBackground.add(lblConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 195, 20));

        txtConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtConfirmPassword.setForeground(new java.awt.Color(15, 23, 42));
        txtConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlBackground.add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 292, 157, 36));

        btnToggleConfirm.setBackground(new java.awt.Color(255, 255, 255));
        btnToggleConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        btnToggleConfirm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        btnToggleConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnToggleConfirm.setFocusPainted(false);
        pnlBackground.add(btnToggleConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 292, 36, 36));

        chkTerminos.setBackground(new java.awt.Color(255, 255, 255));
        chkTerminos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        chkTerminos.setForeground(new java.awt.Color(71, 85, 105));
        chkTerminos.setText("Términos y condiciones");
        chkTerminos.setFocusPainted(false);
        pnlBackground.add(chkTerminos, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 342, 220, 24));

        btnRegistrar.setBackground(new java.awt.Color(0, 159, 227));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar-usuario.png"))); // NOI18N
        btnRegistrar.setText("  CREAR CUENTA");
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 374, 410, 40));

        lblTengoCuenta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTengoCuenta.setForeground(new java.awt.Color(100, 116, 139));
        lblTengoCuenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTengoCuenta.setText("¿Ya tienes una cuenta?");
        pnlBackground.add(lblTengoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 424, 410, 20));

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 159, 227));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar-sesion.png"))); // NOI18N
        btnVolver.setText("  Iniciar sesion");
        btnVolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 159, 227)));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        pnlBackground.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 450, 410, 38));

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (!chkTerminos.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Debe aceptar los Términos y Condiciones para continuar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (controller != null) {
            controller.procesarRegistro(this);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        dispose();
        new LoginView().setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

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
                new RegisterView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnToggleConfirm;
    private javax.swing.JButton btnTogglePass;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox chkTerminos;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblCrearUsuario;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFinandes;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTengoCuenta;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlTopBar;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextField getTxtNombre() {
        return txtNombre;
    }

    public javax.swing.JTextField getTxtApellido() {
        return txtApellido;
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

    public javax.swing.JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public javax.swing.JButton getBtnVolver() {
        return btnVolver;
    }

    public javax.swing.JCheckBox getChkTerminos() {
        return chkTerminos;
    }
}
