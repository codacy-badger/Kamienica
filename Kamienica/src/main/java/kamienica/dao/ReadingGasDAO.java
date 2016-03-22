//package kamienica.dao;
//
//import java.util.HashMap;
//import java.util.List;
//
//import kamienica.model.Apartment;
//import kamienica.model.InvoiceGas;
//import kamienica.model.ReadingGas;
//
//public interface ReadingGasDAO extends DaoInterface<ReadingGas> {
//
//	public List<ReadingGas> getByDate(String date);
//
//	public List<ReadingGas> getPrevious(String date);
//
//	public List<ReadingGas> getLatestList();
//
//	public HashMap<Integer, ReadingGas> getLatestReadingsMap();
//
//	public List<ReadingGas> getListForTenant(Apartment apartment);
//
//	public List<ReadingGas> getUnresolvedReadings();
//
//	public void ResolveReadings(InvoiceGas invoice);
//
//	public void UnresolveReadings(InvoiceGas invoice);
//
//}
