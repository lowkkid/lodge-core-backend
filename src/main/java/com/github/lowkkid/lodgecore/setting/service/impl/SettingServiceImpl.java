package com.github.lowkkid.lodgecore.setting.service.impl;

import com.github.lowkkid.lodgecore.common.exception.NotFoundException;
import com.github.lowkkid.lodgecore.setting.domain.entity.Setting;
import com.github.lowkkid.lodgecore.setting.domain.repository.SettingRepository;
import com.github.lowkkid.lodgecore.setting.mapper.SettingMapper;
import com.github.lowkkid.lodgecore.setting.model.SettingDTO;
import com.github.lowkkid.lodgecore.setting.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;
    private final SettingMapper settingMapper;

    @Override
    public SettingDTO get() {
        return settingMapper.toDto(getSetting());
    }

    @Override
    @Transactional
    public SettingDTO update(SettingDTO settingDTO) {
        Setting existingSetting = getSetting();
        Setting setting = settingMapper.toEntity(settingDTO);
        setting.setId(existingSetting.getId());
        Setting savedSetting = settingRepository.save(setting);
        return settingMapper.toDto(savedSetting);
    }

    private Setting getSetting() {
        return settingRepository.findAll().stream()
                .findFirst().orElseThrow(() -> new NotFoundException("Settings with not found"));
    }
}