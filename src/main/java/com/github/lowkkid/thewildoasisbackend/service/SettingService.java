package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.entity.Setting;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.repository.SettingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;

    public List<Setting> getAll() {
        return settingRepository.findAll();
    }

    public Setting getById(Long id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting with id " + id + " not found"));
    }

    public Setting create(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting update(Long id, Setting setting) {
        Setting existingSetting = getById(id);
        setting.setId(existingSetting.getId());
        return settingRepository.save(setting);
    }

    public void delete(Long id) {
        Setting setting = getById(id);
        settingRepository.delete(setting);
    }
}
