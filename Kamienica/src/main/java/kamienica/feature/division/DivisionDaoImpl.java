package kamienica.feature.division;

import kamienica.core.dao.AbstractDao;
import kamienica.model.Division;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("divisionDao")
public class DivisionDaoImpl extends AbstractDao<Division> implements DivisionDao {

	@Override
	public void deleteAll() {
		Query query = getSession().createSQLQuery("delete from division where id > 0");
		query.executeUpdate();

	}

}
