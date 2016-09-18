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

	public <T extends ReadingAbstract> void setDates(Map<String, Object> map, List<T> list);

	public <T extends ReadingAbstract> T getById(Long id, Media media);

	public <T extends ReadingAbstract> void save(List<T> reading, LocalDate localDate, Media media);

	public <T extends ReadingAbstract> void update(List<T> readings, LocalDate date, Media media);

	public List<ReadingEnergy> getReadingEnergy();

	public List<ReadingGas> getReadingGas();

	public List<ReadingWater> getReadingWater();

	public List<? extends ReadingAbstract> getByDate(LocalDate date, Media media);

	public void deleteList(List<? extends ReadingAbstract> list, Media media);

	public List<? extends ReadingAbstract> getPreviousReadingEnergy(LocalDate date, Media media);

	public List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> meterIdList);

	public List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> meterIdList);

	public List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> meterIdList);

	public <T extends ReadingAbstract> List<T> getLatestNew(Media media) throws NoMainCounterException;

	public <T extends ReadingAbstract> List<T> latestEdit(Media media);

	public List<ReadingEnergy> energyLatestEdit();

	public List<ReadingGas> gasLatestEdit();

	public List<ReadingWater> waterLatestEdit();

	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	public List<ReadingEnergy> getUnresolvedReadingsEnergy();

	public List<ReadingGas> getUnresolvedReadingsGas();

	public List<ReadingWater> getUnresolvedReadingsWater();

	public void deleteLatestReadings(Media media);

}
