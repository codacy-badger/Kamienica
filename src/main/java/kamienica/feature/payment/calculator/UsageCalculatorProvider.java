package kamienica.feature.payment.calculator;

import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;

class UsageCalculatorProvider {

    static String provideCalculator(final WaterHeatingSystem system, final Media calculatedMedia) {

        if (system.equals(WaterHeatingSystem.SHARED_ELECTRIC) && calculatedMedia.equals(Media.ENERGY)) {
            return CentralEnergyWaterHeatingCalculator.TYPE;
        } else if (system.equals(WaterHeatingSystem.SHARED_GAS) && calculatedMedia.equals(Media.GAS)) {
            return CentralGasWaterHeatingCalculator.TYPE;
        } else {
            return StandardUsageCalculator.TYPE;
        }
    }
}
