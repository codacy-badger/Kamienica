package kamienica.dao;

import org.springframework.stereotype.Repository;

import kamienica.model.MeterEnergy;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends AbstractDao<Integer, MeterEnergy> implements DaoInterface<MeterEnergy> {



//	@Override
//	public void deleteByID(int id) {
//		Query query = getSession().createSQLQuery("delete from meterEnergy where id = :id");
//		query.setInteger("id", id);
//		query.executeUpdate();
//		
//	}

}
