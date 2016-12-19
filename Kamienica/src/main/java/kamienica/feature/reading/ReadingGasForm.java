package kamienica.feature.reading;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ReadingGasForm {

	private List<ReadingGas> currentReadings = new ArrayList<>();
	private List<ReadingGas> previousReadings = new ArrayList<>();

	public ReadingGasForm() {

	}

	public List<ReadingGas> getCurrentReadings() {
		return currentReadings;
	}

	public void setCurrentReadings(List<ReadingGas> odczytyGazu) {
		this.currentReadings = odczytyGazu;
	}

	public List<ReadingGas> getPreviousReadings() {
		return previousReadings;
	}

	public void setPreviousReadings(List<ReadingGas> previousRearing) {
		this.previousReadings = previousRearing;
	}

	@Override
	public String toString() {
		return "OdczytGazFormularz [odczytyGazu=" + currentReadings + ", poprzednieOdczytyGazu=" + previousReadings
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
