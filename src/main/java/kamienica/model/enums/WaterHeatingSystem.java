package kamienica.model.enums;

public enum WaterHeatingSystem {

	SEPARATE_HEATER("Individual heating system"),
	SHARED_GAS_HEATER("Shared gas heater"),
	SHARED_ELECTRIC_HEATER("Shared electric heater");

	private String system;

	WaterHeatingSystem(String system) {
		this.system = system;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
