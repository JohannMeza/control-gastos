package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import model.MetaAhorro;

public class NuevaMetaDialog extends JDialog {
    private JTextField txtDescripcion;
    private JTextField txtMontoObjetivo;
    private JTextField txtAsignacionMensual;
    private JLabel lblProyeccion;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean guardado = false;
    private MetaAhorro nuevaMeta;
    private java.util.List<String> existingMetaNames;

    public void setExistingMetaNames(java.util.List<String> existingMetaNames) {
        this.existingMetaNames = existingMetaNames;
    }

    public NuevaMetaDialog(Frame parent) {
        super(parent, "Nueva Meta de Ahorro", true);
        initComponents();
        setupListeners();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        // Main container layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        // Header Title
        JLabel lblTitle = new JLabel("Nueva Meta de Ahorro");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTitle.setForeground(new Color(11, 28, 48));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        // Subtitle
        JLabel lblSubtitle = new JLabel("Define tu objetivo y asignación mensual requerida.");
        lblSubtitle.setFont(new Font("Dialog", Font.PLAIN, 11));
        lblSubtitle.setForeground(new Color(100, 116, 139));
        gbc.gridy = 1;
        mainPanel.add(lblSubtitle, gbc);

        // Separator
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(226, 232, 240));
        gbc.gridy = 2;
        mainPanel.add(sep, gbc);

        // Row 1: Descripción
        JLabel lblDesc = new JLabel("Descripción / Nombre:");
        lblDesc.setFont(new Font("Dialog", Font.BOLD, 12));
        lblDesc.setForeground(new Color(71, 85, 105));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(lblDesc, gbc);

        txtDescripcion = new JTextField(20);
        txtDescripcion.setFont(new Font("Dialog", Font.PLAIN, 12));
        gbc.gridx = 1;
        mainPanel.add(txtDescripcion, gbc);

        // Row 2: Monto Objetivo
        JLabel lblMonto = new JLabel("Monto Objetivo (S/.):");
        lblMonto.setFont(new Font("Dialog", Font.BOLD, 12));
        lblMonto.setForeground(new Color(71, 85, 105));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(lblMonto, gbc);

        txtMontoObjetivo = new JTextField("0.00");
        txtMontoObjetivo.setFont(new Font("Dialog", Font.PLAIN, 12));
        gbc.gridx = 1;
        mainPanel.add(txtMontoObjetivo, gbc);

        // Row 3: Asignación Mensual
        JLabel lblAsig = new JLabel("Asignación Mensual (S/.):");
        lblAsig.setFont(new Font("Dialog", Font.BOLD, 12));
        lblAsig.setForeground(new Color(71, 85, 105));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(lblAsig, gbc);

        txtAsignacionMensual = new JTextField("0.00");
        txtAsignacionMensual.setFont(new Font("Dialog", Font.PLAIN, 12));
        gbc.gridx = 1;
        mainPanel.add(txtAsignacionMensual, gbc);

        // Proyeccion Live Calculation Box
        JPanel pnlProyeccion = new JPanel();
        pnlProyeccion.setLayout(new BorderLayout());
        pnlProyeccion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(219, 234, 254), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        pnlProyeccion.setBackground(new Color(240, 246, 255));

        lblProyeccion = new JLabel("Ingresa los montos para calcular la proyección.");
        lblProyeccion.setFont(new Font("Dialog", Font.ITALIC | Font.BOLD, 11));
        lblProyeccion.setForeground(new Color(29, 78, 216));
        pnlProyeccion.add(lblProyeccion, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(pnlProyeccion, gbc);

        // Buttons Panel
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlButtons.setBackground(Color.WHITE);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setForeground(new Color(100, 116, 139));
        btnCancelar.setFocusPainted(false);
        pnlButtons.add(btnCancelar);

        btnGuardar = new JButton("Guardar Meta");
        btnGuardar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnGuardar.setBackground(new Color(37, 99, 235));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        pnlButtons.add(btnGuardar);

        gbc.gridy = 7;
        mainPanel.add(pnlButtons, gbc);

        setContentPane(mainPanel);
        setResizable(false);
    }

