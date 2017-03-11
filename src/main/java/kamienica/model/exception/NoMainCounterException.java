package kamienica.model.exception;

public class NoMainCounterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675248840929869633L;

	public NoMainCounterException() {
		super("Brakuje licznika głównego. Wprowadź brakujące liczniki");
	}

	public NoMainCounterException(String message) {
		super(message);
	}

	public NoMainCounterException(Throwable cause) {
		super(cause);
	}

	public NoMainCounterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoMainCounterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
