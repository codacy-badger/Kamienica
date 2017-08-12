package kamienica.feature.payment.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.apache.commons.lang.NotImplementedException;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class CentralGasWaterHeatingCalulator implements IConsumptionCalculator {

    private LocalDate latestDate;
    private LocalDate previousDate;
    private Predicate<Reading> noNullApartment = s -> s.getMeter().getApartment() != null;
    private Predicate<Reading> maxDate = s -> s.getReadingDetails().getReadingDate().equals(latestDate);
    private Predicate<Reading> minDate = s -> s.getReadingDetails().getReadingDate().equals(previousDate);
    private double totalUsageCounted = 0;



    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
       throw new NotImplementedException();
    }
}
