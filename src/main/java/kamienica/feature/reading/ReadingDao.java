package kamienica.feature.reading;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("readingDao")
public class ReadingDao extends BasicDaoImpl<Reading> implements IReadingDao {

    private static final String GET_PREVIOUS = "SELECT * FROM %1$s where readingDate=(SELECT max(readingDate) FROM %1$s WHERE readingDate < :date )  AND meter_id IN(:list)";
    private static final String COUNT_LAST_READING_DAYS = "SELECT DATEDIFF(CURDATE() , readingDate) FROM %s order by readingDate desc limit 1;";
    private static final String DELETE_LATEST = "delete from  %s where readingDate=:date and resolved=:res";
    private static final String CHANGE_RESOLVEMENT = "update %s set resolved= :res where readingDate = :paramdate";


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
        Criteria readings = createEntityCriteria().add(Restrictions.eq("readingDate", date));
        Criteria meters = readings.createCriteria("meter");
        meters.add(Restrictions.eq("residence", r));
        meters.add(Restrictions.gt("deactivation", LocalDate.now()));
        return readings.list();
    }

    @Override
    public List<Reading> getPrevious(LocalDate readingDate, Set<Long> meterId) {
        String queryString = String.format(GET_PREVIOUS, getTabName());
        Query query = getSession().createSQLQuery(queryString).addEntity(this.persistentClass)
                .setDate("date", readingDate.toDate()).setParameterList("list", meterId);
        @SuppressWarnings("unchecked")
        List<Reading> result = query.list();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getUnresolvedReadings(final Media media, Residence r) {
        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("resolved", false));
        c.add(Restrictions.eq("media", media));
        c.addOrder(Order.asc("readingDate"));
        Criteria meters = c.createCriteria("meter");
        meters.add(Restrictions.isNull("apartment"));
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
    public void deleteLatestReadings(LocalDate date) {
        String sql = String.format(DELETE_LATEST, getTabName());
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("date", date.toString()).setParameter("res", false);
        query.executeUpdate();

    }

    @Override
    public LocalDate getLatestDate(final Residence r, final Media media) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("media", media));
        criteria.createCriteria("meter").add(Restrictions.eq("residence", r));
        criteria.setProjection(Projections.max("readingDate"));
        return (LocalDate) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getList(Residence r, Media media) {
        Criteria c = createEntityCriteria();
        c.createCriteria("meter");
        c.add(Restrictions.eq("residence", r));
        c.add(Restrictions.eq("media", media));
        c.addOrder(Order.desc("readingDate")).list();
        return c.list();
    }

    @Override
    public List<Reading> getListForTenant(Apartment apartment) {
        String old = "select * from readingenergy where meter_id IN(select id from meterenergy where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;";
        Query query = getSession().createSQLQuery(old).addEntity(Reading.class).setInteger("num",
                apartment.getApartmentNumber());
        @SuppressWarnings("unchecked")
        List<Reading> result = query.list();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reading> getWaterReadingForGasConsumption(final Residence r, final LocalDate date) {
        String queryString = "SELECT * FROM readingwater where readingdate = "
                + "(select MAX(readingdate) from readingwater where readingdate < :date) AND residence_id = :r";
        Query query = getSession().createSQLQuery(queryString).addEntity(persistentClass).setDate("date",
                date.toDate()).setLong("r", r.getId());

        return query.list();
    }
}
