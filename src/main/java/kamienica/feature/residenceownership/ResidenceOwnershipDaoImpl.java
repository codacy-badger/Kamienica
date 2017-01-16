package kamienica.feature.residenceownership;

import kamienica.core.dao.AbstractDao;
import kamienica.model.ResidenceOwnership;
import org.springframework.stereotype.Repository;

@Repository("residenceOwnership")
public class ResidenceOwnershipDaoImpl extends AbstractDao<ResidenceOwnership> implements ResidenceOwnershipDao {
}
