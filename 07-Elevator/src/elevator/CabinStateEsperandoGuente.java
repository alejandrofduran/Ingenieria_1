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
public class CabinStateEsperandoGuente implements CabinState{

    private Cabin cabin;

    public CabinStateEsperandoGuente(Cabin cabin) {
        this.cabin = cabin;
    }
    
    @Override
    public boolean estaParada() {
        return false;
    }

    @Override
    public boolean estaMoviendose() {
        return false;
    }

    @Override
    public boolean estaEsperandoGuente() {
        return true;
    }

    @Override
    public void cerrarPuerta() {
        this.cabin.cerrarPuertaCabinaEsperandoGuente();
    }

    
}
