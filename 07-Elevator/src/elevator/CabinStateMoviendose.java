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
public class CabinStateMoviendose implements CabinState{

    private Cabin cabin;

    public CabinStateMoviendose(Cabin cabin) {
        this.cabin = cabin;
    }
    
    @Override
    public boolean estaParada() {
        return false;
    }

    @Override
    public boolean estaMoviendose() {
        return true;
    }

    @Override
    public boolean estaEsperandoGuente() {
        return false;
    }

    @Override
    public void cerrarPuerta() {
        this.cabin.cerrarPuertaCabinaMoviendose();
    }

    
}
