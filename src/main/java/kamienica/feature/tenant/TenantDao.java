package kamienica.feature.tenant;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Apartment;
import kamienica.model.Tenant;

import java.util.List;

public interface TenantDao extends BasicDao<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);
}
