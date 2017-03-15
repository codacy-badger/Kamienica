package kamienica.feature.reading;

import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface IReadingService  {

    Reading getById(Long id);

    void save(List<Reading> reading, ReadingDetails details);

    void update(List<Reading> readings, LocalDate date);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getByDate(Residence r, LocalDate date, Media media);

    @Deprecated
    List<Reading> getPreviousReading(LocalDate date, List<Meter> meters);

    @Deprecated
    List<Reading> getLatestNew(Residence r, Media media) throws NoMainCounterException;

    @Deprecated
    List<Reading> latestEdit(Residence r, Media media);

    void deleteLatestReadings(final Residence r, Media media);

}
