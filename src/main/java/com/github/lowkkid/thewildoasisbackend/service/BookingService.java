package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.dto.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.repository.projection.BookingSummary;

import java.util.List;

public interface BookingService {
    List<BookingSummary> getAll();
    BookingDTO getById(Long id);
    BookingDTO create(BookingDTO bookingDTO);
    BookingDTO update(Long id, BookingDTO bookingDTO);
    void delete(Long id);
}
