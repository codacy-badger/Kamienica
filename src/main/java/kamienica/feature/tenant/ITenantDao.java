package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.jpa.dao.BasicDao;

import java.util.List;

public interface ITenantDao extends BasicDao<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);
}
