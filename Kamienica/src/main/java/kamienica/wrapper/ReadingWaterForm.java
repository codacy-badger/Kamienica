package kamienica.wrapper;

import java.util.ArrayList;
import java.util.List;

import kamienica.model.ReadingWater;

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

}
