package com.github.lowkkid.lodgecore.security.domain.repository;

import com.github.lowkkid.lodgecore.security.domain.entity.RefreshToken;
import com.github.lowkkid.lodgecore.user.domain.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByUser(User user);
}
