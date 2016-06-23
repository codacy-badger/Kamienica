package kamienica.feature.reading;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import kamienica.feature.meter.MeterGas;

@Entity
@Table(name="readinggas")
public class ReadingGas extends ReadingAbstract implements Serializable {

	@Column(nullable = false)
	private String unit;
	@ManyToOne
	MeterGas meter;
//	@OneToOne
//	ReadingGas nextReading;
//	
//	public ReadingGas getNextReading() {
//		return nextReading;
//	}
//
//	public void setNextReading(ReadingGas nextReading) {
//		this.nextReading = nextReading;
//	}

	public String getUnit() {
		return this.unit;
	}

	public boolean getIsCWU() {
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