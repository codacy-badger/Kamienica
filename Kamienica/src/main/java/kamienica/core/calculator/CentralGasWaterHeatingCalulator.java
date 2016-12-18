package kamienica.core.calculator;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.exception.UsageCalculationException;
import kamienica.feature.reading.Reading;
import kamienica.model.Apartment;
import kamienica.model.MediaUsage;

import java.util.List;

/**
 * Created by macfol on 12/7/16.
 */
public class CentralGasWaterHeatingCalulator implements ConsumptionCalculator {
    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
        return null;
    }
}
