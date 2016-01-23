package kamienica.service;

import java.util.List;

import kamienica.model.Tenant;

public interface TenantService {

	public void saveTenant(Tenant tenant);

	public List<Tenant> getList();

	public void deleteTenant(int id);

	public void updateTenant(Tenant tenant);

	public Tenant getTenantById(int id);
	
	public List<Tenant> getCurrentTenants();

	public Tenant loadByMail(String mail);
}