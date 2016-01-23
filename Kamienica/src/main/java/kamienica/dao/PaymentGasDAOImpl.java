package kamienica.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Invoice;
import kamienica.model.PaymentGas;
import kamienica.model.ReadingGas;
import kamienica.model.Tenant;

@Repository("paymentGasDao")
public class PaymentGasDAOImpl extends AbstractDao<Integer, PaymentGas> implements PaymentGasDAO {

	public void delete(PaymentGas payment) {
		Query query = getSession().createSQLQuery("delete from paymentenergy where id = :id");
		query.setInteger("id", payment.getId());
		query.executeUpdate();
	}

	@Override
	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<PaymentGas> list = getSession().createCriteria(PaymentGas.class).add(Restrictions.eq("tenant", tenant)).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentGas> getGasByInvoice(Invoice invoice) {
		Query query = getSession().createSQLQuery("Select * from paymentenergy where invoice_id = :id")
				.addEntity(PaymentGas.class).setInteger("id", invoice.getId());
		List<PaymentGas> result = query.list();
		return result;
	}

	@Override
	public List<PaymentGas> getGasByReading(ReadingGas reading) {
		Query query = getSession().createSQLQuery("Select * from paymentenergy where readingdate =:date")
				.addEntity(PaymentGas.class).setDate("date", reading.getReadingDate());
		@SuppressWarnings("unchecked")
		List<PaymentGas> result = query.list();
		return result;
	}

	@Override
	public List<PaymentGas> getPaymentGas() {
		@SuppressWarnings("unchecked")
		List<PaymentGas> list = getSession().createCriteria(PaymentGas.class).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
		return list;
	}

	@Override
	public PaymentGas getLatestPaymentGas() {
		@SuppressWarnings("unchecked")

		List<PaymentGas> list = getSession().createCriteria(PaymentGas.class).addOrder(Order.desc("readingDate"))
				.list();
		if (list.isEmpty()) {
			return new PaymentGas();
		}
		return list.get(0);

	}

	public void saveGas(List<PaymentGas> payment) {

		for (int i = 0; i < payment.size(); i++) {
			PaymentGas tmp = payment.get(i);
			save(tmp);

		}
	}
}
