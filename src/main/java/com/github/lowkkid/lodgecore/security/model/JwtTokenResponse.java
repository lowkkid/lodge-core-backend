package com.github.lowkkid.lodgecore.security.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO containing the JWT access token for authenticated requests.
 */
@Schema(description = "Response containing JWT access token for authenticated API requests")
public record JwtTokenResponse(
        @Schema(
                description = "JWT access token to be used in the Authorization header for authenticated requests. "
                        + "Format: Bearer <token>. The token contains encoded user information including user ID, "
                        + "role, username, and avatar URL.",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzd.....fwpMeJf36POk6yJV_adQssw5c",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String jwtToken
) {
}