package kamienica.feature.division;

import kamienica.model.entity.Division;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("divisionDao")
public class DivisionDaoImpl extends BasicDaoImpl<Division> implements IDivisionDao {

	@Override
	public void deleteAll() {
		Query query = getSession().createSQLQuery("delete from division where id > 0");
		query.executeUpdate();

	}

}
