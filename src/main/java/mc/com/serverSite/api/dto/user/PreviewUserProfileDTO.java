package mc.com.serverSite.api.dto.user;

import mc.com.serverSite.entity.user.UserBadge;
import mc.com.serverSite.entity.user.User;

public record PreviewUserProfileDTO(
        String username,
        String profilePicUrl,
        String clan,
        UserBadge userBadge
) {

    public static PreviewUserProfileDTO toDTO(User user) {
        return new PreviewUserProfileDTO(
                user.getUsername(),
                user.getProfilePicUrl(),
                user.getClan(),
                user.getUserBadge()
        );
    }

}
