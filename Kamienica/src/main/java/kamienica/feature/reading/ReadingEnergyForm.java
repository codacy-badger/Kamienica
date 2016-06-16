package kamienica.feature.reading;

import java.util.ArrayList;
import java.util.List;

public class ReadingEnergyForm {

	private List<ReadingEnergy> currentReadings = new ArrayList<>();
	private List<ReadingEnergy> previousReadings = new ArrayList<>();

	@Override
	public String toString() {
		return currentReadings.toString();
	}

	public ReadingEnergyForm() {

	}

	public ReadingEnergyForm(List<ReadingEnergy> odczytyEnergii) {
		this.currentReadings = odczytyEnergii;
	}

	public List<ReadingEnergy> getPreviousReadings() {
		return previousReadings;
	}

	public void setPreviousReadings(List<ReadingEnergy> previousReadings) {
		this.previousReadings = previousReadings;
	}

	public List<ReadingEnergy> getCurrentReadings() {
		return currentReadings;
	}

	public void setCurrentReadings(List<ReadingEnergy> currentReadings) {
		this.currentReadings = currentReadings;
	}
}
