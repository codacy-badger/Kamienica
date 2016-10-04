package kamienica.core.util;

public enum Media {
	GAS, ENERGY, WATER;

	public Media getFromString(String string) {
		string = string.toUpperCase();
		switch (string) {
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
