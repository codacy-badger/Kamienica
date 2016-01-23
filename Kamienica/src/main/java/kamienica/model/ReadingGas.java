package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ReadingGas extends ReadingAbstract {

	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterGas meter;

	public String getUnit() {
		return this.meter.getUnit();
	}

	public boolean getIsCWU() {
		return this.meter.isCwu();
	}

	public ReadingGas(Date date, double value, MeterGas meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	public ReadingGas() {
	}

	public MeterGas getMeter() {
		return meter;
	}

	public void setMeter(MeterGas meter) {
		this.meter = meter;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		String nrMieszkania = "";
		if (this.meter.getApartment() == null) {
			nrMieszkania = "null";
		} else {
			nrMieszkania = this.meter.getApartment().getDescription();
		}
		return super.toString() + " ->  " + nrMieszkania;
	}

}