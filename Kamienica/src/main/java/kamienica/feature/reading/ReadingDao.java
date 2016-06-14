package kamienica.feature.reading;

import java.util.List;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.Invoice;

public interface ReadingDao<R extends ReadingAbstract, I extends Invoice> extends DaoInterface<R> {


	public List<R> getByDate(String date);

	public List<R> getPrevious(String date);

	public List<R> getLatestList();

	public List<R> getListForTenant(Apartment apartment);

	public List<R> getUnresolvedReadings();

	public void ResolveReadings(I invoice);

	public void UnresolveReadings(I invoice);
	
	public int countDaysFromLastReading();
}
