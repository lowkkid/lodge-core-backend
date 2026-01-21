package com.github.lowkkid.lodgecore.booking.service.impl;

import static com.github.lowkkid.lodgecore.common.utils.Constants.RESOURCE_WITH_ID_NOT_FOUND;

import com.github.lowkkid.lodgecore.booking.domain.entity.Booking;
import com.github.lowkkid.lodgecore.booking.domain.repository.BookingRepository;
import com.github.lowkkid.lodgecore.booking.mapper.BookingMapper;
import com.github.lowkkid.lodgecore.booking.model.BookingDTO;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import com.github.lowkkid.lodgecore.booking.model.BookingSummary;
import com.github.lowkkid.lodgecore.booking.model.CheckinRequest;
import com.github.lowkkid.lodgecore.booking.model.DailyActivity;
import com.github.lowkkid.lodgecore.booking.model.DailyBookingSales;
import com.github.lowkkid.lodgecore.booking.model.StaySummary;
import com.github.lowkkid.lodgecore.booking.service.BookingService;
import com.github.lowkkid.lodgecore.cabin.domain.repository.CabinRepository;
import com.github.lowkkid.lodgecore.common.exception.AlreadyExistsException;
import com.github.lowkkid.lodgecore.common.exception.NotFoundException;
import com.github.lowkkid.lodgecore.guest.domain.repository.GuestRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CabinRepository cabinRepository;
    private final GuestRepository guestRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Page<BookingSummary> getAll(
            BookingStatus status,
            Integer pageNumber,
            Integer pageSize,
            String sortField,
            Sort.Direction sortDirection) {
        return bookingRepository.findAllWithCabinsAndGuests(
                status, PageRequest.of(--pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public List<DailyBookingSales> getSalesBetweenDates(LocalDate start, LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        var nonZeroSales = bookingRepository.findDailySalesBetweenDates(startDateTime, endDateTime);
        var nonZeroSalesByDateMap  = nonZeroSales.stream()
                .collect(Collectors.toMap(DailyBookingSales::date, Function.identity()));


        return start.datesUntil(end.plusDays(1))
                .map(date -> nonZeroSalesByDateMap.getOrDefault(date, DailyBookingSales.empty(date)))
                .toList();
    }

    @Override
    public Integer getBookingsCountBetweenDates(LocalDate start, LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return bookingRepository.countBookingsByCreatedAtBetween(startDateTime, endDateTime);
    }

    @Override
    public List<StaySummary> getStaySummariesBetweenDates(LocalDate start, LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return bookingRepository.findAllStaysByStartDateBetween(startDateTime, endDateTime);
    }

    @Override
    public List<DailyActivity> getTodayActivity() {
        return bookingRepository.getActivityForTheDay(LocalDate.now());
    }

    @Override
    public BookingDTO getById(Long id) {
        var booking = getEntityById(id);
        return bookingMapper.toDto(booking);
    }

    @Override
    @Transactional
    public void checkin(Long id, CheckinRequest checkinRequest) {
        var booking = getEntityById(id);

        if (!BookingStatus.UNCONFIRMED.equals(booking.getStatus())) {
            throw new IllegalArgumentException("Only unconfirmed bookings can be checked in");
        }
        booking.setStatus(BookingStatus.CHECKED_IN);
        booking.setIsPaid(true);
        if (checkinRequest.getAddBreakfast()) {
            if (booking.getHasBreakfast()) {
                throw new AlreadyExistsException("Booking with id " + id + " already has breakfast");
            }
            booking.setHasBreakfast(true);
            booking.setExtrasPrice(checkinRequest.getExtrasPrice());
            booking.setTotalPrice(checkinRequest.getTotalPrice());
        }
    }

    @Override
    @Transactional
    public void checkout(Long id) {
        var booking = getEntityById(id);

        if (!BookingStatus.CHECKED_IN.equals(booking.getStatus())) {
            throw new IllegalArgumentException("Only checked in bookings can be checked out");
        }

        booking.setStatus(BookingStatus.CHECKED_OUT);
    }

    @Override
    @Transactional
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);

        booking.setCabin(cabinRepository.findById(bookingDTO.getCabin().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format(RESOURCE_WITH_ID_NOT_FOUND, "Cabin", bookingDTO.getCabin().getId()))));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuest().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format(RESOURCE_WITH_ID_NOT_FOUND, "Guest", bookingDTO.getGuest().getId()))));

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    @Transactional
    public BookingDTO update(Long id, BookingDTO bookingDTO) {
        var existingBooking = getEntityById(id);

        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setId(existingBooking.getId());

        booking.setCabin(cabinRepository.findById(bookingDTO.getCabin().getId())
                .orElseThrow(() -> new NotFoundException(String.format(
                        RESOURCE_WITH_ID_NOT_FOUND, "Cabin", bookingDTO.getCabin().getId()))));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuest().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format(RESOURCE_WITH_ID_NOT_FOUND, "Guest", bookingDTO.getGuest().getId()))));

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public void delete(Long id) {
        var booking = getEntityById(id);
        bookingRepository.delete(booking);
    }

    private Booking getEntityById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Booking", id)));
    }
}