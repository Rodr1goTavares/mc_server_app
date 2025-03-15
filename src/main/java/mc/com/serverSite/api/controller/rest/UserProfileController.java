package mc.com.serverSite.api.controller.rest;

import mc.com.serverSite.api.dto.user.PreviewUserProfileDTO;
import mc.com.serverSite.api.dto.user.SelfUserProfileDTO;
import mc.com.serverSite.entity.user.User;
import mc.com.serverSite.service.UserService;
import mc.com.serverSite.service.auth.context.AuthenticationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserProfileController {

    private final AuthenticationContext authenticationContext;
    private final UserService userService;

    public UserProfileController(AuthenticationContext authenticationContext, UserService userService) {
        this.authenticationContext = authenticationContext;
        this.userService = userService;
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        User user = (User) this.userService.loadUserByUsername(username);
        return ResponseEntity.ok().body(PreviewUserProfileDTO.toDTO(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getAuthenticatedUserProfile(@PathVariable String username) {
        User authenticatedUser = this.authenticationContext.getAuthenticatedUser();
        return ResponseEntity.ok().body(SelfUserProfileDTO.toDto(authenticatedUser));
    }

}
