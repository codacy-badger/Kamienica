package kamienica.model.entity;

import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "READING_DETAILS", uniqueConstraints = {@UniqueConstraint(columnNames = {"residence_id", "readingDate", "media"})})
public class ReadingDetails extends DBEntity implements Comparable<ReadingDetails> {

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

    public ReadingDetails(LocalDate readingDate, Media media, Residence r) {
        this.readingDate = readingDate;
        this.resolvement = Resolvement.UNRESOLVED;
        this.media = media;
        this.residence = r;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadingDetails details = (ReadingDetails) o;

        if (!readingDate.equals(details.readingDate)) return false;
        if (resolvement != details.resolvement) return false;
        if (media != details.media) return false;
        return residence.equals(details.residence);
    }

    @Override
    public int hashCode() {
        int result = readingDate.hashCode();
        result = 31 * result + resolvement.hashCode();
        result = 31 * result + media.hashCode();
        result = 31 * result + residence.hashCode();
        return result;
    }

    @Override
    public int compareTo(ReadingDetails o) {
        if(!this.media.equals(o.getMedia())) {
            throw new ClassCastException("Compared ReadingDetails are of different media type");
        }
       return this.getReadingDate().compareTo(o.getReadingDate());
    }
}
