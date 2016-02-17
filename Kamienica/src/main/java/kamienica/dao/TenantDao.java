package kamienica.dao;

import java.util.List;

import kamienica.model.Tenant;

public interface TenantDao {

	public void save(Tenant tenant);

	public void deactivateByApparmentId(int id);

	public List<Tenant> getList();

	public void delete(int id);

	public void update(Tenant tenant);

	public Tenant getById(int id);

	public List<Tenant> getActiveTenants();

	public Tenant loadByMail(String mail);
}
