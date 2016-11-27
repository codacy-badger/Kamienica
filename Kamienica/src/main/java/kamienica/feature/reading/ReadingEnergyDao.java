package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;
import kamienica.model.Invoice;

public interface ReadingEnergyDao extends DaoInterface<ReadingEnergy> {

	List<ReadingEnergy> getByDate(LocalDate date);

	List<ReadingEnergy> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingEnergy> getLatestList(LocalDate date);
	
	//public List<ReadingEnergy> getLatestList(Set<Long> meterId, LocalDate date);

	List<ReadingEnergy> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<ReadingEnergy> getUnresolvedReadings();
	
	void changeResolvmentState(Invoice invoice, boolean resolved);

//	public void resolveReadings(InvoiceEnergy invoice);
//
//	public void unresolveReadings(InvoiceEnergy invoice);

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate();

}
