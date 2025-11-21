package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

