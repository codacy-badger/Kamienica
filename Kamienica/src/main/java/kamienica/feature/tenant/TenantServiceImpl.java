package kamienica.feature.tenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.enums.Status;
import kamienica.feature.settings.SettingsDao;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

	@Autowired
	TenantDao tenantDao;
	@Autowired
	private SettingsDao settingsDao;

	@Override
	public void saveTenant(Tenant newTenant) {
		Tenant currentTenant = tenantDao.getTenantForApartment(newTenant.getApartment());
		if (currentTenant == null) {
			tenantDao.save(newTenant);
		} else if (currentTenant.getMovementDate().isAfter(newTenant.getMovementDate())) {
			newTenant.setStatus(Status.INACTIVE.getStatus());
			tenantDao.save(newTenant);
		} else {
			tenantDao.deactivateByApparmentId(newTenant.getApartment().getId());
			tenantDao.save(newTenant);
		}
		settingsDao.changeDivisionState(false);
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
	public List<Tenant> getCurrentTenants() {
		return tenantDao.getActiveTenants();
	}

	@Override
	public Tenant loadByMail(String mail) {
		return tenantDao.loadByMail(mail);
	}

}
