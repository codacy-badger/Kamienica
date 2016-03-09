package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ReadingWater extends ReadingAbstract {

	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterWater meter;

	public String getUnit() {
		return this.meter.getUnit();
	}

	public ReadingWater(Date date, double value, MeterWater meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	public ReadingWater() {
	}

	public MeterWater getMeter() {
		return meter;
	}

	public void setLMeter(MeterWater meter) {
		this.meter = meter;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setMeter(MeterWater meter) {
		this.meter = meter;
	}

	@Override
	public String toString() {
		return "ReadingWater [unit=" + unit + ", meter=" + meter + ", toString()=" + super.toString() + "]";
	}

}