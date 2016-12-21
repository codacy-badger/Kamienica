package kamienica.core.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WaterHeatingSystem {

	INDIVIDUAL_GAS("Indywidualny podgrzewacz gazowy"), SHARED_GAS("Wspólny podgrzewacz gazowy"), ELECTRIC(
			"Indywidualny podgrzewacz elektryczny");

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

	public static List<String> getValues() {
		return new ArrayList<>(Arrays.asList(WaterHeatingSystem.ELECTRIC.getSystem(),
				WaterHeatingSystem.INDIVIDUAL_GAS.getSystem(), WaterHeatingSystem.SHARED_GAS.getSystem()));
	}
}
