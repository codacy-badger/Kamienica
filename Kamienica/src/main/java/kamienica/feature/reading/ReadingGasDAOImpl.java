package kamienica.feature.reading;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceGas;

@Repository("readingGasDao")
public class ReadingGasDAOImpl extends AbstractDao<Long, ReadingGas> implements ReadingDao<ReadingGas, InvoiceGas> {

	@Override
	public List<ReadingGas> getList() {
		@SuppressWarnings("unchecked")
		List<ReadingGas> list = getSession().createCriteria(ReadingGas.class).addOrder(Order.desc("readingDate"))
				.list();
		return list;
	}

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

	@Override
	public List<ReadingGas> getPrevious(String readingDate) {
		Query query = getSession()
				.createSQLQuery(
						"SELECT * FROM readinggas where readingDate =(SELECT max(readingDate) FROM readinggas WHERE readingDate < :date)")
				.addEntity(ReadingGas.class).setString("date", readingDate);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}

	@Override
	public List<ReadingGas> getByDate(String readingDate) {
		Query query = getSession().createSQLQuery("SELECT * FROM readingGas where readingDate= :date")
				.addEntity(ReadingGas.class).setString("date", readingDate);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}

	@Override
	public List<ReadingGas> getLatestList() {
		//		String original = "Select * from (select * from readingGas order by readingDate desc) as c group by meter_id";
		String test = "Select * from readingGas where readingDate=(select MAX(readingDate) from readingGas)";
		Query query = getSession().createSQLQuery(test).addEntity(ReadingGas.class);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingGas> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readinggas r join meterGas m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingGas.class);
		;

		return query.list();

	}

	@Override
	public void resolveReadings(InvoiceGas invoice) {
		Query query = getSession().createSQLQuery("update readinggas set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", true);
		query.executeUpdate();

	}

	@Override
	public void unresolveReadings(InvoiceGas invoice) {
		Query query = getSession().createSQLQuery("update readinggas set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", false);
		query.executeUpdate();

	}

	@Override
	public int countDaysFromLastReading() {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT DATEDIFF(CURDATE()  ,readinggas.readingDate) FROM readinggas order by readingDate desc limit 1");
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
}
