package kamienica.feature.reading;

import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.ReadingWater;
import kamienica.model.Residence;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("readingWaterDao")
public class ReadingWaterDAOImpl extends ReadingAbstractDaoImpl<ReadingWater> implements ReadingWaterDao {


    @Override
    public List<ReadingWater> getListForTenant(Apartment apartment) {
        Query query = getSession()
                .createSQLQuery(
                        "select * from readingwater where meter_id IN(select id from meterwater where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc")
                .addEntity(ReadingWater.class).setInteger("num", apartment.getApartmentNumber());
        @SuppressWarnings("unchecked")
        List<ReadingWater> result = query.list();
        return result;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<ReadingWater> getWaterReadingForGasConsumption2(final Residence r, final LocalDate date) {
        String queryString = "SELECT * FROM readingwater where readingdate = "
                + "(select MAX(readingdate) from readingwater where readingdate < :date) AND residence_id = :r";
        Query query = getSession().createSQLQuery(queryString).addEntity(persistentClass).setDate("date",
                date.toDate()).setLong("r", r.getId());

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice) {
        String queryString = "SELECT * FROM readingwater where readingdate = (select MAX(readingdate) from readingwater where readingdate < :date)";
        HashMap<String, List<ReadingWater>> out = new HashMap<>();
        List<ReadingWater> oldReadings = new ArrayList<>();
        Query query = getSession().createSQLQuery(queryString).addEntity(ReadingWater.class).setDate("date",
                invoice.getBaseReading().getReadingDate().toDate());
        List<ReadingWater> newReadings = query.list();
        if (!newReadings.isEmpty()) {

            query = getSession().createSQLQuery(queryString).addEntity(ReadingWater.class).setDate("date",
                    newReadings.get(0).getReadingDate().toDate());
            oldReadings = query.list();
        }

        out.put("old", oldReadings);
        out.put("new", newReadings);

        return out;
    }

    @Override
    public List<ReadingWater> getList(Residence r) {
        return createEntityCriteria().createCriteria("meter").add(Restrictions.eq("residence",r )).list();
    }


}
