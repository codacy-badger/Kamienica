package kamienica.validator;

import java.util.List;

import kamienica.model.ReadingAbstract;

public class ReadingValidator {

	public static boolean validateMeterReadings(List<? extends ReadingAbstract> readings) {
		double primaryReading = 0;
		double sumOfReadings = 0;

		for (int i = 0; i < readings.size(); i++) {
			if (readings.get(i).getMeter().getApartment() == null) {
				primaryReading += readings.get(i).getValue();
			} else {
				sumOfReadings += readings.get(i).getValue();
			}
		}

		if (Math.abs((sumOfReadings - sumOfReadings)) / primaryReading > 0.03) {
			return false;
		} else {
			return true;
		}
	}
}
