//package kamienica.dao.invoice;
//
//import java.util.List;
//
//import kamienica.dao.DaoInterface;
//import kamienica.model.Invoice;
//import kamienica.model.InvoiceGas;
//
//public interface InvoiceGasDAO extends DaoInterface<InvoiceGas> {
//
//	public InvoiceGas getLatest();
//
//	public List<InvoiceGas> getInvoicesForCalulation(Invoice invoice);
//
//	public List<InvoiceGas> getUnpaidInvoices();
//
//	public InvoiceGas getLastResolved();
//
//	public void resolveInvoice(InvoiceGas invoice);
//
//	public void unresolveInvoice(int id);
//}
