package com.example.event_booking.event.booking.API.controller;

import com.example.event_booking.event.booking.API.model.EventFilterCriteria;
import com.example.event_booking.event.booking.API.model.command.CreateBookingCommand;
import com.example.event_booking.event.booking.API.model.command.CreateEventCommand;
import com.example.event_booking.event.booking.API.model.command.UpdateBookingCommand;
import com.example.event_booking.event.booking.API.model.command.UpdateEventCommand;
import com.example.event_booking.event.booking.API.model.dto.BookingDto;
import com.example.event_booking.event.booking.API.model.dto.EventDto;
import com.example.event_booking.event.booking.API.service.BookingService;
import com.example.event_booking.event.booking.API.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final BookingService bookingService;
    private final EventService eventService;

    @PostMapping("/search")
    public Page<EventDto> getAll(@RequestBody(required = false) List<EventFilterCriteria> criteria, @PageableDefault Pageable pageable) {
        return eventService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public EventDto getById(@PathVariable int id) {
        return eventService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@Valid @RequestBody CreateEventCommand command) {
        return eventService.create(command);
    }

    @PutMapping("/{id}")
    public EventDto update(@Valid @PathVariable int id, @RequestBody UpdateEventCommand command) {
        return eventService.update(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        eventService.deleteById(id);
    }

    @GetMapping("/{eventId}/bookings")
    public List<BookingDto> getAllBookings(@PathVariable int eventId) {
        return bookingService.getAllByEventId(eventId);
    }

    @GetMapping("/{eventId}/bookings/{bookingId}")
    public BookingDto getBookingById(@PathVariable int eventId, @PathVariable int bookingId) {
        return bookingService.getById(eventId, bookingId);
    }

    @PostMapping("/{eventId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto createBooking(@PathVariable int eventId, @Valid @RequestBody CreateBookingCommand command) {
        return bookingService.create(eventId, command);
    }

    @PutMapping("/{eventId}/bookings/{bookingId}")
    public BookingDto updateBooking(@PathVariable int eventId, @PathVariable int bookingId, @RequestBody UpdateBookingCommand command) {
        return bookingService.update(eventId, bookingId, command);
    }

    @DeleteMapping("/{eventId}/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteById(bookingId);

    }
}
