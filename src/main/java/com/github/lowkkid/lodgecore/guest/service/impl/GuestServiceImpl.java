package com.github.lowkkid.lodgecore.guest.service.impl;

import static com.github.lowkkid.lodgecore.common.utils.Constants.RESOURCE_WITH_ID_NOT_FOUND;

import com.github.lowkkid.lodgecore.common.exception.NotFoundException;
import com.github.lowkkid.lodgecore.guest.domain.entity.Guest;
import com.github.lowkkid.lodgecore.guest.domain.repository.GuestRepository;
import com.github.lowkkid.lodgecore.guest.mapper.GuestMapper;
import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
import com.github.lowkkid.lodgecore.guest.service.GuestService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    @Override
    public List<GuestDTO> getAll() {
        return guestRepository.findAll().stream()
                .map(guestMapper::toDto)
                .toList();
    }

    @Override
    public GuestDTO getById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Guest", id)));
        return guestMapper.toDto(guest);
    }

    @Override
    @Transactional
    public GuestDTO create(GuestDTO guestDTO) {
        Guest guest = guestMapper.toEntity(guestDTO);
        Guest savedGuest = guestRepository.save(guest);
        return guestMapper.toDto(savedGuest);
    }

    @Override
    @Transactional
    public GuestDTO update(Long id, GuestDTO guestDTO) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Guest", id)));
        Guest guest = guestMapper.toEntity(guestDTO);
        guest.setId(existingGuest.getId());
        Guest savedGuest = guestRepository.save(guest);
        return guestMapper.toDto(savedGuest);
    }

    @Override
    public void delete(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Guest", id)));
        guestRepository.delete(guest);
    }
}