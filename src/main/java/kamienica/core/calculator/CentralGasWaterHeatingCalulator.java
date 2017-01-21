package kamienica.core.calculator;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.exception.UsageCalculationException;
import kamienica.model.Apartment;
import kamienica.model.MediaUsage;
import kamienica.model.Reading;

import java.util.List;

public class CentralGasWaterHeatingCalulator implements ConsumptionCalculator {
    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
        return null;
    }
}
