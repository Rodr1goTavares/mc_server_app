package mc.com.serverSite.api.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterCredentialsDTO(
        @NotNull(message = "Username must not be null")
        @Size(min = 4, max = 27, message = "Name must be between 4 and 27 characters")
        String username,

        @NotNull(message = "Name must not be null")
        @Size(min = 5, max = 90, message = "Name must be between 5 and 90 characters")
        String email,

        @NotNull(message = "Name must not be null")
        @Size(min = 10, max = 200, message = "Name must be between 10 and 200 characters")
        String password
) {}
