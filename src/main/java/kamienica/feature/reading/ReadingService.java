package kamienica.feature.reading;

import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.criterion.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ReadingService implements IReadingService {

    private final IReadingDao readingDao;
    private final IReadingDetailsDao readingDetailsDao;

    @Autowired
    public ReadingService(IReadingDao readingDao, IReadingDetailsDao readingDetailsDao) {
        this.readingDao = readingDao;
        this.readingDetailsDao = readingDetailsDao;
    }

    @Override
    public List<Reading> getList(final Residence r, final Media media) {
        return readingDao.getList(r, media);
    }


    @Override
    public List<Reading> latestEdit(final Residence r, final Media media) {
        final ReadingDetails details = readingDetailsDao.getLatest(r, media);
        Criterion c1 = Restrictions.eq("residence", r);
        Criterion c2 = Restrictions.eq("readingDetails", details);
        return readingDao.findByCriteria(c1, c2);
    }

    @Override
    public List<Reading> getForInvoice(final Invoice invoice) {
        final ReadingDetails rd = invoice.getReadingDetails();
        return readingDao.findByCriteria(Restrictions.eq("readingDetails", rd));
    }



    @Override
    public void save(List<Reading> readings) {
        final ReadingDetails details = readings.get(0).getReadingDetails();
        readingDetailsDao.save(details);
        for (final Reading r : readings) {
            validateReadingValue(r);
            r.setReadingDetails(details);
            readingDao.save(r);
        }
    }

    @Override
    public void save(ReadingForm readingForm) {
        final ReadingDetails details = readingForm.getReadings().get(0).getReadingDetails();
        readingDetailsDao.save(details);
        final List<Reading> readings = readingForm.getReadings();
        for (final Reading r : readings) {
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
    public void update(ReadingForm readingForm) {
        final ReadingDetails details = readingForm.getReadings().get(0).getReadingDetails();
        readingDetailsDao.update(details);
        final List<Reading> readings = readingForm.getReadings();
        for (final Reading r : readings) {
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
    public List<Reading> getPreviousReadingForWarmWater(final Invoice invoice) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq("residence", invoice.getResidence()));
        detachedCriteria.add(Restrictions.eq("media", invoice.getMedia()));
        detachedCriteria.add(Restrictions.lt("readingDate", invoice.getReadingDetails().getReadingDate()));
        detachedCriteria.setProjection(Projections.max("readingDate"));

        final Criterion one = Restrictions.eq("residence", invoice.getResidence());
        final Criterion two = Restrictions.eq("media", invoice.getMedia());
        final Criterion three = Property.forName("readingDate").eq(detachedCriteria);
        final ReadingDetails rd = readingDetailsDao.findOneByCriteria(one, two, three);
        return readingDao.findByCriteria(Restrictions.eq("readingDetails", rd));
    }

    @Override
    public List<Reading> getPreviousReadingForWarmWater(final Residence r, final Media m, final LocalDate date) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReadingDetails.class);
        detachedCriteria.add(Restrictions.eq("residence", r));
        detachedCriteria.add(Restrictions.eq("media", m));
        detachedCriteria.add(Restrictions.lt("readingDate", date));
        detachedCriteria.setProjection(Projections.max("readingDate"));

        final Criterion one = Restrictions.eq("residence", r);
        final Criterion two = Restrictions.eq("media", m);
        final Criterion three = Property.forName("readingDate").eq(detachedCriteria);
        final ReadingDetails rd = readingDetailsDao.findOneByCriteria(one, two, three);

        return readingDao.findByCriteria(Restrictions.eq("readingDetails", rd));
    }
}
