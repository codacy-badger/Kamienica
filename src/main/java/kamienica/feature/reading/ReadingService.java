package kamienica.feature.reading;

import kamienica.core.enums.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.model.*;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReadingService {

	<T extends Reading> void setDates(Map<String, Object> map, List<T> list);

	<T extends Reading> T getById(Long id, Media media);

	<T extends Reading> void save(List<T> reading, LocalDate localDate, Media media);

	<T extends Reading> void update(List<T> readings, LocalDate date, Media media);
	
	List<? extends Reading> getList(Residence r, Media media);

	List<? extends Reading> getListForOwner(Media media);

	List<? extends Reading> getByDate(LocalDate date, Media media);

	List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> meterIdList);

	List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> meterIdList);

	List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> meterIdList);

	<T extends Reading> List<T> getLatestNew(Media media) throws NoMainCounterException;

	<T extends Reading> List<T> latestEdit(Media media);

	List<ReadingEnergy> energyLatestEdit();

	List<ReadingGas> gasLatestEdit();

	List<ReadingWater> waterLatestEdit();

	List<ReadingWater> getUnresolvedReadingsWater();

    List<?> getUnresolvedReadings(Media media);

    List<ReadingEnergy> getUnresolvedReadingsEnergy();

	List<ReadingGas> getUnresolvedReadingsGas();

	void deleteLatestReadings(Media media);

}
