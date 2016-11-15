//package kamienica.core.util;
//
//import kamienica.feature.apartment.Apartment;
//import kamienica.feature.reading.Reading;
//import kamienica.feature.usagevalue.UsageValue;
//
//import org.
//import java.util.ArrayList;
//import java.util.List;
//
//public class ConsumptionCalcImpl implements ConsumptionCalc {
//    @Override
//    public List<UsageValue> calculateConsumption(List<Apartment> apartment, List<Reading> readings) {
//
//        List<UsageValue> result = new ArrayList<>();
//
//        LocalDate latestDate = readings.stream().map(u -> u.getReadingDate()).max(LocalDate::compareTo).get();
//        LocalDate previousDate = readings.stream().map(u -> u.getReadingDate()).min(LocalDate::compareTo).get();
//
//        for (Apartment ap : apartment) {
//            double consumption = countUsage(ap, readings);
//            UsageValue value = createDummyUsageValue(ap, latestDate, previousDate, consumption);
//
//        }
//
//        return result;
//    }
//
//    private UsageValue createDummyUsageValue(Apartment ap, LocalDate max, LocalDate min, double consumption) {
//        int daysBetween = Days.daysBetween(max, min).getDays();
//        return new UsageValue("Użycie za " + ap.getDescription(), consumption, "unit", daysBetween, ap);
//    }
//
//    private double countUsage(Apartment ap, List<ReadingAbstract> readings) {
//
//        LocalDate latestDate = readings.stream().map(u -> u.getReadingDate()).max(LocalDate::compareTo).get();
//        LocalDate previousDate = readings.stream().map(u -> u.getReadingDate()).min(LocalDate::compareTo).get();
//
//        Predicate<ReadingAbstract> apartmentPredicate = s -> s.getMeter().getApartment().getId().equals(ap.getId());
//        Predicate<ReadingAbstract> maxDate = s -> s.getReadingDate().equals(latestDate);
//        Predicate<ReadingAbstract> minDate = s -> s.getReadingDate().equals(previousDate);
//
//
//        double consumption;
//        consumption = -readings.stream().filter(apartmentPredicate).filter(minDate).mapToDouble(o -> o.getValue()).sum();
//        consumption = +readings.stream().filter(apartmentPredicate).filter(maxDate).mapToDouble(o -> o.getValue()).sum();
//
//        return consumption;
//    }
//
//
//}
