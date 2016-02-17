package kamienica.model;

public enum UserStatus {
	ACTIVE("AKTYWNY"), INACTIVE("NIEAKTYWNY");
	
	private String userStatus;

	UserStatus(String status) {
		this.userStatus = status;
	}

	public String getUserStatus() {
		return userStatus;
	}

	
}
