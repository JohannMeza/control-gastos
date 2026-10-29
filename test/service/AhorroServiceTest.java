package service;

import dao.IAhorroDAO;
import model.Ahorro;
import model.ComboItem;
import model.MetaAhorro;
import model.MetasResumen;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para AhorroService (TDD).
 * Valida reglas de progreso de metas de ahorro y programación defensiva en memoria.
 */
public class AhorroServiceTest {

    private AhorroService ahorroService;
    private MockAhorroDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockAhorroDAO();
        ahorroService = new AhorroService(mockDao);
    }

    @Test
    public void testCalcularPorcentajeProgreso() {
        double progreso = ahorroService.calcularPorcentajeProgreso(750.0, 1500.0);
        assertEquals(50.00, progreso, 0.001);

        double metaCumplida = ahorroService.calcularPorcentajeProgreso(1600.0, 1500.0);
        assertEquals(100.00, metaCumplida, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPorcentajeProgresoMetaCero() {
        ahorroService.calcularPorcentajeProgreso(100.0, 0.0);
    }

    @Test
    public void testCalcularMontoFaltante() {
        double faltante = ahorroService.calcularMontoFaltante(600.0, 1000.0);
        assertEquals(400.00, faltante, 0.001);

        double yaCompletado = ahorroService.calcularMontoFaltante(1200.0, 1000.0);
        assertEquals(0.00, yaCompletado, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcularMontoFaltanteMontoActualNegativo() {
        ahorroService.calcularMontoFaltante(-100.0, 1000.0);
    }

    @Test
    public void testGuardarAhorroExitoso() {
        Ahorro a = new Ahorro();
        a.setMonto(500.0);
        String res = ahorroService.guardar(a);
        assertEquals("AHORRO_GUARDADO_OK", res);
    }

    @Test
    public void testGuardarMetaExitoso() {
        MetaAhorro meta = new MetaAhorro();
        meta.setDescripcion("Fondo de Emergencia");
        String res = ahorroService.guardarMeta(meta, 1);
        assertEquals("META_GUARDADA_OK", res);
    }

    private static class MockAhorroDAO implements IAhorroDAO {
        @Override
        public List<ComboItem> listarMetas(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public List<Ahorro> listarAhorros(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public Ahorro obtenerPorId(int idAhorro) {
            return new Ahorro();
        }

        @Override
        public String guardar(Ahorro ahorro) {
            return "AHORRO_GUARDADO_OK";
        }

        @Override
        public String actualizar(Ahorro ahorro) {
            return "AHORRO_ACTUALIZADO_OK";
        }

        @Override
        public String eliminar(int idAhorro) {
            return "AHORRO_ELIMINADO_OK";
        }

        @Override
        public MetasResumen obtenerResumen(int idUsuario) {
            return new MetasResumen();
        }

        @Override
        public double obtenerAhorradoMesPasado(int idUsuario) {
            return 300.0;
        }

        @Override
        public List<MetaAhorro> obtenerMetasDetalle(int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public String guardarMeta(MetaAhorro meta, int idUsuario) {
            return "META_GUARDADA_OK";
        }
    }
}
