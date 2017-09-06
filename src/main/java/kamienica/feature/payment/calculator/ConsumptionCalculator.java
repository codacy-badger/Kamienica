package kamienica.feature.payment.calculator;

import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class ConsumptionCalculator implements IConsumptionCalculator {

    protected final IReadingService readingService;
    protected List<Reading> newReadings;
    protected List<Reading> oldReadins;
    protected List<Apartment> apartments;
    protected Invoice invoice;

    public ConsumptionCalculator(final IReadingService readingService) {
        this.readingService = readingService;
    }

    public List<MediaUsage> calculateConsumption(final Invoice invoice, final List<Apartment> apartments) {
        newReadings = readingService.getForInvoice(invoice);
        oldReadins = readingService.getPreviousReadingForWarmWater(invoice);
        this.apartments = apartments;
        this.invoice = invoice;

        final List<MediaUsage> result = calculateUsage();

        recalculateSharedPartConsuption(result);
        return result;
    }

    protected abstract void recalculateSharedPartConsuption(List<MediaUsage> result);

    protected MediaUsage countUsageForApartment(final Apartment ap) {
        final double totalUsageForNewReadings = getSumForApartment(newReadings, ap);
        final double totalUsageForOldReadings = getSumForApartment(oldReadins, ap);
        final double consumption = totalUsageForNewReadings - totalUsageForOldReadings;
        return new MediaUsage(consumption, ap);
    }

    protected double getSumForApartment(List<Reading> readings, final Apartment ap) {
        final Predicate<Reading> notNull = x -> x.getMeter().getApartment() != null;
        final Predicate<Reading> thisApartment = x -> x.getMeter().getApartment().equals(ap);
        final Predicate<Reading> notCWU = x -> !x.getMeter().isCwu();
        return readings.stream()
                .filter(notNull)
                .filter(thisApartment)
                .filter(notCWU)
                .mapToDouble(x -> x.getValue())
                .sum();
    }

    protected List<MediaUsage> calculateUsage() {
        final List<MediaUsage> result = new ArrayList<>();
        for (final Apartment ap : apartments) {
            final MediaUsage value = countUsageForApartment(ap);
            result.add(value);
        }
        return result;
    }

    protected double getSumForMainMeter(List<Reading> readings) {
        return readings.stream().filter(x -> x.getMeter().getApartment() == null).filter(x->!x.getMeter().isCwu()).mapToDouble(x -> x.getValue()).sum();
    }

    protected double getMainMeterUsage() {
        final double oldMainMeterUsage = getSumForMainMeter(oldReadins);
        final double newMainMeterUsage = getSumForMainMeter(newReadings);
        return newMainMeterUsage - oldMainMeterUsage;
    }
}
