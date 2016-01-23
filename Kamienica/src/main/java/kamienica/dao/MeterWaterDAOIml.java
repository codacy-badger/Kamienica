package kamienica.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.MeterWater;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends AbstractDao<Integer, MeterWater> implements MeterWaterDAO {

	@Override
	public void deleteWaterByID(int id) {
		Query query = getSession().createSQLQuery("delete from meterWater where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

}
