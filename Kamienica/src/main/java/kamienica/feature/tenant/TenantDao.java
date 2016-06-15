package kamienica.feature.tenant;

import java.util.List;

import kamienica.dao.DaoInterface;

public interface TenantDao extends DaoInterface<Tenant> {

	public void deactivateByApparmentId(Long id);

	public List<Tenant> getActiveTenants();

	public Tenant loadByMail(String mail);
}
