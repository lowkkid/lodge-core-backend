package com.github.lowkkid.lodgecore.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for user authentication containing username and password credentials.
 */
@Schema(description = "User credentials for authentication")
public record UsernameAndPassword(
        @Schema(
                description = "User's username or email address",
                example = "john.doe@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        String username,

        @Schema(
                description = "User's password",
                example = "SecureP@ssw0rd!",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password"
        )
        @NotNull
        String password
) {
}
