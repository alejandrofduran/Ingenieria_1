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
public class ElevatorStateIdle implements ElevatorState{

    Elevator elevator;

    public ElevatorStateIdle(Elevator elevator) {
        this.elevator = elevator;
    }
    
    @Override
    public boolean estaIdle() {
        return true;
    }

    @Override
    public boolean estaTrabajando() {
        return false;
    }

    @Override
    public void subirEmpujadaDesdeElSuelo(int alPiso) {
        this.elevator.subirEmpujadaDesdeElSueloIdle(alPiso);
    }

    @Override
    public void cerrarPuertaDeCabina() {
        this.elevator.cerrrPuertaDeCabinaIdle();
    }
    
}
