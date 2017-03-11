package kamienica.model.enums;

public enum Status {
	ACTIVE("Aktywny"), INACTIVE("Nieaktywny");

	private String status;

	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
