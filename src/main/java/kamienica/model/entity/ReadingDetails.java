package kamienica.model.entity;

import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "READING_DETAILS", uniqueConstraints = {@UniqueConstraint(columnNames = {"residence_id", "readingDate", "media"})})
public class ReadingDetails extends DBEntity {

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate readingDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Resolvement resolvement;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Media media;
    @ManyToOne
    private Residence residence;

    public ReadingDetails() {
    }

    public ReadingDetails(LocalDate readingDate, Resolvement resolvement, Media media) {
        this.readingDate = readingDate;
        this.resolvement = resolvement;
        this.media = media;
    }

    public ReadingDetails(LocalDate readingDate, Media media) {
        this.readingDate = readingDate;
        this.resolvement = Resolvement.UNRESOLVED;
        this.media = media;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public Resolvement getResolvement() {
        return resolvement;
    }

    public void setResolvement(Resolvement resolvement) {
        this.resolvement = resolvement;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return "ReadingDetails{" +
                "readingDate=" + readingDate +
                ", resolvement=" + resolvement +
                ", media=" + media +
                '}';
    }
}
