package kamienica.feature.residence;

import java.util.List;

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
    private ResidenceDao dao;

    @Override
    public void save(Residence residence) {
            dao.save(residence);
    }


    @Override
    public void update(Residence residence) {
        dao.update(residence);
    }

    @Override
    public List<Residence> getList() {
        return dao.getList();
    }

    /**
     * Gets list depending on the role user is having
     */

    @Override
    public Residence getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    private boolean isOwner(Tenant t) {
        return t.getRole().equals(UserRole.OWNER);
    }

    private boolean isAdmin(Tenant t) {
        return t.getRole().equals(UserRole.ADMIN);
    }
}
