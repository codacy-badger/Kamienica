package kamienica.core.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kamienica.core.util.CommonUtils;
import kamienica.model.MediaUsage;
import org.joda.time.Days;

import kamienica.model.Apartment;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;

@Deprecated
public class GasConsumptionCalculator {

	/**
	 * This method is designed for shared water heatig system
	 */
	public static List<MediaUsage> countConsumption(List<Apartment> aparment, List<ReadingGas> gasOld,
													List<ReadingGas> gasNew, List<ReadingWater> waterOld, List<ReadingWater> waterNew) {
		ArrayList<MediaUsage> out = new ArrayList<>();
		for (Apartment m : aparment) {

			MediaUsage tmp = new MediaUsage();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			double sumPrevious = 0;
			double sumNew = 0;
			tmp.setApartment(m);
			for (int i = 0; i < gasNew.size(); i++) {
				if (!gasNew.get(i).getIsCWU()) {

					if (gasNew.get(i).getMeter().getApartment() != null) {
						if (gasNew.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
							sumNew = sumNew + gasNew.get(i).getValue();
						}
					}
					if (!gasOld.isEmpty()) {
						if (!gasOld.get(i).getIsCWU()) {
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
				tmp.setDaysBetweenReadings(
						Days.daysBetween(gasOld.get(0).getReadingDate(), gasNew.get(0).getReadingDate()).getDays());
				// tmp.setDaysBetweenReadings(Days.daysBetween(new
				// DateTime(gasOld.get(0).getReadingDate()),
				// new DateTime(gasNew.get(0).getReadingDate())).getDays());
			} else {
				tmp.setDaysBetweenReadings(0);
			}
			out.add(tmp);
		}

		double zuzycieCWU = GasConsumptionCalculator.sumCWU(gasOld, gasNew);
		if (zuzycieCWU != 0) {

			double sumaZuzyciaCieplejWody = WaterConsumptionCalculator.countWarmWaterUsage(waterOld, waterNew);
			HashMap<Integer, Double> mapaZuzyciaCieplejWody = GasConsumptionCalculator.hotWaterUsageMap(waterOld, waterNew);
			for (int i = 0; i < out.size(); i++) {
				if (out.get(i).getApartment().getApartmentNumber() != 0) {
					int nrMieszkania = out.get(i).getApartment().getApartmentNumber();
					if (mapaZuzyciaCieplejWody.containsKey(nrMieszkania)) {
						double zuzycieGazuCwuDlaDanegoMieszkania = (mapaZuzyciaCieplejWody.get(nrMieszkania)
								/ sumaZuzyciaCieplejWody) * zuzycieCWU;
						double tmp = out.get(i).getUsage() + zuzycieGazuCwuDlaDanegoMieszkania;
						out.get(i).setUsage(CommonUtils.decimalFormat(tmp));
					}
				}
			}
		}
		return out;

	}

	/** Method for individual water heating system */
	public static ArrayList<MediaUsage> countConsumption(List<Apartment> aparment, List<ReadingGas> gasOld,
														 List<ReadingGas> gasNew) {
		ArrayList<MediaUsage> out = new ArrayList<>();
		for (Apartment m : aparment) {

			MediaUsage tmp = new MediaUsage();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			double sumPrevious = 0;
			double sumNew = 0;
			tmp.setApartment(m);
			for (int i = 0; i < gasNew.size(); i++) {
				if (!gasNew.get(i).getIsCWU()) {

					if (gasNew.get(i).getMeter().getApartment() != null) {
						if (gasNew.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
							sumNew = sumNew + gasNew.get(i).getValue();
						}
					}
					if (!gasOld.isEmpty()) {
						if (!gasOld.get(i).getIsCWU()) {
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
				tmp.setDaysBetweenReadings(
						Days.daysBetween(gasOld.get(0).getReadingDate(), gasNew.get(0).getReadingDate()).getDays());
				// tmp.setDaysBetweenReadings(Days.daysBetween(new
				// DateTime(gasOld.get(0).getReadingDate()),
				// new DateTime(gasNew.get(0).getReadingDate())).getDays());
			} else {
				tmp.setDaysBetweenReadings(0);
			}
			out.add(tmp);
		}

		return out;

	}

	/**
	 * Counts warm water usage for each apartment
	 * 
	 */
	private static HashMap<Integer, Double> hotWaterUsageMap(List<ReadingWater> oldReading,
			List<ReadingWater> newReading) {
		HashMap<Integer, Double> output = new HashMap<>();
		for (ReadingWater o : newReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().getIsWarmWater()) {
					output.put(o.getMeter().getApartment().getApartmentNumber(), o.getValue());
				}
			}
		}
		for (ReadingWater o : oldReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().getIsWarmWater()) {
					double consumption = output.get(o.getMeter().getApartment().getApartmentNumber());
					consumption = consumption - o.getValue();
					output.put(o.getMeter().getApartment().getApartmentNumber(), consumption);
				}
			}
		}
		return output;
	}

	private static double sumCWU(List<ReadingGas> gasOld, List<ReadingGas> gasNew) {
		double out = 0;
		for (ReadingGas w : gasNew) {
			if (w.getIsCWU())
				out += w.getValue();
		}

		for (ReadingGas w : gasOld) {
			if (w.getIsCWU())
				out -= w.getValue();
		}
		return out;
	}

}
