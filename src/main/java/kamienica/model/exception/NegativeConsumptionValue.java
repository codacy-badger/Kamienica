package kamienica.model.exception;

import kamienica.model.entity.Apartment;

public class NegativeConsumptionValue extends Exception {


	private static final long serialVersionUID = 1L;

	public NegativeConsumptionValue(double total, Apartment m) {
        super("Total usage value for " + m.getDescription() + " has a negative value: " + total);
    }
}
