package kamienica.feature.reading;

import kamienica.feature.meter.IMeterDao;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.exception.NoMainCounterException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class ReadingService implements IReadingService {

    private final IReadingDao readingDao;
    private final IMeterDao meterDao;

    @Autowired
    public ReadingService(IReadingDao readingDao, IMeterDao meterDao) {
        this.readingDao = readingDao;
        this.meterDao = meterDao;
    }

    @Override
    public List<Reading> getList(final Residence r, final Media media) {
        return readingDao.getList(r, media);
    }

    /**
     * Method designed for creating new readings. If this is the first input the
     * method will create fake '0' readings for each meter. It will also create
     * 0 reading for every new meter that has been recently added
     *
     * @throws NoMainCounterException when no main meter for residence
     */
    @Override
    public List<Reading> getLatestNew(final Residence r, final Media media) throws NoMainCounterException {
        if (!meterDao.ifMainExists()) {
            throw new NoMainCounterException();
        }
        return latestReadings(r, media);
    }

    @Override
    public List<Reading> latestEdit(final Residence r, final Media media) {
        return readingDao.getLatestList(r, readingDao.getLatestDate(r, media));
    }

    @Override
    public List<Reading> getPreviousReading(LocalDate date, Set<Long> idList) {
        return readingDao.getPrevious(date, idList);
    }

    @Override
    public List<Reading> getByDate(final Residence r, final LocalDate date, final Media media) {
        return readingDao.getByDate(r, date);
    }

    @Override
    public void save(List<Reading> reading, LocalDate localDate) {
        for (Reading r : reading) {
            setReading(localDate, r);
            readingDao.save(r);
        }
    }

    @Override
    public Reading getById(Long id) {
        return readingDao.getById(id);
    }

    @Override
    public List<Reading> getUnresolvedReadings(final Media media, final Residence residence) {
        return readingDao.getUnresolvedReadings(media, residence);

    }

    @Override
    public void update(List<Reading> readings, LocalDate date) {
        for (Reading r : readings) {
            setReading(date, r);
            readingDao.update(r);
        }
    }

    private void setReading(LocalDate date, Reading r) {
        if (r.getValue() < 0) {
            throw new IllegalArgumentException();
        }
        r.getReadingDetails().setReadingDate(date);
    }

    @Override
    public void deleteLatestReadings(final Residence r, Media media) {
        readingDao.deleteLatestReadings(readingDao.getLatestDate(r, media));
    }

    @Override
    public void setDates(Map<String, Object> model, List<Reading> list) {
        model.put("date", new LocalDate());

        if (list.isEmpty()) {
            model.put("oldDate", "2000-01-01");
        } else {
            model.put("oldDate", list.get(0).getReadingDetails().getReadingDate().plusDays(1));
        }

    }

    private List<Reading> latestReadings(final Residence r, final Media m) {
        //TODO refactor this method after merging is done (issue #100)
        final LocalDate fakeDate = new LocalDate().minusDays(100);
        final Set<Long> idList = meterDao.getIdListForActiveMeters(r);
        final List<Reading> readings = latestEdit(r, m);
        // if this the very first time user creates readings
        if (readings.isEmpty()) {
            for (Long tmpLong : idList) {
                final ReadingDetails rd = new ReadingDetails(fakeDate, Resolvement.UNRESOLVED, Media.ENERGY);
                final Reading reading = new Reading(rd, 0, r, meterDao.getById(tmpLong));
                readings.add(reading);
            }
        } else {
            for (Reading reading : readings) {
                // consider using LambdaJ
                idList.remove(reading.getMeter().getId());
            }
            for (Long tmpLong : idList) {
                final ReadingDetails rd = readings.get(0).getReadingDetails();
                final Reading reading = new Reading(rd, 0.0, r, meterDao.getById(tmpLong));
                readings.add(reading);
            }
        }
        return readings;
    }
}
