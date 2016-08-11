package kamienica.core.exception;

public class InvalidDivisionException extends Exception {

	public InvalidDivisionException() {
		super("Sum for one element is not equal to 1");
	}
	
	public InvalidDivisionException(String message) {
		super(message);
	}
}
