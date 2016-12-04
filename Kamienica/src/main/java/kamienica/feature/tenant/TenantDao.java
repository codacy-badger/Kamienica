package kamienica.feature.tenant;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;

import java.util.List;

public interface TenantDao extends DaoInterface<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);
}
