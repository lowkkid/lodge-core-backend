package com.github.lowkkid.thewildoasisbackend.security.service.impl;

import com.github.lowkkid.thewildoasisbackend.domain.entity.RefreshToken;
import com.github.lowkkid.thewildoasisbackend.security.repository.RefreshTokenRepository;
import com.github.lowkkid.thewildoasisbackend.security.repository.UserRepository;
import com.github.lowkkid.thewildoasisbackend.exception.TokenException;
import com.github.lowkkid.thewildoasisbackend.security.service.RefreshTokenService;
import com.github.lowkkid.thewildoasisbackend.security.TokenExpirationTimeUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int REFRESH_TOKEN_LENGTH = 72;
    private static final int BCRYPT_COST_FACTOR = 10;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenExpirationTimeUtils tokenExpirationTimeUtils;

    @Override
    @Transactional
    public void generate(UUID userId, HttpServletResponse response) {
        //delete previous refresh token
        var userProxy = userRepository.getReferenceById(userId);
        refreshTokenRepository.findByUser(userProxy).ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userProxy);

        byte[] tokenBytes = new byte[REFRESH_TOKEN_LENGTH];
        SECURE_RANDOM.nextBytes(tokenBytes);
        String token = Base64.getEncoder().encodeToString(tokenBytes);
        String salt = BCrypt.gensalt(BCRYPT_COST_FACTOR, SECURE_RANDOM);
        String tokenHash = BCrypt.hashpw(token, salt);
        refreshToken.setTokenHash(tokenHash);

        LocalDateTime issuedAt = LocalDateTime.now();
        refreshToken.setIssuedAt(issuedAt);
        refreshToken.setExpiresAt(tokenExpirationTimeUtils.getRefreshTokenExpirationTime(issuedAt));
        refreshToken.setExpiresAbsolute(tokenExpirationTimeUtils.getRefreshTokenAbsoluteExpirationTime(issuedAt));

        var savedToken = refreshTokenRepository.save(refreshToken);

        var userToken = savedToken.getId().toString() + "." + token;
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", userToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .maxAge(tokenExpirationTimeUtils.getRefreshTokenExpirationTimeSec(issuedAt))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }

    @Override
    public RefreshToken refresh(String userToken, HttpServletResponse response) {
        var userTokenParts = userToken.split("\\.");
        var userRefreshTokenId = userTokenParts[0];
        var userRefreshToken = userTokenParts[1];

        var tokenFromDb = refreshTokenRepository.findById(UUID.fromString(userRefreshTokenId))
                .orElseThrow(() -> new TokenException("Refresh token with id " + userRefreshTokenId + " not found"));

        if (!BCrypt.checkpw(userRefreshToken, tokenFromDb.getTokenHash())) {
            throw new TokenException("Invalid refresh token");
        }

        if (!tokenFromDb.isValid()) {
            throw new TokenException("Refresh token expired, please login again");
        }

        // if everything ok - delete old token and create new one
        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUser(tokenFromDb.getUser());

        byte[] tokenBytes = new byte[REFRESH_TOKEN_LENGTH];
        SECURE_RANDOM.nextBytes(tokenBytes);
        String token = Base64.getEncoder().encodeToString(tokenBytes);
        String salt = BCrypt.gensalt(BCRYPT_COST_FACTOR, SECURE_RANDOM);
        String tokenHash = BCrypt.hashpw(token, salt);
        newRefreshToken.setTokenHash(tokenHash);

        var issuedAt = LocalDateTime.now();
        newRefreshToken.setIssuedAt(issuedAt);
        newRefreshToken.setExpiresAt(tokenExpirationTimeUtils.getRefreshTokenExpirationTime(issuedAt));
        newRefreshToken.setExpiresAbsolute(tokenFromDb.getExpiresAbsolute());

        var savedToken = refreshTokenRepository.save(newRefreshToken);
        refreshTokenRepository.delete(tokenFromDb);

        var newUserToken = savedToken.getId().toString() + "." + token;
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", newUserToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .maxAge(tokenExpirationTimeUtils.getRefreshTokenExpirationTimeSec(issuedAt))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
        return savedToken;
    }
}

