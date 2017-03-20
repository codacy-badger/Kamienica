package kamienica.feature.rentcontract;

import kamienica.model.entity.RentContract;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.List;

public interface IRentContractDao extends IBasicDao<RentContract> {

    List<RentContract> findCurrentContract(Residence residence);
}
