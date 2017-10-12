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
public class CabinDoorStateCerrada implements CabinDoorState {

    private CabinDoor cabinDoor;

    public CabinDoorStateCerrada(CabinDoor cabinDoor) {
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
        return false;
    }

    @Override
    public boolean estaCerrada() {
        return true;
    }

    @Override
    public void abrir() {
        this.cabinDoor.abrirSiCerrada();
    }

    @Override
    public void cerrar() {
        this.cabinDoor.cerradoSiCerrada();
    }
    
}
