package com.github.lowkkid.thewildoasisbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_booking_length", nullable = false)
    private Short minBookingLength;

    @Column(name = "max_booking_length", nullable = false)
    private Short maxBookingLength;

    @Column(name = "max_guests_per_booking", nullable = false)
    private Short maxGuestsPerBooking;

    @Column(name = "breakfast_price", nullable = false)
    private BigDecimal breakfastPrice;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}

