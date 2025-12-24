package com.github.lowkkid.thewildoasisbackend.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "issued_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "expires_absolute", nullable = false)
    private LocalDateTime expiresAbsolute;

    @Column(name = "revoked", nullable = false)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    private Boolean revoked = false;

    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return !revoked
                && now.isBefore(expiresAt)
                && now.isBefore(expiresAbsolute);
    }
}