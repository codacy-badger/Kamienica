package kamienica.junit;

import java.util.ArrayList;

import kamienica.model.Apartment;
import kamienica.model.MeterEnergy;
import kamienica.model.ReadingEnergy;

public class TestRunner {

	public static void main(String[] args) {
		
		ArrayList<Apartment> apartments = new ArrayList<>();
		apartments.add(new Apartment(0, 0, "1234", "test"));
		apartments.add(new Apartment(0, 1, "1234", "test"));
		ArrayList<MeterEnergy> meters = new ArrayList<>();
		meters.add(new MeterEnergy("test1", "ap1", "t1", apartments.get(0)));
		meters.add(new MeterEnergy("test2", "ap2", "t2", apartments.get(1)));
		ArrayList<ReadingEnergy> readingsOld = new ArrayList<>();
		readingsOld.add(new ReadingEnergy(null, 0, meters.get(0)));
		readingsOld.add(new ReadingEnergy(null, 21, meters.get(1)));
		ArrayList<ReadingEnergy> readingsNew = new ArrayList<>();
		readingsNew.add(new ReadingEnergy(null, 12, meters.get(0)));
		readingsNew.add(new ReadingEnergy(null, 24, meters.get(1)));

	}

}
