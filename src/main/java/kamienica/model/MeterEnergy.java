package kamienica.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="meterenergy")
public class MeterEnergy extends Meter implements Serializable {

	private static final long serialVersionUID = 1767126420777438931L;

	public MeterEnergy(String description, String serialNumber, String unit, Apartment apartment) {
		super(description, serialNumber, unit, apartment);

	}

	public MeterEnergy(String description, String serialNumber, String unit, Apartment apartment, Residence res) {
		super(description, serialNumber, unit, apartment, res);

	}

	public MeterEnergy() {
		super.setUnit("kWh");
	}

}