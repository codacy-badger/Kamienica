package kamienica.core.exception;

public class InvalidDivisionException extends Exception {

	public InvalidDivisionException() {
		super("Sum for one element is not equal to 1");
	}
}
