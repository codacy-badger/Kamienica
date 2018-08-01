package kamienica.model.entity;

import java.util.List;

public class ReadingForm {

    private Set<Reading> readings;

    public ReadingForm() {
    }

    public ReadingForm(ReadingDetails readingDetails, Set<Reading> readings) {
        this.readingDetails = readingDetails;
        this.readings = readings;
    }

    public ReadingForm(ReadingDetails readingDetails, List<Reading> readings) {
        this.readingDetails = readingDetails;
        this.readings = (Set<Reading>) readings;
    }

    public ReadingDetails getReadingDetails() {
        return readingDetails;
    }

    public Set<Reading> getReadings() {
        return readings;
    }

}
