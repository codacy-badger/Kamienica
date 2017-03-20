package kamienica.feature.reading;

import kamienica.model.entity.Reading;
import org.joda.time.LocalDate;

import java.util.List;

public class ReadingForm {

    private LocalDate oldDate;
    private LocalDate newDate;
    private List<Reading> currentReadings;
    private List<Reading> previousReadings;
    private List<Reading> newReadings;


    public String toString() {
        return "currentReadings=\n" + currentReadings + "\n previousReadings\n" + previousReadings + "\n newReadings\n"
                + newReadings;
    }

    public ReadingForm() {
    }

    public ReadingForm(LocalDate oldDate, LocalDate newDate, List<Reading> currentReadings,
                       List<Reading> previousReadings, List<Reading> newReadings) {
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.currentReadings = currentReadings;
        this.previousReadings = previousReadings;
        this.newReadings = newReadings;
    }

    public ReadingForm(List<Reading> odczytyEnergii) {
        this.currentReadings = odczytyEnergii;
    }


    public List<Reading> getPreviousReadings() {
        return previousReadings;
    }


    public void setPreviousReadings(List<Reading> previousReadings) {
        this.previousReadings = previousReadings;
    }


    public List<Reading> getCurrentReadings() {
        return currentReadings;
    }


    public void setCurrentReadings(List<Reading> currentReadings) {
        this.currentReadings = currentReadings;
    }


    public List<Reading> getNewReadings() {
        return newReadings;
    }


    public void setNewReadings(List<Reading> newReadings) {
        this.newReadings = newReadings;
    }

    public LocalDate getDate() {
        return currentReadings.get(0).getReadingDetails().getReadingDate();
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

    public LocalDate getPreviousDate() {
        if (previousReadings.isEmpty()) {
            return LocalDate.parse("2010-01-01");
        } else {
            return previousReadings.get(0).getReadingDetails().getReadingDate();
        }
    }

}
