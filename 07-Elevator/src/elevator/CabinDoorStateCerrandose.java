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
public class CabinDoorStateCerrandose implements CabinDoorState {

    private CabinDoor cabinDoor;

    public CabinDoorStateCerrandose(CabinDoor cabinDoor) {
        this.cabinDoor = cabinDoor;
    }
  
    @Override
    public boolean estaAbierta() {
        return false;
    }

    @Override
    public boolean estaAbriendose() {
        return false;
    }

    @Override
    public boolean estaCerrandose() {
        return true;
    }

    @Override
    public boolean estaCerrada() {
        return false;
    }

    @Override
    public void abrir() {
        this.cabinDoor.abrirSiCerrandose();
    }

    @Override
    public void cerrar() {
        this.cabinDoor.cerradoSiCerrandoce();
    }
    
}
