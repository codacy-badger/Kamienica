package kamienica.feature.reading;

import kamienica.core.dao.AbstractDao;
import kamienica.model.Invoice;
import kamienica.model.Reading;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Set;

public abstract class ReadingAbstractDaoImpl<T extends Reading> extends AbstractDao<T> {

	public static final String GET_PREVIOUS = "SELECT * FROM %1$s where readingDate=(SELECT max(readingDate) FROM %1$s WHERE readingDate < :date )  AND meter_id IN(:list)";
	public static final String COUNT_LAST_READING_DAYS = "SELECT DATEDIFF(CURDATE() , readingDate) FROM %s order by readingDate desc limit 1;";
	public static final String DELETE_LATEST = "delete from  %s where readingDate=:date and resolved=:res";
	public static final String CHANGE_RESOLVEMENT = "update %s set resolved= :res where readingDate = :paramdate";

	@Override
	public List<T> getList() {

		@SuppressWarnings("unchecked")
		List<T> list = createEntityCriteria().addOrder(Order.desc("readingDate")).list();
		return list;
	}

	public List<T> getByDate(LocalDate readingDate) {
		@SuppressWarnings("unchecked")
		List<T> list = createEntityCriteria().add(Restrictions.eq("readingDate", readingDate)).list();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<T> getLatestList(LocalDate date) {
		Criteria readings = createEntityCriteria().add(Restrictions.eq("readingDate", date));
		Criteria meters = readings.createCriteria("meter");
		meters.add(Restrictions.gt("deactivation", LocalDate.now()));
		
		
		return readings.list();
	}

	public List<T> getPrevious(LocalDate readingDate, Set<Long> meterId) {
		String queryString = String.format(GET_PREVIOUS, getTabName());
		Query query = getSession().createSQLQuery(queryString).addEntity(this.persistentClass)
				.setDate("date", readingDate.toDate()).setParameterList("list", meterId);
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> getUnresolvedReadings() {
		Criteria readings = createEntityCriteria().add(Restrictions.eq("resolved", false));
		readings.addOrder(Order.asc("readingDate"));
		Criteria meters = readings.createCriteria("meter");
		meters.add(Restrictions.isNull("apartment"));
		return readings.list();

	}

	public void changeResolvementState(Invoice invoice, boolean resolved) {
		String sql = String.format(CHANGE_RESOLVEMENT, getTabName());
		Query query = getSession().createSQLQuery(sql)
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", resolved);
		query.executeUpdate();

	}

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

	public void deleteLatestReadings(LocalDate date) {
		String sql = String.format(DELETE_LATEST, getTabName());
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("date", date.toString()).setParameter("res", false);
		query.executeUpdate();

	}

	public LocalDate getLatestDate() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.max("readingDate"));
		return (LocalDate) criteria.uniqueResult();
	}
}
