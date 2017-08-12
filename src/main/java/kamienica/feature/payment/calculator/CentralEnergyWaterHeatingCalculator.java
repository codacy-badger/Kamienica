package kamienica.feature.payment.calculator;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by maciej on 12/08/17.
 */
public class CentralEnergyWaterHeatingCalculator implements IConsumptionCalculator {
    @Override
    public List<MediaUsage> calculateConsumption(List<Apartment> apartments, List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {
       throw new NotImplementedException();
    }
}
