package com.github.lowkkid.lodgecore.booking.domain.repository;

import com.github.lowkkid.lodgecore.booking.domain.entity.Booking;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import com.github.lowkkid.lodgecore.booking.model.BookingSummary;
import com.github.lowkkid.lodgecore.booking.model.DailyActivity;
import com.github.lowkkid.lodgecore.booking.model.DailyBookingSales;
import com.github.lowkkid.lodgecore.booking.model.StaySummary;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
    SELECT new com.github.lowkkid.lodgecore.booking.model.BookingSummary(
        b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests,
        b.status, b.totalPrice, c.name, g.fullName, g.email
    )
    FROM Booking b
    JOIN b.cabin c
    JOIN b.guest g
    WHERE (:status IS NULL OR b.status = :status)
           \s""")
    Page<BookingSummary> findAllWithCabinsAndGuests(BookingStatus status, Pageable pageable);

    @Query("""
    SELECT new com.github.lowkkid.lodgecore.booking.model.DailyBookingSales(
        CAST(b.paidAt AS localdate), SUM(b.totalPrice), SUM(b.extrasPrice)
    )
    FROM Booking b
    WHERE b.paidAt BETWEEN :start AND :end
    GROUP BY CAST(b.paidAt AS localdate)
    ORDER BY CAST(b.paidAt AS localdate) ASC
           \s""")
    List<DailyBookingSales> findDailySalesBetweenDates(LocalDateTime start, LocalDateTime end);

    int countBookingsByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
    SELECT new com.github.lowkkid.lodgecore.booking.model.StaySummary(
        b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests,
        b.status, b.totalPrice, b.extrasPrice, b.cabinPrice, g.fullName
    )
    FROM Booking b
    JOIN b.guest g
    WHERE (b.startDate BETWEEN :start AND :end)
        AND (b.status = 'CHECKED_IN' OR b.status = 'CHECKED_OUT')
           \s""")
    List<StaySummary> findAllStaysByStartDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
    SELECT new com.github.lowkkid.lodgecore.booking.model.DailyActivity(
        b.id, b.status, b.numNights, g.fullName, g.country.flag
    )
    FROM Booking b
    JOIN b.guest g
        WHERE (DATE(b.startDate) = :day  AND b.status = 'UNCONFIRMED')
            OR (DATE(b.endDate) = :day AND b.status = 'CHECKED_IN')
           \s""")
    List<DailyActivity> getActivityForTheDay(LocalDate day);

    @NotNull
    @Query("SELECT b FROM Booking b JOIN FETCH b.cabin c JOIN FETCH b.guest g JOIN FETCH g.country cn WHERE b.id=:id")
    Optional<Booking> findById(@NotNull Long id);
}

