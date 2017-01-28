package kamienica.feature.reading;

import kamienica.model.Reading;
import kamienica.model.Residence;

import java.util.List;
import java.util.Map;

public class NewReadingForm {

    private Map<Residence, List<Reading>> readings;

    public NewReadingForm(Map<Residence, List<Reading>> readings) {
        this.readings = readings;
    }

    public Map<Residence, List<Reading>> getReadings() {
        return readings;
    }

    public void setReadings(Map<Residence, List<Reading>> readings) {
        this.readings = readings;
    }
}
