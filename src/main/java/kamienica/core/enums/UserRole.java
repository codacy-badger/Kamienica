package kamienica.core.enums;

public enum UserRole {
	USER("USER"), ADMIN("ADMIN");

	String userRole;

	UserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

}
