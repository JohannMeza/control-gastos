package service;

import dao.IDeudaDAO;
import model.AbonoDeuda;
import model.ComboItem;
import model.Deuda;
import model.DeudaResumen;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para DeudaService (TDD).
 * Valida reglas de amortización de pasivos, saldos y programación defensiva en memoria.
 */
public class DeudaServiceTest {

    private DeudaService deudaService;
    private MockDeudaDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockDeudaDAO();
        deudaService = new DeudaService(mockDao);
    }

    @Test
    public void testCalcularSaldoPendiente() {
        double saldo = deudaService.calcularSaldoPendiente(5000.0, 1500.0);
        assertEquals(3500.0, saldo, 0.001);

        double saldoLiquidado = deudaService.calcularSaldoPendiente(2000.0, 2000.0);
        assertEquals(0.0, saldoLiquidado, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularSaldoPendienteMontoInvalido() {
        deudaService.calcularSaldoPendiente(0.0, 50.0);
    }

    @Test
    public void testCalcularPorcentajeAmortizacion() {
        double porcentaje = deudaService.calcularPorcentajeAmortizacion(4000.0, 1000.0);
        assertEquals(25.00, porcentaje, 0.001);

        double totalAmortizado = deudaService.calcularPorcentajeAmortizacion(4000.0, 4000.0);
        assertEquals(100.00, totalAmortizado, 0.001);
    }

    @Test
    public void testValidarAbonoExitoso() {
        AbonoDeuda abono = new AbonoDeuda();
        abono.setMontoAbono(500.0);

        deudaService.validarAbono(abono, 1000.0);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarAbonoNulo() {
        deudaService.validarAbono(null, 1000.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarAbonoExcedeSaldoPendiente() {
        AbonoDeuda abono = new AbonoDeuda();
        abono.setMontoAbono(1500.0);

        deudaService.validarAbono(abono, 1000.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarAbonoMontoCero() {
        AbonoDeuda abono = new AbonoDeuda();
        abono.setMontoAbono(0.0);

        deudaService.validarAbono(abono, 1000.0);
    }

    @Test
    public void testGuardarDeudaExitoso() {
        Deuda d = new Deuda();
        d.setMontoTotal(3000.0);
        String res = deudaService.guardar(d);
        assertEquals("DEUDA_GUARDADA_OK", res);
    }

    @Test
    public void testRegistrarAbonoExitoso() {
        AbonoDeuda abono = new AbonoDeuda();
        abono.setMontoAbono(250.0);
        String res = deudaService.registrarAbono(abono);
        assertEquals("ABONO_REGISTRADO_OK", res);
    }

    private static class MockDeudaDAO implements IDeudaDAO {
        @Override
        public List<ComboItem> listarDeudasCombo(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public List<Deuda> listarDeudas(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public Deuda obtenerPorId(int idDeuda) {
            return new Deuda();
        }

        @Override
        public String guardar(Deuda deuda) {
            return "DEUDA_GUARDADA_OK";
        }

        @Override
        public String actualizar(Deuda deuda) {
            return "DEUDA_ACTUALIZADA_OK";
        }

        @Override
        public String eliminar(int idDeuda) {
            return "DEUDA_ELIMINADA_OK";
        }

        @Override
        public String registrarAbono(AbonoDeuda abono) {
            return "ABONO_REGISTRADO_OK";
        }

        @Override
        public DeudaResumen obtenerResumen(int idUsuario) {
            return new DeudaResumen();
        }

        @Override
        public List<AbonoDeuda> listarHistorialAbonos(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public double obtenerUltimoAbono(int idDeuda) {
            return 250.0;
        }
    }
}
