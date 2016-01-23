package kamienica.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.model.ReadingEnergy;

public class ReadingEnergyForm {

	private List<ReadingEnergy> currentReadings = new ArrayList<>();
	private List<ReadingEnergy> previousReadings = new ArrayList<>();

	public List<ReadingEnergy> getCurrentReadings() {
		return currentReadings;
	}

	public void setCurrentReadings(List<ReadingEnergy> currentReadings) {
		this.currentReadings = currentReadings;
	}

	@Override
	public String toString() {
		return currentReadings.toString();
	}

	public ReadingEnergyForm() {

	}

	@Autowired
	public ReadingEnergyForm(List<ReadingEnergy> odczytyEnergii) {
		super();
		this.currentReadings = odczytyEnergii;
	}

	public List<ReadingEnergy> getPreviousReadings() {
		return previousReadings;
	}

	public void setPreviousReadings(List<ReadingEnergy> previousReadings) {
		this.previousReadings = previousReadings;
	}

	public void dodajBrakujacyOdczytZPoprzedniejListy(ReadingEnergy odczyt) {
		this.previousReadings.add(odczyt);
	}
}
