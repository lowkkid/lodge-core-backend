package com.github.lowkkid.lodgecore.security.model;

import com.github.lowkkid.lodgecore.user.model.UserRole;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
@ToString
public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final UserRole role;
    private final UUID userId;
    private final String avatar;

    public UserDetailsImpl(String username, UserRole role, UUID userId, String avatar) {
        this.role = role;
        this.password = "";
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
