package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Summary of a stay with pricing breakdown")
public record StaySummary(
        @Schema(description = "Unique booking identifier", example = "1")
        Long id,

        @Schema(description = "Booking creation timestamp", example = "2025-09-01T10:30:00")
        LocalDateTime createdAt,

        @Schema(description = "Start date and time of the stay", example = "2025-09-15T14:00:00")
        LocalDateTime startDate,

        @Schema(description = "End date and time of the stay", example = "2025-09-20T11:00:00")
        LocalDateTime endDate,

        @Schema(description = "Number of nights for the stay", example = "5", minimum = "1")
        Short numNights,

        @Schema(description = "Number of guests", example = "2", minimum = "1")
        Short numGuests,

        @Schema(description = "Current booking status", example = "unconfirmed",
                allowableValues = {"unconfirmed", "checked-in", "checked-out"})
        BookingStatus status,

        @Schema(description = "Total price including cabin and extras", example = "575.00")
        BigDecimal totalPrice,

        @Schema(description = "Price of extras (e.g., breakfast)", example = "75.00")
        BigDecimal extrasPrice,

        @Schema(description = "Price of the cabin per stay", example = "500.00")
        BigDecimal cabinPrice,

        @Schema(description = "Full name of the guest", example = "John Doe")
        String guestFullName) {

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}
