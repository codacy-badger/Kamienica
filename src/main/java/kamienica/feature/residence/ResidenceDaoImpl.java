package kamienica.feature.residence;

import kamienica.core.dao.AbstractDao;
import kamienica.model.Residence;
import org.springframework.stereotype.Repository;

@Repository("residenceDao")
public class ResidenceDaoImpl extends AbstractDao<Residence> implements ResidenceDao {
}
