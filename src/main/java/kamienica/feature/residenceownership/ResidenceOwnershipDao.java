package kamienica.feature.residenceownership;

import kamienica.model.jpa.dao.BasicDaoImpl;
import kamienica.model.entity.ResidenceOwnership;
import org.springframework.stereotype.Repository;

@Repository("residenceOwnership")
public class ResidenceOwnershipDao extends BasicDaoImpl<ResidenceOwnership> implements IResidenceOwnershipDao {
}
