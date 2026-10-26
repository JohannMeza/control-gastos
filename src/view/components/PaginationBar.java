package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Barra de paginación para tablas con indicador de registros y botones numerados.
 * JavaBean visual compatible con NetBeans GUI Builder.
 */
public class PaginationBar extends JPanel {

    public interface PageChangeListener {
        void onPageChanged(int newPage);
    }

    private int currentPage = 1;
    private int totalRecords = 0;
    private int pageSize = 10;
    private int totalPages = 1;

    private JLabel lblInfo;
    private JPanel pnlButtons;
    private final List<PageChangeListener> listeners = new ArrayList<>();

    public PaginationBar() {
        initComponents();
        updatePagination(1, 0, 10);
    }

    public PaginationBar(int currentPage, int totalRecords, int pageSize) {
        initComponents();
        updatePagination(currentPage, totalRecords, pageSize);
    }

    private void initComponents() {
        setOpaque(false);
        setLayout(new BorderLayout(16, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setPreferredSize(new Dimension(600, 48));

        lblInfo = new JLabel("Mostrando 0 a 0 de 0 transacciones");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInfo.setForeground(ThemeColor.TEXTO_SECUNDARIO);
        add(lblInfo, BorderLayout.WEST);

        pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        pnlButtons.setOpaque(false);
        add(pnlButtons, BorderLayout.EAST);
    }

    public void updatePagination(int currentPage, int totalRecords, int pageSize) {
        this.currentPage = Math.max(1, currentPage);
        this.totalRecords = Math.max(0, totalRecords);
        this.pageSize = Math.max(1, pageSize);
        this.totalPages = (int) Math.ceil((double) this.totalRecords / this.pageSize);
        if (this.totalPages == 0) this.totalPages = 1;
        if (this.currentPage > this.totalPages) this.currentPage = this.totalPages;

        // Actualizar texto informativo
        if (this.totalRecords == 0) {
            lblInfo.setText("Mostrando 0 transacciones");
        } else {
            int desde = ((this.currentPage - 1) * this.pageSize) + 1;
            int hasta = Math.min(this.currentPage * this.pageSize, this.totalRecords);
            lblInfo.setText(String.format("Mostrando %d a %d de %d transacciones", desde, hasta, this.totalRecords));
        }

        rebuildButtons();
    }

    private void rebuildButtons() {
        pnlButtons.removeAll();

        // Botón Anterior (<)
        JButton btnPrev = createPageButton("<", currentPage > 1, false);
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    setPage(currentPage - 1);
                }
            }
        });
        pnlButtons.add(btnPrev);

        // Rango de páginas a mostrar (máximo 5 páginas visibles)
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, startPage + 4);
        if (endPage - startPage < 4) {
            startPage = Math.max(1, endPage - 4);
        }

        for (int p = startPage; p <= endPage; p++) {
            final int pageNum = p;
            boolean isActive = (p == currentPage);
            JButton btnPage = createPageButton(String.valueOf(p), true, isActive);
            btnPage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setPage(pageNum);
                }
            });
            pnlButtons.add(btnPage);
        }

        // Botón Siguiente (>)
        JButton btnNext = createPageButton(">", currentPage < totalPages, false);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < totalPages) {
                    setPage(currentPage + 1);
                }
            }
        });
        pnlButtons.add(btnNext);

        pnlButtons.revalidate();
        pnlButtons.repaint();
    }

    private JButton createPageButton(String text, boolean enabled, boolean isActive) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                if (isActive) {
                    g2.setColor(ThemeColor.CIAN_ACCION);
                    g2.fillRoundRect(0, 0, w - 1, h - 1, 6, 6);
                } else if (getModel().isRollover() && isEnabled()) {
                    g2.setColor(ThemeColor.CIAN_LIGHT);
                    g2.fillRoundRect(0, 0, w - 1, h - 1, 6, 6);
                    g2.setColor(ThemeColor.CIAN_ACCION);
                    g2.drawRoundRect(0, 0, w - 1, h - 1, 6, 6);
                } else {
                    g2.setColor(ThemeColor.FONDO_CARD);
                    g2.fillRoundRect(0, 0, w - 1, h - 1, 6, 6);
                    g2.setColor(ThemeColor.BORDE_CARD);
                    g2.drawRoundRect(0, 0, w - 1, h - 1, 6, 6);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setPreferredSize(new Dimension(34, 30));
        btn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn.setFont(new Font("Segoe UI", isActive ? Font.BOLD : Font.PLAIN, 12));
        btn.setForeground(isActive ? Color.WHITE : (enabled ? ThemeColor.TEXTO_TITULO : ThemeColor.TEXTO_MUTED));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setEnabled(enabled);
        btn.setCursor(enabled ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));

        return btn;
    }

    public void setPage(int page) {
        if (page != this.currentPage && page >= 1 && page <= this.totalPages) {
            this.currentPage = page;
            updatePagination(this.currentPage, this.totalRecords, this.pageSize);
            firePageChanged(this.currentPage);
        }
    }

    public void addPageChangeListener(PageChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removePageChangeListener(PageChangeListener listener) {
        listeners.remove(listener);
    }

    private void firePageChanged(int newPage) {
        for (PageChangeListener listener : listeners) {
            listener.onPageChanged(newPage);
        }
    }

    // Getters y Setters para compatibilidad JavaBean en NetBeans
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public int getTotalRecords() { return totalRecords; }
    public void setTotalRecords(int totalRecords) { this.totalRecords = totalRecords; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getTotalPages() { return totalPages; }
}
