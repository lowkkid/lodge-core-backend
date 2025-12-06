package com.github.lowkkid.thewildoasisbackend.mapper;

import com.github.lowkkid.thewildoasisbackend.model.CabinCreateRequest;
import com.github.lowkkid.thewildoasisbackend.model.CabinDTO;
import com.github.lowkkid.thewildoasisbackend.model.CabinEditRequest;
import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CabinMapper {
    CabinDTO toDto(Cabin cabin);
    Cabin toEntity(CabinDTO cabinDTO);
    @Mapping(target = "image", ignore = true)
    Cabin toEntity(CabinCreateRequest cabinCreateRequest);
    @Mapping(target = "image", ignore = true)
    Cabin toEntity(CabinEditRequest cabinCreateRequest);
}

