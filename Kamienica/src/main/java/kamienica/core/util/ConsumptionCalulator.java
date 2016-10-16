package kamienica.core.util;

import java.util.List;

import kamienica.core.exception.NegativeConsumptionValue;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

public interface ConsumptionCalulator {

    List<UsageValue> calculateConsumption(List<Apartment> apartment,
                                          List<Reading> oldReadings, List<Reading> newReadings) throws NegativeConsumptionValue;


}
