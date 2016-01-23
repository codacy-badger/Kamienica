package kamienica.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.model.MeterGas;

@Repository("meterGasDao")
public class MeterGasDAOImpl extends AbstractDao<Integer, MeterGas> implements MeterGasDAO {

	@Override
	public void deleteGasByID(int id) {
		Query query = getSession().createSQLQuery("delete from meterGas where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
		
	}

	
}
