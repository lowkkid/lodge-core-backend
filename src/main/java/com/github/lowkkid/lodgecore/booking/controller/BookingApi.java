package com.github.lowkkid.lodgecore.booking.controller;

import com.github.lowkkid.lodgecore.booking.model.BookingDTO;
import com.github.lowkkid.lodgecore.booking.model.BookingStatus;
import com.github.lowkkid.lodgecore.booking.model.BookingSummary;
import com.github.lowkkid.lodgecore.booking.model.CheckinRequest;
import com.github.lowkkid.lodgecore.booking.model.DailyActivity;
import com.github.lowkkid.lodgecore.booking.model.DailyBookingSales;
import com.github.lowkkid.lodgecore.booking.model.StaySummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

@Tag(name = "Bookings", description = "API for booking management")
public interface BookingApi {

    @Operation(
            summary = "Get all bookings",
            description = "Retrieves a paginated list of booking summaries with optional filtering by status. "
                    + "Results include cabin and guest information for each booking."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings page",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string")))
    })
    ResponseEntity<Page<BookingSummary>> getAll(
            @Parameter(description = "Filter bookings by status",
                    schema = @Schema(allowableValues = {"UNCONFIRMED", "CHECKED_IN", "CHECKED_OUT"}))
            BookingStatus status,
            @Parameter(description = "Page number (1-indexed)", example = "1")
            Integer pageNumber,
            @Parameter(description = "Number of items per page", example = "10")
            Integer pageSize,
            @Parameter(
                    description = "Field to sort by (if not specified - 'startDate' will be applied)",
                    example = "startDate",
                    schema = @Schema(allowableValues = {"startDate", "totalPrice"}))
            String sortField,
            @Parameter(
                    description = "Sort direction (if not specified - 'DESC' will be applied)",
                    example = "DESC")
            Sort.Direction sortDirection
    );

    @Operation(
            summary = "Get daily booking sales",
            description = "Retrieves daily sales statistics within a date range. Returns a list with one entry per "
                    + "day, including days with zero sales. Each entry contains total booking price and extras price."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved daily sales data",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DailyBookingSales.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string")))
    })
    ResponseEntity<List<DailyBookingSales>> getSalesBetweenDates(
            @Parameter(description = "Start date of the sales period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-09-15"))
            LocalDate start,
            @Parameter(description = "End date of the sales period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-10-15"))
            LocalDate end);

    @Operation(
            summary = "Get booking count",
            description = "Returns the total number of bookings created within the specified date range. "
                    + "Counts are based on the booking creation date (createdAt), not the stay dates."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved booking count",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "integer", example = "42"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string")))
    })
    ResponseEntity<Integer> getBookingCountBetweenDates(
            @Parameter(description = "Start date of the period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-09-15"))
            LocalDate start,
            @Parameter(description = "End date of the period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-10-15"))
            LocalDate end
    );

    @Operation(
            summary = "Get stay summaries",
            description = "Retrieves a list of stay summaries for bookings with start dates within the specified range."
                    + " Includes pricing breakdown (cabin price, extras price, total price) and guest information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved stay summaries",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StaySummary.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string")))
    })
    ResponseEntity<List<StaySummary>> getStaySummariesBetweenDates(
            @Parameter(description = "Start date of the period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-09-15"))
            LocalDate start,
            @Parameter(description = "End date of the period (inclusive)",
                    required = true,
                    schema = @Schema(type = "string", format = "date", example = "2025-10-15"))
            LocalDate end
    );

    @Operation(
            summary = "Get today's activity",
            description = "Retrieves all booking activities scheduled for today, including check-ins and check-outs. "
                    + "Returns guest details and booking status for operational dashboard use."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved today's activities",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DailyActivity.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string")))
    })
    ResponseEntity<List<DailyActivity>> getActivityForToday();

    @Operation(
            summary = "Get booking by ID",
            description = "Retrieves detailed booking information including cabin details, guest information, "
                    + "pricing breakdown, and current status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved booking",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(type = "string", example = "Booking with id 123 not found")))
    })
    ResponseEntity<BookingDTO> getById(
            @Parameter(description = "Booking ID", required = true, example = "1")
            Long id);

    @Operation(
            summary = "Check in a booking",
            description = "Checks in a guest for their booking. Changes status from UNCONFIRMED to CHECKED_IN, "
                    + "marks booking as paid, and optionally adds breakfast with updated pricing. "
                    + "Only bookings with UNCONFIRMED status can be checked in."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully checked in"),
            @ApiResponse(responseCode = "400", description = "Invalid request - booking is not in UNCONFIRMED status "
                    + "or validation failed",
                    content = @Content(schema = @Schema(type = "string",
                            example = "Only unconfirmed bookings can be checked in"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(type = "string", example = "Booking with id 123 not found"))),
            @ApiResponse(responseCode = "409", description = "Conflict - booking already has breakfast",
                    content = @Content(schema = @Schema(type = "string",
                            example = "Booking with id 123 already has breakfast")))
    })
    ResponseEntity<Void> checkin(
            @Parameter(description = "Booking ID", required = true, example = "1")
            Long id,
            CheckinRequest checkinRequest);

    @Operation(
            summary = "Check out a booking",
            description = "Checks out a guest from their booking. Changes status from CHECKED_IN to CHECKED_OUT. "
                    + "Only bookings with CHECKED_IN status can be checked out."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully checked out"),
            @ApiResponse(responseCode = "400", description = "Invalid request - booking is not in CHECKED_IN status",
                    content = @Content(schema = @Schema(type = "string",
                            example = "Only checked in bookings can be checked out"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(type = "string", example = "Booking with id 123 not found")))
    })
    ResponseEntity<Void> checkout(
            @Parameter(description = "Booking ID", required = true, example = "1")
            Long id);

    @Operation(
            summary = "Delete a booking",
            description = "Permanently deletes a booking from the system. This action cannot be undone."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted booking"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid",
                    content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(type = "string", example = "Booking with id 123 not found")))
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Booking ID", required = true, example = "1")
            Long id);
}
