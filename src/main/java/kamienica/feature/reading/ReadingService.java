package kamienica.feature.reading;

import kamienica.feature.meter.IMeterDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.*;
import kamienica.model.entity.ReadingForm;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.enums.Status;
import kamienica.model.exception.NoMainCounterException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public List<Reading> getList(final Media media) {
        return readingDao.getList(media);
    }

    @Override
    public Map<ReadingDetails, List<Reading>> list(final Residence r, final Media media) {
        final Criterion res = Restrictions.eq("residence", r);
        final Criterion med = Restrictions.eq("media", media);
        List<ReadingDetails> readingDetails = readingDetailsDao.findByCriteria(res, med);

        final Map<ReadingDetails, List<Reading>> result = new TreeMap<>();

        for (ReadingDetails details : readingDetails) {
            final List<Reading> readings = readingDao.list(details);
            result.put(details, readings);
        }
        return result;
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
    public List<Reading> getForInvoice(final Invoice invoice) {
        final ReadingDetails rd = invoice.getReadingDetails();
        final DetachedCriteria activeMeters = DetachedCriteria.forClass(Meter.class);
        activeMeters.add(Restrictions.eq("status", Status.ACTIVE));
        Criterion c = Restrictions.eq()
        return readingDao.findByCriteria(Restrictions.eq("readingDetails", rd), activeMeters);
    }

    @Override
    public EnumMap<ReadingAge, List<Reading>> readingsForPayment(Invoice invoice) {
        return null;
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
    public void save(ReadingForm readingForm) {
        final ReadingDetails details = readingForm.getReadingDetails();
        readingDetailsDao.save(details);
        final Set<Reading> readings = readingForm.getReadings();
        for (Reading r : readings) {
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

    @Override
    public void update(ReadingForm readingForm) {
        final ReadingDetails details = readingForm.getReadingDetails();
        readingDetailsDao.update(details);
        final Set<Reading> readings = readingForm.getReadings();
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
        readingDetailsDao.delete(details);
    }

    @Override
    public void delete(ReadingForm readingForm) {
        for(Reading r:readingForm.getReadings()) {
            readingDao.delete(r);
        }
        readingDetailsDao.delete(readingForm.getReadingDetails());
    }
}
