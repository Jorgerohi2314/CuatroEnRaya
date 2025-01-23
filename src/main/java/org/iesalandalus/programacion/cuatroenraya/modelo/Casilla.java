package org.iesalandalus.programacion.cuatroenraya.modelo;




public class Casilla {

    private Ficha ficha;
    public Casilla() {
        this.ficha = null;

    }
    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) throws CuatroEnRayaExcepcion {
        if (ficha == null) {
            throw new NullPointerException("No se puede poner una ficha nula.");
        }
        if (estaOcupada()) {
            throw new CuatroEnRayaExcepcion("La casilla ya contiene una ficha.");
        }
        this.ficha = ficha;
    }

    public boolean estaOcupada() {
        return this.ficha != null;
    }


    @Override
    public String toString() {
        return String.format("%s", this.ficha != null? this.ficha.toString():" ");
    }
}


