package kamienica.feature.reading;

import kamienica.model.Apartment;
import kamienica.model.ReadingEnergy;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("readingEnergyDao")
public class ReadingEnergyDAOImpl extends ReadingAbstractDaoImpl<ReadingEnergy>
		implements ReadingEnergyDao {


	@Override
	public List<ReadingEnergy> getListForTenant(Apartment apartment) {
		String old = "select * from readingenergy where meter_id IN(select id from meterenergy where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;";
		Query query = getSession().createSQLQuery(old).addEntity(ReadingEnergy.class).setInteger("num",
				apartment.getApartmentNumber());
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}


}
