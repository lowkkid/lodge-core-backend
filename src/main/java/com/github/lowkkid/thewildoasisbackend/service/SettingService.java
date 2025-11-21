package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.dto.SettingDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Setting;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.mapper.SettingMapper;
import com.github.lowkkid.thewildoasisbackend.repository.SettingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;
    private final SettingMapper settingMapper;

    public List<SettingDTO> getAll() {
        return settingRepository.findAll().stream()
                .map(settingMapper::toDto)
                .collect(Collectors.toList());
    }

    public SettingDTO getById(Long id) {
        Setting setting = settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting with id " + id + " not found"));
        return settingMapper.toDto(setting);
    }

    @Transactional
    public SettingDTO create(SettingDTO settingDTO) {
        Setting setting = settingMapper.toEntity(settingDTO);
        Setting savedSetting = settingRepository.save(setting);
        return settingMapper.toDto(savedSetting);
    }

    @Transactional
    public SettingDTO update(Long id, SettingDTO settingDTO) {
        Setting existingSetting = settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting with id " + id + " not found"));
        Setting setting = settingMapper.toEntity(settingDTO);
        setting.setId(existingSetting.getId());
        Setting savedSetting = settingRepository.save(setting);
        return settingMapper.toDto(savedSetting);
    }

    public void delete(Long id) {
        Setting setting = settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting with id " + id + " not found"));
        settingRepository.delete(setting);
    }
}
