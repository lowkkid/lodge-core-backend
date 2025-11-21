package com.github.lowkkid.thewildoasisbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100)
    private String nationality;

    @Column(name = "national_id", nullable = false, unique = true)
    private String nationalId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}

