package kamienica.feature.tenant;

import kamienica.model.jpa.dao.BasicDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;

import java.util.List;

public interface ITenantDao extends BasicDao<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);
}
