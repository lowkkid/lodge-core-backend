package com.github.lowkkid.lodgecore.user.controller;

import com.github.lowkkid.lodgecore.user.model.UpdatePasswordRequest;
import com.github.lowkkid.lodgecore.user.model.UpdateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/**
 * API interface for managing the authenticated user's account.
 * Provides endpoints for updating profile information and password.
 */
@Tag(name = "Account", description = "API for managing the authenticated user's account")
public interface AccountApi {

    @Operation(
            summary = "Update account profile",
            description = "Updates the authenticated user's profile information including username and avatar. "
                    + "If a new avatar image is provided, the old avatar is deleted from storage and replaced. "
                    + "Returns a new JWT token containing the updated user information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile successfully updated. Returns new JWT token with updated claims.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "string",
                                    description = "New JWT access token",
                                    example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing username)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Storage service unavailable - Failed to upload/delete avatar image",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<String> update(
            @Parameter(description = "Profile update request with new username and optional avatar image")
            UpdateUserRequest request
    );

    @Operation(
            summary = "Update account password",
            description = "Updates the authenticated user's password. Requires the current password for verification "
                    + "before setting the new password. The new password will be securely hashed before storage."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Password successfully updated"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing old or new password)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid authentication token, or old password is incorrect",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Void> updatePassword(
            @Parameter(description = "Password update request containing old and new passwords")
            UpdatePasswordRequest request
    );
}
