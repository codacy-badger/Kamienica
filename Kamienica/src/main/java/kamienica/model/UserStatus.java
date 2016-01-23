package kamienica.model;

public enum UserStatus {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
	
	private String userStatus;

	UserStatus(String status) {
		this.userStatus = status;
	}

	public String getUserStatus() {
		return userStatus;
	}

	
}
