package kamienica.core.calculator;

import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;

import java.util.List;

public interface ConsumptionCalculator {

    List<MediaUsage> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException;
}
