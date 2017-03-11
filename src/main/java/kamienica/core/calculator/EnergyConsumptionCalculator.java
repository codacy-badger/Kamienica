package kamienica.core.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class EnergyConsumptionCalculator {

    public static List<MediaUsage> countConsumption(List<Apartment> apartment, List<Reading> oldReadings,
                                                    List<Reading> newReadings) {
        ArrayList<MediaUsage> out = new ArrayList<>();
        for (Apartment m : apartment) {

            MediaUsage mediaUsage = createNewUsageValue(m);
            double sumPrevious = 0;
            double sumCurrent = 0;

            for (int i = 0; i < newReadings.size(); i++) {
                if (newReadings.get(i).getMeter().getApartment() != null) {
                    if (newReadings.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
                        sumCurrent = sumCurrent + newReadings.get(i).getValue();
                    }
                }
                if (!oldReadings.isEmpty()) {
                    if (oldReadings.get(i).getMeter().getApartment() != null) {
                        if (oldReadings.get(i).getMeter().getApartment().getApartmentNumber() == m
                                .getApartmentNumber()) {
                            sumPrevious = sumPrevious + oldReadings.get(i).getValue();
                        }
                    }
                }
            }

            double usage = sumCurrent - sumPrevious;

            mediaUsage.setUsage(usage);
//            mediaUsage.setUnit(newReadings.get(0).getUnit());
            if (oldReadings.isEmpty()) {
                mediaUsage.setDaysBetweenReadings(0);
            } else {
                mediaUsage.setDaysBetweenReadings(
                        CommonUtils.countDaysBetween(oldReadings.get(0).getReadingDetails().getReadingDate(), newReadings.get(0).getReadingDetails().getReadingDate()));
            }
            out.add(mediaUsage);
        }

        return out;

    }

    private static MediaUsage createNewUsageValue(Apartment m) {
        MediaUsage mediaUsage = new MediaUsage();
        mediaUsage.setDescription("Zuzycie calkowite za: " + m.getDescription());
        mediaUsage.setApartment(m);
        return mediaUsage;
    }
}
