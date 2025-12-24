package com.github.lowkkid.thewildoasisbackend.security.controller;

import com.github.lowkkid.thewildoasisbackend.security.model.JwtTokenResponse;
import com.github.lowkkid.thewildoasisbackend.security.model.UsernameAndPassword;
import com.github.lowkkid.thewildoasisbackend.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Sign in as an existing user")
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(
            @RequestBody @Valid UsernameAndPassword usernameAndPassword, HttpServletResponse response) {
        var tokensResponse = authService.login(usernameAndPassword, response);
        return ResponseEntity.ok(tokensResponse);
    }

    @Operation(summary = "Get new pair of tokens (access token inside response body and refresh token in cookies) " +
            "based on valid and non-revoked refresh token")
    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenResponse> refresh(
            @CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        var tokensResponse = authService.refresh(refreshToken, response);
        return ResponseEntity.ok(tokensResponse);
    }
}