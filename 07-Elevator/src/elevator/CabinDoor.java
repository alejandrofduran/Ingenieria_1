package elevator;

public class CabinDoor {

    public static final String SENSOR_DESINCRONIZED = "Sensor de puerta desincronizado";

    /*enum CabinDoorState {
        OPENED, OPENING, CLOSING, CLOSED
    };*/
    private Cabin cabin;
    private Motor motor;
    private CabinDoorState state;

    public CabinDoor(Cabin cabin) {
        this.cabin = cabin;
        this.motor = new Motor();
        //this.state = CabinDoorState.OPENED;
        this.state = new CabinDoorStateAbierta(this);
    }

    //State
    //Esta abierto
    public boolean isOpened() {
        //return this.state == CabinDoorState.OPENED;
        return this.state.estaAbierta();
    }

    //Esta abrtiendose
    public boolean isOpening() {
        //return this.state == CabinDoorState.OPENING;
        return this.state.estaAbriendose();
    }

    //Esta cerrandose
    public boolean isClosing() {
        //return this.state == CabinDoorState.CLOSING;
        return this.state.estaCerrandose();
    }

    //Esta Cerrado
    public boolean isClosed() {
        //return this.state == CabinDoorState.CLOSED;
        return this.state.estaCerrada();
    }

    //Actions
    //Empieza a cerrarse
    public void startClosing() {
        cabin.assertMotorIsNotMoving();
        //this.state = CabinDoorState.CLOSING;
        motor.moveClockwise();
        this.state = new CabinDoorStateCerrandose(this);
    }

    //Empieza a abrirse
    public void startOpening() {
        cabin.assertMotorIsNotMoving();
        //this.state = CabinDoorState.OPENING;
        motor.moveCounterClockwise();
        this.state = new CabinDoorStateAbriendose(this);

    }

    //Sensor events
    //Cerrado
    public void closed() {
        //motor.stop();
        //this.state = CabinDoorState.CLOSED;
        //this.state = this.cabinDoorCerrada();
        this.state.cerrar();
    }

    public void cerradoSiAbierta() {
        throwSensorDesincoizado();
    }

    public void cerradoSiAbiendoce() {
        throwSensorDesincoizado();
    }

    public void cerradoSiCerrandoce() {
        motor.stop();
        this.state = new CabinDoorStateCerrada(this);
    }

    public void cerradoSiCerrada() {
        throwSensorDesincoizado();
    }

    private void throwSensorDesincoizado() throws RuntimeException {
        throw new RuntimeException(SENSOR_DESINCRONIZED);
    }

    //Abierto
    public void opened() {
        motor.stop();
        //this.state = CabinDoorState.OPENED;
        this.state = new CabinDoorStateAbierta(this);
    }

    //Button events
    //abrir
    public void open() {
        //this.motor.stop();
        //this.motor.moveCounterClockwise();
        //this.state = CabinDoorState.OPENING;
        //this.cabinDoorAbriendose();
        this.state.abrir();
    }

    public void abrirSiAbierta() {
    }

    public void abrirSiAbiendose() {
    }

    public void abrirSiCerrandose() {
        this.motor.stop();
        this.motor.moveCounterClockwise();
        this.state = new CabinDoorStateAbriendose(this);
    }

    public void abrirSiCerrada() {
    }

    //Cerrar
    public void close() {
        this.motor.stop();
        //this.motor.moveClockwise();
        //this.state = CabinDoorState.CLOSING;
        //this.cabinDoorCerrandose();
        this.startClosing();

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
