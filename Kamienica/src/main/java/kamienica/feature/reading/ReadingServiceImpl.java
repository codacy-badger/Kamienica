package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.dao.DaoInterface;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterWater;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	ReadingDao<ReadingEnergy, InvoiceEnergy> energy;
	@Autowired
	ReadingWaterDAO water;
	@Autowired
	ReadingDao<ReadingGas, InvoiceGas> gas;
	@Autowired
	DaoInterface<MeterEnergy> meterEnergy;
	@Autowired
	DaoInterface<MeterGas> meterGas;
	@Autowired
	DaoInterface<MeterWater> meterWater;

	@Override
	public List<ReadingEnergy> getReadingEnergy() {
		return energy.getList();
	}

	@Override
	public List<ReadingGas> getReadingGas() {
		return gas.getList();
	}

	@Override
	public List<ReadingWater> getReadingWater() {
		return water.getList();
	}

	@Override
	public List<ReadingEnergy> getLatestEnergyReadings(Set<Long> idList) {
		List<ReadingEnergy> energyList = energy.getLatestList();
		// if this the very first time user creates readings
		if (energyList.isEmpty()) {
			for (Long tmpLong : idList) {

				energyList.add(new ReadingEnergy(new LocalDate().minusDays(100), 0.0, meterEnergy.getById(tmpLong)));
			}

		} else {
			// checks if there has been a new meter and adds fake '0' reading
			for (ReadingEnergy readingEnergy : energyList) {
				idList.remove(readingEnergy.getMeter().getId());
			}
			for (Long tmpLong : idList) {

				energyList
						.add(new ReadingEnergy(energyList.get(0).getReadingDate(), 0.0, meterEnergy.getById(tmpLong)));
			}
		}
		return energyList;

	}

	/**
	 * @return latest gas readings
	 * 
	 *         if there hasn't been any readings yet the
	 */
	@Override
	public List<ReadingGas> getLatestGasReadings(Set<Long> idList) {

		List<ReadingGas> gasList = gas.getLatestList();
		// if this the very first time user creates readings
		if (gasList.isEmpty()) {
			for (Long tmpLong : idList) {

				gasList.add(new ReadingGas(new LocalDate().minusDays(100), 0.0, meterGas.getById(tmpLong)));
			}

		} else {
			// checks if there has been a new meter and adds fake '0' reading
			for (ReadingGas readingGas : gasList) {
				idList.remove(readingGas.getMeter().getId());
			}
			for (Long tmpLong : idList) {

				gasList.add(new ReadingGas(gasList.get(0).getReadingDate(), 0.0, meterGas.getById(tmpLong)));
			}
		}
		return gasList;

	}

	@Override
	public List<ReadingWater> getLatestWaterReadings(Set<Long> idList) {
		List<ReadingWater> waterList = water.getLatestList();
		// if this the very first time user creates readings
		if (waterList.isEmpty()) {
			for (Long tmpLong : idList) {

				waterList.add(new ReadingWater(new LocalDate().minusDays(100), 0.0, meterWater.getById(tmpLong)));
			}

		} else {
			// checks if there has been a new meter and adds fake '0' reading
			for (ReadingWater readingWater : waterList) {
				idList.remove(readingWater.getMeter().getId());
			}
			for (Long tmpLong : idList) {

				waterList.add(new ReadingWater(waterList.get(0).getReadingDate(), 0.0, meterWater.getById(tmpLong)));
			}
		}
		return waterList;

	}

	@Override
	public List<ReadingEnergy> getPreviousReadingEnergy(String date) {

		return energy.getPrevious(date);
	}

	@Override
	public List<ReadingGas> getPreviousReadingGas(String date) {
		return gas.getPrevious(date);
	}

	@Override
	public List<ReadingWater> getPreviousReadingWater(String date) {
		return water.getPrevious(date);
	}

	@Override
	public List<ReadingEnergy> getReadingEnergyByDate(String date) {

		return energy.getByDate(date);
	}

	@Override
	public List<ReadingGas> getReadingGasByDate(String date) {
		return gas.getByDate(date);
	}

	@Override
	public List<ReadingWater> getReadingWaterByDate(String date) {
		return water.getByDate(date);
	}

	@Override
	public void saveGasList(List<ReadingGas> reading, LocalDate date) {
		for (ReadingGas i : reading) {
			i.setReadingDate(date);
			i.setUnit(i.getMeter().getUnit());
			gas.save(i);
		}
	}

	@Override
	public void saveWaterList(List<ReadingWater> reading, LocalDate date) {
		for (ReadingWater i : reading) {
			i.setReadingDate(date);
			i.setUnit(i.getMeter().getUnit());
			water.save(i);
		}

	}

	@Override
	public void saveEnergyList(List<ReadingEnergy> reading, LocalDate date) {
		for (ReadingEnergy readingEnergy : reading) {
			readingEnergy.setReadingDate(date);
			readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
			energy.save(readingEnergy);
		}

	}

	@Override
	public void updateEnergy(ReadingEnergy reading) {
		energy.update(reading);

	}

	@Override
	public void updateGas(ReadingGas reading) {
		gas.update(reading);

	}

	@Override
	public void updateWater(ReadingWater reading) {
		water.update(reading);

	}

	@Override
	public ReadingEnergy getEnergyById(Long id) {
		return energy.getById(id);
	}

	@Override
	public ReadingGas getGasById(Long id) {
		return gas.getById(id);
	}

	@Override
	public ReadingWater getWaterById(Long id) {
		return water.getById(id);
	}

	@Override
	public List<ReadingEnergy> getUnresolvedReadingsEnergy() {
		return energy.getUnresolvedReadings();
	}

	@Override
	public List<ReadingGas> getUnresolvedReadingsGas() {
		return gas.getUnresolvedReadings();
	}

	@Override
	public List<ReadingWater> getUnresolvedReadingsWater() {
		return water.getUnresolvedReadings();
	}

	@Override
	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice) {
		return water.getWaterReadingForGasConsumption(invoice);
	}

	@Override
	public void deleteList(List<? extends ReadingAbstract> list, Media media) {
		switch (media) {
		case ENERGY:
			for (ReadingAbstract reading : list) {
				energy.deleteById(reading.getId());
			}
			break;
		case GAS:
			for (ReadingAbstract reading : list) {
				gas.deleteById(reading.getId());
			}
			break;
		case WATER:
			for (ReadingAbstract reading : list) {
				water.deleteById(reading.getId());
			}
			break;
		default:
			break;
		}
	}



	@Override
	public void updateEnergyList(List<ReadingEnergy> readings) {
		for (ReadingEnergy readingEnergy : readings) {
			energy.update(readingEnergy);
		}

	}

	@Override
	public Set<Long> getEnergyIdList() {
		return energy.getIdList();
	}


}
