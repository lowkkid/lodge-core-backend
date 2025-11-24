package com.github.lowkkid.thewildoasisbackend.controller;

import com.github.lowkkid.thewildoasisbackend.dto.CabinCreateRequest;
import com.github.lowkkid.thewildoasisbackend.dto.CabinDTO;
import com.github.lowkkid.thewildoasisbackend.service.CabinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cabins")
@AllArgsConstructor
public class CabinController {

    private final CabinService cabinService;

    @GetMapping
    public ResponseEntity<List<CabinDTO>> getAll() {
        List<CabinDTO> cabins = cabinService.getAll();
        return ResponseEntity.ok(cabins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinDTO> getById(@PathVariable Long id) {
        CabinDTO cabin = cabinService.getById(id);
        return ResponseEntity.ok(cabin);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CabinDTO> create(@ModelAttribute CabinCreateRequest request) {
        CabinDTO createdCabin = cabinService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCabin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinDTO> update(@PathVariable Long id, @RequestBody CabinDTO cabinDTO) {
        CabinDTO updatedCabin = cabinService.update(id, cabinDTO);
        return ResponseEntity.ok(updatedCabin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cabinService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

