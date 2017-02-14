package kamienica.feature.tenant;

import kamienica.core.daoservice.BasicService;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface TenantService extends BasicService<Tenant> {

	List<Tenant> findByCriteria(Criterion... crit);

	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);

	List<Tenant> getOwners();
}