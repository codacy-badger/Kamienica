package kamienica.feature.reading;

import java.util.HashMap;
import java.util.LinkedList;
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
	public List<ReadingEnergy> energyLatestNew(Set<Long> idList) {
		List<ReadingEnergy> energyList = energy.getLatestList(idList);
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

	@Override
	public List<ReadingEnergy> energyLatestEdit(Set<Long> idList) {
		return energy.getLatestList(idList);
	}

	@Override
	public List<ReadingGas> gasLatestEdit(Set<Long> idList) {
		return gas.getLatestList(idList);
	}

	@Override
	public List<ReadingWater> waterLatestEdit(Set<Long> idList) {
		return water.getLatestList(idList);
	}

	@Override
	public List<ReadingEnergy> getPreviousReadingEnergy(String date, Set<Long> idList) {
		return energy.getPrevious(date, idList);
	}

	/**
	 * @return latest gas readings
	 * 
	 *         if there hasn't been any readings yet the
	 */
	@Override
	public List<ReadingGas> gasLatest(Set<Long> idList) {
		List<ReadingGas> gasList = gas.getLatestList(idList);

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
	public List<ReadingWater> waterLatest(Set<Long> idList) {
		List<ReadingWater> waterList = water.getLatestList(idList);
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
	public List<ReadingGas> getPreviousReadingGas(String date, Set<Long> idList) {
		return gas.getPrevious(date, idList);
	}

	@Override
	public List<ReadingWater> getPreviousReadingWater(String date, Set<Long> idList) {
		return water.getPrevious(date, idList);
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
	public void updateEnergyList(List<ReadingEnergy> readings, String date) {
		for (ReadingEnergy readingEnergy : readings) {
			if (readingEnergy.getValue() < 0) {
				throw new IllegalArgumentException();
			}
			readingEnergy.setReadingDate(LocalDate.parse(date));
			readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
			energy.update(readingEnergy);
		}

	}

	@Override
	public void updateGasList(List<ReadingGas> readings, String date) {
		for (ReadingGas readingEnergy : readings) {
			if (readingEnergy.getValue() < 0) {
				throw new IllegalArgumentException();
			}
			readingEnergy.setReadingDate(LocalDate.parse(date));
			readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
			gas.update(readingEnergy);
		}

	}

	@Override
	public void updateWaterList(List<ReadingWater> readings, String date) {
		for (ReadingWater readingEnergy : readings) {
			if (readingEnergy.getValue() < 0) {
				throw new IllegalArgumentException();
			}
			readingEnergy.setReadingDate(LocalDate.parse(date));
			readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
			water.update(readingEnergy);
		}

	}

	@Override
	public void deleteLatestReadings(Media media) {
		switch (media) {
		case ENERGY:

			energy.deleteLatestReadings(energy.getLatestDate());

			break;
		case GAS:

			gas.deleteLatestReadings(gas.getLatestDate());

			break;
		case WATER:

			water.deleteLatestReadings(water.getLatestDate());

			break;
		default:
			break;
		}

	}

	// @Override
	// public Set<Long> getEnergyIdList() {
	// return energy.getIdList();
	// }

}
