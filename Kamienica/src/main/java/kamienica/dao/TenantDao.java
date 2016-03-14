package kamienica.dao;

import java.util.List;

import kamienica.model.Tenant;

public interface TenantDao extends DaoInterface<Tenant> {

	public void deactivateByApparmentId(int id);

	public List<Tenant> getActiveTenants();

	public Tenant loadByMail(String mail);
}
