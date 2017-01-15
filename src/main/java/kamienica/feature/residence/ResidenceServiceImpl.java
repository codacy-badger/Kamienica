package kamienica.feature.residence;

import java.util.List;

import kamienica.feature.tenant.TenantDao;
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

    @Override
    public void save(Residence residence) {
        residenceDao.save(residence);
    }


    @Override
    public void update(Residence residence) {
        residenceDao.update(residence);
    }

    @Override
    public List<Residence> getList() {
        return residenceDao.getList();
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
