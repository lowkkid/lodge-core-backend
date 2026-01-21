package com.github.lowkkid.lodgecore.security.utils;

import com.github.lowkkid.lodgecore.security.model.UserDetailsImpl;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthUtils {

    @Nullable
    public UUID getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            return userDetails.getUserId();
        }

        return null;
    }
}
