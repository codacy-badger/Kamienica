package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoicewater")
public class InvoiceWater extends Invoice {

	@OneToOne
	private ReadingWater baseReading;

	public ReadingWater getBaseReading() {
		return baseReading;
	}

	public void setBaseReading(ReadingWater baseReading) throws Exception {
		if (baseReading.getMeter().getApartment() != null) {
			throw new Exception();

		}
		this.baseReading = baseReading;
	}

	public InvoiceWater() {
		super();
		super.setDescription("Faktura Za Wodę");
	}

	public InvoiceWater(String serialNumber, String description, Date date, double totalAmount, ReadingWater reading) {
		super(serialNumber, description, date, totalAmount);
		this.baseReading = reading;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
