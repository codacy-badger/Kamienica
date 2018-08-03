package kamienica.feature.rentcontract;

import kamienica.model.entity.RentContract;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rentDao")
public class RentDao extends BasicDao<RentContract> implements IRentContractDao {

    private static final String COTRACT_START = "cotractStart";
    private static final String COTRACT_END = "cotractEnd";

    @Override
    public List<RentContract> findCurrentContract(Residence residence) {
        final LocalDate now = new LocalDate();
        final Criterion startDate = Restrictions.gt(COTRACT_START, now);
        final Criterion endDate = Restrictions.lt(COTRACT_END, now);
        return findByCriteria(startDate, endDate);
    }
}
