package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.List;

public interface ITenantDao extends IBasicDao<Tenant> {

    Tenant getTenantForApartment(Apartment apartment);

    List<Tenant> getActiveTenants(List<Apartment> apartmentList);

    Tenant loadByMail(String mail);
}
