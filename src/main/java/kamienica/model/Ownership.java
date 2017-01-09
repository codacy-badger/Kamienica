package kamienica.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "OWNERSHIP")
public class Ownership {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany
    private Set<Residence> residenceOwned;
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

    public Set<Residence> getResidenceOwned() {
        return residenceOwned;
    }

    public void setResidenceOwned(Set<Residence> residenceOwned) {
        this.residenceOwned = residenceOwned;
    }
}
