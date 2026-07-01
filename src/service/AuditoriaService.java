package service;

import dao.AuditoriaDAO;
import java.util.List;
import model.ComboItem;
import model.Gasto;
import model.GastoAuditoria;
import model.RegistroAuditoria;
import util.estructuras.*;

public class AuditoriaService {
    private final AuditoriaDAO dao;

    // Estructuras en memoria
    private final ListaSimple<GastoAuditoria> listaGastos;
    private final PilaTransacciones<GastoAuditoria> pilaDeshacer;
    private final PilaTransacciones<GastoAuditoria> pilaRehacer;
    private final ListaDoble<RegistroAuditoria> listaLogs;
    private final ColaPrioridadAlertas<String> colaAlertas;
    private final ColaDinamica<String> colaNotificaciones;
    private final ArbolCategorias<String> arbolCategorias;

    // Vectores y Matrices
    private double[] vectorSaldos;
    private double[][] matrizGastos;
    private int logCounter = 1;

    public AuditoriaService() {
        this.dao = new AuditoriaDAO();
        this.listaGastos = new ListaSimple<>();
        this.pilaDeshacer = new PilaTransacciones<>(20);
        this.pilaRehacer = new PilaTransacciones<>(20);
        this.listaLogs = new ListaDoble<>();
        this.colaAlertas = new ColaPrioridadAlertas<>();
        this.colaNotificaciones = new ColaDinamica<>();
        this.arbolCategorias = new ArbolCategorias<>();

        // Inicializar Arreglo 1D (5 elementos de saldos simulados)
        this.vectorSaldos = new double[]{1500.0, 2300.0, 1850.0, 3100.0, 2750.0};

        // Inicializar Matriz 2D (3x3 invertible)
        this.matrizGastos = new double[][]{
            {120.0, 150.0, 110.0},
            {80.0, 95.0, 85.0},
            {50.0, 60.0, 45.0}
        };

        // Cargar datos iniciales
        inicializarDatos();
    }

    private void inicializarDatos() {
        registrarLog("SISTEMA", "Servicio de auditoria contable inicializado.");

        // 1. Cargar categorias de la Base de Datos al Arbol (ABB)
        try {
            List<ComboItem> cats = dao.obtenerCategoriasBase();
            if (cats.isEmpty()) {
                // Fallback académico
                arbolCategorias.insertar("Alimentos");
                arbolCategorias.insertar("Servicios");
                arbolCategorias.insertar("Transporte");
                arbolCategorias.insertar("Entretenimiento");
            } else {
                for (ComboItem item : cats) {
                    if (item.getValue() > 0) {
                        arbolCategorias.insertar(item.getLabel());
                    }
                }
            }
            registrarLog("ABB", "Categorías iniciales cargadas en el árbol.");
        } catch (Exception e) {
            arbolCategorias.insertar("Alimentos");
            arbolCategorias.insertar("Servicios");
            arbolCategorias.insertar("Otros");
        }

        // 2. Cargar gastos reales a la Lista Simple
        try {
            List<Gasto> reales = dao.obtenerGastosBase(1); // Carga gastos de usuario administrador
            int count = 1;
            for (Gasto g : reales) {
                GastoAuditoria ga = new GastoAuditoria(count++, g.getDescripcion(), g.getMonto(), g.getCategoria());
                listaGastos.insertarFinal(ga);
            }
            registrarLog("LISTA_SIMPLE", "Gastos reales cargados en lista simple.");
        } catch (Exception e) {
            // Fallback datos demo
            listaGastos.insertarFinal(new GastoAuditoria(1, "Compra de víveres", 150.0, "Alimentos"));
            listaGastos.insertarFinal(new GastoAuditoria(2, "Pago de luz", 85.0, "Servicios"));
        }

        // 3. Poblar algunas alertas iniciales en la Cola de Prioridad
        colaAlertas.enqueue("Presupuesto de Entretenimiento al 85% de limite", 2); // prioridad media
        colaAlertas.enqueue("CRITICO: Presupuesto de Alimentos excedido en 110%", 5); // prioridad maxima
        colaAlertas.enqueue("Cuenta de ahorros saldo inferior al minimo", 1); // prioridad baja
        registrarLog("COLA_PRIORIDAD", "Alertas financieras encoladas por gravedad.");
    }

