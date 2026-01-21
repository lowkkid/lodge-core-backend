package com.github.lowkkid.lodgecore.cabin.controller;

import com.github.lowkkid.lodgecore.cabin.model.CabinCreateRequest;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.cabin.model.CabinEditRequest;
import com.github.lowkkid.lodgecore.cabin.service.CabinService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CabinDTO> create(@ModelAttribute @Valid CabinCreateRequest request) {
        CabinDTO createdCabin = cabinService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCabin);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CabinDTO> update(@PathVariable Long id, @ModelAttribute CabinEditRequest request) {
        CabinDTO updatedCabin = cabinService.update(id, request);
        return ResponseEntity.ok(updatedCabin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cabinService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

