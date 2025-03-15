package mc.com.serverSite.api.dto.user;

import mc.com.serverSite.entity.user.User;

import java.time.LocalDate;

public record SelfUserProfileDTO(
        String username,
        String email,
        String profilePic,
        LocalDate createdAt,
        LocalDate lastAccess,
        boolean isPrivateAccount
) {

    public static SelfUserProfileDTO toDto(User user) {
        return new SelfUserProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getProfilePicUrl(),
                user.getCreatedAt(),
                user.getLastAccess(),
                user.isPrivateAccount()
        );
    }

}
