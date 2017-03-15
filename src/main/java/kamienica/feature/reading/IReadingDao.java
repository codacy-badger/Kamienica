package kamienica.feature.reading;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.joda.time.LocalDate;

import java.util.List;

public interface IReadingDao extends BasicDao<Reading> {

    List<Reading> getByDate(Residence r, LocalDate date);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getListForTenant(Apartment apartment);

    List<Reading> list(ReadingDetails details);

}
