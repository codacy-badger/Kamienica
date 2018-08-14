package kamienica.model.entity;

import kamienica.model.enums.WaterHeatingSystem;

import javax.persistence.*;

@Entity
@Table(name = "SETTINGS")
public class Settings {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private WaterHeatingSystem waterHeatingSystem;
	@OneToOne
	private Residence residence;

	public Settings() {
	}

	public Settings(final WaterHeatingSystem waterHeatingSystem, final Residence residence) {
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

}
