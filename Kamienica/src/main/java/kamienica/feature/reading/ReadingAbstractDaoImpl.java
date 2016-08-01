package kamienica.feature.reading;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import kamienica.dao.AbstractDao;
import kamienica.feature.invoice.Invoice;

public abstract class ReadingAbstractDaoImpl<T extends ReadingAbstract> extends AbstractDao<Long, T> {

	@Override
	public List<T> getList() {

		@SuppressWarnings("unchecked")
		List<T> list = createEntityCriteria().addOrder(Order.desc("readingdate")).list();
		return list;
	}

	public List<T> getByDate(String readingDate) {
		@SuppressWarnings("unchecked")
		List<T> list = createEntityCriteria().add(Restrictions.eq("readingDate", readingDate)).list();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<T> getLatestList(Set<Long> meterId) {

		String test = "Select * from :clazz where readingDate=(select MAX(readingDate)  from T) AND meter_id IN(:list)";
		Query query = getSession().createSQLQuery(test).setParameterList("list", meterId).setString("clazz",
				persistentClass.getSimpleName());

		return query.list();

	}

	public List<T> getPrevious(String readingDate, Set<Long> meterId) {
		Query query = getSession()
				.createSQLQuery(
						"SELECT * FROM :clazz where readingDate=(SELECT max(readingDate) FROM :clazz WHERE readingDate < :date ) AND meter_id IN(:list)")
				.setString("clazz", persistentClass.getSimpleName()).setString("date", readingDate)
				.setParameterList("list", meterId);
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> getUnresolvedReadings() {
		// Query query = getSession().createSQLQuery("SELECT r.id,
		// r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
		// + "FROM T r join meterEnergy m on r.meter_id = m.id "
		// + "where r.resolved = 0 and m.apartment_id is
		// null").addEntity(T.class);
		// return query.list();

		List<T> list = createEntityCriteria().add(Restrictions.eq("resolved", false))
				.add(Restrictions.eq("apartment", null)).list();
		return list;

	}

	public void resolveReadings(Invoice invoice) {
		Query query = getSession().createSQLQuery("update :clazz set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", true)
				.setString("clazz", persistentClass.getSimpleName());
		query.executeUpdate();

	}

	public void unresolveReadings(Invoice invoice) {
		Query query = getSession().createSQLQuery("update :clazz set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", false)
				.setString("clazz", persistentClass.getSimpleName());
		query.executeUpdate();

	}

	public int countDaysFromLastReading() {
		try {
			Query query = getSession()
					.createSQLQuery(
							"SELECT DATEDIFF(CURDATE() , readingDate) FROM :clazz order by readingDate desc limit 1")
					.setString("clazz", persistentClass.getSimpleName());
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public void deleteLatestReadings(LocalDate date) {
		Query query = getSession().createSQLQuery("delete from :clazz where readingDate=:date and resolved=:res");
		query.setParameter("date", date.toString()).setParameter("res", false).setString("clazz",
				persistentClass.getSimpleName());
		query.executeUpdate();

	}

	public LocalDate getLatestDate() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.max("readingDate"));
		return (LocalDate) criteria.uniqueResult();
	}
}
