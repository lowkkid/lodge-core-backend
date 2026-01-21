package com.github.lowkkid.lodgecore.cabin.mapper;

import com.github.lowkkid.lodgecore.cabin.domain.entity.Cabin;
import com.github.lowkkid.lodgecore.cabin.model.CabinCreateRequest;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.cabin.model.CabinEditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CabinMapper {

    CabinDTO toDto(Cabin cabin);

    @Mapping(target = "image", ignore = true)
    Cabin toEntity(CabinCreateRequest cabinCreateRequest);

    @Mapping(target = "image", ignore = true)
    Cabin toEntity(CabinEditRequest cabinCreateRequest);
}

