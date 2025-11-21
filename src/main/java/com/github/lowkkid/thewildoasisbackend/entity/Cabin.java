package com.github.lowkkid.thewildoasisbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cabins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "max_capacity", nullable = false)
    private Short maxCapacity;

    @Column(name = "regular_price", nullable = false)
    private BigDecimal regularPrice;

    @Column(nullable = false)
    @Builder.Default
    private Short discount = 0;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column
    private byte[] image;
}

