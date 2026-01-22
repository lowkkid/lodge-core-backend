package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Daily activity record for check-ins and check-outs")
public record DailyActivity(
        @Schema(description = "Unique booking identifier", example = "1")
        Long bookingId,

        @Schema(description = "Current booking status indicating the type of activity",
                example = "unconfirmed",
                allowableValues = {"unconfirmed", "checked-in", "checked-out"})
        BookingStatus status,

        @Schema(description = "Number of nights for the stay", example = "5", minimum = "1")
        Short numNights,

        @Schema(description = "Full name of the guest", example = "John Doe")
        String guestFullName,

        @Schema(description = "Country of the guest", example = "United States")
        String guestCountry) {

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}
