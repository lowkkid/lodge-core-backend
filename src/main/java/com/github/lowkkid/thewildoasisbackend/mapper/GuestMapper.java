package com.github.lowkkid.thewildoasisbackend.mapper;

import com.github.lowkkid.thewildoasisbackend.dto.GuestDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Guest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestDTO toDto(Guest guest);
    Guest toEntity(GuestDTO guestDTO);
}

