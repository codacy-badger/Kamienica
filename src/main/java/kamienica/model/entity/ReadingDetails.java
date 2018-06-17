package kamienica.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kamienica.core.util.JodaDateSerializer;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "READING_DETAILS", uniqueConstraints = {@UniqueConstraint(columnNames = {"residence_id", "readingDate", "media"})})
public class ReadingDetails extends DBEntity implements Comparable<ReadingDetails> {

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = JodaDateSerializer.class)
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
        final ReadingDetails details = (ReadingDetails) o;
        return Objects.equals(readingDate, details.readingDate) &&
                resolvement == details.resolvement &&
                media == details.media &&
                Objects.equals(residence, details.residence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readingDate, resolvement, media, residence);
    }

    @Override
    public int compareTo(ReadingDetails o) {
        if(!this.media.equals(o.getMedia())) {
            throw new ClassCastException("Compared ReadingDetails are of different media type");
        }
       return this.getReadingDate().compareTo(o.getReadingDate());
    }
}
