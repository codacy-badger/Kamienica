package kamienica.core.calculator;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.exception.UsageCalculationException;
import kamienica.model.Reading;
import kamienica.model.Apartment;
import kamienica.model.MediaUsage;

import java.util.List;

public interface ConsumptionCalculator {

    List<MediaUsage> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException;
}
