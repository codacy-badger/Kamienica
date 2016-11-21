package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.util.ConsumptionCalcImpl;
import kamienica.core.util.EnergyConsumptionCalculator;
import kamienica.core.util.WaterConsumptionCalculator;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

public class MediaCalculatorsTest {

	
	private final ConsumptionCalcImpl consumptionCalc = new ConsumptionCalcImpl();
	private final WaterConsumptionCalculator waterCalc = new WaterConsumptionCalculator();
	private final EnergyConsumptionCalculator energyCalc = new EnergyConsumptionCalculator();
	

	
	@Test
	public void test() throws NegativeConsumptionValue {
		List<UsageValue> result1 = EnergyConsumptionCalculator.countConsupmtion(EntityProvider.APARTMENTS, EntityProvider.ENERGY_OLD, EntityProvider.ENERGY_NEW);
	
		List<Reading> readings = new ArrayList<>();
		readings.addAll(EntityProvider.ENERGY_OLD);
		readings.addAll(EntityProvider.ENERGY_NEW);
		List<UsageValue> result2 = consumptionCalc.calculateConsumption(EntityProvider.APARTMENTS, readings);
		
		for (UsageValue usageValue : result1) {
			System.out.println(usageValue);
		}
		System.out.println( "-----------------------\n");
		for (UsageValue usageValue : result2) {
			System.out.println(usageValue);
		}
	}
}
