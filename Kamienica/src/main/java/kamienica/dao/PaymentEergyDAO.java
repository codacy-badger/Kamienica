//package kamienica.dao;
//
//import java.util.List;
//
//import kamienica.model.Invoice;
//import kamienica.model.PaymentEnergy;
//import kamienica.model.ReadingEnergy;
//import kamienica.model.Tenant;
//
//public interface PaymentEergyDAO extends DaoInterface<PaymentEnergy> {
//
//	public void deleteByDate(String date);
//
//	public List<PaymentEnergy> getByInvoice(Invoice invoice);
//
//	public List<PaymentEnergy> getByReading(ReadingEnergy reading);
//
//	public List<PaymentEnergy> getPaymentForTenant(Tenant tenant);
//
//	public PaymentEnergy getLatestPayment();
//
//}
