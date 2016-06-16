package kamienica.feature.reading;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.meter.MeterWater;

@Entity
@Table(name = "readingwater")
public class ReadingWater extends ReadingAbstract implements Serializable {

	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterWater meter;

	public String getUnit() {
		return this.meter.getUnit();
	}

	public ReadingWater(LocalDate date, double value, MeterWater meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	public ReadingWater() {
	}

	@Override
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