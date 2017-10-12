/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;

/**
 *
 * @author Cronos
 */
public class CabinStateParada implements CabinState{

    private Cabin cabin;

    public CabinStateParada(Cabin cabin) {
        this.cabin = cabin;
    }
    
    @Override
    public boolean estaParada() {
        return true;
    }

    @Override
    public boolean estaMoviendose() {
        return false;
    }

    @Override
    public boolean estaEsperandoGuente() {
        return false;
    }

    @Override
    public void cerrarPuerta() {
        this.cabin.cerrarPuertaCabinaParada();
    }

    
}
