package kamienica.feature.reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;

@Repository("readingWaterDao")
public class ReadingWaterDAOImpl extends ReadingAbstractDaoImpl<ReadingWater> implements ReadingWaterDao {
//
//	@Override
//	public List<ReadingWater> getList() {
//		@SuppressWarnings("unchecked")
//		List<ReadingWater> list = getSession().createCriteria(ReadingWater.class).addOrder(Order.desc("readingDate"))
//				.list();
//		return list;
//	}

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

//	@Override
//	public List<ReadingWater> getPrevious(String readingDate, Set<Long> meterId) {
//		Query query = getSession()
//				.createSQLQuery(
//						"SELECT * FROM readingwater where readingDate =(SELECT max(readingDate) FROM readingwater WHERE readingDate <  :date )")
//				.addEntity(ReadingWater.class).setString("date", readingDate);
//		@SuppressWarnings("unchecked")
//		List<ReadingWater> result = query.list();
//		return result;
//	}

//	@Override
//	public List<ReadingWater> getByDate(String readingDate) {
//		Query query = getSession().createSQLQuery("SELECT * FROM readingwater where readingDate=:date")
//				.addEntity(ReadingWater.class).setString("date", readingDate);
//		@SuppressWarnings("unchecked")
//		List<ReadingWater> result = query.list();
//		return result;
//	}

//	@Override
//	public List<ReadingWater> getLatestList(Set<Long> meterId) {
//		// String original = "Select * from (select * from readingwater order by
//		// readingDate desc) as c group by meter_id";
//		String test = "Select * from readingwater where readingDate=(select MAX(readingDate) from readingwater) AND meter_id IN(:list)";
//		Query query = getSession().createSQLQuery(test).addEntity(ReadingWater.class).setParameterList("list", meterId);
//		;
//		@SuppressWarnings("unchecked")
//		List<ReadingWater> result = query.list();
//
//		return result;
//	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<ReadingWater> getUnresolvedReadings() {
//		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
//				+ "FROM readingwater r join meterwater m on r.meter_id = m.id "
//				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingWater.class);
//		return query.list();
//
//	}

	@Override
	public void resolveReadings(InvoiceWater invoice) {
		Query query = getSession()
				.createSQLQuery("update readingwater set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", true);
		query.executeUpdate();

	}

	@Override
	public void unresolveReadings(InvoiceWater invoice) {
		Query query = getSession()
				.createSQLQuery("update readingwater set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", false);
		query.executeUpdate();

	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption(InvoiceGas invoice) {
		String queryString = "SELECT * FROM readingwater where readingdate = (select MAX(readingdate) from readingwater where readingdate < :date)";
		// "SELECT * FROM readingwater where readingdate = (select readingdate
		// from readingwater where readingdate < :date GROUP BY readingdate
		// ORDER BY readingdate desc limit 1)"
		// "SELECT * FROM readingwater where readingdate = (select readingdate
		// from readingwater where readingdate < :date GROUP BY readingdate
		// ORDER BY readingdate desc limit 1)"
		HashMap<String, List<ReadingWater>> out = new HashMap<String, List<ReadingWater>>();
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

//	@Override
//	public int countDaysFromLastReading() {
//		try {
//			Query query = getSession().createSQLQuery(
//					"SELECT DATEDIFF(CURDATE()  ,readingwater.readingDate) FROM readingwater order by readingDate desc limit 1");
//			return ((Number) query.uniqueResult()).intValue();
//		} catch (Exception e) {
//			return 0;
//		}
//	}

//	@Override
//	public void deleteLatestReadings(LocalDate date) {
//		Query query = getSession()
//				.createSQLQuery("delete from readingwater where readingDate=:date and resolved = :res");
//		query.setParameter("date", date.toString()).setParameter("res", false);
//		query.executeUpdate();
//
//	}
//
//	@Override
//	public LocalDate getLatestDate() {
//		Criteria criteria = getSession().createCriteria(ReadingWater.class)
//				.setProjection(Projections.max("readingDate"));
//		return (LocalDate) criteria.uniqueResult();
//	}

}
