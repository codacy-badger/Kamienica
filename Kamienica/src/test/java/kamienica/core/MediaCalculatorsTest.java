package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import kamienica.testutils.EntityProvider;
import kamienica.core.exception.UsageCalculationException;
import org.junit.Test;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.calculator.StandardUsageCalculator;
import kamienica.core.calculator.EnergyConsumptionCalculator;
import kamienica.core.calculator.WaterConsumptionCalculator;
import kamienica.feature.reading.Reading;
import kamienica.model.MediaUsage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediaCalculatorsTest {


    private final StandardUsageCalculator consumptionCalc = new StandardUsageCalculator();
    private final WaterConsumptionCalculator waterCalc = new WaterConsumptionCalculator();
    private final EnergyConsumptionCalculator energyCalc = new EnergyConsumptionCalculator();


    @Test
    public void testStandartCalculatorForEnery() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW);
        List<MediaUsage> result2 = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertTrue(result2.get(0).getUsage() == 5);
        assertTrue(result2.get(1).getUsage() == 15);
        assertTrue(result2.get(2).getUsage() == 5);
        assertTrue(result2.get(3).getUsage() == 35);
    }

    @Test
    public void testStandartCalculatorForWater() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.WATER_NEW);
        List<MediaUsage> result2 = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertEquals(2, result2.get(0).getUsage(),0);
        assertEquals(2, result2.get(1).getUsage(),0);
        assertEquals(10, result2.get(2).getUsage(),0);
        assertEquals(6, result2.get(3).getUsage(),0);
    }

    @Test
    public void test() throws NegativeConsumptionValue {
        List<MediaUsage> result1 = WaterConsumptionCalculator.countConsumption(EntityProvider.APARTMENTS, EntityProvider.WATER_OLD, EntityProvider.WATER_NEW);
        // List<MediaUsage> result2 = consumptionCalc.countConsumption(EntityProvider.APARTMENTS, EntityProvider.WATER_OLD, EntityProvider.WATER_NEW);

        for (MediaUsage mediaUsage : result1) {
            System.out.println(mediaUsage);
        }
        System.out.println("-----------------------\n");

        //assertTrue(result1.equals(result2));
    }

    @Test(expected = UsageCalculationException.class)
    public void shouldThrowExceptionForListWithDifferentReadingTypes() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.GAS_NEW);
        List<MediaUsage> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }

    @Test(expected = NegativeConsumptionValue.class)
    public void shouldThrowExceptionForMinusUsageResult() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW_MINUS);
        List<MediaUsage> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }
}

