package kamienica.feature.apartment;

import kamienica.dao.DaoInterface;

public interface ApartmentDao extends DaoInterface<Apartment> {

	public int getNumOfEmptyApartment();
}
