package kamienica.feature.reading;

import kamienica.feature.meter.IMeterDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.enums.Status;
import kamienica.model.exception.NoMainCounterException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ReadingService implements IReadingService {

    private final IReadingDao readingDao;
    private final IMeterDao meterDao;
    private final IReadingDetailsDao readingDetailsDao;

    @Autowired
    public ReadingService(IReadingDao readingDao, IMeterDao meterDao, IReadingDetailsDao readingDetailsDao) {
        this.readingDao = readingDao;
        this.meterDao = meterDao;
        this.readingDetailsDao = readingDetailsDao;
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
    @Deprecated
    public List<Reading> getLatestNew(final Residence r, final Media media) throws NoMainCounterException {
        if (!meterDao.ifMainExists()) {
            throw new NoMainCounterException("Missing main meter");
        }
        //TODO refactor this method after merging is done (issue #100)
        final LocalDate fakeDate = new LocalDate().minusDays(100);
        final Set<Meter> meters = new HashSet<>(meterDao.findByCriteria(Restrictions.eq("residence", r), Restrictions.eq("media", media), Restrictions.eq("status", Status.ACTIVE)));
        final List<Reading> readings = latestEdit(r, media);
        final ReadingDetails rd = new ReadingDetails(fakeDate, Resolvement.UNRESOLVED, Media.ENERGY);
        // if this the very first time user creates readings
        if (readings.isEmpty()) {
            for (Meter m : meters) {
                final Reading reading = new Reading(rd, 0, r, m);
                readings.add(reading);
            }
        } else {
            //two situations -> newly added meter, or a meter that has been deactivated
            for (int i = 0; i < readings.size(); i++) {
                final Reading reading = readings.get(i);
                if (reading.getMeter().getStatus().equals(Status.INACTIVE)) {
                    readings.remove(i--);
                }
                meters.remove(reading.getMeter());
            }
            for (Meter m : meters) {
                final Reading reading = new Reading(rd, 0.0, r, m);
                readings.add(reading);
            }
        }
        return readings;
    }

    @Override
    public List<Reading> latestEdit(final Residence r, final Media media) {
        final ReadingDetails details = readingDetailsDao.getLatest(r, media);
        Criterion c1 = Restrictions.eq("residence", r);
        Criterion c2 = Restrictions.eq("readingDetails", details);
        return readingDao.findByCriteria(c1, c2);
    }


    @Override
    public List<Reading> getPreviousReading(LocalDate date, List<Meter> meters) {
        //TODO very bas solution but this method gets kicked soon anyway so no point of refactoring it
        final Media m = meters.get(0).getMedia();
        List<ReadingDetails> details = readingDetailsDao.findByCriteria(Order.desc("readingDate"), Restrictions.lt("readingDate", date), Restrictions.eq("media", m));
        if (details.isEmpty()) {
            return new ArrayList<>();
        } else {
            return readingDao.findByCriteria(Restrictions.in("meter", meters), Restrictions.eq("readingDetails", details.get(0)));
        }
        //return readingDao.getPrevious(details.get(0), meters);
    }

    @Override
    public List<Reading> getByDate(final Residence r, final LocalDate date, final Media media) {
        final ReadingDetails rd = readingDetailsDao.findOneByCriteria(Restrictions.eq("readingDate", date), Restrictions.eq("media", media), Restrictions.eq("residence", r));
        return readingDao.findByCriteria(Restrictions.eq("readingDetails", rd), Restrictions.eq("residence", r));
    }

    @Override
    public void save(final List<Reading> reading, final ReadingDetails details) {
        readingDetailsDao.save(details);
        for (Reading r : reading) {
            validateReadingValue(r);
            r.setReadingDetails(details);
            readingDao.save(r);
        }
    }

    @Override
    public Reading getById(Long id) {
        return readingDao.getById(id);
    }


    @Override
    public void update(List<Reading> readings, final LocalDate date) {
        final ReadingDetails details = readingDetailsDao.getById(readings.get(0).getReadingDetails().getId());
        details.setReadingDate(date);
        readingDetailsDao.update(details);
        for (Reading r : readings) {
            validateReadingValue(r);
            r.setReadingDetails(details);
            readingDao.update(r);
        }
    }

    private void validateReadingValue(final Reading r) {
        if (r.getValue() < 0) {
            throw new IllegalArgumentException("Reading value cannot be below zero");
        }
    }

    @Override
    public void deleteLatestReadings(final Residence r, final Media media) {
        final ReadingDetails details = readingDetailsDao.getLatest(r, media);
        final Criterion c = Restrictions.eq("readingDetails", details);
        List<Reading> readingsToDelete = readingDao.findByCriteria(c);
        for (Reading reading : readingsToDelete) readingDao.delete(reading);
    }
}
