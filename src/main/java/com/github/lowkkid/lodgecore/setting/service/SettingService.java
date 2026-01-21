package com.github.lowkkid.lodgecore.setting.service;

import com.github.lowkkid.lodgecore.setting.model.SettingDTO;

public interface SettingService {

    SettingDTO get();

    SettingDTO update(SettingDTO settingDTO);
}
