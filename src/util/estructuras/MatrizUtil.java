package util.estructuras;

public class MatrizUtil {

    // Registro de datos en matriz
    public static void registrarDatos(double[][] matriz, int fila, int col, double valor) {
        if (fila >= 0 && fila < matriz.length && col >= 0 && col < matriz[0].length) {
            matriz[fila][col] = valor;
        } else {
            throw new IndexOutOfBoundsException("Indices fuera de limites de la matriz");
        }
    }

    // Transposición de matriz (intercambia filas por columnas)
    public static double[][] transponer(double[][] matriz) {
        int filas = matriz.length;
        int cols = matriz[0].length;
        double[][] transpuesta = new double[cols][filas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }
        return transpuesta;
    }

    // Determinante de matriz (recursivo por cofactores)
    public static double calcularDeterminante(double[][] matriz) {
        if (matriz.length != matriz[0].length) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada");
        }
        int n = matriz.length;
        if (n == 1) {
            return matriz[0][0];
        }
        if (n == 2) {
            return (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
        }
        double det = 0;
        for (int j = 0; j < n; j++) {
            det += Math.pow(-1, j) * matriz[0][j] * calcularDeterminante(obtenerSubmatriz(matriz, 0, j));
        }
        return det;
    }

    // Inversa de matriz
    public static double[][] obtenerInversa(double[][] matriz) {
        double det = calcularDeterminante(matriz);
        if (Math.abs(det) < 0.000001) {
            throw new ArithmeticException("La matriz es singular (Determinante es 0), no posee inversa.");
        }
        int n = matriz.length;
        double[][] adjunta = obtenerAdjunta(matriz);
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = adjunta[i][j] / det;
            }
        }
        return inversa;
    }

    // Matriz simétrica: verifica si es idéntica a su transpuesta (a_ij == a_ji)
    public static boolean esSimetrica(double[][] matriz) {
        if (matriz.length != matriz[0].length) return false;
        int n = matriz.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(matriz[i][j] - matriz[j][i]) > 0.0001) {
                    return false;
                }
            }
        }
        return true;
    }

    // Matriz asimétrica: determina si no existe simetría
    public static boolean esAsimetrica(double[][] matriz) {
        return !esSimetrica(matriz);
    }

    // Auxiliar: Genera la submatriz omitiendo fila y columna indicadas
    private static double[][] obtenerSubmatriz(double[][] matriz, int filaAEliminar, int colAEliminar) {
        int n = matriz.length;
        double[][] submatriz = new double[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == filaAEliminar) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == colAEliminar) continue;
                submatriz[r][c] = matriz[i][j];
                c++;
            }
            r++;
        }
        return submatriz;
    }

    // Auxiliar: Genera la matriz adjunta
    private static double[][] obtenerAdjunta(double[][] matriz) {
        int n = matriz.length;
        double[][] adjunta = new double[n][n];
        if (n == 1) {
            adjunta[0][0] = 1;
            return adjunta;
        }
        double[][] cofactores = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] sub = obtenerSubmatriz(matriz, i, j);
                cofactores[i][j] = Math.pow(-1, i + j) * calcularDeterminante(sub);
            }
        }
        return transponer(cofactores);
    }
}
