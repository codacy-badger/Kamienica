package kamienica.feature.invoice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.reading.ReadingEnergy;

@Entity
@Table(name="invoiceenergy")
public class InvoiceEnergy extends Invoice implements Serializable {
	
	@OneToOne
	private ReadingEnergy baseReading;

	

	public ReadingEnergy getBaseReading() {
		return baseReading;
	}

	public void setBaseReading(ReadingEnergy baseReading) throws Exception {
		if(baseReading.getMeter().getApartment() != null ) {
			throw new Exception();
			
		}
		this.baseReading = baseReading;
	}

	public InvoiceEnergy() {
		super();
		super.setDescription("Faktura Za Energię");
	}

	public InvoiceEnergy(String serialNumber, String description, LocalDate date, double totalAmount, ReadingEnergy reading) {
		super(serialNumber, description, date, totalAmount);

	}

	

}
