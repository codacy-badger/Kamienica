package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceEnergy;

public interface ReadingEnergyDao extends DaoInterface<ReadingEnergy> {

	public List<ReadingEnergy> getByDate(LocalDate date);

	public List<ReadingEnergy> getPrevious(String date, Set<Long> meterId);

	public List<ReadingEnergy> getLatestList(LocalDate date);
	
	//public List<ReadingEnergy> getLatestList(Set<Long> meterId, LocalDate date);

	public List<ReadingEnergy> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
	public List<ReadingEnergy> getUnresolvedReadings();

	public void resolveReadings(InvoiceEnergy invoice);

	public void unresolveReadings(InvoiceEnergy invoice);

	public int countDaysFromLastReading();

	public void deleteLatestReadings(LocalDate date);

	public LocalDate getLatestDate();

}
