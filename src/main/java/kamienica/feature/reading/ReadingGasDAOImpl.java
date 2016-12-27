package kamienica.feature.reading;

import kamienica.model.Apartment;
import kamienica.model.ReadingGas;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("readingGasDao")
public class ReadingGasDAOImpl extends ReadingAbstractDaoImpl<ReadingGas> implements ReadingGasDao {


    @Override
    public List<ReadingGas> getListForTenant(Apartment apartment) {
        Query query = getSession()
                .createSQLQuery(
                        "select * from readinggas where meter_id IN(select id from metergas where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;")
                .addEntity(ReadingGas.class).setInteger("num", apartment.getApartmentNumber());
        @SuppressWarnings("unchecked")
        List<ReadingGas> result = query.list();
        return result;
    }


}
