package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.dto.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.mapper.BookingMapper;
import com.github.lowkkid.thewildoasisbackend.repository.BookingRepository;
import com.github.lowkkid.thewildoasisbackend.repository.CabinRepository;
import com.github.lowkkid.thewildoasisbackend.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CabinRepository cabinRepository;
    private final GuestRepository guestRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDTO> getAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookingDTO getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);
        
        // Load Cabin and Guest entities
        booking.setCabin(cabinRepository.findById(bookingDTO.getCabinId())
                .orElseThrow(() -> new NotFoundException("Cabin with id " + bookingDTO.getCabinId() + " not found")));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuestId())
                .orElseThrow(() -> new NotFoundException("Guest with id " + bookingDTO.getGuestId() + " not found")));
        
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Transactional
    public BookingDTO update(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setId(existingBooking.getId());
        
        // Load Cabin and Guest entities
        booking.setCabin(cabinRepository.findById(bookingDTO.getCabinId())
                .orElseThrow(() -> new NotFoundException("Cabin with id " + bookingDTO.getCabinId() + " not found")));
        booking.setGuest(guestRepository.findById(bookingDTO.getGuestId())
                .orElseThrow(() -> new NotFoundException("Guest with id " + bookingDTO.getGuestId() + " not found")));
        
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    public void delete(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        bookingRepository.delete(booking);
    }
}
