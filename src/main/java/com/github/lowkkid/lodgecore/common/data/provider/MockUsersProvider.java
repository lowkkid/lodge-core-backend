package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.user.domain.entity.User;
import com.github.lowkkid.lodgecore.user.model.UserRole;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockUsersProvider {

    public static final User ADMIN = User.builder()
            .username("admin")
            .password("admin_password")
            .role(UserRole.ADMIN)
            .createdAt(LocalDateTime.now().minusDays(2))
            .build();

    public static final User EMPLOYEE = User.builder()
            .username("employee")
            .password("employee_password")
            .role(UserRole.EMPLOYEE)
            .createdAt(LocalDateTime.now().minusDays(1))
            .build();
}
