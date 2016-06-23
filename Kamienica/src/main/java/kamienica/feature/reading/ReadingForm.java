package kamienica.feature.reading;

public class ReadingForm {

	private Long meterId;
	private String desciption;
	private double previousReading;
	private double currentReading;

	public ReadingForm() {
	}

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}

	public double getPreviousReading() {
		return previousReading;
	}

	public void setPreviousReading(double previousReading) throws IllegalArgumentException {
		if (previousReading < 0) {
			throw new IllegalArgumentException();
		}
		this.previousReading = previousReading;
	}

	public double getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(double currentReading) throws IllegalArgumentException {
		if (currentReading < previousReading) {
			throw new IllegalArgumentException();
		}
		this.currentReading = currentReading;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

}
