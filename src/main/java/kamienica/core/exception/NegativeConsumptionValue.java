package kamienica.core.exception;

import kamienica.model.Apartment;

public class NegativeConsumptionValue extends Throwable {


	private static final long serialVersionUID = 1L;

	public NegativeConsumptionValue(double total, Apartment m) {
        super("Total usage value for " + m.getDescription() + " has a negative value: " + total);
    }
}
