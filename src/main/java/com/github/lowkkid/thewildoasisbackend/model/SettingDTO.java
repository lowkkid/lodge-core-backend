package com.github.lowkkid.thewildoasisbackend.model;

import jakarta.validation.constraints.Min;
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
    @Min(value = 1, message = "Minimum booking length can't be less than 1")
    private Short minBookingLength;
    @Min(value = 1, message = "Maximum booking length can't be less than 1")
    private Short maxBookingLength;
    @Min(value = 1, message = "Amount of guests can't be less than 1")
    private Short maxGuestsPerBooking;
    @Min(value = 0, message = "Breakfast price can't be less than 0")
    private BigDecimal breakfastPrice;
    private LocalDateTime createdAt;
}

