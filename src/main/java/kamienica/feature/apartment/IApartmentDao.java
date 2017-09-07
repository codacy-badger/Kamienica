package kamienica.feature.apartment;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.List;

public interface IApartmentDao extends IBasicDao<Apartment> {

	List<Apartment> getListForOwner(List<Residence> residences);
}
