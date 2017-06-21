package kamienica.model.entity;

import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "METER", uniqueConstraints = {@UniqueConstraint(columnNames = {"description", "serialNumber"})})
public class Meter {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź wartość")
    private String description;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Wprowadź wartość")
    private String serialNumber;
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź wartość")
    private String unit;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Residence residence;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean main = false;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private boolean cwu;
    @Column(nullable = false)
    private boolean isWarmWater;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Media media;

    public Meter() {
    }

    public Meter(String description, String serialNumber, String unit, Apartment apartment, Residence residence, boolean main, Status status, boolean cwu, boolean isWarmWater, Media media) {
        this.description = description;
        this.serialNumber = serialNumber;
        this.unit = unit;
        this.apartment = apartment;
        this.residence = residence;
        this.main = main;
        this.status = status;
        this.cwu = cwu;
        this.isWarmWater = isWarmWater;
        this.media = media;
    }

    public Meter(String description, String serialNumber, String unit, Apartment apartment, Media media) {
        this.description = description;
        this.serialNumber = serialNumber;
        this.unit = unit;
        this.apartment = apartment;
        this.residence = apartment.getResidence();
        this.main = false;
        this.status = Status.ACTIVE;
        this.cwu = false;
        this.isWarmWater = false;
        this.media = media;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isCwu() {
        return cwu;
    }

    public void setCwu(boolean cwu) {
        this.cwu = cwu;
    }

    public boolean isWarmWater() {
        return isWarmWater;
    }

    public void setWarmWater(boolean warmWater) {
        isWarmWater = warmWater;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", unit='" + unit + '\'' +
                ", apartment=" + apartment +
                ", residence=" + residence +
                ", main=" + main +
                ", status=" + status +
                ", cwu=" + cwu +
                ", isWarmWater=" + isWarmWater +
                ", media=" + media +
                '}';
    }
}