    public void registrarLog(String accion, String detalle) {
        RegistroAuditoria log = new RegistroAuditoria(logCounter++, accion, detalle);
        listaLogs.insertarFinal(log);
        listaLogs.irAlFinal();
    }

    // === METODOS PARA SUB-PESTAÑA 1: VECTORES Y MATRICES ===

    public double[] getVectorSaldos() {
        return vectorSaldos;
    }

    public void insertarSaldo(double valor, int posicion) {
        vectorSaldos = ArregloUtil.insertar(vectorSaldos, valor, posicion);
        registrarLog("VECTOR_1D", "Saldo S/ " + valor + " insertado en posición " + posicion);
    }

    public void actualizarSaldo(int index, double valor) {
        ArregloUtil.actualizar(vectorSaldos, index, valor);
        registrarLog("VECTOR_1D", "Saldo en posición " + index + " actualizado a S/ " + valor);
    }

    public void eliminarSaldo(int posicion) {
        double valorEliminado = vectorSaldos[posicion];
        vectorSaldos = ArregloUtil.eliminar(vectorSaldos, posicion);
        registrarLog("VECTOR_1D", "Saldo S/ " + valorEliminado + " eliminado en posición " + posicion);
    }

    public double[] copiarSaldos() {
        double[] copia = ArregloUtil.copiar(vectorSaldos);
        registrarLog("VECTOR_1D", "Arreglo de saldos duplicado en memoria.");
        return copia;
    }

    public boolean compararSaldos(double[] otro) {
        boolean identico = ArregloUtil.comparar(vectorSaldos, otro);
        registrarLog("VECTOR_1D", "Comparación de arreglos realizada. ¿Idénticos?: " + identico);
        return identico;
    }

    public double[][] getMatrizGastos() {
        return matrizGastos;
    }

    public void registrarDatoMatriz(int fila, int col, double valor) {
        MatrizUtil.registrarDatos(matrizGastos, fila, col, valor);
        registrarLog("MATRIZ_2D", "Dato registrado en [" + fila + "][" + col + "] = " + valor);
    }

    public void transponerMatriz() {
        matrizGastos = MatrizUtil.transponer(matrizGastos);
        registrarLog("MATRIZ_2D", "Matriz transpuesta correctamente.");
    }

    public double calcularDeterminanteMatriz() {
        double det = MatrizUtil.calcularDeterminante(matrizGastos);
        registrarLog("MATRIZ_2D", "Determinante calculado: " + det);
        return det;
    }

    public void invertirMatriz() {
        matrizGastos = MatrizUtil.obtenerInversa(matrizGastos);
        registrarLog("MATRIZ_2D", "Matriz invertida correctamente.");
    }

    public boolean esMatrizSimetrica() {
        boolean flag = MatrizUtil.esSimetrica(matrizGastos);
        registrarLog("MATRIZ_2D", "Chequeo de simetría: " + flag);
        return flag;
    }

    public boolean esMatrizAsimetrica() {
        boolean flag = MatrizUtil.esAsimetrica(matrizGastos);
        registrarLog("MATRIZ_2D", "Chequeo de asimetría: " + flag);
        return flag;
    }

    // === METODOS PARA SUB-PESTAÑA 2: LISTAS ENLAZADAS ===

    public ListaSimple<GastoAuditoria> getListaGastos() {
        return listaGastos;
    }

    public void insertarGastoInicio(String desc, double monto, String cat) {
        int id = listaGastos.getTamano() + 1;
        GastoAuditoria ga = new GastoAuditoria(id, desc, monto, cat);
        listaGastos.insertarInicio(ga);
        registrarLog("LISTA_SIMPLE", "Gasto insertado al inicio: " + desc);
    }

    public void insertarGastoFinal(String desc, double monto, String cat) {
        int id = listaGastos.getTamano() + 1;
        GastoAuditoria ga = new GastoAuditoria(id, desc, monto, cat);
        listaGastos.insertarFinal(ga);
        registrarLog("LISTA_SIMPLE", "Gasto insertado al final: " + desc);
    }

