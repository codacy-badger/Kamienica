package kamienica.dao;

import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.ReadingGas;

public interface ReadingGasDAO {

	public ReadingGas getById(int id);

	public void save(ReadingGas reading);

	public void update(ReadingGas reading);

	public List<ReadingGas> getList();

	public List<ReadingGas> getByDate(String date);

	public List<ReadingGas> getPrevious(String date);

	public List<ReadingGas> getLatestList();

	public void deleteById(int id);

	public HashMap<Integer, ReadingGas> getLatestReadingsMap();

	// public List<Date> getReadingDatesForPayment(PaymentAbstract payment);

	public List<ReadingGas> getListForTenant(Apartment apartment);

	public List<ReadingGas> getUnresolvedReadings();

	public List<ReadingGas> getLastPaid(InvoiceGas invoice);

	public void ResolveReadings(InvoiceGas invoice);

	public void UnresolveReadings(InvoiceGas invoice);

}
