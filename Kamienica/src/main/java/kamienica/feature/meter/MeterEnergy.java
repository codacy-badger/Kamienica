package kamienica.feature.meter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.apartment.Apartment;

@Entity
@Table(name="meterenergy")
public class MeterEnergy extends MeterAbstract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1767126420777438931L;

	@Autowired
	public MeterEnergy(String description, String serialNumber, String unit, Apartment apartment) {
		super(description, serialNumber, unit, apartment);

	}

	public MeterEnergy() {
		super.setUnit("kWh");
	}

}