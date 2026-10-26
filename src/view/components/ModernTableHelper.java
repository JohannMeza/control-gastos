package view.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Utilitario global para estilizar tablas Swing y dejarlas con el aspecto moderno
 * del Sistema Finandes según AGENTS.md y PLAN_IMPLEMENTACION.md.
 */
public final class ModernTableHelper {

    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$#,##0.00", new DecimalFormatSymbols(Locale.US));

    private ModernTableHelper() {}

    /**
     * Aplica el estilo visual completo corporativo a una JTable:
     * Cabecera #F8FAFC (36px), filas 40px, separadores #F1F5F9, selección #F0FDF4.
     */
    public static void styleTable(JTable table) {
        if (table == null) return;

        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(ThemeColor.TEXTO_TITULO);
        table.setBackground(ThemeColor.FONDO_CARD);
        table.setSelectionBackground(ThemeColor.TABLA_ROW_SELECTED);
        table.setSelectionForeground(ThemeColor.TEXTO_TITULO);
        table.setGridColor(ThemeColor.TABLA_ROW_DIVIDER);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFocusable(false);
        table.setFillsViewportHeight(true);

        // Estilizar Cabecera
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setPreferredSize(new Dimension(header.getWidth(), 36));
            header.setResizingAllowed(true);
            header.setReorderingAllowed(false);
            header.setDefaultRenderer(new TableCellRenderer() {
                private final TableCellRenderer defaultRenderer = header.getDefaultRenderer();

                @Override
                public Component getTableCellRendererComponent(JTable tbl, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = defaultRenderer.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                    if (c instanceof JLabel) {
                        JLabel lbl = (JLabel) c;
                        String txt = value != null ? value.toString().toUpperCase() : "";
                        lbl.setText(txt);
                        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        lbl.setBackground(ThemeColor.TABLA_HEADER_BG);
                        lbl.setForeground(ThemeColor.TABLA_HEADER_TEXT);
                        lbl.setOpaque(true);
                        lbl.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 0, 1, 0, ThemeColor.BORDE_CARD),
                                BorderFactory.createEmptyBorder(0, 12, 0, 12)
                        ));
                        lbl.setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    return c;
                }
            });
        }

        // Renderer por defecto de celdas con padding
        DefaultTableCellRenderer defaultCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel lbl = (JLabel) c;
                    lbl.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                    if (!isSelected) {
                        lbl.setBackground(ThemeColor.FONDO_CARD);
                        lbl.setForeground(ThemeColor.TEXTO_TITULO);
                    }
                }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, defaultCellRenderer);
    }

    /**
     * Configura la alineación horizontal de una columna específica.
     */
    public static void setColumnAlignment(JTable table, int columnIndex, int alignment) {
        if (table == null || columnIndex < 0 || columnIndex >= table.getColumnCount()) return;

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel lbl = (JLabel) c;
                    lbl.setHorizontalAlignment(alignment);
                    lbl.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                    if (!isSelected) {
                        lbl.setBackground(ThemeColor.FONDO_CARD);
                        lbl.setForeground(ThemeColor.TEXTO_TITULO);
                    }
                }
                return c;
            }
        };
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
    }

    /**
     * Aplica alineación a la derecha para columnas monetarias o numéricas con formato $#,##0.00.
     */
    public static void setMoneyColumn(JTable table, int... columnIndices) {
        if (table == null) return;
        TableColumnModel colModel = table.getColumnModel();

        for (int colIndex : columnIndices) {
            if (colIndex >= 0 && colIndex < colModel.getColumnCount()) {
                DefaultTableCellRenderer moneyRenderer = new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable tbl, Object value,
                            boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                        if (c instanceof JLabel) {
                            JLabel lbl = (JLabel) c;
                            lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                            lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
                            lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 12));

                            if (value instanceof Number) {
                                lbl.setText(MONEY_FORMAT.format(((Number) value).doubleValue()));
                            } else if (value != null) {
                                String str = value.toString().replace("$", "").replace(",", "").trim();
                                try {
                                    double val = Double.parseDouble(str);
                                    lbl.setText(MONEY_FORMAT.format(val));
                                } catch (NumberFormatException ignored) {
                                    lbl.setText(value.toString());
                                }
                            }
                            if (!isSelected) {
                                lbl.setBackground(ThemeColor.FONDO_CARD);
                                lbl.setForeground(ThemeColor.TEXTO_TITULO);
                            }
                        }
                        return c;
                    }
                };
                colModel.getColumn(colIndex).setCellRenderer(moneyRenderer);
            }
        }
    }

    /**
     * Asigna el renderizador tipo badge/píldora semafórica a las columnas indicadas.
     */
    public static void setBadgeColumn(JTable table, int... columnIndices) {
        if (table == null) return;
        TableColumnModel colModel = table.getColumnModel();
        for (int colIndex : columnIndices) {
            if (colIndex >= 0 && colIndex < colModel.getColumnCount()) {
                colModel.getColumn(colIndex).setCellRenderer(new StatusBadgeRenderer());
            }
        }
    }

    /**
     * Aplica el estilo moderno al JScrollPane contenedor de la tabla.
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        if (scrollPane == null) return;
        scrollPane.setBorder(BorderFactory.createLineBorder(ThemeColor.BORDE_CARD, 1));
        scrollPane.getViewport().setBackground(ThemeColor.FONDO_CARD);
        scrollPane.setBackground(ThemeColor.FONDO_CARD);
    }
}
