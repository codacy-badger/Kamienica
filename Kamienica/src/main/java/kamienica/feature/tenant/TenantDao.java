package kamienica.feature.tenant;

import java.util.List;

import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;

public interface TenantDao extends DaoInterface<Tenant> {

	public Tenant getTenantForApartment(Apartment apartment);

	public void deactivateByApparmentId(Long id);

	public List<Tenant> getActiveTenants();

	public Tenant loadByMail(String mail);
}
