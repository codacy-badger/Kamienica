package kamienica.core.util;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;

public class GenericConsumptionCalculator implements ConsumptionCalulator {


    public List<UsageValue> calculateConsumption(List<Apartment> apartment, List<Reading> oldReadings,
                                             List<Reading> newReadings) throws NegativeConsumptionValue {
        List<UsageValue> out = new ArrayList<>();
        for (Apartment m : apartment) {

            UsageValue usageValue = createNewUsageValue(m);
            double totalUsage = 0;

            for (Reading newReading : newReadings) {
                totalUsage += getValue(m, newReading);
            }

            for (int i = 0; i < newReadings.size(); i++) {
                totalUsage -= getValue(m, oldReadings.get(i));
            }


            if (totalUsage < 0) {
                throw new NegativeConsumptionValue(totalUsage, m);
            }

            usageValue.setUsage(totalUsage);
            usageValue.setUnit(newReadings.get(0).getUnit());
            usageValue.setDaysBetweenReadings(getDaysBetween(oldReadings, newReadings));
            out.add(usageValue);
        }

        return out;

    }

    private int getDaysBetween(List<Reading> oldReadings, List<Reading> newReadings) {
        if (oldReadings.isEmpty()) {
            return 0;
        } else {
            return Days.daysBetween(oldReadings.get(0).getReadingDate(), newReadings.get(0).getReadingDate())
                    .getDays();
        }
    }

    private static UsageValue createNewUsageValue(Apartment m) {
        UsageValue usageValue = new UsageValue();
        usageValue.setDescription("Zuzycie calkowite za: " + m.getDescription());
        usageValue.setApartment(m);
        return usageValue;
    }


    private double getValue(Apartment apartment, Reading reading) {
        if (reading.getMeter().getApartment() == null) {
            return 0;
        }
        if (apartment.getApartmentNumber() == reading.getMeter().getApartment().getApartmentNumber()) {
            return reading.getValue();
        }
        return 0;
    }


}
