package kamienica.forms;

import java.util.Date;
import java.util.List;

import kamienica.model.Invoice;

public class ReadingInvoiceForm {

	private Invoice invoice;
	private Date date;

	public ReadingInvoiceForm() {

	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ReadingInvoiceBinder [invoice=" + invoice + ", date=" + date + "]";
	}

	public ReadingInvoiceForm(List<Invoice> invoiceList, List<Date> dateList) {
		this.date = dateList.get(0);
		this.invoice = invoiceList.get(0);
		dateList.remove(0);
		invoiceList.remove(0);
	}
}
