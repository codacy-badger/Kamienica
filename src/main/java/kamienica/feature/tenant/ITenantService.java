package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.jpa.service.BasicService;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface ITenantService extends BasicService<Tenant> {

	List<Tenant> findByCriteria(Criterion... crit);

	List<Tenant> findForSpecifiedResicence(Long residenceId);

	List<Tenant> listActiveTenants(Residence residence);

	Tenant loadByMail(String mail);

	List<Tenant> getOwners();
}