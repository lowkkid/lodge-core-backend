package com.github.lowkkid.thewildoasisbackend.security.service;

import com.github.lowkkid.thewildoasisbackend.security.model.enums.JwtValidationResult;
import com.github.lowkkid.thewildoasisbackend.security.model.enums.UserRole;
import com.github.lowkkid.thewildoasisbackend.security.model.UserDetailsImpl;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface JwtService {

    String generate(UUID userId, UserRole role, String username);

    String generate(UserDetailsImpl userDetails);

    JwtValidationResult validate(String token);

    Claims extractAllClaims(String token);
}
