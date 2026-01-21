package com.github.lowkkid.lodgecore.security.service;

import com.github.lowkkid.lodgecore.security.model.JwtValidationResult;
import com.github.lowkkid.lodgecore.security.model.UserDetailsImpl;
import com.github.lowkkid.lodgecore.user.model.UserRole;
import io.jsonwebtoken.Claims;
import java.util.UUID;


public interface JwtService {

    String generate(UUID userId, UserRole role, String username, String avatar);

    String generate(UserDetailsImpl userDetails);

    JwtValidationResult validate(String token);

    Claims extractAllClaims(String token);
}
