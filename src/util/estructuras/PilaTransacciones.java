package util.estructuras;

public class PilaTransacciones<T> {
    private T[] elementos;
    private int tope;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public PilaTransacciones(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = (T[]) new Object[capacidad];
        this.tope = -1;
    }

    public void push(T elemento) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow - Pila Llena");
        }
        elementos[++tope] = elemento;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow - Pila Vac\u00eda");
        }
        T elemento = elementos[tope];
        elementos[tope] = null; // Liberación de referencia
        tope--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos[tope];
    }

    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == capacidad - 1;
    }

    public int getCantidad() {
        return tope + 1;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public T get(int index) {
        if (index < 0 || index > tope) {
            return null;
        }
        return elementos[index];
    }
}
