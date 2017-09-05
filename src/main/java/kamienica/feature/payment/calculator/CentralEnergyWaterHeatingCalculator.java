package kamienica.feature.payment.calculator;

import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
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
public class CentralEnergyWaterHeatingCalculator extends ConsumptionCalculator {

    static final String TYPE= "CENTRAL_ENERGY";

    @Autowired
    public CentralEnergyWaterHeatingCalculator(IReadingService readingService, IReadingDao readingDao){
        super(readingService, readingDao);
    }

    @Override
    public List<MediaUsage> calculateConsumption(Invoice invoice,  List<Apartment> apartments) {
        throw new NotImplementedException();
    }

    @Override
    protected void recalculateSharedPartConsuption(List<MediaUsage> result) {

    }
}
