package kamienica.feature.reading;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;
import kamienica.model.Invoice;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

public interface ReadingEnergyDao extends DaoInterface<ReadingEnergy> {

	List<ReadingEnergy> getByDate(LocalDate date);

	List<ReadingEnergy> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingEnergy> getLatestList(LocalDate date);
	

	List<ReadingEnergy> getListForTenant(Apartment apartment);

	/**
	 * @return List<R extends ReadingAbsract
	 * 
	 *         Returns list of unresolved reading for main counter. This list is
	 *         used for new Incoives
	 */
    List<ReadingEnergy> getUnresolvedReadings();
	
	void changeResolvementState(Invoice invoice, boolean resolved);

	int countDaysFromLastReading();

	void deleteLatestReadings(LocalDate date);

	LocalDate getLatestDate();

}
