package kamienica.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "CONSUMPTION")
public class Consumption {

    @Id
    @GeneratedValue
    private Long ID;

    private LocalDate DATE;
    private double VALUE;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDate getDATE() {
        return DATE;
    }

    public void setDATE(LocalDate DATE) {
        this.DATE = DATE;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }
}
