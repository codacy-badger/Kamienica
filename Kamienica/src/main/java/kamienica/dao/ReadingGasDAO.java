package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingGas;

public interface ReadingGasDAO {

	public void save(ReadingGas reading);

	public void update(ReadingGas reading);

	public List<ReadingGas> getList();

	public List<ReadingGas> getByDate(String date);

	public List<ReadingGas> getPrevious(String date);

	public List<ReadingGas> getLatestList();

	public void deleteById(int id);

	public HashMap<Integer, ReadingGas> getLatestReadingsMap();

	public List<Date> getReadingDatesForPayment(PaymentAbstract payment);

	public List<ReadingGas> getListForTenant(Apartment apartment); 
}
