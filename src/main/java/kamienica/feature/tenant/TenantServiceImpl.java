package kamienica.feature.tenant;

import kamienica.core.enums.Status;
import kamienica.feature.settings.SettingsDao;
import kamienica.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private SettingsDao settingsDao;

    @Override
    public void saveTenant(Tenant newTenant) {
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
    public List<Tenant> getList() {
        return tenantDao.getList();
    }

    @Override
    public void deleteTenant(Long id) {
        tenantDao.deleteById(id);
        settingsDao.changeDivisionState(false);
    }

    @Override
    public void updateTenant(Tenant tenant) {
        tenantDao.update(tenant);

    }

    @Override
    public Tenant getTenantById(Long id) {
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

}
