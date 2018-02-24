package kamienica.model.entity;

import kamienica.model.enums.UserRole;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;

@Entity
@Table(name = "TENANT")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    @NotEmpty(message = "Wprowadź imie")
    private String firstName;
    @Column
    @NotEmpty(message = "Wprowadź nazwisko")
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email(message = "Nieprawidłowy adres email")
    @NotEmpty(message = "Wprowadź adres email")
    private String email;
    @Column
    @Digits(integer = 100, fraction = 0, message = "Tylko wartości liczbowe")
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    private RentContract rentContract;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.TENANT;
    @Length(min = 5, message = "Hasło musi mieć minimum 5 znaków")
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź hasło")
    private String password = "witaj";

    public Tenant(String firstName, String lastName, String email, String phone, RentContract rentContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.rentContract = rentContract;
    }

    public Tenant() {
        this.password = "witaj";
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public RentContract getRentContract() {
        return rentContract;
    }

    public void setRentContract(RentContract rentContract) {
        this.rentContract = rentContract;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rentContract=" + rentContract +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }

    public Apartment fetchApartment() {
        return this.rentContract.getApartment();
    }

    public boolean checkIsOwner() {
        return this.getRole().equals(UserRole.OWNER);
    }

    public boolean checkIsAdmin() {
        return this.getRole().equals(UserRole.ADMIN);
    }

    public boolean checkIsTenant() {
        return this.getRole().equals(UserRole.TENANT);
    }

    public boolean checkIsActive() {
        if (checkIsAdmin() || checkIsOwner()) {
            return true;
        }
        final LocalDate now = new LocalDate();
        final RentContract rc = this.getRentContract();
        return rc.getContractStart().isBefore(now) && rc.getContractEnd().isAfter(now);
    }
}
