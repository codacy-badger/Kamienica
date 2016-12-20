package kamienica.core.exception;

public class InvalidDivisionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1877305453779752953L;

	public InvalidDivisionException() {
		super("Sum for one element is not equal to 1");
	}
	
	public InvalidDivisionException(String message) {
		super(message);
	}
}
