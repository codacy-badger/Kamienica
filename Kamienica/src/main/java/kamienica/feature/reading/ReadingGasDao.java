package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.Invoice;

public interface ReadingGasDao extends DaoInterface<ReadingGas> {

	List<ReadingGas> getByDate(LocalDate date);

	List<ReadingGas> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingGas> getLatestList(LocalDate date);

	List<ReadingGas> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<ReadingGas> getUnresolvedReadings();
	
	void changeResolvmentState(Invoice invoice, boolean resolved);
	
//	public void resolveReadings(InvoiceGas invoice);
//
//	public void unresolveReadings(InvoiceGas invoice);

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate();
}
