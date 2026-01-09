package com.github.lowkkid.thewildoasisbackend.booking.domain.repository;

import com.github.lowkkid.thewildoasisbackend.booking.domain.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.booking.model.DailyBookingSales;
import com.github.lowkkid.thewildoasisbackend.booking.model.BookingSummary;
import com.github.lowkkid.thewildoasisbackend.booking.model.BookingStatus;
import com.github.lowkkid.thewildoasisbackend.booking.model.StaySummary;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT new com.github.lowkkid.thewildoasisbackend.booking.model.BookingSummary(" +
            "b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests, b.status, b.totalPrice, " +
            "c.name, g.fullName, g.email)  " +
            "FROM Booking b JOIN b.cabin c JOIN b.guest g " +
            "WHERE (:status IS NULL OR b.status = :status)")
    Page<BookingSummary> findAllWithCabinsAndGuests(BookingStatus status, Pageable pageable);

    @Query("SELECT new com.github.lowkkid.thewildoasisbackend.booking.model.DailyBookingSales(" +
        "b.paidAt, SUM(b.totalPrice), SUM(b.extrasPrice)) " +
        "FROM Booking b " +
        "WHERE (b.paidAt BETWEEN :start AND :end) " +
        "GROUP BY b.paidAt"
    )
    List<DailyBookingSales> findDailySalesBetweenDates(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new com.github.lowkkid.thewildoasisbackend.booking.model.StaySummary(" +
            "b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests, b.status, " +
            "b.totalPrice, b.extrasPrice, b.cabinPrice, g.fullName)" +
            "FROM Booking b JOIN b.guest g " +
            "WHERE b.startDate BETWEEN :start AND :end")
    List<StaySummary> findAllStaysByStartDateBetween(LocalDateTime start, LocalDateTime end);

    @NotNull
    @Query("SELECT b FROM Booking b JOIN FETCH b.cabin c JOIN FETCH b.guest g JOIN FETCH g.country cn WHERE b.id=:id")
    Optional<Booking> findById(@NotNull Long id);

    @Modifying
    @Query("UPDATE Booking b set b.status = 'CHECKED_OUT' WHERE b.id = :id")
    void checkout(Long id);
}

