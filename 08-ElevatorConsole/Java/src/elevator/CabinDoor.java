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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class CabinDoor implements Observable {

    public static final String SENSOR_DESINCRONIZED = "Sensor de puerta desincronizado";

    private Cabin cabin;
    private CabinDoorState state;
    private Motor motor;

    private List<Observador> listaObservador;
    

    public CabinDoor(Cabin cabin) {
        this.cabin = cabin;
        this.motor = new Motor();
        this.listaObservador = new ArrayList<Observador>();
        makeOpened();

    }

    @Override
    public void agregarObservador(Observador observador) {
        this.listaObservador.add(observador);
    }

    @Override
    public void notificar() {
        for (Observador observador : this.listaObservador) {
            observador.cabinDoorStateChangedTo(this.state);
        }
    }

    //State
    private void makeOpened() {
        this.state = new CabinDoorOpenedState(this);
        this.notificar();
    }

    private void makeClosing() {
        this.state = new CabinDoorClosingState(this);
        this.notificar();
    }

    private void makeClosed() {
        this.state = new CabinDoorClosedState(this);
        this.notificar();
    }

    private void makeOpening() {
        this.state = new CabinDoorOpeningState(this);
        this.notificar();
    }

    public boolean isOpened() {
        return state.isOpened();
    }

    public boolean isOpening() {
        return state.isOpening();
    }

    public boolean isClosing() {
        return state.isClosing();
    }

    public boolean isClosed() {
        return state.isClosed();
    }

    //Actions
    public void startClosing() {
        cabin.assertMotorIsNotMoving();

        motor.moveClockwise();
        makeClosing();
    }

    public void startOpening() {
        cabin.assertMotorIsNotMoving();

        motor.moveCounterClockwise();
        makeOpening();
    }

    //Sensor events
    public void closed() {
        state.closed();
    }

    public void closedWhenClosing() {
        motor.stop();
        makeClosed();
    }

    public void closedWhenOpened() {
        throwSensorDesincronized();
    }

    public void closedWhenClosed() {
        throwSensorDesincronized();
    }

    public void closedWhenOpening() {
        throwSensorDesincronized();
    }

    public void throwSensorDesincronized() {
        throw new RuntimeException(SENSOR_DESINCRONIZED);
    }

    public void opened() {
        motor.stop();
        makeOpened();
    }

    //Button events
    public void open() {
        state.open();
    }

    public void openWhenOpened() {
        //No hago nada porque ya esta abierta
    }

    public void openWhenClosing() {
        motor.stop();
        startOpening();
    }

    public void openWhenClosed() {
        //Cuando la puerta ya esta cerrada no se puede abrir
    }

    public void openWhenOpening() {
        //No hago nada porque se esta abriendo
    }

    public void close() {
        motor.stop();
        startClosing();
    }

    public boolean isMotorMovingClockwise() {
        return motor.isMovingClockwise();
    }

    public boolean isMotorStopped() {
        return motor.isStopped();
    }

    public boolean isMotorMovingCounterClockwise() {
        return motor.isMovingCounterClockwise();
    }

}
