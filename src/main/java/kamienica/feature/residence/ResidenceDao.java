package kamienica.feature.residence;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Residence;
import kamienica.model.Tenant;

import java.util.List;

public interface ResidenceDao extends DaoInterface<Residence> {

    List<Residence> listForOwner(Tenant t);
}
