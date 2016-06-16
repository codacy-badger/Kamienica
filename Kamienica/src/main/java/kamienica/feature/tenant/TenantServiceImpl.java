package kamienica.feature.tenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

	@Autowired
	TenantDao tenantDao;

	@Override
	public void saveTenant(Tenant newTenant) {
		Tenant currentTenant = tenantDao.getTenantForApartment(newTenant.getApartment());
		if (currentTenant.getMovementDate().isAfter(newTenant.getMovementDate())) {
			newTenant.setStatus(UserStatus.INACTIVE.getUserStatus());
			tenantDao.save(newTenant);
		} else {
			tenantDao.deactivateByApparmentId(newTenant.getApartment().getId());
			tenantDao.save(newTenant);
		}

	}

	@Override
	public List<Tenant> getList() {
		return tenantDao.getList();
	}

	@Override
	public void deleteTenant(Long id) {
		tenantDao.deleteById(id);

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
