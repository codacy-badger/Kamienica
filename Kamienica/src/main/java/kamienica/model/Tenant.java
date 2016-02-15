package kamienica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Tenant {

	@Id
	@GeneratedValue
	@Column
	private int id;
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
	@Column(nullable = false)
	private String role = UserRole.USER.getUserRole();
	@Column(nullable = false)
	private String status = UserStatus.ACTIVE.getUserStatus();
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date movementDate;
	@Column(length = 5, nullable = false)
	@NotEmpty(message = "Wprowadź hasło")
	private String password;

	@Autowired
	public Tenant(String firstName, String lastName, String email, String phone, Apartment apartment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.apartment = apartment;
		this.phone = phone;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Tenant() {

		this.movementDate = new Date();
		this.password = "witaj";
	}

	public String getFullName() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", apartment=" + apartment + ", role=" + role + ", status=" + status
				+ ", movementDate=" + movementDate + ", password=" + password + "]";
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

}
