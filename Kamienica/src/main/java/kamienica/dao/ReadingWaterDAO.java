package kamienica.dao;

import java.util.HashMap;
import java.util.List;

import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.ReadingWater;

public interface ReadingWaterDAO extends ReadingDao<ReadingWater, InvoiceWater>{

//	public List<ReadingWater> getByDate(String date);
//
//	public List<ReadingWater> getPrevious(String date);
//
//	public List<ReadingWater> getLatestList();
//
//	public HashMap<Integer, ReadingWater> getLatestReadingsMap();
//
//	public List<ReadingWater> getListForTenant(Apartment apartment);
//
//	public List<ReadingWater> getUnresolvedReadings();
//
//	public void ResolveReadings(InvoiceWater invoice);
//
//	public void UnresolveReadings(InvoiceWater invoice);
//	
	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice);

}
