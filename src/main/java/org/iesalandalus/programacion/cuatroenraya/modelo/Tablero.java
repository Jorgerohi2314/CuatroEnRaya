package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private boolean columnaVacia (int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (casillas[i][columna].estaOcupada()) {
                return false;
            }
        }
        return true;
    }

    public boolean estaVacio() {
        for (int j = 0; j < COLUMNAS; j++){
            if (!columnaVacia(j)) {
                return false;
            }
        }
        return true;
    }

    private boolean columnaLlena(int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (!casillas[i][columna]. estaOcupada()) {
                return false;
            }
        }
        return true;
    }

    public boolean estaLleno() {
        for (int j = 0; j < COLUMNAS; j++) {
            if (!columnaLlena(j)) {
                return false;
            }
        }
        return true;
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha== null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
    }

    private void comprobarColumna(int columna) throws CuatroEnRayaExcepcion {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (!this.casillas[i][columna].estaOcupada()) {
                return i;
            }
        }
        return -1;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int n = 0;
        for (int j = 0; j < COLUMNAS; j++) {
            if (casillas[fila][j].toString().equals(ficha.toString())) {
                n++;
            } else if (n < FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                n = 0;
            }
        }
        return objetivoAlcanzado(n);
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int n = 0;
        for (int i = 0; i < FILAS; i++) {
            if (casillas[i][columna].toString().equals(ficha.toString())) {
                n++;
            } else if(n < FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                n = 0;
            }
        }
        return objetivoAlcanzado(n);
    }

    private int menor (int fila, int columna) {
        int menor = 0;
        if (fila < columna) {
           menor = fila;
        }
        else if (columna < fila) {
            menor = columna;
        }
        return menor;
    }

    private boolean comprobarDianogalNE(int filaActual, int columnaActual, Ficha ficha) {
        int n = 0;
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;

       for (int fila = filaInicial, columna = columnaInicial; fila < FILAS && columna < COLUMNAS; fila++, columna++) {
           if (casillas[fila][columna].toString().equals(ficha.toString())) {
               n++;
           }else if (n < FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
               n = 0;
           }
       }
        return objetivoAlcanzado(n);
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int n = 0;
        int desplazamiento = menor(filaActual, (COLUMNAS - columnaActual));
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        for (int fila = filaInicial, columna = columnaInicial; fila < FILAS && columna < COLUMNAS; fila++, columna++) {
            if (casillas[fila][columna].toString().equals(ficha.toString())) {
                n++;
            }else if (n < FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                n = 0;
            }
        }

        return objetivoAlcanzado(n);
    }

    private boolean comprobarTirada(int fila, int columna) {
        Ficha ficha = casillas[fila][columna].getFicha();
        return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha)
                || comprobarDiagonalNO(fila, columna, ficha) || comprobarDianogalNE(fila, columna, ficha);
    }

    public boolean introducirFicha (int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarFicha(ficha);
        comprobarColumna(columna);
        if (columnaLlena(columna)) {
            return true;
        }
        int pos = getPrimeraFilaVacia(columna);
        Casilla c = new Casilla();
        c.setFicha(ficha);
        this.casillas[pos][columna] = c;
        return comprobarTirada(pos, columna);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < FILAS; i++) {
            sb.append("|");
            for (int j = 0; j < COLUMNAS; j++) {
                sb.append(casillas[i][j]);
            }
            sb.append("|\n");
        }

        sb.append(" ");
        for (int j = 0; j < COLUMNAS ; j++) {
            sb.append("-");
        }
        sb.append("\n");

        return sb.toString();
    }
}
