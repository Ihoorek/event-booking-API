package com.example.event_booking.event.booking.API.controller;

import com.example.event_booking.event.booking.API.model.command.CreateUserCommand;
import com.example.event_booking.event.booking.API.model.command.UpdateUserCommand;
import com.example.event_booking.event.booking.API.model.dto.UserDto;
import com.example.event_booking.event.booking.API.service.BookingService;
import com.example.event_booking.event.booking.API.service.EventService;
import com.example.event_booking.event.booking.API.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

import java.awt.print.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDto> getAll(@PageableDefault Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody CreateUserCommand command) {
        return userService.create(command);
    }

    @PutMapping
    public UserDto update(@Valid @PathVariable int id, @RequestBody UpdateUserCommand command) {
        return userService.update(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.deleteById(id);
    }
}
