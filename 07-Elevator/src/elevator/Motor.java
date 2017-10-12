package elevator;

public class Motor {
	
	public static final String MOTOR_IS_MOVING = "El motor se esta moviendo";

	enum MotorState {
		STOPPED, //Parado
		MOVING_CLOCKWISE, //moviendoce en sentido de las agujas del reloj
		MOVING_COUNTER_CLOCKWISE //moviendoce en contra de las agujas del reloj
	};
	
	private ExceptionHandler movingHandler;
	
	private MotorState state;
	
	public Motor() {
		state = MotorState.STOPPED;
		movingHandler = new NullException();
	}
	
        //Parar
	public void stop() {
		state = MotorState.STOPPED;
		movingHandler = new NullException();
	}

        //Moverce en sentido de las agujas del reloj
	public void moveClockwise() {
		assertIsNotMoving();
		state = MotorState.MOVING_CLOCKWISE;
		movingHandler = new MovingExceptionHandler();
	}

        //Mocerse en contra de las ajuigas del reloj
	public void moveCounterClockwise() {
		assertIsNotMoving();
		
		state = MotorState.MOVING_COUNTER_CLOCKWISE;
		movingHandler = new MovingExceptionHandler();
	}

        //se esta moviendo en contra de las agujas del reloj
	public boolean isMovingCounterClockwise() {
		return state==MotorState.MOVING_COUNTER_CLOCKWISE;
	}

        //Esta mociendoce en sentido de las agujas del reloj
	public boolean isMovingClockwise() {
		return state==MotorState.MOVING_CLOCKWISE;
	}

        //Esta detenido
	public boolean isStopped() {
		return state==MotorState.STOPPED;
	}

        //Esta moviendoce
	public boolean isMoving() {
		return !isStopped();
	}

        //assert no se esta moviendo
	void assertIsNotMoving() {
		movingHandler.throwEx();
	}
}
