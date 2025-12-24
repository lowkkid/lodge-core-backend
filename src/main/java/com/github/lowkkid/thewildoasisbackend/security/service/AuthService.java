package com.github.lowkkid.thewildoasisbackend.security.service;

import com.github.lowkkid.thewildoasisbackend.security.model.JwtTokenResponse;
import com.github.lowkkid.thewildoasisbackend.security.model.UsernameAndPassword;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    JwtTokenResponse login(UsernameAndPassword usernameAndPassword, HttpServletResponse response);
    JwtTokenResponse refresh(String refreshToken, HttpServletResponse response);
}
