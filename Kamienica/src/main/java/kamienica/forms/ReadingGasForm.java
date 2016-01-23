package kamienica.forms;

import java.util.ArrayList;
import java.util.List;

import kamienica.model.ReadingGas;

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

}
