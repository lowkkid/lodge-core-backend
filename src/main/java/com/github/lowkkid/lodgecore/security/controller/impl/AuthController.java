package com.github.lowkkid.lodgecore.security.controller.impl;

import com.github.lowkkid.lodgecore.security.controller.AuthApi;
import com.github.lowkkid.lodgecore.security.model.JwtTokenResponse;
import com.github.lowkkid.lodgecore.security.service.AuthService;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller implementation for authentication operations.
 * Handles user login, logout, and token refresh endpoints.
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(
            @RequestBody @Valid UsernameAndPassword usernameAndPassword,
            HttpServletResponse response) {
        var tokensResponse = authService.login(usernameAndPassword, response);
        return ResponseEntity.ok(tokensResponse);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        var clearRefreshTokenCookie = ResponseCookie.from("refreshToken")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .partitioned(true)
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, clearRefreshTokenCookie.toString());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenResponse> refresh(
            @CookieValue("refreshToken") String refreshToken,
            HttpServletResponse response) {
        var tokensResponse = authService.refresh(refreshToken, response);
        return ResponseEntity.ok(tokensResponse);
    }
}
