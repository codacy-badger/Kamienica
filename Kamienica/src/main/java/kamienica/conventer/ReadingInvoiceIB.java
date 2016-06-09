package kamienica.conventer;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.wrapper.ReadingInvoiceForm;

public class ReadingInvoiceIB extends PropertyEditorSupport {

	private final InvoiceService invoiceService;
	

	public ReadingInvoiceIB(InvoiceService invoiceService) {
		this.invoiceService= invoiceService;
			}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] array = text.split("&");
		ReadingInvoiceForm tmp = new ReadingInvoiceForm();
		if (array[0].equals("energy")) {
			InvoiceEnergy inv = (InvoiceEnergy) invoiceService.getEnergyByID(Integer.valueOf(array[1]));
			try {
				tmp.setDate(df.parse(array[2]));
			} catch (ParseException e) {
				
			}
			tmp.setInvoice(inv);
			setValue(tmp);
		}
		
		if (array[0].equals("water")) {
			InvoiceWater inv = (InvoiceWater) invoiceService.getWaterByID(Integer.valueOf(array[1]));
			try {
				tmp.setDate(df.parse(array[2]));
			} catch (ParseException e) {
				
			}
			tmp.setInvoice(inv);
			setValue(tmp);
		}
		
		if (array[0].equals("gas")) {
			InvoiceGas inv = (InvoiceGas) invoiceService.getGasByID(Integer.valueOf(array[1]));
			try {
				tmp.setDate(df.parse(array[2]));
			} catch (ParseException e) {
				
			}
			tmp.setInvoice(inv);
			setValue(tmp);
		}
	}

}
