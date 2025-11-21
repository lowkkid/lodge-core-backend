package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.dto.GuestDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Guest;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.mapper.GuestMapper;
import com.github.lowkkid.thewildoasisbackend.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    public List<GuestDTO> getAll() {
        return guestRepository.findAll().stream()
                .map(guestMapper::toDto)
                .collect(Collectors.toList());
    }

    public GuestDTO getById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guest with id " + id + " not found"));
        return guestMapper.toDto(guest);
    }

    @Transactional
    public GuestDTO create(GuestDTO guestDTO) {
        Guest guest = guestMapper.toEntity(guestDTO);
        Guest savedGuest = guestRepository.save(guest);
        return guestMapper.toDto(savedGuest);
    }

    @Transactional
    public GuestDTO update(Long id, GuestDTO guestDTO) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guest with id " + id + " not found"));
        Guest guest = guestMapper.toEntity(guestDTO);
        guest.setId(existingGuest.getId());
        Guest savedGuest = guestRepository.save(guest);
        return guestMapper.toDto(savedGuest);
    }

    public void delete(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guest with id " + id + " not found"));
        guestRepository.delete(guest);
    }
}
