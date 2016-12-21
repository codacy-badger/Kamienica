package kamienica.feature.reading;

import kamienica.feature.meter.Meter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@MappedSuperclass
@Inheritance
public abstract class Reading {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate readingDate;
	@Column(nullable = false)
	private double value;
	@Column
	private boolean resolved = false;

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	@Autowired
	public Reading(LocalDate readingDate, double value) {
		this.readingDate = readingDate;
		this.value = value;
	}

	public Reading() {
	}

	@Override
	public String toString() {

		return "\nData Odczytu: " + readingDate + ", Zuzycie= " + value;
	}

	public LocalDate getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(LocalDate readingDate) {
		this.readingDate = readingDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract String getUnit();
	
	public abstract void setUnit(String unit);
	
	public abstract Meter getMeter();
}