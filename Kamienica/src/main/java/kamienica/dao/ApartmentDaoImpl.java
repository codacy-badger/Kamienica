package kamienica.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Integer, Apartment> implements ApartmentDao {

	// @SuppressWarnings("unchecked")
	// public List<Apartment> getList() {
	// Criteria criteria = createEntityCriteria();
	// return (List<Apartment>) criteria.list();
	// }

	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from apartment where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
		
		

	}

	// public Apartment getById(int id) {
	// return getById(id);
	// }

	public Apartment getByAparmentNumber(int number) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("apartmentNumber", number));
		return (Apartment) criteria.uniqueResult();

	}

}
