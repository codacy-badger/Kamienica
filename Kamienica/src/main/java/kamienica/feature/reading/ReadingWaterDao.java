package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.Invoice;
import kamienica.feature.invoice.InvoiceGas;

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
	
	void changeResolvmentState(Invoice invoice, boolean resolved);

//	public void resolveReadings(InvoiceWater invoice);
//
//	public void unresolveReadings(InvoiceWater invoice);

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate();
	
	List<ReadingWater> getWaterReadingForGasConsumption2(LocalDate date);

	HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
