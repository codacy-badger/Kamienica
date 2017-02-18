package kamienica.core.calculator;

import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;

import java.util.List;

public class CentralGasWaterHeatingCalulator implements ConsumptionCalculator {
    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
        return null;
    }
}
