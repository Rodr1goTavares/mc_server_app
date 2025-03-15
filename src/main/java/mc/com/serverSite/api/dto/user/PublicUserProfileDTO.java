package mc.com.serverSite.api.dto.user;

import mc.com.serverSite.entity.user.User;

import java.time.LocalDate;

public record PublicUserProfileDTO(
        String username,
        String profilePic,
        LocalDate createdAt,
        boolean isPrivateAccount
) {

    public static PublicUserProfileDTO toDto(User user) {
        return new PublicUserProfileDTO(
                user.getUsername(),
                user.getProfilePicUrl(),
                user.getCreatedAt(),
                user.isPrivateAccount()
        );
    }

}
