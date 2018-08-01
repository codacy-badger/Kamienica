package kamienica.feature.reading;

import java.util.List;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingForm;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;

public interface IReadingService {

    Reading getById(Long id);

    void save(ReadingForm readingForm);

    void save(List<Reading> readings);

    void update(ReadingForm readingForm);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getForInvoice(Invoice invoice);

    @Deprecated
    List<Reading> latestEdit(Residence r, Media media);

    void deleteLatestReadings(final Residence r, Media media);

    List<Reading> getPreviousReadingForWarmWater(final Invoice invoice);

    List<Reading> getPreviousReadingForWarmWater(Residence r, Media m, LocalDate date);
}
