package com.github.lowkkid.thewildoasisbackend.security.service.impl;

import com.github.lowkkid.thewildoasisbackend.security.model.enums.JwtValidationResult;
import com.github.lowkkid.thewildoasisbackend.security.model.enums.UserRole;
import com.github.lowkkid.thewildoasisbackend.security.model.UserDetailsImpl;
import com.github.lowkkid.thewildoasisbackend.security.service.JwtService;
import com.github.lowkkid.thewildoasisbackend.security.TokenExpirationTimeUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final String jwtSecretKey;
    private final TokenExpirationTimeUtils tokenExpirationTimeUtils;

    public JwtServiceImpl(
            @Value("${jwt.secret}") String jwtSecretKey, TokenExpirationTimeUtils tokenExpirationTimeUtils) {
        this.jwtSecretKey = jwtSecretKey;
        this.tokenExpirationTimeUtils = tokenExpirationTimeUtils;
    }

    @Override
    public String generate(UUID userId, UserRole role, String username) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role);
        extraClaims.put("username", username);
        var issuedAt = new Date();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userId.toString())
                .issuedAt(issuedAt)
                .expiration(tokenExpirationTimeUtils.getJwtExpirationTime(issuedAt))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String generate(UserDetailsImpl userDetails) {
        return generate(userDetails.getUserId(), userDetails.getRole(), userDetails.getUsername());
    }

    @Override
    public JwtValidationResult validate(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().before(new Date()) ? JwtValidationResult.EXPIRED : JwtValidationResult.SUCCESS;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("Invalid JWT: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            return JwtValidationResult.ERROR;
        }
    }


    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    /*
     * for HMAC-SHA256 algorithm we need at least 256 bits (32 bytes) of key material
     *
     * we store the secret key as a Base64-encoded string for several reasons:
     * 1. the encoded length tells us the decoded byte length (sting from random chars length depends on chars)
     * 2. base64 strings contain only safe characters for environment variables
     *
     * generate a proper key with:
     *   openssl rand -base64 32
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
