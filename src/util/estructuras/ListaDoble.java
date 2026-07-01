package util.estructuras;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private NodoDoble<T> cursor;
    private int tamano;

    public static class NodoDoble<T> {
        public T dato;
        public NodoDoble<T> siguiente;
        public NodoDoble<T> anterior;

        public NodoDoble(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.cursor = null;
        this.tamano = 0;
    }

    public void insertarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cursor = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamano++;
    }

    public void insertarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cursor = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamano++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        NodoDoble<T> temp = cabeza;
        while (temp != null) {
            if (temp.dato.equals(dato)) {
                if (temp == cursor) {
                    cursor = (temp.siguiente != null) ? temp.siguiente : temp.anterior;
                }
                if (temp.anterior != null) {
                    temp.anterior.siguiente = temp.siguiente;
                } else {
                    cabeza = temp.siguiente;
                }
                if (temp.siguiente != null) {
                    temp.siguiente.anterior = temp.anterior;
                } else {
                    cola = temp.anterior;
                }
                tamano--;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    // Métodos de navegación
    public T irSiguiente() {
        if (cursor != null && cursor.siguiente != null) {
            cursor = cursor.siguiente;
            return cursor.dato;
        }
        return null;
    }

    public T irAnterior() {
        if (cursor != null && cursor.anterior != null) {
            cursor = cursor.anterior;
            return cursor.dato;
        }
        return null;
    }

    public T getActual() {
        return (cursor != null) ? cursor.dato : null;
    }

    public boolean tieneSiguiente() {
        return cursor != null && cursor.siguiente != null;
    }

    public boolean tieneAnterior() {
        return cursor != null && cursor.anterior != null;
    }

    public void reiniciarCursor() {
        cursor = cabeza;
    }

    public void irAlFinal() {
        cursor = cola;
    }

    public int getTamano() {
        return tamano;
    }

    public NodoDoble<T> getCabeza() {
        return cabeza;
    }
}
