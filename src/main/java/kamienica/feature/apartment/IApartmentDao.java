package kamienica.feature.apartment;

import kamienica.model.jpa.dao.BasicDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;

import java.util.List;

public interface IApartmentDao extends BasicDao<Apartment> {

	int getNumOfEmptyApartment();

	List<Apartment> getListForOwner(List<Residence> residences);
}
