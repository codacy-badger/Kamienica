package kamienica.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.MeterEnergy;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends AbstractDao<Integer, MeterEnergy> implements MeterEnergyDAO {



	@Override
	public void deleteEnergyByID(int id) {
		Query query = getSession().createSQLQuery("delete from meterEnergy where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
		
	}

}
