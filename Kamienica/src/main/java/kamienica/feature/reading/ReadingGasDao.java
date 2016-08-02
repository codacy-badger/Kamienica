package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceGas;

public interface ReadingGasDao extends DaoInterface<ReadingGas> {

	public List<ReadingGas> getByDate(String date);

	public List<ReadingGas> getPrevious(String date, Set<Long> meterId);

	public List<ReadingGas> getLatestList(Set<Long> meterId);

	public List<ReadingGas> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
	public List<ReadingGas> getUnresolvedReadings();

	public void resolveReadings(InvoiceGas invoice);

	public void unresolveReadings(InvoiceGas invoice);

	public int countDaysFromLastReading();

	public void deleteLatestReadings(LocalDate date);

	public LocalDate getLatestDate();
}
