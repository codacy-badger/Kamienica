package kamienica.core.util;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

import java.util.List;
import java.util.stream.Stream;

public interface ConsumptionCalulator {

    List<UsageValue> calculateConsumption(List<Apartment> apartment,
                                          List<Reading> readings) throws NegativeConsumptionValue;


}
