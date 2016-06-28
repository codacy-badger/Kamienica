package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.Invoice;

public interface ReadingDao<R extends ReadingAbstract, I extends Invoice> extends DaoInterface<R> {

	public List<R> getByDate(String date);

	public List<R> getPrevious(String date, Set<Long> meterId);

	public List<R> getLatestList(Set<Long> meterId);

	public List<R> getListForTenant(Apartment apartment);

	public List<R> getUnresolvedReadings();

	public void resolveReadings(I invoice);

	public void unresolveReadings(I invoice);

	public int countDaysFromLastReading();

	public void deleteLatestReadings(LocalDate date);

	public LocalDate getLatestDate();
	

}
