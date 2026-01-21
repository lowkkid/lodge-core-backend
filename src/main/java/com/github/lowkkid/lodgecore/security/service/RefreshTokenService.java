package com.github.lowkkid.lodgecore.security.service;

import com.github.lowkkid.lodgecore.security.domain.entity.RefreshToken;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface RefreshTokenService {

    void generate(UUID userId, HttpServletResponse response);

    RefreshToken refresh(String userRefreshToken, HttpServletResponse response);
}
