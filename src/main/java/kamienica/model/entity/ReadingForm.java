package kamienica.model.entity;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

public class ReadingForm {

    private ReadingDetails readingDetails;
    private Set<Reading> readings;

    public ReadingForm() {
    }

    public ReadingForm(ReadingDetails readingDetails, Set<Reading> readings) {
        this.readingDetails = readingDetails;
        this.readings = readings;
    }

    public ReadingForm(ReadingDetails readingDetails, List<Reading> readings) {
        this.readingDetails = readingDetails;
        this.readings = Sets.newHashSet(readings);
    }

    public ReadingDetails getReadingDetails() {
        return readingDetails;
    }

    public Set<Reading> getReadings() {
        return readings;
    }

}
