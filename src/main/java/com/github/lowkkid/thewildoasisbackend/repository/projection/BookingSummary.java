package com.github.lowkkid.thewildoasisbackend.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingSummary(Long id, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime endDate,
                             Short numNights, Short numGuests, String status, BigDecimal totalPrice, String cabinName,
                             String guestFullName, String guestEmail) {
}
