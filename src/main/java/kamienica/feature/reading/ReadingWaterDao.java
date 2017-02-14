package kamienica.feature.reading;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.*;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ReadingWaterDao extends BasicDao<ReadingWater> {

	List<ReadingWater> getByDate(Residence r, LocalDate date);

	List<ReadingWater> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingWater> getLatestList(Residence r, LocalDate date);

	List<ReadingWater> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<ReadingWater> getUnresolvedReadings();
	
	void changeResolvementState(Invoice invoice, boolean resolved);

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate(Residence r);
	
	List<ReadingWater> getWaterReadingForGasConsumption2(Residence r, LocalDate date);

	HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

    List<ReadingWater> getList(Residence r);
}
