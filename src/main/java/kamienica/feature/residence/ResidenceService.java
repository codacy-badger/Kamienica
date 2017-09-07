package kamienica.feature.residence;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.meter.IMeterDao;
import kamienica.feature.residenceownership.IResidenceOwnershipDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.entity.ResidenceOwnership;
import kamienica.model.entity.Tenant;
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
public class ResidenceService implements IResidenceService {

    private final IResidenceDao residenceDao;
    private final IResidenceOwnershipDao residenceOwnershipDao;
    private final IApartmentDao apartmentDao;
    private final IMeterDao meterDao;
    private final IPurgeService purgeService;

    @Autowired
    public ResidenceService(IResidenceDao residenceDao, IResidenceOwnershipDao residenceOwnershipDao,
                            IApartmentDao apartmentDao, IMeterDao meterDao, IPurgeService purgeService) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.apartmentDao = apartmentDao;
        this.meterDao = meterDao;
        this.purgeService = purgeService;
    }

    @Override
    public void save(final Residence residence) {
        ResidenceOwnership ro = new ResidenceOwnership();
        ro.setResidence(residence);
        ro.setOwner(SecurityDetails.getLoggedTenant());
        residenceDao.save(residence);
        residenceOwnershipDao.save(ro);

        saveEssentialData(residence);
    }

    private void saveEssentialData(Residence residence) {
        final Apartment ap = new Apartment(residence, 0, "0000", "Część Wpólna");
        apartmentDao.save(ap);
        final Meter mw = new Meter("Licznik Główny Wody", residence.toString() + "GW", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.WATER);
        final Meter mg = new Meter("Licznik Główny Gazu", residence.toString() + "GG", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.GAS);
        final Meter me = new Meter("Licznik Główny Energii", residence.toString() + "GE", "m3", ap, residence, true, Status.ACTIVE, false, false, Media.ENERGY);
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
        return owned.stream().map(ResidenceOwnership::getResidence).collect(Collectors.toList());
    }

    @Override
    public List<Residence> listForFirstLogin(Tenant tenant) {
        Criterion forOwner = Restrictions.eq("owner", tenant);
        List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return owned.stream().map(ResidenceOwnership::getResidence).collect(Collectors.toList());
    }

    @Override
    public Residence getById(Long id) {
        return residenceDao.getById(id);
    }

    @Override
    public void delete(Residence residence) {
        purgeService.purgeData(residence);
    }
}
