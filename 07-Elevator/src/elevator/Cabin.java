package elevator;

public class Cabin {

	public static final String SENSOR_DESINCRONIZED = "Sensor de cabina desincronizado";
	
	private Elevator elevator;
	private CabinDoor door;
	private Motor motor;
	private int currentFloorNumber;
	private boolean waitForPeople;
        
	public Cabin(Elevator elevator) {
		this.elevator = elevator;
		this.door = new CabinDoor(this);
		this.motor = new Motor();
		currentFloorNumber = 0;
                this.waitForPeople = true;
	}

        //Numero de piso
	public int currentFloorNumber() {
		return currentFloorNumber;
	}

	//Cabin State
        //Esta parado
	public boolean isStopped() {
		return motor.isStopped();
	}

        //Esta moviendose
	public boolean isMoving() {
		return motor.isMoving();
	}

        //Esperando gente
	public boolean isWaitingForPeople() {
		return this.waitForPeople;
	}

	//Cabin Actions
        //Comando para
	public void stop() {
		motor.stop();
	}

        //Esperando gente
	public void waitForPeople() {
		this.waitForPeople = true;
	}

	//Cabin events
        //Esperando gente se quedo sin tiempo
	public void waitForPeopleTimedOut() {
		this.waitForPeople = false;
	}

        //En el piso
	public void onFloor(int aFloorNumber) {
		this.motor.stop();
                this.door.startOpening();
                this.currentFloorNumber = aFloorNumber;
	}

	//Door state
        //Esta puerta abierta
	public boolean isDoorOpened() {
		return door.isOpened();
	}

        //Esta puerta abriendose
	public boolean isDoorOpening() {
		return door.isOpening();
	}

        //Esta puerta cerrandose
	public boolean isDoorClosing() {
		return door.isClosing();
	}

        //Esta puerta cerrada
	public boolean isDoorClosed() {
		return door.isClosed();
	}

	//Door - Sensor events
        //Puerta cerrada
	public void doorClosed() {
            this.motor.moveClockwise();
            door.closed();    
	}
        
        //puerta abierta
	public void doorOpened() {
                this.motor.stop();
		door.opened();
	}

	//Door - Button events
        //Abrir puerta
	public void openDoor() {
		door.open();
	}

        //Cerrar puerta
	public void closeDoor() {
		door.close();
	}

        //assert motor no se esta moviendo
	public void assertMotorIsNotMoving() {
		motor.assertIsNotMoving();
	}

        //el motor de la puerta se esta moviendo en sentido de las agujas del reloj
	public boolean isDoorMotorMovingClockwise() {
		return door.isMotorMovingClockwise();
	}

        //el motor esta detenido
	public boolean isMotorStopped() {
		return motor.isStopped();
	}

        //el motor de la puerta esta detenido
	public boolean isDoorMotorStopped() {
		return door.isMotorStopped();
	}

        //El motor se esta moviendo en sentido de las agujas del reloj
	public boolean isMotorMovingClockwise() {
		return motor.isMovingClockwise();
	}

        //El motor de la puerta se esta moviendo contra reloj
	public boolean isDoorMotorMovingCounterClockwise() {
		return door.isMotorMovingCounterClockwise();
	}
}
