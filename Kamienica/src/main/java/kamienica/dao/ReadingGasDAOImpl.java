package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;

@Repository("readingGasDao")
public class ReadingGasDAOImpl extends AbstractDao<Integer, ReadingGas> implements ReadingGasDAO {

	public List<ReadingGas> getList() {
		@SuppressWarnings("unchecked")
		List<ReadingGas> list = getSession().createCriteria(ReadingGas.class).addOrder(Order.desc("readingDate"))
				.list();
		return list;
	}

	public List<ReadingGas> getListForTenant(Apartment apartment) {
		Query query = getSession()
				.createSQLQuery(
						"select * from readinggas where meter_id IN(select id from metergas where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;")
				.addEntity(ReadingGas.class).setInteger("num", apartment.getApartmentNumber());
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}
	
	public void deleteById(int id) {
		Query query = getSession().createSQLQuery("delete from readinggas where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public HashMap<Integer, ReadingGas> getLatestReadingsMap() {

		Query query = getSession()
				.createSQLQuery(
						"Select * from (select * from readinggas order by readingDate desc) as c group by meter_id")
				.addEntity(ReadingGas.class);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		HashMap<Integer, ReadingGas> mappedResult = new HashMap<>();
		for (ReadingGas i : result) {
			mappedResult.put(i.getMeter().getId(), i);
		}
		return mappedResult;
	}

 
	public List<ReadingGas> getPrevious(String readingDate) {
		Query query = getSession().createSQLQuery(
				"SELECT * FROM readinggas where readingDate =(SELECT max(readingDate) FROM readinggas WHERE readingDate < "
						+ "\"" + readingDate + "\" );")
				.addEntity(ReadingGas.class);	
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}

 
	public List<ReadingGas> getByDate(String readingDate) {
		Query query = getSession()
				.createSQLQuery("SELECT * FROM readingGas where readingDate=\"" + readingDate + "\"")
				.addEntity(ReadingGas.class);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();
		return result;
	}

 
	public List<ReadingGas> getLatestList() {
		Query query = getSession().createSQLQuery(
				"Select * from (select * from readingGas order by readingDate desc) as c group by meter_id")

				.addEntity(ReadingGas.class);
		@SuppressWarnings("unchecked")
		List<ReadingGas> result = query.list();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Date> getReadingDatesForPayment(PaymentAbstract payment) {
		if (payment.getReadingDate() != null) {
			Query query = getSession()
					.createSQLQuery(
							"SELECT readingdate FROM readinggas where readingdate >= :date GROUP BY readingdate ORDER BY readingdate asc")
					.setParameter("date", payment.getReadingDate());
			return query.list();
		} else {
			Query query = getSession()
					.createSQLQuery(
							"SELECT readingdate FROM readinggas where readingdate >= :date GROUP BY readingdate ORDER BY readingdate asc")
					.setParameter("date", "19800101");
			return query.list();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ReadingGas> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readinggas r join meterGas m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingGas.class);;

		return query.list();

	}
	
	@Override
	public void ResolveReadings(InvoiceGas invoice) {
		Query query = getSession()
				.createSQLQuery(
						"update readinggas set resolved= :res where readingDate = :paramdate")
				.setParameter("paramdate", invoice.getBaseReading().getReadingDate()).setParameter("res", true);
		query.executeUpdate();
		
	}
	
}
