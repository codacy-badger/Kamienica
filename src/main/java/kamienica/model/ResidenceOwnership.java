package kamienica.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RESIDENCE_OWNERSHIP"
        //, uniqueConstraints = {@UniqueConstraint(columnNames = {"readingDate", "te_id"})}
)
public class ResidenceOwnership {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Residence residenceOwned;
    @OneToOne
    private Tenant owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getOwner() {
        return owner;
    }

    public void setOwner(Tenant owner) {
        this.owner = owner;
    }

    public Residence getResidenceOwned() {
        return residenceOwned;
    }

    public void setResidenceOwned(Residence residenceOwned) {
        this.residenceOwned = residenceOwned;
    }
}
