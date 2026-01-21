package com.github.lowkkid.lodgecore.guest.controller;

import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
import com.github.lowkkid.lodgecore.guest.service.GuestService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

