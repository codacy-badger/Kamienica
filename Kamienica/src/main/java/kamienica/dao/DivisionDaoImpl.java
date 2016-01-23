package kamienica.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.Division;

@Repository("divisionDao")
public class DivisionDaoImpl extends AbstractDao<Integer, Division> implements DivisionDao {

	@Override
	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from division where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@Override
	public void deleteAll() {
		System.out.println("----------------------halooo-------------------");
		Query query = getSession().createSQLQuery("delete from division where id > 0");
		query.executeUpdate();

	}

	@Override
	public void saveList(List<Division> division) {

		for (int i = 0; i < division.size(); i++) {
			Division tmp = division.get(i);
			save(tmp);

		}

	}

}
