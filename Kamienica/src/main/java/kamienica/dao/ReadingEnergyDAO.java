package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingEnergy;

public interface ReadingEnergyDAO {
	
	public ReadingEnergy getById(int id);

	public void save(ReadingEnergy reading);

	public void saveList(List<ReadingEnergy> reading);
	
	public void update(ReadingEnergy reading);

	public List<ReadingEnergy> getList();

	public List<ReadingEnergy> getByDate(String date);

	public List<ReadingEnergy> getPrevious(String date);

	public List<ReadingEnergy> getLatestList();

	public void deleteById(int id);

	public HashMap<Integer, ReadingEnergy> getLatestReadingsMap();

	public List<Date> getReadingDatesForPayment(PaymentAbstract payment);

	public List<ReadingEnergy> getListForTenant(Apartment apartment); 
	
	public List<ReadingEnergy> getUnresolvedReadings();
	
	public List<ReadingEnergy> getLastPaid(InvoiceEnergy invoice);
	
	public void ResolveReadings(InvoiceEnergy invoice);
	
	public void UnresolveReadings(InvoiceEnergy invoice);
	
	
	
}
