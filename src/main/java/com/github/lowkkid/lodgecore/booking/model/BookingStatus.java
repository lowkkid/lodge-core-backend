package com.github.lowkkid.lodgecore.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Schema(description = "Booking status indicating the current state of a reservation")
public enum BookingStatus {

    @Schema(description = "Guest has checked in and is currently staying")
    CHECKED_IN("checked-in"),

    @Schema(description = "Guest has completed their stay and checked out")
    CHECKED_OUT("checked-out"),

    @Schema(description = "Booking is created but guest has not yet checked in")
    UNCONFIRMED("unconfirmed");

    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static BookingStatus fromValue(String value) {
        for (BookingStatus status : values()) {
            if (value.equals(status.value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid booking status value: " + value);
    }
}
