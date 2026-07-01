package util.estructuras;

public class ArregloUtil {

    // Recorrido de elementos
    public static String recorrer(double[] arr, int size) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%.2f", arr[i]));
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Inserción de datos en arreglos unidimensionales (retorna un nuevo arreglo redimensionado)
    public static double[] insertar(double[] original, double elemento, int posicion) {
        if (posicion < 0 || posicion > original.length) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        double[] nuevo = new double[original.length + 1];
        for (int i = 0; i < posicion; i++) {
            nuevo[i] = original[i];
        }
        nuevo[posicion] = elemento;
        for (int i = posicion; i < original.length; i++) {
            nuevo[i + 1] = original[i];
        }
        return nuevo;
    }

    // Actualización de elementos
    public static void actualizar(double[] arr, int index, double nuevoValor) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        arr[index] = nuevoValor;
    }

    // Eliminación de elementos (retorna un nuevo arreglo con la celda removida y corrimiento)
    public static double[] eliminar(double[] original, int posicion) {
        if (posicion < 0 || posicion >= original.length) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        double[] nuevo = new double[original.length - 1];
        for (int i = 0, j = 0; i < original.length; i++) {
            if (i == posicion) continue;
            nuevo[j++] = original[i];
        }
        return nuevo;
    }

    // Copia de arreglos (duplicado en nueva dirección de memoria)
    public static double[] copiar(double[] arr) {
        double[] copia = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copia[i] = arr[i];
        }
        return copia;
    }

    // Comparación de arreglos (elemento por elemento)
    public static boolean comparar(double[] arr1, double[] arr2) {
        if (arr1 == null || arr2 == null) return arr1 == arr2;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (Math.abs(arr1[i] - arr2[i]) > 0.0001) return false;
        }
        return true;
    }
}
