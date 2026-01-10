package com.github.lowkkid.thewildoasisbackend.booking.service;

import com.github.lowkkid.thewildoasisbackend.booking.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    Page<BookingSummary> getAll(
            BookingStatus status, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection);

    List<DailyBookingSales> getSalesBetweenDates(LocalDate start, LocalDate end);

    Integer getBookingsCountBetweenDates(LocalDate start, LocalDate end);

    List<StaySummary> getStaySummariesBetweenDates(LocalDate start, LocalDate end);

    BookingDTO getById(Long id);

    void checkin(Long id, CheckinRequest checkinRequest);

    void checkout(Long id);

    BookingDTO create(BookingDTO bookingDTO);

    BookingDTO update(Long id, BookingDTO bookingDTO);

    void delete(Long id);
}
