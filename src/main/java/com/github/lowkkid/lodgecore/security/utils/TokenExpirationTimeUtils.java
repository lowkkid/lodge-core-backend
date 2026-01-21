package com.github.lowkkid.lodgecore.security.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class TokenExpirationTimeUtils {

    private final Long jwtExpirationTime;
    private final ChronoUnit jwtExpirationTimeUnit;
    private final Long refreshTokenExpirationTime;
    private final ChronoUnit refreshTokenExpirationTimeUnit;
    private final Long refreshTokenAbsoluteExpirationTime;
    private final ChronoUnit refreshTokenAbsoluteExpirationTimeUnit;

    public TokenExpirationTimeUtils(
            @Value("${jwt.expiration-time}") String jwtExpirationTime,
            @Value("${jwt.expiration-time-unit}") String jwtExpirationTimeUnit,
            @Value("${refresh-token.expiration-time}") String refreshTokenExpirationTime,
            @Value("${refresh-token.expiration-time-unit}") String refreshTokenExpirationTimeUnit,
            @Value("${refresh-token.absolute-expiration-time}") String refreshTokenAbsoluteExpirationTime,
            @Value("${refresh-token.absolute-expiration-time-unit}") String refreshTokenAbsoluteExpirationTimeUnit
    ) {
        this.jwtExpirationTime = Long.parseLong(jwtExpirationTime);
        this.jwtExpirationTimeUnit = ChronoUnit.valueOf(jwtExpirationTimeUnit);
        this.refreshTokenExpirationTime = Long.parseLong(refreshTokenExpirationTime);
        this.refreshTokenExpirationTimeUnit = ChronoUnit.valueOf(refreshTokenExpirationTimeUnit);
        this.refreshTokenAbsoluteExpirationTime = Long.parseLong(refreshTokenAbsoluteExpirationTime);
        this.refreshTokenAbsoluteExpirationTimeUnit = ChronoUnit.valueOf(refreshTokenAbsoluteExpirationTimeUnit);
    }

    public LocalDateTime getRefreshTokenExpirationTime(LocalDateTime issuedAt) {
        return issuedAt.plus(refreshTokenExpirationTime, refreshTokenExpirationTimeUnit);
    }

    public long getRefreshTokenExpirationTimeSec(LocalDateTime issuedAt) {
        return getRefreshTokenAbsoluteExpirationTime(issuedAt).toEpochSecond(ZoneOffset.UTC);
    }

    public LocalDateTime getRefreshTokenAbsoluteExpirationTime(LocalDateTime issuedAt) {
        return issuedAt.plus(refreshTokenAbsoluteExpirationTime, refreshTokenAbsoluteExpirationTimeUnit);
    }

    public Date getJwtExpirationTime(Date issuedAt) {
        LocalDateTime issuedAtLDT = LocalDateTime.ofInstant(issuedAt.toInstant(), ZoneId.systemDefault());
        var expirationTime = issuedAtLDT.plus(jwtExpirationTime, jwtExpirationTimeUnit);
        return Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
