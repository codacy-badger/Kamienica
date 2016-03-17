//package kamienica.dao.invoice;
//
//import java.util.List;
//
//import kamienica.dao.DaoInterface;
//import kamienica.model.Invoice;
//import kamienica.model.InvoiceEnergy;
//
//public interface InvoiceEnergyDAO extends DaoInterface<InvoiceEnergy> {
//
//	public InvoiceEnergy getLatest();
//
//	public List<InvoiceEnergy> getInvoicesForCalulation(Invoice invoice);
//
//	public List<InvoiceEnergy> getUnpaidInvoices();
//
//	public InvoiceEnergy getLastResolved();
//
//	public void resolveInvoice(InvoiceEnergy invoice);
//
//	public void unresolveInvoice(int id);
//
//}
