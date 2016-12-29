package kamienica.feature.tenant;

import kamienica.model.Tenant;

import java.util.List;

public interface TenantService {

	void saveTenant(Tenant tenant);

	List<Tenant> getList();

	void deleteTenant(Long id);

	void updateTenant(Tenant tenant);

	Tenant getTenantById(Long id);
	
	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);

	List<Tenant> getOwners();
}