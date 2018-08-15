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

}
