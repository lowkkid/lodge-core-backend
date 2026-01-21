package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.booking.domain.entity.Booking;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockBookingsProvider {
    private static final LocalDateTime NOW = LocalDateTime.now();

    public static final Booking BOOKING_1 = Booking.builder()
            .startDate(NOW.plusDays(15))
            .endDate(NOW.plusDays(22))
            .numNights((short) 7)
            .numGuests((short) 1)
            .cabinPrice(new BigDecimal("250.00"))
            .extrasPrice(new BigDecimal("15.00"))
            .totalPrice(new BigDecimal("1765.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(false)
            .observations("I have a gluten allergy and would like to request a gluten-free breakfast.")
            .paidAt(null)
            .createdAt(NOW.minusDays(1))
            .build();

    public static final Booking BOOKING_2 = Booking.builder()
            .startDate(NOW.minusDays(5))
            .endDate(NOW.plusDays(5))
            .numNights((short) 10)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("250.00"))
            .extrasPrice(new BigDecimal("30.00"))
            .totalPrice(new BigDecimal("2530.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(5).plusHours(2))
            .createdAt(NOW.minusDays(15))
            .build();

    public static final Booking BOOKING_3 = Booking.builder()
            .startDate(NOW.plusDays(20))
            .endDate(NOW.plusDays(26))
            .numNights((short) 6)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("250.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("1500.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(10).plusHours(3))
            .createdAt(NOW.minusDays(10))
            .build();

    public static final Booking BOOKING_4 = Booking.builder()
            .startDate(NOW.minusDays(20))
            .endDate(NOW.minusDays(4))
            .numNights((short) 16)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("325.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("5200.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(20).plusHours(3))
            .createdAt(NOW.minusDays(30))
            .build();

    public static final Booking BOOKING_5 = Booking.builder()
            .startDate(NOW.plusDays(25))
            .endDate(NOW.plusDays(28))
            .numNights((short) 3)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("325.00"))
            .extrasPrice(new BigDecimal("30.00"))
            .totalPrice(new BigDecimal("1005.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(false)
            .observations(null)
            .paidAt(null)
            .createdAt(NOW.minusDays(2))
            .build();

    public static final Booking BOOKING_6 = Booking.builder()
            .startDate(NOW.plusDays(40))
            .endDate(NOW.plusDays(55))
            .numNights((short) 15)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("325.00"))
            .extrasPrice(new BigDecimal("30.00"))
            .totalPrice(new BigDecimal("4905.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(false)
            .observations(null)
            .paidAt(null)
            .createdAt(NOW.minusDays(5))
            .build();

    public static final Booking BOOKING_7 = Booking.builder()
            .startDate(NOW.minusDays(7))
            .endDate(NOW.minusDays(2))
            .numNights((short) 5)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("300.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("1560.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(7).plusHours(1))
            .createdAt(NOW.minusDays(40))
            .build();

    public static final Booking BOOKING_8 = Booking.builder()
            .startDate(NOW.plusDays(2))
            .endDate(NOW.plusDays(4))
            .numNights((short) 2)
            .numGuests((short) 3)
            .cabinPrice(new BigDecimal("300.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("600.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(false)
            .isPaid(true)
            .observations("We will be bringing our small dog with us")
            .paidAt(NOW.minusDays(2).minusHours(2))
            .createdAt(NOW.minusDays(3))
            .build();

    public static final Booking BOOKING_9 = Booking.builder()
            .startDate(NOW.plusDays(3))
            .endDate(NOW.plusDays(6))
            .numNights((short) 3)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("300.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("960.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(6).plusHours(4))
            .createdAt(NOW.minusDays(6).minusHours(2))
            .build();

    public static final Booking BOOKING_10 = Booking.builder()
            .startDate(NOW.plusDays(10))
            .endDate(NOW.plusDays(19))
            .numNights((short) 9)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("450.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("4110.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(10).plusHours(3))
            .createdAt(NOW.minusDays(12))
            .build();

    public static final Booking BOOKING_11 = Booking.builder()
            .startDate(NOW.plusDays(20))
            .endDate(NOW.plusDays(25))
            .numNights((short) 5)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("450.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("2310.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusHours(1))
            .createdAt(NOW.minusDays(1))
            .build();

    public static final Booking BOOKING_12 = Booking.builder()
            .startDate(NOW.plusDays(28))
            .endDate(NOW.plusDays(29))
            .numNights((short) 1)
            .numGuests((short) 1)
            .cabinPrice(new BigDecimal("450.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("450.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW)
            .createdAt(NOW)
            .build();

    public static final Booking BOOKING_13 = Booking.builder()
            .startDate(NOW.plusDays(22))
            .endDate(NOW.plusDays(29))
            .numNights((short) 7)
            .numGuests((short) 5)
            .cabinPrice(new BigDecimal("350.00"))
            .extrasPrice(new BigDecimal("75.00"))
            .totalPrice(new BigDecimal("2525.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(false)
            .observations(null)
            .paidAt(null)
            .createdAt(NOW.minusDays(1))
            .build();

    public static final Booking BOOKING_14 = Booking.builder()
            .startDate(NOW.minusDays(2))
            .endDate(NOW)
            .numNights((short) 2)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("350.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("760.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(2).plusMinutes(25))
            .createdAt(NOW.minusDays(2))
            .build();

    public static final Booking BOOKING_15 = Booking.builder()
            .startDate(NOW.minusDays(3))
            .endDate(NOW)
            .numNights((short) 3)
            .numGuests((short) 6)
            .cabinPrice(new BigDecimal("350.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("1050.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(3).plusHours(1))
            .createdAt(NOW.minusDays(3))
            .build();

    public static final Booking BOOKING_16 = Booking.builder()
            .startDate(NOW.plusDays(4))
            .endDate(NOW.plusDays(15))
            .numNights((short) 11)
            .numGuests((short) 6)
            .cabinPrice(new BigDecimal("700.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("7700.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(false)
            .isPaid(true)
            .observations("We will be checking in late, around midnight. Hope that's okay :)")
            .paidAt(NOW.minusDays(4).plusHours(6))
            .createdAt(NOW.minusDays(3))
            .build();

    public static final Booking BOOKING_17 = Booking.builder()
            .startDate(NOW.minusDays(12))
            .endDate(NOW.minusDays(5))
            .numNights((short) 7)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("700.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("4960.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations("I will need a rollaway bed for one of the guests")
            .paidAt(NOW.minusDays(12))
            .createdAt(NOW.minusDays(13))
            .build();

    public static final Booking BOOKING_18 = Booking.builder()
            .startDate(NOW.minusDays(8))
            .endDate(NOW.minusDays(5))
            .numNights((short) 3)
            .numGuests((short) 6)
            .cabinPrice(new BigDecimal("700.00"))
            .extrasPrice(new BigDecimal("90.00"))
            .totalPrice(new BigDecimal("2190.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(8))
            .createdAt(NOW.minusDays(15))
            .build();

    public static final Booking BOOKING_19 = Booking.builder()
            .startDate(NOW.plusDays(25))
            .endDate(NOW.plusDays(31))
            .numNights((short) 6)
            .numGuests((short) 8)
            .cabinPrice(new BigDecimal("500.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("3000.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(false)
            .isPaid(false)
            .observations(null)
            .paidAt(null)
            .createdAt(NOW.minusDays(2))
            .build();

    public static final Booking BOOKING_20 = Booking.builder()
            .startDate(NOW.plusDays(45))
            .endDate(NOW.plusDays(55))
            .numNights((short) 10)
            .numGuests((short) 7)
            .cabinPrice(new BigDecimal("500.00"))
            .extrasPrice(new BigDecimal("105.00"))
            .totalPrice(new BigDecimal("5105.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(16).plusMinutes(5))
            .createdAt(NOW.minusDays(16))
            .build();

    public static final Booking BOOKING_21 = Booking.builder()
            .startDate(NOW.minusDays(37).minusHours(5))
            .endDate(NOW.minusDays(42))
            .numNights((short) 5)
            .numGuests((short) 6)
            .cabinPrice(new BigDecimal("500.00"))
            .extrasPrice(new BigDecimal("90.00"))
            .totalPrice(new BigDecimal("2590.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(37).minusHours(5))
            .createdAt(NOW.minusDays(50))
            .build();

    public static final Booking BOOKING_22 = Booking.builder()
            .startDate(NOW.minusDays(3))
            .endDate(NOW.plusDays(2))
            .numNights((short) 5)
            .numGuests((short) 9)
            .cabinPrice(new BigDecimal("1400.00"))
            .extrasPrice(new BigDecimal("135.00"))
            .totalPrice(new BigDecimal("7135.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(true)
            .isPaid(true)
            .observations("My wife has a gluten allergy so I would like to request a gluten-free breakfast if possible")
            .paidAt(NOW.minusDays(3))
            .createdAt(NOW.minusDays(4))
            .build();

    public static final Booking BOOKING_23 = Booking.builder()
            .startDate(NOW.minusDays(3))
            .endDate(NOW.plusDays(7))
            .numNights((short) 10)
            .numGuests((short) 5)
            .cabinPrice(new BigDecimal("1400.00"))
            .extrasPrice(new BigDecimal("150.00"))
            .totalPrice(new BigDecimal("7150.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(true)
            .isPaid(true)
            .observations("I am celebrating my anniversary, can you arrange for any special amenities or decorations?")
            .paidAt(NOW.minusDays(6).plusMinutes(10))
            .createdAt(NOW.minusDays(6))
            .build();

    public static final Booking BOOKING_24 = Booking.builder()
            .startDate(NOW.plusDays(18))
            .endDate(NOW.plusDays(21))
            .numNights((short) 3)
            .numGuests((short) 7)
            .cabinPrice(new BigDecimal("1400.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("4200.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(false)
            .isPaid(false)
            .observations(null)
            .paidAt(null)
            .createdAt(NOW.minusDays(6))
            .build();

    public static final Booking BOOKING_25 = Booking.builder()
            .startDate(NOW.minusDays(2).plusHours(2))
            .endDate(NOW.plusHours(1))
            .numNights((short) 2)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("700.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("700.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(2).plusHours(2).minusMinutes(5))
            .createdAt(NOW.minusDays(6))
            .build();

    public static final Booking BOOKING_26 = Booking.builder()
            .startDate(NOW.plusHours(3))
            .endDate(NOW.plusDays(4))
            .numNights((short) 3)
            .numGuests((short) 7)
            .cabinPrice(new BigDecimal("1400.00"))
            .extrasPrice(BigDecimal.ZERO)
            .totalPrice(new BigDecimal("1400.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(false)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(3).plusHours(2).minusMinutes(5))
            .createdAt(NOW.minusDays(3).plusHours(2).minusMinutes(5))
            .build();

    public static final Booking BOOKING_27 = Booking.builder()
            .startDate(NOW.minusDays(2).plusHours(3))
            .endDate(NOW.plusDays(4))
            .numNights((short) 6)
            .numGuests((short) 2)
            .cabinPrice(new BigDecimal("300.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("1860.00"))
            .status(BookingStatus.CHECKED_IN)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(2).plusHours(3))
            .createdAt(NOW.minusDays(40))
            .build();

    public static final Booking BOOKING_28 = Booking.builder()
            .startDate(NOW.plusHours(1))
            .endDate(NOW.plusDays(5))
            .numNights((short) 5)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("450.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("2310.00"))
            .status(BookingStatus.UNCONFIRMED)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(1))
            .createdAt(NOW.minusDays(1))
            .build();


    public static final Booking BOOKING_29 = Booking.builder()
            .startDate(NOW.minusDays(55))
            .endDate(NOW.minusDays(49))
            .numNights((short) 6)
            .numGuests((short) 4)
            .cabinPrice(new BigDecimal("250.00"))
            .extrasPrice(new BigDecimal("60.00"))
            .totalPrice(new BigDecimal("1560.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(55))
            .createdAt(NOW.minusDays(66))
            .build();


    public static final Booking BOOKING_30 = Booking.builder()
            .startDate(NOW.minusDays(78))
            .endDate(NOW.plusDays(75))
            .numNights((short) 3)
            .numGuests((short) 5)
            .cabinPrice(new BigDecimal("200.00"))
            .extrasPrice(new BigDecimal("100.00"))
            .totalPrice(new BigDecimal("700.00"))
            .status(BookingStatus.CHECKED_OUT)
            .hasBreakfast(true)
            .isPaid(true)
            .observations(null)
            .paidAt(NOW.minusDays(87).minusHours(2))
            .createdAt(NOW.minusDays(87).minusHours(2))
            .build();

    public static final List<Booking> ALL_BOOKINGS = Arrays.asList(
            BOOKING_1,
            BOOKING_2,
            BOOKING_3,
            BOOKING_4,
            BOOKING_5,
            BOOKING_6,
            BOOKING_7,
            BOOKING_8,
            BOOKING_9,
            BOOKING_10,
            BOOKING_11,
            BOOKING_12,
            BOOKING_13,
            BOOKING_14,
            BOOKING_15,
            BOOKING_16,
            BOOKING_17,
            BOOKING_18,
            BOOKING_19,
            BOOKING_20,
            BOOKING_21,
            BOOKING_22,
            BOOKING_23,
            BOOKING_24,
            BOOKING_25,
            BOOKING_26,
            BOOKING_27,
            BOOKING_28,
            BOOKING_29,
            BOOKING_30
    );
}