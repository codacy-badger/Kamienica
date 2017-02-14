package kamienica.feature.reading;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Apartment;
import kamienica.model.Invoice;
import kamienica.model.ReadingGas;
import kamienica.model.Residence;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

public interface ReadingGasDao extends BasicDao<ReadingGas> {

	List<ReadingGas> getByDate(Residence r, LocalDate date);

	List<ReadingGas> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingGas> getLatestList(Residence r, LocalDate date);

	List<ReadingGas> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<ReadingGas> getUnresolvedReadings();
	
	void changeResolvementState(Invoice invoice, boolean resolved);
	

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate(Residence r);

    List<ReadingGas> getList(Residence r);
}

