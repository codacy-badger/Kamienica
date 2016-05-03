package kamienica.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.apartment.Apartment;

@Entity
@Table(name="meterenergy")
public class MeterEnergy extends MeterAbstract {

	@Autowired
	public MeterEnergy(String description, String serialNumber, String unit, Apartment apartment) {
		super(description, serialNumber, unit, apartment);

	}

	public MeterEnergy() {
		super.setUnit("kWh");
	}

}