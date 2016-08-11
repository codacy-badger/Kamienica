package kamienica.feature.tenant;

import java.util.List;

public interface TenantService {

	public void saveTenant(Tenant tenant);

	public List<Tenant> getList();

	public void deleteTenant(Long id);

	public void updateTenant(Tenant tenant);

	public Tenant getTenantById(Long id);
	
	public List<Tenant> getCurrentTenants();

	public Tenant loadByMail(String mail);
}