package com.github.lowkkid.thewildoasisbackend.mapper;

import com.github.lowkkid.thewildoasisbackend.dto.BookingDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Booking;
import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import com.github.lowkkid.thewildoasisbackend.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDTO toDto(Booking booking);

    Booking toEntity(BookingDTO bookingDTO);
//    @Mapping(target = "cabin", source = "cabinId", qualifiedByName = "cabinIdToCabin")
//    @Mapping(target = "guest", source = "guestId", qualifiedByName = "guestIdToGuest")
//    Booking toEntity(BookingDTO bookingDTO);

    @Named("cabinIdToCabin")
    default Cabin cabinIdToCabin(Long cabinId) {
        if (cabinId == null) {
            return null;
        }
        return Cabin.builder().id(cabinId).build();
    }

    @Named("guestIdToGuest")
    default Guest guestIdToGuest(Long guestId) {
        if (guestId == null) {
            return null;
        }
        return Guest.builder().id(guestId).build();
    }
}

