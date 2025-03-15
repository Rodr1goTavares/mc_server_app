package mc.com.serverSite.entity.user.builder;

import mc.com.serverSite.entity.user.User;

public interface UserBuilder<B, U extends User> {
    B setUsername(String username);
    B setEmail(String email);
    B setPassword(String password);
    U build();
}
