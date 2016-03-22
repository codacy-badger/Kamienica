//package kamienica.dao;
//
//import java.util.HashMap;
//import java.util.List;
//
//import kamienica.model.Apartment;
//import kamienica.model.InvoiceEnergy;
//import kamienica.model.ReadingEnergy;
//
//public interface ReadingEnergyDAO extends DaoInterface<ReadingEnergy> {
//
//	public List<ReadingEnergy> getByDate(String date);
//
//	public List<ReadingEnergy> getPrevious(String date);
//
//	public List<ReadingEnergy> getLatestList();
//
//	public HashMap<Integer, ReadingEnergy> getLatestReadingsMap();
//
//	public List<ReadingEnergy> getListForTenant(Apartment apartment);
//
//	public List<ReadingEnergy> getUnresolvedReadings();
//
//	public void ResolveReadings(InvoiceEnergy invoice);
//
//	public void UnresolveReadings(InvoiceEnergy invoice);
//
//}
