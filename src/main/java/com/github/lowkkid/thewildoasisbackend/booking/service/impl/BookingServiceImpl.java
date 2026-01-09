package com.github.lowkkid.thewildoasisbackend.booking.service.impl;

import com.github.lowkkid.thewildoasisbackend.booking.model.*;
import com.github.lowkkid.thewildoasisbackend.common.exception.AlreadyExistsException;
import com.github.lowkkid.thewildoasisbackend.booking.domain.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.common.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.booking.mapper.BookingMapper;
import com.github.lowkkid.thewildoasisbackend.booking.domain.repository.BookingRepository;
import com.github.lowkkid.thewildoasisbackend.cabin.domain.repository.CabinRepository;
import com.github.lowkkid.thewildoasisbackend.guest.domain.repository.GuestRepository;
import com.github.lowkkid.thewildoasisbackend.booking.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CabinRepository cabinRepository;
    private final GuestRepository guestRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Page<BookingSummary> getAll(BookingStatus status, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection) {

        return bookingRepository.findAllWithCabinsAndGuests(
                status, PageRequest.of(--pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public List<DailyBookingSales> getSalesBetweenDates(LocalDate start, LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return bookingRepository.findDailySalesBetweenDates(startDateTime, endDateTime);
    }

    @Override
    public List<StaySummary> getStaySummariesBetweenDates(LocalDate start, LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return bookingRepository.findAllStaysByStartDateBetween(startDateTime, endDateTime);
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
        bookingRepository.checkout(id);
    }

    @Override
    @Transactional
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);

        // Load Cabin and Guest entities
        booking.setCabin(cabinRepository.findById(bookingDTO.getCabin().getId())
                .orElseThrow(() -> new NotFoundException("Cabin with id " + bookingDTO.getCabin().getId() + " not found")));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuest().getId())
                .orElseThrow(() -> new NotFoundException("Guest with id " + bookingDTO.getGuest().getId() + " not found")));

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    @Transactional
    public BookingDTO update(Long id, BookingDTO bookingDTO) {
        var existingBooking = getEntityById(id);

        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setId(existingBooking.getId());

        // Load Cabin and Guest entities
        booking.setCabin(cabinRepository.findById(bookingDTO.getCabin().getId())
                .orElseThrow(() -> new NotFoundException("Cabin with id " + bookingDTO.getCabin().getId() + " not found")));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuest().getId())
                .orElseThrow(() -> new NotFoundException("Guest with id " + bookingDTO.getGuest().getId() + " not found")));

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
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
    }
}