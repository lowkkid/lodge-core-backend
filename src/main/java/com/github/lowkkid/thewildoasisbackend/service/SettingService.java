package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.model.SettingDTO;

public interface SettingService {
    SettingDTO get();
    SettingDTO update(SettingDTO settingDTO);
}
