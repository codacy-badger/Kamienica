package kamienica.feature.meter;

import kamienica.core.enums.Media;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.*;
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
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterDao<MeterEnergy> energy;

    @Autowired
    private MeterDao<MeterGas> gas;

    @Autowired
    private MeterDao<MeterWater> water;

    @Autowired
    private ResidenceService residenceService;
    @Autowired
    private ApartmentDao apartmentDao;

    @Override
    public <T extends Meter> void save(T meter, Media media) {
        //TODO think on a better way to do this...
        meter.setResidence(meter.getApartment().getResidence());
        //TODO this check shoudl be removed in #138
        if (meter.getApartment() == null) {
            meter.setMain(true);
        }
        switch (media) {
            case ENERGY:

                energy.save((MeterEnergy) meter);
                break;
            case GAS:

                gas.save((MeterGas) meter);
                break;
            case WATER:

                water.save((MeterWater) meter);
                break;
            default:
                break;
        }

    }

    @Override
    public <T extends Meter> void update(T meter, Media media) {
        meter.setMain(meter.getApartment() == null);
        switch (media) {
            case ENERGY:

                energy.update((MeterEnergy) meter);
                break;
            case GAS:

                gas.update((MeterGas) meter);
                break;
            case WATER:

                water.update((MeterWater) meter);
                break;
            default:
                break;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Meter> List<T> getListForOwner(final Media media, final Tenant t) {
        List<Residence> residences = residenceService.listForOwner(t);
        Criterion c = Restrictions.in("residence", residences);
        switch (media) {
            case ENERGY:

                return (List<T>) energy.findByCriteria(c);

            case GAS:

                return (List<T>) gas.findByCriteria(c);

            case WATER:

                return (List<T>) water.findByCriteria(c);

            default:
                return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Meter> T getById(Long id, Media media) {
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
    public void delete(Long id, Media media) {
        switch (media) {
            case ENERGY:

                try {
                    energy.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterEnergy meter = energy.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    energy.update(meter);
                }

                break;
            case GAS:
                try {
                    gas.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterGas meter = gas.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    gas.update(meter);
                }
                break;
            case WATER:
                try {
                    water.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterWater meter = water.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    water.update(meter);
                }
                break;
            default:
                break;
        }

    }


    @Override
    public Set<Long> getIdList(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getIdList();
            case WATER:
                return water.getIdList();
            case GAS:
                return gas.getIdList();

            default:
                return null;
        }
    }

    @Override
    public Set<Long> getIdListForActiveMeters(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getIdListForActiveMeters();
            case WATER:
                return water.getIdListForActiveMeters();
            case GAS:
                return gas.getIdListForActiveMeters();

            default:
                return null;
        }
    }

    @Override
    public boolean ifMainExists(Media media) {
        switch (media) {
            case ENERGY:

                return energy.ifMainExists();

            case GAS:

                return gas.ifMainExists();

            case WATER:

                return water.ifMainExists();

            default:
                return false;
        }
    }

}
