package kamienica.feature.reading;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDate;

public class ReadingEnergyForm extends ReadingForm<ReadingEnergy> {

	private List<ReadingEnergy> currentReadings = new LinkedList<>();
	private List<ReadingEnergy> previousReadings = new LinkedList<>();
	private List<ReadingEnergy> newReadings = new LinkedList<>();

	@Override
	public String toString() {
		return "currentReadings=\n" + currentReadings + "\n previousReadings\n" + previousReadings + "\n newReadings\n"
				+ newReadings;
	}

	public ReadingEnergyForm() {

	}

	public ReadingEnergyForm(LocalDate oldDate, LocalDate newDate, List<ReadingEnergy> currentReadings,
			List<ReadingEnergy> previousReadings, List<ReadingEnergy> newReadings) {
		super(oldDate, newDate);
		this.currentReadings = currentReadings;
		this.previousReadings = previousReadings;
		this.newReadings = newReadings;
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
