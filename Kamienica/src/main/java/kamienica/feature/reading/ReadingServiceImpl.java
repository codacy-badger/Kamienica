package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.meter.MeterDao;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterWater;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Qualifier("readingEnergyDao")
	@Autowired
	ReadingEnergyDao energy;
	@Autowired
	ReadingWaterDao water;
	@Autowired
	ReadingGasDao gas;
	@Autowired
	MeterDao<MeterEnergy> meterEnergy;
	@Autowired
	MeterDao<MeterGas> meterGas;
	@Autowired
	MeterDao<MeterWater> meterWater;

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

	/**
	 * 
	 * Method designed for creating new readings. If this is the first input the
	 * method will create fake '0' readings for each meter. It will also create
	 * 0 reading for every new meter that has been recently added
	 * 
	 * @throws NoMainCounterException
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends ReadingAbstract> List<T> getLatestNew(Media media) throws NoMainCounterException {
		Set<Long> idList;
		switch (media) {
		case ENERGY:
			if (!meterEnergy.ifMainExists()) {
				throw new NoMainCounterException();
			}
			idList = meterEnergy.getIdList();
			List<ReadingEnergy> energyList = latestEdit(Media.ENERGY);
			if (energyList.isEmpty()) {
				for (Long tmpLong : idList) {
					energyList
							.add(new ReadingEnergy(new LocalDate().minusDays(100), 0.0, meterEnergy.getById(tmpLong)));
				}
			} else {
				for (ReadingEnergy readingEnergy : energyList) {
					idList.remove(readingEnergy.getMeter().getId());
				}
				for (Long tmpLong : idList) {
					energyList.add(
							new ReadingEnergy(energyList.get(0).getReadingDate(), 0.0, meterEnergy.getById(tmpLong)));
				}
			}
			return (List<T>) energyList;
		case GAS:
			if (!meterGas.ifMainExists()) {
				throw new NoMainCounterException();
			}
			idList = meterGas.getIdList();
			List<ReadingGas> gasList = latestEdit(Media.GAS);

			// if this the very first time user creates readings
			if (gasList.isEmpty()) {
				for (Long tmpLong : idList) {
					gasList.add(new ReadingGas(new LocalDate().minusDays(100), 0.0, meterGas.getById(tmpLong)));
				}
			} else {
				// checks if there has been a new meter and adds fake '0'
				// reading
				for (ReadingGas readingGas : gasList) {
					idList.remove(readingGas.getMeter().getId());
				}
				for (Long tmpLong : idList) {
					gasList.add(new ReadingGas(gasList.get(0).getReadingDate(), 0.0, meterGas.getById(tmpLong)));
				}
			}
			return (List<T>) gasList;

		case WATER:
			if (!meterWater.ifMainExists()) {
				throw new NoMainCounterException();
			}
			idList = meterWater.getIdList();
			List<ReadingWater> waterList = latestEdit(Media.WATER);
			if (waterList.isEmpty()) {
				for (Long tmpLong : idList) {
					waterList.add(new ReadingWater(new LocalDate().minusDays(100), 0.0, meterWater.getById(tmpLong)));
				}
			} else {
				for (ReadingWater readingWater : waterList) {
					idList.remove(readingWater.getMeter().getId());
				}
				for (Long tmpLong : idList) {
					waterList
							.add(new ReadingWater(waterList.get(0).getReadingDate(), 0.0, meterWater.getById(tmpLong)));
				}
			}
			return (List<T>) waterList;

		default:
			break;
		}
		return null;
	}

	// @Override
	// public List<ReadingEnergy> energyLatestNew() {
	// Set<Long> idList = meterEnergy.getIdList();
	// List<ReadingEnergy> energyList = latestEdit(Media.ENERGY);
	// // if this the very first time user creates readings
	// if (energyList.isEmpty()) {
	// for (Long tmpLong : idList) {
	// energyList.add(new ReadingEnergy(new LocalDate().minusDays(100), 0.0,
	// meterEnergy.getById(tmpLong)));
	// }
	// } else {
	// // checks if there has been a new meter and adds fake '0' reading
	// for (ReadingEnergy readingEnergy : energyList) {
	// idList.remove(readingEnergy.getMeter().getId());
	// }
	// for (Long tmpLong : idList) {
	// energyList
	// .add(new ReadingEnergy(energyList.get(0).getReadingDate(), 0.0,
	// meterEnergy.getById(tmpLong)));
	// }
	// }
	// return energyList;
	// }

	// @Override
	// public List<ReadingGas> gasLatest() {
	// List<ReadingGas> gasList = gas.getLatestList(idList);
	//
	// // if this the very first time user creates readings
	// if (gasList.isEmpty()) {
	// for (Long tmpLong : idList) {
	// gasList.add(new ReadingGas(new LocalDate().minusDays(100), 0.0,
	// meterGas.getById(tmpLong)));
	// }
	// } else {
	// // checks if there has been a new meter and adds fake '0' reading
	// for (ReadingGas readingGas : gasList) {
	// idList.remove(readingGas.getMeter().getId());
	// }
	// for (Long tmpLong : idList) {
	// gasList.add(new ReadingGas(gasList.get(0).getReadingDate(), 0.0,
	// meterGas.getById(tmpLong)));
	// }
	// }
	// return gasList;
	// }
	//
	// @Override
	// public List<ReadingWater> waterLatest(Set<Long> idList) {
	// List<ReadingWater> waterList = water.getLatestList(idList);
	// // if this the very first time user creates readings
	// if (waterList.isEmpty()) {
	// for (Long tmpLong : idList) {
	// waterList.add(new ReadingWater(new LocalDate().minusDays(100), 0.0,
	// meterWater.getById(tmpLong)));
	// }
	// } else {
	// // checks if there has been a new meter and adds fake '0' reading
	// for (ReadingWater readingWater : waterList) {
	// idList.remove(readingWater.getMeter().getId());
	// }
	// for (Long tmpLong : idList) {
	// waterList.add(new ReadingWater(waterList.get(0).getReadingDate(), 0.0,
	// meterWater.getById(tmpLong)));
	// }
	// }
	// return waterList;
	//
	// }
	@SuppressWarnings("unchecked")
	@Override
	public <T extends ReadingAbstract> List<T> latestEdit(Media media) {
		switch (media) {
		case ENERGY:

			return (List<T>) energy.getLatestList(energy.getLatestDate());
		case GAS:

			return (List<T>) gas.getLatestList(gas.getLatestDate());
		case WATER:

			return (List<T>) water.getLatestList(water.getLatestDate());

		default:
			break;
		}
		return null;
	}

	@Override
	public List<ReadingEnergy> energyLatestEdit() {

		return energy.getLatestList(energy.getLatestDate());
	}

	@Override
	public List<ReadingGas> gasLatestEdit() {
		return gas.getLatestList(gas.getLatestDate());
	}

	@Override
	public List<ReadingWater> waterLatestEdit() {
		return water.getLatestList(water.getLatestDate());
	}

	
	@Override
	public List <? extends ReadingAbstract> getPreviousReadingEnergy(LocalDate date, Media media) {
		switch (media) {
		case ENERGY:
			return energy.getPrevious(date, meterEnergy.getIdList());

		case GAS:

			return  gas.getPrevious(date, meterGas.getIdList());
		case WATER:

			return  water.getPrevious(date, meterWater.getIdList());

		default:
			break;
		}
		return null;
	}

	@Override
	public List<ReadingEnergy> getPreviousReadingEnergy(LocalDate date, Set<Long> idList) {
		return energy.getPrevious(date, idList);
	}

	@Override
	public List<ReadingGas> getPreviousReadingGas(LocalDate date, Set<Long> idList) {
		return gas.getPrevious(date, idList);
	}

	@Override
	public List<ReadingWater> getPreviousReadingWater(LocalDate date, Set<Long> idList) {
		return water.getPrevious(date, idList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ReadingAbstract> List<T> getByDate(LocalDate date, Media media) {
		switch (media) {
		case ENERGY:
			return (List<T>) energy.getByDate(date);
		case GAS:
			return (List<T>) gas.getByDate(date);
		case WATER:
			return (List<T>) water.getByDate(date);

		default:
			return null;
		}

	}

	// @Override
	// public List<ReadingEnergy> getReadingEnergyByDate(LocalDate date) {
	//
	// return energy.getByDate(date);
	// }
	//
	// @Override
	// public List<ReadingGas> getReadingGasByDate(LocalDate date) {
	// return gas.getByDate(date);
	// }
	//
	// @Override
	// public List<ReadingWater> getReadingWaterByDate(LocalDate date) {
	// return water.getByDate(date);
	// }

	@Override
	public <T extends ReadingAbstract> void save(List<T> reading, LocalDate localDate, Media media) {
		switch (media) {
		case ENERGY:
			for (T t : reading) {
				t.setReadingDate(localDate);
				t.setUnit(t.getMeter().getUnit());
				energy.save((ReadingEnergy) t);
			}
			break;

		case GAS:
			for (T t : reading) {
				t.setReadingDate(localDate);
				t.setUnit(t.getMeter().getUnit());
				gas.save((ReadingGas) t);
			}

			break;
		case WATER:

			for (T t : reading) {
				t.setReadingDate(localDate);
				t.setUnit(t.getMeter().getUnit());
				water.save((ReadingWater) t);
			}
			break;
		default:
			break;
		}

	}

	// @Override
	// public void saveGasList(List<ReadingGas> reading, LocalDate date) {
	// for (ReadingGas i : reading) {
	// i.setReadingDate(date);
	// i.setUnit(i.getMeter().getUnit());
	// gas.save(i);
	// }
	// }
	//
	// @Override
	// public void saveWaterList(List<ReadingWater> reading, LocalDate date) {
	// for (ReadingWater i : reading) {
	// i.setReadingDate(date);
	// i.setUnit(i.getMeter().getUnit());
	// water.save(i);
	// }
	//
	// }
	//
	// @Override
	// public void saveEnergyList(List<ReadingEnergy> reading, LocalDate date) {
	// for (ReadingEnergy readingEnergy : reading) {
	// readingEnergy.setReadingDate(date);
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// energy.save(readingEnergy);
	// }
	//
	// }

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ReadingAbstract> T getById(Long id, Media media) {
		switch (media) {
		case ENERGY:

			return (T) energy.getById(id);
		case GAS:

			return (T) gas.getById(id);
		case WATER:

			return (T) water.getById(id);

		default:
			return null;
		}
	}

	// @Override
	// public ReadingEnergy getEnergyById(Long id) {
	// return energy.getById(id);
	// }
	//
	// @Override
	// public ReadingGas getGasById(Long id) {
	// return gas.getById(id);
	// }
	//
	// @Override
	// public ReadingWater getWaterById(Long id) {
	// return water.getById(id);
	// }

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

	// @Override
	// public <T extends ReadingAbstract> void update(List<T> readings, String
	// date, Media media) {
	// switch (media) {
	// case ENERGY:
	// for (T readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// energy.update((ReadingEnergy) readingEnergy);
	// }
	//
	// break;
	// case GAS:
	// for (T readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// gas.update((ReadingGas) readingEnergy);
	// }
	// break;
	// case WATER:
	// for (T readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// water.update((ReadingWater) readingEnergy);
	// }
	// break;
	// default:
	// break;
	// }
	// }
	@Override
	public <T extends ReadingAbstract> void update(List<T> readings, LocalDate date, Media media) {
		switch (media) {
		case ENERGY:
			for (T readingEnergy : readings) {
				if (readingEnergy.getValue() < 0) {
					throw new IllegalArgumentException();
				}
				readingEnergy.setReadingDate(date);
				readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
				energy.update((ReadingEnergy) readingEnergy);
			}
			break;
		case GAS:
			for (T readingGas : readings) {
				if (readingGas.getValue() < 0) {
					throw new IllegalArgumentException();
				}
				readingGas.setReadingDate(date);
				readingGas.setUnit(readingGas.getMeter().getUnit());
				gas.update((ReadingGas) readingGas);
			}
			break;
		case WATER:
			for (T readingEnergy : readings) {
				if (readingEnergy.getValue() < 0) {
					throw new IllegalArgumentException();
				}
				readingEnergy.setReadingDate(date);
				readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
				water.update((ReadingWater) readingEnergy);
			}
			break;

		default:
			break;
		}
	}

	// @Override
	// public void updateEnergyList(List<ReadingEnergy> readings, String date) {
	// for (ReadingEnergy readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// energy.update(readingEnergy);
	// }
	//
	// }
	//
	// @Override
	// public void updateGasList(List<ReadingGas> readings, String date) {
	// for (ReadingGas readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// gas.update(readingEnergy);
	// }
	//
	// }
	//
	// @Override
	// public void updateWaterList(List<ReadingWater> readings, String date) {
	// for (ReadingWater readingEnergy : readings) {
	// if (readingEnergy.getValue() < 0) {
	// throw new IllegalArgumentException();
	// }
	// readingEnergy.setReadingDate(LocalDate.parse(date));
	// readingEnergy.setUnit(readingEnergy.getMeter().getUnit());
	// water.update(readingEnergy);
	// }
	//
	// }

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

	@Override
	public <T extends ReadingAbstract> void setDates(Map<String, Object> model, List<T> list) {
		model.put("date", new LocalDate());

		if (list.isEmpty()) {
			model.put("oldDate", "2000-01-01");
		} else {
			model.put("oldDate", list.get(0).getReadingDate().plusDays(1));
		}

	}

	// @Override
	// public <F extends ReadingForm> void prepareForm(F form, Media media)
	// throws AbsentMainMeterException {
	//
	// //
	// //
	// // List<ReadingEnergy> readings =
	// // readingService.energyLatestNew(meterService.getIdList(Media.ENERGY));
	// //
	// // model.put("date", new LocalDate());
	// // readingForm.setCurrentReadings(readings);
	// // readingForm.setNewReadings(readings);
	// // if (readings.isEmpty()) {
	// // model.put("oldDate", "2000-01-01");
	// // } else {
	// // model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
	// // }
	//
	// switch (media) {
	// case ENERGY:
	// if (!meterEnergy.ifMainExists()) {
	// throw new AbsentMainMeterException();
	// }
	//
	// List<ReadingEnergy> readings = energyLatestNew(meterEnergy.getIdList());
	// form.setCurrentReadings(readings);
	// form.setNewReadings(readings);
	// if (readings.isEmpty()) {
	// form.setOldDate(LocalDate.parse("2000-01-01"));
	// } else {
	// form.setOldDate(readings.get(0).getReadingDate().plusDays(1));
	// }
	// break;
	//
	// default:
	// break;
	// }
	//
	// }

	// @Override
	// public Set<Long> getEnergyIdList() {
	// return energy.getIdList();
	// }

}
