package com.github.lowkkid.lodgecore.setting.mapper;

import com.github.lowkkid.lodgecore.setting.domain.entity.Setting;
import com.github.lowkkid.lodgecore.setting.model.SettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingMapper {

    SettingDTO toDto(Setting setting);

    Setting toEntity(SettingDTO settingDTO);
}

