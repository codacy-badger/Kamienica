package kamienica.feature.residenceownership;

import kamienica.core.daoservice.BasicDaoImpl;
import kamienica.model.ResidenceOwnership;
import org.springframework.stereotype.Repository;

@Repository("residenceOwnership")
public class ResidenceOwnershipDaoImpl extends BasicDaoImpl<ResidenceOwnership> implements ResidenceOwnershipDao {
}
