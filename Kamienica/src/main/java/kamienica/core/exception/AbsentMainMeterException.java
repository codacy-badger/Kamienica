package kamienica.core.exception;

public class AbsentMainMeterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1852521379355997801L;

	public AbsentMainMeterException() {
	}

	public AbsentMainMeterException(String message) {
		super(message);
	}

	public AbsentMainMeterException(Throwable cause) {
		super(cause);
	}

	public AbsentMainMeterException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbsentMainMeterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
