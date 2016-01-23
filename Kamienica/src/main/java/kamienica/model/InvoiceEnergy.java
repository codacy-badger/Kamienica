package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceEnergy extends Invoice {

	@Override
	public String toString() {
		return super.toString();
	}

	public InvoiceEnergy() {
		super();
		super.setDescription("Faktura Za Energię");
	}

	public InvoiceEnergy(String serialNumber, String description, Date date, double totalAmount) {
		super(serialNumber, description, date, totalAmount);

	}

}
