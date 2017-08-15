package kamienica.feature.payment.calculator;

import kamienica.feature.reading.IReadingDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service(value = CentralEnergyWaterHeatingCalculator.TYPE)
@Transactional
public class CentralEnergyWaterHeatingCalculator implements IConsumptionCalculator {

    static final String TYPE= "CENTRAL_ENERGY";
    private final IReadingDao readingDao;

    @Autowired
    public CentralEnergyWaterHeatingCalculator(IReadingDao readingDao) {
        this.readingDao = readingDao;
    }

    @Override
    public List<MediaUsage> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
       throw new NotImplementedException();
    }
}
