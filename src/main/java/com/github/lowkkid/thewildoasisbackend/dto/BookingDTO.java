package com.github.lowkkid.thewildoasisbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String status;
    private Boolean hasBreakfast;
    private Boolean isPaid;
    private String observations;
    private CabinDTO cabin;
    private GuestDTO guest;
    private LocalDateTime createdAt;
}

