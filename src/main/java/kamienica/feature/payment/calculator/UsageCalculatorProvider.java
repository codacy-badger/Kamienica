package kamienica.feature.payment.calculator;

import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;

public class UsageCalculatorProvider {

    public static IConsumptionCalculator provideCalculator(final WaterHeatingSystem system, final Media calculatedMedia) {

        if (system.equals(WaterHeatingSystem.SHARED_ELECTRIC) && calculatedMedia.equals(Media.ENERGY)) {
            return new CentralEnergyWaterHeatingCalculator();
        } else if (system.equals(WaterHeatingSystem.SHARED_GAS) && calculatedMedia.equals(Media.GAS)) {
            return new CentralGasWaterHeatingCalulator();
        } else {
            return new StandardUsageCalculator();
        }
    }
}
