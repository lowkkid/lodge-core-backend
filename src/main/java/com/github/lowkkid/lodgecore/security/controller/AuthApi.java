package com.github.lowkkid.lodgecore.security.controller;

import com.github.lowkkid.lodgecore.security.model.JwtTokenResponse;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

/**
 * Authentication API for user login, logout, and token refresh operations.
 * This API handles JWT-based authentication with refresh tokens stored in HTTP-only cookies.
 */
@Tag(name = "Authentication", description = "API for user authentication operations (login, logout, and token refresh")
public interface AuthApi {

    /**
     * Authenticates a user with username and password credentials.
     * On successful authentication, returns a JWT access token in the response body
     * and sets a refresh token in an HTTP-only cookie.
     *
     * @param usernameAndPassword the user credentials containing username and password
     * @param response            the HTTP response used to set the refresh token cookie
     * @return JWT access token response
     */
    @Operation(
            summary = "User login",
            description = "Authenticates a user with username and password credentials. "
                    + "On successful authentication, returns a JWT access token in the response body "
                    + "and sets a secure HTTP-only refresh token cookie. "
                    + "The cookie has the following properties: HttpOnly, Secure, SameSite=None, Partitioned."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully authenticated. "
                            + "Returns JWT access token in body and sets refresh token cookie.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtTokenResponse.class)
                    ),
                    headers = @Header(
                            name = "Set-Cookie",
                            description = "Refresh token cookie (refreshToken=<token>; HttpOnly; Secure; "
                                    + "SameSite=None; Partitioned; Path=/)",
                            schema = @Schema(type = "string")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing username or password)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed - invalid username or password",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<JwtTokenResponse> login(
            @Parameter(
                    description = "User credentials for authentication",
                    required = true
            )
            UsernameAndPassword usernameAndPassword,
            HttpServletResponse response
    );

    /**
     * Signs out the current user by clearing the refresh token cookie.
     * The access token should be discarded by the client.
     *
     * @param response the HTTP response used to clear the refresh token cookie
     * @return empty response with 204 status
     */
    @Operation(
            summary = "User logout",
            description = "Signs out the current user by clearing the refresh token cookie. "
                    + "The client should discard any stored access tokens. "
                    + "This endpoint sets a cookie with maxAge=0 to clear the refresh token."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully logged out. Refresh token cookie has been cleared.",
                    headers = @Header(
                            name = "Set-Cookie",
                            description = "Clears the refresh token cookie (refreshToken=; Max-Age=0; Path=/)",
                            schema = @Schema(type = "string")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Void> logout(HttpServletResponse response);

    /**
     * Refreshes the authentication tokens using a valid refresh token from cookie.
     * Generates a new JWT access token and rotates the refresh token (old token is invalidated,
     * new token is issued and set in cookie).
     *
     * @param refreshToken the refresh token from the HTTP-only cookie
     * @param response     the HTTP response used to set the new refresh token cookie
     * @return new JWT access token response
     */
    @Operation(
            summary = "Refresh access token",
            description = "Generates a new JWT access token using a valid refresh token from the cookie. "
                    + "This endpoint implements refresh token rotation - the old refresh token is invalidated "
                    + "and a new one is issued. The new refresh token is set in an HTTP-only cookie. "
                    + "Use this endpoint when the access token has expired but the refresh token is still valid."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully refreshed tokens. "
                            + "Returns new JWT access token and sets new refresh token cookie.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtTokenResponse.class)
                    ),
                    headers = @Header(
                            name = "Set-Cookie",
                            description = "New refresh token cookie (refreshToken=<new_token>; HttpOnly; Secure; "
                                    + "SameSite=None; Partitioned; Path=/)",
                            schema = @Schema(type = "string")
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token refresh failed. Possible reasons: "
                            + "empty refresh token, token not found, invalid token, or token expired. "
                            + "User should re-authenticate via login.",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<JwtTokenResponse> refresh(
            @Parameter(
                    description = "Refresh token from HTTP-only cookie. "
                            + "Format: <token_id>.<token_value>. "
                            + "This cookie is automatically sent by the browser.",
                    required = true,
                    example = "550e8400-e29b-41d4-a716-446655440000.dGhpcyBpcyBhIHNhbXBsZSB0b2tlbiB2YWx1ZQ=="
            )
            String refreshToken,
            HttpServletResponse response
    );
}
