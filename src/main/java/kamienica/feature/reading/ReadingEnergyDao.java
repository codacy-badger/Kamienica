package kamienica.feature.reading;

import kamienica.core.daoservice.BasicDao;
import kamienica.core.enums.Media;
import kamienica.model.Apartment;
import kamienica.model.Invoice;
import kamienica.model.ReadingEnergy;
import kamienica.model.Residence;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

public interface ReadingEnergyDao extends BasicDao<ReadingEnergy> {

	List<ReadingEnergy> getByDate(Residence r, LocalDate date);

	List<ReadingEnergy> getPrevious(LocalDate date, Set<Long> meterId);

	List<ReadingEnergy> getLatestList(Residence r, LocalDate date);

	List<ReadingEnergy> getList(Residence r);

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

	LocalDate getLatestDate(Residence r);

}
