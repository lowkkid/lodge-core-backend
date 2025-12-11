package com.github.lowkkid.thewildoasisbackend.domain.repository;

import com.github.lowkkid.thewildoasisbackend.domain.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.model.enums.BookingStatus;
import com.github.lowkkid.thewildoasisbackend.domain.repository.projection.BookingSummary;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT new com.github.lowkkid.thewildoasisbackend.domain.repository.projection.BookingSummary(" +
            "b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests, b.status, b.totalPrice, " +
            "c.name, g.fullName, g.email)  " +
            "FROM Booking b JOIN b.cabin c JOIN b.guest g " +
            "WHERE (:status IS NULL OR b.status = :status)")
    Page<BookingSummary> findAllWithCabinsAndGuests(BookingStatus status, Pageable pageable);

    @NotNull
    @Query("SELECT b FROM Booking b JOIN FETCH b.cabin c JOIN FETCH b.guest g JOIN FETCH g.country cn WHERE b.id=:id")
    Optional<Booking> findById(@NotNull Long id);

    @Modifying
    @Query("UPDATE Booking b set b.status = 'CHECKED_IN', b.isPaid = true WHERE b.id = :id")
    void checkin(Long id);

    @Modifying
    @Query("UPDATE Booking b set b.status = 'CHECKED_OUT' WHERE b.id = :id")
    void checkout(Long id);
}

