package util.estructuras;

public class ListaSimple<T extends Comparable<T>> {
    private Nodo<T> cabeza;
    private int tamano;

    public static class Nodo<T> {
        public T dato;
        public Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ListaSimple() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamano++;
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
        tamano++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamano--;
            return true;
        }
        Nodo<T> anterior = cabeza;
        Nodo<T> actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                anterior.siguiente = actual.siguiente;
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public Nodo<T> buscar(T dato) {
        Nodo<T> temp = cabeza;
        while (temp != null) {
            if (temp.dato.equals(dato)) {
                return temp;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    public void ordenarBubble() {
        if (tamano <= 1) return;
        boolean huboIntercambio;
        do {
            huboIntercambio = false;
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.dato.compareTo(actual.siguiente.dato) > 0) {
                    // Intercambio de datos en el nodo
                    T temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    huboIntercambio = true;
                }
                actual = actual.siguiente;
            }
        } while (huboIntercambio);
    }

    public int getTamano() {
        return tamano;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }
}
