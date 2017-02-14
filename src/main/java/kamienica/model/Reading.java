package kamienica.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@MappedSuperclass
@Inheritance
public abstract class Reading {

    @Id
    @GeneratedValue
    @Column
    protected Long id;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    protected LocalDate readingDate;
    @Column(nullable = false)
    protected double value;
    @Column
    protected boolean resolved = false;
    @ManyToOne
    protected Residence residence;

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


    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
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

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
