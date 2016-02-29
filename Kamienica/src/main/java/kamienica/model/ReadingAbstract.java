package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Inheritance
public abstract class ReadingAbstract {

	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date readingDate;
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
	public ReadingAbstract(Date readingDate, double value) {
		this.readingDate = readingDate;
		this.value = value;
	}

	public ReadingAbstract() {
	}

	@Override
	public String toString() {

		return "\nData Odczytu: " + readingDate + ", Zuzycie= " + value;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public abstract MeterAbstract getMeter();
}
