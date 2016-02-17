package kamienica.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import kamienica.model.Apartment;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;
import kamienica.model.UsageValue;

public class ManagerGas {

	public static HashMap<Integer, Double> stworzMapeUdzialuZuzyciaCieplejWody(List<ReadingWater> oldReading,
			List<ReadingWater> newReading) {
		HashMap<Integer, Double> output = new HashMap<>();
		for (ReadingWater o : newReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().getIsWarmWater() == true) {
					output.put(o.getMeter().getApartment().getApartmentNumber(), o.getValue());
				}
			}
		}
		for (ReadingWater o : oldReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().getIsWarmWater() == true) {
					double consumption = output.get(o.getMeter().getApartment().getApartmentNumber());
					consumption = consumption - o.getValue();
					output.put(o.getMeter().getApartment().getApartmentNumber(), consumption);
				}
			}
		}
		return output;
	}

	public static double sumCWU(List<ReadingGas> gazStare, List<ReadingGas> gazNowe) {
		double out = 0;
		for (ReadingGas w : gazNowe) {
			if (w.getIsCWU() == true)
				out += w.getValue();
		}

		for (ReadingGas w : gazStare) {
			if (w.getIsCWU() == true)
				out -= w.getValue();
		}
		return out;
	}

	public static ArrayList<UsageValue> countGasConsumption(List<Apartment> aparment, List<ReadingGas> gasOld,
			List<ReadingGas> gasNew, List<ReadingWater> waterOld, List<ReadingWater> waterNew) {
		ArrayList<UsageValue> out = new ArrayList<UsageValue>();
		for (Apartment m : aparment) {

			UsageValue tmp = new UsageValue();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			double sumPrevious = 0;
			double sumNew = 0;
			tmp.setApartment(m);
			for (int i = 0; i < gasNew.size(); i++) {
				if (gasNew.get(i).getIsCWU() == false) {

					if (gasNew.get(i).getMeter().getApartment() != null) {
						if (gasNew.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
							sumNew = sumNew + gasNew.get(i).getValue();
						}
					}
					if (!gasOld.isEmpty()) {
						if (gasOld.get(i).getIsCWU() == false) {
							if (gasOld.get(i).getMeter().getApartment() != null) {
								if (gasOld.get(i).getMeter().getApartment().getApartmentNumber() == m
										.getApartmentNumber()) {
									sumPrevious = sumPrevious + gasOld.get(i).getValue();
								}
							}
						}
					}

				}
			}
			double usage = sumNew - sumPrevious;
			tmp.setUsage(usage);
			tmp.setUnit(gasNew.get(0).getUnit());
			if (!gasOld.isEmpty()) {
				tmp.setDaysBetweenReadings(Days.daysBetween(new DateTime(gasOld.get(0).getReadingDate()),
						new DateTime(gasNew.get(0).getReadingDate())).getDays());
			} else {
				tmp.setDaysBetweenReadings(0);
			}
			out.add(tmp);
		}

		double zuzycieCWU = ManagerGas.sumCWU(gasOld, gasNew);
		if (zuzycieCWU != 0) {
			double sumaZuzyciaCieplejWody = ManagerWater.countWarmWaterUsage(waterOld, waterNew);
			HashMap<Integer, Double> mapaZuzyciaCieplejWody = ManagerGas.stworzMapeUdzialuZuzyciaCieplejWody(waterOld,
					waterNew);
			for (int i = 0; i < out.size(); i++) {
				if (out.get(i).getMieszkanie().getApartmentNumber() != 0) {
					int nrMieszkania = out.get(i).getMieszkanie().getApartmentNumber();
					double zuzycieGazuCwuDlaDanegoMieszkania = (mapaZuzyciaCieplejWody.get(nrMieszkania)
							/ sumaZuzyciaCieplejWody) * zuzycieCWU;
					double tmp = out.get(i).getUsage() + zuzycieGazuCwuDlaDanegoMieszkania;
					out.get(i).setUsage(tmp);
				}
			}
		}
		return out;

	}

}
