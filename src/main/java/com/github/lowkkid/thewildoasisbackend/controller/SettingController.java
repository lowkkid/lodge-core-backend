package com.github.lowkkid.thewildoasisbackend.controller;

import com.github.lowkkid.thewildoasisbackend.dto.SettingDTO;
import com.github.lowkkid.thewildoasisbackend.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings")
@AllArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping
    public ResponseEntity<List<SettingDTO>> getAll() {
        List<SettingDTO> settings = settingService.getAll();
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettingDTO> getById(@PathVariable Long id) {
        SettingDTO setting = settingService.getById(id);
        return ResponseEntity.ok(setting);
    }

    @PostMapping
    public ResponseEntity<SettingDTO> create(@RequestBody SettingDTO settingDTO) {
        SettingDTO createdSetting = settingService.create(settingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSetting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettingDTO> update(@PathVariable Long id, @RequestBody SettingDTO settingDTO) {
        SettingDTO updatedSetting = settingService.update(id, settingDTO);
        return ResponseEntity.ok(updatedSetting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        settingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}