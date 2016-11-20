package kamienica.feature.tenant;

import java.util.List;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;

public interface TenantDao extends DaoInterface<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);

	void deactivateByApparmentId(Long id);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);
}
