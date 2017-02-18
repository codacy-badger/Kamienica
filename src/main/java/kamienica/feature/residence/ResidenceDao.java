package kamienica.feature.residence;

import kamienica.model.jpa.dao.BasicDao;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;

import java.util.List;

public interface ResidenceDao extends BasicDao<Residence> {

    List<Residence> listForOwner(Tenant t);
}
