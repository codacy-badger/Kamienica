package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;

public interface ReadingWaterDao extends DaoInterface<ReadingWater> {

	public List<ReadingWater> getByDate(String date);

	public List<ReadingWater> getPrevious(String date, Set<Long> meterId);

	public List<ReadingWater> getLatestList(Set<Long> meterId);

	public List<ReadingWater> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
	public List<ReadingWater> getUnresolvedReadings();

	public void resolveReadings(InvoiceWater invoice);

	public void unresolveReadings(InvoiceWater invoice);

	public int countDaysFromLastReading();

	public void deleteLatestReadings(LocalDate date);

	public LocalDate getLatestDate();

	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
