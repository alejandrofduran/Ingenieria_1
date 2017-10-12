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
public interface CabinState {

    boolean estaParada();
    boolean estaMoviendose();
    boolean estaEsperandoGuente();

    void cerrarPuerta();
}
