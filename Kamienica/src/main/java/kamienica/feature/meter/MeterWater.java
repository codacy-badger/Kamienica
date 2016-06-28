package kamienica.feature.meter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.apartment.Apartment;

@Entity
@Table(name="meterwater")
public class MeterWater extends MeterAbstract implements Serializable {
	@Column(nullable = false)
	private boolean isWarmWater;

	@Autowired
	public MeterWater(String description, String serialNumber, String unit, Apartment apartment, boolean isWarmWater) {
		super(description, serialNumber, unit, apartment);

		this.isWarmWater = isWarmWater;
	}

	public MeterWater() {
		super.setUnit("m3");
		this.isWarmWater = false;
	}

	public void setIsWarmWater(boolean isWarmWater) {
		this.isWarmWater = isWarmWater;
	}

	public boolean getIsWarmWater() {
		return isWarmWater;
	}

	@Override
	public String toString() {
		String opisMieszkania;
		try {
			opisMieszkania = apartment.getDescription();
		} catch (NullPointerException e) {
			opisMieszkania = "brak";
		}
		return "\n" + description + ", nrSeryjnyLicznika:" + serialNumber + ", Mieszkanie:" + opisMieszkania
				+ ", CieplaWoda: " + isWarmWater;
	}

}
