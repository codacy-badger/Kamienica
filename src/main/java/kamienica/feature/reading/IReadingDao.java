package kamienica.feature.reading;

import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.joda.time.LocalDate;

import java.util.List;

public interface IReadingDao extends BasicDao<Reading> {

    List<Reading> getByDate(Residence r, LocalDate date);

    List<Reading> getPrevious(ReadingDetails details, List<Meter> meters);

    List<Reading> getLatestList(Residence r, LocalDate date);

    List<Reading> getList(Residence r, Media media);

    List<Reading> getListForTenant(Apartment apartment);

    List<Reading> list(ReadingDetails details);
    /**
     * @return List<ReadingAbsract>
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<Reading> getUnresolvedReadings(Media media, Residence r);

    void changeResolvementState(Invoice invoice, boolean resolved);

    int countDaysFromLastReading();

    LocalDate getLatestDate(Residence r, Media m);

    List<Reading> getWaterReadingForGasConsumption(Residence r, LocalDate date);
}
