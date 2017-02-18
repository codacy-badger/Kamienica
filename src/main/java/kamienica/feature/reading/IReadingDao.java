package kamienica.feature.reading;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

public interface IReadingDao extends BasicDao<Reading> {

    List<Reading> getByDate(Residence r, LocalDate date);

    List<Reading> getPrevious(LocalDate date, Set<Long> meterId);

    List<Reading> getLatestList(Residence r, LocalDate date);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getListForTenant(Apartment apartment);

    /**
     * @return List<ReadingAbsract>
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<Reading> getUnresolvedReadings(Media media, Residence r);

    void changeResolvementState(Invoice invoice, boolean resolved);

    int countDaysFromLastReading();

    void deleteLatestReadings(LocalDate date);

    LocalDate getLatestDate(Residence r, Media m);

    List<Reading> getWaterReadingForGasConsumption(Residence r, LocalDate date);
}
