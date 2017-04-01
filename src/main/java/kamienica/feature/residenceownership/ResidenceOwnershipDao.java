package kamienica.feature.residenceownership;

import kamienica.model.entity.ResidenceOwnership;
import kamienica.model.jpa.dao.BasicDao;
import org.springframework.stereotype.Repository;

@Repository("residenceOwnership")
public class ResidenceOwnershipDao extends BasicDao<ResidenceOwnership> implements IResidenceOwnershipDao {
}
