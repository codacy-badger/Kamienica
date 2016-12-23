package kamienica.feature.reading;

import kamienica.core.enums.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.feature.meter.MeterDao;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import kamienica.model.InvoiceGas;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

    @Qualifier("readingEnergyDao")
    @Autowired
    private ReadingEnergyDao energy;
    @Autowired
    private ReadingWaterDao water;
    @Autowired
    private ReadingGasDao gas;
    @Autowired
    private MeterDao<MeterEnergy> meterEnergy;
    @Autowired
    private MeterDao<MeterGas> meterGas;
    @Autowired
    private MeterDao<MeterWater> meterWater;

    @Override
    public List<? extends Reading> getList(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getList();
            case GAS:
                return gas.getList();
            case WATER:
                return water.getList();
            default:
                return null;
        }
    }


    /**
     * Method designed for creating new readings. If this is the first input the
     * method will create fake '0' readings for each meter. It will also create
     * 0 reading for every new meter that has been recently added
     *
     * @throws NoMainCounterException
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> List<T> getLatestNew(Media media) throws NoMainCounterException {
        Set<Long> idList;
        LocalDate fakeDate = new LocalDate().minusDays(100);
        switch (media) {
            case ENERGY:
                if (!meterEnergy.ifMainExists()) {
                    throw new NoMainCounterException();
                }
                idList = meterEnergy.getIdListForActiveMeters();
                List<ReadingEnergy> energyList = latestEdit(Media.ENERGY);
                if (energyList.isEmpty()) {
                    for (Long tmpLong : idList) {
                        energyList.add(new ReadingEnergy(fakeDate, 0.0, meterEnergy.getById(tmpLong)));
                    }
                } else {
                    for (ReadingEnergy readingEnergy : energyList) {
                        // consider using LambdaJ
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
                idList = meterGas.getIdListForActiveMeters();
                List<ReadingGas> gasList = latestEdit(Media.GAS);

                // if this the very first time user creates readings
                if (gasList.isEmpty()) {
                    for (Long tmpLong : idList) {
                        gasList.add(new ReadingGas(fakeDate, 0.0, meterGas.getById(tmpLong)));
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
                idList = meterWater.getIdListForActiveMeters();
                List<ReadingWater> waterList = latestEdit(Media.WATER);
                if (waterList.isEmpty()) {
                    for (Long tmpLong : idList) {
                        waterList.add(new ReadingWater(fakeDate, 0.0, meterWater.getById(tmpLong)));
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

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> List<T> latestEdit(Media media) {
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
    public List<? extends Reading> getPreviousReadingEnergy(LocalDate date, Media media) {
        switch (media) {
            case ENERGY:
                return energy.getPrevious(date, meterEnergy.getIdList());

            case GAS:

                return gas.getPrevious(date, meterGas.getIdList());
            case WATER:

                return water.getPrevious(date, meterWater.getIdList());

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
    public List<? extends Reading> getByDate(LocalDate date, Media media) {
        switch (media) {
            case ENERGY:
                return energy.getByDate(date);
            case GAS:
                return gas.getByDate(date);
            case WATER:
                return water.getByDate(date);

            default:
                return null;
        }

    }


    @Override
    public <T extends Reading> void save(List<T> reading, LocalDate localDate, Media media) {
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


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> T getById(Long id, Media media) {
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


    @Override
    public List<?> getUnresolvedReadings(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getUnresolvedReadings();
            case GAS:
                return gas.getUnresolvedReadings();
            case WATER:
                return water.getUnresolvedReadings();
            default:
                return null;
        }

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
    public void deleteList(List<? extends Reading> list, Media media) {
        switch (media) {
            case ENERGY:
                for (Reading reading : list) {
                    energy.deleteById(reading.getId());
                }
                break;
            case GAS:
                for (Reading reading : list) {
                    gas.deleteById(reading.getId());
                }
                break;
            case WATER:
                for (Reading reading : list) {
                    water.deleteById(reading.getId());
                }
                break;
            default:
                break;
        }
    }


    @Override
    public <T extends Reading> void update(List<T> readings, LocalDate date, Media media) {
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
    public <T extends Reading> void setDates(Map<String, Object> model, List<T> list) {
        model.put("date", new LocalDate());

        if (list.isEmpty()) {
            model.put("oldDate", "2000-01-01");
        } else {
            model.put("oldDate", list.get(0).getReadingDate().plusDays(1));
        }

    }


}
