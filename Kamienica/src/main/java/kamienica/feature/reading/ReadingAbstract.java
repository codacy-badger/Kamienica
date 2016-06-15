package kamienica.feature.reading;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import kamienica.feature.meter.MeterAbstract;

@MappedSuperclass
@Inheritance
public abstract class ReadingAbstract {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
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
	public ReadingAbstract(LocalDate readingDate, double value) {
		this.readingDate = readingDate;
		this.value = value;
	}

	public ReadingAbstract() {
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

	public abstract MeterAbstract getMeter();
}
