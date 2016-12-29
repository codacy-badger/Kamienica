package kamienica.feature.tenant;

import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface TenantService {

	void saveTenant(Tenant tenant);

	List<Tenant> getList();

	List<Tenant> findByCriteria(Criterion... crit);

	void deleteTenant(Long id);

	void updateTenant(Tenant tenant);

	Tenant getTenantById(Long id);
	
	List<Tenant> getActiveTenants();

	Tenant loadByMail(String mail);

	List<Tenant> getOwners();
}