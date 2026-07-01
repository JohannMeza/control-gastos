package util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuHelper {
    public static void setupUserMenu(JFrame currentFrame, JLabel lblAvatar) {
        JPopupMenu popup = new JPopupMenu();
        
        JMenuItem itemPerfil = new JMenuItem("Mi perfil");
        JMenuItem itemCerrar = new JMenuItem("Cerrar sesi\u00f3n");
        
        itemPerfil.setFont(new java.awt.Font("Tahoma", 0, 12));
        itemCerrar.setFont(new java.awt.Font("Tahoma", 0, 12));
        
        popup.add(itemPerfil);
        popup.add(itemCerrar);
        
        itemPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(currentFrame, "Perfil del usuario", "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
        });
        
        itemCerrar.addActionListener(e -> {
            try {
                Class<?> loginViewClass = Class.forName("view.LoginView");
                JFrame loginFrame = (JFrame) loginViewClass.getDeclaredConstructor().newInstance();
                loginFrame.setVisible(true);
                currentFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(currentFrame, "Error al cerrar sesi\u00f3n: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        lblAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.show(lblAvatar, 0, lblAvatar.getHeight());
            }
        });
    }

    public static void addAvatarToHeader(JFrame currentFrame, JPanel headerPanel) {
        JLabel lblAvatar = new JLabel();
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            lblAvatar.setIcon(new ImageIcon(currentFrame.getClass().getResource("/images/icon/blue/usuario.png")));
        } catch (Exception ex) {}
        
        headerPanel.add(lblAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 5, 30, 30));
        setupUserMenu(currentFrame, lblAvatar);
    }
}
