package com.github.lowkkid.thewildoasisbackend.controller;

import com.github.lowkkid.thewildoasisbackend.model.GuestDTO;
import com.github.lowkkid.thewildoasisbackend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
@AllArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAll() {
        List<GuestDTO> guests = guestService.getAll();
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getById(@PathVariable Long id) {
        GuestDTO guest = guestService.getById(id);
        return ResponseEntity.ok(guest);
    }

    @PostMapping
    public ResponseEntity<GuestDTO> create(@RequestBody GuestDTO guestDTO) {
        GuestDTO createdGuest = guestService.create(guestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGuest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> update(@PathVariable Long id, @RequestBody GuestDTO guestDTO) {
        GuestDTO updatedGuest = guestService.update(id, guestDTO);
        return ResponseEntity.ok(updatedGuest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

