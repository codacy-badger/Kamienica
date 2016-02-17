//package kamienica.dao.invoice;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import kamienica.model.Invoice;
//import kamienica.model.InvoiceEnergy;
//import kamienica.model.InvoiceGas;
//import kamienica.model.InvoiceWater;
//import kamienica.model.PaymentEnergy;
//import kamienica.model.PaymentGas;
//import kamienica.model.PaymentWater;
//
//@Repository("invoiceDao")
//public class InvoiceDaoImpl implements InvoiceDao {
//
//	@Autowired
//	private SessionFactory sessionfactory;
//
//	@Override
//	public void save(Invoice invoice) {
//
//		sessionfactory.getCurrentSession().saveOrUpdate(invoice);
//	}
//
//	@Override
//	public void deleteGasByID(int id) {
//		InvoiceGas invoice = (InvoiceGas) sessionfactory.getCurrentSession().load(InvoiceGas.class, id);
//
//		if (invoice != null) {
//
//			Session session = this.sessionfactory.getCurrentSession();
//			session.beginTransaction();
//			session.delete(invoice);
//			session.flush();
//			session.getTransaction().commit();
//			session.close();
//
//		}
//
//	}
//
//	@Override
//	public void deleteWaterByID(int id) {
//		InvoiceWater invoice = (InvoiceWater) sessionfactory.getCurrentSession().load(InvoiceWater.class, id);
//
//		if (invoice != null) {
//
//			Session session = this.sessionfactory.getCurrentSession();
//			session.beginTransaction();
//			session.delete(invoice);
//			session.flush();
//			session.getTransaction().commit();
//			session.close();
//
//		}
//
//	}
//
//	@Override
//	public void deleteEnergyByID(int id) {
//		InvoiceEnergy invoice = (InvoiceEnergy) sessionfactory.getCurrentSession().load(InvoiceEnergy.class, id);
//
//		if (invoice != null) {
//
//			Session session = this.sessionfactory.getCurrentSession();
//			session.beginTransaction();
//			session.delete(invoice);
//			session.flush();
//			session.getTransaction().commit();
//			session.close();
//
//		}
//
//	}
//
//	@Override
//	public void update(Invoice invoice) {
//		Session session = this.sessionfactory.getCurrentSession();
//		session.beginTransaction();
//		session.update(invoice);
//		session.flush();
//		session.getTransaction().commit();
//		session.close();
//
//	}
//
//	@Override
//	public Invoice getGasByID(int id) {
//		Session session = this.sessionfactory.getCurrentSession();
//		session.beginTransaction();
//		Invoice out = (InvoiceGas) session.get(InvoiceGas.class, new Integer(id));
//		session.close();
//		return out;
//	}
//
//	@Override
//	public Invoice getWaterByID(int id) {
//		Session session = this.sessionfactory.getCurrentSession();
//
//		session.beginTransaction();
//		Invoice out = (InvoiceWater) session.get(InvoiceWater.class, new Integer(id));
//		session.close();
//		return out;
//	}
//
//	@Override
//	public Invoice getEnergyByID(int id) {
//		Session session = this.sessionfactory.getCurrentSession();
//		session.beginTransaction();
//		Invoice out = (InvoiceEnergy) session.get(InvoiceEnergy.class, new Integer(id));
//		session.close();
//		return out;
//	}
//
//	@Override
//	public InvoiceGas getLatestGas() {
//		Session session = this.sessionfactory.getCurrentSession();
//		Query query = session
//				.createSQLQuery(
//						"SELECT * from kamienica.invoicegas where date = (select MAX(date) from kamienica.invoicegas)")
//				.addEntity(InvoiceGas.class);
//		@SuppressWarnings("unchecked")
//		List<InvoiceGas> invoice = query.list();
//
//		return invoice.get(0);
//	}
//
//	@Override
//	public InvoiceWater getLatestWater() {
//		Session session = this.sessionfactory.getCurrentSession();
//		Query query = session
//				.createSQLQuery(
//						"SELECT * from kamienica.invoicewater where date = (select MAX(date) from kamienica.invoicewater)")
//				.addEntity(InvoiceWater.class);
//		@SuppressWarnings("unchecked")
//		List<InvoiceWater> invoice = query.list();
//
//		return invoice.get(0);
//	}
//
//	@Override
//	public InvoiceEnergy getLatestEnergy() {
//		Session session = this.sessionfactory.getCurrentSession();
//		Query query = session
//				.createSQLQuery(
//						"select * from kamienica.invoiceenergy where date = (select MAX(date) from kamienica.invoiceenergy)")
//				.addEntity(InvoiceEnergy.class);
//		@SuppressWarnings("unchecked")
//		List<InvoiceEnergy> invoice = query.list();
//
//		return invoice.get(0);
//	}
//
////	@Override
////	public List<InvoiceWater> getInvoicesWaterForPayment(PaymentWater payment) {
////		Session session = this.sessionfactory.getCurrentSession();
////		String sql = "";
////		if (payment.getId() < 1) {
////			sql = "select * from kamienica.invoicewater order by date asc";
////		} else {
////			sql = "select * from kamienica.invoicewater where date >= (select date from kamienica.invoicewater where id = "
////					+ payment.getInvoice().getId() + ") order by date asc ";
////		}
////		Query query = session.createSQLQuery(sql).addEntity(InvoiceGas.class);
////		@SuppressWarnings("unchecked")
////		List<InvoiceWater> invoice = query.list();
////
////		return invoice;
////	}
////
////	@Override
////	public List<InvoiceGas> getInvoicesGasForPayment(PaymentGas payment) {
////		Session session = this.sessionfactory.getCurrentSession();
////		String sql = "";
////		if (payment.getId() < 1) {
////			sql = "select * from kamienica.invoicegas order by date asc";
////		} else {
////			sql = "select * from kamienica.invoicegas where date >= (select date from kamienica.invoicegas where id = "
////					+ payment.getInvoice().getId() + ") order by date asc ";
////		}
////		Query query = session.createSQLQuery(sql).addEntity(InvoiceGas.class);
////		@SuppressWarnings("unchecked")
////		List<InvoiceGas> invoice = query.list();
////
////		return invoice;
////	}
////
////	@Override
////	public List<InvoiceEnergy> getInvoicesEnergyForPayment(PaymentEnergy payment) {
////		Session session = this.sessionfactory.getCurrentSession();
////		System.out.println("getWaterReadingDatesForPayment!!!!!!" +payment.toString() +"  id  " +payment.getId());
////		String sql = "";
////		if (payment.getId() < 1) {
////			sql = "select * from kamienica.invoiceenergy order by date asc";
////		} else {
////			sql = "select * from kamienica.invoiceenergy where date >= (select date from kamienica.invoiceenergy where id = "
////					+ payment.getInvoice().getId() + ") order by date asc ";
////		}
////		Query query = session.createSQLQuery(sql).addEntity(InvoiceGas.class);
////		@SuppressWarnings("unchecked")
////		List<InvoiceEnergy> invoice = query.list();
////
////		return invoice;
////	}
//
//	@Override
//	public List<InvoiceWater> getWaterInvoiceList() {
//		@SuppressWarnings("unchecked")
//		List<InvoiceWater> list = sessionfactory.getCurrentSession().createCriteria(InvoiceWater.class)
//				.addOrder(Order.desc("date")).list();
//		return list;
//	}
//
//	@Override
//	public List<InvoiceEnergy> getEnergyInvoiceList() {
//		@SuppressWarnings("unchecked")
//		List<InvoiceEnergy> list = sessionfactory.getCurrentSession().createCriteria(InvoiceEnergy.class)
//				.addOrder(Order.desc("date")).list();
//		return list;
//	}
//
//	@Override
//	public List<InvoiceGas> getGasInvoiceList() {
//		@SuppressWarnings("unchecked")
//		List<InvoiceGas> list = sessionfactory.getCurrentSession().createCriteria(InvoiceGas.class)
//				.addOrder(Order.desc("date")).list();
//		return list;
//	}
//
//	@Override
//	public List<Invoice> getInvoicesWaterForCalulation(Invoice first, Invoice second) {
//		Session session = this.sessionfactory.getCurrentSession();
//		System.out.println("INVOICE water DAO");
//		System.out.println(first.toString());
//		System.out.println(second.toString());
//		Query query = session
//				.createSQLQuery(
//						"select * from kamienica.invoicewater where date >  :date1 and date <= :date2 order by date asc")
//				.addEntity(InvoiceWater.class).setParameter("date1",first.getDate()).setParameter("date2", second.getDate());
//		@SuppressWarnings("unchecked")
//		List<Invoice> invoice = query.list();
//		return invoice;
//	}
//
//	@Override
//	public List<Invoice> getInvoicesGasForCalulation(Invoice first, Invoice second) {
//		Session session = this.sessionfactory.getCurrentSession();
//		System.out.println("INVOICE gas DAO");
//		System.out.println(first.toString());
//		System.out.println(second.toString());
//		Query query = session
//				.createSQLQuery(
//						"select * from kamienica.invoicegas where date >  :date1 and date <= :date2 order by date asc")
//				.addEntity(InvoiceGas.class).setParameter("date1",first.getDate()).setParameter("date2", second.getDate());
//		@SuppressWarnings("unchecked")
//		List<Invoice> invoice = query.list();
//		return invoice;
//	}
//
//	@Override
//	public List<Invoice> getInvoicesEnergyForCalulation(Invoice first, Invoice second) {
//		System.out.println("INVOICE ENERGY DAO");
//		System.out.println(first.toString());
//		System.out.println(second.toString());
//		Session session = this.sessionfactory.getCurrentSession();
//		Query query = session
//				.createSQLQuery(
//						"select * from kamienica.invoiceenergy where date >  :date1 and date <= :date2 order by date asc")
//				.addEntity(InvoiceEnergy.class).setParameter("date1",first.getDate()).setParameter("date2", second.getDate());
//		@SuppressWarnings("unchecked")
//		List<Invoice> invoice = query.list();
//		return invoice;
//	}
//
//}
