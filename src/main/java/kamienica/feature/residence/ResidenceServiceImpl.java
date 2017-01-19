package kamienica.feature.residence;

import kamienica.feature.residenceownership.ResidenceOwnershipDao;
import kamienica.feature.tenant.TenantDao;
import kamienica.model.Residence;
import kamienica.model.ResidenceOwnership;
import kamienica.model.Tenant;
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
    private final TenantDao tenantDao;

    @Autowired
    public ResidenceServiceImpl(ResidenceDao residenceDao, ResidenceOwnershipDao residenceOwnershipDao, TenantDao tenantDao) {
        this.residenceDao = residenceDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.tenantDao = tenantDao;
    }

    @Override
    public void save(Residence residence, Tenant t) {
        ResidenceOwnership ro = new ResidenceOwnership();
        ro.setResidenceOwned(residence);
        ro.setOwner(t);
        residenceDao.save(residence);
        residenceOwnershipDao.save(ro);
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
