package kamienica.feature.apartment;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;
import kamienica.model.Residence;

import java.util.List;

public interface ApartmentDao extends DaoInterface<Apartment> {

	int getNumOfEmptyApartment();

	List<Apartment> getListForOwner(List<Residence> residences);
}
