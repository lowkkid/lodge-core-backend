package com.github.lowkkid.thewildoasisbackend.controller;

import com.github.lowkkid.thewildoasisbackend.model.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.model.enums.BookingStatus;
import com.github.lowkkid.thewildoasisbackend.domain.repository.projection.BookingSummary;
import com.github.lowkkid.thewildoasisbackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingSummary>> getAll(@RequestParam(required = false) BookingStatus status,
                                                       @RequestParam(defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(defaultValue = "10" ) Integer pageSize,
                                                       @RequestParam(defaultValue = "startDate") String sortField,
                                                       @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        var bookings = bookingService.getAll(status, pageNumber, pageSize, sortField, sortDirection);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getById(id);
        return ResponseEntity.ok(booking);
    }

    @PatchMapping("/{id}/checkin")
    public ResponseEntity<Void> checkin(@PathVariable Long id) {
        bookingService.checkin(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/checkout")
    public ResponseEntity<Void> checkout(@PathVariable Long id) {
        bookingService.checkout(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.create(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.update(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

