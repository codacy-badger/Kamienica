package kamienica.feature.reading;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class ReadingWaterForm {

	private List<ReadingWater> currentReadings = new ArrayList<>();
	private List<ReadingWater> previousReadings = new ArrayList<>();

	public List<ReadingWater> getCurrentReadings() {
		return currentReadings;
	}

	public ReadingWaterForm() {

	}

	public void setCurrentReadings(List<ReadingWater> currentReadings) {
		this.currentReadings = currentReadings;
	}

	public List<ReadingWater> getPreviousReadings() {
		return previousReadings;
	}

	public void setPreviousReadings(List<ReadingWater> previousReadings) {
		this.previousReadings = previousReadings;
	}

	@Override
	public String toString() {
		return "OdczytWodaFormularz [odczytyWody=" + currentReadings + ", poprzednieOdczytyWody=" + previousReadings
				+ "]";
	}

	public LocalDate getDate() {
		return currentReadings.get(0).getReadingDate();
	}

	public LocalDate getPreviousDate() {
		if (previousReadings.isEmpty()) {
			return LocalDate.parse("2010-01-01");
		} else {
			return previousReadings.get(0).getReadingDate();
		}
	}
}
