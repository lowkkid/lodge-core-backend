package com.github.lowkkid.thewildoasisbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingDTO {
    private Long id;
    private Short minBookingLength;
    private Short maxBookingLength;
    private Short maxGuestsPerBooking;
    private BigDecimal breakfastPrice;
    private LocalDateTime createdAt;
}

