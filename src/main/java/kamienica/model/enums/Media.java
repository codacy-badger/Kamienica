package kamienica.model.enums;

public enum Media {
	GAS, ENERGY, WATER;

	public Media getFromString(String string) {
		switch (string.toUpperCase()) {
		case "ENERGY":
			return ENERGY;
		case "GAS":
			return GAS;
		case "WATER":
			return WATER;
		default:
			return null;
		}

	}
}
