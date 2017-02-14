package kamienica.feature.reading;


import kamienica.model.Meter;
import kamienica.model.Reading;
import org.joda.time.LocalDate;

import java.util.Map;

public class NewReadingForm {

    private LocalDate readingDate;
    private Map<Meter,Reading> readings;

}
