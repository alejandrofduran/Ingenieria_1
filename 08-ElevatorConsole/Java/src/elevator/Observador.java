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
public interface Observador {

    void cabinStateChangedTo(CabinState cabinState);
    void cabinDoorStateChangedTo(CabinDoorState cabinDoorState);
    
}
