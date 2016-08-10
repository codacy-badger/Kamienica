package kamienica.feature.apartment;

import kamienica.core.dao.DaoInterface;

public interface ApartmentDao extends DaoInterface<Apartment> {

	public int getNumOfEmptyApartment();
}
