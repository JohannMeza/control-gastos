package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Session;
import model.Usuario;
import model.UsuarioCompartido;
import model.UsuariosResumen;
import service.UsuarioService;
import view.UsuariosView;

public final class UsuarioController {
    private UsuariosView view;
    private UsuarioService service;
    private int idEditando = 0;
    private String ultimoQuery = "";

    public UsuarioController(UsuariosView view) {
        this.view = view;
        this.service = new UsuarioService();

        listarUsuariosCompartidos();
        cargarResumen();
        setupListeners();
    }

    private void setupListeners() {
        // Handle search placeholder
        view.txtBuscar.setForeground(new java.awt.Color(156, 163, 175));
        view.txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (view.txtBuscar.getText().equals("Buscar usuarios...")) {
                    view.txtBuscar.setText("");
                    view.txtBuscar.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (view.txtBuscar.getText().trim().isEmpty()) {
                    view.txtBuscar.setText("Buscar usuarios...");
                    view.txtBuscar.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });

        // Handle search key/document listener for real-time filtering
        view.txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            private void filtrar() {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String query = view.txtBuscar.getText().trim();
                        if (query.equals("Buscar usuarios...")) {
                            query = "";
                        }
                        if (query.equals(ultimoQuery)) {
                            return;
                        }
                        ultimoQuery = query;
                        listarUsuariosCompartidos();
                    }
                });
            }
        });

        // Handle action link clicks on tblUsuarios
        view.tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = view.tblUsuarios.rowAtPoint(evt.getPoint());
                int col = view.tblUsuarios.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 3) {
                    Object actionVal = view.tblUsuarios.getValueAt(row, 3);
                    if (actionVal != null) {
                        String actionStr = actionVal.toString();
                        String email = "";
                        Object userVal = view.tblUsuarios.getValueAt(row, 0);
                        if (userVal != null) {
                            String valStr = userVal.toString();
                            if (valStr.contains("|")) {
                                String[] parts = valStr.split("\\|");
                                if (parts.length > 2) {
                                    email = parts[2].trim();
                                }
                                if (email.equalsIgnoreCase("Invitado recientemente") || email.isEmpty()) {
                                    if (parts.length > 1) {
                                        email = parts[1].trim();
                                    }
                                }
                            }
                        }

                        Usuario activeUser = Session.getInstance().getUsuarioActivo();
                        int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;
                        int idCompartido = 0;

                        List<UsuarioCompartido> list = service.listarUsuariosCompartidos(idUsuarioOwner);
                        for (UsuarioCompartido uc : list) {
                            if (uc.getEmailInvitado().equalsIgnoreCase(email)) {
                                idCompartido = uc.getId();
                                break;
                            }
                        }

                        if (actionStr.equals("CHANGE_REMOVE")) {
                            String[] options = {"Cambiar Rol", "Eliminar / Revocar", "Cancelar"};
                            int choice = JOptionPane.showOptionDialog(view,
                                "Seleccione la acción para el usuario:",
                                "Gestión de Colaborador",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]);

                            if (choice == 0) {
                                try {
                                    String name = "";
                                    String emailLocal = "";
                                    String permiso = "VIEWER";
                                    
                                    for (UsuarioCompartido uc : list) {
                                        if (uc.getId() == idCompartido) {
                                            name = uc.getNombreInvitado();
                                            if (name == null || name.trim().isEmpty()) {
                                                name = uc.getEmailInvitado();
                                            }
                                            emailLocal = uc.getEmailInvitado();
                                            permiso = uc.getPermiso();
                                            break;
                                        }
                                    }
                                    
                                    String soloNombre = name;
                                    if (name.contains(" ")) {
                                        soloNombre = name.split(" ")[0];
                                    }
                                    
                                    idEditando = idCompartido;
                                    view.lblInvitarUsuarioTitle.setText("Actualizar Rol de " + soloNombre);
                                    view.txtCorreo.setText(emailLocal);
                                    view.txtCorreo.setEnabled(false);
                                    view.btnEnviarInvitacion.setText("Actualizar Usuario");
                                    
                                    if (permiso.equalsIgnoreCase("ADMIN")) {
                                        view.cbRol.setSelectedIndex(2);
                                    } else if (permiso.equalsIgnoreCase("EDITOR")) {
                                        view.cbRol.setSelectedIndex(1);
                                    } else {
                                        view.cbRol.setSelectedIndex(0);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (choice == 1) {
                                confirmAndRevoke(idCompartido);
                            }
                        } else if (actionStr.equals("RESEND_REVOKE")) {
                            String[] options = {"Reenviar Invitación", "Revocar Invitación", "Cancelar"};
                            int choice = JOptionPane.showOptionDialog(view,
                                "Seleccione la acción para la invitación:",
                                "Gestión de Invitación",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]);

                            if (choice == 0) {
                                try {
                                    String nombreCompleto = "";
                                    String correoStr = "";
                                    
                                    for (UsuarioCompartido uc : list) {
                                        if (uc.getId() == idCompartido) {
                                            nombreCompleto = uc.getNombreInvitado();
                                            if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                                                nombreCompleto = uc.getEmailInvitado();
                                            }
                                            correoStr = uc.getEmailInvitado();
                                            break;
                                        }
                                    }
                                    
                                    int confirm = JOptionPane.showConfirmDialog(view,
                                        "¿Está seguro de reenviar la invitación a " + nombreCompleto + " con el correo " + correoStr + "?",
                                        "Confirmar Reenvío",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                        
                                    if (confirm == JOptionPane.YES_OPTION) {
                                        service.registrarLogAuditoria(idUsuarioOwner, "REENVIAR INVITACION", "Invitación reenviada a " + nombreCompleto + " (" + correoStr + ")");
                                        JOptionPane.showMessageDialog(view, "Invitación reenviada correctamente.", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                                        cargarResumen();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (choice == 1) {
                                confirmAndRevoke(idCompartido);
                            }
                        }
                    }
                }
            }
        });
    }

    public void listarUsuariosCompartidos() {
        String query = "";
        if (view.txtBuscar != null) {
            query = view.txtBuscar.getText().trim();
            if (query.equals("Buscar usuarios...")) {
                query = "";
            }
        }
        listarUsuariosCompartidosConFiltro(query);
    }

    public void listarUsuariosCompartidosConFiltro(String query) {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            DefaultTableModel model = (DefaultTableModel) view.tblUsuarios.getModel();
            model.setRowCount(0);

            String qLower = query.toLowerCase();

            // 1. Add Owner as the top row
            Usuario owner = service.obtenerUsuario(idUsuarioOwner);
            if (owner != null) {
                boolean matches = qLower.isEmpty() 
                    || owner.getNombre().toLowerCase().contains(qLower) 
                    || owner.getEmail().toLowerCase().contains(qLower);
                if (matches) {
                    String ownerInitials = getInitials(owner.getNombre(), owner.getEmail());
                    String ownerUserCol = ownerInitials + "|" + owner.getNombre() + " (Propietario)|" + owner.getEmail();
                    model.addRow(new Object[]{
                        ownerUserCol,
                        owner.getRol(),
                        "Activo",
                        "SETTINGS",
                        0 // idCompartido = 0 for owner
                    });
                }
            }

            // 2. Add Collaborators
            List<UsuarioCompartido> lista = service.listarUsuariosCompartidos(idUsuarioOwner);
            
            // Build Binary Search Tree (BST)
            BSTNode rootNode = null;
            for (UsuarioCompartido uc : lista) {
                rootNode = insertarBST(rootNode, uc);
            }

            // Search and filter using BST in-order traversal (which returns them already sorted)
            List<UsuarioCompartido> matchingCollaborators = new java.util.ArrayList<>();
            buscarYFiltrarBST(rootNode, qLower, matchingCollaborators);

            int matchingCollaboratorsCount = 0;
            for (UsuarioCompartido uc : matchingCollaborators) {
                matchingCollaboratorsCount++;
                String name = uc.getNombreInvitado();
                if (name == null || name.trim().isEmpty()) {
                    name = uc.getEmailInvitado();
                }
                String initials = getInitials(uc.getNombreInvitado(), uc.getEmailInvitado());
                String emailDesc = uc.getEmailInvitado();
                
                if (uc.getEstado().equalsIgnoreCase("Pendiente")) {
                    initials = "❓";
                    emailDesc = "Invitado recientemente";
                }
                
                String userColValue = initials + "|" + name + "|" + emailDesc;
                String actionCol = uc.getEstado().equalsIgnoreCase("Pendiente") ? "RESEND_REVOKE" : "CHANGE_REMOVE";

                model.addRow(new Object[]{
                    userColValue,
                    uc.getPermiso(),
                    uc.getEstado(),
                    actionCol,
                    uc.getId()
                });
            }

            // Update connections count label
            int totalColaboradores = lista.size();
            view.lblConexionesCount.setText("Mostrando " + matchingCollaboratorsCount + " de " + totalColaboradores + " conexiones activas");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarResumen() {
        try {
            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            UsuariosResumen r = service.obtenerResumen(idUsuarioOwner);
            view.lblAsientosValue.setText(r.getAsientosUsados() + " / " + r.getAsientosTotales());
            
            int progress = (r.getAsientosTotales() > 0) ? (r.getAsientosUsados() * 100) / r.getAsientosTotales() : 0;
            view.pbAsientos.setValue(Math.min(progress, 100));
            
            view.lblCardSincronizacionValue.setText(r.getUltimaSincronizacion());
            view.lblCardMfaValue.setText(r.getMfaActivoStatus());
            view.lblCardAuditoriaValue.setText(r.getLogsAuditoriaCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarInvitacion() {
        try {
            if (idEditando > 0) {
                // Modo Edición
                int index = view.cbRol.getSelectedIndex();
                String permiso = "VIEWER";
                if (index == 1) permiso = "EDITOR";
                else if (index == 2) permiso = "ADMIN";

                String msg = service.actualizarPermiso(idEditando, permiso);
                JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

                // Volver a la normalidad
                idEditando = 0;
                view.lblInvitarUsuarioTitle.setText("Invitar Nuevo Usuario");
                view.txtCorreo.setText("colega@ejemplo.com");
                view.txtCorreo.setEnabled(true);
                view.btnEnviarInvitacion.setText("Enviar Invitación");
                view.cbRol.setSelectedIndex(0);

                listarUsuariosCompartidos();
                cargarResumen();
                return;
            }

            String correo = view.txtCorreo.getText().trim();
            if (correo.isEmpty() || correo.equals("colega@ejemplo.com") || !correo.contains("@")) {
                JOptionPane.showMessageDialog(view, "Debe ingresar un correo electrónico válido.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int index = view.cbRol.getSelectedIndex();
            String permiso = "VIEWER";
            if (index == 1) permiso = "EDITOR";
            else if (index == 2) permiso = "ADMIN";

            Usuario activeUser = Session.getInstance().getUsuarioActivo();
            int idUsuarioOwner = (activeUser != null) ? activeUser.getId() : 1;

            UsuarioCompartido uc = new UsuarioCompartido();
            uc.setUsuarioOwnerId(idUsuarioOwner);
            uc.setEmailInvitado(correo);
            uc.setPermiso(permiso);

            String msg = service.invitarUsuario(uc);
            JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

            view.txtCorreo.setText("colega@ejemplo.com");
            listarUsuariosCompartidos();
            cargarResumen();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al enviar invitación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cambiarRol(int idCompartido) {
        try {
            String[] roles = {"Lector (Solo Lectura)", "Editor (Modificar)", "Administrador (Acceso Total)"};
            String selection = (String) JOptionPane.showInputDialog(
                view,
                "Seleccione el nuevo rol de acceso:",
                "Cambiar Rol de Colaborador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roles,
                roles[0]
            );

            if (selection != null) {
                String permiso = "VIEWER";
                if (selection.contains("Editor")) permiso = "EDITOR";
                else if (selection.contains("Administrador")) permiso = "ADMIN";

                String msg = service.actualizarPermiso(idCompartido, permiso);
                JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

                listarUsuariosCompartidos();
                cargarResumen();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al cambiar rol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void confirmAndRevoke(int idCompartido) {
        try {
            int confirm = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de que desea revocar el acceso a este usuario?",
                "Confirmar Revocación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String msg = service.revocarAcceso(idCompartido);
                JOptionPane.showMessageDialog(view, msg, "Sistema", JOptionPane.INFORMATION_MESSAGE);

                listarUsuariosCompartidos();
                cargarResumen();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al revocar acceso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportarCSV() {
        service.exportarCSV(view.tblUsuarios);
    }

    private String getInitials(String name, String email) {
        if (name != null && !name.trim().isEmpty()) {
            String[] parts = name.trim().split("\\s+");
            if (parts.length >= 2) {
                return (parts[0].substring(0, 1) + parts[1].substring(0, 1)).toUpperCase();
            } else if (name.trim().length() >= 2) {
                return name.trim().substring(0, 2).toUpperCase();
            }
        }
        if (email != null && email.contains("@")) {
            String prefix = email.split("@")[0];
            if (prefix.length() >= 2) {
                return prefix.substring(0, 2).toUpperCase();
            } else if (prefix.length() == 1) {
                return prefix.toUpperCase();
            }
        }
        return "❓";
    }

    private String getResolvedName(UsuarioCompartido uc) {
        String name = uc.getNombreInvitado();
        if (name == null || name.trim().isEmpty()) {
            name = uc.getEmailInvitado();
        }
        return name != null ? name.toLowerCase() : "";
    }

    private void quicksort(List<UsuarioCompartido> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quicksort(list, low, pi - 1);
            quicksort(list, pi + 1, high);
        }
    }

    private int partition(List<UsuarioCompartido> list, int low, int high) {
        UsuarioCompartido pivot = list.get(high);
        String pivotName = getResolvedName(pivot);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            String currentName = getResolvedName(list.get(j));
            if (currentName.compareTo(pivotName) <= 0) {
                i++;
                // Swap
                UsuarioCompartido temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        // Swap list[i+1] and list[high]
        UsuarioCompartido temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    private static class BSTNode {
        UsuarioCompartido data;
        BSTNode left;
        BSTNode right;

        BSTNode(UsuarioCompartido data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode insertarBST(BSTNode root, UsuarioCompartido uc) {
        if (root == null) {
            return new BSTNode(uc);
        }
        String name1 = getResolvedName(uc);
        String name2 = getResolvedName(root.data);
        if (name1.compareTo(name2) < 0) {
            root.left = insertarBST(root.left, uc);
        } else {
            root.right = insertarBST(root.right, uc);
        }
        return root;
    }

    private void buscarYFiltrarBST(BSTNode node, String qLower, List<UsuarioCompartido> result) {
        if (node == null) {
            return;
        }
        // In-order traversal: left, root, right (alphabetical order)
        buscarYFiltrarBST(node.left, qLower, result);

        String name = getResolvedName(node.data);
        String email = node.data.getEmailInvitado();
        
        boolean matches = qLower.isEmpty() 
            || (name != null && name.toLowerCase().contains(qLower)) 
            || (email != null && email.toLowerCase().contains(qLower));

        if (matches) {
            result.add(node.data);
        }

        buscarYFiltrarBST(node.right, qLower, result);
    }
}
