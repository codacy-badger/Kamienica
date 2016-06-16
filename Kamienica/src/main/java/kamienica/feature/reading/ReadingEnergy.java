package kamienica.feature.reading;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.meter.MeterEnergy;

@Entity
@Table(name = "readingenergy")
public class ReadingEnergy extends ReadingAbstract implements Serializable {

	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterEnergy meter;
//	@OneToOne
//	ReadingEnergy nextReading;
//
//	public ReadingEnergy getNextReading() {
//		return nextReading;
//	}
//
//	public void setNextReading(ReadingEnergy nextReading) {
//		this.nextReading = nextReading;
//	}

	public ReadingEnergy() {
	}

	public ReadingEnergy(LocalDate date, double value, MeterEnergy meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	@Override
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
		return "ReadingEnergy [unit=" + unit + ", meter=" + meter + " " + super.toString() + "]";
	}

}
