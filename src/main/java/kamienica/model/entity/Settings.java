package kamienica.model.entity;

import kamienica.model.enums.WaterHeatingSystem;

import javax.persistence.*;

@Entity
@Table(name = "SETTINGS")
public class Settings {

	@Id
	@GeneratedValue
	private Long id;
	private boolean gas = true;
	private boolean internet = false;
	private boolean garbage = false;
	@Enumerated(EnumType.STRING)
	private WaterHeatingSystem waterHeatingSystem;
	@OneToOne
	private Residence residence;

	public Settings() {
	}

	public Settings(Long id, boolean gas, boolean internet, boolean garbage,
					WaterHeatingSystem waterHeatingSystem, Residence residence) {
		this.id = id;
		this.gas = gas;
		this.internet = internet;
		this.garbage = garbage;
		this.waterHeatingSystem = waterHeatingSystem;
		this.residence = residence;
	}

	public WaterHeatingSystem getWaterHeatingSystem() {
		return waterHeatingSystem;
	}

	public void setWaterHeatingSystem(WaterHeatingSystem waterHeatingSystem) {
		this.waterHeatingSystem = waterHeatingSystem;
	}

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
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
		return "Settings [id=" + id + ", gas=" + gas + ", internet=" + internet
				+ ", garbage=" + garbage + ", waterHeatingSystem=" + waterHeatingSystem + "]";
	}

}
