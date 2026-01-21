package com.github.lowkkid.lodgecore.guest.mapper;

import com.github.lowkkid.lodgecore.guest.domain.entity.Guest;
import com.github.lowkkid.lodgecore.guest.model.GuestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    GuestDTO toDto(Guest guest);

    Guest toEntity(GuestDTO guestDTO);
}

