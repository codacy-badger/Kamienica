package kamienica.feature.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Settings {

	@Id
	@GeneratedValue
	private Long id;
	boolean gas = true;
	boolean correctDivision = false;
	boolean internet = false;
	boolean garbage = false;

	public Settings() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isGas() {
		return gas;
	}

	public void setGas(boolean gas) {
		this.gas = gas;
	}

	public boolean isCorrectDivision() {
		return correctDivision;
	}

	public void setCorrectDivision(boolean correctDivision) {
		this.correctDivision = correctDivision;
	}

	public boolean isInternet() {
		return internet;
	}

	public void setInternet(boolean internet) {
		this.internet = internet;
	}

	public boolean isGarbage() {
		return garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

	@Override
	public String toString() {
		return "Settings [id=" + id + ", gas=" + gas + ", correctDivision=" + correctDivision + ", internet=" + internet
				+ ", garbage=" + garbage + "]";
	}

	public Settings(Long id, boolean gas, boolean correctDivision, boolean internet, boolean garbage) {
		super();
		this.id = id;
		this.gas = gas;
		this.correctDivision = correctDivision;
		this.internet = internet;
		this.garbage = garbage;
	}

}
