package mc.com.serverSite.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import mc.com.serverSite.entity.user.builder.UserBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@ToString(exclude = {"password"})
@Entity
@Table(name = "tb_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String profilePicUrl;

    private UserBadge userBadge;

    private String clan;

    private LocalDate createdAt;

    private LocalDate lastAccess;

    private boolean privateAccount;

    private boolean active;

    public User() {
        this.role = UserRole.USER;
        this.createdAt = LocalDate.now();
        this.lastAccess = LocalDate.now();
        this.active = true;
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String identityDocument, String documentType, String nationality, String phoneNumber) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch(this.role) {
            case ADMIN -> {
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_STAFF"),
                        new SimpleGrantedAuthority("ROLE_USER")
                );
            }
            case STAFF -> {
                return List.of(
                        new SimpleGrantedAuthority("ROLE_STAFF"),
                        new SimpleGrantedAuthority("ROLE_USER")
                );
            }
            default -> {
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public static class Builder implements UserBuilder<Builder, User> {

        private String username;
        private String email;
        private String password;

        @Override
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public User build() {
            return new User(
                    this.username,
                    this.email,
                    this.password
            );
        }

    }

}
