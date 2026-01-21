package com.github.lowkkid.lodgecore.booking.controller;

import com.github.lowkkid.lodgecore.booking.model.BookingDTO;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import com.github.lowkkid.lodgecore.booking.model.BookingSummary;
import com.github.lowkkid.lodgecore.booking.model.CheckinRequest;
import com.github.lowkkid.lodgecore.booking.model.DailyActivity;
import com.github.lowkkid.lodgecore.booking.model.DailyBookingSales;
import com.github.lowkkid.lodgecore.booking.model.StaySummary;
import com.github.lowkkid.lodgecore.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingSummary>> getAll(@RequestParam(required = false) BookingStatus status,
                                                       @RequestParam(defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                       @RequestParam(defaultValue = "startDate") String sortField,
                                                       @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        var bookings = bookingService.getAll(status, pageNumber, pageSize, sortField, sortDirection);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<DailyBookingSales>> getSalesBetweenDates(
            @Parameter(
                    description = "Start date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-09-15"
                    )
            ) @RequestParam LocalDate start,
            @Parameter(
                    description = "End date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-10-15"
                    )
            )
            @RequestParam LocalDate end) {
        var sales = bookingService.getSalesBetweenDates(start, end);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getBookingCountBetweenDates(
            @Parameter(
                    description = "Start date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-09-15"
                    )
            ) @RequestParam LocalDate start,
            @Parameter(
                    description = "End date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-10-15"
                    )
            ) @RequestParam LocalDate end
    ) {
        var count = bookingService.getBookingsCountBetweenDates(start, end);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stays")
    public ResponseEntity<List<StaySummary>> getStaySummariesBetweenDates(
            @Parameter(
                    description = "Start date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-09-15"
                    )
            ) @RequestParam LocalDate start,
            @Parameter(
                    description = "End date",
                    schema = @Schema(
                            type = "string",
                            format = "date",
                            example = "2025-10-15"
                    )
            ) @RequestParam LocalDate end
    ) {
        var stays = bookingService.getStaySummariesBetweenDates(start, end);
        return ResponseEntity.ok(stays);
    }

    @GetMapping("/today")
    public ResponseEntity<List<DailyActivity>> getActivityForToday() {
        var activity = bookingService.getTodayActivity();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        var booking = bookingService.getById(id);
        return ResponseEntity.ok(booking);
    }

    @PatchMapping("/{id}/checkin")
    public ResponseEntity<Void> checkin(@PathVariable Long id, @RequestBody @Valid CheckinRequest checkinRequest) {
        bookingService.checkin(id, checkinRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/checkout")
    public ResponseEntity<Void> checkout(@PathVariable Long id) {
        bookingService.checkout(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO bookingDTO) {
        var createdBooking = bookingService.create(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        var updatedBooking = bookingService.update(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

