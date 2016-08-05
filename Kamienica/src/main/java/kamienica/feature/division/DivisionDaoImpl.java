package kamienica.feature.division;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("divisionDao")
public class DivisionDaoImpl extends AbstractDao<Division> implements DivisionDao {

	@Override
	public void deleteAll() {
		Query query = getSession().createSQLQuery("delete from division where id > 0");
		query.executeUpdate();

	}

}
