package kamienica.feature.reading;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("readingDao")
public class ReadingDao extends BasicDaoImpl<Reading> implements IReadingDao {

    //TODO what the hell is this...
    private static final String LIST_FOR_TENANT = "select * from reading where meter_id IN(select id from meter where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num)));";

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getByDate(final Residence r, LocalDate readingDate) {
        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("readingDate", readingDate));
        c.add(Restrictions.eq("residence", r));
        return c.list();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getList(Residence r, Media media) {
        Criteria c = createEntityCriteria();
        c.createCriteria("meter").add(Restrictions.eq("residence", r));
        c.createCriteria("readingDetails").add(Restrictions.eq("media", media)).addOrder(Order.desc("readingDate"));
        return c.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reading> getListForTenant(Apartment apartment) {
        Query query = getSession().createSQLQuery(LIST_FOR_TENANT).addEntity(Reading.class).setInteger("num",
                apartment.getApartmentNumber());

        return (List<Reading>) query.list();
    }

    @Override
    public List<Reading> list(ReadingDetails details) {
        return findByCriteria(Restrictions.eq("readingDetails", details));
    }

}
