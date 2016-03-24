package kamienica.wrapper;

import java.util.ArrayList;
import java.util.List;

import kamienica.model.ReadingAbstract;

public class ReadingForm<R extends ReadingAbstract> {

	private List<R> currentReadings = new ArrayList<>();
	private List<R> previousReadings = new ArrayList<>();

	public ReadingForm() {

	}

	public List<R> getCurrentReadings() {
		return currentReadings;
	}

	public void setCurrentReadings(List<R> odczytyGazu) {
		this.currentReadings = odczytyGazu;
	}

	public List<R> getPreviousReadings() {
		return previousReadings;
	}

	public void setPreviousReadings(List<R> previousRearing) {
		this.previousReadings = previousRearing;
	}

	@Override
	public String toString() {
		return "OdczytGazFormularz [odczytyGazu=" + currentReadings + ", poprzednieOdczytyGazu=" + previousReadings
				+ "]";
	}
}
