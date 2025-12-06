package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.model.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.model.enums.BookingStatus;
import com.github.lowkkid.thewildoasisbackend.repository.projection.BookingSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BookingService {
    Page<BookingSummary> getAll(
            BookingStatus status, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection);

    BookingDTO getById(Long id);

    BookingDTO create(BookingDTO bookingDTO);

    BookingDTO update(Long id, BookingDTO bookingDTO);

    void delete(Long id);
}
