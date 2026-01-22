package com.github.lowkkid.lodgecore.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Daily sales aggregation for bookings")
public record DailyBookingSales(
        @Schema(description = "Date of the sales record", example = "2025-09-15")
        LocalDate date,

        @Schema(description = "Total booking price (cabin prices) for the day", example = "1500.00")
        BigDecimal totalBookingPrice,

        @Schema(description = "Total extras price (e.g., breakfast) for the day", example = "225.00")
        BigDecimal totalExtrasPrice) {

    public static DailyBookingSales empty(LocalDate date) {
        return new DailyBookingSales(date, BigDecimal.ZERO, BigDecimal.ZERO);
    }

}