    public boolean eliminarGasto(String desc) {
        // Busca y elimina por descripción
        ListaSimple.Nodo<GastoAuditoria> temp = listaGastos.getCabeza();
        GastoAuditoria target = null;
        while (temp != null) {
            if (temp.dato.getDescripcion().equalsIgnoreCase(desc)) {
                target = temp.dato;
                break;
            }
            temp = temp.siguiente;
        }
        if (target != null) {
            listaGastos.eliminar(target);
            registrarLog("LISTA_SIMPLE", "Gasto eliminado: " + desc);
            return true;
        }
        return false;
    }

    public GastoAuditoria buscarGasto(String desc) {
        ListaSimple.Nodo<GastoAuditoria> temp = listaGastos.getCabeza();
        while (temp != null) {
            if (temp.dato.getDescripcion().toLowerCase().contains(desc.toLowerCase())) {
                registrarLog("LISTA_SIMPLE", "Gasto encontrado: " + temp.dato.getDescripcion());
                return temp.dato;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    public void ordenarGastosBubble() {
        listaGastos.ordenarBubble();
        registrarLog("LISTA_SIMPLE", "Ordenamiento Bubble Sort ejecutado sobre montos.");
    }

    public ListaDoble<RegistroAuditoria> getListaLogs() {
        return listaLogs;
    }

    // === METODOS PARA SUB-PESTAÑA 3: PILAS Y COLAS ===

    public PilaTransacciones<GastoAuditoria> getPilaDeshacer() {
        return pilaDeshacer;
    }

    public PilaTransacciones<GastoAuditoria> getPilaRehacer() {
        return pilaRehacer;
    }

    public void registrarAccionSimulada(String desc, double monto, String cat) {
        int id = listaGastos.getTamano() + 1;
        GastoAuditoria ga = new GastoAuditoria(id, desc, monto, cat);
        
        // Agregar a la pila de deshacer y vaciar la de rehacer (nueva rama)
        pilaDeshacer.push(ga);
        while (!pilaRehacer.isEmpty()) {
            pilaRehacer.pop();
        }
        
        listaGastos.insertarFinal(ga);
        registrarLog("PILA_ACCIONES", "Asiento registrado: " + desc + " (Push)");
    }

    public boolean deshacer() {
        if (pilaDeshacer.isEmpty()) return false;
        GastoAuditoria ga = pilaDeshacer.pop();
        pilaRehacer.push(ga);
        listaGastos.eliminar(ga);
        registrarLog("PILA_ACCIONES", "Deshacer acción: " + ga.getDescripcion() + " (Pop & Push Rehacer)");
        return true;
    }

    public boolean rehacer() {
        if (pilaRehacer.isEmpty()) return false;
        GastoAuditoria ga = pilaRehacer.pop();
        pilaDeshacer.push(ga);
        listaGastos.insertarFinal(ga);
        registrarLog("PILA_ACCIONES", "Rehacer acción: " + ga.getDescripcion() + " (Pop Rehacer & Push)");
        return true;
    }

    public ColaPrioridadAlertas<String> getColaAlertas() {
        return colaAlertas;
    }

    public void encolarAlerta(String mensaje, int prioridad) {
        colaAlertas.enqueue(mensaje, prioridad);
        registrarLog("COLA_PRIORIDAD", "Alerta encolada con prioridad " + prioridad + ": " + mensaje);
    }

    public String desencolarAlerta() {
        if (colaAlertas.isEmpty()) return null;
        String alerta = colaAlertas.dequeue();
        registrarLog("COLA_PRIORIDAD", "Alerta procesada (Dequeue): " + alerta);
        return alerta;
    }

    // === METODOS PARA SUB-PESTAÑA 4: ARBOLES ===

    public ArbolCategorias<String> getArbolCategorias() {
        return arbolCategorias;
    }

    public void insertarCategoria(String nombre) {
        arbolCategorias.insertar(nombre);
        registrarLog("ABB", "Categoría insertada en ABB: " + nombre);
    }

    public void eliminarCategoria(String nombre) {
        arbolCategorias.eliminar(nombre);
        registrarLog("ABB", "Categoría eliminada en ABB: " + nombre);
    }

    public boolean buscarCategoria(String nombre) {
        boolean encontrada = arbolCategorias.buscar(nombre);
        registrarLog("ABB", "Búsqueda en ABB de '" + nombre + "': " + (encontrada ? "Encontrada" : "No encontrada"));
        return encontrada;
    }
}
