package com.github.lowkkid.thewildoasisbackend.security.service.impl;

import com.github.lowkkid.thewildoasisbackend.security.model.JwtTokenResponse;
import com.github.lowkkid.thewildoasisbackend.security.model.UsernameAndPassword;
import com.github.lowkkid.thewildoasisbackend.security.model.UserDetailsImpl;
import com.github.lowkkid.thewildoasisbackend.security.service.AuthService;
import com.github.lowkkid.thewildoasisbackend.security.service.JwtService;
import com.github.lowkkid.thewildoasisbackend.security.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public JwtTokenResponse login(UsernameAndPassword usernameAndPassword, HttpServletResponse response) {
        var username = usernameAndPassword.username();
        var password = usernameAndPassword.password();

        var authToken = new UsernamePasswordAuthenticationToken(username, password);

        var authentication = authenticationManager.authenticate(authToken);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtService.generate(userDetails);
        refreshTokenService.generate(userDetails.getUserId(), response);

        return new JwtTokenResponse(jwt);
    }

    @Override
    @Transactional
    public JwtTokenResponse refresh(String refreshToken, HttpServletResponse response) {
        var newRefreshToken = refreshTokenService.refresh(refreshToken, response);
        var user = newRefreshToken.getUser();
        var newJwtToken = jwtService.generate(user.getId(), user.getRole(), user.getUsername());

        return new JwtTokenResponse(newJwtToken);
    }
}
