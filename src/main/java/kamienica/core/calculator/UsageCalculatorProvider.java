package kamienica.core.calculator;

import kamienica.model.enums.WaterHeatingSystem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by maciej on 02/08/17.
 */
public class UsageCalculatorProvider {

    public static IConsumptionCalculator provideCalculator(final WaterHeatingSystem system) {
        switch (system) {
            case SHARED_ELECTRIC:
                throw new NotImplementedException();
            case INDIVIDUAL:
                return new StandardUsageCalculator();
            case SHARED_GAS:
                return new CentralGasWaterHeatingCalulator();
            default:
                return new StandardUsageCalculator();
        }

    }
}
