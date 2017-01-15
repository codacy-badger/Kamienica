package kamienica.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENT_CONTRACT")
public class RentContract extends DBEntity {

    @OneToOne
    private Apartment apartment;
    @OneToOne
    private Tenant tenant;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate contractStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate contractEnd;
}
