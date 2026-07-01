package util.estructuras;

import java.util.ArrayList;
import java.util.List;

public class ArbolCategorias<T extends Comparable<T>> {
    private NodoArbol<T> raiz;

    public static class NodoArbol<T> {
        public T dato;
        public NodoArbol<T> izquierdo;
        public NodoArbol<T> derecho;

        public NodoArbol(T dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    public ArbolCategorias() {
        this.raiz = null;
    }

    // Inserción ordenada (ABB)
    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoArbol<T> insertarRecursivo(NodoArbol<T> actual, T dato) {
        if (actual == null) {
            return new NodoArbol<>(dato);
        }
        if (dato.compareTo(actual.dato) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, dato);
        } else if (dato.compareTo(actual.dato) > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, dato);
        }
        return actual;
    }

    // Búsqueda ordenada (ABB)
    public boolean buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private boolean buscarRecursivo(NodoArbol<T> actual, T dato) {
        if (actual == null) return false;
        if (actual.dato.equals(dato)) return true;
        if (dato.compareTo(actual.dato) < 0) {
            return buscarRecursivo(actual.izquierdo, dato);
        }
        return buscarRecursivo(actual.derecho, dato);
    }

    // Eliminación ordenada (ABB)
    public void eliminar(T dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private NodoArbol<T> eliminarRecursivo(NodoArbol<T> actual, T dato) {
        if (actual == null) return null;

        if (dato.compareTo(actual.dato) < 0) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, dato);
        } else if (dato.compareTo(actual.dato) > 0) {
            actual.derecho = eliminarRecursivo(actual.derecho, dato);
        } else {
            // Nodo encontrado
            // Casos 1 y 2: Nodo sin hijos o con un solo hijo
            if (actual.izquierdo == null) return actual.derecho;
            if (actual.derecho == null) return actual.izquierdo;

            // Caso 3: Dos hijos (sucesor en inorden)
            actual.dato = encontrarMenorValor(actual.derecho);
            actual.derecho = eliminarRecursivo(actual.derecho, actual.dato);
        }
        return actual;
    }

    private T encontrarMenorValor(NodoArbol<T> actual) {
        T min = actual.dato;
        while (actual.izquierdo != null) {
            min = actual.izquierdo.dato;
            actual = actual.izquierdo;
        }
        return min;
    }

    // Recorrido Inorden (LNR)
    public List<T> recorrerInorden() {
        List<T> resultado = new ArrayList<>();
        inordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void inordenRecursivo(NodoArbol<T> actual, List<T> resultado) {
        if (actual != null) {
            inordenRecursivo(actual.izquierdo, resultado);
            resultado.add(actual.dato);
            inordenRecursivo(actual.derecho, resultado);
        }
    }

    // Recorrido Preorden (NLR)
    public List<T> recorrerPreorden() {
        List<T> resultado = new ArrayList<>();
        preordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void preordenRecursivo(NodoArbol<T> actual, List<T> resultado) {
        if (actual != null) {
            resultado.add(actual.dato);
            preordenRecursivo(actual.izquierdo, resultado);
            preordenRecursivo(actual.derecho, resultado);
        }
    }

    // Recorrido Postorden (LRN)
    public List<T> recorrerPostorden() {
        List<T> resultado = new ArrayList<>();
        postordenRecursivo(raiz, resultado);
        return resultado;
    }

    private void postordenRecursivo(NodoArbol<T> actual, List<T> resultado) {
        if (actual != null) {
            postordenRecursivo(actual.izquierdo, resultado);
            postordenRecursivo(actual.derecho, resultado);
            resultado.add(actual.dato);
        }
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }
}
