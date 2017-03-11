package kamienica.core.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CentralGasWaterHeatingCalulator implements IConsumptionCalculator {
    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
        return null;
    }
}
