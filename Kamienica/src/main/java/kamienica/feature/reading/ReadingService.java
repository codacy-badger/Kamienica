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

	public <T extends Reading> void setDates(Map<String, Object> map, List<T> list);

	public <T extends Reading> T getById(Long id, Media media);

	public <T extends Reading> void save(List<T> reading, LocalDate localDate, Media media);

	public <T extends Reading> void update(List<T> readings, LocalDate date, Media media);
	
	public List<? extends Reading> getList(Media media);

//	public List<ReadingEnergy> getReadingEnergy();
//
//	public List<ReadingGas> getReadingGas();
//
//	public List<ReadingWater> getReadingWater();

	public List<? extends Reading> getByDate(LocalDate date, Media media);

	public void deleteList(List<? extends Reading> list, Media media);

	public List<? extends Reading> getPreviousReadingEnergy(LocalDate date, Media media);

	public List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> meterIdList);

	public List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> meterIdList);

	public List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> meterIdList);

	public <T extends Reading> List<T> getLatestNew(Media media) throws NoMainCounterException;

	public <T extends Reading> List<T> latestEdit(Media media);

	public List<ReadingEnergy> energyLatestEdit();

	public List<ReadingGas> gasLatestEdit();

	public List<ReadingWater> waterLatestEdit();

	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	public List<ReadingEnergy> getUnresolvedReadingsEnergy();

	public List<ReadingGas> getUnresolvedReadingsGas();

	public List<ReadingWater> getUnresolvedReadingsWater();

	public void deleteLatestReadings(Media media);

}
