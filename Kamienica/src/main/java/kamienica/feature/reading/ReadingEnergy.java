package kamienica.feature.reading;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kamienica.feature.meter.MeterEnergy;

@Entity
@Table(name="readingenergy")
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
		return "ReadingEnergy [unit=" + unit + ", meter=" + meter + ", isResolved()=" + isResolved() + ", toString()="
				+ super.toString() + ", getReadingDate()=" + getReadingDate() + ", getValue()=" + getValue()
				+ ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
