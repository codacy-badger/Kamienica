package kamienica.feature.reading;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;
import kamienica.model.Invoice;
import kamienica.model.InvoiceGas;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface ReadingWaterDao extends DaoInterface<ReadingWater> {

	List<ReadingWater> getByDate(LocalDate date);

	List<ReadingWater> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingWater> getLatestList(LocalDate date);

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

	LocalDate getLatestDate();
	
	List<ReadingWater> getWaterReadingForGasConsumption2(LocalDate date);

	HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
