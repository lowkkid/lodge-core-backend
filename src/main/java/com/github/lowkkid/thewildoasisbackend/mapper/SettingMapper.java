package com.github.lowkkid.thewildoasisbackend.mapper;

import com.github.lowkkid.thewildoasisbackend.dto.SettingDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Setting;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingMapper {
    SettingDTO toDto(Setting setting);
    Setting toEntity(SettingDTO settingDTO);
}

