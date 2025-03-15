package mc.com.serverSite.entity.user;

public enum UserRole {

    USER("ROLE_USER"),
    STAFF("ROLE_STAFF"),
    ADMIN("ROLE_ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
