package kamienica.feature.apartment;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Apartment;

public interface ApartmentDao extends DaoInterface<Apartment> {

	int getNumOfEmptyApartment();
}
