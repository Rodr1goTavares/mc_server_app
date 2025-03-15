package mc.com.serverSite.entity.user.builder;

import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.entity.user.UserRole;

import java.time.LocalDate;

public interface AdminBuilder<B, A extends User> {
    B setRole(UserRole role);
    B isActive(boolean status);
    B setCreatedAt(LocalDate date);
    B setLastAccess(LocalDate date);
    A build();
}
