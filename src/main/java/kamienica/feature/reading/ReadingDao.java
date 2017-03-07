package kamienica.feature.reading;

import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
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

    private static final String GET_PREVIOUS = "SELECT * FROM %1$s where readingDate=(SELECT max(readingDate) FROM %1$s WHERE readingDate < :date )  AND meter_id IN(:list)";
    private static final String COUNT_LAST_READING_DAYS = "SELECT DATEDIFF(CURDATE() , readingDate) FROM %s order by readingDate desc limit 1;";
    private static final String DELETE_LATEST = "delete from  %s where readingDate=:date and resolved=:res";
    private static final String CHANGE_RESOLVEMENT = "update %s set resolved= :res where readingDate = :paramdate";
    private static final String LIST_FOR_TENANT = "select * from readingenergy where meter_id IN(select id from meterenergy where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;";

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
    public List<Reading> getLatestList(final Residence r, LocalDate date) {
        //TODO inspect whether this method does exatcly as getByDate
        Criteria c = createEntityCriteria().add(Restrictions.eq("readingDate", date));
        c.createCriteria("meter").add(Restrictions.eq("residence", r)).add(Restrictions.eq("status", Status.ACTIVE));
        return c.list();
    }

    @Override
    public List<Reading> getPrevious(ReadingDetails details, List<Meter> meters) {
//        String queryString = String.format(GET_PREVIOUS, getTabName());
//        Query query = getSession().createSQLQuery(queryString).addEntity(this.persistentClass)
//                .setDate("date", readingDate.toDate()).setParameterList("list", meterId);
//        @SuppressWarnings("unchecked")
//        List<Reading> result = query.list();
        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("readingDetails", details));
        c.add(Restrictions.in("meter", meters));
        return c.list();
    }

    @Override
    public void changeResolvementState(Invoice invoice, boolean resolved) {
        String sql = String.format(CHANGE_RESOLVEMENT, getTabName());
        Query query = getSession().createSQLQuery(sql)
                .setDate("paramdate", invoice.getReadingDetails().getReadingDate().toDate()).setParameter("res", resolved);
        query.executeUpdate();

    }

    @Override
    public int countDaysFromLastReading() {
        String sql = String.format(COUNT_LAST_READING_DAYS,
                getTabName());
        try {
            Query query = getSession().createSQLQuery(sql);
            return ((Number) query.uniqueResult()).intValue();
        } catch (Exception e) {
            return 999;
        }
    }

    @Override
    public LocalDate getLatestDate(final Residence r, final Media media) {
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("meter").add(Restrictions.eq("residence", r));
        criteria.createCriteria("readingDetails").add(Restrictions.eq("media", media)).setProjection(Projections.max("readingDate"));
        return (LocalDate) criteria.uniqueResult();
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getWaterReadingForGasConsumption(final Residence r, final ReadingDetails details) {
        String queryString = "SELECT * FROM reading where readingdate = "
                + "(select MAX(readingdate) from readingwater where readingdate < :date) AND residence_id = :r";
        Query query = getSession().createSQLQuery(queryString).addEntity(persistentClass).setDate("date",
                details.getReadingDate().toDate()).setLong("r", r.getId());

        return query.list();
    }
}
