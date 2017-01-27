package kamienica.feature.tenant;

import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;
import kamienica.feature.settings.SettingsDao;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantDao tenantDao;
    private final SettingsDao settingsDao;

    @Autowired
    public TenantServiceImpl(TenantDao tenantDao, SettingsDao settingsDao) {
        this.tenantDao = tenantDao;
        this.settingsDao = settingsDao;
    }

    @Override
    public void save(Tenant newTenant) {
        Tenant currentTenant = tenantDao.getTenantForApartment(newTenant.getApartment());
        if (currentTenant == null) {
            tenantDao.save(newTenant);
            settingsDao.changeDivisionState(false);
        } else compareMovementDatesAndPersist(newTenant, currentTenant);
    }

    private void compareMovementDatesAndPersist(Tenant newTenant, Tenant currentTenant) {
        if (currentTenant.getMovementDate().isAfter(newTenant.getMovementDate())) {
            newTenant.setStatus(Status.INACTIVE);
            tenantDao.save(newTenant);
        } else {
            currentTenant.setStatus(Status.INACTIVE);
            tenantDao.save(currentTenant);
            tenantDao.save(newTenant);
            settingsDao.changeDivisionState(false);
        }
    }


    @Override
    public List<Tenant> list() {
        return tenantDao.getList();
    }

    @Override
    public List<Tenant> listForOwner() {
        return null;
    }

    @Override
    public List<Tenant> listForTenant() {
        return null;
    }

    @Override
    public List<Tenant> findByCriteria(Criterion... crit) {
        return tenantDao.findByCriteria(crit);
    }

    @Override
    public void deleteById(Long id) {
        tenantDao.deleteById(id);
        settingsDao.changeDivisionState(false);
    }

    @Override
    public void update(Tenant tenant) {
        tenantDao.update(tenant);

    }

    @Override
    public Tenant getById(Long id) {
        return tenantDao.getById(id);
    }

    @Override
    public List<Tenant> getActiveTenants() {
        return tenantDao.getActiveTenants();
    }

    @Override
    public Tenant loadByMail(String mail) {
        return tenantDao.loadByMail(mail);
    }

    @Override
    public List<Tenant> getOwners() {
        Criterion onlyOwners = Restrictions.eq("role", UserRole.OWNER);
        return tenantDao.findByCriteria(onlyOwners);
    }

}
