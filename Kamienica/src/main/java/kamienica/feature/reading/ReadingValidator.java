package kamienica.feature.reading;

import java.util.List;

public class ReadingValidator {

	public static boolean validateMeterReadings(List<? extends ReadingAbstract> currentReadings,
			List<? extends ReadingAbstract> newReadings) {
//		double primaryReading = 0;
//		double sumOfReadings = 0;
		/*
		 * checking if new reading's value is smaller than the one stored id db
		 */
		for (int i = 0; i < newReadings.size(); i++) {
			if (newReadings.get(i).getValue() < currentReadings.get(i).getValue()) {
				return true;
			}
//			if (newReadings.get(i).getMeter().getApartment() == null) {
//				primaryReading += newReadings.get(i).getValue();
//			} else {
//				sumOfReadings += newReadings.get(i).getValue();
//			}
		}
		/*
		 * Checking discrepancies between main meter's reading and sum of the rest (should be close match)
		 * disabled due to no-shared-part problem solution
		 */
//		System.out.println("-----------walidacja----------------");
//		System.out.println(primaryReading);
//		System.out.println(sumOfReadings);
//		System.out.println(sumOfReadings / primaryReading);
//		if (Math.abs((sumOfReadings - sumOfReadings)) / primaryReading > 0.03) {
//			return true;
//		}
		return false;

	}
}
