package com.github.lowkkid.thewildoasisbackend.service.impl;

import com.github.lowkkid.thewildoasisbackend.model.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.mapper.BookingMapper;
import com.github.lowkkid.thewildoasisbackend.model.enums.BookingStatus;
import com.github.lowkkid.thewildoasisbackend.repository.BookingRepository;
import com.github.lowkkid.thewildoasisbackend.repository.CabinRepository;
import com.github.lowkkid.thewildoasisbackend.repository.GuestRepository;
import com.github.lowkkid.thewildoasisbackend.repository.projection.BookingSummary;
import com.github.lowkkid.thewildoasisbackend.service.BookingService;
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
    public Page<BookingSummary> getAll(BookingStatus status, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection) {

        return bookingRepository.findAllWithCabinsAndGuests(
                status, PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public BookingDTO getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        return bookingMapper.toDto(booking);
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
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));

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
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        bookingRepository.delete(booking);
    }
}