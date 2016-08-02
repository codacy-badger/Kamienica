package kamienica.feature.reading;

import java.util.List;

import org.joda.time.LocalDate;

public abstract class ReadingForm<T extends ReadingAbstract> {

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
