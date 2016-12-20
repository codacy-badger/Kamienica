package kamienica.feature.reading;

import org.joda.time.LocalDate;

import java.util.List;

public abstract class ReadingForm<T extends Reading> {

	LocalDate oldDate;
	LocalDate newDate;

	public ReadingForm(LocalDate oldDate, LocalDate newDate) {
		this.oldDate = oldDate;
		this.newDate = newDate;
	}

	public ReadingForm() {
	}

	public LocalDate getOldDate() {
		return oldDate;
	}

	public void setOldDate(LocalDate oldDate) {
		this.oldDate = oldDate;
	}

	public LocalDate getNewDate() {
		return newDate;
	}

	public void setNewDate(LocalDate newDate) {
		this.newDate = newDate;
	}

	public abstract List<ReadingEnergy> getPreviousReadings();

	public abstract void setPreviousReadings(List<T> previousReadings);

	public abstract List<ReadingEnergy> getCurrentReadings();

	public abstract void setCurrentReadings(List<T> currentReadings);

	public abstract List<ReadingEnergy> getNewReadings();

	public abstract void setNewReadings(List<T> newReadings);

}
