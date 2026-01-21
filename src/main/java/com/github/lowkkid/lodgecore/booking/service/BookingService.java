package com.github.lowkkid.lodgecore.booking.service;

import com.github.lowkkid.lodgecore.booking.model.BookingDTO;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import com.github.lowkkid.lodgecore.booking.model.BookingSummary;
import com.github.lowkkid.lodgecore.booking.model.CheckinRequest;
import com.github.lowkkid.lodgecore.booking.model.DailyActivity;
import com.github.lowkkid.lodgecore.booking.model.DailyBookingSales;
import com.github.lowkkid.lodgecore.booking.model.StaySummary;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface BookingService {
    Page<BookingSummary> getAll(
            BookingStatus status, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection);

    List<DailyBookingSales> getSalesBetweenDates(LocalDate start, LocalDate end);

    Integer getBookingsCountBetweenDates(LocalDate start, LocalDate end);

    List<StaySummary> getStaySummariesBetweenDates(LocalDate start, LocalDate end);

    List<DailyActivity> getTodayActivity();

    BookingDTO getById(Long id);

    void checkin(Long id, CheckinRequest checkinRequest);

    void checkout(Long id);

    BookingDTO create(BookingDTO bookingDTO);

    BookingDTO update(Long id, BookingDTO bookingDTO);

    void delete(Long id);
}
