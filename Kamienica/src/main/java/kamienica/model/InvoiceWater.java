package kamienica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceWater extends Invoice {

	public InvoiceWater() {
		super();
		super.setDescription("Faktura Za Wodę");
	}
	
	public InvoiceWater(String serialNumber, String description, Date date, double totalAmount) {
		super(serialNumber, description, date, totalAmount);

	}

	@Override
	public String toString() {
		return super.toString();
	}

}
