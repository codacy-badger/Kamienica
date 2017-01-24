package kamienica.feature.apartment;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Apartment;
import kamienica.model.Residence;

import java.util.List;

public interface ApartmentDao extends BasicDao<Apartment> {

	int getNumOfEmptyApartment();

	List<Apartment> getListForOwner(List<Residence> residences);
}
