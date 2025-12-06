package com.github.lowkkid.thewildoasisbackend.repository.projection;

import com.github.lowkkid.thewildoasisbackend.model.enums.BookingStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BookingSummary {

    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Short numNights;
    private final Short numGuests;
    @Getter(AccessLevel.NONE)
    private final BookingStatus status;
    private final BigDecimal totalPrice;
    private final String cabinName;
    private final String guestFullName;
    private final String guestEmail;

    public String getStatus() {
        return status.toString();
    }
}