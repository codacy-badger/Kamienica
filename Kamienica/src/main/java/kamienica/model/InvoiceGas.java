package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceGas extends Invoice {

	public InvoiceGas() {
		super();
		super.setDescription("Faktura Za Gaz");
	}

	public InvoiceGas(String serialNumber, String description, Date date, double totalAmount) {
		super(serialNumber, description, date, totalAmount);

	}

	@Override
	public String toString() {
		return super.toString();
	}

}
