package com.github.lowkkid.lodgecore.guest.service;

import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
import java.util.List;


public interface GuestService {

    List<GuestDTO> getAll();

    GuestDTO getById(Long id);

    GuestDTO create(GuestDTO guestDTO);

    GuestDTO update(Long id, GuestDTO guestDTO);

    void delete(Long id);
}
