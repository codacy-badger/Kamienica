package kamienica.model;

import kamienica.apartment.Apartment;

public class UsageValue {

	private String description;
	private double usage;
	private String unit;
	private int daysBetweenReadings;
	private Apartment apartment;

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getDaysBetweenReadings() {
		return daysBetweenReadings;
	}

	public void setDaysBetweenReadings(int daysBetweenReadings) {
		this.daysBetweenReadings = daysBetweenReadings;
	}

	public UsageValue(String description, double usage, String unit, int daysBetweenReadings, Apartment apartment) {
		super();
		this.description = description;
		this.usage = usage;
		this.unit = unit;
		this.daysBetweenReadings = daysBetweenReadings;
		this.apartment = apartment;
	}

	public UsageValue() {
	}

	@Override
	public String toString() {
		String formattedString = String.format("%.02f", usage);
		return "\n" + description + ": Zuzycie=" + formattedString + " " + unit + ". Liczba Dni Pomiedzy Odczytami: "
				+ daysBetweenReadings + " mieskzanie:" + apartment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
