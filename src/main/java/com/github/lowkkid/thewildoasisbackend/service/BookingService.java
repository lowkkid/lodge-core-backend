package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
    }

    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking update(Long id, Booking booking) {
        Booking existingBooking = getById(id);
        booking.setId(existingBooking.getId());
        return bookingRepository.save(booking);
    }

    public void delete(Long id) {
        Booking booking = getById(id);
        bookingRepository.delete(booking);
    }
}
