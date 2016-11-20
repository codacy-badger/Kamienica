package kamienica.feature.tenant;

import java.util.List;

public interface TenantService {

	void saveTenant(Tenant tenant);

	List<Tenant> getList();

	void deleteTenant(Long id);

	void updateTenant(Tenant tenant);

	Tenant getTenantById(Long id);
	
	List<Tenant> getCurrentTenants();

	Tenant loadByMail(String mail);
}