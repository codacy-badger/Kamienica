package kamienica.feature.residenceownership;

import kamienica.model.enums.UserRole;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.ResidenceOwnership;
import kamienica.model.entity.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResidenceOwnershipService implements IResidenceOwnershipService {

    private final IResidenceOwnershipDao residenceOwnershipDao;

    @Autowired
    public ResidenceOwnershipService(IResidenceOwnershipDao residenceOwnershipDao) {
        this.residenceOwnershipDao = residenceOwnershipDao;
    }

    @Override
    public List<ResidenceOwnership> list() {
        final Tenant t = SecurityDetails.getLoggedTenant();
        if (t.getRole().equals(UserRole.OWNER)) {
            Criterion forOwner = Restrictions.eq("owner", t);
            return residenceOwnershipDao.findByCriteria(forOwner);
        } else {
            return residenceOwnershipDao.getList();
        }
    }

    @Override
    public void delete(Long id) {
        residenceOwnershipDao.delete(id);
    }

    @Override
    public void save(final ResidenceOwnership residenceOwnership, final Tenant tenant) {

        residenceOwnershipDao.save(residenceOwnership);
    }

    @Override
    public void update(final ResidenceOwnership residenceOwnership) {
        residenceOwnershipDao.update(residenceOwnership);
    }
}
