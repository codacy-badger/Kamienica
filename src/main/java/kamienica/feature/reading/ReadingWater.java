package kamienica.feature.reading;

import kamienica.feature.meter.MeterWater;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "readingwater")
public class ReadingWater extends Reading implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterWater meter;
//	@OneToOne
//	ReadingWater nextReading;
//
//	public ReadingWater getNextReading() {
//		return nextReading;
//	}
//
//	public void setNextReading(ReadingWater nextReading) {
//		this.nextReading = nextReading;
//	}

	public String getUnit() {
		return this.unit;
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

	@Override
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