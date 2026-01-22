package com.github.lowkkid.lodgecore.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object representing user information.
 */
@Schema(description = "User data transfer object")
public record UserDTO(
        @Schema(
                description = "Unique identifier of the user",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Username of the user",
                example = "john.doe"
        )
        String username,

        @Schema(
                description = "Role of the user in the system",
                example = "EMPLOYEE"
        )
        UserRole role,

        @Schema(
                description = "Timestamp when the user was created",
                example = "2024-01-15T10:30:00"
        )
        LocalDateTime createdAt
) {
}
