package kamienica.feature.residence;

import java.util.List;
import java.util.stream.Collectors;

import kamienica.feature.residenceownership.ResidenceOwnershipDao;
import kamienica.feature.tenant.TenantDao;
import kamienica.model.ResidenceOwnership;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.enums.UserRole;
import kamienica.feature.user_admin.OwnerUserDataService;
import kamienica.model.Residence;
import kamienica.model.Tenant;

@Service
@Transactional
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    private ResidenceDao residenceDao;
    @Autowired
    private ResidenceOwnershipDao residenceOwnershipDao;
    @Autowired
    private TenantDao tenantDao;

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

    /**
     * Gets list depending on the role user is having
     */

    @Override
    public Residence getById(Long id) {
        return residenceDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        residenceDao.delete(id);
    }

    private boolean isOwner(Tenant t) {
        return t.getRole().equals(UserRole.OWNER);
    }

    private boolean isAdmin(Tenant t) {
        return t.getRole().equals(UserRole.ADMIN);
    }
}
