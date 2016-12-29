package kamienica.model;

import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tenant")
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
    @OneToOne
    private Apartment apartment;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Residence> residence;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.TENANT;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate movementDate;
    @Length(min = 5, message = "Hasło musi mieć minimum 5 znaków")
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź hasło")
    private String password = "witaj";

    @Autowired
    public Tenant(String firstName, String lastName, String email, String phone, Apartment apartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.apartment = apartment;
        this.phone = phone;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Tenant() {
        this.movementDate = new LocalDate();
        this.password = "witaj";
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

    public LocalDate getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	@Override
	public String toString() {
		return "Tenant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", apartment=" + apartment + ", role=" + role + ", status=" + status
				+ ", movementDate=" + movementDate + ", password=" + password + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartment == null) ? 0 : apartment.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((movementDate == null) ? 0 : movementDate.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenant other = (Tenant) obj;
		if (apartment == null) {
			if (other.apartment != null)
				return false;
		} else if (!apartment.equals(other.apartment))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (movementDate == null) {
			if (other.movementDate != null)
				return false;
		} else if (!movementDate.equals(other.movementDate))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role != other.role)
			return false;
        return status == other.status;
    }

    public List<Residence> getResidence() {
        return residence;
    }

    public void setResidence(List<Residence> residence) {
        this.residence = residence;
    }
}
