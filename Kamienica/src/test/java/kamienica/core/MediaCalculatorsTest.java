package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import kamienica.core.exception.IncompatibleReadingType;
import kamienica.core.util.GasConsumptionCalculator;
import org.junit.Test;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.util.StandardUsageCalculator;
import kamienica.core.util.EnergyConsumptionCalculator;
import kamienica.core.util.WaterConsumptionCalculator;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediaCalculatorsTest {


    private final StandardUsageCalculator consumptionCalc = new StandardUsageCalculator();
    private final WaterConsumptionCalculator waterCalc = new WaterConsumptionCalculator();
    private final EnergyConsumptionCalculator energyCalc = new EnergyConsumptionCalculator();


    @Test
    public void testStandartCalculatorForEnery() throws NegativeConsumptionValue, IncompatibleReadingType {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW);
        List<UsageValue> result2 = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertTrue(result2.get(0).getUsage() == 5);
        assertTrue(result2.get(1).getUsage() == 15);
        assertTrue(result2.get(2).getUsage() == 5);
        assertTrue(result2.get(3).getUsage() == 35);
    }

    @Test
    public void testStandartCalculatorForWater() throws NegativeConsumptionValue, IncompatibleReadingType {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.WATER_NEW);
        List<UsageValue> result2 = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertEquals(2, result2.get(0).getUsage(),0);
        assertEquals(2, result2.get(1).getUsage(),0);
        assertEquals(10, result2.get(2).getUsage(),0);
        assertEquals(6, result2.get(3).getUsage(),0);
    }

    @Test
    public void test() throws NegativeConsumptionValue {
        List<UsageValue> result1 = WaterConsumptionCalculator.countConsumption(EntityProvider.APARTMENTS, EntityProvider.WATER_OLD, EntityProvider.WATER_NEW);
        // List<UsageValue> result2 = consumptionCalc.countConsumption(EntityProvider.APARTMENTS, EntityProvider.WATER_OLD, EntityProvider.WATER_NEW);

        for (UsageValue usageValue : result1) {
            System.out.println(usageValue);
        }
        System.out.println("-----------------------\n");

        //assertTrue(result1.equals(result2));
    }

    @Test(expected = IncompatibleReadingType.class)
    public void shouldThrowExceptionForListWithDifferentReadingTypes() throws NegativeConsumptionValue, IncompatibleReadingType {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.GAS_NEW);
        List<UsageValue> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }

    @Test(expected = NegativeConsumptionValue.class)
    public void shouldThrowExceptionForMinusUsageResult() throws NegativeConsumptionValue, IncompatibleReadingType {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW_MINUS);
        List<UsageValue> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }
}

