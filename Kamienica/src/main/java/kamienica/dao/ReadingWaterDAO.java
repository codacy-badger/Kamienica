package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingAbstract;
import kamienica.model.ReadingWater;

public interface ReadingWaterDAO {
	
	public ReadingWater getById(int id);

	public void save(ReadingWater reading);

	public void update(ReadingWater reading);

	public List<ReadingWater> getList();

	public List<ReadingWater> getByDate(String date);

	public List<ReadingWater> getPrevious(String date);

	public List<ReadingWater> getLatestList();

	public void deleteById(int id);

	public HashMap<Integer, ReadingWater> getLatestReadingsMap();

	public List<Date> getReadingDatesForPayment(PaymentAbstract payment);

	public List<ReadingWater> getWaterReadingsForGasConsumption(ReadingAbstract reading);
	
	public List<ReadingWater> getListForTenant(Apartment apartment); 
	
	public List<ReadingWater> getUnresolvedReadings();
	
	public void ResolveReadings(InvoiceWater invoice);
	
	public void UnresolveReadings(InvoiceWater invoice);
}
