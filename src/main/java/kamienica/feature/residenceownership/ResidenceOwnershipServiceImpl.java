package kamienica.feature.residenceownership;

import kamienica.core.enums.UserRole;
import kamienica.model.ResidenceOwnership;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResidenceOwnershipServiceImpl implements ResidenceOwnershipService {

    @Autowired
    ResidenceOwnershipDao dao;

    @Override
    public List<ResidenceOwnership> list(final Tenant t) {
        if (t.getRole().equals(UserRole.OWNER)) {
            Criterion forOwner = Restrictions.eq("owner", t);
            return dao.findByCriteria(forOwner);
        } else {
            return dao.getList();
        }
    }


    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void save(final ResidenceOwnership residenceOwnership) {
        dao.save(residenceOwnership);
    }

    @Override
    public void update(final ResidenceOwnership residenceOwnership) {
        dao.update(residenceOwnership);
    }
}
