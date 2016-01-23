package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ReadingEnergy extends ReadingAbstract {
	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterEnergy meter;

	public ReadingEnergy() {
	}

	public ReadingEnergy(Date date, double value, MeterEnergy meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	public MeterEnergy getMeter() {
		return meter;
	}

	public String getUnit() {
		return this.meter.getUnit();
	}

	public void setMeter(MeterEnergy meter) {
		this.meter = meter;
	}

	public void setUnit(String jednostka) {
		this.unit = jednostka;
	}

	@Override
	public String toString() {
		return "OdczytEnergia dla " + this.meter.getDescription() + " Odczyt: " + this.getValue() +" ID: "+getId();
	}

}
