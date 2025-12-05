package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.repository.projection.BookingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT new com.github.lowkkid.thewildoasisbackend.repository.projection.BookingSummary(" +
            "b.id, b.createdAt, b.startDate, b.endDate, b.numNights, b.numGuests, b.status, b.totalPrice, " +
            "c.name, g.fullName, g.email)  " +
            "FROM Booking b JOIN b.cabin c JOIN b.guest g")
    List<BookingSummary> findAllWithCabinsAndGuests();
}

