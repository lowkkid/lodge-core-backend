package com.github.lowkkid.thewildoasisbackend.booking.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyBookingSales(LocalDate date, BigDecimal totalBookingPrice, BigDecimal totalExtrasPrice) {

}
