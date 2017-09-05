package kamienica.feature.payment.calculator;

import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.MediaUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Counts usage in most standard way.
 * The difference between main meter and the sum of submeters will be divided evenly among the tenants.
 */
@Service(value = StandardUsageCalculator.TYPE)
@Transactional
public class StandardUsageCalculator extends ConsumptionCalculator {

    static final String TYPE = "STANDARD";

    @Autowired
    public StandardUsageCalculator(IReadingService readingService, IReadingDao readingDao) {
        super(readingService, readingDao);
    }

    protected void recalculateSharedPartConsuption(final List<MediaUsage> result) {
        final double mainMeterUsage = getMainMeterUsage();
        final double calculatedResult = result.stream().mapToDouble(x -> x.getUsage()).sum();
        if (mainMeterUsage > calculatedResult) {
            final MediaUsage sharedPart = result.stream().filter(x -> x.getApartment().getApartmentNumber() == 0).findFirst().get();
            final double currentUsage = sharedPart.getUsage();
            sharedPart.setUsage(currentUsage + (mainMeterUsage - calculatedResult));
        }
    }
}
