//package kamienica.junit;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//
//import kamienica.core.ManagerEnergy;
//import kamienica.model.Apartment;
//import kamienica.model.MeterEnergy;
//import kamienica.model.ReadingEnergy;
//import kamienica.model.UsageValue;
//
//public class ManagerEnergyTest {
//
//	@Test
//	public final void testCountEnergyConsupmtion() {
//		ArrayList<Apartment> apartments = new ArrayList<>();
//		apartments.add(new Apartment(0, 0, "1234", "test"));
//		apartments.add(new Apartment(0, 1, "1234", "test"));
//		ArrayList<MeterEnergy> meters = new ArrayList<>();
//		meters.add(new MeterEnergy("test1", "ap1", "t1", apartments.get(0)));
//		meters.add(new MeterEnergy("test2", "ap2", "t2", apartments.get(1)));
//		ArrayList<ReadingEnergy> readingsOld = new ArrayList<>();
//		readingsOld.add(new ReadingEnergy(null, 0, meters.get(0)));
//		readingsOld.add(new ReadingEnergy(null, 21, meters.get(1)));
//		ArrayList<ReadingEnergy> readingsNew = new ArrayList<>();
//		readingsNew.add(new ReadingEnergy(null, 12, meters.get(0)));
//		readingsNew.add(new ReadingEnergy(null, 24, meters.get(1)));
//		ArrayList<UsageValue> usage = ManagerEnergy.countEnergyConsupmtion(apartments, readingsOld, readingsNew);
//		double[] actual = new double[2];
//		actual[0] = usage.get(0).getUsage();
//		actual[1] = usage.get(1).getUsage();
//		
//		double[] expected = {12, 3};
//		assertArrayEquals("checking manager energy", expected, actual, 0.0);
//		
//	}
//
//}
