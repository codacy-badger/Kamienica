package kamienica.feature.apartment;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Long, Apartment> implements ApartmentDao {

	@Override
	public int getNumOfEmptyApartment() {
		Query query = getSession().createSQLQuery(
				"SELECT count(*) FROM apartment WHERE NOT EXISTS (SELECT * FROM tenant WHERE apartment.id = tenant.apartment_id ) and apartmentNumber > 0");

		return ((Number) query.uniqueResult()).intValue();
	}



}
