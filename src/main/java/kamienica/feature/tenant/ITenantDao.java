package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.jpa.dao.IBasicDao;

public interface ITenantDao extends IBasicDao<Tenant> {

	Tenant getTenantForApartment(Apartment apartment);


	Tenant loadByMail(String mail);
}
