package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.entity.Guest;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    public Guest getById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guest with id " + id + " not found"));
    }

    public Guest create(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest update(Long id, Guest guest) {
        Guest existingGuest = getById(id);
        guest.setId(existingGuest.getId());
        return guestRepository.save(guest);
    }

    public void delete(Long id) {
        Guest guest = getById(id);
        guestRepository.delete(guest);
    }
}
