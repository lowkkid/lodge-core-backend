package com.github.lowkkid.thewildoasisbackend.security;

import com.github.lowkkid.thewildoasisbackend.security.model.UserDetailsImpl;
import com.github.lowkkid.thewildoasisbackend.security.model.enums.JwtValidationResult;
import com.github.lowkkid.thewildoasisbackend.security.model.enums.UserRole;
import com.github.lowkkid.thewildoasisbackend.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_NAME);
        // if request has no jwt inside
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith(BEARER_PREFIX)) {
            handleJwtError(response, JwtValidationResult.ERROR);
            return;
        }

        String jwt = authHeader.substring(BEARER_PREFIX.length());

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            var jwtValidationResult = jwtService.validate(jwt);

            if (jwtValidationResult == JwtValidationResult.SUCCESS) {
                var claims = jwtService.extractAllClaims(jwt);
                var userId = claims.getSubject();
                var username = claims.get("username", String.class);
                var role =  UserRole.valueOf(claims.get("role", String.class));

                var userDetails = new UserDetailsImpl(username, role, UUID.fromString(userId));
                var authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                List.of(role)
                        );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                handleJwtError(response, jwtValidationResult);
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/auth") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-resources");
    }

    private void handleJwtError(HttpServletResponse response, JwtValidationResult result) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String jsonResponse = "{\"jwtError\": \"" + result + "\"}";

        response.getWriter().write(jsonResponse);
    }
}