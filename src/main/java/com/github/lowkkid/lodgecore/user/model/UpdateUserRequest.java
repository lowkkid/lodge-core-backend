package com.github.lowkkid.lodgecore.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * Request DTO for updating user profile information.
 */
@Schema(description = "Request to update user profile information")
public record UpdateUserRequest(
        @Schema(
                description = "New username for the user",
                example = "john.doe.updated",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        String username,

        @Schema(
                description = "New avatar image file. Supported formats: JPEG, PNG. Max size: 5MB",
                type = "string",
                format = "binary"
        )
        MultipartFile avatar
) {
}
