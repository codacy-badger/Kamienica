//package kamienica.dao;
//
//import java.util.List;
//
//import kamienica.model.Invoice;
//import kamienica.model.PaymentGas;
//import kamienica.model.ReadingGas;
//import kamienica.model.Tenant;
//
//public interface PaymentGasDAO extends DaoInterface<PaymentGas> {
//
//	public void deleteByDate(String date);
//
//	public List<PaymentGas> getByInvoice(Invoice invoice);
//
//	public List<PaymentGas> getByReading(ReadingGas reading);
//
//	public PaymentGas getLatestPayment();
//
//	public List<PaymentGas> getPaymentForTenant(Tenant tenant);
//
//}
