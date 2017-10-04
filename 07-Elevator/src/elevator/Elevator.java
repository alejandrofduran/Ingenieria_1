/*
 * Developed by 10Pines SRL
 * License: 
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 *  
 */
package elevator;

import java.util.SortedSet;
import java.util.TreeSet;

public class Elevator {

    private Cabin cabin;
    private SortedSet<Integer> floorsToGo = new TreeSet<Integer>();
    private SortedSet<Integer> pisosDeSensor = new TreeSet<Integer>();
    private boolean idle;

    public Elevator() {
        cabin = new Cabin(this);
        this.idle = true;

    }

    //Elevator state
    public boolean isIdle() {
        return this.idle;
    }

    //Esta funcionado
    public boolean isWorking() {
        return !this.idle;
    }

    //Door state
    //La puerta de la cabina esta abierta
    public boolean isCabinDoorOpened() {
        return cabin.isDoorOpened();
    }

    //La puerta de la cabina esta abriendose
    public boolean isCabinDoorOpening() {
        return cabin.isDoorOpening();
    }

    //La puerta de la cabina esta cerada
    public boolean isCabinDoorClosed() {
        return cabin.isDoorClosed();
    }

    //La puerta de la cabina esta cerrandose
    public boolean isCabinDoorClosing() {
        return cabin.isDoorClosing();
    }

    //Cabin state
    //Numero de piso de la cabina
    public int cabinFloorNumber() {
        return cabin.currentFloorNumber();
    }

    //La cabina esta parada
    public boolean isCabinStopped() {
        return cabin.isStopped();
    }

    //La cabina esta Moviendose
    public boolean isCabinMoving() {
        return cabin.isMoving();
    }

    //La cabina esta esperando gente
    public boolean isCabinWaitingForPeople() {
        return cabin.isWaitingForPeople();
    }

    //Button Events
    //Subir empujada desde el suelo
    public void goUpPushedFromFloor(int aFloorNumber) {
        this.idle = false;
        this.floorsToGo.add(aFloorNumber);
        
        for (int i = cabin.currentFloorNumber(); i < aFloorNumber; i++) {
           pisosDeSensor.add(i+1); 
        }
                
        if (this.isCabinDoorOpened() || this.isCabinDoorOpening()) {
            this.cabin.closeDoor();
        }
    }

    //Abrir puerta de la cabina                        
    public void openCabinDoor() {
        if (this.isCabinStopped() && !this.isCabinDoorOpened()) {
            cabin.openDoor();
        }
    }

    //Cerrar puerta de la cabina
    public void closeCabinDoor() {
        if (this.isCabinStopped() && !this.isCabinDoorClosed()
                && this.isWorking() && !this.isCabinDoorOpening()) {
            cabin.closeDoor();
        }
    }

    //Sensor Events
    //Cabina en piso
    public void cabinOnFloor(int aFloorNumber) {

        if (!pisosDeSensor.isEmpty() && pisosDeSensor.first().equals(aFloorNumber)) {
            pisosDeSensor.remove(aFloorNumber);
        }else{
            throw new RuntimeException(Cabin.SENSOR_DESINCRONIZED);
        }

        if (this.floorsToGo.first().equals(aFloorNumber)) {
            cabin.onFloor(aFloorNumber);
            this.floorsToGo.remove(aFloorNumber);
        }
    }

    //Puerta de la cabina cerrada
    public void cabinDoorClosed() {
        
        if (!this.hasFloorsToGo() || this.isCabinDoorClosed()) {
            throw new RuntimeException(CabinDoor.SENSOR_DESINCRONIZED);
        }
        
        if (this.isCabinStopped()) {
            this.idle = false;
            cabin.doorClosed();
        }
    }

    //Puerta de la cabina abierta
    public void cabinDoorOpened() {
        if (this.isCabinStopped()) {
            if (!this.hasFloorsToGo()) {
                this.idle = true;
            }
            cabin.doorOpened();
            this.cabin.waitForPeople();
        }
    }

    //Esperar por gente se quedo sin tiempo
    public void waitForPeopleTimedOut() {
        cabin.waitForPeopleTimedOut();
        cabin.closeDoor();
    }

    //Floors queue
    //Esta implementación es muy sencilla y asume que siempre se esta yendo para arriba
    //Tiene que detenerce en el piso actual
    public boolean hasToStopOnCurrentFloor() {
        return floorsToGo.first() == cabin.currentFloorNumber();
    }

    //Alcanzo piso en el que para
    public void reachedFloorToStop() {
        floorsToGo.remove(floorsToGo.first());
    }

    //Tiene pisos por llegar
    public boolean hasFloorsToGo() {
        return !floorsToGo.isEmpty();
    }

    //El motor de la puerta de la cabina se esta moviendo en sentido de las agujas del reloj
    public boolean isCabinDoorMotorMovingClockwise() {
        return cabin.isDoorMotorMovingClockwise();
    }

    //El motor de la cabina esta detenido
    public boolean isCabinMotorStopped() {
        return cabin.isMotorStopped();
    }

    //El motor de la puerta de la cabina esta detenido
    public boolean isCabinDoorMotorStopped() {
        return cabin.isDoorMotorStopped();
    }

    //El motor de la cabina esta moviendose en sentido de las agujas del reloj
    public boolean isCabinMotorMovingClockwise() {
        return cabin.isMotorMovingClockwise();
    }

    //El motor de la puerta de la cabina esta moviendose en sentido de las agujas del reloj
    public boolean isCabinDoorMotorMovingCounterClockwise() {
        return cabin.isDoorMotorMovingCounterClockwise();
    }

}
