package com.github.lowkkid.lodgecore.setting.controller.impl;

import com.github.lowkkid.lodgecore.setting.controller.SettingApi;
import com.github.lowkkid.lodgecore.setting.model.SettingDTO;
import com.github.lowkkid.lodgecore.setting.service.SettingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
@AllArgsConstructor
public class SettingController implements SettingApi {

    private final SettingService settingService;

    @Override
    @GetMapping
    public ResponseEntity<SettingDTO> get() {
        SettingDTO settings = settingService.get();
        return ResponseEntity.ok(settings);
    }

    @Override
    @PutMapping
    public ResponseEntity<SettingDTO> update(@RequestBody @Valid SettingDTO settingDTO) {
        SettingDTO updatedSetting = settingService.update(settingDTO);
        return ResponseEntity.ok(updatedSetting);
    }
}
