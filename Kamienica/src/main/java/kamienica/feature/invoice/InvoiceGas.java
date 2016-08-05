package kamienica.feature.invoice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.reading.ReadingGas;

@Entity
@Table(name="invoicegas")
public class InvoiceGas extends Invoice implements Serializable{

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

	public InvoiceGas(String serialNumber, String description, LocalDate date, double totalAmount, ReadingGas reading) {
		super(serialNumber, description, date, totalAmount);
		this.baseReading = reading;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public LocalDate getReadingDate() {
		return baseReading.getReadingDate();
	}

}
