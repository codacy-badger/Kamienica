package kamienica.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class History {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private LocalDate ocurrence;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getOcurrence() {
        return ocurrence;
    }

    public void setOcurrence(LocalDate ocurrence) {
        this.ocurrence = ocurrence;
    }
}
