package com.github.lowkkid.thewildoasisbackend.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public record DailyActivity(Long bookingId,
                            BookingStatus status,
                            Short numNights,
                            String guestFullName,
                            String guestCountry) {
    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}
