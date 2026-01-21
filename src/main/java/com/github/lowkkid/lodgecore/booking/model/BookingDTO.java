package com.github.lowkkid.lodgecore.booking.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
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
public class BookingDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Short numNights;
    private Short numGuests;
    private BigDecimal cabinPrice;
    private BigDecimal extrasPrice;
    private BigDecimal totalPrice;
    private BookingStatus status;
    private Boolean hasBreakfast;
    private Boolean isPaid;
    private String observations;
    private CabinDTO cabin;
    private GuestDTO guest;
    private LocalDateTime createdAt;

    @JsonGetter("status")
    public String getStatusAsString() {
        return status.toString();
    }
}

