package com.github.lowkkid.lodgecore.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for updating user password.
 */
@Schema(description = "Request to update user password")
public record UpdatePasswordRequest(
        @Schema(
                description = "Current password for verification",
                example = "OldSecureP@ss123",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password"
        )
        String oldPassword,

        @Schema(
                description = "New password to set",
                example = "NewSecureP@ss456",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password"
        )
        String newPassword
) {
}
