package kamienica.feature.residence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "residence", uniqueConstraints = { @UniqueConstraint(columnNames = { "street", "number", "city" }) })
public class Residence {

	@Id
	@GeneratedValue
	private Long id;
	private String street;
	private String number;
	private String city;

	public Residence() {
	}

	public Residence(Long id, String street, String number, String city) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Residence [id=" + id + ", street=" + street + ", number=" + number + ", city=" + city + "]";
	}

}
