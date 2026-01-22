package com.github.lowkkid.lodgecore.booking.mapper;

import com.github.lowkkid.lodgecore.booking.domain.entity.Booking;
import com.github.lowkkid.lodgecore.booking.model.BookingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDTO toDto(Booking booking);
}

