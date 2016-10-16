package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.exception.NoMainCounterException;
import kamienica.core.util.Media;
import kamienica.feature.invoice.InvoiceGas;

public interface ReadingService {

	<T extends Reading> void setDates(Map<String, Object> map, List<T> list);

	<T extends Reading> T getById(Long id, Media media);

	<T extends Reading> void save(List<T> reading, LocalDate localDate, Media media);

	<T extends Reading> void update(List<T> readings, LocalDate date, Media media);
	
	List<? extends Reading> getList(Media media);

//	public List<ReadingEnergy> getReadingEnergy();
//
//	public List<ReadingGas> getReadingGas();
//
//	public List<ReadingWater> getReadingWater();

	List<? extends Reading> getByDate(LocalDate date, Media media);

	void deleteList(List<? extends Reading> list, Media media);

	List<? extends Reading> getPreviousReadingEnergy(LocalDate date, Media media);

	List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> meterIdList);

	List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> meterIdList);

	List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> meterIdList);

	<T extends Reading> List<T> getLatestNew(Media media) throws NoMainCounterException;

	<T extends Reading> List<T> latestEdit(Media media);

	List<ReadingEnergy> energyLatestEdit();

	List<ReadingGas> gasLatestEdit();

	List<ReadingWater> waterLatestEdit();

	HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	List<ReadingEnergy> getUnresolvedReadingsEnergy();

	List<ReadingGas> getUnresolvedReadingsGas();

	List<ReadingWater> getUnresolvedReadingsWater();

	void deleteLatestReadings(Media media);

}
