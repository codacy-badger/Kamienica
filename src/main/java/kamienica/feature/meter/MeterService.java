package kamienica.feature.meter;

import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
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
    public void save(Meter meter, Media media) {
        //TODO think on a better way to do this...
        meter.setResidence(meter.getApartment().getResidence());
        //TODO this check shoudl be removed in #138
        if (meter.getApartment() == null) {
            meter.setMain(true);
        }
        meterDao.save(meter);
    }

    @Override
    public void update(Meter meter, Media media) {
        meter.setMain(meter.getApartment() == null);

        meterDao.update(meter);

    }

    @Override
    public List<Meter> getListForOwner(final Media media) {
        List<Residence> residences = SecurityDetails.getResidencesForOwner();
        Criterion c = Restrictions.in("residence", residences);

        return meterDao.findByCriteria(c);

    }

    @Override
    public Meter getById(Long id, Media media) {
        return meterDao.getById(id);
    }

    @Override
    public void delete(Long id, Media media) {
        try {
            meterDao.deleteById(id);
        } catch (ConstraintViolationException e) {
            Meter meter = meterDao.getById(id);
            meter.setStatus(Status.INACTIVE);
            meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
            meterDao.update(meter);
        }
    }

    @Override
    public List<Meter> list(Media media) {
        return meterDao.getList();
    }

    @Override
    public Set<Long> getIdList(Media media) {
        return meterDao.getIdList();
    }

    @Override
    public Set<Long> getIdListForActiveMeters(final Residence r, final Media media) {
        return meterDao.getIdListForActiveMeters(r);
    }

    @Override
    public boolean ifMainExists(Media media) {
        return meterDao.ifMainExists();
    }
}
