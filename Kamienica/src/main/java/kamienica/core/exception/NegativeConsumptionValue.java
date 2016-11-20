package kamienica.core.exception;

import kamienica.feature.apartment.Apartment;

/**
 * Created by macfol on 10/15/16.
 */
public class NegativeConsumptionValue extends Throwable {


	private static final long serialVersionUID = 1L;

	public NegativeConsumptionValue(double total, Apartment m) {
        super("Total usage value for " + m.getDescription() + " has a negative value: " + total);
    }
}
