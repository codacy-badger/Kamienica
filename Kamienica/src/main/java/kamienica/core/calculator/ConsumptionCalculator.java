package kamienica.core.calculator;

import kamienica.core.exception.IncompatibleReadingType;
import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.model.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

import java.util.List;

public interface ConsumptionCalculator {

    List<UsageValue> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue, IncompatibleReadingType;
}
