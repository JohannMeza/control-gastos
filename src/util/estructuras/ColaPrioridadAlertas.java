package util.estructuras;

public class ColaPrioridadAlertas<T> {
    private NodoPrioridad<T> frente;
    private int tamano;

    public static class NodoPrioridad<T> {
        public T dato;
        public int prioridad; // Mayor valor de prioridad indica mayor urgencia
        public NodoPrioridad<T> siguiente;

        public NodoPrioridad(T dato, int prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
            this.siguiente = null;
        }
    }

    public ColaPrioridadAlertas() {
        this.frente = null;
        this.tamano = 0;
    }

    public void enqueue(T dato, int prioridad) {
        NodoPrioridad<T> nuevo = new NodoPrioridad<>(dato, prioridad);
        if (isEmpty() || prioridad > frente.prioridad) {
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
            NodoPrioridad<T> actual = frente;
            while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow - Cola Vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        tamano--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) return null;
        return frente.dato;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public int getTamano() {
        return tamano;
    }

    public NodoPrioridad<T> getFrente() {
        return frente;
    }
}
