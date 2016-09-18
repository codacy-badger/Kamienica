package kamienica.core.exception;

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
		// TODO Auto-generated constructor stub
	}

	public NoMainCounterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoMainCounterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoMainCounterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
