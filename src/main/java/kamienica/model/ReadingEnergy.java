package kamienica.model;

import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "readingenergy", uniqueConstraints = {@UniqueConstraint(columnNames = {"readingDate", "meter_id"})})
public class ReadingEnergy extends Reading implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String unit;
	@ManyToOne
	private MeterEnergy meter;

	public ReadingEnergy() {
	}

	public ReadingEnergy(LocalDate date, double value, MeterEnergy meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
		this.residence = meter.getResidence();
	}

	@Override
	public MeterEnergy getMeter() {
		return meter;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setMeter(MeterEnergy meter) {
		this.meter = meter;
	}

	@Override
	public void setUnit(String jednostka) {
		this.unit = jednostka;
	}

	@Override
	public String toString() {
		return "ReadingEnergy [unit=" + unit + ", meter=" + meter + " " + super.toString() + "]";
	}

}
