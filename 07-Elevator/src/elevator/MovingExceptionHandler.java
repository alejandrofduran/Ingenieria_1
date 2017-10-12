package elevator;

public class MovingExceptionHandler implements ExceptionHandler{

	@Override
	public void throwEx() {
		throw new RuntimeException(Motor.MOTOR_IS_MOVING);
	}

}
