package kamienica.feature.residence;

import kamienica.core.enums.Media;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.meter.MeterService;
import kamienica.feature.residenceownership.ResidenceOwnershipDao;
import kamienica.model.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResidenceServiceImpl implements ResidenceService {

    private final ResidenceDao residenceDao;
    private final ResidenceOwnershipDao residenceOwnershipDao;
    private final ApartmentDao apartmentDao;

    @Autowired
    public ResidenceServiceImpl(ResidenceDao residenceDao, ResidenceOwnershipDao residenceOwnershipDao,
                                ApartmentDao apartmentDao) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public void save(final Residence residence, final Tenant t) {
        ResidenceOwnership ro = new ResidenceOwnership();
        ro.setResidenceOwned(residence);
        ro.setOwner(t);
        residenceDao.save(residence);
        residenceOwnershipDao.save(ro);

        final Apartment ap = new Apartment(residence, 0, "0000", "Część Wpólna");
        apartmentDao.save(ap);
    }

    @Override
    public void update(Residence residence) {
        residenceDao.update(residence);
    }

    @Override
    public List<Residence> getList() {
        return residenceDao.getList();
    }

    @Override
    public List<Residence> listForOwner(Tenant t) {
        Criterion forOwner = Restrictions.eq("owner", t);
        List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return  owned.stream().map(x -> x.getResidenceOwned()).collect(Collectors.toList());
    }

    @Override
    public Residence getById(Long id) {
        return residenceDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        residenceDao.delete(id);
    }
}
