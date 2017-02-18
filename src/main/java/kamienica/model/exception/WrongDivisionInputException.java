package kamienica.model.exception;

public class WrongDivisionInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1048107997036592812L;

	public WrongDivisionInputException() {
		super("The required data is incomplete");
	}

}
