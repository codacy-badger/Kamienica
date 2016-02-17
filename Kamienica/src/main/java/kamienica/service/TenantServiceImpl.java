package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.TenantDao;
import kamienica.model.Tenant;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

	@Autowired
	TenantDao tenantDao;

	@Override
	public void saveTenant(Tenant tenant) {
		tenantDao.deactivateByApparmentId(tenant.getApartment().getId());
		tenantDao.save(tenant);

	}

	@Override
	public List<Tenant> getList() {
		return tenantDao.getList();
	}

	@Override
	public void deleteTenant(int id) {
		tenantDao.delete(id);

	}

	@Override
	public void updateTenant(Tenant tenant) {
		tenantDao.update(tenant);

	}

	@Override
	public Tenant getTenantById(int id) {
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
