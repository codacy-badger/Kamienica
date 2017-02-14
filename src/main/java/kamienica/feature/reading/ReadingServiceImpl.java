package kamienica.feature.reading;

import kamienica.core.enums.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.feature.meter.MeterDao;
import kamienica.model.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

    private final ReadingEnergyDao energy;
    private final ReadingWaterDao water;
    private final ReadingGasDao gas;
    private final MeterDao<MeterEnergy> meterEnergy;
    private final MeterDao<MeterGas> meterGas;
    private final MeterDao<MeterWater> meterWater;

    @Autowired
    public ReadingServiceImpl(ReadingEnergyDao energy, ReadingWaterDao water,
                              ReadingGasDao gas, MeterDao<MeterEnergy> meterEnergy,
                              MeterDao<MeterGas> meterGas, MeterDao<MeterWater> meterWater) {
        this.energy = energy;
        this.water = water;
        this.gas = gas;
        this.meterEnergy = meterEnergy;
        this.meterGas = meterGas;
        this.meterWater = meterWater;
    }

    @Override
    public List<? extends Reading> getList(final Residence r, final Media media) {
        switch (media) {
            case ENERGY:
                return energy.getList(r);
            case GAS:
                return gas.getList(r);
            case WATER:
                return water.getList(r);
            default:
                return null;
        }
    }

    @Override
    public List<? extends Reading> getList(final Media media) {
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
     * @throws NoMainCounterException when no main meter for residence
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> List<T> getLatestNew(final Residence r, final Media media) throws NoMainCounterException {
        final LocalDate fakeDate = new LocalDate().minusDays(100);
        switch (media) {
            case ENERGY:
                if (!meterEnergy.ifMainExists()) {
                    throw new NoMainCounterException();
                }
                return latestEnergy(r, fakeDate);
            case GAS:
                if (!meterGas.ifMainExists()) {
                    throw new NoMainCounterException();
                }
                return latestGas(r, fakeDate);

            case WATER:
                if (!meterWater.ifMainExists()) {
                    throw new NoMainCounterException();
                }
                return latestWater(r, fakeDate);

            default:
                return null;
        }

    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> List<T> latestEdit(final Residence r, final Media media) {
        switch (media) {
            case ENERGY:

                return (List<T>) energy.getLatestList(r, energy.getLatestDate(r));
            case GAS:

                return (List<T>) gas.getLatestList(r, gas.getLatestDate(r));
            case WATER:

                return (List<T>) water.getLatestList(r, water.getLatestDate(r));

            default:
                break;
        }
        return null;
    }

    @Override
    public List<ReadingEnergy> energyLatestEdit(final Residence r) {
        return energy.getLatestList(r, energy.getLatestDate(r));
    }

    @Override
    public List<ReadingGas> gasLatestEdit(final Residence r) {
        return gas.getLatestList(r, gas.getLatestDate(r));
    }

    @Override
    public List<ReadingWater> waterLatestEdit(final Residence r) {
        return water.getLatestList(r, water.getLatestDate(r));
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
    public List<? extends Reading> getByDate(final Residence r, final LocalDate date, final Media media) {
        switch (media) {
            case ENERGY:
                return energy.getByDate(r, date);
            case GAS:
                return gas.getByDate(r, date);
            case WATER:
                return water.getByDate(r, date);

            default:
                return null;
        }

    }

    @Override
    public <T extends Reading> void save(List<T> reading, LocalDate localDate, Media media) {
        switch (media) {
            case ENERGY:
                for (T t : reading) {
                    setReading(localDate, t);
                    energy.save((ReadingEnergy) t);
                }
                break;

            case GAS:
                for (T t : reading) {
                    setReading(localDate, t);
                    gas.save((ReadingGas) t);
                }

                break;
            case WATER:

                for (T t : reading) {
                    setReading(localDate, t);
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
    public <T extends Reading> void update(List<T> readings, LocalDate date, Media media) {
        switch (media) {
            case ENERGY:
                for (T r : readings) {
                    setReading(date, r);
                    energy.update((ReadingEnergy) r);
                }
                break;
            case GAS:
                for (T r : readings) {
                    setReading(date, r);
                    gas.update((ReadingGas) r);
                }
                break;
            case WATER:
                for (T r : readings) {
                    setReading(date, r);
                    water.update((ReadingWater) r);
                }
                break;

            default:
                break;
        }
    }

    private <T extends Reading> void setReading(LocalDate date, T r) {
        if (r.getValue() < 0) {
            throw new IllegalArgumentException();
        }
        r.setReadingDate(date);
        r.setUnit(r.getMeter().getUnit());
    }


    @Override
    public void deleteLatestReadings(final Residence r, Media media) {
        switch (media) {
            case ENERGY:

                energy.deleteLatestReadings(energy.getLatestDate(r));

                break;
            case GAS:

                gas.deleteLatestReadings(gas.getLatestDate(r));

                break;
            case WATER:

                water.deleteLatestReadings(water.getLatestDate(r));

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


    private <T extends Reading> List<T> latestWater(final Residence r, final LocalDate fakeDate) {
        Set<Long> idList = meterWater.getIdListForActiveMeters(r);
        List<ReadingWater> waterList = latestEdit(r, Media.WATER);
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
    }

    private <T extends Reading> List<T> latestGas(final Residence r, final LocalDate fakeDate) {
        Set<Long> idList = meterGas.getIdListForActiveMeters(r);
        List<ReadingGas> gasList = latestEdit(r, Media.GAS);

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
    }

    private <T extends Reading> List<T> latestEnergy(final Residence r, final LocalDate fakeDate) {
        Set<Long> idList = meterEnergy.getIdListForActiveMeters(r);
        List<ReadingEnergy> energyList = latestEdit(r, Media.ENERGY);
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
    }
}
