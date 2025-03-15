package mc.com.serverSite.entity.user;

public enum UserBadge {

    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PLATINUM("PLATINUM"),

    STAFF("STAFF"),
    ADMIN("ADMIN");

    private String badge;

    UserBadge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return this.badge;
    }

}
