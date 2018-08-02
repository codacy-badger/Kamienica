package kamienica.feature.payment.calculator;

import java.util.List;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service(value = CentralEnergyWaterHeatingCalculator.TYPE)
@Transactional
public class CentralEnergyWaterHeatingCalculator extends ConsumptionCalculator {

  static final String TYPE = "CENTRAL_ENERGY";

  @Autowired
  public CentralEnergyWaterHeatingCalculator(IReadingService readingService) {
    super(readingService);
  }

  @Override
  public List<MediaUsage> calculateConsumption(Invoice invoice, List<Apartment> apartments) {
    throw new NotImplementedException();
  }

  @Override
  protected void recalculateSharedPartConsumption(List<MediaUsage> result) {
    throw new NotImplementedException();
  }
}
