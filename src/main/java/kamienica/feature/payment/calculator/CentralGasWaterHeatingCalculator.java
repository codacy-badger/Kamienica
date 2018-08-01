package kamienica.feature.payment.calculator;

import java.util.List;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO investigate whether this implementation could be used for electric central heating system
@Service(value = CentralGasWaterHeatingCalculator.TYPE)
@Transactional
public class CentralGasWaterHeatingCalculator extends ConsumptionCalculator {

    static final String TYPE = "CENTRAL_GAS";

    @Autowired
    public CentralGasWaterHeatingCalculator(IReadingService readingService) {
        super(readingService);
    }

  protected void recalculateSharedPartConsumption(final List<MediaUsage> result) {
    final double mainMeterUsage = getMainMeterUsage();
    calculateForCWU(result);

    final double calculatedResult = result.stream().mapToDouble(x -> x.getUsage()).sum();

    if (mainMeterUsage > calculatedResult) {
      result.stream()
          .filter(x -> x.getApartment().getApartmentNumber() == 0)
          .findFirst()
          .ifPresent(
              x -> {
                final double currentUsage = x.getUsage();
                x.setUsage(currentUsage + (mainMeterUsage - calculatedResult));
              }
          );

    }
  }

    private void calculateForCWU(List<MediaUsage> result) {
        final Residence r = invoice.getResidence();
        final Media m = Media.WATER;
        //TODO add where clause to fetch readings only for hot water
        List<Reading> newWarmWater = readingService.getPreviousReadingForWarmWater(r, m, invoice.getReadingDetails().getReadingDate());
        List<Reading> oldWarmWater = readingService.getPreviousReadingForWarmWater(r, m, newWarmWater.get(0).getReadingDetails().getReadingDate());

        final double oldCwuState = getSumForCWU(oldReadins);
        final double newCWUState = getSumForCWU(newReadings);

        final double CWUUsage = newCWUState - oldCwuState;
        final double totalWarmWaterUsage = sumUsage(oldWarmWater, newWarmWater);

        for (Apartment a : apartments) {
            final double sumOldForAp = getWarmWaterSumForApartment(oldWarmWater, a);
            final double sumNewForAp = getWarmWaterSumForApartment(newWarmWater, a);
            final double usageForAparment = sumNewForAp - sumOldForAp;
            final double cwuUsageForApartment = CWUUsage * (usageForAparment / totalWarmWaterUsage);
            result.stream().filter(x -> x.getApartment().equals(a)).forEach(x -> x.setUsage(x.getUsage() + cwuUsageForApartment));
        }
    }

    private double getWarmWaterSumForApartment(List<Reading> readings, Apartment a) {
        return readings.parallelStream()
                .filter(x -> x.getMeter().getApartment() != null)
                .filter(x -> x.getMeter().getApartment().equals(a))
                .filter(x -> x.getMeter().isWarmWater())
                .mapToDouble(Reading::getValue)
                .sum();
    }

    private double sumUsage(List<Reading> oldWarmWater, List<Reading> newWarmWater) {
        final double oldSum = oldWarmWater.parallelStream().filter(x -> x.getMeter().isWarmWater()).mapToDouble(Reading::getValue).sum();
        final double newSUm = newWarmWater.parallelStream().filter(x -> x.getMeter().isWarmWater()).mapToDouble(Reading::getValue).sum();
        return newSUm - oldSum;
    }

    private double getSumForCWU(final List<Reading> readings) {
        return readings.stream().filter(x -> x.getMeter().isCwu()).mapToDouble(Reading::getValue).sum();
    }

}