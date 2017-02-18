package kamienica.feature.residence;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.meter.IMeterDao;
import kamienica.feature.residenceownership.IResidenceOwnershipDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
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
    private final IResidenceOwnershipDao residenceOwnershipDao;
    private final IApartmentDao apartmentDao;
    private final IMeterDao meterDao;

    @Autowired
    public ResidenceServiceImpl(ResidenceDao residenceDao, IResidenceOwnershipDao residenceOwnershipDao,
                                IApartmentDao apartmentDao, IMeterDao meterDao) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.apartmentDao = apartmentDao;
        this.meterDao = meterDao;
    }

    @Override
    public void save(final Residence residence) {
        ResidenceOwnership ro = new ResidenceOwnership();
        ro.setResidenceOwned(residence);
        ro.setOwner(SecurityDetails.getLoggedTenant());
        residenceDao.save(residence);
        residenceOwnershipDao.save(ro);

        saveEssentialData(residence);
    }

    //TODO enabling this fails to save any data in tests
    private void saveEssentialData(Residence residence) {
        final Apartment ap = new Apartment(residence, 0, "0000", "Część Wpólna");
        apartmentDao.save(ap);
        final Meter mw = new Meter("Licznik Główny Wody", "N/A", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.WATER);
        final Meter mg = new Meter("Licznik Główny Gazu", "N/A", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.GAS);
        final Meter me = new Meter("Licznik Główny Energii", "N/A", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.ENERGY);
        meterDao.save(me);
        meterDao.save(mg);
        meterDao.save(mw);
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
    public List<Residence> listForOwner() {
        Criterion forOwner = Restrictions.eq("owner", SecurityDetails.getLoggedTenant());
        List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return owned.stream().map(ResidenceOwnership::getResidenceOwned).collect(Collectors.toList());
    }

    @Override
    public List<Residence> listForFirstLogin(Tenant tenant) {
        Criterion forOwner = Restrictions.eq("owner", tenant);
        List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return owned.stream().map(ResidenceOwnership::getResidenceOwned).collect(Collectors.toList());
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
