package elevator;

public class CabinDoor {

    public static final String SENSOR_DESINCRONIZED = "Sensor de puerta desincronizado";

    enum CabinDoorState {OPENED, OPENING, CLOSING, CLOSED};
    
    private Cabin cabin;
    private Motor motor;
    private CabinDoorState state;
    
    public CabinDoor(Cabin cabin) {
        this.cabin = cabin;
        this.motor = new Motor();
        this.state = CabinDoorState.OPENED;
    }

    //State
    //Esta abierto
    public boolean isOpened() {
        return this.state == CabinDoorState.OPENED;
    }

    //Esta abrtiendose
    public boolean isOpening() {
        return this.state == CabinDoorState.OPENING;
    }

    //Esta cerrandose
    public boolean isClosing() {
        return this.state == CabinDoorState.CLOSING;
    }

    //Esta Cerrado
    public boolean isClosed() {
        return this.state == CabinDoorState.CLOSED;
    }

    //Actions
    //Empieza a cerrarse
    public void startClosing() {
        cabin.assertMotorIsNotMoving();
        this.state = CabinDoorState.CLOSING;        
        motor.moveClockwise();
    }

    //Empieza a abrirse
    public void startOpening() {
        cabin.assertMotorIsNotMoving();

        this.state = CabinDoorState.OPENING;
        
        motor.moveCounterClockwise();
    }

    //Sensor events
    //Cerrado
    public void closed() {
        motor.stop();
        this.state = CabinDoorState.CLOSED;
    }

    //Abierto
    public void opened() {
        motor.stop();
        this.state = CabinDoorState.OPENED;
    }

    //Button events
    //abrir
    public void open() {
        this.motor.stop();
        this.motor.moveCounterClockwise();
        this.state = CabinDoorState.OPENING;
    }

    //Cerrar
    public void close() {
        this.motor.stop();
        this.motor.moveClockwise();
        this.state = CabinDoorState.CLOSING;
    }

    //El motor se esta moviendo en sentido de las agujas del reloj
    public boolean isMotorMovingClockwise() {
        return motor.isMovingClockwise();
    }

    //El motor esta detenido
    public boolean isMotorStopped() {
        return motor.isStopped();
    }

    //EL motor se esta moviendo en contra del reloj
    public boolean isMotorMovingCounterClockwise() {
        return motor.isMovingCounterClockwise();
    }
}
