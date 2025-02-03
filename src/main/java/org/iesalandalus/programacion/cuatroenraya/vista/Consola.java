package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.utilidades.Entrada;

public final class Consola {

    // Constructor privado para evitar instancias de la clase
    private Consola() {
        // Evita que se puedan crear instancias de esta clase
    }

    public static String leerNombre() {
        String nombre;
        do {
            System.out.print("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo.");
            }
        } while (nombre.isEmpty());
        return nombre;
    }

    public static Ficha elegirColorFichas() {
        Ficha colorFicha = null;
        do {
            System.out.print("Elige el color de las fichas (AZUL o VERDE): ");
            String color = Entrada.cadena().trim().toUpperCase();
            try {
                colorFicha = Ficha.valueOf(color);
            } catch (IllegalArgumentException e) {
                System.out.println("Color no válido. Debe ser AZUL o VERDE.");
            }
        } while (colorFicha == null);
        return colorFicha;
    }

    public static Jugador leerJugador() {
        String nombre = leerNombre();
        Ficha colorFicha = elegirColorFichas();
        return new Jugador(nombre, colorFicha);
    }

    public static Jugador leerJugador(Ficha ficha) {
        String nombre = leerNombre();
        return new Jugador(nombre, ficha);
    }


    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.print(jugador.nombre() + ", elige la columna (0-" + (Tablero.COLUMNAS - 1) + "): ");
            columna = Entrada.entero();
            if (columna < 0 || columna >= Tablero.COLUMNAS) {
                System.out.println("Columna no válida. Debe estar entre 0 y " + (Tablero.COLUMNAS - 1) + ".");
            }
        } while (columna < 0 || columna >= Tablero.COLUMNAS);
        return columna;
    }
}