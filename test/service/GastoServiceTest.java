package service;

import dao.IGastoDAO;
import model.ComboItem;
import model.Gasto;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas Unitarias automatizadas (TDD) para GastoService.
 * Valida la lógica de negocio contable y defensiva en memoria sin requerir SQL Server.
 */
public class GastoServiceTest {

    private GastoService gastoService;
    private MockGastoDAO mockDao;

    @Before
    public void setUp() {
        mockDao = new MockGastoDAO();
        gastoService = new GastoService(mockDao);
    }

    @Test
    public void testValidarGastoExitoso() {
        Gasto gasto = new Gasto();
        gasto.setMonto(150.00);
        gasto.setDescripcion("Alimentación semanal");
        gasto.setIdUsuario(1);
        gasto.setIdCategoria(2);

        gastoService.validarGasto(gasto);
        assertTrue("El gasto válido no debe arrojar excepciones", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarGastoNuloArrojaExcepcion() {
        gastoService.validarGasto(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarGastoMontoCeroArrojaExcepcion() {
        Gasto gasto = new Gasto();
        gasto.setMonto(0.0);
        gasto.setDescripcion("Transporte");
        gasto.setIdUsuario(1);

        gastoService.validarGasto(gasto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarGastoMontoNegativoArrojaExcepcion() {
        Gasto gasto = new Gasto();
        gasto.setMonto(-50.0);
        gasto.setDescripcion("Transporte");
        gasto.setIdUsuario(1);

        gastoService.validarGasto(gasto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarGastoDescripcionVaciaArrojaExcepcion() {
        Gasto gasto = new Gasto();
        gasto.setMonto(80.0);
        gasto.setDescripcion("   ");
        gasto.setIdUsuario(1);

        gastoService.validarGasto(gasto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidarGastoUsuarioInvalidoArrojaExcepcion() {
        Gasto gasto = new Gasto();
        gasto.setMonto(80.0);
        gasto.setDescripcion("Servicio de luz");
        gasto.setIdUsuario(0);

        gastoService.validarGasto(gasto);
    }

    @Test
    public void testCalcularTotalGastosListaVacia() {
        double total = gastoService.calcularTotalGastos(new ArrayList<>());
        assertEquals(0.0, total, 0.001);

        total = gastoService.calcularTotalGastos(null);
        assertEquals(0.0, total, 0.001);
    }

    @Test
    public void testCalcularTotalGastosSumaCorrecta() {
        List<Gasto> lista = new ArrayList<>();

        Gasto g1 = new Gasto();
        g1.setMonto(120.50);
        lista.add(g1);

        Gasto g2 = new Gasto();
        g2.setMonto(79.50);
        lista.add(g2);

        Gasto g3 = new Gasto();
        g3.setMonto(50.00);
        lista.add(g3);

        double total = gastoService.calcularTotalGastos(lista);
        assertEquals(250.00, total, 0.001);
    }

    @Test
    public void testFiltrarPorCategoria() {
        List<Gasto> lista = new ArrayList<>();

        Gasto g1 = new Gasto();
        g1.setIdCategoria(1);
        g1.setMonto(100.0);
        lista.add(g1);

        Gasto g2 = new Gasto();
        g2.setIdCategoria(2);
        g2.setMonto(50.0);
        lista.add(g2);

        Gasto g3 = new Gasto();
        g3.setIdCategoria(1);
        g3.setMonto(30.0);
        lista.add(g3);

        List<Gasto> filtradosCat1 = gastoService.filtrarPorCategoria(lista, 1);
        assertEquals(2, filtradosCat1.size());

        List<Gasto> filtradosCat2 = gastoService.filtrarPorCategoria(lista, 2);
        assertEquals(1, filtradosCat2.size());

        List<Gasto> filtradosInexistente = gastoService.filtrarPorCategoria(lista, 99);
        assertTrue(filtradosInexistente.isEmpty());
    }

    @Test
    public void testDelegacionDaoGuardarYActualizar() {
        Gasto g = new Gasto();
        g.setIdGasto(10);
        g.setMonto(200.0);
        g.setDescripcion("Compra supermercado");
        g.setIdUsuario(1);

        gastoService.guardar(g);
        assertEquals("GUARDADO_OK", mockDao.ultimoMensaje);

        String msjActualizar = gastoService.actualizar(g);
        assertEquals("ACTUALIZADO_OK", msjActualizar);

        String msjEliminar = gastoService.eliminar(10);
        assertEquals("ELIMINADO_OK", msjEliminar);
    }

    // Mock / Test Double en memoria de IGastoDAO para pruebas desacopladas (DIP)
    private static class MockGastoDAO implements IGastoDAO {
        String ultimoMensaje = "";

        @Override
        public String guardar(Gasto gasto) {
            ultimoMensaje = "GUARDADO_OK";
            return ultimoMensaje;
        }

        @Override
        public String actualizar(Gasto gasto) {
            ultimoMensaje = "ACTUALIZADO_OK";
            return ultimoMensaje;
        }

        @Override
        public String eliminar(int idGasto) {
            ultimoMensaje = "ELIMINADO_OK";
            return "ELIMINADO_OK";
        }

        @Override
        public List<Gasto> listar(Date fechaDesde, Date fechaHasta, int idUsuario) {
            return new ArrayList<>();
        }

        @Override
        public List<ComboItem> listarCategorias() {
            return new ArrayList<>();
        }

        @Override
        public List<ComboItem> listarTipoGastos() {
            return new ArrayList<>();
        }

        @Override
        public List<ComboItem> listarUsuarios() {
            return new ArrayList<>();
        }

        @Override
        public Gasto obtenerPorId(int idGasto) {
            Gasto g = new Gasto();
            g.setIdGasto(idGasto);
            g.setMonto(100.0);
            return g;
        }
    }
}
