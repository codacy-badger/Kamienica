package kamienica.core;

public class Message {

	String message;

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}

	public Message(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
