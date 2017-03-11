package kamienica.core.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Reading;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class GasConsumptionCalculator {

	private static double sumUsageForApartment(List<Reading> gasOld, List<Reading> gasNew, Apartment m) {
		double usageSum = 0;
		for (int i = 0; i < gasNew.size(); i++) {
			if (!gasNew.get(i).getMeter().isCwu()) {

				if (gasNew.get(i).getMeter().getApartment() != null) {
					if (gasNew.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
						usageSum = usageSum + gasNew.get(i).getValue();
					}
				}
				if (!gasOld.isEmpty()) {
					if (!gasOld.get(i).getMeter().isCwu()) {
						if (gasOld.get(i).getMeter().getApartment() != null) {
							if (gasOld.get(i).getMeter().getApartment().getApartmentNumber() == m
									.getApartmentNumber()) {
								usageSum = usageSum - gasOld.get(i).getValue();
							}
						}
					}
				}

			}
		}
		return usageSum;
	}

	/**
	 * This method is designed for shared water heatig system
	 */
	public static List<MediaUsage> countConsumption(List<Apartment> aparment, List<Reading> gasOld,
													List<Reading> gasNew, List<Reading> waterOld, List<Reading> waterNew) {
		List<MediaUsage> mediaUsageList = new ArrayList<>();
		for (Apartment m : aparment) {

			MediaUsage mediaUsage = new MediaUsage();
			mediaUsage.setDescription("Zuzycie calkowite za: " + m.getDescription());
			mediaUsage.setApartment(m);

			double usage = sumUsageForApartment(gasOld, gasNew, m);
			mediaUsage.setUsage(usage);
			if (!gasOld.isEmpty()) {
				mediaUsage.setDaysBetweenReadings(
						Days.daysBetween(gasOld.get(0).getReadingDetails().getReadingDate(), gasNew.get(0).getReadingDetails().getReadingDate()).getDays());
			} else {
				mediaUsage.setDaysBetweenReadings(0);
			}
			mediaUsageList.add(mediaUsage);
		}

		double hotWaterUsage = GasConsumptionCalculator.sumCWU(gasOld, gasNew);
		if (hotWaterUsage != 0) {

			double sumaZuzyciaCieplejWody = WaterConsumptionCalculator.countWarmWaterUsage(waterOld, waterNew);
			HashMap<Integer, Double> mapaZuzyciaCieplejWody = GasConsumptionCalculator.hotWaterUsageMap(waterOld, waterNew);
			for (MediaUsage anOut : mediaUsageList) {
				if (anOut.getApartment().getApartmentNumber() != 0) {
					int nrMieszkania = anOut.getApartment().getApartmentNumber();
					if (mapaZuzyciaCieplejWody.containsKey(nrMieszkania)) {
						double zuzycieGazuCwuDlaDanegoMieszkania = (mapaZuzyciaCieplejWody.get(nrMieszkania)
								/ sumaZuzyciaCieplejWody) * hotWaterUsage;
						double tmp = anOut.getUsage() + zuzycieGazuCwuDlaDanegoMieszkania;
						anOut.setUsage(CommonUtils.decimalFormat(tmp));
					}
				}
			}
		}
		return mediaUsageList;

	}

	/** Method for individual water heating system */
	public static ArrayList<MediaUsage> countConsumption(List<Apartment> aparment, List<Reading> gasOld,
														 List<Reading> gasNew) {
		ArrayList<MediaUsage> out = new ArrayList<>();
		for (Apartment m : aparment) {

			MediaUsage tmp = new MediaUsage();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			double sumPrevious = 0;
			double sumNew = 0;
			tmp.setApartment(m);
			for (int i = 0; i < gasNew.size(); i++) {
				if (!gasNew.get(i).getMeter().isCwu()) {

					if (gasNew.get(i).getMeter().getApartment() != null) {
						if (gasNew.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
							sumNew = sumNew + gasNew.get(i).getValue();
						}
					}
					if (!gasOld.isEmpty()) {
						if (!gasOld.get(i).getMeter().isCwu()) {
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
//			tmp.setUnit(gasNew.get(0).getUnit());
			if (!gasOld.isEmpty()) {
				tmp.setDaysBetweenReadings(
						Days.daysBetween(gasOld.get(0).getReadingDetails().getReadingDate(), gasNew.get(0).getReadingDetails().getReadingDate()).getDays());
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
	private static HashMap<Integer, Double> hotWaterUsageMap(List<Reading> oldReading,
			List<Reading> newReading) {
		HashMap<Integer, Double> output = new HashMap<>();
		for (Reading o : newReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().isWarmWater()) {
					output.put(o.getMeter().getApartment().getApartmentNumber(), o.getValue());
				}
			}
		}
		for (Reading o : oldReading) {
			if (o.getMeter().getApartment() != null && o.getMeter().getApartment().getApartmentNumber() != 0) {
				if (o.getMeter().isWarmWater()) {
					double consumption = output.get(o.getMeter().getApartment().getApartmentNumber());
					consumption = consumption - o.getValue();
					output.put(o.getMeter().getApartment().getApartmentNumber(), consumption);
				}
			}
		}
		return output;
	}

	private static double sumCWU(List<Reading> gasOld, List<Reading> gasNew) {
		double out = 0;
		for (Reading w : gasNew) {
			if (w.getMeter().isCwu())
				out += w.getValue();
		}

		for (Reading w : gasOld) {
			if (w.getMeter().isCwu())
				out -= w.getValue();
		}
		return out;
	}

}
