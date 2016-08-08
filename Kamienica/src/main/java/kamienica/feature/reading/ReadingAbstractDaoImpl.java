package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import kamienica.core.dao.AbstractDao;
import kamienica.feature.invoice.Invoice;

public abstract class ReadingAbstractDaoImpl<T extends ReadingAbstract> extends AbstractDao<T> {

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
		List<T> list = createEntityCriteria().add(Restrictions.eq("readingDate", date)).list();
		return list;
	}

	public List<T> getPrevious(LocalDate readingDate, Set<Long> meterId) {
		String queryString = String.format("SELECT * FROM %1$s where readingDate=(SELECT max(readingDate) "
				+ "FROM %1$s WHERE readingDate < :date )  AND meter_id IN(:list)", getTabName());
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

	public void changeResolvmentState(Invoice invoice, boolean resolved) {
		String sql = String.format("update %s set resolved= :res where readingDate = :paramdate", getTabName());
		Query query = getSession().createSQLQuery(sql)
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", resolved);
		query.executeUpdate();

	}

	public int countDaysFromLastReading() {
		String sql = String.format("SELECT DATEDIFF(CURDATE() , readingDate) FROM %s order by readingDate desc limit 1",
				getTabName());
		try {
			Query query = getSession().createSQLQuery(sql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 999;
		}
	}

	public void deleteLatestReadings(LocalDate date) {
		String sql = String.format("delete from  %s where readingDate=:date and resolved=:res", getTabName());
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("date", date.toString()).setParameter("res", false);
		query.executeUpdate();

	}

	public LocalDate getLatestDate() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.max("readingDate"));
		return (LocalDate) criteria.uniqueResult();
	}
}