    private void setupListeners() {
        DocumentListener liveCalcListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { calcularProyeccion(); }
            @Override
            public void removeUpdate(DocumentEvent e) { calcularProyeccion(); }
            @Override
            public void changedUpdate(DocumentEvent e) { calcularProyeccion(); }
        };

        txtMontoObjetivo.getDocument().addDocumentListener(liveCalcListener);
        txtAsignacionMensual.getDocument().addDocumentListener(liveCalcListener);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarYGuardar();
            }
        });
    }

    private void calcularProyeccion() {
        try {
            double objetivo = Double.parseDouble(txtMontoObjetivo.getText().trim());
            double asignacion = Double.parseDouble(txtAsignacionMensual.getText().trim());

            if (objetivo <= 0 || asignacion <= 0) {
                lblProyeccion.setText("El monto y la asignación deben ser mayores que 0.");
                lblProyeccion.setForeground(new Color(185, 28, 28)); // Red
                return;
            }

            double mesesEstimados = Math.ceil(objetivo / asignacion);
            int anios = (int) (mesesEstimados / 12);
            int meses = (int) (mesesEstimados % 12);

            String result = "Proyección: Completarás esta meta en ";
            if (anios > 0) {
                result += anios + (anios == 1 ? " año" : " años");
                if (meses > 0) {
                    result += " y " + meses + (meses == 1 ? " mes" : " meses");
                }
            } else {
                result += meses + (meses == 1 ? " mes" : " meses");
            }
            result += " (" + (int) mesesEstimados + " meses totales).";

            lblProyeccion.setText(result);
            lblProyeccion.setForeground(new Color(29, 78, 216)); // Blue
        } catch (NumberFormatException ex) {
            lblProyeccion.setText("Ingresa montos numéricos válidos.");
            lblProyeccion.setForeground(new Color(185, 28, 28)); // Red
        }
    }

    private void validarYGuardar() {
        String desc = txtDescripcion.getText().trim();
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una descripción o nombre para la meta.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (desc.equalsIgnoreCase("Fondo Emergencia")) {
            boolean alreadyExists = false;
            if (existingMetaNames != null) {
                for (String name : existingMetaNames) {
                    if (name.equalsIgnoreCase("Fondo Emergencia")) {
                        alreadyExists = true;
                        break;
                    }
                }
            }
            if (alreadyExists) {
                JOptionPane.showMessageDialog(this, "Ya existe una meta activa llamada 'Fondo Emergencia'. No se puede duplicar.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        if (desc.equalsIgnoreCase("Ahorro") || desc.equalsIgnoreCase("Ahorros")) {
            JOptionPane.showMessageDialog(this, "No puede usar el nombre 'Ahorro' o 'Ahorros' para una meta de ahorro individual, ya que está reservado para el presupuesto mensual general.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double objetivo = Double.parseDouble(txtMontoObjetivo.getText().trim());
            double asignacion = Double.parseDouble(txtAsignacionMensual.getText().trim());

            if (objetivo <= 0) {
                JOptionPane.showMessageDialog(this, "El monto objetivo debe ser mayor a 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (asignacion <= 0) {
                JOptionPane.showMessageDialog(this, "La asignación mensual debe ser mayor a 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            nuevaMeta = new MetaAhorro();
            nuevaMeta.setDescripcion(desc);
            nuevaMeta.setMontoObjetivo(objetivo);
            nuevaMeta.setMontoActual(0.00);
            nuevaMeta.setAsignacionMensual(asignacion);
            
            // Calculate a raw target date
            int mesesParaMeta = (int) Math.ceil(objetivo / asignacion);
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.add(java.util.Calendar.MONTH, mesesParaMeta);
            nuevaMeta.setFechaEstimada(cal.getTime());
            
            guardado = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa montos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isGuardado() {
        return guardado;
    }

    public MetaAhorro getNuevaMeta() {
        return nuevaMeta;
    }
}
