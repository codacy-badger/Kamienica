package kamienica.feature.reading;

import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IReadingService {

	void setDates(Map<String, Object> map, List<Reading> list);

	Reading getById(Long id);

	void save(List<Reading> reading, LocalDate localDate);

	void update(List<Reading> readings, LocalDate date);
	
	List<Reading> getList(Residence r, Media media);

	List<Reading> getByDate(Residence r, LocalDate date, Media media);

	List<Reading> getPreviousReading(LocalDate date, Set<Long> meterIdList);

	List<Reading> getLatestNew(Residence r, Media media) throws NoMainCounterException;

	List<Reading> latestEdit(Residence r, Media media);

    List<Reading> getUnresolvedReadings(Media media, Residence r);

	void deleteLatestReadings(final Residence r, Media media);

}
