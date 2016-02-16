package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingAbstract;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;

@Repository("readingWaterDao")
public class ReadingWaterDAOImpl extends AbstractDao<Integer, ReadingWater> implements ReadingWaterDAO {

	public List<ReadingWater> getList() {
		@SuppressWarnings("unchecked")
		List<ReadingWater> list = getSession().createCriteria(ReadingWater.class).addOrder(Order.desc("readingDate"))
				.list();
		return list;
	}

	public List<ReadingWater> getListForTenant(Apartment apartment) {
		Query query = getSession()
				.createSQLQuery(
						"select * from readingwater where meter_id IN(select id from meterwater where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;")
				.addEntity(ReadingWater.class).setInteger("num", apartment.getApartmentNumber());
		@SuppressWarnings("unchecked")
		List<ReadingWater> result = query.list();
		return result;
	}

	public void deleteById(int id) {
		Query query = getSession().createSQLQuery("delete from readingwater where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public HashMap<Integer, ReadingWater> getLatestReadingsMap() {

		Query query = getSession()
				.createSQLQuery(
						"Select * from (select * from readingWater order by readingDate desc) as c group by meter_id")
				.addEntity(ReadingWater.class);
		@SuppressWarnings("unchecked")
		List<ReadingWater> result = query.list();
		HashMap<Integer, ReadingWater> mappedResult = new HashMap<>();
		for (ReadingWater i : result) {
			mappedResult.put(i.getMeter().getId(), i);
		}
		return mappedResult;
	}

	public List<ReadingWater> getPrevious(String readingDate) {
		Query query = getSession().createSQLQuery(
				"SELECT * FROM readingwater where readingDate =(SELECT max(readingDate) FROM readingwater WHERE readingDate < "
						+ "\"" + readingDate + "\" );")
				.addEntity(ReadingWater.class);
		@SuppressWarnings("unchecked")
		List<ReadingWater> result = query.list();
		return result;
	}

	public List<ReadingWater> getByDate(String readingDate) {
		Query query = getSession()
				.createSQLQuery("SELECT * FROM readingWater where readingDate=\"" + readingDate + "\"")
				.addEntity(ReadingWater.class);
		@SuppressWarnings("unchecked")
		List<ReadingWater> result = query.list();
		return result;
	}

	public List<ReadingWater> getLatestList() {
		Query query = getSession().createSQLQuery(
				"Select * from (select * from readingWater order by readingDate desc) as c group by meter_id")

				.addEntity(ReadingWater.class);
		@SuppressWarnings("unchecked")
		List<ReadingWater> result = query.list();

		return result;
	}

	// @SuppressWarnings("unchecked")
	// public List<Date> getReadingDatesForPayment(PaymentAbstract payment) {
	// if (payment.getReadingDate() != null) {
	// Query query = getSession()
	// .createSQLQuery(
	// "SELECT readingdate FROM readingwater where readingdate >= :date GROUP BY
	// readingdate ORDER BY readingdate asc")
	// .setParameter("date", payment.getReadingDate());
	// return query.list();
	// } else {
	// Query query = getSession()
	// .createSQLQuery(
	// "SELECT readingdate FROM readingwater where readingdate >= :date GROUP BY
	// readingdate ORDER BY readingdate asc")
	// .setParameter("date", "19800101");
	// return query.list();
	// }
	// }

	@SuppressWarnings("unchecked")

	public List<ReadingWater> getWaterReadingsForGasConsumption(ReadingAbstract reading) {

		Query query = getSession()
				.createSQLQuery(
						"SELECT * FROM readingwater where readingdate = (select readingdate from readingwater where readingdate < :date GROUP BY readingdate ORDER BY readingdate desc limit 1)")
				.addEntity(ReadingWater.class).setParameter("date", reading.getReadingDate());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ReadingWater> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readingwater r join meterwater m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingWater.class);
		;

		return query.list();

	}

	@Override
	public void ResolveReadings(InvoiceWater invoice) {
		Query query = getSession()
				.createSQLQuery("update readingwater set resolved= :res where readingDate = :paramdate")
				.setParameter("paramdate", invoice.getBaseReading().getReadingDate()).setParameter("res", true);
		query.executeUpdate();

	}

	@Override
	public void UnresolveReadings(InvoiceWater invoice) {
		Query query = getSession()
				.createSQLQuery("update readingwater set resolved= :res where readingDate = :paramdate")
				.setParameter("paramdate", invoice.getBaseReading().getReadingDate()).setParameter("res", false);
		query.executeUpdate();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingWater> getLastPaid(InvoiceWater invoice) {
		Query query = getSession()
				.createSQLQuery("SELECT * FROM readingWater where status = :stat order by date desc l")
				.setParameter("stat", true);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, List<ReadingWater>> getWaterReadingForGasConsumption2(InvoiceGas invoice) {
		HashMap<String, List<ReadingWater>> out = new HashMap<String, List<ReadingWater>>();

		Query query = getSession().createSQLQuery("SELECT * FROM readingwater where readingdate = :date")
				.addEntity(ReadingWater.class).setParameter("date", invoice.getBaseReading().getReadingDate());
		List<ReadingWater> newReadings = query.list();
		out.put("new", newReadings);
		query = getSession()
				.createSQLQuery(
						"SELECT * FROM readingwater where readingdate = (select readingdate from readingwater where readingdate < :date GROUP BY readingdate ORDER BY readingdate desc limit 1)")
				.addEntity(ReadingWater.class).setParameter("date", newReadings.get(0).getReadingDate());
		List<ReadingWater> oldReadings = query.list();
		out.put("old", oldReadings);
		return out;
	}
}
