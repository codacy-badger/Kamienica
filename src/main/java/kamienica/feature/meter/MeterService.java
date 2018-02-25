package kamienica.feature.meter;

import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MeterService implements IMeterService {

    private final IMeterDao meterDao;

    @Autowired
    public MeterService(IMeterDao meterDao) {
        this.meterDao = meterDao;
    }

    @Override
    public void save(Meter meter) {
       // meter.setResidence(meter.getApartment().getResidence());
        meterDao.save(meter);
    }

    @Override
    public void update(Meter meter) {
        meter.setMain(meter.getApartment() == null);
        meterDao.update(meter);
    }

    @Override
    public List<Meter> getListForOwner(final Media media) {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        final Criterion c1 = Restrictions.in("residence", residences);
        final Criterion c2 = Restrictions.eq("media", media);
        return meterDao.findByCriteria(c1, c2);
    }

    @Override
    public Meter getById(Long id) {
        return meterDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        try {
            meterDao.delete(id);
        } catch (ConstraintViolationException e) {
            Meter meter = meterDao.getById(id);
            meter.setStatus(Status.INACTIVE);
            meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
            meterDao.update(meter);
        }
    }

    @Override
    public void delete(Meter meter) {
        meterDao.delete(meter);
    }

    @Override
    public List<Meter> list(Media media) {
        return meterDao.getList(media);
    }

    @Override
    public List<Meter> list(final Residence r, final Media media) {
        Criterion c1 = Restrictions.eq("residence", r);
        Criterion c2 = Restrictions.eq("media", media);
        Criterion c3 = Restrictions.eq("status", Status.ACTIVE);
        return meterDao.findByCriteria(c1, c2, c3);
    }

    @Override
    public Set<Long> getIdListForActiveMeters(final Residence r, final Media media) {
        return meterDao.getIdListForActiveMeters(r, media);
    }

    @Override
    public boolean ifMainExists(Media media) {
        return meterDao.ifMainExists();
    }
}
