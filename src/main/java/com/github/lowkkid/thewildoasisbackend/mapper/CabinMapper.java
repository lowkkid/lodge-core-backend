package com.github.lowkkid.thewildoasisbackend.mapper;

import com.github.lowkkid.thewildoasisbackend.dto.CabinDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CabinMapper {
    CabinDTO toDto(Cabin cabin);
    Cabin toEntity(CabinDTO cabinDTO);
}

