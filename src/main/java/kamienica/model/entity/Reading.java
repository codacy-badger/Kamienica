package kamienica.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "READING")
public class Reading {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @ManyToOne
    private ReadingDetails readingDetails;
    @Column(nullable = false)
    private double value;
    @ManyToOne
    private Residence residence;
    @ManyToOne
    private Meter meter;

    public Reading() {
    }

    public Reading(ReadingDetails readingDetails, double value, Residence residence, Meter meter) {
        this.readingDetails = readingDetails;
        this.value = value;
        this.residence = residence;
        this.meter = meter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReadingDetails getReadingDetails() {
        return readingDetails;
    }

    public void setReadingDetails(ReadingDetails readingDetails) {
        this.readingDetails = readingDetails;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }
}