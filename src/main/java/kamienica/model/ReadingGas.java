package kamienica.model;

import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="readinggas", uniqueConstraints = {@UniqueConstraint(columnNames = {"readingDate", "meter_id"})})
public class ReadingGas extends Reading implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterGas meter;

	public String getUnit() {
		return this.unit;
	}

	public boolean belongsToCWUMeter() {
		return this.meter.isCwu();
	}

	public ReadingGas(LocalDate date, double value, MeterGas meter) {
		super(date, value);
		this.unit = meter.getUnit();
		this.meter = meter;
	}

	public ReadingGas() {
	}

	@Override
	public MeterGas getMeter() {
		return meter;
	}

	public void setMeter(MeterGas meter) {
		this.meter = meter;
	}

	@Override
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		String nrMieszkania;
		if (this.meter.getApartment() == null) {
			nrMieszkania = "null";
		} else {
			nrMieszkania = this.meter.getApartment().getDescription();
		}
		return super.toString() + " ->  " + nrMieszkania;
	}

}