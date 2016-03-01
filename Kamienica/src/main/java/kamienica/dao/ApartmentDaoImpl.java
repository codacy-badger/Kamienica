package kamienica.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Integer, Apartment> implements ApartmentDao {



	@Override
	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from apartment where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
		
		

	}



}
