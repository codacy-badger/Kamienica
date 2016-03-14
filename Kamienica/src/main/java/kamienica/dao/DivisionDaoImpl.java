package kamienica.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.Division;

@Repository("divisionDao")
public class DivisionDaoImpl extends AbstractDao<Integer, Division> implements DivisionDao {

	@Override
	public void deleteAll() {
		Query query = getSession().createSQLQuery("delete from division where id > 0");
		query.executeUpdate();

	}

}
