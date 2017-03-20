package kamienica.feature.residence;

import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.jpa.dao.BasicDao;

import java.util.List;

public interface IResidenceDao extends BasicDao<Residence> {

    List<Residence> listForOwner(Tenant t);
}
