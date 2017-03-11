package kamienica.model.enums;

public enum UserRole {
	TENANT("TENANT"), OWNER("OWNER"), ADMIN("ADMIN");

	String userRole;

	UserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

}
