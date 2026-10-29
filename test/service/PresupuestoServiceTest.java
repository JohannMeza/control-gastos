package service;

import dao.IPresupuestoDAO;
import model.ComboItem;
import model.Presupuesto;
import model.PresupuestoActividad;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas Unitarias automatizadas (TDD) para PresupuestoService.
 * Valida reglas de asignación 50/30/20, cálculo de ejecución y alertas contables en memoria.
 */
public class PresupuestoServiceTest {

    private PresupuestoService presupuestoService;
    private MockPresupuestoDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockPresupuestoDAO();
        presupuestoService = new PresupuestoService(mockDao);
    }

    @Test
    public void testCalcularDistribucion503020Exitoso() {
        double ingreso = 3000.00;
        double[] distribucion = presupuestoService.calcularDistribucion503020(ingreso);

        assertNotNull("La distribución no debe ser nula", distribucion);
        assertEquals(3, distribucion.length);

        double necesidades = distribucion[0]; // 50%
        double deseos = distribucion[1];       // 30%
        double ahorro = distribucion[2];       // 20%

        assertEquals(1500.00, necesidades, 0.001);
        assertEquals(900.00, deseos, 0.001);
        assertEquals(600.00, ahorro, 0.001);
        assertEquals(ingreso, necesidades + deseos + ahorro, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularDistribucion503020IngresoCeroArrojaExcepcion() {
        presupuestoService.calcularDistribucion503020(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularDistribucion503020IngresoNegativoArrojaExcepcion() {
        presupuestoService.calcularDistribucion503020(-500.0);
    }

    @Test
    public void testCalcularPorcentajeEjecucionNormal() {
        double porcentaje = presupuestoService.calcularPorcentajeEjecucion(1000.0, 500.0);
        assertEquals(50.00, porcentaje, 0.001);
    }

    @Test
    public void testCalcularPorcentajeEjecucionLimiteAlerta() {
        double porcentaje = presupuestoService.calcularPorcentajeEjecucion(2000.0, 1600.0);
        assertEquals(80.00, porcentaje, 0.001);
    }

    @Test
    public void testCalcularPorcentajeEjecucionSobregiro() {
        double porcentaje = presupuestoService.calcularPorcentajeEjecucion(1000.0, 1250.0);
        assertEquals(125.00, porcentaje, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPorcentajeEjecucionMontoAsignadoCeroArrojaExcepcion() {
        presupuestoService.calcularPorcentajeEjecucion(0.0, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPorcentajeEjecucionGastoNegativoArrojaExcepcion() {
        presupuestoService.calcularPorcentajeEjecucion(1000.0, -50.0);
    }

    @Test
    public void testCalcularSaldoDisponible() {
        double saldoDisponible = presupuestoService.calcularSaldoDisponible(1000.0, 650.0);
        assertEquals(350.00, saldoDisponible, 0.001);

        double saldoDeficitario = presupuestoService.calcularSaldoDisponible(1000.0, 1200.0);
        assertEquals(-200.00, saldoDeficitario, 0.001);
    }

    @Test
    public void testEvaluarAlertaPresupuestoNormal() {
        String estado = presupuestoService.evaluarAlertaPresupuesto(50.0);
        assertEquals("NORMAL", estado);

        estado = presupuestoService.evaluarAlertaPresupuesto(79.9);
        assertEquals("NORMAL", estado);
    }

    @Test
    public void testEvaluarAlertaPresupuestoAdvertencia() {
        String estado = presupuestoService.evaluarAlertaPresupuesto(80.0);
        assertEquals("ADVERTENCIA", estado);

        estado = presupuestoService.evaluarAlertaPresupuesto(99.9);
        assertEquals("ADVERTENCIA", estado);
    }

    @Test
    public void testEvaluarAlertaPresupuestoSobregiro() {
        String estado = presupuestoService.evaluarAlertaPresupuesto(100.0);
        assertEquals("SOBREGIRO", estado);

        estado = presupuestoService.evaluarAlertaPresupuesto(150.0);
        assertEquals("SOBREGIRO", estado);
    }

    @Test
    public void testDelegacionDaoPresupuesto() {
        Presupuesto p = new Presupuesto();
        p.setIdPresupuesto(5);
        p.setMontoAsignado(1500.0);

        String resGuardar = presupuestoService.guardarPresupuesto(p);
        assertEquals("PRESUPUESTO_GUARDADO_OK", resGuardar);

        String resEliminar = presupuestoService.eliminarPresupuesto(5);
        assertEquals("PRESUPUESTO_ELIMINADO_OK", resEliminar);
    }

    // Mock / Test Double en memoria de IPresupuestoDAO para desacoplamiento DIP
    private static class MockPresupuestoDAO implements IPresupuestoDAO {
        @Override
        public List<ComboItem> listarCategorias() {
            return new ArrayList<>();
        }

        @Override
        public List<ComboItem> listarPeriodo() {
            return new ArrayList<>();
        }

        @Override
        public List<Presupuesto> listarPresupuesto(int idUsuario, int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin, int idCategoria, int idEstado) {
            return new ArrayList<>();
        }

        @Override
        public List<PresupuestoActividad> listarPresupuestoActividades(int idUsuarioOwner, int idFiltradoUsuario, String fechaInicio, String fechaFin) {
            return new ArrayList<>();
        }

        @Override
        public String guardarPresupuesto(Presupuesto presupuesto) {
            return "PRESUPUESTO_GUARDADO_OK";
        }

        @Override
        public String eliminarPresupuesto(int idPresupuesto) {
            return "PRESUPUESTO_ELIMINADO_OK";
        }

        @Override
        public List<Integer> obtenerAniosConDatos(int idUsuario) {
            return new ArrayList<>();
        }
    }
}
