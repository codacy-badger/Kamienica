package kamienica.core;

public class Message {

	private String message;
	private String exception;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Message(String message, String exception) {
		super();
		this.message = message;
		this.exception = exception;
	}

	public Message() {
		super();
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", exception=" + exception + "]";
	}

}
