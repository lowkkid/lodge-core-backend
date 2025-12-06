package com.github.lowkkid.thewildoasisbackend.controller;

import com.github.lowkkid.thewildoasisbackend.model.SettingDTO;
import com.github.lowkkid.thewildoasisbackend.service.SettingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
@AllArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping
    public ResponseEntity<SettingDTO> get() {
        SettingDTO settings = settingService.get();
        return ResponseEntity.ok(settings);
    }

    @PutMapping()
    public ResponseEntity<SettingDTO> update(@RequestBody @Valid SettingDTO settingDTO) {
        SettingDTO updatedSetting = settingService.update(settingDTO);
        return ResponseEntity.ok(updatedSetting);
    }
}