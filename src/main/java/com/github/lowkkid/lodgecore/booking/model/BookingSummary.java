package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingSummary(Long id, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime endDate,
                             Short numNights, Short numGuests, BookingStatus status, BigDecimal totalPrice,
                             String cabinName, String guestFullName, String guestEmail) {

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}