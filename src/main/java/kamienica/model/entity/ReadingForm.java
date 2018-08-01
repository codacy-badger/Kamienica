package kamienica.model.entity;

import java.util.List;

public class ReadingForm {

    private List<Reading> readings;

    public ReadingForm() {
    }

    public ReadingForm(final List<Reading> readings) {
        this.readings = readings;
    }

    public List<Reading> getReadings() {
        return readings;
    }
}
