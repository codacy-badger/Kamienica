package kamienica.dao.invoice;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.InvoiceGas;
import kamienica.model.PaymentGas;

public interface InvoiceGasDAO {

	public void save(InvoiceGas invoice);

	public List<InvoiceGas> getList();

	public void update(InvoiceGas invoice);

	public void deleteByID(int id);

	public InvoiceGas getById(int id);

	public InvoiceGas getLatest();

	public List<InvoiceGas> getInvoicesForPayment(PaymentGas payment);

	public List<InvoiceGas> getInvoicesForCalulation(Invoice invoice);

	List<InvoiceGas> getUnpaidInvoices();
}
