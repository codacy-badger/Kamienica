package kamienica.core.util;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

import java.util.List;

public interface StreamConsumptionCalc {

    List<UsageValue> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue;
}
