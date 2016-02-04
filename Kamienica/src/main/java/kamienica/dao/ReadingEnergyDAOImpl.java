package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentAbstract;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentStatus;
import kamienica.model.ReadingEnergy;

@Repository("readingEnergyDao")
public class ReadingEnergyDAOImpl extends AbstractDao<Integer, ReadingEnergy> implements ReadingEnergyDAO {

	public List<ReadingEnergy> getList() {
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> list = getSession().createCriteria(ReadingEnergy.class).addOrder(Order.desc("readingDate"))
				.list();
		return list;
	}

	public List<ReadingEnergy> getListForTenant(Apartment apartment) {
		Query query = getSession()
				.createSQLQuery(
						"select * from readingenergy where meter_id IN(select id from meterenergy where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;")
				.addEntity(ReadingEnergy.class).setInteger("num", apartment.getApartmentNumber());
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	public void deleteById(int id) {
		Query query = getSession().createSQLQuery("delete from readingenergy where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public HashMap<Integer, ReadingEnergy> getLatestReadingsMap() {

		Query query = getSession()
				.createSQLQuery(
						"Select * from (select * from readingEnergy order by readingDate desc) as c group by meter_id")
				.addEntity(ReadingEnergy.class);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		HashMap<Integer, ReadingEnergy> mappedResult = new HashMap<>();
		for (ReadingEnergy i : result) {
			mappedResult.put(i.getMeter().getId(), i);
		}
		return mappedResult;
	}

	public List<ReadingEnergy> getPrevious(String readingDate) {
		Query query = getSession().createSQLQuery(
				"SELECT * FROM readingEnergy where readingDate =(SELECT max(readingDate) FROM readingEnergy WHERE readingDate < "
						+ "\"" + readingDate + "\" );")
				.addEntity(ReadingEnergy.class);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	public List<ReadingEnergy> getByDate(String readingDate) {
		Query query = getSession()
				.createSQLQuery("SELECT * FROM readingEnergy where readingDate=\"" + readingDate + "\"")
				.addEntity(ReadingEnergy.class);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	public List<ReadingEnergy> getLatestList() {
		Query query = getSession().createSQLQuery(
				"Select * from (select * from readingEnergy order by readingDate desc) as c group by meter_id")

				.addEntity(ReadingEnergy.class);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Date> getReadingDatesForPayment(PaymentAbstract payment) {
		if (payment.getReadingDate() != null) {
			Query query = getSession()
					.createSQLQuery(
							"SELECT readingdate FROM readingenergy where readingdate >= :date GROUP BY readingdate ORDER BY readingdate asc")
					.setParameter("date", payment.getReadingDate());
			return query.list();
		} else {
			Query query = getSession()
					.createSQLQuery(
							"SELECT readingdate FROM readingenergy where readingdate >= :date GROUP BY readingdate ORDER BY readingdate asc")
					.setParameter("date", "19800101");
			return query.list();
		}
	}

	@Override
	public void saveList(List<ReadingEnergy> reading) {
		for (int i = 0; i < reading.size(); i++) {
			save(reading.get(i));
		}

	}

	@SuppressWarnings("unchecked")
	public List<ReadingEnergy> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readingenergy r join meterEnergy m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingEnergy.class);;

		return query.list();

	}

	@Override
	public void ResolveReadings(InvoiceEnergy invoice) {
		Query query = getSession()
				.createSQLQuery(
						"update readingenergy set resolved= :res where readingDate = :paramdate")
				.setParameter("paramdate", invoice.getBaseReading().getReadingDate()).setParameter("res", true);
		query.executeUpdate();
		
	}

}
