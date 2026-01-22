package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Detailed booking information including cabin and guest details")
public class BookingDTO {

    @Schema(description = "Unique booking identifier", example = "1")
    private Long id;

    @Schema(description = "Start date and time of the stay", example = "2025-09-15T14:00:00")
    private LocalDateTime startDate;

    @Schema(description = "End date and time of the stay", example = "2025-09-20T11:00:00")
    private LocalDateTime endDate;

    @Schema(description = "Number of nights for the stay", example = "5", minimum = "1")
    private Short numNights;

    @Schema(description = "Number of guests", example = "2", minimum = "1")
    private Short numGuests;

    @Schema(description = "Price of the cabin per stay", example = "500.00")
    private BigDecimal cabinPrice;

    @Schema(description = "Price of extras (e.g., breakfast)", example = "75.00")
    private BigDecimal extrasPrice;

    @Schema(description = "Total price including cabin and extras", example = "575.00")
    private BigDecimal totalPrice;

    @Schema(description = "Current booking status", example = "unconfirmed",
            allowableValues = {"unconfirmed", "checked-in", "checked-out"})
    private BookingStatus status;

    @Schema(description = "Whether breakfast is included", example = "true")
    private Boolean hasBreakfast;

    @Schema(description = "Whether the booking has been paid", example = "false")
    private Boolean isPaid;

    @Schema(description = "Additional observations or notes", example = "Guest requested late check-out")
    private String observations;

    @Schema(description = "Cabin details")
    private CabinDTO cabin;

    @Schema(description = "Guest details")
    private GuestDTO guest;

    @Schema(description = "Booking creation timestamp", example = "2025-09-01T10:30:00")
    private LocalDateTime createdAt;

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}

