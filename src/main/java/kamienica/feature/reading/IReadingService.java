package kamienica.feature.reading;

import kamienica.model.entity.*;
import kamienica.model.entity.ReadingForm;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface IReadingService {

    Reading getById(Long id);

    void save(List<Reading> reading, ReadingDetails details);

    void save(ReadingForm readingForm);

    void update(List<Reading> readings, LocalDate date);

    void update(ReadingForm readingForm);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getList(Media media);

    Map<ReadingDetails, List<Reading>> list(Residence r, Media media);

    List<Reading> getForInvoice(Invoice invoice);

    @Deprecated
    List<Reading> getPreviousReading(LocalDate date, List<Meter> meters);

    @Deprecated
    List<Reading> getLatestNew(Residence r, Media media) throws NoMainCounterException;

    @Deprecated
    List<Reading> latestEdit(Residence r, Media media);

    void deleteLatestReadings(final Residence r, Media media);

    void delete(ReadingForm readingForm);
}
