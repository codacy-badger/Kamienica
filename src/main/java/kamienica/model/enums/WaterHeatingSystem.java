package kamienica.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WaterHeatingSystem {

    INDIVIDUAL("Standard"), SHARED_GAS("Shared gas heating system"), SHARED_ELECTRIC(
            "Shared Electric System");

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
        return new ArrayList<>(Arrays.asList(WaterHeatingSystem.SHARED_ELECTRIC.getSystem(),
                WaterHeatingSystem.INDIVIDUAL.getSystem(), WaterHeatingSystem.SHARED_GAS.getSystem()));
    }
}
