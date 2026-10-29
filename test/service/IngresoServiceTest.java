package service;

import dao.IIngresoDAO;
import model.ComboItem;
import model.Ingreso;
import model.IngresoResumen;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para IngresoService (TDD).
 * Valida reglas de ingresos, balances netos y programación defensiva en memoria.
 */
public class IngresoServiceTest {

    private IngresoService ingresoService;
    private MockIngresoDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockIngresoDAO();
        ingresoService = new IngresoService(mockDao);
    }

    @Test
    public void testGuardarIngresoNuloRetornaMensajeDefensivo() {
        String res = ingresoService.guardar(null);
        assertEquals("El registro de ingreso no puede ser nulo.", res);
    }

    @Test
    public void testGuardarIngresoMontoInvalido() {
        Ingreso ing = new Ingreso();
        ing.setMonto(0.0);
        ing.setDescripcion("Honorarios");
        ing.setFecha(new Date());

        String res = ingresoService.guardar(ing);
        assertEquals("El monto del ingreso debe ser mayor a 0.", res);
    }

    @Test
    public void testGuardarIngresoDescripcionVacia() {
        Ingreso ing = new Ingreso();
        ing.setMonto(1500.0);
        ing.setDescripcion("   ");
        ing.setFecha(new Date());

        String res = ingresoService.guardar(ing);
        assertEquals("Debe ingresar una descripción o fuente de pago.", res);
    }

    @Test
    public void testGuardarIngresoFechaNula() {
        Ingreso ing = new Ingreso();
        ing.setMonto(1500.0);
        ing.setDescripcion("Sueldo mensual");
        ing.setFecha(null);

        String res = ingresoService.guardar(ing);
        assertEquals("Debe seleccionar una fecha de recepción válida.", res);
    }

    @Test
    public void testGuardarIngresoExitoso() {
        Ingreso ing = new Ingreso();
        ing.setMonto(2500.0);
        ing.setDescripcion("Sueldo mensual");
        ing.setFecha(new Date());

        String res = ingresoService.guardar(ing);
        assertEquals("INGRESO_GUARDADO_OK", res);
    }

    @Test
    public void testCalcularTotalIngresos() {
        List<Ingreso> lista = new ArrayList<>();

        Ingreso i1 = new Ingreso();
        i1.setMonto(1200.50);
        lista.add(i1);

        Ingreso i2 = new Ingreso();
        i2.setMonto(800.50);
        lista.add(i2);

        double total = ingresoService.calcularTotalIngresos(lista);
        assertEquals(2001.00, total, 0.001);

        assertEquals(0.0, ingresoService.calcularTotalIngresos(null), 0.001);
    }

    @Test
    public void testCalcularBalanceNetoSuperavit() {
        double balance = ingresoService.calcularBalanceNeto(5000.0, 3200.0);
        assertEquals(1800.0, balance, 0.001);
    }

    @Test
    public void testCalcularBalanceNetoDeficit() {
        double balance = ingresoService.calcularBalanceNeto(2000.0, 2500.0);
        assertEquals(-500.0, balance, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularBalanceNetoValoresInvalidos() {
        ingresoService.calcularBalanceNeto(Double.NaN, 1000.0);
    }

    @Test
    public void testFiltrarPorEstado() {
        List<Ingreso> lista = new ArrayList<>();

        Ingreso i1 = new Ingreso();
        i1.setEstado("Cobrado");
        lista.add(i1);

        Ingreso i2 = new Ingreso();
        i2.setEstado("Pendiente");
        lista.add(i2);

        Ingreso i3 = new Ingreso();
        i3.setEstado("Cobrado");
        lista.add(i3);

        List<Ingreso> cobrados = ingresoService.filtrarPorEstado(lista, "Cobrado");
        assertEquals(2, cobrados.size());

        List<Ingreso> pendientes = ingresoService.filtrarPorEstado(lista, "Pendiente");
        assertEquals(1, pendientes.size());

        List<Ingreso> anulados = ingresoService.filtrarPorEstado(lista, "Anulado");
        assertTrue(anulados.isEmpty());
    }

    @Test
    public void testListarCategoriasRetornaLista() {
        List<ComboItem> categorias = ingresoService.listarCategorias();
        assertNotNull(categorias);
    }

    private static class MockIngresoDAO implements IIngresoDAO {
        @Override
        public List<Ingreso> listarIngresos(int idUsuario, String tipoFlujo, String estado, String fechaDesde, String fechaHasta, String categoria, String cuenta) {
            return new ArrayList<>();
        }

        @Override
        public String guardar(Ingreso ingreso) {
            return "INGRESO_GUARDADO_OK";
        }

        @Override
        public String actualizar(Ingreso ingreso) {
            return "INGRESO_ACTUALIZADO_OK";
        }

        @Override
        public String eliminar(int idIngreso, int idUsuario) {
            return "INGRESO_ELIMINADO_OK";
        }

        @Override
        public Ingreso obtenerPorId(int idIngreso, int idUsuario) {
            return new Ingreso();
        }

        @Override
        public IngresoResumen obtenerResumenKpi(int idUsuario, String fechaInicio, String fechaFin, String fechaInicioPrev, String fechaFinPrev) {
            return new IngresoResumen();
        }

        @Override
        public List<ComboItem> listarCategorias() {
            return new ArrayList<>();
        }

        @Override
        public List<ComboItem> listarCuentasDestino(int idUsuario) {
            return new ArrayList<>();
        }
    }
}
