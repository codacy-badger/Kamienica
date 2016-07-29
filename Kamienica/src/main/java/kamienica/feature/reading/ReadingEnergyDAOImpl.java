package kamienica.feature.reading;

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
import kamienica.feature.invoice.InvoiceEnergy;

/**
 * @author kdeveloper
 *
 */
/**
 * @author kdeveloper
 *
 */
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
	public List<ReadingEnergy> getByDate(String readingDate) {
		Query query = getSession().createSQLQuery("SELECT * FROM readingEnergy where readingDate=:date")
				.addEntity(ReadingEnergy.class).setString("date", readingDate);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingEnergy> getLatestList(Set<Long> meterId) {

		String test = "Select * from readingenergy where readingDate=(select MAX(readingDate) from readingEnergy) AND meter_id IN(:list)";
		Query query = getSession().createSQLQuery(test).addEntity(ReadingEnergy.class).setParameterList("list",
				meterId);

		return query.list();

	}

	@Override
	public List<ReadingEnergy> getPrevious(String readingDate, Set<Long> meterId) {
		Query query = getSession()
				.createSQLQuery(
						"SELECT * FROM readingEnergy where readingDate=(SELECT max(readingDate) FROM readingEnergy WHERE readingDate < :date )  AND meter_id IN(:list)")
				.addEntity(ReadingEnergy.class).setString("date", readingDate.toString())
				.setParameterList("list", meterId);
		@SuppressWarnings("unchecked")
		List<ReadingEnergy> result = query.list();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadingEnergy> getUnresolvedReadings() {
		Query query = getSession().createSQLQuery("SELECT r.id, r.readingDate, r.value, r.unit, r.meter_id, r.resolved "
				+ "FROM readingenergy r join meterEnergy m on r.meter_id = m.id "
				+ "where r.resolved = 0 and m.apartment_id is null").addEntity(ReadingEnergy.class);
		System.out.println("99999999999999");
		System.out.println(query.list());
		System.out.println("99999999999999");
		return query.list();

	}

	@Override
	public void resolveReadings(InvoiceEnergy invoice) {
		Query query = getSession()
				.createSQLQuery("update readingenergy " + "set resolved= :res " + "where readingDate = :paramdate")
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

	@Override
	public void deleteLatestReadings(LocalDate date) {
		Query query = getSession()
				.createSQLQuery("delete from readingenergy where readingDate=:date and resolved=:res");
		query.setParameter("date", date.toString()).setParameter("res", false);
		query.executeUpdate();

	}

	@Override
	public LocalDate getLatestDate() {
		Criteria criteria = getSession().createCriteria(ReadingEnergy.class)
				.setProjection(Projections.max("readingDate"));
		return (LocalDate) criteria.uniqueResult();
	}

}
