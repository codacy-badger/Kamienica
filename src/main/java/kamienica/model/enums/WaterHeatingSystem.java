package kamienica.model.enums;

public enum WaterHeatingSystem {

    INDIVIDUAL("Standard"), SHARED_GAS("Shared gas heating system"), SHARED_ELECTRIC(
            "Shared Electric System");

    private String system;

    WaterHeatingSystem(String system) {
        this.system = system;
    }

    public String value() {
        return system;
    }

}
