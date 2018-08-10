package kamienica.feature.residence;

import java.util.List;
import java.util.stream.Collectors;
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

@Service
@Transactional
public class ResidenceService implements IResidenceService {

    private final IResidenceDao residenceDao;
    private final IResidenceOwnershipDao residenceOwnershipDao;
    private final IApartmentDao apartmentDao;
    private final IMeterDao meterDao;
    private final IPurgeService purgeService;

    @Autowired
    public ResidenceService(final IResidenceDao residenceDao, final IResidenceOwnershipDao residenceOwnershipDao,
                            final IApartmentDao apartmentDao, final IMeterDao meterDao, final IPurgeService purgeService) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.apartmentDao = apartmentDao;
        this.meterDao = meterDao;
        this.purgeService = purgeService;
    }

    @Override
    public void save(final Residence residence) {
        final ResidenceOwnership ro = new ResidenceOwnership();
        ro.setResidence(residence);
        ro.setOwner(SecurityDetails.getLoggedTenant());
        residenceOwnershipDao.save(ro);
        SecurityDetails.addNewlyCreatedResidenceToSecurityContext(residence);
        saveEssentialData(residence);
    }

    private void saveEssentialData(final Residence residence) {
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
    public void update(final Residence residence) {
        residenceDao.update(residence);
    }

    @Override
    public List<Residence> getList() {
        return residenceDao.getList();
    }

    @Override
    public List<Residence> listForOwner() {
        final Criterion forOwner = Restrictions.eq("owner", SecurityDetails.getLoggedTenant());
        final List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return owned.stream().map(ResidenceOwnership::getResidence).collect(Collectors.toList());
    }

    @Override
    public List<Residence> listForFirstLogin(final Tenant tenant) {
        final Criterion forOwner = Restrictions.eq("owner", tenant);
        final List<ResidenceOwnership> owned = residenceOwnershipDao.findByCriteria(forOwner);
        return owned.stream().map(ResidenceOwnership::getResidence).collect(Collectors.toList());
    }

    @Override
    public Residence getById(final Long id) {
        return residenceDao.getById(id);
    }

    @Override
    public void delete(final Residence residence) {
        purgeService.purgeData(residence);
    }
}
