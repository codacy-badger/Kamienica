//package kamienica.junit;
//
//import static org.junit.Assert.assertArrayEquals;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//
//import kamienica.core.ManagerGas;
//import kamienica.model.Apartment;
//import kamienica.model.MeterGas;
//import kamienica.model.MeterWater;
//import kamienica.model.ReadingGas;
//import kamienica.model.ReadingWater;
//import kamienica.model.UsageValue;
//
//public class ManagerGasTest {
//
//	@Test
//	public final void test() {
//		ArrayList<Apartment> apartments = new ArrayList<>();
//		apartments.add(new Apartment(1, 0, "0000", "Czesc Wspolna"));
//		apartments.add(new Apartment(2, 1, "1111", "Piwnica"));
//		apartments.add(new Apartment(3, 2, "2222", "Parter"));
//		
//
//		ArrayList<MeterGas> meterGas = new ArrayList<>();
//	
//		meterGas.add(new MeterGas("cwu", "ap2", "t2", apartments.get(0), true));
//		meterGas.add(new MeterGas("gas1", "ap2", "t2", apartments.get(1), false));
//		meterGas.add(new MeterGas("gas2", "ap2", "t2", apartments.get(2), false));
//
//		ArrayList<ReadingGas> gasOld = new ArrayList<>();
//		ArrayList<ReadingGas> gasNew = new ArrayList<>();
//		
//		gasOld.add(new ReadingGas(null, 0, meterGas.get(0)));
//		gasOld.add(new ReadingGas(null, 0, meterGas.get(1)));
//		gasOld.add(new ReadingGas(null, 0, meterGas.get(2)));
//	
//		gasNew.add(new ReadingGas(null, 3, meterGas.get(0)));
//		gasNew.add(new ReadingGas(null, 3, meterGas.get(1)));
//		gasNew.add(new ReadingGas(null, 1, meterGas.get(2)));
//
//		
//		ArrayList<MeterWater> meterWater = new ArrayList<>();
//		meterWater.add(new MeterWater("1hot", "ap2", "t2", apartments.get(1), true));
//		meterWater.add(new MeterWater("1cold", "ap2", "t2", apartments.get(1), false));
//		meterWater.add(new MeterWater("2hot", "ap2", "t2", apartments.get(2), true));
//		meterWater.add(new MeterWater("2cold", "ap2", "t2", apartments.get(2), false));
//		
//		ArrayList<ReadingWater> waterOld = new ArrayList<>();
//		ArrayList<ReadingWater> waterNew = new ArrayList<>();
//		
//		waterOld.add(new ReadingWater(null, 0, meterWater.get(0)));
//		waterOld.add(new ReadingWater(null, 0, meterWater.get(1)));
//		waterOld.add(new ReadingWater(null, 0, meterWater.get(2)));
//		waterOld.add(new ReadingWater(null, 0, meterWater.get(3)));
//	
//		waterNew.add(new ReadingWater(null, 2, meterWater.get(0)));
//		waterNew.add(new ReadingWater(null, 3, meterWater.get(1)));
//		waterNew.add(new ReadingWater(null, 1, meterWater.get(2)));
//		waterOld.add(new ReadingWater(null, 1, meterWater.get(3)));
//		
//		ArrayList<UsageValue> usage = ManagerGas.countGasConsumption(apartments, gasOld, gasNew, waterOld, waterNew);
//		System.out.println(usage.toString());
//		double[] actual = new double[3];
//		actual[0] = usage.get(0).getUsage();
//		actual[1] = usage.get(1).getUsage();
//		actual[2] = usage.get(2).getUsage();
//		double[] expected = {0,5,2};
//		assertArrayEquals("checking manager energy", expected, actual, 0.0);
//	}
//
//}
