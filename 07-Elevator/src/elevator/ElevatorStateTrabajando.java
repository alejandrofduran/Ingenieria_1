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
public class ElevatorStateTrabajando implements ElevatorState{

    Elevator elevator;

    public ElevatorStateTrabajando(Elevator elevator) {
        this.elevator = elevator;
    }
    
    @Override
    public boolean estaIdle() {
        return false;
    }

    @Override
    public boolean estaTrabajando() {
        return true;
    }
    
    @Override
    public void subirEmpujadaDesdeElSuelo(int alPiso) {
        this.elevator.subirEmpujadaDesdeElSueloTrabajando(alPiso);
    }

    @Override
    public void cerrarPuertaDeCabina() {
        this.elevator.cerrrPuertaDeCabinaTrabajando();
    }
}
