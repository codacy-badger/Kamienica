package kamienica.model.entity;

import kamienica.model.enums.Media;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "INVOICE", uniqueConstraints = {@UniqueConstraint(columnNames = {"invoiceDate", "media", "residence_id"})})
public class Invoice extends DBEntity {

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Podaj wartość")
    private String serialNumber;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Wprowadź datę")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate invoiceDate;
    @Column(nullable = false)
    @Min(value = 0, message = "Tylko wartości dodatnie")
    @NotNull(message = "Podaj wartość")
    private double totalAmount;
    @OneToOne
    private Residence residence;
    @OneToOne
    private ReadingDetails readingDetails;
    @Enumerated(EnumType.STRING)
    private Media media;

    public Invoice() {
    }

    public Invoice(String serialNumber, LocalDate invoiceDate, double totalAmount, Residence residence, ReadingDetails readingDetails, Media media) {
        this.serialNumber = serialNumber;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.residence = residence;
        this.readingDetails = readingDetails;
        this.media = media;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public ReadingDetails getReadingDetails() {
        return readingDetails;
    }

    public void setReadingDetails(ReadingDetails readingDetails) {
        this.readingDetails = readingDetails;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "serialNumber='" + serialNumber + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", totalAmount=" + totalAmount +
                ", residence=" + residence +
                ", readingDetails=" + readingDetails +
                '}';
    }
}
