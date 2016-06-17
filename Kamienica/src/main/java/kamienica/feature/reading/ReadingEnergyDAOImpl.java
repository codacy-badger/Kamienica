package kamienica.feature.reading;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceEnergy;

@Repository("readingEnergyDao")
public class ReadingEnergyDAOImpl extends AbstractDao<Long, ReadingEnergy>
		implements ReadingDao<ReadingEnergy, InvoiceEnergy> {

	@Override
	public List<ReadingEnergy> getList() {
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> list = getSession().createCriteria(ReadingEnergy.class).addOrder(Order.desc("readingDate"))
				.list();
		return list;
	}

	@Override
	public List<ReadingEnergy> getListForTenant(Apartment apartment) {
		String old = "select * from readingenergy where meter_id IN(select id from meterenergy where apartment_id IN(SELECT id FROM apartment where apartmentnumber IN(0, :num))) order by readingDate desc;";
		// String newer = "select * from readingenergy join ";
		Query query = getSession().createSQLQuery(old).addEntity(ReadingEnergy.class).setInteger("num",
				apartment.getApartmentNumber());
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	@Override
	public List<ReadingEnergy> getPrevious(String readingDate) {
		Query query = getSession()
				.createSQLQuery(
						"SELECT * FROM readingEnergy where readingDate =(SELECT max(readingDate) FROM readingEnergy WHERE readingDate < :date )")
				.addEntity(ReadingEnergy.class).setString("date", readingDate);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	@Override
	public List<ReadingEnergy> getByDate(String readingDate) {
		Query query = getSession().createSQLQuery("SELECT * FROM readingEnergy where readingDate=:date")
				.addEntity(ReadingEnergy.class).setString("date", readingDate);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingEnergy> getLatestList() {
		// String original = "Select * from (select * from readingEnergy order
		// by readingDate desc) as c group by meter_id";
		String test = "Select * from readingEnergy where readingDate=(select MAX(readingDate) from readingEnergy)";
		Query query = getSession().createSQLQuery(test).addEntity(ReadingEnergy.class);
		return query.list();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingEnergy> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readingenergy r join meterEnergy m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingEnergy.class);
		return query.list();

	}

	@Override
	public void resolveReadings(InvoiceEnergy invoice) {
		Query query = getSession()
				.createSQLQuery("update readingenergy set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", true);
		query.executeUpdate();

	}

	@Override
	public void unresolveReadings(InvoiceEnergy invoice) {
		Query query = getSession()
				.createSQLQuery("update readingenergy set resolved= :res where readingDate = :paramdate")
				.setDate("paramdate", invoice.getBaseReading().getReadingDate().toDate()).setParameter("res", false);
		query.executeUpdate();

	}

	@Override
	public int countDaysFromLastReading() {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT DATEDIFF(CURDATE()  ,readingenergy.readingDate) FROM readingenergy order by readingDate  desc limit 1");
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

}
