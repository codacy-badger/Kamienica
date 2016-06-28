package kamienica.feature.tenant;

public enum UserRole {
	USER("USER"), ADMIN("ADMIN");

	String userRole;

	private UserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

}
