package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.Media;
import kamienica.feature.invoice.InvoiceGas;

public interface ReadingService {

	// public ReadingEnergy getEnergyById(Long id);
	//
	// public ReadingGas getGasById(Long id);
	//
	// public ReadingWater getWaterById(Long id);

	public <T extends ReadingAbstract> T getById(Long id, Media media);

	public <T extends ReadingAbstract> void save(List<T> reading, LocalDate localDate, Media media);

	// public<F extends ReadingForm> F prepareForm(F form, Media media) throws
	// AbsentMainMeterException;

	// public void saveGasList(List<ReadingGas> reading, LocalDate localDate);
	//
	// public void saveWaterList(List<ReadingWater> reading, LocalDate
	// localDate);
	//
	// public void saveEnergyList(List<ReadingEnergy> reading, LocalDate
	// localDate);

	public <T extends ReadingAbstract> void update(List<T> readings, LocalDate date, Media media);

//	public void updateEnergyList(List<ReadingEnergy> readings, String date);
//
//	public void updateGasList(List<ReadingGas> readings, String date);
//
//	public void updateWaterList(List<ReadingWater> readings, String date);

	public List<ReadingEnergy> getReadingEnergy();

	public <T extends ReadingAbstract> List<T> getByDate(LocalDate date, Media media);

	// public List<ReadingEnergy> getReadingEnergyByDate(LocalDate date);

	public List<ReadingGas> getReadingGas();

	// public List<ReadingGas> getReadingGasByDate(LocalDate date);

	public List<ReadingWater> getReadingWater();

	// public List<ReadingWater> getReadingWaterByDate(LocalDate date);

	public void deleteList(List<? extends ReadingAbstract> list, Media media);

	// public void deleteLatestReadings(Media media);
	
	public<T extends ReadingAbstract> List<T> getPreviousReadingEnergy(LocalDate date, Media media);

	public List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> meterIdList);

	public List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> meterIdList);

	public List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> meterIdList);

	public List<ReadingEnergy> energyLatestNew();

	// public List<ReadingGas> gasLatestNew();
	//
	// public List<ReadingWater> waterLatestNew();

	public <T extends ReadingAbstract> List<T> getLatestNew(Media media);

	public <T extends ReadingAbstract> List<T> latestEdit(Media media);

	public List<ReadingEnergy> energyLatestEdit();

	public List<ReadingGas> gasLatestEdit();

	public List<ReadingWater> waterLatestEdit();

	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	public List<ReadingEnergy> getUnresolvedReadingsEnergy();

	public List<ReadingGas> getUnresolvedReadingsGas();

	public List<ReadingWater> getUnresolvedReadingsWater();

	public void deleteLatestReadings(Media media);

	// public Set<Long> getEnergyIdList();

	// public List<ReadingAbstract> getReadingsForTenant2(Apartment apartment,
	// Media media);

	// public <T extends ReadingAbstract> List<T> getReadingsForTenant(Apartment
	// apartment, Media media);
}
