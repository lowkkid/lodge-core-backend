package com.github.lowkkid.lodgecore.security.service;

import com.github.lowkkid.lodgecore.security.model.JwtTokenResponse;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    JwtTokenResponse login(UsernameAndPassword usernameAndPassword, HttpServletResponse response);

    JwtTokenResponse refresh(String refreshToken, HttpServletResponse response);
}
