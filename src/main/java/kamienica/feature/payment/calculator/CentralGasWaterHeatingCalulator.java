package kamienica.feature.payment.calculator;

import kamienica.feature.reading.IReadingDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.apache.commons.lang.NotImplementedException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;


@Service(value = CentralGasWaterHeatingCalulator.TYPE)
@Transactional
public class CentralGasWaterHeatingCalulator implements IConsumptionCalculator {

    static final String TYPE = "CENTRAL_GAS";
    private final IReadingDao readingDao;

    @Autowired
    public CentralGasWaterHeatingCalulator(IReadingDao readingDao) {
        this.readingDao = readingDao;
    }

    @Override
    public List<MediaUsage> calculateConsumption(final List<Apartment> apartments, final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
        throw new NotImplementedException();
    }

    @Override
    public List<MediaUsage> calculateConsumption(Invoice invoice) {
        return null;
    }
}
