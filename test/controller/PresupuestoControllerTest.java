package controller;

import java.awt.Color;
import java.util.Collections;
import model.Presupuesto;
import model.Session;
import model.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.IPresupuestoService;
import view.PresupuestoView;

import static org.junit.Assert.*;

public class PresupuestoControllerTest {

    private Usuario usuarioPrueba;

    @Before
    public void setUp() {
        usuarioPrueba = new Usuario();
        usuarioPrueba.setId(2011);
        usuarioPrueba.setNombre("Johann");
        Session.getInstance().setUsuarioActivo(usuarioPrueba);
    }

    @After
    public void tearDown() {
        Session.getInstance().setUsuarioActivo(null);
    }

    @Test
    public void testInicializacionTarjetasPresupuesto() {
        PresupuestoView view = new PresupuestoView();
        PresupuestoController controller = new PresupuestoController(view);

        assertNotNull(view.jLabel21.getText());
        assertNotNull(view.lblIngresoVal.getText());
        assertNotNull(view.jLabel23.getText());
        assertNotNull(view.jLabel34.getText());
        assertNotNull(view.jLabel36.getText());

        assertEquals("MARGEN DISPONIBLE", view.jLabel33.getText());
        assertEquals("S/. 1365.00", view.jLabel21.getText());
        assertEquals("S/. 1200.00", view.lblIngresoVal.getText());
        assertEquals("S/. 1304.90", view.jLabel23.getText());
        assertEquals("-S/. 165.00", view.jLabel34.getText());
        assertEquals("FUERA DEL PRESUPUESTO", view.jLabel36.getText());
        assertEquals(new Color(217, 119, 6), view.jLabel34.getForeground());
        assertEquals(new Color(217, 119, 6), view.jLabel36.getForeground());
    }

    @Test
    public void testAlternanciaBotonAgregarActualizarPresupuesto() {
        PresupuestoView view = new PresupuestoView();
        PresupuestoController controller = new PresupuestoController(view);

        // Sin selección: debe decir "Agregar Presupuesto" y "NUEVO PRESUPUESTO"
        assertEquals("Agregar Presupuesto", view.btnAgregarEditarPresupuesto.getText());
        assertEquals("NUEVO PRESUPUESTO", view.lblRegistro.getText());
        assertEquals(0, view.idPresupuestoSeleccionado);

        // Simular selección de fila 0 si existen filas
        if (view.tblPresupuestos.getRowCount() > 0) {
            view.tblPresupuestos.setRowSelectionInterval(0, 0);
            for (java.awt.event.MouseListener ml : view.tblPresupuestos.getMouseListeners()) {
                ml.mouseClicked(new java.awt.event.MouseEvent(view.tblPresupuestos, 0, 0, 0, 0, 0, 1, false));
            }
            assertEquals("Actualizar Presupuesto", view.btnAgregarEditarPresupuesto.getText());
            assertEquals("EDITAR PRESUPUESTO", view.lblRegistro.getText());
            assertTrue(view.idPresupuestoSeleccionado > 0);
        }

        // Limpiar formulario: debe regresar a "Agregar Presupuesto" y "NUEVO PRESUPUESTO"
        view.limpiarFormularioGasto();
        assertEquals("Agregar Presupuesto", view.btnAgregarEditarPresupuesto.getText());
        assertEquals("NUEVO PRESUPUESTO", view.lblRegistro.getText());
        assertEquals(0, view.idPresupuestoSeleccionado);
    }
}
