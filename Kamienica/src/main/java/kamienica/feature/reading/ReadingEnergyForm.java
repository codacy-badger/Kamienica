package kamienica.feature.reading;

import java.util.LinkedList;
import java.util.List;

public class ReadingEnergyForm {

	private List<ReadingEnergy> currentReadings = new LinkedList<>();
	private List<ReadingEnergy> previousReadings = new LinkedList<>();
	private List<ReadingEnergy> newReadings = new LinkedList<>();

	public String toString() {
		return "ReadingEnergyForm [currentReadings=" + currentReadings + ", previousReadings=" + previousReadings
				+ ", newReadings=" + newReadings + "]";
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

	public List<ReadingEnergy> getNewReadings() {
		return newReadings;
	}

	public void setNewReadings(List<ReadingEnergy> newReadings) {
		this.newReadings = newReadings;
	}
}
