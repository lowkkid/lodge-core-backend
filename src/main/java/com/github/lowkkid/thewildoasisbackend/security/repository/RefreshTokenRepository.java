package com.github.lowkkid.thewildoasisbackend.security.repository;

import com.github.lowkkid.thewildoasisbackend.domain.entity.RefreshToken;
import com.github.lowkkid.thewildoasisbackend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByUser(User user);
}
