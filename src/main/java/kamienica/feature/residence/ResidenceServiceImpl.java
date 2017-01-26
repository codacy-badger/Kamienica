package kamienica.feature.residence;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.meter.MeterDao;
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
    private final MeterDao<MeterEnergy> energy;
    private final MeterDao<MeterGas> gas;
    private final MeterDao<MeterWater> water;

    @Autowired
    public ResidenceServiceImpl(ResidenceDao residenceDao, ResidenceOwnershipDao residenceOwnershipDao,
                                ApartmentDao apartmentDao, MeterDao<MeterEnergy> energy, MeterDao<MeterGas> gas, MeterDao<MeterWater> water) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.apartmentDao = apartmentDao;
        this.energy = energy;
        this.gas = gas;
        this.water = water;
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
        final MeterWater mw = new MeterWater("Licznik Główny Wody", "", "m3", ap, residence, false);
        final MeterGas mg = new MeterGas("Licznik Główny Gazu", "", "m3", ap, residence, false);
        final MeterEnergy me = new MeterEnergy("Licznik Główny Energii", "", "m3", ap, residence);
        energy.save(me);
        gas.save(mg);
        water.save(mw);
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
    public Residence getById(Long id) {
        return residenceDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        residenceDao.delete(id);
    }
}
