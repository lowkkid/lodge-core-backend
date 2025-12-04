package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN FETCH b.cabin c JOIN FETCH b.guest g")
    List<Booking> findAllWithCabinsAndGuests();
}

