package kamienica.feature.residenceownership;

import kamienica.core.dao.AbstractDao;
import kamienica.model.ResidenceOwnership;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("resicendeOwnership")
public class ResicendeOwnershipDaoImpl extends AbstractDao<ResidenceOwnership> implements ResicendeOwnershipDao {
}
