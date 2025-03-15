package mc.com.serverSite.service.auth.context;

import mc.com.serverSite.entity.user.User;

public interface AuthenticationContext {

    User getAuthenticatedUser();

}
