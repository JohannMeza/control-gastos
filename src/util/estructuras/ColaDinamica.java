package util.estructuras;

public class ColaDinamica<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public static class Nodo<T> {
        public T dato;
        public Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ColaDinamica() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow - Cola Vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
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
}
