package kamienica.core;

import kamienica.core.calculator.IConsumptionCalculator;
import kamienica.core.calculator.StandardUsageCalculator;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import kamienica.testutils.EntityProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StandardUsageCalculator.class)
public class StandardMediaCalculatorsTest {


    @Autowired
    private IConsumptionCalculator consumptionCalc;

    @Test
    public void standartCalculatorForEnery() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW);
        List<MediaUsage> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertEquals(5, result.get(0).getUsage(), 0);
        assertEquals(15, result.get(1).getUsage(), 0);
        assertEquals(5, result.get(2).getUsage(), 0);
        assertEquals(35, result.get(3).getUsage(), 0);
    }


    @Test
    public void testStandartCalculatorForWater() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.WATER_NEW);
        List<MediaUsage> result = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);

        assertEquals(2, result.get(0).getUsage(), 0);
        assertEquals(2, result.get(1).getUsage(), 0);
        assertEquals(10, result.get(2).getUsage(), 0);
        assertEquals(6, result.get(3).getUsage(), 0);
    }


    @Test(expected = UsageCalculationException.class)
    public void shouldThrowExceptionForListWithDifferentReadingTypes() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.WATER_OLD);
        readings.addAll(EntityProvider.GAS_NEW);
        consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }

    @Test(expected = NegativeConsumptionValue.class)
    public void shouldThrowExceptionForNegativeUsageResult() throws NegativeConsumptionValue, UsageCalculationException {
        List<Reading> readings = new ArrayList<>();
        readings.addAll(EntityProvider.ENERGY_OLD);
        readings.addAll(EntityProvider.ENERGY_NEW_MINUS);
        consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
    }
}

