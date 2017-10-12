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
public class CabinDoorStateAbriendose implements CabinDoorState {

    private CabinDoor cabinDoor;

    public CabinDoorStateAbriendose(CabinDoor cabinDoor) {
        this.cabinDoor = cabinDoor;
    }
  
    @Override
    public boolean estaAbierta() {
        return false;
    }

    @Override
    public boolean estaAbriendose() {
        return true;
    }

    @Override
    public boolean estaCerrandose() {
        return false;
    }

    @Override
    public boolean estaCerrada() {
        return false;
    }

    @Override
    public void abrir() {
        this.cabinDoor.abrirSiAbiendose();
    }

    @Override
    public void cerrar() {
        this.cabinDoor.cerradoSiAbiendoce();
    }
    
}
