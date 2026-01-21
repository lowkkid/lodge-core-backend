package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StaySummary(Long id, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime endDate,
                          Short numNights, Short numGuests, BookingStatus status, BigDecimal totalPrice,
                          BigDecimal extrasPrice, BigDecimal cabinPrice, String guestFullName) {

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}
