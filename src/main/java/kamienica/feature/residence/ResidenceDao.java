package kamienica.feature.residence;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Residence;
import kamienica.model.Tenant;

import java.util.List;

public interface ResidenceDao extends BasicDao<Residence> {

    List<Residence> listForOwner(Tenant t);
}
