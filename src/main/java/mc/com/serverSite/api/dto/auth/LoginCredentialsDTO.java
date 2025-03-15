package mc.com.serverSite.api.dto.auth;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginCredentialsDTO(
        @NotNull(message = "Name must not be null")
        @Size(min = 5, max = 90, message = "Name must be between 5 and 35 characters")
        String username,

        @NotNull(message = "Name must not be null")
        @Size(min = 10, max = 200, message = "Password must be between 10 and 150 characters")
        String password) {}
