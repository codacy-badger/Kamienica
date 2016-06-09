package kamienica.feature.invoice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kamienica.feature.reading.ReadingGas;

@Entity
@Table(name="invoicegas")
public class InvoiceGas extends Invoice {

	@OneToOne
	private ReadingGas baseReading;

	public ReadingGas getBaseReading() {
		return baseReading;
	}

	public void setBaseReading(ReadingGas baseReading) throws Exception {
		if (baseReading.getMeter().getApartment() != null) {
			throw new Exception();

		}
		this.baseReading = baseReading;
	}

	public InvoiceGas() {
		super();
		super.setDescription("Faktura Za Gaz");
	}

	public InvoiceGas(String serialNumber, String description, Date date, double totalAmount, ReadingGas reading) {
		super(serialNumber, description, date, totalAmount);
		this.baseReading = reading;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
