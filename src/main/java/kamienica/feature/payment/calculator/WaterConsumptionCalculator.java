package kamienica.feature.payment.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class WaterConsumptionCalculator {

    public static ArrayList<MediaUsage> countConsumption(List<Apartment> apartment, List<Reading> oldReading,
                                                         List<Reading> newRading) {

        Reading sharedReadingOld;
        if (!oldReading.isEmpty()) {
            sharedReadingOld = WaterConsumptionCalculator.generateUsageForAdministrativePart(oldReading, apartment);
            oldReading.add(sharedReadingOld);
        }
        Reading sharedReadingNew = WaterConsumptionCalculator.generateUsageForAdministrativePart(newRading, apartment);
        newRading.add(sharedReadingNew);

        ArrayList<MediaUsage> usage = new ArrayList<>();
        for (Apartment m : apartment) {
            MediaUsage tmpUsage = new MediaUsage();
            tmpUsage.setDescription("Zuzycie calkowite za: " + m.getDescription());
            tmpUsage.setApartment(m);
            double sumPrevious = 0;
            double sumNew = 0;

            for (int i = 0; i < newRading.size(); i++) {
                if (isReadingForApartmentInScope(newRading, m, i)) {
                    sumNew = sumNew + newRading.get(i).getValue();
                }
                if (!oldReading.isEmpty() && isReadingForApartmentInScope(oldReading, m, i)) {
                    sumPrevious = sumPrevious + oldReading.get(i).getValue();
                }
            }
            double zuzycie = sumNew - sumPrevious;
            tmpUsage.setUsage(zuzycie);
            if (!oldReading.isEmpty()) {
                tmpUsage.setDaysBetweenReadings(Days.daysBetween(oldReading.get(0).getReadingDetails().getReadingDate(), newRading.get(0).getReadingDetails().getReadingDate()).getDays());
            } else {
                tmpUsage.setDaysBetweenReadings(0);
            }
            usage.add(tmpUsage);
        }

        return usage;

    }

    private static boolean isReadingForApartmentInScope(List<Reading> oldReading, Apartment m, int i) {
        return oldReading.get(i).getMeter().getApartment() != null && oldReading.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber();
    }

    // metoda stworzona gdy� nie istnieje fizyczny licznik wody dla czesci
    // administracyjnej a cześć wpólna jest wylczana jako rónica między
    // licznikiemgłównym a poszczególnymi licznikami
    private static Reading generateUsageForAdministrativePart(List<Reading> readings,
                                                              List<Apartment> apartments) {
        Apartment apartment = apartments.stream().filter(x -> x.getApartmentNumber() == 0).findFirst().get();


        //TODO hardcoded unit? Do we even need to keep unit?
        Meter temporaryWaterMeter = new Meter("Część Wspólna", "N/A", "m3", apartment, apartment.getResidence(), false, Status.ACTIVE, false, false, Media.WATER);
        double sumOfOthers = 0;
        double main = 0;

        for (Reading odczyt : readings) {
            if (odczyt.getMeter().getApartment() != null) {
                sumOfOthers += odczyt.getValue();
            } else {
                main = odczyt.getValue();
            }
        }
        final double sharedPartValue = main - sumOfOthers;
        return new Reading(readings.get(0).getReadingDetails(), sharedPartValue, apartment.getResidence(), temporaryWaterMeter);
    }

    // metoda dla managera gazu
    public static double countWarmWaterUsage(List<Reading> oldReadings, List<Reading> newReadings) {
        double output = 0;

        for (Reading o : newReadings) {
            if (o.getMeter().isWarmWater()) output += o.getValue();
        }

        for (Reading o : oldReadings) {
            if (o.getMeter().isWarmWater()) output = output - o.getValue();
        }

        return output;
    }
}
